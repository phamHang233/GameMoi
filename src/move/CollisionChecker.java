package move;

import entity.Entity;
import entity.EntityGraphic;
import entity.MonsterGraphic;
import object.Projectile;
import object.ProjectileGraphic;
import screen.GamePanel;

public class CollisionChecker {

    GamePanel gp;
    
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

 public void checkTile(EntityGraphic entityGra, Entity entity) {
    	
        int entityLeftWorldX = entityGra.worldX + entityGra.solidArea.x;
        int entityRightWorldX = entityGra.worldX + entityGra.solidArea.x + entityGra.solidArea.width; 
        int entityTopWorldY = entityGra.worldY + entityGra.solidArea.y;
        int entityBottomWorldY = entityGra.worldY + entityGra.solidArea.y + entityGra.solidArea.height;
        
        // Based on these coordinates => need to find their col and row number
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entityGra.getDirection()) {
            case "up":
                
                entityTopRow = (entityTopWorldY - entity.getSpeed())/gp.tileSize;
                
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entityGra.setCollisionOn(true); 
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed())/gp.tileSize;
//                System.out.println(entityTopRow);
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                	 entityGra.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed())/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                	 entityGra.setCollisionOn(true);   
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed())/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                	 entityGra.setCollisionOn(true);   
                }
                break;
        }
    }
public int checkEntity(EntityGraphic entityGra, Entity entity, EntityGraphic[][] targetGra) {
 	   Entity target[][] = new Entity[gp.maxMap][10];
 	   if (targetGra == gp.monsterGra) {
 		   for (int j = 0; j < targetGra.length; j++) {
 			   if (gp.monsterGra[gp.currentMap][j]!=null) {
 		   		target[gp.currentMap][j] = gp.monsterGra[gp.currentMap][j].monGreen;}
 		   }
 	   }
 	  if (targetGra == gp.oldMan_Gra) {
		   for (int j = 0; j < targetGra.length; j++) {
			   if (gp.oldMan_Gra[gp.currentMap][j]!=null) {
		   		target[gp.currentMap][j] = gp.oldMan_Gra[gp.currentMap][j].npc;}
		   }
	   } 
// 	  if (targetGra == gp.princess_Gra) {
//		   for (int j = 0; j < targetGra.length; j++) {
//			   if (gp.princess_Gra[gp.currentMap][j]!=null) {
//		   		target[gp.currentMap][j] = gp.princess_Gra[gp.currentMap][j].npc;}
//		   }
//	   }  
 	  
 	  int index = 999;

        for(int i = 0; i < targetGra.length; i++){

            if(targetGra[gp.currentMap][i] != null) {
            	
                // Get entity's solid area position
                entityGra.solidArea.x = entityGra.worldX + entityGra.solidArea.x;
                entityGra.solidArea.y = entityGra.worldY + entityGra.solidArea.y;
                //Get the target's solid area position
                targetGra[gp.currentMap][i].solidArea.x = targetGra[gp.currentMap][i].worldX + targetGra[gp.currentMap][i].solidArea.x;
                targetGra[gp.currentMap][i].solidArea.y = targetGra[gp.currentMap][i].worldY + targetGra[gp.currentMap][i].solidArea.y;

                switch(entityGra.getDirection()) {
                    case "up": entityGra.solidArea.y -= entity.getSpeed(); break;
                    case "down": entityGra.solidArea.y += entity.getSpeed(); break;
                    case "left": entityGra.solidArea.x -= entity.getSpeed(); break;
                    case "right": entityGra.solidArea.x += entity.getSpeed(); break;
                }
                if(entityGra.solidArea.intersects(targetGra[gp.currentMap][i].solidArea)) {
                     if(targetGra[gp.currentMap][i] != entityGra) {
                         entityGra.setCollisionOn(true);
                         index = i;      
                     }
                 }
            //RESET SOLID AREA NUMBER
                entityGra.solidArea.x = entityGra.getSolidAreaDefaultX();
                entityGra.solidArea.y = entityGra.getSolidAreaDefaultY();
                targetGra[gp.currentMap][i].solidArea.x = targetGra[gp.currentMap][i].getSolidAreaDefaultX();
                targetGra[gp.currentMap][i].solidArea.y = targetGra[gp.currentMap][i].getSolidAreaDefaultY();
            }
        }
        return index;
      }
        
public int checkObject(EntityGraphic entityGra, Entity entity, boolean player){

        int index = 999;

        for(int i = 0; i < gp.objGra.length; i++){

            if(gp.objGra[gp.currentMap][i] != null) {
                // Get entity's solid area position
            	entityGra.solidArea.x = entityGra.worldX + entityGra.solidArea.x;
            	entityGra.solidArea.y = entityGra.worldY + entityGra.solidArea.y;
                //Get the object's solid area position
                gp.objGra[gp.currentMap][i].solidArea.x = gp.objGra[gp.currentMap][i].worldX + gp.objGra[gp.currentMap][i].solidArea.x;
                gp.objGra[gp.currentMap][i].solidArea.y = gp.objGra[gp.currentMap][i].worldY + gp.objGra[gp.currentMap][i].solidArea.y;

                switch(entityGra.getDirection()) {
                    case "up":
                    	entityGra.solidArea.y -= entity.getSpeed();break;
                    case "down":
                    	entityGra.solidArea.y += entity.getSpeed(); break;
                    case "left":
                    	entityGra.solidArea.x -= entity.getSpeed();break;
                    case "right":
                    	entityGra.solidArea.x += entity.getSpeed();break;
                }
                if(entityGra.solidArea.intersects(gp.objGra[gp.currentMap][i].solidArea)) {
                    if(gp.objGra[gp.currentMap][i].collision == true) {
                        entityGra.setCollisionOn(true);
                    }
                    if(player == true) {index = i; }
                }
            //RESET SOLID AREA NUMBER
                entityGra.solidArea.x = entityGra.getSolidAreaDefaultX();
                entityGra.solidArea.y = entityGra.getSolidAreaDefaultY();
                gp.objGra[gp.currentMap][i].solidArea.x = gp.objGra[gp.currentMap][i].getSolidAreaDefaultX();
                gp.objGra[gp.currentMap][i].solidArea.y = gp.objGra[gp.currentMap][i].getSolidAreaDefaultY();
            }

        }

        return index;
    }
    
    public int checkProjectile(ProjectileGraphic projectileGra ,EntityGraphic entityGra[][]) {
    	MonsterGraphic monGreen[][] = new MonsterGraphic[gp.maxMap][10];
  	  
	   for (int j = 0; j < entityGra.length; j++) {
		   if (gp.monsterGra[gp.currentMap][j]!=null) {
	   		monGreen[gp.currentMap][j] = gp.monsterGra[gp.currentMap][j];}
  		   
  	   }	
  	  
  	  int index = 999;

         for(int i = 0; i < entityGra.length; i++){

             if(entityGra[gp.currentMap][i] != null) {
             	
                 // Get entity's solid area position
            	 projectileGra.solidArea.x = projectileGra.worldX + projectileGra.solidArea.x;
            	 projectileGra.solidArea.y = projectileGra.worldY + projectileGra.solidArea.y;
                 //Get the target's solid area position
            	 entityGra[gp.currentMap][i].solidArea.x = monGreen[gp.currentMap][i].worldX + entityGra[gp.currentMap][i].solidArea.x;
            	 entityGra[gp.currentMap][i].solidArea.y = monGreen[gp.currentMap][i].worldY + entityGra[gp.currentMap][i].solidArea.y;

                 switch(projectileGra.getDirection()) {
                     case "up": projectileGra.solidArea.y -= projectileGra.projectile.getSpeed(); break;
                     case "down": projectileGra.solidArea.y += projectileGra.projectile.getSpeed(); break;
                     case "left": projectileGra.solidArea.x -= projectileGra.projectile.getSpeed(); break;
                     case "right": projectileGra.solidArea.x += projectileGra.projectile.getSpeed(); break;
                 }
                 if(projectileGra.solidArea.intersects(entityGra[gp.currentMap][i].solidArea)) {
                      
                	 projectileGra.setCollisionOn(true);
                      index = i;      
                      
                  }
             //RESET SOLID AREA NUMBER
                 projectileGra.solidArea.x = projectileGra.getSolidAreaDefaultX();
                 projectileGra.solidArea.y = projectileGra.getSolidAreaDefaultY();
                 entityGra[gp.currentMap][i].solidArea.x = entityGra[gp.currentMap][i].getSolidAreaDefaultX();
                 entityGra[gp.currentMap][i].solidArea.y = entityGra[gp.currentMap][i].getSolidAreaDefaultY();
             }
         }
         return index;
       }
//    //NPC OR MONSTER
   
    public boolean checkPlayer(EntityGraphic entityGra, Entity entity) {
    	boolean contactPlayer = false;
    	// Get entity's solid area position
        entityGra.solidArea.x = entityGra.worldX + entityGra.solidArea.x;
        entityGra.solidArea.y = entityGra.worldY + entityGra.solidArea.y;
        
        //Get the object's solid area position
        gp.playerGra.solidArea.x = gp.playerGra.worldX+ gp.playerGra.solidArea.x;
        gp.playerGra.solidArea.y = gp.playerGra.worldY + gp.playerGra.solidArea.y;

        switch(entityGra.getDirection()) {
        case "up": entityGra.solidArea.y -= entity.getSpeed(); break;
        case "down": entityGra.solidArea.y += entity.getSpeed(); break;
        case "left": entityGra.solidArea.x -= entity.getSpeed(); break;
        case "right": entityGra.solidArea.x += entity.getSpeed(); break;
    }
        
    if(entityGra.solidArea.intersects(gp.playerGra.solidArea)) {                            
    	entityGra.setCollisionOn(true);
        contactPlayer = true;
    }
    
    //RESET SOLID AREA NUMBER
        entityGra.solidArea.x = entityGra.getSolidAreaDefaultX();
        entityGra.solidArea.y = entityGra.getSolidAreaDefaultY();
        gp.playerGra.solidArea.x = gp.playerGra.getSolidAreaDefaultX();
        gp.playerGra.solidArea.y = gp.playerGra.getSolidAreaDefaultY();
        return contactPlayer;
    }
    public boolean checkPlayerProjectile(ProjectileGraphic projectileGra) {
    	boolean contactPlayer = false;
    	 projectileGra.solidArea.x = projectileGra.worldX + projectileGra.solidArea.x;
    	 projectileGra.solidArea.y = projectileGra.worldY + projectileGra.solidArea.y;
         //Get the target's solid area position
    	 gp.playerGra.solidArea.x = gp.playerGra.worldX + gp.playerGra.solidArea.x;
    	 gp.playerGra.solidArea.y = gp.playerGra.worldY + gp.playerGra.solidArea.y;

         switch(projectileGra.getDirection()) {
             case "up": projectileGra.solidArea.y -= projectileGra.projectile.getSpeed(); break;
             case "down": projectileGra.solidArea.y += projectileGra.projectile.getSpeed(); break;
             case "left": projectileGra.solidArea.x -= projectileGra.projectile.getSpeed(); break;
             case "right": projectileGra.solidArea.x += projectileGra.projectile.getSpeed(); break;
         }
    
    if(projectileGra.solidArea.intersects(gp.playerGra.solidArea)) {                            
    	projectileGra.setCollisionOn(true);
        contactPlayer = true;
    }
    //RESET SOLID AREA NUMBER
        projectileGra.solidArea.x = projectileGra.getSolidAreaDefaultX();
        projectileGra.solidArea.y = projectileGra.getSolidAreaDefaultY();
        gp.playerGra.solidArea.x = gp.playerGra.getSolidAreaDefaultX();
        gp.playerGra.solidArea.y = gp.playerGra.getSolidAreaDefaultY();
        return contactPlayer;
    }
//    public void checkNPC(PlayerGraphic playerGra, OldMan_Graphic npcGra) {
// 	   
// 	   
////      int index = 999;
//          // Get entity's solid area position
//          playerGra.solidArea.x = playerGra.player.worldX +  playerGra.solidArea.x;
//          playerGra.solidArea.y = playerGra.player.worldY +  playerGra.solidArea.y;
//          //Get the object's solid area position
//          npcGra.solidArea.x = npcGra.npc.worldX + npcGra.solidArea.x;
//          npcGra.solidArea.y = npcGra.npc.worldY + npcGra.solidArea.y;
//
//          switch(playerGra.getDirection()) {
//              case "up": playerGra.solidArea.y -= playerGra.player.getSpeed(); break;
//              case "down":  playerGra.solidArea.y += playerGra.player.getSpeed(); break;
//              case "left":  playerGra.solidArea.x -= playerGra.player.getSpeed(); break;
//              case "right":  playerGra.solidArea.x += playerGra.player.getSpeed(); break;
//          }
//          if( playerGra.solidArea.intersects(npcGra.solidArea)) {
//               
//            	   playerGra.setCollisionOn(true);
////                   index = i;      
//               
//          }
//    }
}
          
    


