package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import screen.GamePanel;
import screen.UtilityTool;

public class SuperObjectGraphic {
	GamePanel gp;
	public SuperObject obj;
	protected BufferedImage image1, image2, image3;
    public boolean collision;
    public int worldX;
    public int worldY;
    public Rectangle solidArea;
    protected int solidAreaDefaultX ;
    protected int solidAreaDefaultY;
    public Rectangle attackArea;
    protected boolean collisionOn;
	public SuperObjectGraphic(GamePanel gp) {
		
		this.gp = gp;
		obj = new SuperObject();
    	collision = false;
    	solidArea = new Rectangle(0,0,48,48);
    	attackArea = new Rectangle(0,0,0,0);
	}
    public void draw(Graphics2D g2) {

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
    public int getSolidAreaDefaultX() { return solidAreaDefaultX;}
	public int getSolidAreaDefaultY() { return solidAreaDefaultY;}
	public void setCollisionOn(boolean collisionOn) {this.collisionOn= collisionOn;}
    public void use() {}

}
