package object;

import screen.GamePanel;

public class OBJ_Heart extends SuperObjectGraphic {
    public OBJ_Heart(GamePanel gp) {
    	super(gp);
    	
    	obj.name = "heart";
    	obj.type = obj.type_pickupOnly;
    	obj.value = 2;
    	image1 = setup("/res/objects/heart_full", gp.tileSize, gp.tileSize);
    	image2 = setup("/res/objects/heart_half", gp.tileSize, gp.tileSize);
    	image3 = setup("/res/objects/heart_blank", gp.tileSize, gp.tileSize);
    }
    public void use() {
    	
    	gp.playSE(2);
    	gp.ui.addMessage("HP +" + obj.value);
    	gp.playerGra.player.hp += obj.value;
    	
    }
}

