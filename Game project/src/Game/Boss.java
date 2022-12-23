public class Boss extends Enemy {

    private int[] bossHp;
    boolean damageAble;
    int damageImmuneTick;
    int bossProjectileX;
    int bossProjectileY;
    int shootingDirection;
    boolean shooting;

    public Boss(int x, int y, int healthPoints, int speed) {
        super(x, y, healthPoints, speed);
        rest = 0;
        alive = true;
        bossHp = new int[hp/2];
        damageAble = true;
        damageImmuneTick = 0;
        shooting = false;

        for(int i = 0; i < bossHp.length; i ++) {
            bossHp[i] = 2;
        }

    }

    @Override
    public void enemyHit() {
        if(damageAble) {
            hp -= 1;
            if (bossHp[2] != 0) {
        bossHp[2] = bossHp[2] - 1;
         } else if (bossHp[1] != 0) {
        bossHp[1] = bossHp[1] - 1;
         } else {
        bossHp[0] = bossHp[0] - 1;
            }
            damageAble = false;
            damageImmuneTick = 10;
            }
        }

        public boolean getDamageAble() {
            return damageAble;
        }

        public int getDamageImmuneTick() {
            return damageImmuneTick;
        }

        public void refreshDamageImmuneTick() {
          damageImmuneTick -= 1;
          if(damageImmuneTick == 0) {
              damageAble = true;
          }
        }

        public int[] getBossHp() {
            return bossHp;
        }

        public boolean getShooting() {
            return shooting;
        }

        public int getBossProjectileX() {
            return bossProjectileX;
        }

        public int getBossProjectileY() {
             return bossProjectileY;
        }

        public void setShootingFalse() {
            shooting = false;
        }

        public void shoot(int playerX) {
            if(shooting) {
            bossProjectileX += shootingDirection * walkingSpeed;
            }

            if(!shooting) {
                if(xPos > playerX){
                shootingDirection = -1;
                 } else {
                shootingDirection = 1;
                }

            bossProjectileX = xPos;
            bossProjectileY = yPos;
            shooting = true;
            rest = 25;
        }
    }
    @Override
    public void moveX(int x) {
        if(rest == 0) {
            xPos = xPos + x * walkingSpeed;
        } else {
            rest -= 1;
        }

    }

    @Override
    public void moveY(int y) {
        if(rest == 0) {
            yPos = yPos + y * walkingSpeed;
        } else {
            rest -= 1;
        }
    }


}