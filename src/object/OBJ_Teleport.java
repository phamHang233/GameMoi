package object;
import screen.GamePanel;


public class OBJ_Teleport extends SuperObjectGraphic{
    public OBJ_Teleport(GamePanel gp) {
    	 super(gp);
    	 obj.type = obj.type_consumable;
    	 collision = false;
    	 obj.name = "Tele";
    	 image1 = setup("/res/objects/Tele",gp.tileSize, gp.tileSize);

      
    }
    
}

