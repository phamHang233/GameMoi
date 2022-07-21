package entity;


import screen.GamePanel;

import object.SuperObject;

public class Monster_GreenSlime extends Entity{
	


    public Monster_GreenSlime() {
        name =  "Green Slime";
        speed = 1;
        maxHp = 5;
        hp = maxHp;
        attack = 4;
        defense = 0;
        exp = 2;
    }
    public void dropItem(SuperObject droppedItem, MonsterGraphic monsterGra, GamePanel gp) {
        for(int i = 0; i < gp.obj.length; i++) {
        	if(gp.obj[gp.currentMap][i] == null) {
        		gp.obj[gp.currentMap][i] = droppedItem;
    			gp.obj[gp.currentMap][i].worldX = monsterGra.worldX;  //the dead monster's worldX
        		gp.obj[gp.currentMap][i].worldY = monsterGra.worldY;
        		break;
        	}
        }
    }
    public void damagePlayer(int attack, GamePanel gp) {
		if(gp.playerGra.invincible == false) {
		// we can give damage
		gp.playSE(5);
		int damage = attack - gp.playerGra.player.defense;
		if(damage < 0){
			damage =0;
		}
		gp.playerGra.player.hp -= damage;  
		gp.playerGra.invincible = true;
		}
    }
}
