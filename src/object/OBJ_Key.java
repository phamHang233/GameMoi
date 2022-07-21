package object;
import screen.GamePanel;


public class OBJ_Key extends SuperObject{
	 GamePanel gp;

    public OBJ_Key(GamePanel gp) {
    	 this.gp = gp;
    	 type = type_pickupOnly;
    	 collision = true;
        name = "Key";
        image1 = setup("/res/objects/key",gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nIt opens a door.";
      
    }
    
}

