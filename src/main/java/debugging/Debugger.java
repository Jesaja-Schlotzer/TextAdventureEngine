package debugging;

import data.FileHandler;
import data.Settings;
import debugging.DebugShapeCreator.DebugShape;
import graphics.img.Img;

import java.util.ArrayList;


public class Debugger {
	
	// Constants 
	
	public static final int DEBUG_POINT = 0;
	public static final int DEBUG_LINE = 1;
	public static final int DEBUG_RECT = 2;
	public static final int DEBUG_CIRCLE = 3;
	public static final int DEBUG_IMAGE = 4;
	
	
	public static final int TTL_IN_MILLIS = 3500;
	
	
	// End
	
	
	private static DebugShapeCreator debugShapeCreator = new DebugShapeCreator();
	private static ArrayList<DebugShape> debugShapes = new ArrayList<>();

	private static ArrayList<String> log = new ArrayList<>();

	

	public static void addLogEntry(Object entry) {
		if ((entry+"").length() >= 2 && (entry+"").startsWith("//")) {
			log.add(entry+"");
		} else {
			boolean isUnique = true;
			for(String logEntry : log) {
				if (logEntry.equals(entry + "")) {
					isUnique = false;
					break;
				}
			}
			if(isUnique) {
				log.add(entry+"");
			}
		}
		FileHandler.saveStrings(FileHandler.PROJECT_PATH + "log.txt", log.toArray(new String[0]));
		System.out.println(entry);
	}
	
	
	
	
	
	public static void addDebugShape(int type, int x, int y, int... params) {
		switch (type) {
		case DEBUG_POINT:
			debugShapes.add(debugShapeCreator.new DebugPoint(x, y));
			break;
		case DEBUG_LINE:
			debugShapes.add(debugShapeCreator.new DebugLine(x, y, params[0], params[1]));
			break;
		case DEBUG_RECT:
			debugShapes.add(debugShapeCreator.new DebugRect(x, y, params[0], params[1]));
			break;
		case DEBUG_CIRCLE:
			debugShapes.add(debugShapeCreator.new DebugCircle(x, y, params[0]));
			break;
		}
	}
	
	
	
	public static void addDebugImage(int x, int y, Img img) {
		debugShapes.add(debugShapeCreator.new DebugImage(x, y, img));

	}
	
	
	
	public static void drawDebugging() {
		if(!Settings.debug) {
			debugShapes.clear();
		}
		
		int index = -1;
		int i = 0;
		ArrayList<DebugShape> temp = (ArrayList<DebugShape>) debugShapes.clone();
		for (DebugShape debugShape : temp) {
			debugShape.draw();
			if (debugShape.ttlTimer.proceedTimer() || !debugShape.ttlTimer.isRunning()) {
				index = i;
			}
			i++;
		}
		if (index != -1) {
			debugShapes.remove(index);
		}
		// println("DebugShapes: "+debugShapes.size());
	}
	
	
	
	
	
}
