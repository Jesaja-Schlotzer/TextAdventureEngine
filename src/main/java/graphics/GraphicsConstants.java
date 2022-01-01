package graphics;

public class GraphicsConstants {
	
	public static final int PIXEL_SIZE_X = 5;
	public static final int PIXEL_SIZE_Y = 5;

	public static final int REF_SCREEN_SIZE_X = 1920;
	public static final int REF_SCREEN_SIZE_Y = 1080;
	
	public static final int RENDER_SCREEN_SIZE_X = REF_SCREEN_SIZE_X / PIXEL_SIZE_X; // 192
	public static final int RENDER_SCREEN_SIZE_Y = REF_SCREEN_SIZE_Y / PIXEL_SIZE_Y; // 108
	
	
	
	public static float SCALEX;
	public static float SCALEY;
	
	public static int XOFF;
	public static int YOFF;
	
	
	
	public static boolean SCREEN_SCALING_MODE_XY_EQUALS = false;
	public static boolean SCREEN_SCALING_MODE_INTEGER_PIXEL_SIZE = false;
	
}
