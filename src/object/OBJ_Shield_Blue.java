package object;
import screen.GamePanel;

public class OBJ_Shield_Blue extends SuperObject  {
	GamePanel gp;
	  public OBJ_Shield_Blue(GamePanel gp) {
	        this.gp = gp;

	        type = type_shield;

	        name = "Blue Shield";
	        image1 = setup("/res/objects/shield_blue", gp.tileSize, gp.tileSize);
	        defenseValue = 2;
	        description = "[" + name + "]\nA shiny blue shield.";
	    }

}
