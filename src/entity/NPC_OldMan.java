package entity;

import screen.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan() {

		speed = 1;
	}
	public void speak(GamePanel gp, OldMan_Graphic npcGra) {

	    	if(npcGra.dialogues[npcGra.dialogueIndex] == null) {
	    		npcGra.dialogueIndex = 0;
			}
	    	
			gp.ui.setCurrentDialogue(npcGra.dialogues[npcGra.dialogueIndex]);
			npcGra.dialogueIndex++;
			if(npcGra.dialogueIndex > 4) npcGra.dialogueIndex = 4;
			
			switch(gp.playerGra.direction) {
			case "up":
				npcGra.direction = "down";
				break;
			case "down":
				npcGra.direction = "up";
				break;
			case "left":
				npcGra.direction = "right";
				break;
			case "right":
				npcGra.direction = "left";
				break;
			}
	    }
	
}
