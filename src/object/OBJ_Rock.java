package object;

import java.awt.Color;

import entity.Entity;

import screen.GamePanel;

public class OBJ_Rock extends ProjectileGraphic{
    public OBJ_Rock (GamePanel gp) {
    	super(gp);
        projectile.name = "Rock";
        projectile.speed = 8;
        projectile.maxHp = 80;
        projectile.hp = projectile.maxHp;
        projectile.attack = 2;
        projectile.useCost = 1;
        projectile.alive = false;
        getImage();
    }

    public void getImage() {
    	up1 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
        up2 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
        down1 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
        down2 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
        left1 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
        left2 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
        right1 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
        right2 = setup("/res/projectile/bomb_4", gp.tileSize, gp.tileSize);
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if(user.ammo >= projectile.useCost) {
            haveResource = true;
        }
        return haveResource; 
    }
    public void subtractResource(Entity user) {
        user.ammo -= projectile.useCost;
    }
}
