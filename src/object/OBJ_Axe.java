package object;

import screen.GamePanel;

public class OBJ_Axe extends SuperObjectGraphic {
	public OBJ_Axe(GamePanel gp) {
		super(gp);
         
		obj.type = obj.type_axe;

		obj.name = "Woodcutter's Axe";
		 image1 = setup("/res/objects/axe",gp.tileSize, gp.tileSize);
		 obj.attackValue = 1;
		 attackArea.width = 30;
		 attackArea.height = 30;
		 obj.description = "[Woodcutter's Axe]\nA bit rusty but still \n can cut some trees.";
	}

}
