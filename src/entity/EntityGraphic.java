package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.Projectile;
import screen.GamePanel;
import screen.UtilityTool;

public class EntityGraphic {

	GamePanel gp;
	//FOR PLAYER
	Entity entity;
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    protected BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, 
    attackLeft1, attackLeft2, attackRight1, attackRight2;
    public Projectile projectile;
    
    //public BufferedImage image, image2, image3;
    public Rectangle solidArea ;
    public Rectangle attackArea ;
    protected int solidAreaDefaultX, solidAreaDefaultY; // for object interacting
    //public boolean collision ;
    protected String dialogues[] = new String[20]; 
    //STATE
  	public int worldX, worldY;
  	protected boolean invincible ; //for monster collision
  	protected boolean attacking ;
  	protected boolean alive = true;
  	protected boolean dying ;
    
    //STATE
   
    protected String direction;
    protected  int spriteNum = 1;
    protected int dialogueIndex;
    protected boolean collisionOn ;
    protected boolean hpBarOn ;
    
    //COUNTER
    protected int spriteCounter;
    protected int actionLockCounter;
    protected int invincibleCounter; //for monster collision
    protected int shotAvailableCounte;
    protected int dyingCounter;
    protected int hpBarCounter;
    
    //TYPE 
    protected int type; 
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
	
    public EntityGraphic(GamePanel gp) {
   
    	this.gp = gp;
    	solidArea = new Rectangle(0,0,48,48);
    	attackArea = new Rectangle(0,0,0,0);
    	entity = new Entity();
    	direction = "down";
    }    
    
    public void setAction() {}
    
   
    public void dyingAnimation(Graphics2D g2, EntityGraphic entityGra) {
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i) changAlpha(g2, 0f);
		if(dyingCounter > i && dyingCounter <=i*2) changAlpha(g2, 1f);
		if(dyingCounter > i*2 && dyingCounter <=i*3) changAlpha(g2, 0f);
		if(dyingCounter > i*3 && dyingCounter <=i*4) changAlpha(g2, 1f);
		if(dyingCounter > i*4 && dyingCounter <=i*5) changAlpha(g2, 0f);
		if(dyingCounter > i*5 && dyingCounter <=i*6) changAlpha(g2, 1f);
		if(dyingCounter > i*6 && dyingCounter <=i*7) changAlpha(g2, 0f);
		if(dyingCounter > i*7 && dyingCounter <=i*8) changAlpha(g2, 1f);
		if(dyingCounter > i*8) {
			entityGra.alive = false;
		}
	}	
    public void update(EntityGraphic entityGra, Entity entity) {
    	
    	setAction();
    	collisionOn = false;
    	gp.cChecker.checkTile(this, entity);
    	//gp.cChecker.checkObject(this, entity, false);
        gp.cChecker.checkEntity(this, entity, gp.oldMan_Gra);
        //gp.cChecker.checkEntity(this, entity, gp.princess_Gra);
        gp.cChecker.checkEntity(this, entity, gp.monsterGra);

    	boolean contactPlayer = gp.cChecker.checkPlayer(this, entity);
//    	
        if(entityGra.type == type_monster && contactPlayer == true) {
			entity.damagePlayer(entity.attack, gp);
        }
    	if (collisionOn == false) {

			switch(entityGra.direction) {
            	case "up": worldY -= entity.getSpeed(); break;
            	case "down": worldY += entity.getSpeed(); break; 
            	case "left": worldX -= entity.getSpeed(); break;
            	case "right": worldX += entity.getSpeed(); break;
        	}
        }

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
    	if(invincible ==  true) {
            invincibleCounter ++;
            if(invincibleCounter > 40) {
            	invincible = false;
                invincibleCounter = 0;
            }
        }  
		if(shotAvailableCounte < 30) {
			shotAvailableCounte ++;
		}
    }
    
    public void draw(Graphics2D g2, EntityGraphic entityGra, Entity entity) {
    	BufferedImage image = null;
    	int screenX = entityGra.worldX - gp.playerGra.worldX + gp.playerGra.getScreenX( );
        int screenY = entityGra.worldY - gp.playerGra.worldY + gp.playerGra.getScreenY();

        if(entityGra.worldX + gp.tileSize > gp.playerGra.worldX - gp.playerGra.getScreenX() &&
        		entityGra.worldX - gp.tileSize < gp.playerGra.worldX + gp.playerGra.getScreenX() &&
        		entityGra.worldY + gp.tileSize > gp.playerGra.worldY - gp.playerGra.getScreenY() &&
        		entityGra.worldY - gp.tileSize < gp.playerGra.worldY + gp.playerGra.getScreenY()) {
        	switch(entityGra.direction) {
            case "up":
            	if(spriteNum == 1) {
            		image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
          }
        	if(entityGra.type == type_monster && hpBarOn == true) {
    			double oneScale = (double)gp.tileSize/entity.getMaxHp();
    			double hpBarValue = oneScale*entity.hp;
    			
    			g2.setColor(new Color(35,35,35));
    			g2.fillRect(screenX-1, screenY - 16, gp.tileSize+2, 12);
    			
    			g2.setColor(new Color(255,0,30));
    			g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
    			
    			hpBarCounter++;
    			
    			if(hpBarCounter > 600) {
    				hpBarCounter = 0;
    				hpBarOn = false;
    			}
    		}

        	
        	
        	if(invincible == true) {
        		hpBarOn = true;
        		hpBarCounter = 0;
                changAlpha(g2,0.4F);
            }
        	if(dying == true) {
        		dyingAnimation(g2, entityGra);
        	}
          g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
          changAlpha(g2,1F);
        }
        
}
    public void checkDrop() {}
    public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void changAlpha(Graphics2D g2, float alphaValue) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,  alphaValue));
	}
	public BufferedImage getDown1() { return down1;}
	public int getSolidAreaDefaultX() { return solidAreaDefaultX;}
	public int getSolidAreaDefaultY() { return solidAreaDefaultY;}
	public String getDirection() { return direction;}
	public void setCollisionOn(boolean collisionOn) {this.collisionOn= collisionOn;}
	public boolean getInvincible() { return invincible;}
	public boolean  isAlive() {return alive;}
	public boolean  isDying() {	return dying;}

}

	


