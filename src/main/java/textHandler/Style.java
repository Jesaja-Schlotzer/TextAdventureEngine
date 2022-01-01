package textHandler;

import graphics.colors.Color;
import graphics.colors.ColorUtils;

public class Style {
	
	// Standard-Styles

	public static final Style STANDARD_STYLE = new Style();
	public static final Style STANDARD_HEADER_STYLE = new Style().setUnderline(new Underline(Underline.NORMAL_LOWER_UNDERLINE).setColorMode(Underline.MONOCHROME));
	public static final Style STANDARD_UNDERLINED = new Style().setUnderline(new Underline(Underline.NORMAL_UNDERLINE).setColorMode(Underline.MONOCHROME));
															// evtl mit new Underline(NORMAL_UNDERLINE).setCo... weil Ref
	
	
	
	
	
	private int localCharSpacing = -1;
	private int localWordSpacing = -1;
	private int localLineSpacing = -1;

	private int horizontalTextAlign = -1;
	private int vertikalTextAlign = -1;

	private Underline underline;
	private int additionalUnderlineLength;

	private Font font;

	private byte shadow;

	private Color textColor, shadowColor, underlineColor;

	
	
	// Normaler Konstruktor
	public Style() {
		textColor = Color.BLACK;
	}

	
	
	// "return/copy"- Konstruktor
	public Style(Style style) { // Neue Attribute hier hinzufügen !!!
		this.localCharSpacing = style.localCharSpacing;
		this.localWordSpacing = style.localWordSpacing;
		this.localLineSpacing = style.localLineSpacing;

		this.horizontalTextAlign = style.horizontalTextAlign;
		this.vertikalTextAlign = style.vertikalTextAlign;

		this.underline = style.underline;
		this.additionalUnderlineLength = style.additionalUnderlineLength;

		this.font = style.font;

		this.shadow = style.shadow;

		this.textColor = style.textColor;
		this.shadowColor = style.shadowColor;
		this.underlineColor = style.underlineColor;
	}

	
	
	
	
	// get-Special-Methoden

	public Style getGrayStyle() {
		Style grayStyle = new Style(this);

		if (this.underline != null) {
			grayStyle.underline = new Underline(this.underline).setColorMode(Underline.GRAY_SCALE);
		}

		grayStyle.textColor = this.textColor.asGray();
		grayStyle.shadowColor = this.shadowColor.asGray();
		grayStyle.underlineColor = this.underlineColor.asGray();

		return grayStyle;
	}

	
	
	
	
	
	// Set-Methoden
	
	
	// --------------------------------------------------SET-Spacings--------------------------------------------------
	public Style setSpacings(int charSpacing, int wordSpacing, int lineSpacing) {
		setCharSpacing(charSpacing);
		setWordSpacing(wordSpacing);
		setLineSpacing(lineSpacing);
		return this;
	}
	

	
	public Style setCharSpacing(int charSpacing) {
		localCharSpacing = charSpacing;
		return this;
	}

	
	
	public Style setWordSpacing(int wordSpacing) {
		localWordSpacing = wordSpacing;
		return this;
	}

	
	
	public Style setLineSpacing(int lineSpacing) {
		localLineSpacing = lineSpacing;
		return this;
	}

	
	
	
	
	// --------------------------------------------------SET-Align--------------------------------------------------
	public Style setAlign(int align) {
		if (align == TextHandler.LEFT || align == TextHandler.CENTER || align == TextHandler.RIGHT) {
			this.horizontalTextAlign = align;
		}
		this.vertikalTextAlign = TextHandler.TOP;
		return this;
	}

	
	
	public Style setAlign(int horiAlign, int vertAlign) {
		if (horiAlign == TextHandler.LEFT || horiAlign == TextHandler.CENTER || horiAlign == TextHandler.RIGHT) {
			this.horizontalTextAlign = horiAlign;
		}
		if (vertAlign == TextHandler.TOP || vertAlign == TextHandler.CENTER || vertAlign == TextHandler.BOTTOM) {
			this.vertikalTextAlign = vertAlign;
		}
		return this;
	}

	
	
	
	
	// --------------------------------------------------SET-Underline--------------------------------------------------
	public Style setUnderline(Underline underline) {
		this.underline = underline;
		return this;
	}
	
	

	public Style setUnderline(Underline underline, int colorMode) {
		this.underline = underline;
		this.underline.setColorMode(colorMode);
		return this;
	}

	
	
	public Style setUnderline(String underlinePath) {
		this.underline = new Underline(underlinePath);
		return this;
	}
	
	

	public Style setUnderline(int colorMode, String underlinePath) {
		this.underline = new Underline(underlinePath);
		this.underline.setColorMode(colorMode);
		return this;
	}

	
	
	public Style setAdditionalUnderlineLength(int additionalUnderlineLength) {
		this.additionalUnderlineLength = additionalUnderlineLength;
		return this;
	}

	
	
	
	
	// --------------------------------------------------SET-Font--------------------------------------------------
	public Style setFont(Font font) {
		this.font = font;
		return this;
	}

	
	
	
	// --------------------------------------------------SET-Shadow--------------------------------------------------
	public Style setShadow(boolean left, boolean leftUp, boolean up, boolean upRight, boolean right, boolean rightDown,
			boolean down, boolean downLeft) {
		String bools = (left ? 1 : 0) + "" + (leftUp ? 1 : 0) + "" + (up ? 1 : 0) + "" + (upRight ? 1 : 0) + ""
				+ (right ? 1 : 0) + "" + (rightDown ? 1 : 0) + "" + (down ? 1 : 0) + "" + (downLeft ? 1 : 0);
		setShadow(bools);
		return this;
	}

	
	
	public Style setShadow(String bool) {
		if (bool.length() < 8) {
			for (int i = 0; i < 8 - bool.length(); i++) {
				bool += "0";
			}
		} else if (bool.length() > 8) {
			bool = bool.substring(0, 7);
		}
		shadow = (byte) Integer.parseInt(bool, 2);
		return this;
	}

	
	
	public Style setShadow(byte shadow) {
		this.shadow = shadow;
		
		return this;
	}

	
	
	
	
	// --------------------------------------------------SET-Colors--------------------------------------------------
	public Style setColors(Color textColor, Color shadowColor, Color underlineColor) {
		this.textColor = textColor;
		this.shadowColor = shadowColor;
		this.underlineColor = underlineColor;
		return this;
	}

	
	
	public Style setTextColor(Color textColor) {
		this.textColor = textColor;
		return this;
	}

	
	
	public Style setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
		return this;
	}

	
	
	public Style setUnderlineColor(Color underlineColor) {
		this.underlineColor = underlineColor;
		return this;
	}

	
	
	
	
	
	// Get-Methoden

	
	// --------------------------------------------------GET-Font--------------------------------------------------
	public Font getFont() {
		return this.font;
	}

	
	
	
	
	// --------------------------------------------------GET-Shadow--------------------------------------------------
	public boolean getShadow(int index) {
		return ColorUtils.decToBin(shadow, 8).substring(index, index + 1).equals("1");
	}

	
	
	public boolean[] getShadows() {
		boolean[] shadowsOn = new boolean[8];
		for (int i = 0; i < shadowsOn.length; i++) {
			shadowsOn[i] = getShadow(i);
		}
		return shadowsOn;
	}


	
	
	
	// --------------------------------------------------GET-Spacings--------------------------------------------------
	public int getLocalCharSpacing() {
		return localCharSpacing;
	}



	public int getLocalWordSpacing() {
		return localWordSpacing;
	}



	public int getLocalLineSpacing() {
		return localLineSpacing;
	}


	
	
	// --------------------------------------------------GET-Align--------------------------------------------------
	public int getHorizontalTextAlign() {
		return horizontalTextAlign;
	}



	public int getVertikalTextAlign() {
		return vertikalTextAlign;
	}



	
	
	// --------------------------------------------------GET-Underline--------------------------------------------------
	public Underline getUnderline() {
		return underline;
	}



	public int getAdditionalUnderlineLength() {
		return additionalUnderlineLength;
	}


	
	
	
	// --------------------------------------------------GET-Colors--------------------------------------------------
	public Color getTextColor() {
		return textColor;
	}



	public Color getShadowColor() {
		return shadowColor;
	}



	public Color getUnderlineColor() {
		return underlineColor;
	}

}
