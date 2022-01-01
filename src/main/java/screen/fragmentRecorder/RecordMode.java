package screen.fragmentRecorder;

public interface RecordMode {
	
	
	public static final int ONLY_DRAW_ON_BUFFER = 		0B10000000_00000000_00000000_00000000; //erstes Bit zählt
	public static final int DRAW_ON_BUFFER_AND_SCREEN = 0B00000000_00000000_00000000_00000000; //erstes Bit zählt
	
	
	public static final int _ROFL = 					0B01000000_00000000_00000000_00000000; //zweites Bit zählt
	
	
	
	
	public static final int DEBUG_MODE_ON =				0B00000000_00000000_00000000_00000001; //32. Bit zählt
	public static final int DEBUG_MODE_OFF = 			0B00000000_00000000_00000000_00000000; //32. Bit zählt
	
	
	
	// Indecies
	
	public static final byte DRAW_MODE = 0;
	

	public static final byte DEBUG_MODE = 31;
}
