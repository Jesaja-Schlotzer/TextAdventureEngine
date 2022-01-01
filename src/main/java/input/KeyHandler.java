package input;


import data.Settings;
import screen.ScreenHandler;


public class KeyHandler{
	public static char lastKey;
	public static boolean[] keys = new boolean[100];
	
	/*
	@Override
	public void keyPressed(KeyEvent e) {
		setKey(e.getKeyCode(), true);
		
		lastKey = e.getKeyChar();
		
		ScreenHandler.getInstance().keyPressed((short) lastKey);
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_TAB) {
			Settings.debug = !Settings.debug;
		}
		
		
		// TextSupplierFactory
		TextSupplierFactory.getInstance().keyPressed(e);
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
		setKey(e.getKeyCode(), false);
		
		
		ScreenHandler.getInstance().keyReleased();
		
		
	}
	
	
	
	
	
	private void setKey(short chr, boolean b){
		switch(chr){
			case KeyEvent.VK_A: keys[ 0] = b; break; // a  A
			case KeyEvent.VK_B: keys[ 1] = b; break; // b  B
			case KeyEvent.VK_C: keys[ 2] = b; break; // c  C
			case KeyEvent.VK_D: keys[ 3] = b; break; // d  D
			case KeyEvent.VK_E: keys[ 4] = b; break; // e  E
			case KeyEvent.VK_F: keys[ 5] = b; break; // f  F
			case KeyEvent.VK_G: keys[ 6] = b; break; // g  G
			case KeyEvent.VK_H: keys[ 7] = b; break; // h  H
			case KeyEvent.VK_I: keys[ 8] = b; break; // i  I
			case KeyEvent.VK_J: keys[ 9] = b; break; // j  K
			case KeyEvent.VK_K: keys[10] = b; break; // k  J
			case KeyEvent.VK_L: keys[11] = b; break; // l  L
			case KeyEvent.VK_M: keys[12] = b; break; // m  M
			case KeyEvent.VK_N: keys[13] = b; break; // n  N
			case KeyEvent.VK_O: keys[14] = b; break; // o  O
			case KeyEvent.VK_P: keys[15] = b; break; // p  P
			case KeyEvent.VK_Q: keys[16] = b; break; // q  Q
			case KeyEvent.VK_R: keys[17] = b; break; // r  R
			case KeyEvent.VK_S: keys[18] = b; break; // s  S
			case KeyEvent.VK_T: keys[19] = b; break; // t  T
			case KeyEvent.VK_U: keys[20] = b; break; // u  U
			case KeyEvent.VK_V: keys[21] = b; break; // v  V
			case KeyEvent.VK_W: keys[22] = b; break; // w  W
			case KeyEvent.VK_X: keys[23] = b; break; // x  X
			case KeyEvent.VK_Y: keys[24] = b; break; // y  Y
			case KeyEvent.VK_Z: keys[25] = b; break; // z  Z
			
			
			case KeyEvent.VK_0: keys[26] = b; break; // 0  =
			case KeyEvent.VK_1: keys[27] = b; break; // 1  !
			case KeyEvent.VK_2: keys[28] = b; break; // 2  "
			case KeyEvent.VK_3: keys[29] = b; break; // 3  §
			case KeyEvent.VK_4: keys[30] = b; break; // 4  $
			case KeyEvent.VK_5: keys[31] = b; break; // 5  %
			case KeyEvent.VK_6: keys[32] = b; break; // 6  &
			case KeyEvent.VK_7: keys[33] = b; break; // 7  /
			case KeyEvent.VK_8: keys[34] = b; break; // 8  (
			case KeyEvent.VK_9: keys[35] = b; break; // 9  )
			
			
			case KeyEvent.VK_DOWN: 	keys[36] = b; break; // Down
			case KeyEvent.VK_UP: 	keys[37] = b; break; // Up
			case KeyEvent.VK_LEFT: 	keys[38] = b; break; // Left
			case KeyEvent.VK_RIGHT: keys[39] = b; break; // Right
			
			case KeyEvent.VK_ALT: 			keys[40] = b; break; // Alt
			case KeyEvent.VK_ALT_GRAPH: 	keys[41] = b; break; // AltGr
			case KeyEvent.VK_BACK_SPACE:	keys[42] = b; break; // Backspace
			case KeyEvent.VK_CAPS_LOCK:		keys[43] = b; break; // CapsLock
			case KeyEvent.VK_COLON: 		keys[44] = b; break; // .  :
			case KeyEvent.VK_COMMA: 		keys[45] = b; break; // ,  ;
			case KeyEvent.VK_CONTROL: 		keys[46] = b; break; // CTRL
			case KeyEvent.VK_DELETE: 		keys[47] = b; break; // DEL
			
			
		}
	}
	
	
	
	public boolean isKeyPressed(short chr) {
		switch(chr){
			case 'A': return keys[0];
			case 'B': return keys[1];
			case 'C': return keys[2];
			case 'D': return keys[3];
			case 'E': return keys[4];
			case 'F': return keys[5];
			case 'G': return keys[6];
			case 'H': return keys[7];
			case 'I': return keys[8];
			case 'J': return keys[9];
			case 'K': return keys[10];
			case 'L': return keys[11];
			case 'M': return keys[12];
			case 'N': return keys[13];
			case 'O': return keys[14];
			case 'P': return keys[15];
			case 'Q': return keys[16];
			case 'R': return keys[17];
			case 'S': return keys[18];
			case 'T': return keys[19];
			case 'U': return keys[20];
			case 'V': return keys[21];
			case 'W': return keys[22];
			case 'X': return keys[23];
			case 'Y': return keys[24];
			case 'Z': return keys[25];
			
			
			case '0': return keys[26];
			case '1': return keys[27];
			case '2': return keys[28];
			case '3': return keys[29];
			case '4': return keys[30];
			case '5': return keys[31];
			case '6': return keys[32];
			case '7': return keys[33];
			case '8': return keys[34];
			case '9': return keys[35];
			
			
			case KeyEvent.VK_ALT: return keys[40];
			
		}
		return false;
	}*/

}
