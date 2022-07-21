package object;

import screen.GamePanel;

public class OBJ_Axe extends SuperObject {
	GamePanel gp;
	public OBJ_Axe(GamePanel gp) {
		this.gp =gp;
         
        type = type_axe;

		 name = "Woodcutter's Axe";
		 image1 = setup("/res/objects/axe",gp.tileSize, gp.tileSize);
		 attackValue = 1;
		 attackArea.width = 30;
		 attackArea.height = 30;
		 description = "[Woodcutter's Axe]\nA bit rusty but still \n can cut some trees.";
	}

}
