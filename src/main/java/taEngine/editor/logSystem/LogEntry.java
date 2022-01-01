package taEngine.editor.logSystem;

import graphics.colors.Color;

public class LogEntry {
	
	// Effect
	/*
	 * z.B. das des FollowingGroup TextField kurz rot umrandet wird 
	 * und eben der Log sich soweit hochfährt das man den text sehen kann
	 */
	
	
	// Log Configuration
	private String logText;
	private Color logTextColor;
	
	
	
	LogEntry(String logText, Color logTextColor){
		this.logText = logText;
		this.logTextColor = logTextColor;
	}
	
	
	
	
	String getLogText() {
		return logText;
	}
	
	
	Color getLogTextColor() {
		return logTextColor;
	}
}
