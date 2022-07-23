package object;

import screen.GamePanel;

public class OBJ_Coin_Bronze extends SuperObjectGraphic {
	public OBJ_Coin_Bronze(GamePanel gp) {
		super(gp);
		 
		obj.type = obj.type_pickupOnly;
		obj.name = "Bronze Coin";
		obj.value = 1;
		 image1 = setup("/res/objects/coin_bronze", gp.tileSize, gp.tileSize);
	}
	
	
   public void use() {
		 
		 gp.playSE(obj.value);
		 gp.ui.addMessage("Coin +" + obj.value);
		 gp.playerGra.player.coin += obj.value;
	 
   }

}
