package entity;

import screen.GamePanel;
import object.SuperObjectGraphic;

import java.util.ArrayList;

public class Player extends Entity {

  
    //PlayerGraphic playerGra;
	public ArrayList<SuperObjectGraphic> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;

    public Player() {
    	//this.playerGra = playerGra;
        setDefaultValues();
    }
    public void setDefaultValues() { // Player's position on world map
       
        speed = 4; //moving 4 pixels for each press

        name = "";
        //PLAYER STATUS
		level = 1;
        maxHp = 6;
        hp = maxHp;
		maxMana = 4;
		mana = maxMana;
		ammo = 10;	
		strength = 1; // the more strength he has, the more damage he gives
		dexteriry = 1; // the more dexterity he has, the less damage he receives
		exp = 0;
		nextLevelExp = 5; // the amount of exp needed to be level up
		coin = 0;
		 // the total attack value based on strength and weapon
		// currentWeapon = new OBJ_Axe(gp);
    }

   public void restoreLifeAndMana(PlayerGraphic playerGra) {
   		hp = maxHp;
   		mana = maxMana;
   		playerGra.invincible = false;
   }
  
    public void attacking(GamePanel gp, PlayerGraphic playerGra) {
		playerGra.spriteCounter++;
		if(playerGra.spriteCounter <= 5) {
			playerGra.spriteNum = 1;
		}
		if(playerGra.spriteCounter > 5 && playerGra.spriteCounter <= 25) {
			playerGra.spriteNum = 2;
			// save the current worldX, worldY, solidArea
			int currentWorldX = playerGra.worldX;
			int currentWorldY = playerGra.worldY;
			int solidAreaWidth = playerGra.solidArea.width;
			int solidAreaHeight = playerGra.solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(playerGra.direction) {
			case "up": playerGra.worldY -= playerGra.attackArea.height; break;
			case "down": playerGra.worldY += playerGra.attackArea.height; break;
			case "left": playerGra.worldX -=playerGra. attackArea.width; break;
			case "right": playerGra.worldX += playerGra.attackArea.width; break;
			}
			
			// attackArea becomes solidArea
			playerGra.solidArea.width = playerGra.attackArea.width;
			playerGra.solidArea.height = playerGra.attackArea.height;
			
			// Check monster collision with the updated worldX, worldY and //solidArea
			int monsterIndex = gp.cChecker.checkEntity(playerGra, this, gp.monsterGra);
			damageMonster(monsterIndex, attack, gp, playerGra);			
			
			//After checking collision, restore the original data
			playerGra.worldX = currentWorldX;
			playerGra.worldY = currentWorldY;
			playerGra.solidArea.width = solidAreaWidth;
			playerGra.solidArea.height = solidAreaHeight;
		}
		if(playerGra.spriteCounter > 25) {
			playerGra.spriteNum = 1;
			playerGra.spriteCounter = 0;
			playerGra.attacking = false;
		}
  }   
    public void pickUpObject(int i, GamePanel gp) {
        if(i != 999) {
        	
        	//PICKUP ONLY ITEMS
        	if(gp.objGra[gp.currentMap][i].obj.getType() == gp.objGra[gp.currentMap][i].obj.type_pickupOnly  ) {
    			
    			gp.objGra[gp.currentMap][i].use();
    			gp.objGra[gp.currentMap][i] = null;
    		}
        		//INVENTORY ITEM
        	else {
        		String text;
			 
        		if( inventory.size() != maxInventorySize) {
				 
        			inventory.add(gp.objGra[gp.currentMap][i]);
        			gp.playSE(1);
        			text = "Got a " + gp.objGra[gp.currentMap][i].obj.getName() + "!";
        		}
        		else {
				  text = " You cannot carry anymore!";
        		}
			gp.ui.addMessage(text);
			gp.objGra[gp.currentMap][i] = null;
        	}	
        }

    }
    public void interactNPC(int i, GamePanel gp, PlayerGraphic playerGra) {
    	
    	if(gp.keyH.enterPressed == true){
        	if(i!=999){
        		playerGra.setAttackCanceled(true);
        		gp.gameState=gp.dialogueState;
        		gp.oldMan_Gra[gp.currentMap][i].npc.speak(gp, gp.oldMan_Gra[gp.currentMap][i]);
        	}
   
    	}
    }
    public void interactPrin(int i, GamePanel gp, PlayerGraphic playerGra) {
    	if(i != 999) {
    		gp.princess_Gra[gp.currentMap][i].npcPrin.speak(gp, gp.princess_Gra[gp.currentMap][i]);
    		gp.gameState = gp.dialogueState;
    	}
    	if(gp.keyH.enterPressed == true) {
    		if(i!=999) {
    			
    			playerGra.setAttackCanceled(true);    			
    			gp.gameState = gp.winGameState;
    			gp.stopMusic();
    			gp.playSE(14);
    		}
    	}
    }

    public void contactMonster(int i, GamePanel gp, PlayerGraphic playerGra) {

        if(i != 999) {
            if(playerGra.invincible == false && gp.monsterGra[gp.currentMap][i].dying == false) {
            	gp.playSE(5);
            	int damage = attack - gp.monsterGra[gp.currentMap][i].monGreen.defense;
				if(damage<0) {
					damage = 0;
				}
                hp -= 1;
                playerGra.invincible = true;
            }
        }
    }
    public void damageMonster(int i, int attack, GamePanel gp, PlayerGraphic playerGra) {
		if(i !=  999) {
			if(gp.monsterGra[gp.currentMap][i].invincible == false) {
				
				gp.playSE(4);
				
				int damage = attack - gp.monsterGra[gp.currentMap][i].monGreen.defense;
				if(damage<0) {
					damage =0;
				}
				gp.monsterGra[gp.currentMap][i].monGreen.hp -= damage;
				gp.ui.addMessage(damage + " damage!");
				gp.monsterGra[gp.currentMap][i].invincible = true;
				gp.monsterGra[gp.currentMap][i].damageReaction();

				if(gp.monsterGra[gp.currentMap][i].monGreen.hp <= 0) {
					gp.monsterGra[gp.currentMap][i].dying = true;
					gp.ui.addMessage("Killed the " + gp.monsterGra[gp.currentMap][i].monGreen.name +"!");
					gp.ui.addMessage("Exp + " + gp.monsterGra[gp.currentMap][i].monGreen.exp);
					exp += gp.monsterGra[gp.currentMap][i].monGreen.exp;
					checkLevelUp(gp, playerGra);
				}
			}
		}
	}

	public void checkLevelUp(GamePanel gp, PlayerGraphic playerGra) {//video26
		if(exp >=nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp*2;
			maxHp +=2;
			strength++;
			dexteriry++;
			attack = playerGra.getAttack();
			defense = playerGra.getDefense();
	
			gp.playSE(7);
			gp.gameState =gp.dialogueState;
			gp.ui. setCurrentDialogue( "You are level "+level+" now!\n"
				+"You fell stronger");
		}
	}

	public void selectItem(GamePanel gp, PlayerGraphic playerGra) {
		
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
		if(itemIndex < inventory.size()) {
			
			SuperObjectGraphic selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.obj.getType() == selectedItem.obj.type_sword || selectedItem.obj.getType() == selectedItem.obj.type_axe) {
				
				playerGra.currentWeapon = selectedItem;
				attack = getAttack();
				playerGra.getPlayerAttackImage();
				
				
			}
			if(selectedItem.obj.getType() == selectedItem.obj.type_shield) {
				
				playerGra.currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.obj.getType() == selectedItem.obj.type_consumable) {
				
				selectedItem.obj.use();
				inventory.remove(itemIndex);
			}
		}
	}
   
}


