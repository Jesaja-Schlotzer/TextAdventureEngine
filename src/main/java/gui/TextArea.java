package gui;

import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Palette;
import input.KeyHandler;
import input.KeyPredicate;
import input.MouseHandler;
import input.TextSupplierFactory;
import org.lwjgl.glfw.GLFW;
import textHandler.TextHandler;

import java.util.function.BooleanSupplier;


public class TextArea {
	private int x, y, w, h;
	protected String text = "";
	protected String pufferText = "";
	
	private boolean limitTextInsideBounds;
	
	private boolean isFocused, locked;
	private int focusCount;
	
	private Palette textAreaPalette = Palette.STANDARD_PALETTE;
	private Layout<TextArea> textAreaLayout = STANDARD_LAYOUT;
	
	
	
	public TextArea(int x, int y, int w, int h) {
		setPos(x, y);
		setWidth(Math.max(w, 7 * GraphicsConstants.PIXEL_SIZE_X));
		setHeight(this.h = Math.max(h, (TextHandler.font().getCharHeight() + 4) * GraphicsConstants.PIXEL_SIZE_Y));
	}

	
	
	
	public TextArea lock() {
		locked = true;
		return this;
	}

	
	
	public TextArea unlock() {
		locked = false;
		return this;
	}
	
	
	
	
	public TextArea addTextSupplier() {
		TextSupplierFactory.getInstance().addSupplier(this::isFocused, KeyPredicate.KEY_PREDICATE_ALL_PRINTABLE_CHARS.andThen(KeyPredicate.KEY_PREDICATE_BACKSPACE) ,this::appendText);
		
		return this;
	}
	
	public TextArea addTextSupplier(BooleanSupplier extraCondition) {
		TextSupplierFactory.getInstance().addSupplier(() -> extraCondition.getAsBoolean() && this.isFocused, KeyPredicate.KEY_PREDICATE_ALL_PRINTABLE_CHARS.andThen(KeyPredicate.KEY_PREDICATE_BACKSPACE) ,this::appendText);
		
		return this;
	}
	
	
	
	public TextArea addTextSupplier(KeyPredicate keyPredicate) {
		TextSupplierFactory.getInstance().addSupplier(this::isFocused, keyPredicate ,this::appendText);
		
		return this;
	}
	
	
	
	public TextArea addTextSupplier(BooleanSupplier extraCondition, KeyPredicate keyPredicate) {
		TextSupplierFactory.getInstance().addSupplier(() -> extraCondition.getAsBoolean() && this.isFocused, keyPredicate ,this::appendText);
		
		return this;
	}
	
	
	
	
	
	// WENN erst false dann true == text wird momentan nicht gekürzt
	public TextArea setLimitTextInsideBounds(boolean limitTextInsideBounds) {
		this.limitTextInsideBounds = limitTextInsideBounds;
		
		return this;
	}
	
	
	
	public void setFocus(boolean focus) {
		if(locked || focus == this.isFocused) {
			return;
		}
		
		this.isFocused = focus;
	}
	
	
	
	
	
	public TextArea setText(String text) {
		if(limitTextInsideBounds) {
			if(TextHandler.font().doesTextFitInBounds(text, w, h)) {
				this.text = text;
			}else {
				for(int i = 0; i < text.length(); i++){
					appendText(text.charAt(i)+"");
				}
			}
		}else {
			this.text = text;
		}
		
		return this;
	}

	
	
	public TextArea appendText(String str) {
		if(limitTextInsideBounds) {
			if(TextHandler.font().doesTextFitInBounds(this.text+this.pufferText+str, w-4*GraphicsConstants.PIXEL_SIZE_X, h-2*GraphicsConstants.PIXEL_SIZE_Y)) {
				this.text += str;
			}else {
				for(int i = 0; i < str.length(); i++){
					if(TextHandler.font().doesTextFitInBounds(this.text+this.pufferText+str.charAt(i), w-4*GraphicsConstants.PIXEL_SIZE_X, h-2*GraphicsConstants.PIXEL_SIZE_Y)) {
						this.text += str.charAt(i);
					}else {
						break;
					}
				}
			}
		}else {
			this.text += str;
		}
		
		return this;
	}
	
	
	
	public TextArea appendText(char chr) {
		if(KeyPredicate.KEY_PREDICATE_BACKSPACE.test((short) chr)) {
			removeChar();
			
			return this;
		}
		
		appendText(chr+"");
		
		return this;
	}
	
	
	
	
	
	public TextArea setBounds(int x, int y, int w, int h) {
		setPos(x, y);
		setSize(w, h);
		
		return this;
	}
	
	
	
	public TextArea setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		return this;
	}
	
	
	
	
	public TextArea setSize(int w, int h) {
		w = w / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		h = h / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		this.w = Math.max(w, 7 * GraphicsConstants.PIXEL_SIZE_X);
		this.h = Math.max(h, 9 * GraphicsConstants.PIXEL_SIZE_Y);
		return this;
	}
	
	

	public TextArea setWidth(int w) {
		w = w / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		
		this.w = Math.max(w, 7 * GraphicsConstants.PIXEL_SIZE_X);
		return this;
	}

	
	
	
	public TextArea setHeight(int h) {
		h = h / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		this.h = Math.max(h, 9 * GraphicsConstants.PIXEL_SIZE_Y);
		return this;
	}
	
	
	
	
	
	
	
	
	public TextArea setPalette(Palette palette) {
		if(palette != null) {
			this.textAreaPalette = palette;
		}
		
		return this;
	}

	
	
	public Palette getPalette() {
		return textAreaPalette;
	}
	
	
	
	
	
	
	
	public TextArea setLayout(Layout<TextArea> layout) {
		if(layout != null) {
			this.textAreaLayout = layout;
		}
		
		return this;
	}
	
	
	
	

	
	public String getText() {
		return text + pufferText;
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
	
	
	
	public boolean isFocused() {
		return isFocused;
	}
	
	
	
	
	public void handleKeyInput(short keyCode) {
		if(locked) {
			return;
		}
		
		if (this.isFocused) {
			
			if (KeyPredicate.KEY_PREDICATE_BACKSPACE.test(keyCode)) {//keyCode == KeyEvent.VK_BACK_SPACE) {
				this.removeChar();
			} else if (KeyPredicate.KEY_PREDICATE_ARROW_BUTTONS.test(keyCode)) {//keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_UP	|| keyCode == KeyEvent.VK_DOWN) {
				this.moveTextCursor((short) KeyHandler.lastKey);
			} else if (KeyPredicate.KEY_PREDICATE_ONLY_ALPHABET.test(keyCode) ||
					keyCode == GLFW.GLFW_KEY_ENTER || keyCode >= 44 && keyCode <= 46 || keyCode >= 48 && keyCode <= 57
					|| keyCode >= 65 && keyCode <= 90 || keyCode == ' ' || keyCode == '?' || keyCode == 520
					|| keyCode == 521 || keyCode == 153 || keyCode == 'ü' || keyCode == 'ö' || keyCode == 'ä') {

				this.write(keyCode);
			}
		}
	}

	
	
	
	
	private void moveTextCursor(short keyCode) {
		if (keyCode == GLFW.GLFW_KEY_LEFT && text.length() > 0) {
			pufferText = text.substring(text.length() - 1, text.length()) + pufferText;
			text = text.substring(0, text.length() - 1);
		} else if (keyCode == GLFW.GLFW_KEY_RIGHT && pufferText.length() > 0) {
			text += pufferText.substring(0, 1);
			pufferText = pufferText.substring(1, pufferText.length());
		} else if (keyCode == GLFW.GLFW_KEY_UP
				&& TextHandler.font().countLines(text, w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy()) - 1 > 0) {
			int cursorXoff = TextHandler.font()
					.getTextWidth(TextHandler.font().getLines(text,
							w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy())[TextHandler.font().countLines(text,
									w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy()) - 1])
					* GraphicsConstants.PIXEL_SIZE_X + (3 * GraphicsConstants.PIXEL_SIZE_X);
			int cursorLineIndex = TextHandler.font().countLines(text, w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy()) - 1;

			String cursorLine = TextHandler.font().getLines(text,
					w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy())[cursorLineIndex];
			String upperLine = TextHandler.font().getLines(text,
					w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy())[cursorLineIndex - 1];

			String subStr = "";
			String pufferSubStr = "";
			boolean finished = false;
			for (int i = 0; i < upperLine.length(); i++) {
				subStr += upperLine.charAt(i);

				if (finished) {
					pufferSubStr += upperLine.charAt(i);
				}

				if ((TextHandler.font().getTextWidth(subStr) * GraphicsConstants.PIXEL_SIZE_X
						+ (3 * GraphicsConstants.PIXEL_SIZE_X)) >= cursorXoff) {
					finished = true;
				}
			}

			pufferText = pufferSubStr + cursorLine + pufferText;
			text = text.substring(0, text.length() - (cursorLine.length() + pufferSubStr.length()));

		} else if (keyCode == GLFW.GLFW_KEY_UP
				&& TextHandler.font().countLines(text + "i", w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy()) - 1 > 0) { // textCursor
			// befindet
			// sich
			// genau
			// am
			// Anfang
			// der
			// zweiten
			// Zeile
			pufferText = text + pufferText;
			text = "";

		} else if (keyCode == GLFW.GLFW_KEY_DOWN) {
			// if(text.length() == 0){
			// if(TextHandler.font().countLines(pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X)) > 0){

			// }else if(TextHandler.font().countLines(pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X)) <= 1){
			// text = TextHandler.font().getLines(pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))[0];
			// pufferText = text + pufferText;
			// }
			// return;
			// }

			//// int cursorXoff =
			//// TextHandler.font().getTextWidth(TextHandler.font().getLines(text,
			//// w-(4*GraphicsConstants.PIXEL_SIZE_X))[TextHandler.font().countLines(text,
			//// w-(4*GraphicsConstants.PIXEL_SIZE_X))-1]) * GraphicsConstants.PIXEL_SIZE_X
			//// + (3*GraphicsConstants.PIXEL_SIZE_X);
			// int cursorLineIndex = TextHandler.font().countLines(text,
			//// w-(4*GraphicsConstants.PIXEL_SIZE_X))-1;

			// String cursorLine = TextHandler.font().getLines(text,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))[cursorLineIndex];
			// int cursorXoff =
			// TextHandler.font().getTextWidth(TextHandler.font().getLines(text,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))[TextHandler.font().countLines(text,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))-1]) * GraphicsConstants.PIXEL_SIZE_X +
			// (3*GraphicsConstants.PIXEL_SIZE_X);

			// if(TextHandler.font().countLines(cursorLine+"i"+pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))-1 > 0){

			// }

			// String lowerLine = TextHandler.font().getLines(cursorLine+"i"+pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))[1];
			// println(TextHandler.font().countLines(cursorLine+"i"+pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))-1,
			// TextHandler.font().getLines(cursorLine+"i"+pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))[0]+"\n"+TextHandler.font().getLines(cursorLine+"i"+pufferText,
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))[1]);
			// String subStr = "";
			// String pufferSubStr = "";
			// boolean finished = false;
			// for(int i = 0; i < lowerLine.length(); i++){
			// subStr += lowerLine.charAt(i);

			// if(finished){
			// pufferSubStr += lowerLine.charAt(i);
			// }

			// if((TextHandler.font().getTextWidth(subStr) * GraphicsConstants.PIXEL_SIZE_X
			// +
			// (3*GraphicsConstants.PIXEL_SIZE_X)) >= cursorXoff){
			// finished = true;
			// }
			// }

			// pufferText = pufferSubStr + cursorLine + pufferText;
			// text = text.substring(0, text.length() - (cursorLine.length() +
			// pufferSubStr.length()));

		} // else if(keyCode == DOWN && TextHandler.font().countLines(text+"i",
			// w-(4*GraphicsConstants.PIXEL_SIZE_X))-1 > 0){ // textCursor befindet sich
			// genau in der nächsten
			// Zeile
			// pufferText = text + pufferText;
			// text = "";
			// }
	}

	
	
	
	protected void write(short keyCode) {
		if(limitTextInsideBounds && !TextHandler.font().doesTextFitInBounds(text+pufferText+(char) keyCode, w, h)) {
			return;
		}
		
		text += (char) keyCode;
		
		/* Müsste unnötig sein
		 * 
		if (TextHandler.font().getTextHeight(text + (pufferText.length() > 0 ? "i" + pufferText : "") + ((char)keyCode),
				w - (4 * GraphicsConstants.PIXEL_SIZE_X), TextHandler.getWrapWordPolicy())
				* GraphicsConstants.PIXEL_SIZE_X <= h - (2 * GraphicsConstants.PIXEL_SIZE_Y)) {
			text += (char) keyCode;
		}
		*/
	}

	
	
	
	protected void removeChar() {
		if (text.length() > 0) {
			text = text.substring(0, text.length() - 1);
		}
	}

	
	
	
	
	
	
	public void draw() {
		if(locked) {
			textAreaLayout.drawLocked(this);
		}else {
			if (isFocused) {
				textAreaLayout.drawFocused(this);
			}else {
				textAreaLayout.drawIdle(this);
			}
		}
	}
	
	
	

	public void handleMouseClick() {
		if(locked) {
			return;
		}
		
		
		
		if (isFocused && MouseHandler.overButton(x, y, x + w, y + h)) { // TODO textCursor auf Mausposition setzten
			
			return;
		}
		
		
		
		if (MouseHandler.overButton(x, y, x + w, y + h)) {
			isFocused = true;
		} else {
			isFocused = false;
			focusCount = 0;
		}
	}
	
	
	
	
	
	
	
	public static Layout<TextArea> STANDARD_LAYOUT = new LayoutAdapter<TextArea>() {
		
		@Override
		public void drawIdle(TextArea ta) {
			Painter.drawRect(ta.x, ta.y, ta.w, ta.h, ta.textAreaPalette.getColor(4), ta.textAreaPalette.getColor(2));
			TextHandler.drawString(ta.text + ta.pufferText, ta.x + (2 * GraphicsConstants.PIXEL_SIZE_X), ta.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), ta.w - (4 * GraphicsConstants.PIXEL_SIZE_X), ta.h - (4 * GraphicsConstants.PIXEL_SIZE_Y), ta.textAreaPalette.getColor(0));
		}
		
		
		
		
		@Override
		public void drawFocused(TextArea ta) {
			Painter.drawRect(ta.x, ta.y, ta.w, ta.h, ta.textAreaPalette.getColor(4), ta.textAreaPalette.getColor(1));

			ta.focusCount++;
			if (ta.focusCount >= 25) {
				TextHandler.drawString(ta.text + "i" + ta.pufferText, ta.x + (2 * GraphicsConstants.PIXEL_SIZE_X), ta.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), ta.w - (4 * GraphicsConstants.PIXEL_SIZE_X), ta.h - (4 * GraphicsConstants.PIXEL_SIZE_Y), ta.textAreaPalette.getColor(0));
				if (ta.focusCount > 45) {
					ta.focusCount = 0;
				}
			} else {
				TextHandler.drawString(ta.text + TextHandler.ONE_SPACE + ta.pufferText, ta.x + (2 * GraphicsConstants.PIXEL_SIZE_X), ta.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), ta.w - (4 * GraphicsConstants.PIXEL_SIZE_X), ta.h - (4 * GraphicsConstants.PIXEL_SIZE_Y), ta.textAreaPalette.getColor(0));
			}
		}
		
		
		
		@Override
		public void drawLocked(TextArea ta) {
			Painter.drawRect(ta.x, ta.y, ta.w, ta.h, ta.textAreaPalette.getColor(4).asGray(), ta.textAreaPalette.getColor(2).asGray());
			TextHandler.drawString(ta.text + ta.pufferText, ta.x + (2 * GraphicsConstants.PIXEL_SIZE_X), ta.y + (2 * GraphicsConstants.PIXEL_SIZE_Y), ta.w - (4 * GraphicsConstants.PIXEL_SIZE_X), ta.h - (4 * GraphicsConstants.PIXEL_SIZE_Y), ta.textAreaPalette.getColor(0).asGray());

		}
	};
}
