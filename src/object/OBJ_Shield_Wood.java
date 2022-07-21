package object;
import screen.GamePanel;

public class OBJ_Shield_Wood extends SuperObject {
	GamePanel gp;
    public OBJ_Shield_Wood(GamePanel gp) {
        this.gp =gp;
        type = type_shield;

        name = "Wood Shield";
        image1 = setup("/res/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade by wood.";
    }
    
}
