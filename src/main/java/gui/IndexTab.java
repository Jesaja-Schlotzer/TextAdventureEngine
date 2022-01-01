package gui;

import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import input.MouseHandler;
import textHandler.StyledString;
import textHandler.TextHandler;


public class IndexTab {
	private int selected, x, y;
	private Color textColor, backgroundColor, frameColor;
	private GCC[] options;
	
	

	public IndexTab(IndexTab it) {
		setPos(it.x, it.y);
		setColors(it.textColor, it.backgroundColor, it.frameColor);
		this.options = GCC.toGCCArray(it.options);
	}
	
	
	
	public IndexTab(int x, int y, String... options) {
		this(x, y, GCC.toGCCArray(options));
	}

	
	public IndexTab(int x, int y, StyledString... options) {
		this(x, y, GCC.toGCCArray(options));
	}

	
	public IndexTab(int x, int y, Img... options) {
		this(x, y, GCC.toGCCArray(options));
	}
	

	public IndexTab(int x, int y, Animation... options) {
		this(x, y, GCC.toGCCArray(options));
	}
	
	
	

	public IndexTab(int x, int y, GCC... options) {
		setPos(x, y);
		if(options.length == 0) {
			options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
		
		if (GCC.containsString(options)) {
			setColors(Color.BLACK, Color.YELLOW, Color.ORANGE);
		} else {
			setColors(Color.YELLOW, Color.ORANGE);
		}
	}
	
	
	
	public IndexTab(int x, int y, Object... selectables) {
		this(x, y, GCC.cast(selectables));
	}
	
	
	
	
	

	public void setContent(String... options) {
		setContent(GCC.toGCCArray(options));
	}
	

	public void setContent(StyledString... options) {
		setContent(GCC.toGCCArray(options));
	}

	
	public void setContent(Img... options) {
		setContent(GCC.toGCCArray(options));
	}
	

	public void setContent(Animation... options) {
		setContent(GCC.toGCCArray(options));
	}
	
	
	

	public void setContent(GCC... options) {
		if(options.length == 0) {
			options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
		
		if (GCC.containsString(options)) {
			setColors(Color.BLACK, Color.YELLOW, Color.ORANGE);
		} else {
			setColors(Color.YELLOW, Color.ORANGE);
		}
	}
	
	
	
	public void setContent(Object... options) {
		setContent(GCC.cast(options));
	}
	
	
	
	

	public IndexTab setColors(Color textColor, Color backgroundColor, Color frameColor) {
		if (GCC.containsString(options)) {
			this.textColor = textColor;
		}
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;

		return this;
	}

	
	public IndexTab setColors(Color backgroundColor, Color frameColor) {
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;

		return this;
	}
	
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	

	public int getSelectedIndex() {
		return selected;
	}
	
	
	public GCC getSelectedOption() {
		return options[selected];
	}
	
	public GCC getOption(int index) {
		if(index >= 0 && index < options.length) {
			return options[index];
		}else {
			return GCC.NULL;
		}
	}

	
	
	
	
	public void draw() {
		int xOff = 0;

		if (GCC.containsString(options)) {
			TextHandler.textAlign(TextHandler.LEFT, TextHandler.TOP);
			TextHandler.textColor(textColor);
		}

		for (int i = 0; i < options.length; i++) {
			if (MouseHandler.overButton(x + xOff, y,
					x + xOff + options[i].getWidth() + (6 * GraphicsConstants.PIXEL_SIZE_X),
					y + options[i].getHeight() + (i == selected ? (6 * GraphicsConstants.PIXEL_SIZE_Y)
							: (5 * GraphicsConstants.PIXEL_SIZE_Y)))) { // hover
				Painter.drawRect(x + xOff, y, options[i].getWidth() + (6 * GraphicsConstants.PIXEL_SIZE_X), options[i]
						.getHeight()
						+ (i == selected ? (6 * GraphicsConstants.PIXEL_SIZE_Y) : (5 * GraphicsConstants.PIXEL_SIZE_Y)),
						backgroundColor, frameColor);

				options[i].draw(x + xOff + (3 * GraphicsConstants.PIXEL_SIZE_X), y
						+ (i == selected ? (3 * GraphicsConstants.PIXEL_SIZE_Y) : (3 * GraphicsConstants.PIXEL_SIZE_Y)),
						textColor);
				xOff += options[i].getWidth() + (5 * GraphicsConstants.PIXEL_SIZE_X);

			} else {
				Painter.drawRect(x + xOff, y, options[i].getWidth() + (6 * GraphicsConstants.PIXEL_SIZE_X), options[i]
						.getHeight()
						+ (i == selected ? (6 * GraphicsConstants.PIXEL_SIZE_Y) : (4 * GraphicsConstants.PIXEL_SIZE_Y)),
						backgroundColor, frameColor);

				options[i].draw(x + xOff + (3 * GraphicsConstants.PIXEL_SIZE_X), y
						+ (i == selected ? (3 * GraphicsConstants.PIXEL_SIZE_Y) : (2 * GraphicsConstants.PIXEL_SIZE_Y)),
						textColor);
				xOff += options[i].getWidth() + (5 * GraphicsConstants.PIXEL_SIZE_X);
			}
		}
	}
	
	
	

	public void handleMouseClick() {
		int xOff = 0;

		for (int i = 0; i < options.length; i++) {
			if (MouseHandler.overButton(x + xOff + (int) (0.5 * GraphicsConstants.PIXEL_SIZE_X), y,
					x + xOff + options[i].getWidth() + (int) (5.5 * GraphicsConstants.PIXEL_SIZE_X),
					y + options[i].getHeight() + (i == selected ? (6 * GraphicsConstants.PIXEL_SIZE_Y)
							: (4 * GraphicsConstants.PIXEL_SIZE_Y)))) {
				selected = i;
			}
			xOff += options[i].getWidth() + (5 * GraphicsConstants.PIXEL_SIZE_X);
		}
	}
}
