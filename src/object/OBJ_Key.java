package object;
import screen.GamePanel;


public class OBJ_Key extends SuperObjectGraphic{
    public OBJ_Key(GamePanel gp) {
    	 super(gp);
    	 obj.type = obj.type_pickupOnly;
    	 collision = true;
    	 obj.name = "Key";
    	 image1 = setup("/res/objects/key",gp.tileSize, gp.tileSize);
    	 obj.description = "[" + obj.name + "]\nIt opens a door.";
      
    }
    
}

