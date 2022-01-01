package gui;

import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import input.MouseHandler;
import textHandler.StyledString;


public class OptionSlider {

	// Constants
	
	public static final int ADDITIONAL_INTERACTION_SPACE = 1; // 1 == 1*PIXEL_SIZE_Y 
	
	// End
	
	
	
	
	private int x, y, barLength, selected;
	private GCC[] options;
	private boolean dragging;
	private Color barColor, sliderColor, hoverSliderColor;
	
	private Label valueLabel;

	
	
	

	
	public OptionSlider(OptionSlider os) {
		setPos(os.x, os.y);
		setBarLength(os.barLength);
		setContent(GCC.toGCCArray(os.options));
		setColors(os.barColor, os.sliderColor, os.hoverSliderColor);
		
		this.valueLabel = new Label(os.valueLabel);
	}
	
	
	
	
	
	
	public OptionSlider(int x, int y, int barLength, String... options) {
		this(x, y, barLength, GCC.toGCCArray(options));
	}
	
	

	public OptionSlider(int x, int y, int barLength, StyledString... options) {
		this(x, y, barLength, GCC.toGCCArray(options));
	}

	
	
	public OptionSlider(int x, int y, int barLength, Img... options) {
		this(x, y, barLength, GCC.toGCCArray(options));
	}
	
	

	public OptionSlider(int x, int y, int barLength, Animation... options) {
		this(x, y, barLength, GCC.toGCCArray(options));
	}
	
	
	

	public OptionSlider(int x, int y, int barLength, GCC... options) {
		setPos(x, y);
		setBarLength(barLength);
		
		if(options.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
		
		setColors(Color.ORANGE, Color.RED, Color.RED);
		
		valueLabel = new Label(x, y - this.options[0].getHeight() - (5*GraphicsConstants.PIXEL_SIZE_Y), this.options[0]);
	}
	
	
	
	public OptionSlider(int x, int y, int barLength, Object... options) {
		this(x, y, barLength, GCC.cast(options));
	}

	
	
	
	
	
	public OptionSlider setColors(Color barColor, Color sliderColor, Color hoverSliderColor) {
		this.barColor = barColor;
		this.sliderColor = sliderColor;
		this.hoverSliderColor = hoverSliderColor;
		
		return this;
	}
	
	
	


	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	public OptionSlider setBarLength(int barLength) {
		this.barLength = Math.max(barLength, GraphicsConstants.PIXEL_SIZE_X);
		
		return this;
	}
	
	
	
	
	public void setContent(String... strings) {
		setContent(GCC.toGCCArray(strings));
	}
	
	
	public void setContent(StyledString... styledStrings) {
		setContent(GCC.toGCCArray(styledStrings));
	}
	
	
	public void setContent(Img... imgs) {
		setContent(GCC.toGCCArray(imgs));
	}
	
	
	public void setContent(Animation... animations) {
		setContent(GCC.toGCCArray(animations));
	}
	
	
	
	public void setContent(GCC... gccs) {
		if(gccs.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}else {
			this.options = gccs;
		}
		
		valueLabel = new Label(x, y - this.options[0].getHeight() - (5*GraphicsConstants.PIXEL_SIZE_Y), this.options[0]);
	}
	
	
	
	public void setContent(Object... objects) {
		setContent(GCC.cast(objects));
	}
	
	
	
	public GCC getSelectedOption() {
		return options[selected];
	}
	
	
	public GCC getOption(int index) {
		if(index < options.length && index >= 0) {
			return options[index];
		}else {
			return GCC.NULL;
		}
	}
	
	public GCC[] getOptions() {
		return options;
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
		
		int xOff = (int) (selected / ((float)options.length-1) * barLength);
		
		if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength + (3 * GraphicsConstants.PIXEL_SIZE_X), y + ((3 + ADDITIONAL_INTERACTION_SPACE) * GraphicsConstants.PIXEL_SIZE_Y)) || dragging) {
			valueLabel.draw();
			
			Painter.drawRect(x + xOff, y, (3 * GraphicsConstants.PIXEL_SIZE_X), (3 * GraphicsConstants.PIXEL_SIZE_Y), Color.TRANSPARENT, hoverSliderColor);
		}else {
			Painter.drawRect(x + xOff, y, (3 * GraphicsConstants.PIXEL_SIZE_X), (3 * GraphicsConstants.PIXEL_SIZE_Y), Color.TRANSPARENT, sliderColor);
		}
	}

	
	
	
	
	public void handleMouseClick() {
		if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength + (3 * GraphicsConstants.PIXEL_SIZE_X), y + ((3 + ADDITIONAL_INTERACTION_SPACE) * GraphicsConstants.PIXEL_SIZE_Y)) || dragging) {
			selected = (int) Math.min(Math.max((MouseHandler.mouseX - x - GraphicsConstants.PIXEL_SIZE_X) / ((float)barLength) * options.length, 0), options.length-1);
			valueLabel.setContent(options[selected]);
			valueLabel.setPos(x, y - options[selected].getHeight() - (5*GraphicsConstants.PIXEL_SIZE_Y));
			dragging = true;
		}
		if (dragging && !MouseHandler.mousePressed) {
			dragging = false;
		}
	}
	
	
	

	public void handleMouseWheel(float mouseWheelInput) {
		if (MouseHandler.overButton(x, y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), x + barLength + ((3 + ADDITIONAL_INTERACTION_SPACE) * GraphicsConstants.PIXEL_SIZE_X), y + (5 * GraphicsConstants.PIXEL_SIZE_Y))) {
			if (mouseWheelInput == 1 && selected > 0) {
				selected--;
				valueLabel.setContent(options[selected]);
				valueLabel.setPos(x, y - options[selected].getHeight() - (5*GraphicsConstants.PIXEL_SIZE_Y));
			}
			if (mouseWheelInput == -1 && selected < options.length-1) {
				selected++;
				valueLabel.setContent(options[selected]);
				valueLabel.setPos(x, y - options[selected].getHeight() - (5*GraphicsConstants.PIXEL_SIZE_Y));
			}
		}
	}
}
