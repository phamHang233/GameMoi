package object;
import screen.GamePanel;

public class OBJ_Shield_Blue extends SuperObjectGraphic  {
	  public OBJ_Shield_Blue(GamePanel gp) {
	        super(gp);

	        obj.type = obj.type_shield;

	        obj.name = "Blue Shield";
	        image1 = setup("/res/objects/shield_blue", gp.tileSize, gp.tileSize);
	        obj.defenseValue = 2;
	        obj.description = "[" + obj.name + "]\nA shiny blue shield.";
	    }

}
