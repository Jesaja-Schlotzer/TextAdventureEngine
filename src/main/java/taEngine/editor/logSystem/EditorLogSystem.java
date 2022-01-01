package taEngine.editor.logSystem;

import events.EventTimerPool;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import gui.Drawable;
import input.MouseHandler;
import taEngine.TAColors;
import taEngine.editor.Editor;
import textHandler.TextHandler;

import java.util.ArrayList;


public class EditorLogSystem {
	

	private static EditorLogSystem instance;
	
	
	
	private EditorLogSystem() {
		addWarning("dein spiel hat noch kein ende!");
		addWarning("gruppe 5 existiert nicht.");
		addWarning(".game datei wurde erfolgreich erstellt", Editor.DECISION_WAY_COLORS[3]);
		addWarning("entscheidungsweg 3 von gruppe 4 ist nicht gesetzt");
	}
	
	
	public static EditorLogSystem getInstance () {
		if(instance == null) {
			instance = new EditorLogSystem();
		}
		return EditorLogSystem.instance;
	}
	
	
	
	
	private boolean logIsOpen;
	private int logWidth = 600;
	
	
	private ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();
	private ArrayList<LogEntry> justOccuredLogEntries = new ArrayList<LogEntry>();
	
	private Img logGraphic;
	private Img justOccuredLogGraphic;
	
	
	
	
	
	public void draw() {
		
		// Komplette Liste
		if(logIsOpen) {
			if(logGraphic.getHeight() > 790/* = 1080 - guiHeight*/) {
				Painter.drawImage(logGraphic.subImage(0, logGraphic.getNativeHeight()-(790/GraphicsConstants.PIXEL_SIZE_Y), logGraphic.getNativeWidth(), 790), 0, 290); // TODO mit Scrollbar ausstatten
			}
			
			Painter.drawImage(logGraphic, 0, 1080 - logGraphic.getHeight());

			
			Painter.drawRect(logWidth/2-30, 1080 - logGraphic.getHeight() - 15, 60, 15, TAColors.DARK_GRAY);
			
		}else {
			//Nur neuster Eintrag
			if(!justOccuredLogEntries.isEmpty()) {
				Painter.drawImage(justOccuredLogGraphic, 0, 1080 - justOccuredLogGraphic.getHeight());
				Painter.drawRect(logWidth/2-30, 1080 - justOccuredLogGraphic.getHeight() - 15, 60, 15, TAColors.DARK_GRAY);
			}else {
				Painter.drawRect(logWidth/2-30, 1080 - 15, 60, 15, TAColors.DARK_GRAY);
			}
		}
	}
	
	
	
	
	
	public void handleMouseClick() {
		if(!logIsOpen) {
			if(MouseHandler.overButton(0, GraphicsConstants.REF_SCREEN_SIZE_Y-15, logWidth, GraphicsConstants.REF_SCREEN_SIZE_Y)) {
				logIsOpen = true;				
			}
		}else {
			if(!MouseHandler.overButton(0, GraphicsConstants.REF_SCREEN_SIZE_Y-logGraphic.getHeight(), logWidth, GraphicsConstants.REF_SCREEN_SIZE_Y)) {
				logIsOpen = false;
			}
		}
	}
	
	
	
	
	
	public void handleMouseWheel(float wheelRotation) {
		
	}
	
	
	
	
	
	
	
	private Img generateLogGraphic(ArrayList<LogEntry> logEntries) {
		return new Drawable(){

			@Override
			public void draw(int x, int y) {
				// Rand oben
				Painter.drawRect(x, y, logWidth-2*GraphicsConstants.PIXEL_SIZE_X, GraphicsConstants.PIXEL_SIZE_Y, TAColors.DARK_GRAY);
				Painter.drawPoint(x, y+GraphicsConstants.PIXEL_SIZE_Y, TAColors.DARK_GRAY);
				Painter.drawPoint(x+logWidth-2*GraphicsConstants.PIXEL_SIZE_X, y+GraphicsConstants.PIXEL_SIZE_Y, TAColors.DARK_GRAY);
				Painter.drawRect(x+GraphicsConstants.PIXEL_SIZE_X, y+GraphicsConstants.PIXEL_SIZE_Y, logWidth-3*GraphicsConstants.PIXEL_SIZE_X, GraphicsConstants.PIXEL_SIZE_Y, TAColors.WHITE);
				
				// Zwischenteil
				int yoff = 2*GraphicsConstants.PIXEL_SIZE_Y;
				
				for(LogEntry logEntry : logEntries) {
					String[] logEntryLines = TextHandler.font().getLines(logEntry.getLogText(), logWidth-6*GraphicsConstants.PIXEL_SIZE_X, true);
					
					Painter.drawRect(x, y+yoff, GraphicsConstants.PIXEL_SIZE_X, (TextHandler.font().getTextHeight(logEntry.getLogText(), logWidth-6*GraphicsConstants.PIXEL_SIZE_X, true) + 5)*GraphicsConstants.PIXEL_SIZE_Y, TAColors.DARK_GRAY);
					Painter.drawRect(x+logWidth-GraphicsConstants.PIXEL_SIZE_X, y+yoff, GraphicsConstants.PIXEL_SIZE_X, (TextHandler.font().getTextHeight(logEntry.getLogText(), logWidth-6*GraphicsConstants.PIXEL_SIZE_X, true) + 5)*GraphicsConstants.PIXEL_SIZE_Y, TAColors.DARK_GRAY);
					
					Painter.drawRect(x+GraphicsConstants.PIXEL_SIZE_X, y+yoff, logWidth-2*GraphicsConstants.PIXEL_SIZE_X, (TextHandler.font().getTextHeight(logEntry.getLogText(), logWidth-6*GraphicsConstants.PIXEL_SIZE_X, true) + 5)*GraphicsConstants.PIXEL_SIZE_Y, TAColors.WHITE);
					
					TextHandler.drawLines(logEntryLines, x+3*GraphicsConstants.PIXEL_SIZE_X, y+yoff+2*GraphicsConstants.PIXEL_SIZE_Y, logEntry.getLogTextColor());
					
					yoff += (TextHandler.font().getTextHeight(logEntry.getLogText(), logWidth-6*GraphicsConstants.PIXEL_SIZE_X, true) + 5)*GraphicsConstants.PIXEL_SIZE_Y;
					
					Painter.drawRect(x+2*GraphicsConstants.PIXEL_SIZE_X, y+yoff-GraphicsConstants.PIXEL_SIZE_Y, logWidth-4*GraphicsConstants.PIXEL_SIZE_X, GraphicsConstants.PIXEL_SIZE_Y, TAColors.LIGHT_GRAY);
				}
				
				
				// Rand unten
				Painter.drawRect(x, y+yoff-GraphicsConstants.PIXEL_SIZE_Y, logWidth, GraphicsConstants.PIXEL_SIZE_Y, TAColors.DARK_GRAY);
			}
			
		}.asImg();
	}
	
	
	
	
	
	public void addWarning(String warningLogText) {
		LogEntry newLogEntry = new LogEntry(warningLogText, TAColors.RED);
		logEntries.add(newLogEntry);
		justOccuredLogEntries.add(newLogEntry);
		
		logGraphic = generateLogGraphic(logEntries);
		justOccuredLogGraphic = generateLogGraphic(justOccuredLogEntries);
		
		EventTimerPool.getInstance().addTimer(3500, () -> {justOccuredLogEntries.remove(newLogEntry);});
	}
	
	
	
	
	public void addWarning(String warningLogText, Color warningLogColor) {
		LogEntry newLogEntry = new LogEntry(warningLogText, warningLogColor);
		logEntries.add(newLogEntry);
		justOccuredLogEntries.add(newLogEntry);
		
		logGraphic = generateLogGraphic(logEntries);
		justOccuredLogGraphic = generateLogGraphic(justOccuredLogEntries);
		
		EventTimerPool.getInstance().addTimer(3500, () -> {justOccuredLogEntries.remove(newLogEntry);});
	}
	
}
