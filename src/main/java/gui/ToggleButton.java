
package gui;

import components.nods.UINode;
import debugging.Debugger;
import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.colors.Palette;
import graphics.img.Img;
import graphics.img.ImgOperator;
import input.MouseHandler;
import screen.fragmentRecorder.FragmentRecorderPool;
import screen.fragmentRecorder.RecordMode;
import textHandler.StyledString;


public class ToggleButton extends UINode {
	private int x, y, w, h;
	private GCC buttonContent = GCC.NULL;
	private Palette buttonPalette = Palette.STANDARD_PALETTE;
	private Layout<ToggleButton> buttonLayout = STANDARD_LAYOUT;
	private boolean locked;
	
	
	private ImgOperator operator = ImgOperator.NULL; // TODO passenderen Namen finden (als operator)
	
	
	
	public ToggleButton(ToggleButton tb) {
		super("", null); // TODO
		this.buttonContent = new GCC(tb.buttonContent);
		this.locked = tb.locked;
		setBounds(tb.x, tb.y, tb.w, tb.h);
		setPalette(tb.buttonPalette);
		setLayout(tb.buttonLayout);
	}
	
	
	
	public ToggleButton(int x, int y, String text) {
		this(x, y, new GCC(text));
	}
	
	
	public ToggleButton(int x, int y, StyledString styledString) {
		this(x, y, new GCC(styledString));
	}
	
	

	public ToggleButton(int x, int y, Img img) {
		this(x, y, new GCC(img));
	}
	
	

	public ToggleButton(int x, int y, Animation animation) {
		this(x, y, new GCC(animation));
	}
	
	
	
	public ToggleButton(int x, int y, Drawable drawable) {
		this(x, y, new GCC(drawable));
	}
	
	
	

	public ToggleButton(int x, int y, GCC content) {
		super("", null); // TODO
		setPos(x, y);
		setWidth(content.getWidth() + (4 * GraphicsConstants.PIXEL_SIZE_X));
		setHeight(content.getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y));
		this.buttonContent = content;
	}

	
	
	
	
	
	public ToggleButton setWindowAlign(int align) {
		if (align == Gui.VERT_CENTERED) {
			setPosX((Gui.REF_SCREEN_SIZE_X / 2) - (w / 2));
		} else if (align == Gui.HORZ_CENTERED) {
			setPosY((Gui.REF_SCREEN_SIZE_Y / 2) - (h / 2));
		} else if (align == Gui.BOTH_CENTERED) {
			setPos((Gui.REF_SCREEN_SIZE_X / 2) - (w / 2),
							   (Gui.REF_SCREEN_SIZE_Y / 2) - (h / 2));
		} else {
			Debugger.addLogEntry("FEHLER - Gui/ToggleButton/setWindowAlign() - Angegebene Align existiert nicht.");
		}
		return this;
	}
	
	
	

	public ToggleButton lock() {
		locked = true;
		return this;
	}

	
	
	public ToggleButton unlock() {
		locked = false;
		return this;
	}

	
	
	
	
	
	public void setContent(String text) {
		setContent(new GCC(text));
	}

	
	
	public void setContent(StyledString styledString) {
		setContent(new GCC(styledString));
	}
	
	

	public void setContent(Img img) {
		setContent(new GCC(img));
	}
	
	

	public void setContent(Animation animation) {
		setContent(new GCC(animation));
	}
	
	
	
	public void setContent(Drawable drawable) {
		setContent(new GCC(drawable));
	}
	
	
	
	

	public void setContent(GCC content) {
		this.setSize(content.getWidth() + (4 * GraphicsConstants.PIXEL_SIZE_X), content.getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y));
		this.buttonContent = content;
	}
	
	
	public GCC getContent() {
		return buttonContent;
	}
	
	
	
	
	
	
	public ToggleButton setBounds(int x, int y, int w, int h) {
		setPos(x, y);
		setSize(w, h);
		
		return this;
	}
	
	
	
	public ToggleButton setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;

		return this;
	}
	
	
	public ToggleButton setPosX(int x) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;

		return this;
	}


	public ToggleButton setPosY(int y) {
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;

		return this;
	}

	
	
	public ToggleButton setSize(int w, int h) {
		setWidth(w);
		setHeight(h);

		return this;
	}
	
	
	

	public ToggleButton setWidth(int w) {
		w = w / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.w = Math.max(buttonContent.getWidth() + (4 * GraphicsConstants.PIXEL_SIZE_X), w);

		return this;
	}
	
	

	public ToggleButton setHeight(int h) {
		h = h / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		this.h = Math.max(buttonContent.getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_X), h);

		return this;
	}
	

	
	
	
	
	
	
	public int getPosX() {
		return x;
	}
	
	
	public int getPosY() {
		return y;
	}
	
	
	
	public int getWidth() {
		return w;
	}
	
	
	public int getHeight() {
		return h;
	}
	
	
	
	
	
	

	public ToggleButton setPalette(Palette palette) {
		if(palette != null) {
			this.buttonPalette = palette;
		}
		
		return this;
	}

	
	public Palette getPalette() {
		return buttonPalette;
	}
	
	
	
	
	
	
	
	public ToggleButton setLayout(Layout<ToggleButton> layout) {
		if(layout != null) {
			this.buttonLayout = layout;
		}
		
		return this;
	}
	
	
	
	
	
	
	public ToggleButton applyOperator(ImgOperator imgOperator) {
		if(imgOperator != null) {
			operator = imgOperator;
		}
		
		return this;
	}
	
	
	
	
	public void draw() {
		if(operator.equals(ImgOperator.NULL)) {
			drawIntern();
			return;
		}
		
		FragmentRecorderPool.getInstance().begin("main/exitButton", RecordMode.ONLY_DRAW_ON_BUFFER);
		drawIntern();
		FragmentRecorderPool.getInstance().end("main/exitButton");
		Painter.drawImage(operator.apply(FragmentRecorderPool.getInstance().getRecordAndRemove("main/exitButton")), x, y);
	}
	
	
	
	private void drawIntern() {
		
		
		if(buttonContent.type == Gui.DRAWABLE) {
			buttonContent.draw(x, y, Color.NO_COLOR);
			return;
		}
		
		if(locked) {
			buttonLayout.drawLocked(this);
		}else {
			if(MouseHandler.overButton(x, y, w, h)) {
				if (MouseHandler.mousePressed) {
					buttonLayout.drawPressing(this);
				}else {
					buttonLayout.drawHover(this);
				}
			}else {
				buttonLayout.drawIdle(this);
			}
		}
	}
	
	
	
	
	public boolean handleMouseClick() {
		if (locked) {
			return false;
		}
		return MouseHandler.overButton(x, y, w, h);
	}
	
	
	
	
	
	
	
	
	public static Layout<ToggleButton> STANDARD_LAYOUT = new LayoutAdapter<ToggleButton>() {
		
		@Override
		public void drawIdle(ToggleButton tb) {
			Painter.drawRect(tb.x, tb.y, tb.w, tb.h, tb.buttonPalette.getColor(4), tb.buttonPalette.getColor(1));
			Painter.drawRect(tb.x + GraphicsConstants.PIXEL_SIZE_X, tb.y + tb.h, tb.w, GraphicsConstants.PIXEL_SIZE_Y, tb.buttonPalette.getColor(6));
			Painter.drawRect(tb.x + tb.w, tb.y + GraphicsConstants.PIXEL_SIZE_Y, GraphicsConstants.PIXEL_SIZE_X, tb.h - GraphicsConstants.PIXEL_SIZE_Y, tb.buttonPalette.getColor(6));
			
			//if(tb.buttonContent.type == Gui.STYLED_STRING) {
			//	tb.buttonContent.draw(tb.x + (tb.w / 2), tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0)); 
			//}else {
				tb.buttonContent.draw(tb.x + (tb.w / 2) - tb.buttonContent.getWidth() / 2, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			//}
		}
		
			
			
		@Override
		public void drawHover(ToggleButton tb) {
			Painter.drawRect(tb.x, tb.y, tb.w, tb.h, tb.buttonPalette.getColor(4), tb.buttonPalette.getColor(2));
			Painter.drawRect(tb.x + GraphicsConstants.PIXEL_SIZE_X, tb.y + tb.h, tb.w, GraphicsConstants.PIXEL_SIZE_Y, tb.buttonPalette.getColor(6));
			Painter.drawRect(tb.x + tb.w, tb.y + GraphicsConstants.PIXEL_SIZE_Y, GraphicsConstants.PIXEL_SIZE_X, tb.h - GraphicsConstants.PIXEL_SIZE_Y, tb.buttonPalette.getColor(6));
				
			//if(tb.buttonContent.type == Gui.STYLED_STRING) {
			//	TextHandler.drawStyledString(tb.buttonContent.asStyledString().getAlternativeStyledString(), tb.x + (tb.w / 2), tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
			//}else {
				tb.buttonContent.draw(tb.x + (tb.w / 2) - tb.buttonContent.getWidth() / 2, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			//}
		}
			
			
			
		@Override
		public void drawPressing(ToggleButton tb) {
			Painter.drawRect(tb.x + GraphicsConstants.PIXEL_SIZE_X, tb.y + GraphicsConstants.PIXEL_SIZE_Y, tb.w, tb.h, tb.buttonPalette.getColor(4), tb.buttonPalette.getColor(2));
			
			//if(tb.buttonContent.type == Gui.STYLED_STRING) {
			//	tb.buttonContent.draw(tb.x + (tb.w / 2) + GraphicsConstants.PIXEL_SIZE_X, tb.y + (3 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			//}else {
				tb.buttonContent.draw(tb.x + (tb.w / 2) - tb.buttonContent.getWidth() / 2 + GraphicsConstants.PIXEL_SIZE_X, tb.y + (3 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			//}
		}
		
			
			
		@Override
		public void drawLocked(ToggleButton tb) {
			Painter.drawRect(tb.x, tb.y, tb.w, tb.h, tb.buttonPalette.getColor(4).asGray(), tb.buttonPalette.getColor(2).asGray());
			Painter.drawRect(tb.x + GraphicsConstants.PIXEL_SIZE_X, tb.y + tb.h, tb.w, GraphicsConstants.PIXEL_SIZE_Y, tb.buttonPalette.getColor(6).asGray());
			Painter.drawRect(tb.x + tb.w, tb.y + GraphicsConstants.PIXEL_SIZE_Y, GraphicsConstants.PIXEL_SIZE_X, tb.h - GraphicsConstants.PIXEL_SIZE_Y, tb.buttonPalette.getColor(6).asGray());
				
			//if(tb.buttonContent.type == Gui.STYLED_STRING) {
			//	tb.buttonContent.asGray().draw(tb.x + (tb.w / 2), tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0).asGray());
			//}else {
				tb.buttonContent.asGray().draw(tb.x + (tb.w / 2) - tb.buttonContent.getWidth() / 2, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0).asGray());
			//}
		}
	};
	
	
	
	
	
public static Layout<ToggleButton> TAE_MENU_LAYOUT = new LayoutAdapter<ToggleButton>() {
		
		@Override
		public void drawIdle(ToggleButton tb) {
			Painter.drawRect(tb.x, tb.y, tb.w, tb.h, tb.buttonPalette.getColor(1), tb.buttonPalette.getColor(2));
			
			if(tb.buttonContent.type == Gui.STYLED_STRING) {
				tb.buttonContent.draw(tb.x + (tb.w / 2), tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0)); 
			}else {
				tb.buttonContent.draw(tb.x + (tb.w / 2) - (tb.buttonContent.getWidth() / 2), tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			}
		}
		
		
		
		@Override
		public void drawHover(ToggleButton tb) {
			Painter.drawRect(tb.x + GraphicsConstants.PIXEL_SIZE_X, tb.y, tb.w, tb.h, tb.buttonPalette.getColor(1), tb.buttonPalette.getColor(2));
			
			if(tb.buttonContent.type == Gui.STYLED_STRING) {
				new GCC(tb.buttonContent.asStyledString().getAlternativeStyledString()).draw(tb.x + (tb.w / 2) + GraphicsConstants.PIXEL_SIZE_X, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			}else {
				tb.buttonContent.draw(tb.x + (tb.w / 2) - tb.buttonContent.getWidth() / 2 + GraphicsConstants.PIXEL_SIZE_X, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			}
		}
		
		
		
		@Override
		public void drawPressing(ToggleButton tb) {
			Painter.drawRect(tb.x + GraphicsConstants.PIXEL_SIZE_X, tb.y, tb.w, tb.h, tb.buttonPalette.getColor(1), tb.buttonPalette.getColor(2));
			
			if(tb.buttonContent.type == Gui.STYLED_STRING) {
				tb.buttonContent.draw(tb.x + (tb.w / 2) + GraphicsConstants.PIXEL_SIZE_X, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			}else {
				tb.buttonContent.draw(tb.x + (tb.w / 2) - tb.buttonContent.getWidth() / 2 + GraphicsConstants.PIXEL_SIZE_X, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0));
			}
		}
		
			
			
		@Override
		public void drawLocked(ToggleButton tb) {
			Painter.drawRect(tb.x, tb.y, tb.w, tb.h, tb.buttonPalette.getColor(1).asGray(), tb.buttonPalette.getColor(2).asGray());
			
			if(tb.buttonContent.type == Gui.STYLED_STRING) {
				tb.buttonContent.asGray().draw(tb.x + (tb.w / 2), tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0).asGray());
			}else {
				tb.buttonContent.asGray().draw(tb.x + (tb.w / 2) - tb.buttonContent.getWidth() / 2, tb.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), tb.buttonPalette.getColor(0).asGray());
			}
		}
	};
}