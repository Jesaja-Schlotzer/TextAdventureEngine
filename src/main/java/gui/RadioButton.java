package gui;

import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Palette;
import graphics.img.Img;
import input.MouseHandler;
import textHandler.StyledString;
import textHandler.TextHandler;


public class RadioButton { 	// TODO hover-Effekt hinzufügen (evtl. mit Schatten in checkBox)
	
	// Constants
	
	public static final int VERTIKAL_BOX_ALIGN_TOP 	  = 0;
	public static final int VERTIKAL_BOX_ALIGN_CENTER = 1;
	public static final int VERTIKAL_BOX_ALIGN_BOTTOM = 2;
	
	// End
	
	
	private int x, y, vertBoxAlign = VERTIKAL_BOX_ALIGN_CENTER;
	private GCC[] options;
	private int checkedOption;
	private Palette palette = Palette.STANDARD_PALETTE;
	private Layout<RadioButton> layout = STANDARD_LAYOUT;
	private boolean locked;

	
	
	public RadioButton(RadioButton rb) {
		setPos(rb.x, rb.y);
		this.vertBoxAlign = rb.vertBoxAlign;
		this.options = GCC.toGCCArray(rb.options);
		setPalette(rb.palette);
		setLayout(rb.layout);
	}
	
	
	
	public RadioButton(int x, int y, String... options) {
		this(x, y, GCC.toGCCArray(options));
	}

	
	public RadioButton(int x, int y, StyledString... options) {
		this(x, y, GCC.toGCCArray(options));
	}
	
	
	public RadioButton(int x, int y, Img... options) {
		this(x, y, GCC.toGCCArray(options));
	}	
	

	public RadioButton(int x, int y, Animation... options) {
		this(x, y, GCC.toGCCArray(options));
	}
	

	
	public RadioButton(int x, int y, GCC... options) {
		setPos(x, y);
		if(options.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
	}
	
	
	
	public RadioButton(int x, int y, Object... options) {
		this(x, y, GCC.cast(options));
	}
	
	
	
	
	public void lock() {
		locked = true;
	}
	
	
	public void unlock() {
		locked = false;
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
			this.options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
	}
	
	
	
	public void setContent(Object... options) {
		setContent(GCC.cast(options));
	}

	
	
	
	
	public RadioButton setVertBoxAlign(int align) {
		if (align == VERTIKAL_BOX_ALIGN_TOP || align == VERTIKAL_BOX_ALIGN_CENTER || align == VERTIKAL_BOX_ALIGN_BOTTOM) {
			this.vertBoxAlign = align;
		}

		return this;
	}

	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	
	

	public RadioButton setPalette(Palette palette) {
		if(palette != null) {
			this.palette = palette;
		}
		
		return this;
	}

	
	public Palette getPalette() {
		return palette;
	}
	
	
	
	
	
	
	
	public RadioButton setLayout(Layout<RadioButton> layout) {
		if(layout != null) {
			this.layout = layout;
		}
		
		return this;
	}
	
	
	
	

	public void draw() {
		if (GCC.containsString(options)) {
			TextHandler.textAlign(TextHandler.LEFT, TextHandler.TOP);
		}
		
		if(!locked) {
			layout.drawIdle(this);
		}else {
			layout.drawLocked(this);
		}
	}

	
	
	
	
	public void handleMouseClick() {
		int yoff = 0;
		for (int i = 0; i < options.length; i++) {
			if (MouseHandler.overButton(x - GraphicsConstants.PIXEL_SIZE_X, y + yoff - GraphicsConstants.PIXEL_SIZE_Y, x + (8 * GraphicsConstants.PIXEL_SIZE_X) + options[i].getWidth(), y + yoff + options[i].getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
				checkedOption = i;
			}
			yoff += options[i].getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
		}
	}
	
	
	
	
	
	
	
	public static Layout<RadioButton> STANDARD_LAYOUT = new LayoutAdapter<RadioButton>() {
		
		@Override
		public void drawIdle(RadioButton rb) {
			int yoff = 0;
			for (int i = 0; i < rb.options.length; i++) {
				switch (rb.vertBoxAlign) {
				case VERTIKAL_BOX_ALIGN_TOP:
					Painter.drawRect(rb.x, rb.y + yoff, (5 * GraphicsConstants.PIXEL_SIZE_X),
							(5 * GraphicsConstants.PIXEL_SIZE_Y), (rb.checkedOption == i ? rb.palette.getColor(1) : rb.palette.getColor(3)), rb.palette.getColor(2));
					break;
				case VERTIKAL_BOX_ALIGN_CENTER:
					Painter.drawRect(rb.x, rb.y + yoff + (rb.options[i].getHeight() / 2) - (2 * GraphicsConstants.PIXEL_SIZE_Y),
							(5 * GraphicsConstants.PIXEL_SIZE_X), (5 * GraphicsConstants.PIXEL_SIZE_Y),
							(rb.checkedOption == i ? rb.palette.getColor(1) : rb.palette.getColor(3)), rb.palette.getColor(2));
					break;
				case VERTIKAL_BOX_ALIGN_BOTTOM:
					Painter.drawRect(rb.x, rb.y + yoff + rb.options[i].getHeight() - (5 * GraphicsConstants.PIXEL_SIZE_Y),
							(5 * GraphicsConstants.PIXEL_SIZE_X), (5 * GraphicsConstants.PIXEL_SIZE_Y),
							(rb.checkedOption == i ? rb.palette.getColor(1) : rb.palette.getColor(3)), rb.palette.getColor(2));
					break;
				}

				if (rb.options[i].type == Gui.STYLED_STRING) {
					GCC gcc = new GCC(new StyledString(rb.options[i].asStyledString().getText(),
							rb.options[i].asStyledString().style().setAlign(TextHandler.LEFT, TextHandler.TOP)));
					
					if(rb.checkedOption == i) {
						gcc = new GCC(gcc.asStyledString().getAlternativeStyledString());
					}
					
					gcc.draw(rb.x + (7 * GraphicsConstants.PIXEL_SIZE_X), rb.y + yoff, rb.palette.getColor(0));
					yoff += gcc.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				} else {
					rb.options[i].draw(rb.x + (7 * GraphicsConstants.PIXEL_SIZE_X), rb.y + yoff, rb.palette.getColor(0));
					yoff += rb.options[i].getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				}
			}
		}
		
		
		
		
		
		@Override
		public void drawLocked(RadioButton rb) {
			int yoff = 0;
			for (int i = 0; i < rb.options.length; i++) {
				switch (rb.vertBoxAlign) {
				case VERTIKAL_BOX_ALIGN_TOP:
					Painter.drawRect(rb.x, rb.y + yoff, (5 * GraphicsConstants.PIXEL_SIZE_X), (5 * GraphicsConstants.PIXEL_SIZE_Y), (rb.checkedOption == i ? rb.palette.getColor(1) : rb.palette.getColor(3)).asGray(), rb.palette.getColor(2).asGray());
					break;
				case VERTIKAL_BOX_ALIGN_CENTER:
					Painter.drawRect(rb.x, rb.y + yoff + (rb.options[i].getHeight() / 2) - (2 * GraphicsConstants.PIXEL_SIZE_Y),
							(5 * GraphicsConstants.PIXEL_SIZE_X), (5 * GraphicsConstants.PIXEL_SIZE_Y),
							(rb.checkedOption == i ? rb.palette.getColor(1) : rb.palette.getColor(3)).asGray(), rb.palette.getColor(2).asGray());
					break;
				case VERTIKAL_BOX_ALIGN_BOTTOM:
					Painter.drawRect(rb.x, rb.y + yoff + rb.options[i].getHeight() - (5 * GraphicsConstants.PIXEL_SIZE_Y), (5 * GraphicsConstants.PIXEL_SIZE_X), (5 * GraphicsConstants.PIXEL_SIZE_Y), (rb.checkedOption == i ? rb.palette.getColor(1) : rb.palette.getColor(3)).asGray(), rb.palette.getColor(2).asGray());
					break;
				}
				
				if (rb.options[i].type == Gui.STYLED_STRING) {
					GCC gcc = new GCC(new StyledString(rb.options[i].asStyledString().getText(), rb.options[i].asStyledString().style().setAlign(TextHandler.LEFT, TextHandler.TOP)));
					
					if(rb.checkedOption == i) {
						gcc = new GCC(gcc.asStyledString().getAlternativeStyledString());
					}
					
					gcc.asGray().draw(rb.x + (7 * GraphicsConstants.PIXEL_SIZE_X), rb.y + yoff, rb.palette.getColor(0));
					yoff += gcc.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				} else {
					rb.options[i].asGray().draw(rb.x + (7 * GraphicsConstants.PIXEL_SIZE_X), rb.y + yoff, rb.palette.getColor(0));
					yoff += rb.options[i].asGray().getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				}
			}
		}
		
	};
}
