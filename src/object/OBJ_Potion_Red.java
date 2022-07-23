package object;

import screen.GamePanel;

public class OBJ_Potion_Red extends SuperObjectGraphic {
	 
	 public OBJ_Potion_Red(GamePanel gp) {
		 super(gp);
		 obj.type = obj.type_consumable;
		 obj.name = "Red Potion";
		 obj. value = 4;
		 image1 = setup("/res/objects/potion_red",gp.tileSize,gp.tileSize);
		 obj.description = "[Red Potion]\nHeals your life by " + obj.value + ".";
				 
	 }
	 public void use() {
		 
		 gp.gameState = gp.dialogueState;
		 gp.ui. setCurrentDialogue(" You drink the " + obj.name + "!\n"
				 + "Your life has been recovered by " + obj.value + ".");
		 gp.playerGra.player.hp += obj.value;
		 gp.playSE(2);
	 }
}


