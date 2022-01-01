package gui;

import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import textHandler.Style;
import textHandler.StyledString;
import textHandler.TextHandler;


public class Label {
	private int x, y, w, h;
	private String title;
	private GCC content = GCC.NULL;
	private Color backgroundColor, frameColor, textColor, titleColor;

	
	
	
	public Label(Label l) {
		setBounds(l.x, l.y, l.w, l.h);
		this.title = l.title;
		setContent(l.getContent());
		setColors(l.textColor, l.backgroundColor, l.frameColor, l.titleColor);
	}
	
	
	
	
	public Label(int x, int y, String content) {
		this(x, y, new GCC(content));
	}
	
	

	public Label(int x, int y, String title, String content) {
		this(x, y, title, new GCC(content));
	}

	
	
	
	public Label(int x, int y, StyledString content) {
		this(x, y, new GCC(content));
	}
	
	

	public Label(int x, int y, String title, StyledString content) {
		this(x, y, title, new GCC(content));
	}

	
	
	
	public Label(int x, int y, Img content) {
		this(x, y, new GCC(content));
	}
	
	

	public Label(int x, int y, String title, Img content) {
		this(x, y, title, new GCC(content));
	}
	
	
	

	public Label(int x, int y, Animation content) {
		this(x, y, new GCC(content));
	}

	
	
	public Label(int x, int y, String title, Animation content) {
		this(x, y, title, new GCC(content));
	}
	
	
	
	

	public Label(int x, int y, GCC contentContainer) {
		setPos(x, y);
		setContent(contentContainer);

		if (contentContainer.type == Gui.STRING) {
			setColors(Color.BLACK, Color.YELLOW, Color.ORANGE, Color.BLACK);
		} else {
			setColors(Color.YELLOW, Color.ORANGE, Color.BLACK);
		}
	}
	
	
	

	public Label(int x, int y, String title, GCC contentContainer) {
		setPos(x, y);
		this.title = title;
		setContent(contentContainer);

		if (contentContainer.type == Gui.STRING) {
			setColors(Color.BLACK, Color.YELLOW, Color.ORANGE, Color.BLACK);
		} else {
			setColors(Color.YELLOW, Color.ORANGE, Color.BLACK);
		}
	}

	
	
	
	
	
	
	public void setTitle(String title) {
		this.title = title;
		setContent(content);
	}
	
	
	
	public void setTitle(String title, Color titleColor) {
		setTitle(title);
		this.titleColor = titleColor;
	}
	
	

	public void noTitle() {
		this.title = null;
		setContent(content);
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
	
	
	

	public void setContent(GCC content) {
		if (title == null) {
			this.w = content.getWidth() + (4 * GraphicsConstants.PIXEL_SIZE_X);
			this.h = content.getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
		} else {
			this.w = (int) ((content
					.getWidth() > (TextHandler.font().getTextWidth(title) * GraphicsConstants.PIXEL_SIZE_X)
							? content.getWidth()
							: Math.floor(TextHandler.font().getTextWidth(title) * GraphicsConstants.PIXEL_SIZE_X))
					+ (4 * GraphicsConstants.PIXEL_SIZE_X));
			this.h = (TextHandler.font().getCharHeight() + (5 * GraphicsConstants.PIXEL_SIZE_Y))
					+ content.getHeight();
		}
		this.content = content;
	}
	
	
	
	
	public GCC getContent(){
		return content;
	}
	
	
	

	
	
	public Label setColors(Color textColor, Color backgroundColor, Color frameColor, Color titleColor) {
		if (this.content.type == Gui.STRING) {
			this.textColor = textColor;
		}
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;
		this.titleColor = titleColor;

		return this;
	}

	
	
	public Label setColors(Color backgroundColor, Color frameColor, Color titleColor) {
		this.backgroundColor = backgroundColor;
		this.frameColor = frameColor;
		this.titleColor = titleColor;

		return this;
	}

	
	
	
	
	public void setBounds(int x, int y, int w, int h) {
		setPos(x, y);
		setSize(w, h);
	}
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}

	
	
	
	public Label setSize(int w, int h) {
		setWidth(w);
		setHeight(h);

		return this;
	}
	
	
	

	public Label setWidth(int w) {
		w = w  / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		
		if (title == null) {
			this.w = Math.max(content.getWidth() + (4 * GraphicsConstants.PIXEL_SIZE_X), w);
		} else {
			this.w = Math.max((content.getWidth() > TextHandler.font().getTextWidth(title) ? content.getWidth() : TextHandler.font().getTextWidth(title)) + (4 * GraphicsConstants.PIXEL_SIZE_X), w);
		}

		return this;
	}
	
	
	

	public Label setHeight(int h) {
		h = h  / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		if (title == null) {
			this.h = Math.max(content.getHeight(), h);
		} else {
			this.h = Math.max(content.getHeight() + (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (5 * GraphicsConstants.PIXEL_SIZE_Y)), h);
		}
		
		return this;
	}
	
	
	
	
	

	public void draw() {
		Painter.drawRect(x, y, w, h, backgroundColor, frameColor);

		if (title == null) {
			content.draw(x + (2 * GraphicsConstants.PIXEL_SIZE_X), y + (2 * GraphicsConstants.PIXEL_SIZE_Y), textColor);
		} else {
			TextHandler.drawStyledString(title, new Style(Style.STANDARD_UNDERLINED).setTextColor(titleColor), x + (2 * GraphicsConstants.PIXEL_SIZE_X), y + (2 * GraphicsConstants.PIXEL_SIZE_Y));
			content.draw(x + (2 * GraphicsConstants.PIXEL_SIZE_X), y + (5 * GraphicsConstants.PIXEL_SIZE_Y)	+ TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y, textColor);
		}
	}
}
