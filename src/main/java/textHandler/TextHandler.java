package textHandler;

import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;

import java.util.ArrayList;


public class TextHandler {

	// Constants

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int CENTER = 2;
	public static final int TOP = 3;
	public static final int BOTTOM = 4;

	
	
	
	
	public static final char ONE_SPACE = '\t'; // TODO kritisch
	
	
	public static String space(int spaceWidth) {
		String space = "";
		
		for(int i = 0; i < spaceWidth; i++) {
			space += ONE_SPACE;
		}
		
		return space;
	}
	
	
	
	
	// Properties
	private static Font usingFont;

	
	protected static Color textColor = Color.BLACK;
	private static int horizontalTextAlign = LEFT;
	private static int vertikalTextAlign = TOP;

	
	private static boolean wrapWordPolicy = true;
	
	
	// set

	public static void textFont(Font font) {
		usingFont = font;
	}

	
	
	public static void textColor(Color value) {
		textColor = value;
	}

	
	
	public static Color getTextColor() {
		return textColor;
	}

	
	
	public static void textAlign(int align) {
		if (align == LEFT || align == CENTER || align == RIGHT) {
			horizontalTextAlign = align;
		}
		vertikalTextAlign = TOP;
	}

	
	
	
	public static void textAlign(int horiAlign, int vertAlign) {
		if (horiAlign == LEFT || horiAlign == CENTER || horiAlign == RIGHT) {
			horizontalTextAlign = horiAlign;
		}
		if (vertAlign == TOP || vertAlign == CENTER || vertAlign == BOTTOM) {
			vertikalTextAlign = vertAlign;
		}
	}
	
	
	

	public static void charSpacing(int charSpacing) {
		usingFont.charSpacing = charSpacing;
	}

	
	
	public static int getCharSpacing() {
		return usingFont.charSpacing;
	}

	
	
	public static void wordSpacing(int wordSpacing) {
		usingFont.wordSpacing = wordSpacing;
	}

	
	
	public static int getWordSpacing() {
		return usingFont.wordSpacing;
	}

	
	
	public static void lineSpacing(int lineSpacing) {
		usingFont.lineSpacing = lineSpacing;
	}
	
	

	public static int getLineSpacing() {
		return usingFont.lineSpacing;
	}

	
	
	
	
	
	public static void setWrapWordPolicy(boolean WrapWordPolicy) {
		wrapWordPolicy = WrapWordPolicy;
	}
	
	
	
	public static boolean getWrapWordPolicy() {
		return wrapWordPolicy;
	}
	
	
	
	
	
	
	// get

	
	public static Font font() {
		return usingFont;
	}

	
	
	
	
	// Draw
	
	
	public static void drawString(Object text, float x, float y, float w, float h, Color tempColor, Font tempFont) {
		x /= GraphicsConstants.PIXEL_SIZE_X;
		y /= GraphicsConstants.PIXEL_SIZE_Y;
		w /= GraphicsConstants.PIXEL_SIZE_X;
		h /= GraphicsConstants.PIXEL_SIZE_Y;
		
		Color c = getTextColor();
		textColor(tempColor);
		tempFont.drawString(text + "", (int) x, (int) y, (int) (x + w), (int) (y + h));
		textColor(c);
	}
	
	

	public static void drawString(Object text, float x, float y, float w, float h, Font tempFont) { // TODO was passiert bei -w / -h ?!?
		x /= GraphicsConstants.PIXEL_SIZE_X;
		y /= GraphicsConstants.PIXEL_SIZE_Y;
		w /= GraphicsConstants.PIXEL_SIZE_X;
		h /= GraphicsConstants.PIXEL_SIZE_Y;
		
		tempFont.drawString(text + "", (int) x, (int) y, (int) (x + w), (int) (y + h));
	}
	
	
	public static void drawString(Object text, float x, float y, float w, float h, Color tempColor) { // TODO was passiert bei -w / -h ?!?
		x /= GraphicsConstants.PIXEL_SIZE_X;
		y /= GraphicsConstants.PIXEL_SIZE_Y;
		w /= GraphicsConstants.PIXEL_SIZE_X;
		h /= GraphicsConstants.PIXEL_SIZE_Y;
		
		Color c = getTextColor();
		textColor(tempColor);
		usingFont.drawString(text + "", (int) x, (int) y, (int) (x + w), (int) (y + h));
		textColor(c);
	}
	
	
	

	public static void drawString(Object text, float x, float y, float w, float h) {
		x /= GraphicsConstants.PIXEL_SIZE_X;
		y /= GraphicsConstants.PIXEL_SIZE_Y;
		w /= GraphicsConstants.PIXEL_SIZE_X;
		h /= GraphicsConstants.PIXEL_SIZE_Y;
		
		usingFont.drawString(text + "", (int) x, (int) y, (int) (x + w), (int) (y + h));
	}
	
	
	

	
	public static void drawString(Object text, float x, float y, Color tempColor, Font tempFont) {
		Font font = font();
		textFont(tempFont);
		drawString(text, (int) x, (int) y, tempColor);
		textFont(font);
	}
	
	
	
	public static void drawString(Object text, float x, float y, Font tempFont) {
		Font font = font();
		textFont(tempFont);
		drawString(text, (int) x, (int) y);
		textFont(font);
	}
	
	
	public static void drawString(Object text, float x, float y, Color tempColor) {
		Color c = getTextColor();
		textColor(tempColor);
		drawString(text, (int) x, (int) y);
		textColor(c);
	}
	
	
	

	public static void drawString(Object text, float x, float y) {
		x /= GraphicsConstants.PIXEL_SIZE_X;
		y /= GraphicsConstants.PIXEL_SIZE_Y;
		
		float yOff = 0;
		
		
		switch (vertikalTextAlign) {
		case TOP:
			yOff = 0;
			break;
		case CENTER:
			yOff = usingFont.maxCharHeight / 2;
			break;
		case BOTTOM:
			yOff = usingFont.maxCharHeight;
			break;
		}

		
		switch (horizontalTextAlign) {
		case LEFT:
			usingFont.drawString(text + "", (int) x, (int) (y - yOff));
			break;
		case CENTER:
			usingFont.drawString(text + "", (int) x - (usingFont.getTextWidth(text + "") / 2), (int) (y - yOff));
			break;
		case RIGHT:
			usingFont.drawString(text + "", (int) x - usingFont.getTextWidth(text + ""), (int) (y - yOff));
			break;
		}
	}

	
	
	
	
	public static void drawString(Object text, float x, float y, float w) {
		switch (horizontalTextAlign) {
		case LEFT:
			drawString(text + "", (int) x, (int) y, (int) w, Integer.MAX_VALUE);
			break;
		case CENTER:
			drawString(text + "", (int) x + (w - usingFont.getTextWidth(text + "")), (int) y, (int) w, Integer.MAX_VALUE);
			break;
		case RIGHT:
			drawString(text + "", (int) x + w - usingFont.getTextWidth(text + ""), (int) y, (int) w, Integer.MAX_VALUE);
			break;
		}
	}

	
	
	
	
	
	
	// Draw - StyledString

	
	public static void drawStyledString(String text, Style style, int x, int y) {
		drawStyledString(new StyledString(text, style), x, y);
	}

	
	
	
	
	public static void drawStyledString(StyledString styledString, int x, int y) {
		Font globalFont = font();

		
		
		if (styledString.style().getShadow(0) || styledString.style().getShadow(1) || styledString.style().getShadow(7)) {
			x += GraphicsConstants.PIXEL_SIZE_X;
		}

		if (styledString.style().getShadow(1) || styledString.style().getShadow(2) || styledString.style().getShadow(3)) {
			y += GraphicsConstants.PIXEL_SIZE_Y;
		}
		
		

		// globale Spacings werden gespeichert
		int globalCharSpacing = getCharSpacing();
		int globalWordSpacing = getWordSpacing();
		int globalLineSpacing = getLineSpacing();

		
		// lokale Spacings werden, wenn nötig, genutzt
		if (styledString.style().getLocalCharSpacing() != -1) {
			charSpacing(styledString.style().getLocalCharSpacing());
		}
		if (styledString.style().getLocalWordSpacing() != -1) {
			wordSpacing(styledString.style().getLocalWordSpacing());
		}
		if (styledString.style().getLocalLineSpacing() != -1) {
			lineSpacing(styledString.style().getLocalLineSpacing());
		}
		
		
		

		if (styledString.style().getFont() != null) {
			textFont(styledString.style().getFont());
		}

		
		
		
		// setAlign
		if (styledString.style().getHorizontalTextAlign() == -1) {
			textAlign(LEFT);
		} else {
			textAlign(styledString.style().getHorizontalTextAlign());
		}
		
		

		if (styledString.style().getVertikalTextAlign() == -1) {
			textAlign(horizontalTextAlign, TOP);
		} else {
			textAlign(horizontalTextAlign, styledString.style().getVertikalTextAlign());
		}
		
		
		
		

		// drawShadow
		textColor(styledString.style().getShadowColor());
		if (styledString.style().getShadow(0)) {
			drawString(styledString.getText(), x - GraphicsConstants.PIXEL_SIZE_X, y);
		}
		if (styledString.style().getShadow(4)) {
			drawString(styledString.getText(), x + GraphicsConstants.PIXEL_SIZE_X, y);
		}
		if (styledString.style().getShadow(2)) {
			drawString(styledString.getText(), x, y - GraphicsConstants.PIXEL_SIZE_Y);
		}
		if (styledString.style().getShadow(6)) {
			drawString(styledString.getText(), x, y + GraphicsConstants.PIXEL_SIZE_Y);
		}

		if (styledString.style().getShadow(1)) {
			drawString(styledString.getText(), x - GraphicsConstants.PIXEL_SIZE_X, y - GraphicsConstants.PIXEL_SIZE_Y);
		}
		if (styledString.style().getShadow(3)) {
			drawString(styledString.getText(), x + GraphicsConstants.PIXEL_SIZE_X, y - GraphicsConstants.PIXEL_SIZE_Y);
		}
		if (styledString.style().getShadow(5)) {
			drawString(styledString.getText(), x + GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y);
		}
		if (styledString.style().getShadow(7)) {
			drawString(styledString.getText(), x - GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y);
		}
		
		
		
		

		// drawText
		textColor(styledString.style().getTextColor());
		drawString(styledString.getText(), x, y);

		
		
		
		
		// drawUnderline
		String[] textInLines = styledString.getText().split("\n");
		for (int lines = 0; lines < textInLines.length; lines++) {
			if (styledString.style().getUnderline() != null) {
				int textWidth = usingFont.getTextWidth(styledString.getText()) * GraphicsConstants.PIXEL_SIZE_X + (styledString.style().getAdditionalUnderlineLength() * GraphicsConstants.PIXEL_SIZE_X);
				int alignOff = (styledString.style().getHorizontalTextAlign() == CENTER ? -styledString.getWidth() * GraphicsConstants.PIXEL_SIZE_X / 2 : (styledString.style().getHorizontalTextAlign() == RIGHT ? -styledString.getWidth() * GraphicsConstants.PIXEL_SIZE_X : 0));
				int xoff = alignOff - (styledString.style().getAdditionalUnderlineLength() * GraphicsConstants.PIXEL_SIZE_X);
				int yoff = 0;
				while (xoff < textWidth + alignOff) {
					for (int i = 0; i < styledString.style().getUnderline().underlineWidth; i++) {
						for (int j = 0; j < styledString.style().getUnderline().underlineHeight; j++) {
							if (Color.isTransparent(styledString.style().getUnderline().underlineData[i][j])) {
								continue;
							}

							if (styledString.style().getUnderline().getColorMode() == Underline.MONOCHROME) {
								Painter.drawPoint(x + xoff, y + yoff + (usingFont.getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y) + GraphicsConstants.PIXEL_SIZE_Y + (j * GraphicsConstants.PIXEL_SIZE_Y), styledString.style().getUnderlineColor());
							} else if (styledString.style().getUnderline().getColorMode() == Underline.GRAY_SCALE) {
								Painter.drawPoint(x + xoff, y + yoff + (usingFont.getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y) + GraphicsConstants.PIXEL_SIZE_Y + (j * GraphicsConstants.PIXEL_SIZE_Y), styledString.style().getGrayStyle().getUnderline().underlineData[i][j]);
							} else if (styledString.style().getUnderline().getColorMode() == Underline.POLYCHROME) {
								Painter.drawPoint(x + xoff, y + yoff + (usingFont.getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y) + GraphicsConstants.PIXEL_SIZE_Y + (j * GraphicsConstants.PIXEL_SIZE_Y), styledString.style().getUnderline().underlineData[i][j]);
							}
						}
						xoff += GraphicsConstants.PIXEL_SIZE_X;
						if (xoff >= textWidth) {
							break;
						}
					}
				}
				yoff += styledString.getHeight() + usingFont.lineSpacing;
			}
		}

		
		
		// Spacings werden resetet
		charSpacing(globalCharSpacing);
		wordSpacing(globalWordSpacing);
		lineSpacing(globalLineSpacing);

		
		// Font wird resetet
		textFont(globalFont);
	}
	
	
	

	public static void drawStyledString(StyledString styledString, int x, int y, int w, int h) {
		Font globalFont = font();

		
		
		if (styledString.style().getFont() != null) {
			textFont(styledString.style().getFont());
		}

		
		
		
		if (styledString.style().getShadow(0) || styledString.style().getShadow(1) || styledString.style().getShadow(7)) {
			x += GraphicsConstants.PIXEL_SIZE_X;
		}

		if (styledString.style().getShadow(1) || styledString.style().getShadow(2) || styledString.style().getShadow(3)) {
			y += GraphicsConstants.PIXEL_SIZE_Y;
		}

		
		
		
		// globale Spacings werden gespeichert
		int globalCharSpacing = getCharSpacing();
		int globalWordSpacing = getWordSpacing();
		int globalLineSpacing = getLineSpacing();

		
		
		// lokale Spacings werden, wenn nötig, genutzt
		if (styledString.style().getLocalCharSpacing() != -1) {
			charSpacing(styledString.style().getLocalCharSpacing());
		}
		if (styledString.style().getLocalWordSpacing() != -1) {
			wordSpacing(styledString.style().getLocalWordSpacing());
		}
		if (styledString.style().getLocalLineSpacing() != -1) {
			lineSpacing(styledString.style().getLocalLineSpacing());
		}

		
		
		
		// setAlign
		if (styledString.style().getHorizontalTextAlign() == -1) {
			textAlign(LEFT);
		} else {
			textAlign(styledString.style().getHorizontalTextAlign());
		}

		
		if (styledString.style().getVertikalTextAlign() == -1) {
			textAlign(horizontalTextAlign, TOP);
		} else {
			textAlign(horizontalTextAlign, styledString.style().getVertikalTextAlign());
		}

		
		
		
		int lineYoff = 0;
		for (int lineIndex = 0; lineIndex < styledString.getLines(w).length; lineIndex++) {
			StyledString styledStringLine = styledString.getLines(w)[lineIndex];
			
			// drawShadow
			textColor(styledStringLine.style().getShadowColor());
			if (styledStringLine.style().getShadow(0)) {
				drawString(styledStringLine.getText(), x - GraphicsConstants.PIXEL_SIZE_X, y + lineYoff, w, h);
			}
			if (styledStringLine.style().getShadow(4)) {
				drawString(styledStringLine.getText(), x + GraphicsConstants.PIXEL_SIZE_X, y + lineYoff, w, h);
			}
			if (styledStringLine.style().getShadow(2)) {
				drawString(styledStringLine.getText(), x, y - GraphicsConstants.PIXEL_SIZE_Y + lineYoff, w, h);
			}
			if (styledStringLine.style().getShadow(6)) {
				drawString(styledStringLine.getText(), x, y + GraphicsConstants.PIXEL_SIZE_Y + lineYoff, w, h);
			}

			if (styledStringLine.style().getShadow(1)) {
				drawString(styledStringLine.getText(), x - GraphicsConstants.PIXEL_SIZE_X, y - GraphicsConstants.PIXEL_SIZE_Y + lineYoff, w, h);
			}
			if (styledStringLine.style().getShadow(3)) {
				drawString(styledStringLine.getText(), x + GraphicsConstants.PIXEL_SIZE_X, y - GraphicsConstants.PIXEL_SIZE_Y + lineYoff, w, h);
			}
			if (styledStringLine.style().getShadow(5)) {
				drawString(styledStringLine.getText(), x + GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y + lineYoff, w, h);
			}
			if (styledStringLine.style().getShadow(7)) {
				drawString(styledStringLine.getText(), x - GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y + lineYoff, w, h);
			}
			
			
			

			// drawText
			textColor(styledStringLine.style().getTextColor());
			drawString(styledStringLine.getText(), x, y + lineYoff, w, h);

			
			
			
			// drawUnderline

			if (styledStringLine.style().getUnderline() != null) {
				int textWidth = usingFont.getTextWidth(styledStringLine.getText()) * GraphicsConstants.PIXEL_SIZE_X + (styledStringLine.style().getAdditionalUnderlineLength() * GraphicsConstants.PIXEL_SIZE_X);
				int alignOff = (styledStringLine.style().getHorizontalTextAlign() == CENTER ? -styledStringLine.getWidth() * GraphicsConstants.PIXEL_SIZE_X / 2 : (styledStringLine.style().getHorizontalTextAlign() == RIGHT ? -styledStringLine.getWidth() * GraphicsConstants.PIXEL_SIZE_X	: 0));
				int xoff = alignOff - (styledStringLine.style().getAdditionalUnderlineLength() * GraphicsConstants.PIXEL_SIZE_X);
				int yoff = 0;
				while (xoff < textWidth + alignOff) {
					for (int i = 0; i < styledStringLine.style().getUnderline().underlineWidth; i++) {
						for (int j = 0; j < styledStringLine.style().getUnderline().underlineHeight; j++) {
							if (Color.isTransparent(styledStringLine.style().getUnderline().underlineData[i][j])) {
								continue;
							}

							if (styledStringLine.style().getUnderline().getColorMode() == Underline.MONOCHROME) {
								Painter.drawPoint(x + xoff, y + yoff + (usingFont.getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y) + GraphicsConstants.PIXEL_SIZE_Y + (j * GraphicsConstants.PIXEL_SIZE_Y), styledStringLine.style().getUnderlineColor());
							} else if (styledStringLine.style().getUnderline().getColorMode() == Underline.GRAY_SCALE) {
								Painter.drawPoint(x + xoff,	y + yoff + (usingFont.getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y) + GraphicsConstants.PIXEL_SIZE_Y + (j * GraphicsConstants.PIXEL_SIZE_Y), styledStringLine.style().getGrayStyle().getUnderline().underlineData[i][j]);
							} else if (styledStringLine.style().getUnderline().getColorMode() == Underline.POLYCHROME) {
								Painter.drawPoint(x + xoff,	y + yoff + (usingFont.getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y) + GraphicsConstants.PIXEL_SIZE_Y + (j * GraphicsConstants.PIXEL_SIZE_Y), styledStringLine.style().getUnderline().underlineData[i][j]);
							}
						}
						xoff += GraphicsConstants.PIXEL_SIZE_X;
						if (xoff >= textWidth) {
							break;
						}
					}
				}
				yoff += styledStringLine.getHeight() + usingFont.lineSpacing;
			}
			lineYoff += styledString.getNativeHeight() * GraphicsConstants.PIXEL_SIZE_Y + usingFont.lineSpacing * GraphicsConstants.PIXEL_SIZE_Y;
		}
		

		// Font wird resetet
		textFont(globalFont);

		// V ^ Reihenfolge scheint egal zu sein
		
		// Spacings werden resetet
		charSpacing(globalCharSpacing);
		wordSpacing(globalWordSpacing);
		lineSpacing(globalLineSpacing);
	}

	
	
	
	
	
	
	
	
	public static void drawLines(String[] lines, float x, float y, Color textColor) {
		Color globalColor = getTextColor();
		textColor(textColor);
		
		drawLines(lines, x, y);
		
		textColor(globalColor);
	}
	
	
	public static void drawLines(String[] lines, float x, float y) {
		String linesAsText = "";
		
		for(int i = 0; i < lines.length-1; i++) {
			linesAsText += lines[i] + "\n";
		}
		linesAsText += lines[lines.length-1];
		
		drawString(linesAsText, x, y);
	}
	
	
	
	
	
	
	
	public static void drawLines(String[] lines, float x, float y, int startLine, int endLine, Color textColor) {
		Color globalColor = getTextColor();
		textColor(textColor);
		
		drawLines(lines, x, y, startLine, endLine);
		
		textColor(globalColor);
	}
	
	
	public static void drawLines(String[] lines, float x, float y, int startLine, int endLine) {
		if(lines.length == 0 || startLine > lines.length || startLine > endLine || startLine < 0 || endLine < 1) {
			return;
		}
		
		if(endLine > lines.length) {
			endLine = lines.length;
		}
		
		String linesAsText = "";
		
		for(int i = startLine; i < endLine-1; i++) {
			linesAsText += lines[i] + "\n";
		}
		linesAsText += lines[endLine-1];
		
		drawString(linesAsText, x, y);
	}
	
	
	
	
	
	
	
	// Misc

	
	
	
	public static String getLongestString(ArrayList<String> list) {
		int strLength = 0;
		int counter = 0;
		int index = 0;
		for (String str : list) {
			if (TextHandler.font().getTextWidth(str) > strLength) {
				strLength = TextHandler.font().getTextWidth(str);
				index = counter;
			}
			counter++;
		}
		return list.get(index);
	}

	public static String getLongestString(String... list) {
		int strLength = 0;
		int index = 0;
		for (int i = 0; i < list.length; i++) {
			if (TextHandler.font().getTextWidth(list[i]) > strLength) {
				strLength = TextHandler.font().getTextWidth(list[i]);
				index = i;
			}
		}
		return list[index];
	}

	public static StyledString getLongestStyledString(ArrayList<StyledString> list) {
		int strLength = 0;
		int counter = 0;
		int index = 0;
		for (StyledString str : list) {
			if (str.getWidth() > strLength) {
				strLength = str.getWidth();
				index = counter;
			}
			counter++;
		}
		return list.get(index);
	}

	public static StyledString getLongestStyledString(StyledString[] list) {
		int strLength = 0;
		int index = 0;
		for (int i = 0; i < list.length; i++) {
			if (list[i].getWidth() > strLength) {
				strLength = list[i].getWidth();
				index = i;
			}
		}
		return list[index];
	}

}
