package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader; // read the content of the text file
import java.io.IOException;
import java.io.InputStream; // import text file
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import screen.GamePanel;
import screen.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile,tile1;
	public int mapTileNum[][][];

	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[250]; // number of kinds of Tiles
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/res/maps/worldV4.txt",0);
		loadMap("/res/maps/Insidehouse.txt",1);		
	}
	public void getTileImage() {
	
		//GRASS
		setup(00,"grass1",false);
		setup(01,"grass2",false);
		setup(02,"grass3",false);
		
		//GROUND
		setup(03,"Ground2",false);
		setup(04,"Ground3",false);
		setup(05,"Ground4",false);
		setup(06,"Ground5",false);
		
		setup(07,"Sprite-0002",false);
		setup(8,"Sprite-0003",false);
		setup(9,"Sprite-0004",false);
		setup(10,"Sprite-0005",false);
		setup(11,"Sprite-0006",false);
		setup(12,"Sprite-0007",false);
		setup(13,"Sprite-0008",false);
		setup(14,"Sprite-0009",false);
		setup(15,"Sprite-0010",false);
		
		//MOUNTAIN
		setup(16,"mountain1",true);
		setup(17,"mountain2",true);
		setup(18,"mountain3",true);
		setup(19,"mountain4",true);
		setup(20,"mountain5",true);
		setup(21,"mountain6",true);
		setup(22,"mountain7",true);
		setup(23,"mountain8",true);
		setup(24,"mountain9",true);
		
		setup(25,"Mountain21",true);
		setup(26,"Mountain22",true);
		setup(27,"Mountain23",true);
		setup(28,"Mountain24",true);
		setup(29,"Mountain25",true);
		setup(30,"Mountain26",true);
		setup(31,"Mountain27",true);
		setup(32,"Mountain28",true);
		setup(33,"Mountain29",true);

		//WATER
		setup(34,"water01",true);
		setup(35,"water02",true);
		setup(36,"water03",true);
		setup(37,"water04",true);
		setup(38,"water05",true);
		setup(39,"water06",true);
		setup(40,"water07",true);
		setup(41,"water08",true);
		setup(42,"water09",true);
		
		setup(43,"water2",true);
		setup(44,"water3",true);
		setup(45,"water4",true);
		setup(46,"water5",true);
		
		//HOUSE
		setup(47,"house1",true);
		setup(48,"house2",true);
		setup(49,"house3",true);
		setup(50,"house4",true);
		setup(51,"house5",true);
		setup(52,"house6",true);
		setup(53,"house7",true);
		setup(54,"house8",true);
		setup(55,"house9",true);
		setup(56,"house10",true);
		setup(57,"house11",true);
		setup(58,"house12",true);
		setup(59,"house13",true);
		setup(60,"house14",true);
		setup(61,"house15",true);
		setup(62,"house16",true);
		
		//TREE 
		setup(63,"48",true);
		setup(64,"tree1",true);
		setup(65,"tree2",true);
		setup(66,"tree3",true);
		setup(67,"tree4",true);
		setup(68,"tree5",true);
		setup(69,"tree6",true);
		setup(70,"tree7",true);
		setup(71,"tree8",true);
		setup(72,"tree9",true);
		setup(73,"tree10",true);
		setup(74,"tree11",true);
		setup(75,"tree12",true);
		setup(76,"tree13",true);
		setup(77,"tree14",true);
		setup(78,"tree15",true);
		setup(79,"tree16",true);
		
		//TUSSOCK
		setup(80,"tussock1",false);
		setup(81,"tussock2",false);
		setup(82,"tussock3",false);
		setup(83,"tussock4",false);
		
		//FENCE
		setup(84,"fence1",true);
		setup(85,"fence2",true);
		setup(86,"fence3",true);
		setup(87,"fence4",true);
		setup(88,"fence5",true);
		setup(89,"fence6",true);
		setup(90,"fence7",true);
		setup(91,"fence8",true);
		setup(92,"fence9",true);
		
		//NPCHOUSE
		setup(93,"npchouse1",true);
		setup(94,"npchouse2",true);
		setup(95,"npchouse3",true);
		setup(96,"npchouse4",true);
		setup(97,"npchouse5",true);
		setup(98,"npchouse6",true);
		setup(99,"npchouse7",true);
		setup(100,"npchouse8",true);
		setup(101,"npchouse9",true);
		setup(102,"npchouse10",true);
		setup(103,"npchouse11",true);
		setup(104,"npchouse12",true);
		setup(105,"npchouse13",true);
		setup(106,"npchouse14",true);
		setup(107,"npchouse15",true);
		setup(108,"npchouse16",true);
		setup(109,"npchouse17",true);
		setup(110,"npchouse18",true);
		setup(111,"npchouse19",true);
		setup(112,"npchouse20",true);
		setup(113,"npchouse21",true);
		setup(114,"npchouse22",true);
		setup(115,"npchouse23",true);
		setup(116,"npchouse24",true);
		setup(117,"npchouse25",true);
		setup(118,"npchouse26",true);
		setup(119,"npchouse27",true);
		setup(120,"npchouse28",true);
		setup(121,"npchouse29",true);
		setup(122,"npchouse30",true);
		
		//INSIDEHOUSE
		setup(123,"insidehouse1",true);
		setup(124,"insidehouse2",true);
		setup(125,"insidehouse3",true);
		setup(126,"insidehouse4",true);
		setup(127,"insidehouse5",true);
		setup(128,"insidehouse6",true);
		setup(129,"insidehouse7",true);
		setup(130,"insidehouse8",true);
		setup(131,"insidehouse9",true);
		setup(132,"insidehouse10",true);
		setup(133,"insidehouse11",false);
		setup(134,"insidehouse12",false);
		setup(135,"insidehouse13",false);
		setup(136,"insidehouse14",false);
		setup(137,"insidehouse15",false);
		setup(138,"insidehouse16",false);
		setup(139,"insidehouse17",false);
		setup(140,"insidehouse18",false);
		setup(141,"insidehouse19",true);
		setup(142,"insidehouse20",true);
		setup(143,"insidehouse21",false);
		setup(144,"insidehouse22",false);
		setup(145,"insidehouse23",false);
		setup(146,"insidehouse24",false);
		setup(147,"insidehouse25",false);
		setup(148,"insidehouse26",false);
		setup(149,"insidehouse27",false);
		setup(150,"insidehouse28",false);
		setup(151,"insidehouse29",true);
		setup(152,"insidehouse30",true);
		setup(153,"insidehouse31",false);
		setup(154,"insidehouse32",false);
		setup(155,"insidehouse33",false);
		setup(156,"insidehouse34",false);
		setup(157,"insidehouse35",false);
		setup(158,"insidehouse36",false);
		setup(159,"insidehouse37",false);
		setup(160,"insidehouse38",false);
		setup(161,"insidehouse39",true);
		setup(162,"insidehouse40",true);
		setup(163,"insidehouse41",false);
		setup(164,"insidehouse42",false);
		setup(165,"insidehouse43",false);
		setup(166,"insidehouse44",false);
		setup(167,"insidehouse45",false);
		setup(168,"insidehouse46",false);
		setup(169,"insidehouse47",false);
		setup(170,"insidehouse48",false);
		setup(171,"insidehouse49",true);
		setup(172,"insidehouse50",true);
		setup(173,"insidehouse51",false);
		setup(174,"insidehouse52",false);
		setup(175,"insidehouse53",false);
		setup(176,"insidehouse54",false);
		setup(177,"insidehouse55",false);
		setup(178,"insidehouse56",false);
		setup(179,"insidehouse57",false);
		setup(180,"insidehouse58",false);
		setup(181,"insidehouse59",true);
		setup(182,"insidehouse60",true);
		setup(183,"insidehouse61",false);
		setup(184,"insidehouse62",false);
		setup(185,"insidehouse63",false);
		setup(186,"insidehouse64",false);
		setup(187,"insidehouse65",false);
		setup(188,"insidehouse66",false);
		setup(189,"insidehouse67",false);
		setup(190,"insidehouse68",false);
		setup(191,"insidehouse69",true);
		setup(192,"insidehouse70",true);
		setup(193,"insidehouse71",true);
		setup(194,"insidehouse72",true);
		setup(195,"insidehouse73",true);
		setup(196,"insidehouse74",true);
		setup(197,"insidehouse75",true);
		setup(198,"insidehouse76",true);
		setup(199,"insidehouse77",true);
		setup(200,"insidehouse78",true);
		setup(201,"insidehouse79",true);
		setup(202,"insidehouse80",true);
		
	
	}
	public void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image =ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath, int map) {
		try {
			InputStream is= getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col =0;
			int row =0;
			
			while (col< gp.maxWorldCol&& row < gp.maxWorldRow) {
				
				String line = br.readLine(); // read a line of text and put into line(String)
				
				while(col< gp.maxWorldCol) { 
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]); // change String -> Interger
					
					mapTileNum[map][col][row] = num; // stored extracted number into mapTileNum[][]
					col++;
					
				}
				if(col == gp.maxWorldCol) { // read another line
					col =0;
					row++ ;
				}	
			}
			br.close();
			 
		}catch( Exception e){
			
			
		}
	}
	public void draw(Graphics2D g2) {
		int worldCol =0;
		int worldRow =0;
		
		while (worldCol <gp.maxWorldCol && worldRow < gp.maxWorldRow ) {
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.playerGra.worldX + gp.playerGra. getScreenX( );
			int screenY = worldY - gp.playerGra.worldY + gp.playerGra.getScreenY();

			//STOP MOVING THE CAMERA AT THE EDGE
			if(gp.playerGra. getScreenX( ) > gp.playerGra.worldX) {
				screenX = worldX;
			}
			if(gp.playerGra. getScreenY() > gp.playerGra.worldY) {
				screenY = worldY;
			}
			int rightOffset = gp.screenWidth - gp.playerGra. getScreenX( );
			if(rightOffset > gp.worldWidth - gp.playerGra.worldX) {
				screenX = gp.screenWidth - (gp.worldWidth - worldX);
			}
			int bottomOffset = gp.screenHeight - gp.playerGra.getScreenY();
			if(bottomOffset > gp.worldHeigth - gp.playerGra.worldY) {
				screenY = gp.screenHeight - (gp.worldHeigth - worldY);
			}
			// as long as a tile is in this boundary => draw
			if(	worldX + gp.tileSize > gp.playerGra.worldX - gp.playerGra. getScreenX( ) &&
				worldX - gp.tileSize < gp.playerGra.worldX + gp.playerGra. getScreenX( ) &&
				worldY + gp.tileSize > gp.playerGra.worldY - gp.playerGra.getScreenY() &&
				worldY - gp.tileSize < gp.playerGra.worldY + gp.playerGra.getScreenY()) {

					g2.drawImage(tile[tileNum].image, screenX, screenY, null);
						
			}
			else if(gp.playerGra. getScreenX( ) > gp.playerGra.worldX||
					gp.playerGra.getScreenY() > gp.playerGra.worldY ||
					rightOffset > gp.worldWidth - gp.playerGra.worldX ||
					bottomOffset > gp.worldHeigth - gp.playerGra.worldY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}

			worldCol++ ;

			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}			
		}
	}
//	public void nextMap() {
//		loadMap("/res/maps/worldV2.txt");
//			
//	}
//


}
