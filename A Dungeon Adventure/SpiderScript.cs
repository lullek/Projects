using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpiderScript : MonoBehaviour
{
    [SerializeField] private float spiderSpeed = 3f;
    [SerializeField] private float spiderRotationSpeed = 15f;
    private float spiderSize = 0.7f;
    public Transform player;
    private PlayerBehaviour playerBehaviour;
    private float distance;
    private Vector3 attackDistance;
    private Vector3 interactDirection;
    private Vector3 zeroVector = new Vector3(0, 0, 0);
    private Vector3 posVector;
    private bool moving;
    private bool attackReady;
    private bool vulnerable;
    private bool takenHit;
    private bool dead;
    private int attack;
    private SpiderAnimation animation;
    public GameObject hitPrefab;
    public GameObject defendPrefab;

    void Start()
    {
        playerBehaviour = player.GetComponent<PlayerBehaviour>();
        posVector = transform.position;
        attackReady = true;
        vulnerable = false;
        dead = false;
        attack = 0;
        animation = transform.Find("spider_green").GetComponent<SpiderAnimation>();
        CreateShield();
    }

    void Update()
    {
        if(!dead) {
            if(!vulnerable) {
                if(playerBehaviour.GetAlive()) {
                    CheckPlayerDistance();
                } else {
                    animation.walking = false;
                }
            }
        }
    }

    private void CheckPlayerDistance() {
        distance = Vector3.Distance(transform.position, player.position);
        if(distance < 10) {
            AttackPlayer();
        }
    }

    private void AttackPlayer() {
        if(attackReady) {
            Vector3 playerPos = (player.position - transform.position);
            Quaternion rotate = Quaternion.LookRotation(playerPos);
            transform.rotation = Quaternion.Slerp(transform.rotation, rotate, Time.deltaTime*spiderRotationSpeed);
            Vector3 direction = playerPos;
            bool canMove = !Physics.Raycast(transform.position, direction, spiderSize);
            UpdateInteractionDirection(direction);
            if(canMove) {
                GetComponent<Rigidbody>().position += direction * spiderSpeed * Time.deltaTime;
                posVector = transform.position;
                animation.walking = true;
            }
            if(!canMove) {
                animation.walking = false;
            }

            if(distance < 2f) {
                animation.walking = false;
                animation.attackInt = attack;
                animation.attacking = true;
                Vector3 hitPos = transform.position + interactDirection;
                hitPos.y +=1;
                Instantiate(hitPrefab, hitPos, Quaternion.identity);
                playerBehaviour.GetHit(transform.position);
                StartCoroutine(AttackCooldown());
                attackReady = false;
            }
        }
    }

    private IEnumerator AttackCooldown() {
        yield return new WaitForSeconds(0.2f);
        vulnerable = true;
        animation.attacking = false;
        attack++;
        if(attack > 1) {
            attack = 0;
        }
        yield return new WaitForSeconds(1.5f);
        attackReady = true;
        vulnerable = false;
        CreateShield();
    }

    public void GetHit() {
        if(vulnerable) {
            animation.attacking = false;
            animation.walking = false;
            dead = true;
            Destroy(GetComponent<Rigidbody>());
            Destroy(GetComponent<CapsuleCollider>());
            animation.takeHit = true;
            vulnerable = false;
        }
    }

    private void UpdateInteractionDirection(Vector3 direction) {
        if(direction != zeroVector) {
            interactDirection = direction.normalized;
        }
    }

    public bool CheckDead() {
        return dead;
    }

    public bool CheckVulnerable() {
        return vulnerable;
    }

    public Vector3 GetPosition() {
        return posVector;
    }

    private void CreateShield() {
        Vector3 defPos = transform.position;
        defPos.y += 0.5f;
        GameObject spiderShield = Instantiate(defendPrefab, defPos, Quaternion.identity);
        SpiderShield shieldScript = spiderShield.GetComponent<SpiderShield>();
        shieldScript.SetSpider(this);
    }
}
