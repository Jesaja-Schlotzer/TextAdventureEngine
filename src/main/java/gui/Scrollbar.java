package gui;

import debugging.Debugger;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import input.MouseHandler;

public class Scrollbar {
	
	// Constants
	
	public static final int ADDITIONAL_INTERACTION_SPACE = 1; // 1 == 1*PIXEL_SIZE_Y 
		
	// End
		
		
		
	private int x, y, type, barLength, barWidth, pusherLength, scrollStrenght = 2;
	private double pusherPos; // 0.0 - 1.0
	private Color barColor, sliderColor, hoverSliderColor;
	private boolean dragging, isLocked;
	

	
	
	
	public Scrollbar(Scrollbar sb) {
		setPos(sb.x, sb.y);
		this.type = sb.type;
		setBarLength(sb.barLength);
		setBarWidth(sb.barWidth);
		setPusherLength(sb.pusherLength);
		setColor(sb.barColor, sb.sliderColor, sb.hoverSliderColor);
	}
	
	
	
	public Scrollbar(int x, int y, int type, int barLength) {
		setPos(x, y);
		setType(type);
		setBarLength(barLength);
		setBarWidth((type == Gui.HORIZONTAL ? GraphicsConstants.PIXEL_SIZE_Y : GraphicsConstants.PIXEL_SIZE_X));
		setPusherLength(4*(type == Gui.HORIZONTAL ? GraphicsConstants.PIXEL_SIZE_X : GraphicsConstants.PIXEL_SIZE_Y));

		setColor(Color.ORANGE, Color.RED, Color.RED);
	}
	

	
	
	public Scrollbar lock() {
		isLocked = true;
		
		return this;
	}
	
	public Scrollbar unlock() {
		isLocked = false;
		
		return this;
	}
	
	
	
	
	public Scrollbar setColor(Color barColor, Color sliderColor, Color hoverSliderColor) {
		this.barColor = barColor;
		this.sliderColor = sliderColor;
		this.hoverSliderColor = hoverSliderColor;
				
		return this;
	}
	
	
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	public Scrollbar setBarLength(int barLength) {
		this.barLength = Math.max(barLength, pusherLength + (type == Gui.HORIZONTAL ? GraphicsConstants.PIXEL_SIZE_X : GraphicsConstants.PIXEL_SIZE_Y));
		
		return this;
	}
	
	
	public Scrollbar setBarWidth(int barWidth) {
		this.barWidth = Math.max(barWidth, (type == Gui.HORIZONTAL ? GraphicsConstants.PIXEL_SIZE_Y : GraphicsConstants.PIXEL_SIZE_X));
		
		return this;
	}
	
	
	
	public Scrollbar setPusherLength(int pusherLength) {
		this.pusherLength = Math.min(Math.max(pusherLength, (type == Gui.HORIZONTAL ? GraphicsConstants.PIXEL_SIZE_X : GraphicsConstants.PIXEL_SIZE_Y)), barLength-(type == Gui.HORIZONTAL ? GraphicsConstants.PIXEL_SIZE_X : GraphicsConstants.PIXEL_SIZE_Y));
		
		return this;
	}
	
	
	
	public Scrollbar setScrollStrenght(int strength) {
		this.scrollStrenght = Math.max(strength, 1);
		
		return this;
	}
	
	
	
	
	public Scrollbar setPusherPos(double pos) {
		this.pusherPos = Math.min(1.0, Math.max(0.0, pos));
		
		return this;
	}
	
	
	
	
	public void setType(int type) {
		if(type == Gui.HORIZONTAL || type == Gui.VERTIKAL) {
			this.type = type;
		}else {
			this.type = Gui.VERTIKAL;
			Debugger.addLogEntry("FEHLER - gui.Scrollbar/setType(int) - Der Typ \"" + type + "\" existiert nicht.");
		}
	}
	
	
	
	
	
	public double getPusherPos() {
		return pusherPos;
	}
	
	
	
	public int getPusherLength() {
		return pusherLength;
	}
	
	
	
	
	
	public void draw() {
		switch (type) {
		case Gui.HORIZONTAL:
			if(MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength, y+barWidth+(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y))) {
				Painter.drawRect(x, y, barLength, barWidth, barColor);
				Painter.drawRect(x + (int)(pusherPos*(barLength-pusherLength)), y, pusherLength, barWidth, hoverSliderColor);
			}else {
				Painter.drawRect(x, y, barLength, barWidth, barColor);
				Painter.drawRect(x + (int)(pusherPos*(barLength-pusherLength)), y, pusherLength, barWidth, sliderColor);
			}
			break;
		case Gui.VERTIKAL:
			if(MouseHandler.overButton(x-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), y, x+barWidth+(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), y+barLength)) {
				Painter.drawRect(x, y, barWidth, barLength, barColor);
				Painter.drawRect(x, y + (int)(pusherPos*(barLength-pusherLength)), barWidth, pusherLength, hoverSliderColor);
			}else {
				Painter.drawRect(x, y, barWidth, barLength, barColor);
				Painter.drawRect(x, y + (int)(pusherPos*(barLength-pusherLength)), barWidth, pusherLength, sliderColor);
			}
			break;
		}

		if (MouseHandler.mousePressed || dragging) {
			handleMouseClick();
		}
	}

	
	
	
	
	
	public void handleMouseClick() {
		switch (type) {
		case Gui.HORIZONTAL:
			if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength, y+barWidth+(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y)) || dragging) {
				pusherPos = Math.min(Math.max((MouseHandler.mouseX - x - (pusherLength/2)) / (double) (barLength-pusherLength), 0), 1);
				dragging = true;
			}
			if (dragging && !MouseHandler.mousePressed) {
				dragging = false;
			}
			break;
		case Gui.VERTIKAL:
			if (MouseHandler.overButton(x-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), y, x+barWidth+(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), y+barLength) || dragging) {
				pusherPos = Math.min(Math.max((MouseHandler.mouseY - y - (pusherLength/2)) / (double) (barLength-pusherLength), 0), 1);
				dragging = true;
			}
			if (dragging && !MouseHandler.mousePressed) {
				dragging = false;
			}
			break;
		}
	}

	
	
	
	
	public void handleMouseWheel(float mouseWheelInput) {
		switch (type) {
		case Gui.HORIZONTAL:
			if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength, y+barWidth+(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y))) {
				if (mouseWheelInput == -1 && pusherPos < 1) {
					pusherPos = Math.min(Math.max(pusherPos + (scrollStrenght*GraphicsConstants.PIXEL_SIZE_X/ (double) (barLength-pusherLength)), 0), 1);
				}
				if (mouseWheelInput == 1 && pusherPos > 0) {
					pusherPos = Math.min(Math.max(pusherPos - (scrollStrenght*GraphicsConstants.PIXEL_SIZE_X/ (double) (barLength-pusherLength)), 0), 1);
				}
			}
			break;
		case Gui.VERTIKAL:
			if (MouseHandler.overButton(x-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), y, x+barWidth+(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), y+barLength)) {
				if (mouseWheelInput == -1 && pusherPos < 1) { // zu mir rollen / runter
					pusherPos = Math.min(Math.max(pusherPos + (scrollStrenght*GraphicsConstants.PIXEL_SIZE_Y/ (double) (barLength-pusherLength)), 0), 1);
					System.out.println((GraphicsConstants.PIXEL_SIZE_Y/(barLength-pusherLength)));
				}
				if (mouseWheelInput == 1 && pusherPos > 0) {
					pusherPos = Math.min(Math.max(pusherPos - (scrollStrenght*GraphicsConstants.PIXEL_SIZE_Y/ (double) (barLength-pusherLength)), 0), 1);
				}
			}
			break;
		}
	}
	
	
	
	public void handleMouseWheelInArea(float mouseWheelInput, int xl, int yo, int xr, int yu) {
		if(MouseHandler.overButton(xl, yo, xr, yu)) {
			switch (type) {
			case Gui.HORIZONTAL:
				if (mouseWheelInput == -1 && pusherPos < 1) {
					pusherPos = Math.min(Math.max(pusherPos + (scrollStrenght*GraphicsConstants.PIXEL_SIZE_X/ (double) (barLength-pusherLength)), 0), 1);
				}
				if (mouseWheelInput == 1 && pusherPos > 0) {
					pusherPos = Math.min(Math.max(pusherPos - (scrollStrenght*GraphicsConstants.PIXEL_SIZE_X/ (double) (barLength-pusherLength)), 0), 1);
				}
				break;
			case Gui.VERTIKAL:
				if (mouseWheelInput == -1 && pusherPos < 1) { // zu mir rollen / runter
					pusherPos = Math.min(Math.max(pusherPos + (scrollStrenght*GraphicsConstants.PIXEL_SIZE_Y/ (double) (barLength-pusherLength)), 0), 1);
				}
				if (mouseWheelInput == 1 && pusherPos > 0) {
					pusherPos = Math.min(Math.max(pusherPos - (scrollStrenght*GraphicsConstants.PIXEL_SIZE_Y/ (double) (barLength-pusherLength)), 0), 1);
				}
				break;
			}
		}
	}
}
