package graphics.colors;

import java.util.HashMap;
import java.util.Map;

public class ColorUtils {
	
	
	
	
	// Conversation
	
	private static Map<String, String> digiMap = new HashMap<>();

	static {
	    digiMap.put("0", "0000");
	    digiMap.put("1", "0001");
	    digiMap.put("2", "0010");
	    digiMap.put("3", "0011");
	    digiMap.put("4", "0100");
	    digiMap.put("5", "0101");
	    digiMap.put("6", "0110");
	    digiMap.put("7", "0111");
	    digiMap.put("8", "1000");
	    digiMap.put("9", "1001");
	    digiMap.put("A", "1010");
	    digiMap.put("B", "1011");
	    digiMap.put("C", "1100");
	    digiMap.put("D", "1101");
	    digiMap.put("E", "1110");
	    digiMap.put("F", "1111");
	}

	public static String hexToBin(String hex) {
	    char[] hexArray = hex.toUpperCase().toCharArray();
	    String binaryString = "";
	    for(char h : hexArray){
	    	if(digiMap.containsKey(h+"")){
	    		binaryString += digiMap.get(String.valueOf(h));
	    	}else{
	    		binaryString += "0000";
	    		System.out.println("Wrong char: " + h);
	    	}
	    }
	    return binaryString;
	}
	
	
	
	public static String binToHex(String bin){
		String[] section = new String[bin.length()/4];
		for(int i = 0; i < section.length; i++){
			section[i] = bin.substring(i*4, i*4+4);
		}
		
		String hex = "";
		for(int i = 0; i < section.length; i++){
			if(digiMap.containsValue(section[i])){
				for(String str : digiMap.keySet()){
					if(digiMap.get(str).equals(section[i])){
						hex += str;
					}
				}
			}
		}
		return hex;
	}
	
	
	
	public static String decToBin(int dec) {
		return Integer.toBinaryString(dec);
	}
	
	
	public static String decToBin(int dec, int minLenght) {
		String bin = decToBin(dec);
		
		while(bin.length() < minLenght) {
			bin = "0" + bin;
		}
		
		return bin;
	}
	
	
	
	// RGB to Int
	
	public static int rgbToInt(int r, int g, int b){ // Values between 0 - 255
	    r = (r << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    g = (g << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
	    b = b & 0x000000FF; //Mask out anything not blue.

	    return 0xFF000000 | r | g | b; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}
	
	
	public static int rgbToInt(float r, float g, float b){ // Values between 0 - 1
	    int R = Math.round(255 * r);
	    int G = Math.round(255 * g);
	    int B = Math.round(255 * b);

	    return rgbToInt(R, G, B);
	}
	
	
	// ARGB to Int
	
	public static int argbToInt(int a, int r, int g, int b){ // Values between 0 - 255
		a = (a << 24) & 0xFF000000; //Shift alpha 24-bits and mask out other stuff
	    r = (r << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    g = (g << 8) & 0x0000FF00; //Shift green 8-bits and mask out other stuff
	    b = b & 0x000000FF; //Mask out anything not blue.

	    return a | r | g | b;
	}
	
	
	public static int argbToInt(float a, float r, float g, float b){ // Values between 0 - 1
		int A = Math.round(255 * a);
	    int R = Math.round(255 * r);
	    int G = Math.round(255 * g);
	    int B = Math.round(255 * b);

	    return argbToInt(A, R, G, B);
	}
	
	
	
	// 32Bit Int to Color
	
	public static Color intToColor(int c) {
		if(c == 0) {
			return Color.TRANSPARENT;
		}
		return new Color("#" + ((binToHex(decToBin(c, 24)) + (binToHex(decToBin(c, 24)).substring(0, 2))).substring(2)));
	}
	
	
	// AWT.Color Conversations
	
	
	public static java.awt.Color toAwtColor(Color c) {
		return new java.awt.Color(c.r(), c.g(), c.b(), c.a());
	}
}