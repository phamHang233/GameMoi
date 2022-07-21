package object;

import screen.GamePanel;

public class OBJ_Coin_Bronze extends SuperObject {
	GamePanel gp;
	public OBJ_Coin_Bronze(GamePanel gp) {
		this.gp = gp;
		 
		 type = type_pickupOnly;
		 name = "Bronze Coin";
		 value = 1;
		 image1 = setup("/res/objects/Coin_Bronze", gp.tileSize, gp.tileSize);
	}
	
	
   public void use() {
		 
		 gp.playSE(value);
		 gp.ui.addMessage("Coin +" + value);
		 gp.playerGra.player.coin += value;
	 
   }

}
