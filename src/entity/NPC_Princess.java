package entity;

import screen.GamePanel;

public class NPC_Princess extends Entity {

	
	public NPC_Princess() {
		
	}
	public void speak(GamePanel gp, Princess_Graphic prinGra) {

    	if(prinGra.dialogues[prinGra.dialogueIndex] == null) {
    		prinGra.dialogueIndex = 0;
		}
    	
		gp.ui.setCurrentDialogue(prinGra.dialogues[prinGra.dialogueIndex]);
		
		switch(gp.playerGra.direction) {
		case "up":
			prinGra.direction = "down";
			break;
		case "down":
			prinGra.direction = "up";
			break;
		case "left":
			prinGra.direction = "right";
			break;
		case "right":
			prinGra.direction = "left";
			break;
		}
    }
}
