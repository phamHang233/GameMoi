package entity;
import screen.GamePanel;


public class Princess_Graphic extends EntityGraphic {
    public NPC_Princess npcPrin;
    
    public Princess_Graphic(GamePanel gp) {
        super(gp);
		npcPrin = new NPC_Princess();
		direction = "down";
		
		getImage();

		solidArea.x = 8;
        solidArea.y = 16;
        solidArea.height = 48;
        solidArea.width = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
	}
	public void getImage() {
        up1 = setup("/res/npc/princess_up_2",gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/princess_up_4",gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/princess_down_2",gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/princess_down_4",gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/princess_left_2",gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/princess_left_4",gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/princess_right_2",gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/princess_right_4",gp.tileSize, gp.tileSize);
    }
	public void setDialogue() {
		dialogues[0] = "Hello, lad.";
		dialogues[1] = "So you've come to this island to find the treasure?";
		dialogues[2] = "I used to be a great wizard but now...I'm a bit too old for \ntaking an adventure.";
		dialogues[3] = "Well, good luck on you.";
		dialogues[4] = "Hello!";
	}
	public void update() {
		switch(gp.playerGra.direction) {
		case "up":
			this.direction = "down";
			break;
		case "down":
			this.direction = "up";
			break;
		case "left":
			this.direction = "right";
			break;
		case "right":
			this.direction = "left";
			break;
		}
	}
    
}
