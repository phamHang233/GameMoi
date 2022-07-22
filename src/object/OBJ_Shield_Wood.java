package object;
import screen.GamePanel;

public class OBJ_Shield_Wood extends SuperObjectGraphic {
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        obj.type = obj.type_shield;

        obj.name = "Wood Shield";
        image1 = setup("/res/objects/shield_wood", gp.tileSize, gp.tileSize);
        obj.defenseValue = 1;
        obj.description = "[" + obj.name + "]\nMade by wood.";
    }
    
}
