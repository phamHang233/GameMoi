package object;

import screen.GamePanel;

public class OBJ_Sword_Normal extends SuperObject {
    GamePanel gp;
    public OBJ_Sword_Normal(GamePanel gp) {
        this.gp =gp;
        
        type = type_sword;
        name = "Normal Sword";
        image1 = setup("/res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        
    }
    
}
