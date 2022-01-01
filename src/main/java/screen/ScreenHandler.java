package screen;

import components.Sprite;
import components.SpriteRenderer;
import debugging.Debugger;
import engine.GameObject;
import engine.Transform;
import engine.scenes.Scene;
import engine.scenes.SceneHandler;
import game.scenes.MainMenuScene;
import graphics.GraphicsConstants;
import graphics.colors.Color;
import graphics.colors.ColorUtils;
import org.joml.Vector2f;
import org.joml.Vector4f;
import screen.fragmentRecorder.FragmentRecorderPool;


public class ScreenHandler {
	
	
	private static ScreenHandler instance;
	
	private ScreenHandler() {
		for (int x = 0; x < screenBufferWorkaround.length; x++) {
			for (int y = 0; y < screenBufferWorkaround[0].length; y++) {

				screenBufferWorkaround[x][y] = new SpriteRenderer(new Vector4f());

				int tempY = 215 - y;

				GameObject quad = new GameObject("Pixel" + x + "," + tempY, new Transform(new Vector2f(x, tempY), new Vector2f(1, 1)));
				quad.addComponent(screenBufferWorkaround[x][y]);
				SceneHandler.getInstance().getCurrentScene().addGameObjectToScene(quad);

			}
		}
	}
	
	
	public static ScreenHandler getInstance() {
		if(instance == null) {
			instance = new ScreenHandler();
		}
		return instance;
	}
	
	
	
	
	
	
	
	
	// ScreenHandling
	
	public void draw() {
		getScreen().draw();
		
		//System.out.println("LoadQueue: " + Img.imgsToLoadQueue.size());
		//Painter.checkImgClearTimers();
		Debugger.drawDebugging();
		
		
		
		if(nextScreen != null) {
			screen.close();
			
			screen = nextScreen;
			
			screen.open();
			
			nextScreen = null;
		}
	}
	
	
	public void mouseClick() {
		getScreen().mouseClick();
	}
	
	
	public void mouseWheel(float wheelRotation) {
		getScreen().mouseWheel(wheelRotation);
		
	}
	
	
	public void keyPressed(short keyCode) {
		getScreen().keyPressed(keyCode);
	}
	
	
	public void keyReleased() {
		getScreen().keyReleased();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Weiteres
	
	
	private Screen screen = Screens.main;
	private Screen nextScreen;
	
	
	public void requestNextScreen(Screen nextScreen) {
		if(nextScreen == null) {
			return;
		}
		
		this.nextScreen = nextScreen;
	}
	
	
	
	private Screen getScreen() {
		return (screen != null ? screen : Screens.main);
	}

	
	// index
	// byte[][] screenBuffer = new byte[192][108];
	// byte[][] lastScreenBuffer = new byte[192][108];


	private SpriteRenderer[][] screenBufferWorkaround = new SpriteRenderer[384][216];

	private Color[][] screenBuffer = new Color[GraphicsConstants.RENDER_SCREEN_SIZE_X][GraphicsConstants.RENDER_SCREEN_SIZE_Y];
	private Color[][] lastScreenBuffer = new Color[GraphicsConstants.RENDER_SCREEN_SIZE_X][GraphicsConstants.RENDER_SCREEN_SIZE_Y];
	private boolean[][] screenBufferChange = new boolean[GraphicsConstants.RENDER_SCREEN_SIZE_X][GraphicsConstants.RENDER_SCREEN_SIZE_Y];
	
	
	private float[] pixelSizeListX;
	private float[] pixelSizeListY;

	
	
	
	public void setScreenBuffer(int x, int y, Color value) {
		if(value == null) {
			return;
		}
		
		
		if(value.ai() == 0) {
			return;
		}
		

		if (x < 0 || x >= GraphicsConstants.RENDER_SCREEN_SIZE_X || y < 0 || y >= GraphicsConstants.RENDER_SCREEN_SIZE_Y) {
			/*if(FragmentRecorderPool.getInstance().isRecording()) {
				FragmentRecorderPool.getInstance().record(x, y, value);
			}*/ // TODO Remove FragmentRecorder
			return;	
		}

		
		if (value.ai() == 255) {
			
			/*if(FragmentRecorderPool.getInstance().isRecording()) {
				FragmentRecorderPool.getInstance().record(x, y, value);
			}
			
			if(!FragmentRecorderPool.getInstance().isRecording() || !FragmentRecorderPool.getInstance().getRecordModeState(0)) {
				screenBuffer[x][y] = value;
			}*/ // TODO Remove FragmentRecorder

			screenBuffer[x][y] = value;

		}else {
			Color currentColor = screenBuffer[x][y];
			
			int currentColorAsInt = currentColor.toInt();
			
			int newRed = ((currentColorAsInt >> 16) & 0xff) - (int)((((currentColorAsInt >> 16) & 0xff) - ((value.toInt() >> 16) & 0xff)) * value.a());
		    int newGreen = ((currentColorAsInt >> 8) & 0xff) - (int)((((currentColorAsInt >> 8) & 0xff) - ((value.toInt() >> 8) & 0xff)) * value.a());
		    int newBlue = (currentColorAsInt & 0xff) - (int)(((currentColorAsInt & 0xff) - (value.toInt() & 0xff)) * value.a());
		    
		    
			//int newRed = currentColor.ri() - (int) (currentColor.ri() - (value.ri()) * value.a());
			//int newGreen = currentColor.gi() - (int) (currentColor.gi() - (value.gi()) * value.a());
			//int newBlue = currentColor.bi() - (int) (currentColor.bi() - (value.bi()) * value.a());
		    
		    
		    // Buffer-Part
		    /*if(FragmentRecorderPool.getInstance().isRecording()) {
		    	FragmentRecorderPool.getInstance().record(x, y, ColorUtils.intToColor(255 << 24 | newRed << 16 | newGreen << 8 | newBlue));
		    }
		    if(!FragmentRecorderPool.getInstance().getRecordModeState(0)) {
		    	screenBuffer[x][y] = ColorUtils.intToColor(255 << 24 | newRed << 16 | newGreen << 8 | newBlue);
		    }*/// TODO Remove FragmentRecorder

			screenBuffer[x][y] = ColorUtils.intToColor(255 << 24 | newRed << 16 | newGreen << 8 | newBlue);
			
		}
		
		// screenBuffer[x][y] = value;
		
		// if(lastScreenBuffer[x][y] != value){
		// screenBufferChange[x][y] = true;
		// }else{
		// screenBufferChange[x][y] = false;
		// }
	}
	
	
	
	
	public void drawScreenBuffer(Scene scene) {
		//if (screenBufferEqualsLastScreenBuffer()) {
			// System.out.println("Frame konnte übersprungen werden");
			 //return;
		//}
		// println("\n\n");


		for (int x = 0; x < screenBuffer.length; x++) {
			for (int y = 0; y < screenBuffer[0].length; y++) {
				// if(screen.screenBufferChange[x][y]){
				

				// TODO Workaround:
				screenBufferWorkaround[x][y].getColor().set(getScreenBuffer(x, y).r(), getScreenBuffer(x, y).g(), getScreenBuffer(x, y).b(), getScreenBuffer(x, y).a());
				screenBufferWorkaround[x][y].setDirty();

				/*
				gl2.glBegin(GL2.GL_TRIANGLE_STRIP);
					gl2.glColor4f(getScreenBuffer(x, y).r(), getScreenBuffer(x, y).g(), getScreenBuffer(x, y).b(), getScreenBuffer(x, y).a());
					gl2.glVertex2i(posX, posY);
					gl2.glColor4f(getScreenBuffer(x, y).r()+0.2f, getScreenBuffer(x, y).g(), getScreenBuffer(x, y).b(), getScreenBuffer(x, y).a());
					gl2.glVertex2i(posX+1, posY);
					gl2.glColor4f(getScreenBuffer(x, y).r()+0.35f, getScreenBuffer(x, y).g(), getScreenBuffer(x, y).b(), getScreenBuffer(x, y).a());
					gl2.glVertex2i(posX, posY+1);
					gl2.glColor4f(getScreenBuffer(x, y).r()+0.5f, getScreenBuffer(x, y).g(), getScreenBuffer(x, y).b(), getScreenBuffer(x, y).a());
					gl2.glVertex2i(posX+1, posY+1);
					
				gl2.glEnd();
				*/
				
				// }
				// screen.screenBufferChange[x][y] = false;
				//+= pixelSizeListY[y];
			}
			//+= pixelSizeListX[x];
		}

		lastScreenBuffer = screenBuffer;
	}
	
	
	
	
	
	
	
	
	
	
	
	private void generatePixelSizeLists(){
		float scaleX = 1920/*Renderer.windowWidth()*/  / (float)GraphicsConstants.REF_SCREEN_SIZE_X;
		float scaleY = 1080/*Renderer.windowHeight()*/ / (float)GraphicsConstants.REF_SCREEN_SIZE_Y;
		
		float pixelSizeX = scaleX * GraphicsConstants.PIXEL_SIZE_X;
		float pixelSizeY = scaleY * GraphicsConstants.PIXEL_SIZE_Y;
		
		
		pixelSizeListX = new float[GraphicsConstants.REF_SCREEN_SIZE_X/GraphicsConstants.PIXEL_SIZE_X];
	    pixelSizeListY = new float[GraphicsConstants.REF_SCREEN_SIZE_Y/GraphicsConstants.PIXEL_SIZE_Y];
	    
	    
	    
	    if(GraphicsConstants.SCREEN_SCALING_MODE_XY_EQUALS){ // Bildschirmgröße angepasst (schwarzer Rand) == true
	    	if(pixelSizeX < pixelSizeY){
	    		pixelSizeY = pixelSizeX;
	    	}else{
	    		pixelSizeX = pixelSizeY;
	    	} 
	    }
	    
	    if(GraphicsConstants.SCREEN_SCALING_MODE_INTEGER_PIXEL_SIZE){ // pixelSize nur als Ganzzahl (1, 2, 3...)
	    	pixelSizeX = (int) Math.floor(pixelSizeX);
	    	pixelSizeY = (int) Math.floor(pixelSizeY);
	    }
	    
	    if(GraphicsConstants.SCREEN_SCALING_MODE_XY_EQUALS || GraphicsConstants.SCREEN_SCALING_MODE_INTEGER_PIXEL_SIZE){
	    	GraphicsConstants.SCALEX = pixelSizeX / GraphicsConstants.PIXEL_SIZE_X; // Bei 'PIXEL_SIZE_X' stand vorher '10' - vielleicht war das vorher gewollt kp
	    	GraphicsConstants.SCALEY = pixelSizeY / GraphicsConstants.PIXEL_SIZE_Y; // Bei 'PIXEL_SIZE_Y' stand vorher '10' - vielleicht war das vorher gewollt kp
	      
	      
	    	GraphicsConstants.XOFF = (int) ((1920/*Renderer.windowWidth()*/ - (pixelSizeX * pixelSizeListX.length)) / 2);
	    	GraphicsConstants.YOFF = (int) ((1080/*Renderer.windowHeight()*/ - (pixelSizeY * pixelSizeListY.length)) / 2);
	      
	    	if(GraphicsConstants.SCALEX < GraphicsConstants.SCALEY){
	    		GraphicsConstants.SCALEY = GraphicsConstants.SCALEX;
	    		GraphicsConstants.YOFF = (int) (1080/*Renderer.windowHeight()*/ - ((GraphicsConstants.SCALEY * GraphicsConstants.PIXEL_SIZE_X) * pixelSizeListY.length)) / 2;
	    	}else{
	    		GraphicsConstants.SCALEX = GraphicsConstants.SCALEY;
	    		GraphicsConstants.XOFF = (int) (1920/*Renderer.windowWidth()*/  - ((GraphicsConstants.SCALEX * GraphicsConstants.PIXEL_SIZE_Y) * pixelSizeListX.length)) / 2;
	    	}
	    	
	    }else{
	    	GraphicsConstants.SCALEX = 1920/*Renderer.windowWidth()*/ / (float)GraphicsConstants.REF_SCREEN_SIZE_X;
	    	GraphicsConstants.SCALEY = 1080/*Renderer.windowHeight()*/ / (float)GraphicsConstants.REF_SCREEN_SIZE_Y;
	    }
	    
	    
	    float x = 0;
	    for(int i = 0; i < pixelSizeListX.length; i++){
	    	x = x - (int) Math.floor(x);
	    	x += pixelSizeX;
	    	
	    	pixelSizeListX[i] = (int) Math.floor(x);
	    }
	    
	    
	    float y = 0;
	    for(int i = 0; i < pixelSizeListY.length; i++){
	    	y = y - (int) Math.floor(y);
	    	y += pixelSizeY;
	      
	    	pixelSizeListY[i] = (int) Math.floor(y);
	    }
	    
	    
	    
	    if(!GraphicsConstants.SCREEN_SCALING_MODE_XY_EQUALS && !GraphicsConstants.SCREEN_SCALING_MODE_INTEGER_PIXEL_SIZE){
	    	if(1920/*Renderer.windowWidth()*/ > sumX() || 1080/*Renderer.windowHeight()*/ > sumY()){
	    		trimBlackFrame();
	    	}
	    }
	    
	    //if(angepasst && !pixelPerfect){
	    //  if(Renderer.screenHeight > sumY()){
	    //    trimBlackFrameTB();
	    //  }
	    //  if(Renderer.screenWidth > sumX()){
	    //    trimBlackFrameLR();
	    //  }
	    //}
	    
	  }
	
	

	private void trimBlackFrame() {
		// if(split(settings[1], ' ')[1].equals("0")){
		//Renderer.getWindow().setSize((int) Math.floor(sumX()), (int) Math.floor(sumY()));
		// }
	}

	private void trimBlackFrameLR() {
		// if(split(settings[1], ' ')[1].equals("0")){
		//Renderer.getWindow().setSize((int) Math.floor(sumX()), (int) Math.floor(sumY()));
		// }
	}

	private void trimBlackFrameTB() {
		// if(split(settings[1], ' ')[1].equals("0")){
		//Renderer.getWindow().setSize((int) Math.floor(sumX()), (int) Math.floor(sumY()));
		// }
	}

	
	
	
	
	// SumX/Y

	private float sumX() {
		float sumX = 0;
		for (int i = 0; i < pixelSizeListX.length; i++) {
			sumX += pixelSizeListX[i];
		}
		return sumX;
	}

	private float sumY() {
		float sumY = 0;
		for (int i = 0; i < pixelSizeListY.length; i++) {
			sumY += pixelSizeListY[i];
		}
		return sumY;
	}
	
	
	
	
	private boolean screenBufferEqualsLastScreenBuffer() {
		for (int i = 0; i < ScreenHandler.getInstance().screenBuffer.length; i++) {
			for (int j = 0; j < ScreenHandler.getInstance().screenBuffer[0].length; j++) {
				if (screenBuffer[i][j] != lastScreenBuffer[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	public Color[][] getScreenBuffer(){
		return screenBuffer;
	}
	

	public Color getScreenBuffer(int x, int y){
		if ((x >= 0 && x < screenBuffer.length && y >= 0 && y < screenBuffer[0].length) && screenBuffer[x][y] != null) {
			return screenBuffer[x][y];
		}
		return Color.DEBUG;
	}
	
}
