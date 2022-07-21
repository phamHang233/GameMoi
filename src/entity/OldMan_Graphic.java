package entity;


import java.util.Random;

import screen.GamePanel;


public class OldMan_Graphic extends EntityGraphic{
	
	public NPC_OldMan npc;

	public OldMan_Graphic(GamePanel gp) {
		super(gp);
		direction = "down";
	
		
		npc = new NPC_OldMan();
		solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = 48;
        solidArea.width = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
		setDialogue();
	}
	public void getImage() {
        up1 = setup("/res/npc/oldman_up_1",gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/oldman_up_2",gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/oldman_down_1",gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/oldman_down_2",gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/oldman_left_1",gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/oldman_left_2",gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/oldman_right_1",gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/oldman_right_2",gp.tileSize, gp.tileSize);
    }
	public void setDialogue() {
		dialogues[0] = "Hello, lad.";
		dialogues[1] = "So you've come to this island to find the treasure?";
		dialogues[2] = "I used to be a great wizard but now...I'm a bit too old for \ntaking an adventure.";
		dialogues[3] = "Well, good luck on you.";
		dialogues[4] = "Hello!";
	}
	public void setAction() {
		 actionLockCounter++;
			
			if(actionLockCounter == 120) {
				Random random = new Random();
				int i = random.nextInt(100)+1; //pick up a number from 1 to 100
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				
				actionLockCounter = 0;
			}
		}
	
}
	
