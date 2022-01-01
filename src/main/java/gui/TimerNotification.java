package gui;

import events.Event;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import util.Timer;
import textHandler.TextHandler;


public class TimerNotification {
	private int w, h;
	private String text;
	private Color textColor, backgroundColor, frameColor;
	private Timer timer;
	private Event event;

	private boolean isRunning;

	
	
	
	public TimerNotification(TimerNotification tn) {
		this.w = tn.w;
		this.h = tn.h;
		this.text = tn.text;
		setColors(tn.textColor, tn.backgroundColor, tn.frameColor);
		this.timer = new Timer(tn.timer);
		this.event = tn.event;
	}
	
	
	
	public TimerNotification(String text, Timer timer, Event event) {
		this.text = text;
		this.timer = timer;
		this.event = event;

		this.w = TextHandler.font().getTextWidth(text) * GraphicsConstants.PIXEL_SIZE_X + (6 * GraphicsConstants.PIXEL_SIZE_X);
		this.h = TextHandler.font().getTextHeight(text) * GraphicsConstants.PIXEL_SIZE_Y + (6 * GraphicsConstants.PIXEL_SIZE_Y);

		setColors(Color.BLACK, Color.YELLOW, Color.RED);
	}

	
	
	
	public TimerNotification(String text, Timer timer) {
		this(text, timer, Event.NULL);
	}

	
	
	
	
	public TimerNotification(String text, int timeInMilliseconds, Event event) {
		this(text, new Timer(timeInMilliseconds), event);
	}

	
	
	
	
	public TimerNotification(String text, int timeInMilliseconds) {
		this(text, timeInMilliseconds, Event.NULL);
	}
	
	
	
	

	public void setColors(Color textColor, Color backgroundColor, Color frameColor) {
		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;
	}

	
	
	
	
	public void start() {
		isRunning = true;
		timer.startTimer();
	}

	
	public void pause() {
		isRunning = false;
	}
	
	
	public void stop() {
		isRunning = false;
	}

	
	
	
	public void draw() {
		if (isRunning) {
			int x = ((Gui.REF_SCREEN_SIZE_X / 2) / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X) - (w / 2);
			int y = ((Gui.REF_SCREEN_SIZE_Y / 2) / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y) - (h / 2);
			Painter.drawRect(x - GraphicsConstants.PIXEL_SIZE_X, y - GraphicsConstants.PIXEL_SIZE_Y, w + (2 * GraphicsConstants.PIXEL_SIZE_X), h + (2 * GraphicsConstants.PIXEL_SIZE_Y), Color.NO_COLOR, Color.GLOBAL_BACKGROUND_COLOR);
			Painter.drawRect(x, y, w, h, backgroundColor, frameColor);
			
			TextHandler.textAlign(TextHandler.LEFT, TextHandler.TOP);
			TextHandler.drawString(text, x + (3 * GraphicsConstants.PIXEL_SIZE_X), y + (3 * GraphicsConstants.PIXEL_SIZE_Y), textColor);

			if (timer.proceedTimer()) {
				stop();
				event.start();
			}
		}
	}


}
