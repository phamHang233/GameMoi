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

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
    	
//    	int i = 0;
//    	gp.obj[0][i] = new OBJ_Coin_Bronze(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*25;
//    	gp.obj[0][i].worldY = gp.tileSize*23;
//    	i++;
//    	gp.obj[0][i] = new OBJ_Coin_Bronze(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*21;
//    	gp.obj[0][i].worldY = gp.tileSize*19;
//    	i++;
//    	gp.obj[0][i] = new OBJ_Key(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*26;
//    	gp.obj[0][i].worldY = gp.tileSize*21;
//    	i++;
//    	gp.obj[0][i] = new OBJ_Axe(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*30;
//    	gp.obj[0][i].worldY = gp.tileSize*21;
//    	i++;
//    	gp.obj[0][i] = new OBJ_Shield_Blue(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*35;
//    	gp.obj[0][i].worldY = gp.tileSize*21;
//    	i++;
//    	gp.obj[0][i] = new OBJ_Potion_Red(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*22;
//    	gp.obj[0][i].worldY = gp.tileSize*27;
//    	i++;
//    	gp.obj[0][i] = new OBJ_Heart(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*22;
//    	gp.obj[0][i].worldY = gp.tileSize*29;
//    	i++;
//    	gp.obj[0][i] = new OBJ_ManaCrystal(gp);
//    	gp.obj[0][i].worldX = gp.tileSize*27;
//    	gp.obj[0][i].worldY = gp.tileSize*31;
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
    	gp.monsterGra[0][i].worldX =(gp.tileSize*21);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*38);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX= (gp.tileSize*23);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*42);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX= (gp.tileSize*24);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*37);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX= (gp.tileSize*34);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*42);
    	i++;
    	gp.monsterGra[0][i]=new MonsterGraphic(gp);
    	gp.monsterGra[0][i].worldX=(gp.tileSize*38);
    	gp.monsterGra[0][i].worldY= (gp.tileSize*42);
    	i++;
    }
}

