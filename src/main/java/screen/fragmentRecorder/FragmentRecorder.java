package screen.fragmentRecorder;

import graphics.colors.Color;
import graphics.img.Img;
import graphics.img.ImgRaster;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class FragmentRecorder {
	
	
	
	private String id = "";
	
	
	private boolean record;
	private boolean[] recordModeSettings = new boolean[32];
	
	private int minX = Integer.MAX_VALUE;
	private int minY = Integer.MAX_VALUE;
	private int maxX = Integer.MIN_VALUE;
	private int maxY = Integer.MIN_VALUE;
	
	
	
	private Map<Pos, Color> map = new ConcurrentHashMap<Pos, Color>();
	//private LinkedList<Pixel> pixelBuffer = new LinkedList<Pixel>();
	
	
	
	public void record(final int x, final int y, final Color value) {
		map.put(new Pos(x, y), value);
		
		
		if(x < minX) {
			minX = x;
		}else if(x > maxX) {
			maxX = x;
		}
		
		if(y < minY) {
			minY = y;
		}else if(y > maxY) {
			maxY = y;
		}
	}

	
	
	
	
	public void begin(String id, int recordMode) {
		reset();
		
		this.id = id;
		setRecordMode(recordMode);
		record = true;
	}
	
	
	
	public void begin(String id) {
		reset();
		
		this.id = id;
		record = true;
	}
	
	
	
	
	public void end() {
		record = false;
	}
	
	
	
	
	
	public boolean isRecording() {
		return record;
	}
	
	
	
	
	public boolean getRecordModeState(int recordModeIndex) {
		if(recordModeIndex < 0 || recordModeIndex > 31) {
			return false;
		}
		
		return recordModeSettings[recordModeIndex];
	}
	
	
	
	
	public boolean[] getRecordModeStates() {
		return recordModeSettings;
	}
	
	
	
	
	
	public void setRecordMode(int recordMode) {
		String binaryString = Integer.toBinaryString(recordMode);
		
		for(int i = binaryString.length()-1; i >= 0; i--) {
			if(binaryString.charAt(31-i) == '1') {
				recordModeSettings[31-i] = true;
			}else {
				recordModeSettings[31-i] = false;
			}
		}
	}
	
	
	
	
	public void resetRecordMode() {
		recordModeSettings = new boolean[32];
	}

	
	public void reset() {
		resetRecordMode();
		
		map.clear();
		
		minX = Integer.MAX_VALUE;
		minY = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		maxY = Integer.MIN_VALUE;
	}
	
	
	
	
	
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String id() {
		return id;
	}
	
	
	
	
	
	
	
	public Img asImg() {
		if(maxX == Integer.MIN_VALUE || minX == Integer.MAX_VALUE) {
			return Img.EMPTY_IMG;
		}
		
		Color[][] imgData = new Color[maxX-minX+1][maxY-minY+1];
		
		
		if(getRecordModeState(RecordMode.DEBUG_MODE)) { // DEBUG
			System.out.println("-------------------------------------"+ id +"--------------------------------------");
			System.out.println("   Min  |  Max  |  Size");
			System.out.println("X | "+minX+" | "+maxX+" | "+imgData.length);
			System.out.println("Y | "+minY+" | "+maxY+" | "+imgData[0].length);
			System.out.println();
		}
		
		
		
		if(getRecordModeState(RecordMode.DEBUG_MODE)) { // DEBUG
			System.out.println("-------------------------------------"+ id +"--------------------------------------");
		}
		
		
		map.forEach((pos, c) -> imgData[pos.x-minX][pos.y-minY] = c);
		
		
		//new HashMap<Pos, Color>(map).forEach((pos, c) -> /*imgData[pos.x-minX][pos.y-minY] = c.clone()*/System.out.println(9));
		
		//for(Entry<Pos, Color> entry: map.entrySet()) {
		//	imgData[entry.getKey().x-minX][entry.getKey().y-minY] = entry.getValue();
		//}
		
		/*
		for(int i = 0; i < imgData.length; i++) {
			for(int j = 0; j < imgData[0].length; j++) {
				System.out.println(buffer[minX+i][minY+j]);
				if(buffer[minX+i][minY+j] == null) {
					imgData[i][j] = Color.NO_COLOR;
				}else {
					imgData[i][j] = buffer[minX+i][minY+j];
				}
				
			}
		}
		*/
		
		return new Img(new ImgRaster(imgData));
	}
	
	
	
	
	
	
	private final class Pos{
		final int x;
		final int y;
		
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		
		@Override
		public boolean equals(Object obj) {
			return toString().equals(obj.toString());
		}
		
		
		@Override
		public String toString() {
			return "Pos[x="+x+",y="+y+"]";
		}
		
		
		@Override
		public int hashCode() {
			return x << 16 + y;
		}
	}


	
}