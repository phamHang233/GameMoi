package event;

import java.awt.Rectangle;

import screen.GamePanel;

public class EventHandler {
	GamePanel gp;
	Event event[];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		event = new Event[20]; //has max 20 event
		
		//Set Event
		event[0] = new Event("Pit",10,32);
		event[1] = new Event("Pit",14,25);
		event[2] = new Event("Pit",15,27);
		event[3] = new Event("Magic Pool",27,18);
		event[4] = new Event("Magic Pool",26,18);
		event[5] = new Event("Magic Pool",25,18);
		event[6] = new Event("Magic Pool",24,18);
		event[7] = new Event("Magic Pool",23,18);
		event[8] = new EventGate(0, 13, 24, 1, 12, 13);
		event[9] = new EventGate(1, 10, 25, 0, 31, 39);
		event[10] = new EventGate(0, 32, 39, 1, 12, 23);
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
			for(int i = 0; i < event.length; i++) {
				if(event[i] != null) {
					if(hit(event[i], "any")){
					if(event[i].getName() == "Pit") damagePit(gp.dialogueState); 
					else if(event[i].getName() == "Gate") teleport((EventGate)event[i], gp.dialogueState);
					}
					if(hit(event[i], "up")) {
						if(event[i].getName() == "Magic Pool") healingPool(gp.dialogueState);
					}
				}
			}
		}

	}
	public boolean hit(Event event, String reqDirection) {
		boolean hit = false;
		Rectangle solidArea = new Rectangle(event.getSolidArea());
		if( event.getLocationMap() == gp.currentMap) {
			gp.playerGra.solidArea.x = gp.playerGra.worldX + gp.playerGra.solidArea.x;
			gp.playerGra.solidArea.y = gp.playerGra.worldY + gp.playerGra.solidArea.y;
			solidArea.x = event.getEventCol()*gp.tileSize + event.getSolidArea().x;
			solidArea.y = event.getEventRow()*gp.tileSize + event.getSolidArea().y;
			
			if(gp.playerGra.solidArea.intersects(solidArea)) {
				if(gp.playerGra.getDirection().contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					previousEventX = gp.playerGra.worldX;
					previousEventY = gp.playerGra.worldY;
				}
			}

			gp.playerGra.solidArea.x = gp.playerGra.getSolidAreaDefaultX();
			gp.playerGra.solidArea.y = gp.playerGra.getSolidAreaDefaultY();

		}
		
		return hit;
	}
	public void teleport(EventGate e, int gameState) {
		gp.gameState = gameState;
		gp.ui.setCurrentDialogue("Teleport!");
		gp.currentMap = e.getToMap();
		gp.playerGra.worldX = e.getToWorldCol()*gp.tileSize;
		gp.playerGra.worldY = e.getToWorldRow()*gp.tileSize;
		canTouchEvent = false;
	}
	public void damagePit (int gameState) {
		gp.gameState = gameState;
		gp.ui.setCurrentDialogue("You fall into a pit!");
		gp.playerGra.player.hp -= 1;
		gp.playSE(6);
		canTouchEvent = false;
	}
	public void healingPool(int gameState) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.playerGra.setAttackCanceled(true);
			gp.playSE(2);
			gp.ui.setCurrentDialogue("You drink the water.\n Your hp has been recovered.");
			gp.playerGra.player.hp = gp.playerGra.player.getMaxHp();
			gp.playerGra.player.mana = gp.playerGra.player.getMaxMana();	
			gp.aSetter.setMonster();
		} 	

	}
//	public void teleport( int map, int col, int row) {
//		gp.currentMap = map;
//		gp.playerGra.player.worldX = gp.tileSize*col;
//		gp.playerGra.player.worldY = gp.tileSize*row;
//		previousEventX = gp.playerGra.player.worldX;
//		previousEventY = gp.playerGra.player.worldY;
//		canTouchEvent = false;
//	}
}
