package graphics.colors;

import java.util.function.UnaryOperator;

public class Color implements Cloneable{
	
	// Constants
	
	public static final Color BLACK = new Color("#000000");
	public static final Color SHADOW_GRAY = new Color("#232323");
	public static final Color RED = new Color("#db6f66");
	public static final Color ORANGE = new Color("#e9a96e");
	public static final Color GREEN = new Color("#9cdb66");
	public static final Color YELLOW = new Color("#ebdb87");
	public static final Color LIGHT_ORANGE = new Color("#eac079");
	
	
	public static final Color WHITE = new Color("#ffffde");
	public static final Color LIGHT_GRAY = new Color("#989882");
	public static final Color DARK_GRAY = new Color("#464646");
	  
	  
	public static final Color GLOBAL_BACKGROUND_COLOR = YELLOW;
	
	public static final Color CONSOLE_BACKGROUND = new Color("#4C493958");
	
	public static final Color TRANSPARENT = new Color("#FFFFFF00");
	public static final Color NO_COLOR = new Color("#FFFFFF00");
	
	
	public static final Color DEBUG = new Color("#FF00FF");
	
	// End
	
	
	
	private byte red, green, blue, alpha;
	
	
	public Color(String RGBAhexValue) { // Values between 0 - F
		if(RGBAhexValue.charAt(0) == '#') {
			RGBAhexValue = RGBAhexValue.substring(1);
			if(RGBAhexValue.length() == 6) {
				String colorAsBinary = ColorUtils.hexToBin(RGBAhexValue);
				colorAsBinary += "11111111";
				
				red = (byte) Integer.parseInt(colorAsBinary.substring(0, 8), 2);
				green = (byte) Integer.parseInt(colorAsBinary.substring(8, 16), 2);
				blue = (byte) Integer.parseInt(colorAsBinary.substring(16, 24), 2);
				alpha = (byte) Integer.parseInt(colorAsBinary.substring(24, 32), 2);
				
			}else if(RGBAhexValue.length() == 8) {
				String colorAsBinary = ColorUtils.hexToBin(RGBAhexValue);
				
				red = (byte) Integer.parseInt(colorAsBinary.substring(0, 8), 2);
				green = (byte) Integer.parseInt(colorAsBinary.substring(8, 16), 2);
				blue = (byte) Integer.parseInt(colorAsBinary.substring(16, 24), 2);
				alpha = (byte) Integer.parseInt(colorAsBinary.substring(24, 32), 2);
				
			}
		}else{
			String colorAsBinary = "11111111000000001111111111111111";
			
			red = (byte) Integer.parseInt(colorAsBinary.substring(0, 8), 2);
			green = (byte) Integer.parseInt(colorAsBinary.substring(8, 16), 2);
			blue = (byte) Integer.parseInt(colorAsBinary.substring(16, 24), 2);
			alpha = (byte) Integer.parseInt(colorAsBinary.substring(24, 32), 2);
		}
	}
	
	
	
	
	public Color(java.awt.Color awtColor) {
		this(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue(), awtColor.getAlpha());
	}
	
	
	
	
	public Color(float rgb) {
		this(rgb, rgb, rgb);
	}
	
	public Color(float rgb, float a) {
		this(rgb, rgb, rgb, a);
	}
	
	public Color(float r, float g, float b) { // Values between 0 - 1
		red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		alpha = (byte) 255;
	}
	
	
	public Color(float r, float g, float b, float a) { // Values between 0 - 1
		red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		alpha = (byte) (Math.max(Math.min(a, 1), 0) * 255);
	}
	
	
	

	
	public Color(double rgb) {
		this(rgb, rgb, rgb);
	}
	
	public Color(double rgb, double a) {
		this(rgb, rgb, rgb, a);
	}
	
	public Color(double r, double g, double b) { // Values between 0 - 1
		red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		alpha = (byte) 255;
	}
	
	
	public Color(double r, double g, double b, double a) { // Values between 0 - 1
		red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		alpha = (byte) (Math.max(Math.min(a, 1), 0) * 255);
	}
	
	
	
	
	
	
	public Color(int rgb) { // Graustufen
		this(rgb, rgb, rgb);
	}
	
	
	public Color(int rgb, int a) {
		this(rgb, rgb, rgb, a);
	}
	
	
	public Color(int r, int g, int b) { // Values between 0 - 255
		red = (byte) (Math.max(Math.min(r, 255), 0));
		green = (byte) (Math.max(Math.min(g, 255), 0));
		blue = (byte) (Math.max(Math.min(b, 255), 0));
		alpha = (byte) 255;
	}
	
	
	public Color(int r, int g, int b, int a) { // Values between 0 - 255
		red = (byte) (Math.max(Math.min(r, 255), 0));
		green = (byte) (Math.max(Math.min(g, 255), 0));
		blue = (byte) (Math.max(Math.min(b, 255), 0));
		alpha = (byte) (Math.max(Math.min(a, 255), 0));
	}
	
	
	
	
	public float r() {
		return Byte.toUnsignedInt(red) / 255f;
	}
	
	public int ri() {
		return Byte.toUnsignedInt(red);
	}
	
	public float g() {
		return Byte.toUnsignedInt(green) / 255f;
	}
	
	public int gi() {
		return Byte.toUnsignedInt(green);
	}
	
	public float b() {
		return Byte.toUnsignedInt(blue) / 255f;
	}
	
	public int bi() {
		return Byte.toUnsignedInt(blue);
	}
	
	public float a() {
		return Byte.toUnsignedInt(alpha) / 255f;
	}
	
	public int ai() {
		return Byte.toUnsignedInt(alpha);
	}
	
	
	
	
	public String toHex() {
		return ("#"+Integer.toHexString(ri())+Integer.toHexString(gi())+Integer.toHexString(bi())+Integer.toHexString(ai())).toUpperCase();
	}
	
	public int toInt() {
		return ColorUtils.argbToInt(alpha, red, green, blue);
	}
	
	
	
	
	public Color asGray() {
		if (ai() == 0) {
			return Color.NO_COLOR;
		}
		return new Color((ri() + gi() + bi()) / 3, ai());
	}
	
	
	
	
	public Color limitColor(int limit) {
		if(ai() == 0){
			return NO_COLOR;
		}
		float avg = (ri() + gi() + bi()) / 3;
		if(avg > limit){
			return NO_COLOR;
		}else{
			return BLACK;
		}
	}
	
	
	
	
	
	
	public Color modify(UnaryOperator<Color> function) {
		return function.apply(this);
	}
	
	UnaryOperator<Color> TEST = (Color c) -> {return new Color(c.r()*0.8, c.r()*0.4, c.r()*0.7);};
	
	
	
	public boolean isTransparent() {
		return (ai() == 0);
	}
	
	
	public static boolean isTransparent(Color c) {
		return (c.ai() == 0);
	}
	
	
	
	
	
	
	
	
	// Temp Color Management
	
	private static Color tempColor = Color.BLACK;
	
	
	
	
	
	public static Color getColor(String RGBAhexValue) { // Values between 0 - F
		if(RGBAhexValue.charAt(0) == '#') {
			RGBAhexValue = RGBAhexValue.substring(1);
			if(RGBAhexValue.length() == 6) {
				String colorAsBinary = ColorUtils.hexToBin(RGBAhexValue);
				colorAsBinary += "11111111";
				
				tempColor.red = (byte) Integer.parseInt(colorAsBinary.substring(0, 8), 2);
				tempColor.green = (byte) Integer.parseInt(colorAsBinary.substring(8, 16), 2);
				tempColor.blue = (byte) Integer.parseInt(colorAsBinary.substring(16, 24), 2);
				tempColor.alpha = (byte) Integer.parseInt(colorAsBinary.substring(24, 32), 2);
				
			}else if(RGBAhexValue.length() == 8) {
				String colorAsBinary = ColorUtils.hexToBin(RGBAhexValue);
				
				tempColor.red = (byte) Integer.parseInt(colorAsBinary.substring(0, 8), 2);
				tempColor.green = (byte) Integer.parseInt(colorAsBinary.substring(8, 16), 2);
				tempColor.blue = (byte) Integer.parseInt(colorAsBinary.substring(16, 24), 2);
				tempColor.alpha = (byte) Integer.parseInt(colorAsBinary.substring(24, 32), 2);
				
			}
		}else{
			String colorAsBinary = "11111111000000001111111111111111";
			
			tempColor.red = (byte) Integer.parseInt(colorAsBinary.substring(0, 8), 2);
			tempColor.green = (byte) Integer.parseInt(colorAsBinary.substring(8, 16), 2);
			tempColor.blue = (byte) Integer.parseInt(colorAsBinary.substring(16, 24), 2);
			tempColor.alpha = (byte) Integer.parseInt(colorAsBinary.substring(24, 32), 2);
		}
		
		return tempColor;
	}
	
	
	
	
	
	public static Color getColor(java.awt.Color awtColor) {
		return getColor(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue(), awtColor.getAlpha());
	}
	
	
	
	
	
	public static Color getColor(float rgb) {
		return getColor(rgb, rgb, rgb);
	}
	
	
	
	public static Color getColor(float rgb, float a) {
		return getColor(rgb, rgb, rgb, a);
	}
	
	
	
	public static Color getColor(float r, float g, float b) { // Values between 0 - 1
		tempColor.red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		tempColor.green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		tempColor.blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		tempColor.alpha = (byte) 255;

		return tempColor;
	}
	
	
	
	
	public static Color getColor(float r, float g, float b, float a) { // Values between 0 - 1
		tempColor.red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		tempColor.green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		tempColor.blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		tempColor.alpha = (byte) (Math.max(Math.min(a, 1), 0) * 255);

		return tempColor;
	}
	
	
	

	
	public static Color getColor(double rgb) {
		return getColor(rgb, rgb, rgb);
	}
	
	
	
	public static Color getColor(double rgb, double a) {
		return getColor(rgb, rgb, rgb, a);
	}
	
	
	
	public static Color getColor(double r, double g, double b) { // Values between 0 - 1
		tempColor.red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		tempColor.green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		tempColor.blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		tempColor.alpha = (byte) 255;

		return tempColor;
	}
	
	
	
	
	public static Color getColor(double r, double g, double b, double a) { // Values between 0 - 1
		tempColor.red = (byte) (Math.max(Math.min(r, 1), 0) * 255);
		tempColor.green = (byte) (Math.max(Math.min(g, 1), 0) * 255);
		tempColor.blue = (byte) (Math.max(Math.min(b, 1), 0) * 255);
		tempColor.alpha = (byte) (Math.max(Math.min(a, 1), 0) * 255);
		
		return tempColor;
	}
	
	
	
	
	
	
	
	
	public static Color getColor(int rgb) {
		return getColor(rgb, rgb, rgb);
	}
	
	
	
	
	public static Color getColor(int rgb, int a) {
		return getColor(rgb, rgb, rgb, a);
	}
	
	
	
	
	public static Color getColor(int r, int g, int b) { // Values between 0 - 255
		tempColor.red = (byte) (Math.max(Math.min(r, 255), 0));
		tempColor.green = (byte) (Math.max(Math.min(g, 255), 0));
		tempColor.blue = (byte) (Math.max(Math.min(b, 255), 0));
		tempColor.alpha = (byte) 255;

		return tempColor;
	}
	
	
	
	
	public static Color getColor(int r, int g, int b, int a) { // Values between 0 - 255
		tempColor.red = (byte) (Math.max(Math.min(r, 255), 0));
		tempColor.green = (byte) (Math.max(Math.min(g, 255), 0));
		tempColor.blue = (byte) (Math.max(Math.min(b, 255), 0));
		tempColor.alpha = (byte) (Math.max(Math.min(a, 255), 0));

		return tempColor;
	}
	
	
	
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Color) && !(obj instanceof String)) {
			return false;
		}
		
		if(obj instanceof Color) {
			return toString().equals(((Color) obj).toString());
		}
		
		if(obj instanceof String) {
			return toString().equals(new Color((String) obj).toString());
		}
		
		return false;
	}
	
	
	
	
	
	@Override
	public String toString() {
		
		return "Color[R="+ri()+",G="+gi()+",B="+bi()+",HEX="+toHex()+"]";
	}
	
	
	
	
	
	
	@Override
	public int hashCode() {
		return toInt();
	}
	
	
	
	
	@Override
	public Color clone() {
		return new Color(this.toHex());
	}
}
