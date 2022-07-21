package object;

import screen.GamePanel;

public class OBJ_Potion_Red extends SuperObject {
	 
	 GamePanel gp;
	 
	 public OBJ_Potion_Red(GamePanel gp) {
		 this.gp = gp;
		 
		
		 type = type_consumable;
		 name = "Red Potion";
		 value = 4;
		 image1 = setup("/res/objects/potion_red",gp.tileSize,gp.tileSize);
		 description = "[Red Potion]\nHeals your life by " + value + ".";
				 
	 }
	 public void use() {
		 
		 gp.gameState = gp.dialogueState;
		 gp.ui. setCurrentDialogue(" You drink the " + name + "!\n"
				 + "Your life has been recovered by " + value + ".");
		 gp.playerGra.player.hp += value;
		 gp.playSE(2);
	 }
}


