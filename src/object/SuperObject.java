package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import screen.UtilityTool;
import screen.GamePanel;
public class SuperObject {
    protected BufferedImage image1, image2, image3;
    protected String name;
    public boolean collision;
    public int worldX;
    public int worldY;
    public Rectangle solidArea;
    protected int solidAreaDefaultX ;
    protected int solidAreaDefaultY;
    public Rectangle attackArea;
    protected int value;
    protected int defenseValue;
    protected boolean collisionOn;
    protected int speed;

    
    
    protected int attackValue;
    protected String description;
    
    //TYPE
    protected int type; // 0 = player, 1 = npc, 2 = monster, for collision between player and monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
	
    
    public SuperObject() {
    	collision=  false;
    	solidArea  = new Rectangle(0,0,48,48);
    	attackArea = new Rectangle(0,0,0,0);
    }
    
    public void use() {}
    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.playerGra.worldX + gp.playerGra.getScreenX( );
        int screenY = worldY - gp.playerGra.worldY + gp.playerGra.getScreenY();

        if(	worldX + gp.tileSize > gp.playerGra.worldX - gp.playerGra. getScreenX( ) &&
            worldX - gp.tileSize < gp.playerGra.worldX + gp.playerGra. getScreenX( ) &&
            worldY + gp.tileSize > gp.playerGra.worldY - gp.playerGra.getScreenY() &&
            worldY - gp.tileSize < gp.playerGra.worldY + gp.playerGra.getScreenY()) {

                g2.drawImage(image1, screenX, screenY, gp.tileSize, gp.tileSize, null);
                    
        }
        
    }
    
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
    public BufferedImage getImage1(){ return image1;}
    public BufferedImage getImage2(){ return image2;}
    public BufferedImage getImage3(){ return image3;}
    public String getName() { return name;}
    public int getSolidAreaDefaultX() { return solidAreaDefaultX;}
	public int getSolidAreaDefaultY() { return solidAreaDefaultY;}
	public int getDefenseValue() { return defenseValue;}
	public void setCollisionOn(boolean collisionOn) {this.collisionOn= collisionOn;}
	public int getSpeed() {return speed;}
	public int getAttackValue() {return attackValue;}
	public String getDescription() { return description;}
	public int getType() {return type;}
}
	    
