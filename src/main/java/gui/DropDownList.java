package gui;


import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import input.MouseHandler;
import textHandler.StyledString;
import textHandler.TextHandler;


public class DropDownList {
	
	// Constants
	
	public static boolean CLOSE_WHEN_CLICK_ELSEWHERE = false;
	
	// End
	
	

	private int x, y, w;
	private GCC[] selectables;

	private int selected;

	private int startSelect, maxShownItems;

	private boolean isListOpen;

	
	
	
	public DropDownList(DropDownList ddl) {
		setPos(ddl.x, ddl.y);
		setMinWidth(ddl.w);
		this.selectables = GCC.toGCCArray(ddl.selectables);
		calculateMaxShownItems();
	}
	
	
	
	public DropDownList(int x, int y, String... selectables) {
		this(x, y, GCC.toGCCArray(selectables));
	}

	
	
	public DropDownList(int x, int y, StyledString... selectables) {
		this(x, y, GCC.toGCCArray(selectables));
	}
	
	

	public DropDownList(int x, int y, Img... selectables) {
		this(x, y, GCC.toGCCArray(selectables));
	}
	
	

	public DropDownList(int x, int y, Animation... selectables) {
		this(x, y, GCC.toGCCArray(selectables));
	}
	
	
	
	public DropDownList(int x, int y, Drawable... selectables) {
		this(x, y, GCC.toGCCArray(selectables));
	}
	
	
	
	
	public DropDownList(int x, int y, GCC... selectables) {
		setPos(x, y);
		this.selectables = selectables;

		this.selected = 0;

		calculateMaxShownItems();

		this.w = GCC.getWidest(selectables) + (4 * GraphicsConstants.PIXEL_SIZE_X);
	}
	
	
	
	
	public DropDownList(int x, int y, Object... selectables) {
		this(x, y, GCC.cast(selectables));
	}
	
	
	
	

	private void calculateMaxShownItems() {
		this.maxShownItems = selectables.length;
		int currentHeight = 0;
		for (int i = 0; i < selectables.length; i++) {
			if (currentHeight + selectables[i].getHeight()+ (4 * GraphicsConstants.PIXEL_SIZE_Y) > (Gui.REF_SCREEN_SIZE_Y - y + selectables[selected].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y))) {
				maxShownItems = i;
				break;
			}
			currentHeight += selectables[i].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
		}
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
	
	public void setContent(Animation... animation) {
		setContent(GCC.toGCCArray(animation));
	}
	
	public void setContent(Drawable... animation) {
		setContent(GCC.toGCCArray(animation));
	}
	
	
	public void setContent(GCC... gccs) {
		selectables = gccs;
		
		calculateMaxShownItems();
		
		this.w = GCC.getWidest(selectables) + (4 * GraphicsConstants.PIXEL_SIZE_X);
	}
	
	
	
	public void setContent(Object... selectables) {
		setContent(GCC.cast(selectables));
	}
	
	
	
	
	public boolean isOpen() {
		return isListOpen;
	}

	
	
	public int getSelectedIndex() {
		return selected;
	}

	
	
	public GCC getSelectedOption() {
		return selectables[selected];
	}

	
	
	public void setSelectedIndex(int index) {
		if (index >= 0 && index < selectables.length) {
			selected = index;
		}
	}
	

	public int getIndex(GCC keyObj) {
		for (int i = 0; i < selectables.length; i++) {
			if (selectables[i].equals(keyObj)) {
				return i;
			}
		}
		return -1;
	}
	
	
	

	public GCC getOption(int index) {
		return selectables[index];
	}
	
	

	public void setMinWidth(int minWidth) {
		w = Math.max(GCC.getWidest(selectables), minWidth);
	}
	
	
	
	
	
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	
	
	

	public void draw() {
		TextHandler.textAlign(TextHandler.LEFT, TextHandler.TOP);
		if (isListOpen) {
			drawOpenList();
		} else {
			drawClosedList();
		}
	}
	
	

	private void drawClosedList(){
		int cellHeight = 0;
		
		cellHeight = selectables[selected].getHeight() + (4*GraphicsConstants.PIXEL_SIZE_Y);

		Painter.drawRect(x, y, w, cellHeight, Color.TRANSPARENT, Color.ORANGE);
		Painter.drawRect(x+w-GraphicsConstants.PIXEL_SIZE_X, y, cellHeight, cellHeight, Color.TRANSPARENT, Color.ORANGE);
		if(isListOpen){
			int originX = x+w+GraphicsConstants.PIXEL_SIZE_X;
			int originY = y+(2*GraphicsConstants.PIXEL_SIZE_X);
			int size = cellHeight-(4*GraphicsConstants.PIXEL_SIZE_Y);
			//painter.drawPolygon(ORANGE, originX, originY, originX+(2*GraphicsConstants.PIXEL_SIZE_X), originY+(4*GraphicsConstants.PIXEL_SIZE_Y), originX+(4*GraphicsConstants.PIXEL_SIZE_X), originY);
			//painter.drawTriangle(originX, originY, originX+(2*GraphicsConstants.PIXEL_SIZE_X), originY+(4*GraphicsConstants.PIXEL_SIZE_Y), originX+(5*GraphicsConstants.PIXEL_SIZE_X), originY, ORANGE);
			Painter.drawRect(originX, originY+size-size/3, size, size/3, Color.ORANGE);
			Painter.drawRect(originX+(size/4), originY+size-(size/3)-size/2, size/2, size/2, Color.ORANGE);
			Painter.drawRect(originX+(int) (size*(5f/12f)), originY, size/6, size/6, Color.ORANGE);
		}else{
			int originX = x+w+GraphicsConstants.PIXEL_SIZE_X;
			int originY = y+(2*GraphicsConstants.PIXEL_SIZE_X);
			int size = cellHeight-(4*GraphicsConstants.PIXEL_SIZE_Y);
			//painter.drawPolygon(ORANGE, originX+(2*GraphicsConstants.PIXEL_SIZE_X), originY, originX, originY+(4*GraphicsConstants.PIXEL_SIZE_Y), originX+(4*GraphicsConstants.PIXEL_SIZE_X), originY+(4*GraphicsConstants.PIXEL_SIZE_Y));
			Painter.drawRect(originX, originY, size, size/3, Color.ORANGE);
			Painter.drawRect(originX+(size/4), originY+(size/3), size/2, size/2, Color.ORANGE);
			Painter.drawRect(originX+(int) (size*(5f/12f)), originY+(int) (size*(5f/6f)), size/6, size/6, Color.ORANGE);
		}
		
		getSelectedOption().draw(x+(2*GraphicsConstants.PIXEL_SIZE_X), y+(2*GraphicsConstants.PIXEL_SIZE_Y), Color.BLACK);
	}

	
	
	
	private void drawOpenList() {
		drawClosedList();

		int yoff =  y + selectables[selected].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
		
		int maxShownItemIndex = Math.min(startSelect + maxShownItems, selectables.length);
		
		for (int i = startSelect; i < maxShownItemIndex; i++) {
			int cellHeight = 0;
			
			cellHeight = selectables[i].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
			
			
			if (i == selected) {
				Painter.drawRect(x, yoff, w, cellHeight, Color.YELLOW, Color.RED);
			} else if (i == selectables.length - 1 || i == startSelect + maxShownItems - 1) {
				Painter.drawRect(x, yoff, w, cellHeight, Color.YELLOW, Color.ORANGE);
				Painter.drawRect(x + GraphicsConstants.PIXEL_SIZE_X, yoff,	w - (2 * GraphicsConstants.PIXEL_SIZE_X), GraphicsConstants.PIXEL_SIZE_Y, Color.YELLOW);
			} else {
				Painter.drawRect(x + GraphicsConstants.PIXEL_SIZE_X, yoff ,	w - (2 * GraphicsConstants.PIXEL_SIZE_X), cellHeight, Color.YELLOW);
				Painter.drawRect(x, yoff, GraphicsConstants.PIXEL_SIZE_X, cellHeight, Color.ORANGE);
				Painter.drawRect(x + w - GraphicsConstants.PIXEL_SIZE_X, yoff, GraphicsConstants.PIXEL_SIZE_X, cellHeight, Color.ORANGE);
			}
			
			selectables[i].draw(x + (2 * GraphicsConstants.PIXEL_SIZE_X), (2 * GraphicsConstants.PIXEL_SIZE_Y) + yoff, Color.BLACK);
			
			yoff += cellHeight;
		}

		if (selectables.length > maxShownItems) {
			// painter.drawImage(painter.ddlArrows, x+w, y+yoff-(17*GraphicsConstants.PIXEL_SIZE_Y));
		}
	}
	
	
	
	
	
	
	

	public void handleMouseClick() {
		if (selectables.length > maxShownItems) {
			handleMouseClickArrows();
		}
		if (isListOpen) {
			handleMouseClickSelection();
		}
		handleMouseClickOpenList();
	}

	
	
	
	
	
	private void handleMouseClickOpenList() {
		if (MouseHandler.overButton(x + w - GraphicsConstants.PIXEL_SIZE_X, y, x + w + getSelectedOption().getHeight() + (3*GraphicsConstants.PIXEL_SIZE_X), y + getSelectedOption().getHeight() + (4*GraphicsConstants.PIXEL_SIZE_X))) {
			isListOpen = !isListOpen;
		} else if (CLOSE_WHEN_CLICK_ELSEWHERE && isListOpen && !MouseHandler.overButton(x, y, x + w + getSelectedOption().getHeight() + (4*GraphicsConstants.PIXEL_SIZE_X), y + getOpenListHeight())) {
			isListOpen = false;
		}
	}

	
	
	
	
	private void handleMouseClickArrows() {
		if (this.isListOpen) {
			if (this.selectables.length > this.maxShownItems) {
				if (MouseHandler.overButton(x + w,
						y + this.maxShownItems
								* (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y))
								+ (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y))
								- (17 * GraphicsConstants.PIXEL_SIZE_Y),
						x + w + (8 * GraphicsConstants.PIXEL_SIZE_X),
						y + this.maxShownItems
								* (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y)))
						&& this.startSelect > 0) {
					this.startSelect--;
				}
				if (MouseHandler.overButton(x + w,
						y + this.maxShownItems
								* (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y)),
						x + w + (8 * GraphicsConstants.PIXEL_SIZE_X),
						y + this.maxShownItems
								* (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y))
								+ (9 * GraphicsConstants.PIXEL_SIZE_Y))
						&& this.startSelect < this.selectables.length - this.maxShownItems) {
					this.startSelect++;
				}
			}
		}
	}

	
	
	
	private void handleMouseClickSelection() {
		int yoff = y + selectables[selected].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
		
		int maxShownItemIndex = Math.min(startSelect + maxShownItems, selectables.length);
		
		for (int i = startSelect; i < maxShownItemIndex; i++) {
			int cellHeight = 0;
			
			cellHeight = selectables[i].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
			
			if(MouseHandler.overButton(x, yoff, x + w, yoff + cellHeight)) {
				selected = startSelect + i;
				break;
			}
			
			yoff += cellHeight;
		}
	}

	
	
	
	
	
	public void handleMouseWheel(float mouseWheelInput) {
		if (this.isListOpen) {
			if (this.selectables.length > this.maxShownItems) {
				if (MouseHandler.overButton(x, y, x + w,
						y + this.maxShownItems
								* (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y))
								+ (TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y)))) {
					if (mouseWheelInput == -1 && this.startSelect > 0) {
						this.startSelect--;
					}
					if (mouseWheelInput == 1 && this.startSelect < this.selectables.length - this.maxShownItems) {
						this.startSelect++;
					}
				}
			}
		}
	}
	
	
	
	
	
	private int getOpenListHeight() {
		int yoff = selectables[selected].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
		
		int maxShownItemIndex = Math.min(startSelect + maxShownItems, selectables.length);
		
		for (int i = startSelect; i < maxShownItemIndex; i++) {
			yoff += selectables[i].getHeight() + (4 * GraphicsConstants.PIXEL_SIZE_Y);
		}
		
		return yoff;
	}
}
