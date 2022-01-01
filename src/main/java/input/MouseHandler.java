package input;

import data.Settings;
import debugging.Debugger;
import graphics.GraphicsConstants;
import screen.ScreenHandler;


public class MouseHandler{

	public static boolean mousePressed;
	
	public static int mouseX;
	public static int mouseY;
	
	
	private static byte pressedMouseButtons = (byte) 0B0000_0000;
	
	public static final byte LEFT_MOUSE_BUTTON =  (byte) 0B1000_0000;
	public static final byte MOUSE_WHEELE = 	  (byte) 0B0100_0000;
	public static final byte RIGHT_MOUSE_BUTTON = (byte) 0B0010_0000;
	
	/*
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
		
		
		registerMousePress((short) e.getButton());
		
		

		ScreenHandler.getInstance().mouseClick();
	}

	
	

	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
		
		
		
		registerMouseRelease(e.getButton());
	}

	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = (int) (e.getX() / (Renderer.windowWidth() / (float) GraphicsConstants.PIXEL_SIZE_X) * GraphicsConstants.RENDER_SCREEN_SIZE_X);
		mouseY = (int) (e.getY() / (Renderer.windowHeight() / (float) GraphicsConstants.PIXEL_SIZE_Y) * GraphicsConstants.RENDER_SCREEN_SIZE_Y);
		
		
		
	}

	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = (int) (e.getX() / (Renderer.windowWidth() / (float) GraphicsConstants.PIXEL_SIZE_X) * GraphicsConstants.RENDER_SCREEN_SIZE_X);
		mouseY = (int) (e.getY() / (Renderer.windowHeight() / (float) GraphicsConstants.PIXEL_SIZE_Y) * GraphicsConstants.RENDER_SCREEN_SIZE_Y);
		
		
		
		
	}

	@Override
	public void mouseWheelMoved(MouseEvent e) {
		float wheelRotation = e.getRotation()[0];
		if(wheelRotation == 0.0f) {
			wheelRotation = e.getRotation()[1];
		}
		// TODO wegen Shift schauen
		
		ScreenHandler.getInstance().mouseWheel(wheelRotation);
	}*/
	
	
	
	
	
	/*
	private static void registerMousePress(short key) {
		switch(key) {
			case MouseEvent.BUTTON1: // LEFT
				pressedMouseButtons |= LEFT_MOUSE_BUTTON;
				break;
			case MouseEvent.BUTTON2: // MIDDLE
				pressedMouseButtons |= MOUSE_WHEELE;
				break;
			case MouseEvent.BUTTON3: // RIGHT
				pressedMouseButtons |= RIGHT_MOUSE_BUTTON;
				break;
		}
	}
	
	
	private static void registerMouseRelease(short key) {
		switch(key) {
			case MouseEvent.BUTTON1: // LEFT
				pressedMouseButtons = (byte) ((LEFT_MOUSE_BUTTON & pressedMouseButtons) ^ pressedMouseButtons);
				break;
			case MouseEvent.BUTTON2: // MIDDLE
				pressedMouseButtons = (byte) ((MOUSE_WHEELE & pressedMouseButtons) ^ pressedMouseButtons);
				break;
			case MouseEvent.BUTTON3: // RIGHT
				pressedMouseButtons = (byte) ((RIGHT_MOUSE_BUTTON & pressedMouseButtons) ^ pressedMouseButtons);
				break;
		}
	}
	
	
	*/
	
	public static boolean isPressed(int button) {
		if(button == LEFT_MOUSE_BUTTON || button == MOUSE_WHEELE || button == RIGHT_MOUSE_BUTTON) {
			return (pressedMouseButtons & button) == button;
		}else {
			return false;
		}
	}
	
	
	
	
	
	
	public static boolean overButton(int xl, int yo, int xr, int yu) {
		if(Settings.debug){
			Debugger.addDebugShape(Debugger.DEBUG_RECT, xl, yo, (xr-xl), (yu-yo));
		}
		return (mouseX > xl && mouseX < xr && mouseY > yo && mouseY < yu);
	}
	
}


