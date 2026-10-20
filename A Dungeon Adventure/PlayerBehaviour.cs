using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerBehaviour : MonoBehaviour
{
    [SerializeField] private float playerSpeed = 2f;
    [SerializeField] private float rotateSpeed = 0.5f;
    [SerializeField] private float dashSpeed = 15f;
    private float playerSize = 1f;
    private float interactDistance = 2f;
    private int maxHp = 6;
    private int hp;
    private int lives;
    private AnimationScript animation;
    private Vector3 zeroVector = new Vector3(0, 0, 0);
    private Vector3 startDirection = new Vector3(0, 0, 1);
    private Vector3 interactDirection;
    private bool defend;
    private bool moving;
    private bool defendObj;
    private bool superShieldAquired;
    private bool attack;
    private bool attackCooldown;
    private bool dash;
    private bool dashCooldown;
    private bool animateHit;
    private bool interacting;
    private bool alive;
    private int differentAttack;
    public GameObject hitPrefab;
    public GameObject defendPrefab;
    public GameObject interactPrefab;
    private GameObject interactIcon;
    public GameObject healthBar;
    private HealthBarScript hpScript;
    public GameObject dashBar;
    private HealthBarScript dashScript;
    public GameObject level;

    public AudioSource walkSound, dashSound, attackSound1, attackSound2, attackSound3, hitSound;

    void Awake() {
        animation = gameObject.GetComponentInChildren<AnimationScript>();
        defend = false;
        defendObj = false;
        superShieldAquired = false;
        attack = false;
        dash = false;
        attackCooldown = false;
        dashCooldown = false;
        animateHit = true;
        interacting = false;
        alive = true;
        differentAttack = 0;
        hp = maxHp;
        lives = 3;
        hpScript = healthBar.GetComponent<HealthBarScript>();
        hpScript.SetMaxHealth(maxHp);
        dashScript = dashBar.GetComponent<HealthBarScript>();
        dashScript.SetMaxHealth(10);
    }

    void Start() {

    }

    void Update() {
        if(alive) {
            Interact();
        }
    }

    void FixedUpdate()
    {
        if(alive) {
            MovePlayer();
            Defend();
            Attack();
            Dash();
        }
    }

    private void MovePlayer() {

        Vector3 direction = new Vector3(0, 0, 0);

        if(Input.GetKey(KeyCode.W) || Input.GetKey(KeyCode.UpArrow)) {
            direction.z = 1;
        }
        if(Input.GetKey(KeyCode.S) || Input.GetKey(KeyCode.DownArrow)) {
            direction.z = -1;
        }
        if(Input.GetKey(KeyCode.A) || Input.GetKey(KeyCode.LeftArrow)) {
            direction.x = -1;
        }
        if(Input.GetKey(KeyCode.D) || Input.GetKey(KeyCode.RightArrow)) {
            direction.x = 1;
        }
        direction = direction.normalized;
        UpdateInteractionDirection(direction);
        if(!attack && !defend) {
            bool canMove = !Physics.Raycast(transform.position, direction, out RaycastHit rayHit, playerSize);
            if(!canMove && rayHit.transform.TryGetComponent(out PuzzlePlate plate)) {
                canMove = true;
            }
            if(canMove) {
                if(!dash){
                    GetComponent<Rigidbody>().position += direction * playerSpeed * Time.deltaTime;
                }
                if(dash){
                    GetComponent<Rigidbody>().position += direction * dashSpeed * Time.deltaTime;
                }
            }
            if(direction != zeroVector) {
                animation.walking = true;
            } else {
                animation.walking = false;
            }
        }
        if(direction != zeroVector) {
            Quaternion rotate = Quaternion.LookRotation(direction);
            transform.rotation = Quaternion.Slerp(transform.rotation, rotate, Time.deltaTime*rotateSpeed);
            if(!defend) {
                if(dash) {
                    dashSound.enabled = true;
                    walkSound.enabled = false;
                }
                if(!dash) {
                    walkSound.enabled = true;
                    dashSound.enabled = false;
                }
            }
        }  else {
                walkSound.enabled = false;
                dashSound.enabled = false;
        }
    }

    private void Interact() {
        if(Physics.Raycast(transform.position, interactDirection, out RaycastHit hit, interactDistance)) {
            if(hit.transform.TryGetComponent(out PuzzleOrbScript orb)) {
                if(!interacting && orb.GetActive()) {
                    Vector3 pos = orb.transform.position;
                    pos.y += 3f;
                    interactIcon = Instantiate(interactPrefab, pos, Quaternion.identity);
                    interacting = true;
                }
                if(Input.GetKeyDown(KeyCode.E)|| Input.GetKeyDown(KeyCode.RightAlt)) {
                    orb.InteractWith();
                    if(!orb.GetActive()) {
                        Destroy(interactIcon);
                    }
                }
            } else {
                Destroy(interactIcon);
                interacting = false;
            }
        } else {
            Destroy(interactIcon);
            interacting = false;
        }
    }

    private void Defend() {
        defend = (Input.GetKey(KeyCode.Q) || Input.GetKey(KeyCode.RightAlt));
        if(defend && !superShieldAquired) {
            animation.defending = true;
            walkSound.enabled = false;
        }
        if(defend && superShieldAquired) {
            animation.defending = true;
            walkSound.enabled = false;
            Vector3 defPos = transform.position;
            defPos.y +=0.5f;
            if(!defendObj) {
                GameObject superShield = Instantiate(defendPrefab, defPos, Quaternion.identity);
                SuperDefense shieldScript = superShield.GetComponent<SuperDefense>();
                shieldScript.SetPlayer(this);
                defendObj = true;
            }
        }
        if(!defend){
            defendObj = false;
            animation.defending = false;
        }
    }

    private void Attack() {
        attack = Input.GetKey(KeyCode.Space);
        if(attack && !attackCooldown) {
            animation.attackInt = differentAttack;
            animation.attacking = true;
            Physics.autoSimulation = false;
            Physics.Simulate(Time.fixedDeltaTime);
            Physics.autoSimulation = true;
            if(Physics.Raycast(transform.position, interactDirection, out RaycastHit hit, interactDistance)) {
                Vector3 hitPos = transform.position + interactDirection;
                hitPos.y +=1;
                if(hit.transform.TryGetComponent(out SpiderScript spider)) {
                    if(!spider.CheckDead()) {
                        if(animateHit) {
                            Instantiate(hitPrefab, hitPos, Quaternion.identity);
                            animateHit = false;
                            hitSound.enabled = true;
                            StartCoroutine(HitCooldown());
                        }
                        spider.GetHit();
                    }
                }
                if(hit.transform.TryGetComponent(out BossOrbScript bossOrb)) {
                    if(animateHit) {
                        Instantiate(hitPrefab, hitPos, Quaternion.identity);
                        animateHit = false;
                        hitSound.enabled = true;
                        StartCoroutine(HitCooldown());
                    }
                    bossOrb.EnrageBoss();
                }

                if(hit.transform.TryGetComponent(out BossScript boss)) {
                    boss.GetHit();
                }
            }
            differentAttack++;
            if(differentAttack >= 3) {
                differentAttack = 0;
            }
            attackCooldown = true;
            StartCoroutine(AttackCooldown());
        }
    }

    private void Dash() {
        if(!dashCooldown){
            dash = (Input.GetKey(KeyCode.LeftShift));
            if(dash) {
                dashCooldown = true;
                animation.dashing = true;
                dashScript.SetHealth(0);
                StartCoroutine(DashCooldown());
            }
        }
    }

    private IEnumerator DashCooldown() {
        yield return new WaitForSeconds(0.25f);
        dash = false;
        animation.dashing = false;
        for(int i = 0; i < 10; i++) {
            dashScript.SetHealth(i+1);
            yield return new WaitForSeconds(0.15f);
        }
        while((Input.GetKey(KeyCode.LeftShift))) {
            yield return null;
        }
        dashCooldown = false;
    }

    private IEnumerator AttackCooldown() {
        if(differentAttack == 0) {
            attackSound1.enabled = true;
        }
        if(differentAttack == 1) {
            attackSound2.enabled = true;
        }
        if(differentAttack == 2) {
            attackSound3.enabled = true;
        }
        yield return new WaitForSeconds(0.3f);
        attackSound1.enabled = false;
        attackSound2.enabled = false;
        attackSound3.enabled = false;
        yield return new WaitForSeconds(0.2f);
        attackCooldown = false;
    }

    private IEnumerator HitCooldown() {
        yield return new WaitForSeconds(0.5f);
        hitSound.enabled = false;
        animateHit = true;
    }

    private void UpdateInteractionDirection(Vector3 direction) {
        if(direction != zeroVector) {
            interactDirection = direction;
        }
    }

    public void GetHit(Vector3 enemyPos) {
        if(alive) {
            if(!defend) {
                animation.takeHit = true;
                hp -= 1;
                hpScript.SetHealth(hp);
            }
            if(defend && !superShieldAquired) {
                animation.takeHit = true;

            }
            if(defend && superShieldAquired) {

            }
            if(hp <= 0) {
                alive = false;
                StartCoroutine(Restart());
            }
        }
    }

    private IEnumerator Restart() {
        lives -= 1;
        walkSound.enabled = false;
        animation.walking = false;
        animation.dashing = false;
        animation.defending = false;
        animation.dead = true;
        yield return new WaitForSeconds(3f);
        transform.position = zeroVector;
        UpdateInteractionDirection(startDirection);
        Quaternion rotate = Quaternion.LookRotation(startDirection);
        transform.rotation = rotate;
        if(lives >= 0) {
            alive = true;
            animation.dead = false;
            hp = maxHp;
            hpScript.SetMaxHealth(maxHp);
        } else {
            LevelScript levelScript = level.GetComponent<LevelScript>();
            levelScript.GameOver();
        }
    }

    private void OnCollisionEnter(Collision collision)
    {
        if(collision.transform.TryGetComponent(out HealthHeartScript health)) {
            health.TakeHeart();
            hp = maxHp;
            hpScript.SetHealth(hp);
        }

    }

    public bool CheckDefending() {
        return defend;
    }

    public int CheckHP() {
        return hp;
    }

    public bool GetAlive() {
        return alive;
    }

    public int GetLives() {
        return lives;
    }

    public bool GetDash() {
        return dash;
    }
}
