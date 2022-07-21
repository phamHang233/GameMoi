package event;

import screen.GamePanel;

public class EventHandler {
	GamePanel gp;
	EventRect eventRect[][][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		int map = 0;
		int col = 0;
		int row = 0;
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 20;
			eventRect[map][col][row].y = 20;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	}
	public void checkEvent() {
		
		//Check if the player character is more than 1 tile way from the last event
		int xDistance = Math.abs(gp.playerGra.worldX - previousEventX);
		int yDistance = Math.abs(gp.playerGra.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		if(canTouchEvent == true) {
			
//			if(hit(27, 16, "right") == true) {damagePit(27, 16, gp.dialogueState);}
//			if(hit(27, 16, "right") == true) {teleport(gp.dialogueState);}
//			if(hit(23, 12, "up") == true) {healingPool(23, 12, gp.dialogueState);}
	
		}
		
	}
	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		
		if(map == gp.currentMap) {
			gp.playerGra.solidArea.x = gp.playerGra.worldX + gp.playerGra.solidArea.x;
			gp.playerGra.solidArea.y = gp.playerGra.worldY + gp.playerGra.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.playerGra.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.playerGra.getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.playerGra.worldX;
					previousEventY = gp.playerGra.worldY;
				}
			}
			
			gp.playerGra.solidArea.x = gp.playerGra.getSolidAreaDefaultX();
			gp.playerGra.solidArea.y = gp.playerGra.getSolidAreaDefaultY();
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
			
		}
		
		return hit;
	}
	public void teleport(int map, int col, int row) {
		gp.currentMap = map;
		gp.playerGra.worldX = gp.tileSize*col;
		gp.playerGra.worldY = gp.tileSize*row;
		previousEventX = gp.playerGra.worldX;
		previousEventY = gp.playerGra.worldY;
		canTouchEvent = false;
		gp.playSE(13);
	}
	public void damagePit(int gameState) {
		gp.gameState = gameState;
		gp.ui.setCurrentDialogue( "You fall into a pit!");
		gp.playerGra.player.hp -= 1;
		gp.playSE(6);
		//eventRect[col][row].eventDone = true; 
		canTouchEvent = true;
	}
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.playerGra.setAttackCanceled(true);
			gp.playSE(2);
			gp.ui.setCurrentDialogue( "You drink the water.\n Your hp has been recovered.");
			gp.playerGra.player.hp = gp.playerGra.player. getMaxHp();
			gp.playerGra.player.mana = gp.playerGra.player.getMaxMana();	
			gp.aSetter.setMonster();
			//eventRect[col][row].eventDone = true;
		}

	}
}
