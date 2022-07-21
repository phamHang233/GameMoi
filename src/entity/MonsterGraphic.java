package entity;
import java.util.Random;

import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import screen.GamePanel;

public class MonsterGraphic extends EntityGraphic{

	public Monster_GreenSlime monGreen;
    public MonsterGraphic(GamePanel gp) {
    	
        super(gp);        
        monGreen = new Monster_GreenSlime();
        direction = "down";
        type = type_monster;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30; 
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        projectile = new OBJ_Rock(gp);

        getImage(); 
    }
    public void getImage() {
    	up1 = setup("/res/monster/monster_1_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/res/monster/monster_1_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/res/monster/monster_1_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/res/monster/monster_1_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/res/monster/monster_1_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/res/monster/monster_1_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/res/monster/monster_1_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/res/monster/monster_1_right_2",gp.tileSize, gp.tileSize);
	}
    public void setAction() {

        actionLockCounter++;
    	
    	if( actionLockCounter == 120) {
    		Random random = new Random();
    		int i = random.nextInt(100) + 1; //pick up a number from 1 to 100
    		if(i <= 25) {
    			direction = "up";
    		}
    		if(i > 25 && i <= 50) {
    			direction = "down";
    		}
    		if(i > 50 && i <= 75) {
    			direction = "left";
    		}
    		if(i > 75 && i <= 100) {
    			direction = "right";
    		}
    		
    		actionLockCounter = 0;
    	}
    	int i = new Random().nextInt(100) + 1;
        if(i > 99 && this.projectile.isAlive() == false && shotAvailableCounte == 30) {
        	this.projectile.set(this.worldX, this.worldY, this.direction, true, monGreen);
            gp.projectileList.add(projectile);
            shotAvailableCounte = 0;
        }
    }
    public void damageReaction() {
    	actionLockCounter = 0;
    	direction = gp.playerGra.direction;
    	
    }
    public void checkDrop() {
  	   	// CAST A DIE
        int i = new Random().nextInt(100)+1;
        // SET THE MONSTER DROP
        if(i < 50) {
        	monGreen.dropItem(new OBJ_Coin_Bronze(gp), this, gp);
      	   
        }
        if(i >= 50 && i < 75) {
        	monGreen.dropItem(new OBJ_Heart(gp), this, gp);
        }
        if(i >= 75 && i < 100) {
        	monGreen.dropItem(new OBJ_ManaCrystal(gp), this, gp);
        }
   }
    
}
