package gui;

import debugging.Debugger;
import events.Event;
import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.colors.Palette;
import graphics.img.Img;
import textHandler.StyledString;
import textHandler.TextHandler;

import java.util.Arrays;


public class ButtonNotification {
	private int w, h;
	private String text;
	private EventButton[] buttons;
	
	private Palette palette = Palette.STANDARD_PALETTE;
	private Layout<ButtonNotification> layout = ButtonNotification.STANDARD_LAYOUT;

	boolean isOpen;

	
	
	
	public ButtonNotification(ButtonNotification bn) {
		this.w = bn.w;
		this.h = bn.h;
		setPalette(bn.palette);
		setLayout(bn.layout);
		this.text = bn.text;
		this.buttons = new EventButton[bn.getEventButtons().length];
		for(int i = 0; i < buttons.length; i++) {
			this.buttons[i] = new EventButton(bn.getEventButtons()[i]);
		}
	}
	
	
	
	public ButtonNotification(String text, String[] eventTexts, Event[] events) {
		this(text, GCC.toGCCArray(eventTexts), events);
	}
	
	
	public ButtonNotification(String text, StyledString[] eventTexts, Event[] events) {
		this(text, GCC.toGCCArray(eventTexts), events);
	}
	
	
	public ButtonNotification(String text, Img[] eventImgs, Event[] events) {
		this(text, GCC.toGCCArray(eventImgs), events);
	}
	
	
	public ButtonNotification(String text, Animation[] eventAnims, Event[] events) {
		this(text, GCC.toGCCArray(eventAnims), events);
	}
	
	
	public ButtonNotification(String text, Drawable[] eventDrawables, Event[] events) {
		this(text, GCC.toGCCArray(eventDrawables), events);
	}
	
	
	
	public ButtonNotification(String text, GCC[] eventTexts, Event[] events) {
		this.text = text;

		if (eventTexts.length != events.length) {
			Debugger.addLogEntry("FEHLER - Gui/ButtonNotification/ButtonNotification() - Falsche Parameter: eventTexts.length stimmt nicht mit events.length überein");
			
			if(eventTexts.length > events.length) {
				Event[] temp = new Event[eventTexts.length];
				
				for(int i = 0; i < temp.length; i++) {
					if(i < events.length) {
						temp[i] = events[i];
					}else {
						temp[i] = Event.NULL;
					}
				}
				
				events = temp;
			}
		}
		
		
		EventButton[] buttons = new EventButton[eventTexts.length];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new EventButton(0, 0, eventTexts[i], events[i]);
		}

		this.buttons = buttons;
		
		int buttonsWidth = 0;
		for (int i = 0; i < buttons.length; i++) {
			buttonsWidth += buttons[i].getWidth() + (3 * GraphicsConstants.PIXEL_SIZE_X);
		}

		this.w = Math.max((TextHandler.font().getTextWidth(text) * GraphicsConstants.PIXEL_SIZE_X + (6 * GraphicsConstants.PIXEL_SIZE_X)), buttonsWidth + (4 * GraphicsConstants.PIXEL_SIZE_X));

		int buttonsHeight = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getHeight() > buttonsHeight) {
				buttonsHeight = buttons[i].getHeight();
			}
		}

		this.h = TextHandler.font().getTextHeight(text) * GraphicsConstants.PIXEL_SIZE_Y + (12 * GraphicsConstants.PIXEL_SIZE_Y) + buttonsHeight;
		
		int x = (((Gui.REF_SCREEN_SIZE_X / 2) / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X) - (w / 2) + (2 * GraphicsConstants.PIXEL_SIZE_X)) / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		int y = (((Gui.REF_SCREEN_SIZE_Y / 2) / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y) - (h / 2) + (2 * GraphicsConstants.PIXEL_SIZE_Y) + TextHandler.font().getTextHeight(text) * GraphicsConstants.PIXEL_SIZE_Y + (6 * GraphicsConstants.PIXEL_SIZE_Y)) / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;

		int xoff = (w - buttonsWidth - (2 * GraphicsConstants.PIXEL_SIZE_X)) / 2 / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;

		buttons[0].setPos(x + xoff, y);
		for (int i = 1; i < buttons.length; i++) {
			buttons[i].setPos(buttons[i - 1].getPosX() + buttons[i - 1].getWidth() + (4 * GraphicsConstants.PIXEL_SIZE_X), y);
		}
	}
	
	
	
	
	
	public ButtonNotification(String text, Object... gccsAndEvents) {
		this(text,
			 Arrays.stream(gccsAndEvents).filter(GCC.VALIDATION_FILTER).map(GCC.RETURN_VALID_OBJECT).toArray(GCC[]::new),
			 Arrays.stream(gccsAndEvents).filter((Object obj) -> obj instanceof Event).toArray(Event[]::new)
		);
	}
	
	

	public ButtonNotification(String text, EventButton... buttons) {
		this.text = text;
		this.buttons = buttons;

		int buttonsWidth = 0;
		for (int i = 0; i < buttons.length; i++) {
			buttonsWidth += buttons[i].getWidth() + (3 * GraphicsConstants.PIXEL_SIZE_X);
		}

		this.w = Math.max((TextHandler.font().getTextWidth(text) * GraphicsConstants.PIXEL_SIZE_X + (6 * GraphicsConstants.PIXEL_SIZE_X)),
				buttonsWidth + (4 * GraphicsConstants.PIXEL_SIZE_X));

		int buttonsHeight = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getHeight() > buttonsHeight) {
				buttonsHeight = buttons[i].getHeight();
			}
		}

		this.h = TextHandler.font().getTextHeight(text) * GraphicsConstants.PIXEL_SIZE_Y + (12 * GraphicsConstants.PIXEL_SIZE_Y) + buttonsHeight;

		int x = (((Gui.REF_SCREEN_SIZE_X / 2) / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X) - (w / 2) + (2 * GraphicsConstants.PIXEL_SIZE_X))
				/ GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		int y = (((Gui.REF_SCREEN_SIZE_Y / 2) / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y) - (h / 2) + (2 * GraphicsConstants.PIXEL_SIZE_Y)
				+ TextHandler.font().getTextHeight(text) * GraphicsConstants.PIXEL_SIZE_Y + (6 * GraphicsConstants.PIXEL_SIZE_Y)) / GraphicsConstants.PIXEL_SIZE_Y
				* GraphicsConstants.PIXEL_SIZE_Y;

		int xoff = (w - buttonsWidth - (2 * GraphicsConstants.PIXEL_SIZE_X)) / 2 / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;

		buttons[0].setPos(x + xoff, y);
		for (int i = 1; i < buttons.length; i++) {
			buttons[i].setPos(x + xoff + buttons[i - 1].getWidth() + (4 * GraphicsConstants.PIXEL_SIZE_X), y);
		}
	}

	
	
	
	
	
	public ButtonNotification setPalette(Palette palette) {
		if(palette != null) {
			this.palette = palette;
		}
		
		for(EventButton eb : buttons) {
			eb.setPalette(this.palette);
		}

		return this;
	}
	
	
	
	
	
	public ButtonNotification setLayout(Layout<ButtonNotification> layout) {
		if(layout != null) {
			this.layout = layout;
		}
		
		return this;
	}
	


	
	
	
	
	
	public EventButton[] getEventButtons() {
		return buttons;
	}
	
	
	
	
	public void open() {
		isOpen = true;
	}

	
	
	public void close() {
		isOpen = false;
	}
	
	
	
	public boolean isOpen() {
		return isOpen;
	}
	
	
	
	

	public void draw() {
		if (isOpen) {
			layout.drawIdle(this);
		}
	}
	
	
	
	

	public void handleMouseClick() {
		if (isOpen) {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i].handleMouseClick()) {
					close();
				}
			}
		}
	}
	
	
	
	
	
	public static final Layout<ButtonNotification> STANDARD_LAYOUT = new LayoutAdapter<ButtonNotification>() {
		
		public void drawIdle(ButtonNotification bn) {
			int x = ((Gui.REF_SCREEN_SIZE_X / 2) / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X)	- (bn.w / 2);
			int y = ((Gui.REF_SCREEN_SIZE_Y / 2) / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y)	- (bn.h / 2);
			Painter.drawRect(x - GraphicsConstants.PIXEL_SIZE_X, y - GraphicsConstants.PIXEL_SIZE_Y, bn.w + (2 * GraphicsConstants.PIXEL_SIZE_X), bn.h + (2 * GraphicsConstants.PIXEL_SIZE_Y), Color.NO_COLOR, bn.palette.getColor(1));
			Painter.drawRect(x, y, bn.w, bn.h, bn.palette.getColor(1), bn.palette.getColor(2));

			TextHandler.textAlign(TextHandler.LEFT);
			TextHandler.drawString(bn.text, x + (3 * GraphicsConstants.PIXEL_SIZE_X), y + (3 * GraphicsConstants.PIXEL_SIZE_Y), bn.palette.getColor(3));

			for (int i = 0; i < bn.buttons.length; i++) {
				bn.buttons[i].draw();
			}
		}
	};
}
