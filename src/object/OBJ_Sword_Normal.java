package object;

import screen.GamePanel;

public class OBJ_Sword_Normal extends SuperObjectGraphic {
    public OBJ_Sword_Normal(GamePanel gp) {
    	super(gp);
        obj.type =  obj.type_sword;
        obj.name = "Normal Sword";
        image1 = setup("/res/objects/sword_normal", gp.tileSize, gp.tileSize);
        obj.attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        obj.description = "[" +  obj.name + "]\nAn old sword.";
        
    }
    
}
