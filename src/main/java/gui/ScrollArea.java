package gui;


import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import input.KeyPredicate;
import input.MouseHandler;
import input.TextSupplierFactory;
import textHandler.StyledString;
import textHandler.TextHandler;

import java.util.function.BooleanSupplier;


public class ScrollArea {
	
	private int x, y, w, h;
	private GCC content = GCC.NULL;
	private Color textColor, frameColor, backgroundColor; // TODO Palette
	private Color focusedTextColor, focusedFrameColor, focusedBackgroundColor;
	private Color scrollBarColor, scrollBarSliderColor;
	private Color focusedScrollBarColor, focusedScrollBarSliderColor;
	
	
	private boolean isFocused, isLocked;
	
	private boolean horzScrollBar, vertScrollBar;
	private Scrollbar horzScrollbar, vertScrollbar;
	
	
	private Layout<ScrollArea> layout = ScrollArea.STANDARD_LAYOUT;
	
	
	public ScrollArea(int x, int y, String text) {
		this(x, y, new GCC(text));
	}

	public ScrollArea(int x, int y, int w, int h, String text) {
		this(x, y, w, h, new GCC(text));
	}
	
	
	
	
	public ScrollArea(int x, int y, StyledString styledString) {
		this(x, y, new GCC(styledString));
	}

	public ScrollArea(int x, int y, int w, int h, StyledString styledString) {
		this(x, y, w, h, new GCC(styledString));
	}
	
	
	

	public ScrollArea(int x, int y, Img img) {
		this(x, y, new GCC(img));
	}

	public ScrollArea(int x, int y, int w, int h, Img img) {
		this(x, y, w, h, new GCC(img));
	}
	
	
	
	
	public ScrollArea(int x, int y, Animation animation) {
		this(x, y, new GCC(animation));
	}

	public ScrollArea(int x, int y, int w, int h, Animation animation) {
		this(x, y, w, h, new GCC(animation));
	}
	
	
	
	
	public ScrollArea(int x, int y, GCC gcc){
		setPos(x, y);
		this.content = gcc;
		
		setWidth(content.getWidth() + 4*GraphicsConstants.PIXEL_SIZE_X);
		setHeight(content.getHeight() + 4*GraphicsConstants.PIXEL_SIZE_Y);
		
		setColors(Color.BLACK, Color.YELLOW, Color.ORANGE);
		setFocusColors(Color.BLACK, Color.YELLOW, Color.RED);
		setScrollBarColors(Color.ORANGE, Color.RED);
	}
	
	
	public ScrollArea(int x, int y, int w, int h, GCC gcc) {
		this(x, y, gcc);
		setWidth(w);
		setHeight(h);
	}
	
	
	
	

	public ScrollArea addTextSupplier() {
		TextSupplierFactory.getInstance().addSupplier(this::isFocused, KeyPredicate.KEY_PREDICATE_ALL_PRINTABLE_CHARS.andThen(KeyPredicate.KEY_PREDICATE_BACKSPACE) ,this::appendText);
		
		return this;
	}
	
	public ScrollArea addTextSupplier(BooleanSupplier extraCondition) {
		TextSupplierFactory.getInstance().addSupplier(() -> extraCondition.getAsBoolean() && this.isFocused, KeyPredicate.KEY_PREDICATE_ALL_PRINTABLE_CHARS.andThen(KeyPredicate.KEY_PREDICATE_BACKSPACE) ,this::appendText);
		
		return this;
	}
	
	
	
	public ScrollArea addTextSupplier(KeyPredicate keyPredicate) {
		TextSupplierFactory.getInstance().addSupplier(this::isFocused, keyPredicate ,this::appendText);
		
		return this;
	}
	
	
	
	public ScrollArea addTextSupplier(BooleanSupplier extraCondition, KeyPredicate keyPredicate) {
		TextSupplierFactory.getInstance().addSupplier(() -> extraCondition.getAsBoolean() && this.isFocused, keyPredicate ,this::appendText);
		
		return this;
	}
	
	
	
	
	
	public void setContent(String text) {
		setContent(new GCC(text));
	}

	public void setContent(String text, int w, int h) {
		setContent(new GCC(text), w, h);
	}

	
	
	public void setContent(StyledString styledString) {
		setContent(new GCC(styledString));
	}

	public void setContent(StyledString styledString, int w, int h) {
		setContent(new GCC(styledString), w, h);
	}

	
	
	public void setContent(Img img) {
		setContent(new GCC(img));
	}

	public void setContent(Img img, int w, int h) {
		setContent(new GCC(img), w, h);
	}

	
	
	public void setContent(Animation animation) {
		setContent(new GCC(animation));
	}

	public void setContent(Animation animation, int w, int h) {
		setContent(new GCC(animation), w, h);
	}
	
	
	
	
	
	public void setContent(GCC gcc) {
		this.content = gcc;
		setWidth(content.getWidth() + 4*GraphicsConstants.PIXEL_SIZE_X);
		setHeight(content.getHeight() + 4*GraphicsConstants.PIXEL_SIZE_Y);
	}
	
	
	public void setContent(GCC gcc, int w, int h) {
		this.content = gcc;
		setWidth(w);
		setHeight(h);
	}
	
	
	
	
	
	public void appendText(char chr) {
		if(content.type == Gui.STRING) {
			if(KeyPredicate.KEY_PREDICATE_BACKSPACE.test((short) chr)) {
				if(content.asString().length() > 0) {
					setContent(content.asString().substring(0, content.asString().length()-1), w, h);
				}
			}else {
				setContent(content.asString()+chr, w, h);
			}
			
			setScrollbarPos(0.0, 1.0);
		}
	}
	
	
	
	
	
	private void calcScrollBars() {
		if(content.type == Gui.STRING || content.type == Gui.STYLED_STRING) {
			
			horzScrollBar = false;
			horzScrollbar = null;
			
			if(content.type == Gui.STRING) {
				if(TextHandler.font().countLines(content.asString(), w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy()) == 1) {
					vertScrollBar = false;
					vertScrollbar = null;
					return;
				}
			}
			
			if(content.type == Gui.STYLED_STRING) {
				if(content.asStyledString().getLines(w - (4 * GraphicsConstants.PIXEL_SIZE_X)).length == 1) {
					vertScrollBar = false;
					vertScrollbar = null;
					return;
				}
			}
			
			
			if(content.getHeight(w - (6 * GraphicsConstants.PIXEL_SIZE_Y)) >= h - (4 * GraphicsConstants.PIXEL_SIZE_Y)) {
				int pusherLength = (int) ((h - (4f * GraphicsConstants.PIXEL_SIZE_Y)) / (content.type == Gui.STRING ?
						
										TextHandler.font().countLines(content.asString(), w - (6 * GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy()) - ((int) (((h / GraphicsConstants.PIXEL_SIZE_Y) - 4) + TextHandler.font().getLineSpacing()) / (TextHandler.font().getCharHeight() + TextHandler.font().getLineSpacing())) 
						
										: content.asStyledString().style().getFont().countLines(content.asStyledString().getText(), w - (6 * GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy())));
				
				vertScrollBar = true;
				vertScrollbar = new Scrollbar(x + w - (3*GraphicsConstants.PIXEL_SIZE_X), y + (2*GraphicsConstants.PIXEL_SIZE_Y), Gui.VERTIKAL, h - (4*GraphicsConstants.PIXEL_SIZE_Y)).setPusherLength(pusherLength).setScrollStrenght(pusherLength/GraphicsConstants.PIXEL_SIZE_Y).setColor(scrollBarColor, scrollBarSliderColor, scrollBarSliderColor);
			}else {
				vertScrollBar = false;
				vertScrollbar = null;
			}
			
		}else {
			
			
			if(content.getWidth() >= w - (3 * GraphicsConstants.PIXEL_SIZE_X) - (vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0)) {
				int pusherLength = (int) (((w - (4f * GraphicsConstants.PIXEL_SIZE_X)) / (float) content.getWidth()) * (w - (4f*GraphicsConstants.PIXEL_SIZE_X)));
				
				horzScrollBar = true;
				horzScrollbar = new Scrollbar(x + (2*GraphicsConstants.PIXEL_SIZE_X), y + h - (3*GraphicsConstants.PIXEL_SIZE_Y), Gui.HORIZONTAL, w - (4*GraphicsConstants.PIXEL_SIZE_X)).setPusherLength(pusherLength).setScrollStrenght((w - (4*GraphicsConstants.PIXEL_SIZE_X))/100).setColor(scrollBarColor, scrollBarSliderColor, scrollBarSliderColor);
			}else {
				horzScrollBar = false;
				horzScrollbar = null;
			}
			
			if(content.getHeight() >= h - (3 * GraphicsConstants.PIXEL_SIZE_Y) - (horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0)) {
				int pusherLength = (int) (((h - (4f * GraphicsConstants.PIXEL_SIZE_Y)) / (float) content.getHeight(w - (6 * GraphicsConstants.PIXEL_SIZE_Y))) * (h - (4f*GraphicsConstants.PIXEL_SIZE_Y)));
				
				vertScrollBar = true;
				vertScrollbar = new Scrollbar(x + w - (3*GraphicsConstants.PIXEL_SIZE_X), y + (2*GraphicsConstants.PIXEL_SIZE_Y), Gui.VERTIKAL, h - (4*GraphicsConstants.PIXEL_SIZE_Y)).setPusherLength(pusherLength).setScrollStrenght((h - (4*GraphicsConstants.PIXEL_SIZE_Y))/100).setColor(scrollBarColor, scrollBarSliderColor, scrollBarSliderColor);
			}else {
				vertScrollBar = false;
				vertScrollbar = null;
			}
			
			
			// Nochmal, zum absichern
			if(content.getWidth() >= w - (3 * GraphicsConstants.PIXEL_SIZE_X) - (vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0)) {
				int pusherLength = (int) (((w - (4f * GraphicsConstants.PIXEL_SIZE_X)) / (float) content.getWidth()) * (w - (4f*GraphicsConstants.PIXEL_SIZE_X)));
				
				horzScrollBar = true;
				horzScrollbar = new Scrollbar(x + (2*GraphicsConstants.PIXEL_SIZE_X), y + h - (3*GraphicsConstants.PIXEL_SIZE_Y), Gui.HORIZONTAL, w - (4*GraphicsConstants.PIXEL_SIZE_X)).setPusherLength(pusherLength).setColor(scrollBarColor, scrollBarSliderColor, scrollBarSliderColor);
			}else {
				horzScrollBar = false;
				horzScrollbar = null;
			}
		}
	}
	
	
	
	
	
	public ScrollArea lock() {
		isLocked = true;
		
		if(horzScrollbar != null) {
			horzScrollbar.lock();
		}
		
		if(vertScrollbar != null) {
			vertScrollbar.lock();
		}
		
		return this;
	}

	
	
	public ScrollArea unlock() {
		isLocked = false;
		
		if(horzScrollbar != null) {
			horzScrollbar.unlock();
		}
		
		if(vertScrollbar != null) {
			vertScrollbar.unlock();
		}
		
		return this;
	}
	
	
	
	
	public void setFocus(boolean focus) {
		if(isLocked || focus == this.isFocused) {
			return;
		}
		
		if(focus == true) {
			if(horzScrollbar != null) {
				horzScrollbar.setColor(focusedScrollBarColor, focusedScrollBarSliderColor, focusedScrollBarColor);
			}
			if(vertScrollbar != null) {
				vertScrollbar.setColor(focusedScrollBarColor, focusedScrollBarSliderColor, focusedScrollBarColor);
			}
		}else {
			if(horzScrollbar != null) {
				horzScrollbar.setColor(scrollBarColor, scrollBarSliderColor, scrollBarColor);
			}
			if(vertScrollbar != null) {
				vertScrollbar.setColor(scrollBarColor, scrollBarSliderColor, scrollBarColor);
			}
		}
		
		this.isFocused = focus;
	}
	
	
	public boolean isFocused() {
		return isFocused;
	}
	
	
	
	
	
	
	public ScrollArea setColors(Color textColor, Color backgroundColor, Color frameColor) {
		this.textColor = textColor;
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;
		
		return this;
	}
	
	

	public ScrollArea setColors(Color backgroundColor, Color frameColor) {
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;

		return this;
	}
	
	
	
	
	
	public ScrollArea setFocusColors(Color textColor, Color backgroundColor, Color frameColor) {
		this.focusedTextColor = textColor;
		this.focusedBackgroundColor = backgroundColor;
		this.focusedFrameColor = frameColor;
		
		return this;
	}
	
	

	public ScrollArea setFocusColors(Color backgroundColor, Color frameColor) {
		this.focusedBackgroundColor = backgroundColor;
		this.focusedFrameColor = frameColor;

		return this;
	}
	

	
	
	public ScrollArea setScrollBarColors(Color scrollBarColor, Color scrollBarSliderColor) {
		this.scrollBarColor = scrollBarColor;
		this.scrollBarSliderColor = scrollBarSliderColor;

		return this;
	}
	
	
	public ScrollArea setFocusScrollBarColors(Color scrollBarColor, Color scrollBarSliderColor) {
		this.focusedScrollBarColor = scrollBarColor;
		this.focusedScrollBarSliderColor = scrollBarSliderColor;

		return this;
	}
	
	

	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	

	public void setWidth(int w) {
		w = w / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.w = Math.max(w, 5 * GraphicsConstants.PIXEL_SIZE_X);
		
		calcScrollBars();
	}

	
	
	public void setHeight(int h) {
		h = h / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		this.h = Math.max(h, 9 * GraphicsConstants.PIXEL_SIZE_X);
		
		calcScrollBars();
	}
	
	

	public void setSize(int w, int h) {
		setWidth(w);
		setHeight(h);
	}
	
	
	
	public void setBounds(int x, int y, int w, int h) {
		setPos(x, y);
		setSize(w, h);
	}
	
	
	
	
	
	
	public ScrollArea setScrollbarPos(double horzPos, double vertPos) {
		if(horzScrollbar != null) {
			horzScrollbar.setPusherPos(horzPos);
		}
		
		if(vertScrollbar != null) {
			vertScrollbar.setPusherPos(vertPos);
		}
		
		return this;
	}
	
	
	
	
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	
	
	
	public GCC getContent() {
		return content;
	}
	
	
	
	
	

	public void draw() {
		if(isLocked) {
			layout.drawLocked(this);
		}
		
		if(isFocused) {
			layout.drawFocused(this);
		}else {
			layout.drawIdle(this);
		}
	}
	
	
	
	
	public void handleMouseClick() {
		if(MouseHandler.overButton(x, y, x+w, y+h)) {
			isFocused = true;
		}else {
			isFocused = false;
		}
		
		if(horzScrollBar) {
			horzScrollbar.handleMouseClick();
		}
		
		
		if(vertScrollBar) {
			vertScrollbar.handleMouseClick();
		}
	}
	
	
	

	public void handleMouseWheel(float wheelRotation) {
		if(horzScrollBar) {
			if(vertScrollBar) {
				horzScrollbar.handleMouseWheel(wheelRotation);
			}else {
				horzScrollbar.handleMouseWheelInArea(wheelRotation, x, y, x+w, y+h);
			}
		}
		
		
		if(vertScrollBar) {
			vertScrollbar.handleMouseWheelInArea(wheelRotation, x, y, x+w, y+h - (4*GraphicsConstants.PIXEL_SIZE_Y));
		}
	}
	
	

	
	
	
	
	public static final Layout<ScrollArea> STANDARD_LAYOUT = new LayoutAdapter<ScrollArea>() {
		
		@Override
		public void drawIdle(ScrollArea sa) {
			Painter.drawRect(sa.x, sa.y, sa.w, sa.h, sa.backgroundColor, sa.frameColor);
			
			int horzOffset = 0;
			int vertOffset = 0;
			
			
			switch(sa.content.type) {
			
				case Gui.STRING:
					if(sa.vertScrollBar) {
						vertOffset/* as lines */ = (int) (sa.vertScrollbar.getPusherPos() * (TextHandler.font().countLines(sa.content.asString(), sa.w - (4*GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy()) - (int) (((sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4) + TextHandler.font().getLineSpacing()) / (TextHandler.font().getCharHeight() + TextHandler.font().getLineSpacing())));
					}

					TextHandler.drawLines(TextHandler.font().getLines(sa.content.asString(), sa.w - (6 * GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy()), 
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), 
							vertOffset,
							vertOffset + (int) (((sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4) + TextHandler.font().getLineSpacing()) / (TextHandler.font().getCharHeight() + TextHandler.font().getLineSpacing()),
							sa.textColor);
					break;
					
					
				
				case Gui.STYLED_STRING:
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					TextHandler.drawStyledString(sa.content.asStyledString(), 
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), 
							sa.w - (2 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (4*GraphicsConstants.PIXEL_SIZE_X) : 0),
							sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (4*GraphicsConstants.PIXEL_SIZE_Y) : 0));
					break;
					
					
					
				case Gui.IMG:
					if(sa.horzScrollBar) {
						horzOffset = (int) ((sa.content.getWidth() - (sa.w - (4 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0))) * sa.horzScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_X;
					}
					
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					
					Painter.drawImage(sa.content.asImg().subImage(horzOffset, vertOffset, 
									(sa.w / GraphicsConstants.PIXEL_SIZE_X) - 4 - (sa.vertScrollBar ? 2 : 0),
									(sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4 - (sa.horzScrollBar ? 2 : 0)),
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
					break;
					
					
					
				case Gui.ANIMATION:
					if(sa.horzScrollBar) {
						horzOffset = (int) ((sa.content.getWidth() - (sa.w - (4 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0))) * sa.horzScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_X;
					}
					
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					
					Painter.drawLoopedAnimation(sa.content.asAnimation().subAnimation(horzOffset, vertOffset,
									(sa.w / GraphicsConstants.PIXEL_SIZE_X) - 4 - (sa.vertScrollBar ? 2 : 0),
									(sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4 - (sa.horzScrollBar ? 2 : 0)),
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
					break;
			}

			
			
			if(sa.horzScrollBar) {
				sa.horzScrollbar.draw();
			}
			
			
			if(sa.vertScrollBar) {
				sa.vertScrollbar.draw();
			}
		};
		
		
		
		
		
		@Override
		public void drawFocused(ScrollArea sa) {
			Painter.drawRect(sa.x, sa.y, sa.w, sa.h, sa.focusedBackgroundColor, sa.focusedFrameColor);
		
			int horzOffset = 0;
			int vertOffset = 0;
			
		
			switch(sa.content.type) {
			
				case Gui.STRING:
					if(sa.vertScrollBar) {
						vertOffset/* as lines */ = (int) (sa.vertScrollbar.getPusherPos() * (TextHandler.font().countLines(sa.content.asString(), sa.w - (4*GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy()) - (int) (((sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4) + TextHandler.font().getLineSpacing()) / (TextHandler.font().getCharHeight() + TextHandler.font().getLineSpacing())));
					}
						
					TextHandler.drawLines(TextHandler.font().getLines(sa.content.asString(), sa.w - (6 * GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy()), 
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), 
							vertOffset,
							vertOffset + (int) (((sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4) + TextHandler.font().getLineSpacing()) / (TextHandler.font().getCharHeight() + TextHandler.font().getLineSpacing()),
							sa.focusedTextColor);
					break;
					
					
				
				case Gui.STYLED_STRING:
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					TextHandler.drawStyledString(sa.content.asStyledString(), 
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), 
							sa.w - (2 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (4*GraphicsConstants.PIXEL_SIZE_X) : 0),
							sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (4*GraphicsConstants.PIXEL_SIZE_Y) : 0));
					break;
					
					
					
				case Gui.IMG:
					if(sa.horzScrollBar) {
						horzOffset = (int) ((sa.content.getWidth() - (sa.w - (4 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0))) * sa.horzScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_X;
					}
					
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					
					Painter.drawImage(sa.content.asImg().subImage(horzOffset, vertOffset, 
									(sa.w / GraphicsConstants.PIXEL_SIZE_X) - 4 - (sa.vertScrollBar ? 2 : 0),
									(sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4 - (sa.horzScrollBar ? 2 : 0)),
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
					break;
					
					
					
				case Gui.ANIMATION:
					if(sa.horzScrollBar) {
						horzOffset = (int) ((sa.content.getWidth() - (sa.w - (4 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0))) * sa.horzScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_X;
					}
					
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					
					Painter.drawLoopedAnimation(sa.content.asAnimation().subAnimation(horzOffset, vertOffset,
									(sa.w / GraphicsConstants.PIXEL_SIZE_X) - 4 - (sa.vertScrollBar ? 2 : 0),
									(sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4 - (sa.horzScrollBar ? 2 : 0)),
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
					break;
			}
	
			
			
			if(sa.horzScrollBar) {
				sa.horzScrollbar.draw();
			}
		
		
			if(sa.vertScrollBar) {
			sa.vertScrollbar.draw();
			}
		};
		
		
		
		
		@Override
		public void drawLocked(ScrollArea sa) {
			Painter.drawRect(sa.x, sa.y, sa.w, sa.h, sa.backgroundColor.asGray(), sa.frameColor.asGray());
			
			int horzOffset = 0;
			int vertOffset = 0;
			
			
			switch(sa.content.type) {
			
				case Gui.STRING:
					if(sa.vertScrollBar) {
						vertOffset/* as lines */ = (int) (sa.vertScrollbar.getPusherPos() * (TextHandler.font().countLines(sa.content.asString(), sa.w - (4*GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy()) - (int) (((sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4) + TextHandler.font().getLineSpacing()) / (TextHandler.font().getCharHeight() + TextHandler.font().getLineSpacing())));
					}

					TextHandler.drawLines(TextHandler.font().getLines(sa.content.asString(), sa.w - (6 * GraphicsConstants.PIXEL_SIZE_Y), TextHandler.getWrapWordPolicy()), 
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), 
							vertOffset,
							vertOffset + (int) (((sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4) + TextHandler.font().getLineSpacing()) / (TextHandler.font().getCharHeight() + TextHandler.font().getLineSpacing()),
							sa.textColor);
					break;
					
					
				
				case Gui.STYLED_STRING:
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					TextHandler.drawStyledString(sa.content.asStyledString().getGrayStyledString(), 
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), 
							sa.w - (2 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (4*GraphicsConstants.PIXEL_SIZE_X) : 0),
							sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (4*GraphicsConstants.PIXEL_SIZE_Y) : 0));
					break;
					
					
					
				case Gui.IMG:
					if(sa.horzScrollBar) {
						horzOffset = (int) ((sa.content.getWidth() - (sa.w - (4 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0))) * sa.horzScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_X;
					}
					
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					
					Painter.drawImage(sa.content.asImg().subImage(horzOffset, vertOffset, 
									(sa.w / GraphicsConstants.PIXEL_SIZE_X) - 4 - (sa.vertScrollBar ? 2 : 0),
									(sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4 - (sa.horzScrollBar ? 2 : 0)).asGray(),
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
					break;
					
					
					
				case Gui.ANIMATION:
					if(sa.horzScrollBar) {
						horzOffset = (int) ((sa.content.getWidth() - (sa.w - (4 * GraphicsConstants.PIXEL_SIZE_X) - (sa.vertScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_X) : 0))) * sa.horzScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_X;
					}
					
					if(sa.vertScrollBar) {
						vertOffset = (int) ((sa.content.getHeight() - (sa.h - (4 * GraphicsConstants.PIXEL_SIZE_Y) - (sa.horzScrollBar ? (2 * GraphicsConstants.PIXEL_SIZE_Y) : 0))) * sa.vertScrollbar.getPusherPos()) / GraphicsConstants.PIXEL_SIZE_Y;
					}
					
					
					Painter.drawLoopedAnimation(sa.content.asAnimation().subAnimation(horzOffset, vertOffset,
									(sa.w / GraphicsConstants.PIXEL_SIZE_X) - 4 - (sa.vertScrollBar ? 2 : 0),
									(sa.h / GraphicsConstants.PIXEL_SIZE_Y) - 4 - (sa.horzScrollBar ? 2 : 0)).asGray(),
							sa.x + (2 * GraphicsConstants.PIXEL_SIZE_X), 
							sa.y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
					break;
			}

			
			
			if(sa.horzScrollBar) {
				sa.horzScrollbar.draw();
			}
			
			
			if(sa.vertScrollBar) {
				sa.vertScrollbar.draw();
			}	
		};
		
	};
}
