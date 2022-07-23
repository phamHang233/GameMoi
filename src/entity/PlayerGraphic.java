package entity;
import java.awt.*;  
import screen.GamePanel;
import java.awt.image.BufferedImage;

import move.KeyHandler;
import object.OBJ_Fireball;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.SuperObjectGraphic;

public class PlayerGraphic  extends EntityGraphic {

    KeyHandler keyH;
    private final int screenX; // Player's position on screen
    private final int screenY;
    public SuperObjectGraphic currentWeapon;
	public SuperObjectGraphic currentShield;
    private boolean attackCanceled = false;
    public Player player;

    public PlayerGraphic(GamePanel gp, KeyHandler keyH) {
    	super(gp);
     	screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        this.keyH = keyH;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp); // the total defense value is decided by dexterity and shield
        attackArea = currentWeapon.attackArea;
        projectileGra = new OBJ_Fireball(gp);
        player = new Player();
        solidArea = new Rectangle(); // override    
        solidArea.x = 14;
        solidArea.y = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20; 
        direction = "down";
        getPlayerImage();
        getPlayerAttackImage();
        setDefaultPositions();
        setItems();
        player.attack = getAttack();
        player.defense = getDefense();
    
    }
  public void setDefaultPositions() {
	  	worldX = gp.tileSize * 30;
	  	worldY = gp.tileSize * 21;
}
  public void setItems(){
  	player.inventory.clear();
  	player.inventory.add(this.currentWeapon);
  	player.inventory.add(this.currentShield);
  }
    public void getPlayerImage() {
        up1 = setup("/res/player/mario_back_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/mario_back_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/mario_front_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/mario_front_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/mario_run_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/mario_run_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/mario_run_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/mario_run_right_2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerAttackImage() {
        if(currentWeapon.obj.getType() ==currentWeapon.obj.type_sword) {
		attackUp1 = setup("/res/player/smb_attack_up_1",gp.tileSize, gp.tileSize*2);
		attackUp2 = setup("/res/player/smb_attack_up_2",gp.tileSize, gp.tileSize*2);
		attackDown1 = setup("/res/player/smb_attack_down_1",gp.tileSize, gp.tileSize*2);
		attackDown2 = setup("/res/player/smb_attack_down_2",gp.tileSize, gp.tileSize*2);
		attackLeft1 = setup("/res/player/smb_attack_left_1",gp.tileSize*2, gp.tileSize);
		attackLeft2 = setup("/res/player/smb_attack_left_2",gp.tileSize*2, gp.tileSize);
		attackRight1 = setup("/res/player/smb_attack_right_1",gp.tileSize*2, gp.tileSize);
		attackRight2 = setup("/res/player/smb_attack_right_2",gp.tileSize*2, gp.tileSize);
    }
//        if(currentWeapon.obj.getType() == currentWeapon.obj.type_axe) {
//        	attackUp1 = setup("/res/player/boy_axe_up_1",gp.tileSize, gp.tileSize*2);
//    		attackUp2 = setup("/res/player/boy_axe_up_2",gp.tileSize, gp.tileSize*2);
//    		attackDown1 = setup("/res/player/boy_axe_down_1",gp.tileSize, gp.tileSize*2);
//    		attackDown2 = setup("/res/player/boy_axe_down_2",gp.tileSize, gp.tileSize*2);
//    		attackLeft1 = setup("/res/player/boy_axe_left_1",gp.tileSize*2, gp.tileSize);
//    		attackLeft2 = setup("/res/player/boy_axe_left_2",gp.tileSize*2, gp.tileSize);
//    		attackRight1 = setup("/res/player/boy_axe_right_1",gp.tileSize*2, gp.tileSize);
//    		attackRight2 = setup("/res/player/boy_axe_right_2",gp.tileSize*2, gp.tileSize);
//        }
    }
    public int getAttack() {
		this.attackArea = this.currentWeapon.attackArea;
		return player.attack = player.strength * this.currentWeapon.obj.getAttackValue();
	}
	public int getDefense() {
		return player.defense = player.dexteriry * this.currentShield.obj.getDefenseValue();
	}
    public void update() { // is call 60 times per second
    	if (attacking == true) {
    		player.attacking(gp, this);
    		
    	}
    	else if(keyH.getUpPressed() == true || keyH.getDownPressed() == true
    		||keyH.getLeftPressed() == true || keyH.getRightPressed() == true
    		|| keyH.enterPressed == true) {
    		if	   (keyH.getUpPressed() == true) {direction = "up"; }
    		else if(keyH.getDownPressed() == true) {direction = "down"; }
    		else if(keyH.getLeftPressed ()== true) {direction = "left"; }
    		else if(keyH.getRightPressed() == true) {direction = "right"; }

//            // CHECK TILE COLLISION
    		collisionOn = false;
    		gp.cChecker.checkTile(this, this.player);
    		
//    		// CHECK OBJECT COLLISION
    		int objIndex = gp.cChecker.checkObject(this, this.player, true);
    		player.pickUpObject(objIndex, gp);
    		
//    		CHECH MONSTER COLLISION
    		int monsterIndex = gp.cChecker.checkEntity(this, this.player, gp.monsterGra);
    		player.contactMonster(monsterIndex, gp, this);
        	
//    		//CHECK NPC COLLISTION		
    		int npcIndex = gp.cChecker.checkEntity(this, this.player, gp.oldMan_Gra);
    		player.interactNPC(npcIndex, gp, this);
    		
    		//CHECK PRIN COLLISTION
    		int prinIndex = gp.cChecker.checkEntity(this, this.player, gp.princess_Gra);
    		player.interactPrin(prinIndex, gp, this);
    		
    		//CHECK EVENT
    		gp.eHandler.checkEvent();
    	
   		if (collisionOn == false && keyH.enterPressed == false) {			
    			switch(direction) {
                	case "up": worldY -= player.speed; break;
                	case "down": worldY += player.speed; break; 
                	case "left": worldX -= player.speed; break;
                	case "right": worldX += player.speed; break;
            	}
            }
   		if(keyH.enterPressed == true && attackCanceled == false) {
			gp.playSE(4);
			attacking = true;
			spriteCounter = 0;

		}
   		attackCanceled = false;
   		gp.keyH.enterPressed =(false);

        spriteCounter ++;
    	if(spriteCounter > 12) { // change image every 10 frames
    		if(spriteNum == 1 ) {
    			spriteNum = 2;
    		}
    		else if(spriteNum == 2) {
    			spriteNum = 1;
    		}
    		spriteCounter = 0;
    	}
	}
		if(gp.keyH.shotKeyPressed  == true && this.projectileGra.isAlive() == false 
				&& shotAvailableCounte == 30 && this.projectileGra.haveResource(player) == true) {
    		
			// SET DEFAULT COORDINATES, DIRECTION AND USER
			this.projectileGra.set(this.worldX, this.worldY, direction, true, player);
//			// SUBSTRACT THE COST(MANA, AMMO, ETC)
			this.projectileGra.subtractResource(this.player);
//			// ADD IT TO THE LIST
			gp.projectileGraList.add(this.projectileGra);
			gp.playSE(9);
			shotAvailableCounte = 0;
		}
//        // This need to be outside of key if statement! 
        if(this.invincible ==  true) {
            invincibleCounter ++;
            if(invincibleCounter > 60) {
            	this.invincible = false;
                invincibleCounter = 0;
            }
        }
		
//      }
	  	if(shotAvailableCounte < 30) {
			shotAvailableCounte ++;
		}
	  	if(player.hp > player.maxHp) {
	  		player.hp = player.maxHp;
	  	}
	  	if(player.mana > player.maxMana) {
	  		player.mana = player.maxMana;
	  	}
	  	if(player.hp <= 0) {
	  		gp.gameState = gp.gameOverState;
	  		gp.ui.commandNum = -1;
	  		gp.stopMusic();
	  		gp.playSE(11);
	  	}
}
   

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
		int tempScreenY = screenY;	
		switch(direction) {
		case "up": 
			if(this.attacking == false) {
				if(spriteNum == 1) image = up1;
				if(spriteNum == 2) image = up2;
			}
			if(this.attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) image = attackUp1;
				if(spriteNum == 2) image = attackUp2;
			}
			break;
		case "down":
			if(this.attacking == false) {
				if(spriteNum == 1) image = down1;
				if(spriteNum == 2) image = down2;
			}
			if(this.attacking == true) {
				if(spriteNum == 1) image = attackDown1;
				if(spriteNum == 2) image = attackDown2;
			}
			
			break;
		case "left":
			if(this.attacking == false) {
				if(spriteNum == 1) image = left1;
				if(spriteNum == 2) image = left2;
			}
			if(this.attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) image = attackLeft1;
				if(spriteNum == 2) image = attackLeft2;
			}
			break;
		case "right":
			if(this.attacking == false) {
				if(spriteNum == 1)  image = right1;
				if(spriteNum == 2)  image = right2;
			}
			if(this.attacking == true) {
				if(spriteNum == 1) image = attackRight1;
				if(spriteNum == 2) image = attackRight2;
			}
			break;

		}

        int x = screenX;
        int y = screenY;
        
        if(x > this.worldX) {
        	x = this.worldX;
        }
        if(y > this.worldY) {
        	y = this.worldY;
        }
        int rightOffset = gp.screenWidth - screenX;
		if(rightOffset > gp.worldWidth -this.worldX) {
			x = gp.screenWidth - (gp.worldWidth - this.worldX);
		}
		int bottomOffset = gp.screenHeight - screenY;
		if(bottomOffset > gp.worldHeigth - this.worldY) {
			y = gp.screenHeight - (gp.worldHeigth - this.worldY);
		}
        
		if(this.invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null); 

        // Reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


    }
    public int getScreenX( ) { return screenX;}
    public int getScreenY( ) { return screenY;}
    public void setAttackCanceled(boolean attackCanceled)
    {
    	this.attackCanceled = attackCanceled;
    }
    
}
