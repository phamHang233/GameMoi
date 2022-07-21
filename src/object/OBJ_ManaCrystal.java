package object;

import screen.GamePanel;

public class OBJ_ManaCrystal extends SuperObject {
	GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp) {
        this.gp = gp;
        
        type = type_pickupOnly;
        value = 1;
        name = "Mana Crystal";
        image1 = setup("/res/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
    }
    public void use() {
    	
    	gp.playSE(2);
    	gp.ui.addMessage("Mana +" + value);
    	gp.playerGra.player.mana += value;
    }
}
