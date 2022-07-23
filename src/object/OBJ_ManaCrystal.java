package object;

import screen.GamePanel;

public class OBJ_ManaCrystal extends SuperObjectGraphic {
    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        
        obj.type = obj.type_pickupOnly;
        obj.value = 1;
        obj.name = "Mana Crystal";
        image1 = setup("/res/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
    }
    public void use() {
    	
    	gp.playSE(2);
    	gp.ui.addMessage("Mana +" + obj.value);
    	gp.playerGra.player.mana += obj.value;
    }
}
