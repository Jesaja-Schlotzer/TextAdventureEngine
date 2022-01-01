package gui;

import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import input.MouseHandler;
import textHandler.TextHandler;


public class IntSlider {

	// Constants
	
	public static final int ADDITIONAL_INTERACTION_SPACE = 1; // 1 == 1*PIXEL_SIZE_Y 
	
	// End
	
	
	
	private int x, y, barLength;
	private int value, minValue,  maxValue;
	private boolean dragging;
	private Color barColor, sliderColor, hoverSliderColor;
	
	private Label valueLabel;
	
	

	
	public IntSlider(IntSlider is) {
		setPos(is.x, is.y);
		setBarLength(is.barLength);
		
		this.minValue = is.minValue;
		this.maxValue = is.maxValue;
		
		setColors(is.barColor, is.sliderColor, is.hoverSliderColor);
		
		this.valueLabel = new Label(is.valueLabel);
	}
	
	
	
	

	public IntSlider(int x, int y, int barLength, int minValue, int maxValue) {
		setPos(x, y);
		this.barLength = Math.abs(barLength);
		
		if(minValue > maxValue) {
			this.minValue = maxValue;
			this.maxValue = minValue;
		}else {
			this.minValue = minValue;
			this.maxValue = maxValue;
		}
		
		this.value = this.minValue;
		
		setColors(Color.ORANGE, Color.RED, Color.RED);
		
		
		valueLabel = new Label(x, y - TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y - (5*GraphicsConstants.PIXEL_SIZE_Y) , this.minValue+"");
	}
	
	
	
	
	
	
	public IntSlider setColors(Color barColor, Color sliderColor, Color hoverSliderColor) {
		this.barColor = barColor;
		this.sliderColor = sliderColor;
		this.hoverSliderColor = hoverSliderColor;
		
		return this;
	}
	
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	public IntSlider setBarLength(int barLength) {
		this.barLength = Math.max(barLength, GraphicsConstants.PIXEL_SIZE_X);
		
		return this;
	}
	
	
	
	
	
	
	public int getValue() {
		return value;
	}
	
	
	public int getMinValue() {
		return minValue;
	}
	
	public int getMaxValue() {
		return maxValue;
	}

	
	
	public Label getLable() {
		return valueLabel;
	}

	
	
	
	public void draw() {
		Painter.drawRect(x, y, GraphicsConstants.PIXEL_SIZE_X, (3 * GraphicsConstants.PIXEL_SIZE_Y), barColor);
		Painter.drawRect(x + GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y, barLength + GraphicsConstants.PIXEL_SIZE_X, GraphicsConstants.PIXEL_SIZE_Y, barColor);
		Painter.drawRect(x + GraphicsConstants.PIXEL_SIZE_X + barLength + GraphicsConstants.PIXEL_SIZE_X, y, GraphicsConstants.PIXEL_SIZE_X, (3 * GraphicsConstants.PIXEL_SIZE_Y), barColor);
		
		
		if (MouseHandler.mousePressed || dragging) {
			handleMouseClick();
		}
		
		int xOff = (int) ((value-minValue) / (float)(maxValue-minValue) * barLength);

		
		if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength + (3 * GraphicsConstants.PIXEL_SIZE_X), y + ((3 + ADDITIONAL_INTERACTION_SPACE) * GraphicsConstants.PIXEL_SIZE_Y)) || dragging) {
			valueLabel.draw();
			
			
			Painter.drawRect(x + xOff, y, (3 * GraphicsConstants.PIXEL_SIZE_X), (3 * GraphicsConstants.PIXEL_SIZE_Y), Color.TRANSPARENT, hoverSliderColor);

		}else {
			Painter.drawRect(x + xOff, y, (3 * GraphicsConstants.PIXEL_SIZE_X), (3 * GraphicsConstants.PIXEL_SIZE_Y), Color.TRANSPARENT, sliderColor);
		}
	}

	
	
	
	
	public void handleMouseClick() {
		if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength + (3 * GraphicsConstants.PIXEL_SIZE_X), y + ((3 + ADDITIONAL_INTERACTION_SPACE) * GraphicsConstants.PIXEL_SIZE_Y)) || dragging) {
			value = (int) Math.min(Math.max((MouseHandler.mouseX - x - GraphicsConstants.PIXEL_SIZE_X) / ((float)barLength) * (maxValue-minValue) + minValue, minValue), maxValue);
			valueLabel.setContent(value+"");
			dragging = true;
		}
		if (dragging && !MouseHandler.mousePressed) {
			dragging = false;
		}
	}
	
	
	

	public void handleMouseWheel(float mouseWheelInput) {
		if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength + ((3 + ADDITIONAL_INTERACTION_SPACE) * GraphicsConstants.PIXEL_SIZE_X), y + (5 * GraphicsConstants.PIXEL_SIZE_Y))) {
			if (mouseWheelInput == 1 && value > minValue) {
				value -= Math.max(((maxValue-minValue) / barLength) * GraphicsConstants.PIXEL_SIZE_X, 1);
				value = Math.max(value, minValue);
				valueLabel.setContent(value+"");
			}
			if (mouseWheelInput == -1 && value <= maxValue) {
				value += Math.max(((maxValue-minValue) / barLength) * GraphicsConstants.PIXEL_SIZE_X, 1);
				value = Math.min(value, maxValue);
				valueLabel.setContent(value+"");
			}
		}
	}
}
