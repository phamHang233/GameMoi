package object;

import java.awt.Color;

import entity.Entity;

import screen.GamePanel;

public class OBJ_Rock extends Projectile{
    GamePanel gp;
    public OBJ_Rock (GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "Rock";
        speed = 8;
        maxHp = 80;
        hp = maxHp;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }

    public void getImage() {
        up1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/rock_down_1", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if(user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource; 
    }
    public void subtractResource(Entity user) {
        user.ammo -= useCost;
    }
    public Color getParticleColor() {
 		 Color color = new Color(40, 50, 30);
 		 return color;
 	 }
    public int getParticleSize() {
 		 int size = 10;  // 6 pixels
 		 return size;
 	 }
 	 public int getParticleSpeed() {
 		 int speed = 1;
 		 return speed;
 	 }
 	 public int getParticleMaxHp() {
 		 int maxHp = 20;
 		 return maxHp;
 	 }
}
