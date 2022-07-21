package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import screen.GamePanel;

public class Projectile extends SuperObject{

	protected Entity user;
	protected GamePanel gp;
	protected boolean alive;
	protected int hp, maxHp, attack ,spriteCounter, useCost;
	protected int spriteNum=1;
	protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	protected String direction;
	 

    public Projectile (GamePanel gp) {
    	this.gp= gp;
    
    	}
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.alive = alive;
        this.user = user;
        this.direction = direction;
        this.hp = this.maxHp; // reset the life(hp) to the max value everytime you shoot it 
    }
    
    public void update() {

        if(user == gp.playerGra.player) {
            int monsterIndex = gp.cChecker.checkProjectile(this, gp.monsterGra);
            if(monsterIndex != 999) {
                gp.playerGra.player.damageMonster(monsterIndex, attack, gp, gp.playerGra);
                alive = false;  
            }
        }
        if(user != gp.playerGra.player) {
            boolean contactPlayer = gp.cChecker.checkPlayerProjectile(this);
            if(gp.playerGra.getInvincible() == false && contactPlayer == true) {
                user.damagePlayer(attack, gp);
                alive = false;
            } 

        }
         switch(direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }
        hp --;
        if(hp <= 0) {
            alive = false;
        }
       spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user) {
    }
    public void draw(Graphics2D g2) { 
    	BufferedImage image = null;	
    	int screenX = worldX - gp.playerGra.worldX + gp.playerGra.getScreenX();
        int screenY = worldY - gp.playerGra.worldY + gp.playerGra.getScreenY();

        if(	worldX + gp.tileSize > gp.playerGra.worldX - gp.playerGra.getScreenX() &&
              worldX - gp.tileSize < gp.playerGra.worldX + gp.playerGra.getScreenX() &&
              worldY + gp.tileSize > gp.playerGra.worldY - gp.playerGra.getScreenY() &&
              worldY - gp.tileSize < gp.playerGra.worldY + gp.playerGra.getScreenY()) {
        	switch(direction) {
            case "up":
            	if(spriteNum == 1) {image = up1;}
                if(spriteNum == 2) {image = up2;}
                break;
            case "down":
                if(spriteNum == 1) {image = down1;}
                if(spriteNum == 2) {image = down2;}
                break;
            case "left":
                if(spriteNum == 1) {image = left1;}
                if(spriteNum == 2) {image = left2;}
                break;
            case "right":
                if(spriteNum == 1) {image = right1;}
                if(spriteNum == 2) {image = right2;}
                break;
          }
        	
        	
          g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
          
          
        }
    }
//   	 public void damagePlayer(int attack) {
//			if(gp.playerGra.player.invincible == false) {
//				// we can give damage
//				gp.playSE(6);
//				int damage = attack - gp.playerGra.player.defense;
//				if(damage < 0){
//					damage = 0;
//				}
//
//				gp.playerGra.player.hp -= damage;  
//				gp.playerGra.player.hp -= 1;
//				gp.playerGra.player.invincible = true;
//			}
//		}
    
    public boolean isAlive() { return alive;}
	public String getDirection() { return direction;}
    }


