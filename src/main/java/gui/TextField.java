package gui;

import graphics.GraphicsConstants;
import graphics.colors.Palette;
import input.KeyHandler;
import org.lwjgl.glfw.GLFW;
import textHandler.TextHandler;


public class TextField extends TextArea {

	private int maxChars = -1;
	private boolean limitTextToFieldSize;
	
	

	public TextField(int X, int Y) {
		super(X, Y, 25 * GraphicsConstants.PIXEL_SIZE_X, TextHandler.font().getCharHeight() * GraphicsConstants.PIXEL_SIZE_Y + (4 * GraphicsConstants.PIXEL_SIZE_Y));
	}

	
	public TextField(int X, int Y, int MaxChars) {
		this(X, Y);
		this.maxChars = MaxChars;
	}
	
	
	public TextField(int X, int Y, boolean limitTextToFieldSize) {
		this(X, Y);
		this.limitTextToFieldSize = limitTextToFieldSize;
	}

	
	
	
	public void setMaxChars(int maxChars) {
		this.maxChars = maxChars;
	}
	
	
	public void setLimitTextToFieldSize(boolean limitTextToFieldSize) {
		this.limitTextToFieldSize = limitTextToFieldSize;
	}
	
	
	
	public TextField setText(String text) {
		String str = text.replaceAll("\n", "");
		
		if (maxChars != -1 && str.length() > maxChars) {
			str = str.substring(0, maxChars - 1);
		}
		
		if(limitTextToFieldSize && TextHandler.font().getTextWidth(str) > (super.getW() - 6*GraphicsConstants.PIXEL_SIZE_X)) {
			String tempText = "";
			
			for(int i = 0; i < str.length(); i++) {
				if(TextHandler.font().getTextWidth(tempText + text.charAt(i)) > (super.getW() - 6*GraphicsConstants.PIXEL_SIZE_X)) {
					break;
				}
				tempText += text.charAt(i);
			}
			str = tempText;
		}
		
		super.setText(str);
		
		return this;
	}

	
	
	
	public TextField setSize(int w, int h) {
		super.setSize(w, h);
		return this;
	}
	
	
	

	public TextField setWidth(int w) {
		super.setWidth(w);
		return this;
	}

	
	
	
	public TextField setHeight(int h) {
		super.setHeight(h);
		return this;
	}

	
	
	
	public TextField setPalette(Palette palette) {
		super.setPalette(palette);
		return this;
	}


	
	
	
	public void handleKeyInput(short keyCode) {
		if (super.isFocused()) {
			if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
				super.removeChar();
			} else if (keyCode == GLFW.GLFW_KEY_LEFT || keyCode == GLFW.GLFW_KEY_RIGHT) {
				this.moveTextCursor();
			} else if (keyCode >= 44 && keyCode <= 46 || keyCode >= 48 && keyCode <= 57
					|| keyCode >= 65 && keyCode <= 90 || keyCode == ' ' || keyCode == '?' || keyCode == 520
					|| keyCode == 521 || keyCode == 153 || keyCode == 'ü' || keyCode == 'ö' || keyCode == 'ä') {

				if (maxChars == -1 || (this.text + this.pufferText).length() < maxChars) {
					if(limitTextToFieldSize && TextHandler.font().getTextWidth(super.getText() + (char) keyCode) > (super.getW()/GraphicsConstants.PIXEL_SIZE_X - 6)) {
						// Hier is nur leer weil's so besser zu lesen ist
					}else {
						super.write(keyCode);
					}
				}
			}
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
	
	
	
	/*
	public void draw() {
		super.draw();
	}
	
	
	
	public void handleMouseClick() {
		super.handleMouseClick();
	}
	*/
}
