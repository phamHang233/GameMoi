package move;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import screen.GamePanel;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed, shotKeyPressed;
    public KeyHandler(GamePanel gp) {
    	this.gp = gp;
    }
    // Override
    public void  keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        int code = e.getKeyCode(); // return the number of the pressed key
        // TITLE STATE
        if(gp.gameState == gp.titleState) {
			titleState(code);
        }
        //SET NAME STATE
        else if(gp.gameState == gp.setNameState) {
        	setNameState(keyChar, code);
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState) { //video15
    		playState(code);
    	}

    	// PAUSE STATE video15
    	else if(gp.gameState == gp.pauseState){
    		pauseState(code);
    	}
    	
    	// DIALOGUE STATE video15
    	else if(gp.gameState == gp.dialogueState){//video15
			dialogueState(code);
    	}

		// CHARACTER STATE
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
        // OPTIONS STATE
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
        // GAME OVER STATE
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
    }

	public void titleState(int code) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.setNameState;
				}
				if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
	}
public void setNameState(char keyChar, int code) {
	if((keyChar >= 48 && keyChar <= 57 )||
			(keyChar >= 65 && keyChar <= 90) || (keyChar >= 97 && keyChar <= 122)) {
			gp.playerGra.player.name += keyChar;
		}
		if(keyChar == KeyEvent.VK_SPACE) {
			gp.playerGra.player.name += " ";
		}
		if(keyChar == KeyEvent.VK_BACK_SPACE) {
			if(gp.playerGra.player.name != null && gp.playerGra.player.name.length() > 0) {
				gp.playerGra.player.name = gp.playerGra.player.name.substring(0, gp.playerGra.player.name.length() - 1);
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
			gp.playMusic(0);
		}
	}
	public void playState(int code) {
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
			gp.stopMusic();
		}
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(code == KeyEvent.VK_K) {
			shotKeyPressed = true;
		}
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}
	}
	public void pauseState(int code) {
		if(code == KeyEvent.VK_P){
			gp.gameState = gp.playState;
			gp.playMusic(0);
		}
	}
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER){
			gp.gameState = gp.playState;
		}
	}
	public void characterState(int code) {
		if(code == KeyEvent.VK_C) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_W){
			if(gp.ui.slotRow !=0){
				gp.ui.slotRow--;
				gp.playSE(8);
			}
		}
		if(code == KeyEvent.VK_S){
			if(gp.ui.slotRow!=3){
				gp.ui.slotRow++;
				gp.playSE(8);
			}
		}
		if(code == KeyEvent.VK_A){
			if(gp.ui.slotCol!=0){
				gp.ui.slotCol--;
				gp.playSE(8);
			}
		}
		if(code == KeyEvent.VK_D){
			if(gp.ui.slotCol!=4){
				gp.ui.slotCol++;
				gp.playSE(8);
			}
		}
		if(code == KeyEvent.VK_ENTER) {
	    	gp.playerGra.player.selectItem(gp, gp.playerGra);
	    	
	    }
	}
public void optionsState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		int maxCommandNum = 0;
		switch(gp.ui.getSubState()) {
		case 0: maxCommandNum = 5;
		break;
		case 3: maxCommandNum = 1;
		break;
		}
		
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			gp.playSE(8);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			gp.playSE(8);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_A) {
			if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
				gp.music.volumeScale--;
				gp.music.checkVolume();
				gp.playSE(8);
			}
			if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
				gp.se.volumeScale--;
				gp.playSE(8);
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSE(8);
			}
			if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
					gp.playSE(8);
			}
		}
			
	}
    public void gameOverState(int code) {
    	if(code == KeyEvent.VK_W) {
    		gp.ui.commandNum--;
    		if(gp.ui.commandNum < 0) {
    			gp.ui.commandNum = 1;
    		}
    		gp.playSE(8);
    	}
    	if(code == KeyEvent.VK_S) {
    		gp.ui.commandNum++;
    		if(gp.ui.commandNum > 1) {
    			gp.ui.commandNum = 0;
    		}
    		gp.playSE(8);
    	}
    	if(code == KeyEvent.VK_ENTER) {
    		if(gp.ui.commandNum == 0) {
    			gp.gameState = gp.playState;
    			gp.retry();
    			gp.playMusic(0);
    		}
    		else if(gp.ui.commandNum == 1) {
    			gp.gameState = gp.titleState;
    			gp.restart();
    		}
    	}
    }
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
		if (code == KeyEvent.VK_K) {
            shotKeyPressed = false;
        }
        
    }

    

public boolean getUpPressed() {return upPressed;}
public boolean getDownPressed() {return downPressed;}
public boolean getLeftPressed() {return leftPressed;}
public boolean getRightPressed() {return rightPressed;}
}
