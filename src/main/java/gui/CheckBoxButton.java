package gui;

import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import input.MouseHandler;
import textHandler.StyledString;
import textHandler.TextHandler;


public class CheckBoxButton { 	// TODO hover-Effekt hinzufügen (evtl. mit Schatten in checkBox)
	
	// Constants
	
	public static final int VERTIKAL_BOX_ALIGN_TOP 	  = 0;
	public static final int VERTIKAL_BOX_ALIGN_CENTER = 1;
	public static final int VERTIKAL_BOX_ALIGN_BOTTOM = 2;
	
	// End
	
	
	private int x, y, vertBoxAlign = VERTIKAL_BOX_ALIGN_CENTER;
	private GCC[] options;
	private boolean[] optionChecked;
	private Color textColor, frameColor, checkedColor, uncheckedColor;

	
	
	public CheckBoxButton(CheckBoxButton rb) {
		setPos(rb.x, rb.y);
		this.vertBoxAlign = rb.vertBoxAlign;
		this.options = GCC.toGCCArray(rb.options);
		this.optionChecked = new boolean[this.options.length];
		setColors(rb.textColor, rb.frameColor, rb.checkedColor, rb.uncheckedColor);
	}
	
	
	
	public CheckBoxButton(int x, int y, String... options) {
		this(x, y, GCC.toGCCArray(options));
	}

	
	public CheckBoxButton(int x, int y, StyledString... options) {
		this(x, y, GCC.toGCCArray(options));
	}
	
	
	public CheckBoxButton(int x, int y, Img... options) {
		this(x, y, GCC.toGCCArray(options));
	}	
	

	public CheckBoxButton(int x, int y, Animation... options) {
		this(x, y, GCC.toGCCArray(options));
	}
	
	
	public CheckBoxButton(int x, int y, Drawable... options) {
		this(x, y, GCC.toGCCArray(options));
	}
	

	
	public CheckBoxButton(int x, int y, GCC... options) {
		setPos(x, y);
		if(options.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
		
		this.optionChecked = new boolean[this.options.length];

		if (GCC.containsString(this.options)) {
			setColors(Color.BLACK, Color.ORANGE, Color.RED, Color.YELLOW);
		} else {
			setColors(Color.ORANGE, Color.RED, Color.YELLOW);
		}
	}
	
	
	
	public CheckBoxButton(int x, int y, Object... options) {
		this(x, y, GCC.cast(options));
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
	
	public void setContent(Drawable... options) {
		setContent(GCC.toGCCArray(options));
	}
	
	

	public void setContent(GCC... options) {
		if(options.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
		
		this.optionChecked = new boolean[this.options.length];

		if (GCC.containsString(this.options)) {
			setColors(Color.BLACK, Color.ORANGE, Color.RED, Color.YELLOW);
		} else {
			setColors(Color.ORANGE, Color.RED, Color.YELLOW);
		}
	}
	
	
	
	public void setContent(Object... options) {
		setContent(GCC.cast(options));
	}

	
	
	
	
	public CheckBoxButton setVertBoxAlign(int align) {
		if (align == VERTIKAL_BOX_ALIGN_TOP || align == VERTIKAL_BOX_ALIGN_CENTER || align == VERTIKAL_BOX_ALIGN_BOTTOM) {
			this.vertBoxAlign = align;
		}

		return this;
	}

	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	
	public CheckBoxButton setColors(Color frameColor, Color checkedColor, Color uncheckedColor) {
		this.frameColor = frameColor;
		this.checkedColor = checkedColor;
		this.uncheckedColor = uncheckedColor;

		return this;
	}
	
	
	
	public CheckBoxButton setColors(Color textColor, Color frameColor, Color checkedColor, Color uncheckedColor) {
		if (GCC.containsString(options)) {
			this.textColor = textColor;
		}
		this.frameColor = frameColor;
		this.checkedColor = checkedColor;
		this.uncheckedColor = uncheckedColor;

		return this;
	}
	
	
	
	
	
	
	

	public void draw() {
		if (GCC.containsString(options)) {
			TextHandler.textAlign(TextHandler.LEFT, TextHandler.TOP);
		}
		
		int yoff = 0;
		for (int i = 0; i < options.length; i++) {

			switch (vertBoxAlign) {
			case VERTIKAL_BOX_ALIGN_TOP:
				Painter.drawRect(x, y + yoff, (5 * GraphicsConstants.PIXEL_SIZE_X),
						(5 * GraphicsConstants.PIXEL_SIZE_Y), (optionChecked[i] ? checkedColor : uncheckedColor),
						frameColor);
				break;
			case VERTIKAL_BOX_ALIGN_CENTER:
				Painter.drawRect(x, y + yoff + (options[i].getHeight() / 2) - (2 * GraphicsConstants.PIXEL_SIZE_Y),
						(5 * GraphicsConstants.PIXEL_SIZE_X), (5 * GraphicsConstants.PIXEL_SIZE_Y),
						(optionChecked[i] ? checkedColor : uncheckedColor), frameColor);
				break;
			case VERTIKAL_BOX_ALIGN_BOTTOM:
				Painter.drawRect(x, y + yoff + options[i].getHeight() - (5 * GraphicsConstants.PIXEL_SIZE_Y),
						(5 * GraphicsConstants.PIXEL_SIZE_X), (5 * GraphicsConstants.PIXEL_SIZE_Y),
						(optionChecked[i] ? checkedColor : uncheckedColor), frameColor);
				break;
			}

			if (options[i].type == Gui.STYLED_STRING) {
				GCC gcc = new GCC(new StyledString(options[i].asStyledString().getText(),
						options[i].asStyledString().style().setAlign(TextHandler.LEFT, TextHandler.TOP)));
				
				if(optionChecked[i]) {
					gcc = new GCC(gcc.asStyledString().getAlternativeStyledString());
				}
				
				gcc.draw(x + (7 * GraphicsConstants.PIXEL_SIZE_X), y + yoff, textColor);
				yoff += gcc.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
			} else {
				options[i].draw(x + (7 * GraphicsConstants.PIXEL_SIZE_X), y + yoff, textColor);
				yoff += options[i].getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
			}
		}
	}

	
	
	
	
	public void handleMouseClick() {
		int yoff = 0;
		for (int i = 0; i < options.length; i++) {
			if (MouseHandler.overButton(x - GraphicsConstants.PIXEL_SIZE_X, y + yoff - GraphicsConstants.PIXEL_SIZE_Y,
					x + (8 * GraphicsConstants.PIXEL_SIZE_X) + options[i].getWidth(),
					y + yoff + options[i].getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
				optionChecked[i] = !optionChecked[i];
			}
			yoff += options[i].getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
		}
	}

}
