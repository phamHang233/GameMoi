package object;
import screen.GamePanel;

public class OBJ_Door extends SuperObject { 
	 GamePanel gp;
    public OBJ_Door(GamePanel gp) {
    	 this.gp = gp;
    	 
        name = "Door";
        image1 = setup("/res/objects/door",gp.tileSize, gp.tileSize);
     
        collision = true;

        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = 48;
        solidArea.width = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    
}
