package screen;

import entity.OldMan_Graphic;
import entity.Princess_Graphic;
import entity.MonsterGraphic;
import object.OBJ_Axe;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
// import object.OBJ_Door;
//import monster.MON_GreenSlime;
//import object.OBJ_Axe;
//import object.OBJ_Key;
//import object.OBJ_Potion_Red;
//import object.OBJ_Shield_Blue;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Teleport;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
    	
    	int i = 0;
    	gp.objGra[0][i] = new OBJ_Coin_Bronze(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*25;
    	gp.objGra[0][i].worldY = gp.tileSize*23;
    	i++;
    	gp.objGra[0][i] = new OBJ_Coin_Bronze(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*21;
    	gp.objGra[0][i].worldY = gp.tileSize*19;
    	i++;
    	gp.objGra[0][i] = new OBJ_Key(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*26;
    	gp.objGra[0][i].worldY = gp.tileSize*21;
    	i++;
    	gp.objGra[0][i] = new OBJ_Axe(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*30;
    	gp.objGra[0][i].worldY = gp.tileSize*21;
    	i++;
    	gp.objGra[0][i] = new OBJ_Shield_Blue(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*35;
    	gp.objGra[0][i].worldY = gp.tileSize*21;
    	i++;
    	gp.objGra[0][i] = new OBJ_Potion_Red(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*22;
    	gp.objGra[0][i].worldY = gp.tileSize*18;
    	i++;
    	gp.objGra[0][i] = new OBJ_Heart(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*16;
    	gp.objGra[0][i].worldY = gp.tileSize*29;
    	i++;
    	gp.objGra[0][i] = new OBJ_ManaCrystal(gp);
    	gp.objGra[0][i].worldX = gp.tileSize*27;
    	gp.objGra[0][i].worldY = gp.tileSize*31;
    	i++;
    	
//    	// Đây là vị trí cổng tele. 
//    	gp.objGra[0][i] = new OBJ_Teleport(gp);
//    	gp.objGra[0][i].worldX = gp.tileSize*13;
//    	gp.objGra[0][i].worldY = gp.tileSize*25;
//    	i++;
    }
    
    public void setNPC() {
    
    	gp.oldMan_Gra[0][0] = new OldMan_Graphic(gp);
    	gp.oldMan_Gra[0][0].worldX= (gp.tileSize*27);
    	gp.oldMan_Gra[0][0].worldY= (gp.tileSize*30);

		gp.princess_Gra[0][1] = new Princess_Graphic(gp);
		gp.princess_Gra[0][1].worldX= (gp.tileSize*30);
    	gp.princess_Gra[0][1].worldY= (gp.tileSize*30);
        
    }

    public void setMonster() {
    	int i=0;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX =(gp.tileSize*16);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*24);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX= (gp.tileSize*21);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*16);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX= (gp.tileSize*24);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*37);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX= (gp.tileSize*36);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*13);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX=(gp.tileSize*12);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*12);
    	i++;
    }
}

