package gui;


import graphics.GraphicsConstants;
import graphics.colors.Palette;
import input.KeyHandler;
import textHandler.TextHandler;


public class NumberField extends TextArea {

	private int maxChars = -1;

	public NumberField(int X, int Y) {
		super(X, Y, 25 * GraphicsConstants.PIXEL_SIZE_X, TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y));
	}

	public NumberField(int X, int Y, int MaxChars) {
		this(X, Y);
		this.maxChars = MaxChars;
	}

	
	
	
	public void setMaxChars(int maxChars) {
		this.maxChars = maxChars;
	}

	
	
	
	public NumberField setText(String text) {
		String str = text.replaceAll("\n", "");
		if (maxChars != -1 && str.length() > maxChars) {
			str = str.substring(0, maxChars - 1);
		}
		super.setText(str);
		return this;
	}

	
	
	
	public NumberField setSize(int w, int h) {
		return this;
	}
	
	
	

	public NumberField setWidth(int w) {
		super.setWidth(w);
		return this;
	}

	
	
	
	public NumberField setHeight(int h) {
		return this;
	}

	
	
	
	public NumberField setPalette(Palette palette) {
		super.setPalette(palette);
		return this;
	}


	
	
	
	private void testKey() {
		if (super.isFocused()) {
			/*if (KeyHandler.lastKey == KeyEvent.VK_BACK_SPACE) { TODO workaround
				super.removeChar();
			} else if (KeyHandler.lastKey == KeyEvent.VK_LEFT || KeyHandler.lastKey == KeyEvent.VK_RIGHT) {
				this.moveTextCursor();
			} else if (KeyHandler.lastKey >= 44 && KeyHandler.lastKey <= 46 || KeyHandler.lastKey >= 48 && KeyHandler.lastKey <= 57
					|| KeyHandler.lastKey >= 65 && KeyHandler.lastKey <= 90 || KeyHandler.lastKey == ' ' || KeyHandler.lastKey == '?' || KeyHandler.lastKey == 520
					|| KeyHandler.lastKey == 521 || KeyHandler.lastKey == 153 || KeyHandler.lastKey == 'ü' || KeyHandler.lastKey == 'ö' || KeyHandler.lastKey == 'ä') {

				if (maxChars == -1 || (this.text + this.pufferText).length() < maxChars) {
					super.write((short) KeyHandler.lastKey);
				}
			}*/
		}
	}
	
	
	
	
	
	private void moveTextCursor() {
		if (KeyHandler.lastKey == TextHandler.LEFT && super.getText().length() > 0) {
			pufferText = super.getText().substring(super.getText().length() - 1, super.getText().length()) + pufferText;
			super.setText(super.getText().substring(0, super.getText().length() - 1));
		} else if (KeyHandler.lastKey == TextHandler.RIGHT && pufferText.length() > 0) {
			text += pufferText.substring(0, 1);
			pufferText = pufferText.substring(1, pufferText.length());
		}
	}
	
	
	

	public void draw() {
		super.draw();
	}

	public void handleMouseClick() {
		super.handleMouseClick();
	}
}
