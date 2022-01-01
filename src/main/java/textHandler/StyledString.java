package textHandler;

import java.util.ArrayList;


public class StyledString { // Momentan wird immer am Wort getrennt
	private String text;
	private Style style;
	private Style alternativeStyle;

	
	
	
	public StyledString(StyledString ss) {
		this.text = ss.text;
		this.style = new Style(ss.style);
		this.alternativeStyle = new Style(ss.alternativeStyle);
	}
	
	
	
	public StyledString(String text) {
		this.text = text;
		this.style = Style.STANDARD_STYLE;
	}
	
	

	public StyledString(String text, Style style) {
		this.text = text;
		this.style = (style == null ? Style.STANDARD_STYLE : style);
	}
	
	

	public StyledString(String text, Style style, Style alternativeStyle) {
		this.text = text;
		this.style = (style == null ? Style.STANDARD_STYLE : style);
		this.alternativeStyle = alternativeStyle;
	}
	
	
	

	
	// get-Special-Methoden
	public StyledString getAlternativeStyledString() {
		if (alternativeStyle != null) {
			return new StyledString(text, alternativeStyle);
		} else {
			return new StyledString(text, style);
		}
	}
	
	

	public StyledString getGrayStyledString() {
		if (alternativeStyle == null) {
			return new StyledString(text, style.getGrayStyle());
		} else {
			return new StyledString(text, style.getGrayStyle(), alternativeStyle.getGrayStyle());
		}
	}
	
	
	
	

	// Set-Methoden

	
	// --------------------------------------------------SET-Text--------------------------------------------------
	public StyledString setText(String text) {
		this.text = text;
		return this;
	}

	
	
	
	// --------------------------------------------------SET-Style--------------------------------------------------
	public StyledString setStyle(Style style) {
		this.style = (style == null ? Style.STANDARD_STYLE : style);
		return this;
	}
	

	public StyledString setAlternativeStyle(Style alternativeStyle) {
		this.alternativeStyle = alternativeStyle;
		return this;
	}

	
	
	
	
	// Get-Methoden

	
	// --------------------------------------------------GET-Text--------------------------------------------------
	public String getText() {
		return text;
	}

	
	
	
	// --------------------------------------------------GET-Style--------------------------------------------------
	public Style style() {
		return style;
	}
	

	public Style alternativeStyle() {
		return alternativeStyle;
	}
	
	
	

	// --------------------------------------------------GET-Width/Height--------------------------------------------------
	public int getWidth() {
		int styledStringWidth = 0;

		// global Font wird gespeichert
		Font globalFont = TextHandler.font();
		if (style.getFont() != null) {
			TextHandler.textFont(style.getFont());
		}

		// globale Spacings werden gespeichert
		int globalCharSpacing = TextHandler.getCharSpacing();
		int globalWordSpacing = TextHandler.getWordSpacing();
		int globalLineSpacing = TextHandler.getLineSpacing();

		// lokale Spacings werden, wenn nötig, genutzt
		if (style.getLocalCharSpacing() != -1) {
			TextHandler.charSpacing(style.getLocalCharSpacing());
		}
		if (style.getLocalWordSpacing() != -1) {
			TextHandler.wordSpacing(style.getLocalWordSpacing());
		}
		if (style.getLocalLineSpacing() != -1) {
			TextHandler.lineSpacing(style.getLocalLineSpacing());
		}

		// Textbreite wird ermittelt
		styledStringWidth += TextHandler.font().getTextWidth(text);

		// Font zurücksetzten
		TextHandler.textFont(globalFont);

		// Spacings werden resetet
		TextHandler.charSpacing(globalCharSpacing);
		TextHandler.wordSpacing(globalWordSpacing);
		TextHandler.lineSpacing(globalLineSpacing);

		if (style.getShadow(0) || style.getShadow(1) || style.getShadow(3) || style.getShadow(4) || style.getShadow(5) || style.getShadow(7)) {
			styledStringWidth++;
		}

		return styledStringWidth;
	}
	
	
	


	public int getWidth(int w) { // TODO
		return -1000;
	}
	
	
	
	

	public int getNativeHeight() {
		int styledStringHeight = 0;

		// global Font wird gespeichert
		Font globalFont = TextHandler.font();
		if (style.getFont() != null) {
			TextHandler.textFont(style.getFont());
		}

		// globale Spacings werden gespeichert
		int globalCharSpacing = TextHandler.getCharSpacing();
		int globalWordSpacing = TextHandler.getWordSpacing();
		int globalLineSpacing = TextHandler.getLineSpacing();

		// lokale Spacings werden, wenn nötig, genutzt
		if (style.getLocalCharSpacing() != -1) {
			TextHandler.charSpacing(style.getLocalCharSpacing());
		}
		if (style.getLocalWordSpacing() != -1) {
			TextHandler.wordSpacing(style.getLocalWordSpacing());
		}
		if (style.getLocalLineSpacing() != -1) {
			TextHandler.lineSpacing(style.getLocalLineSpacing());
		}

		// Native Texthöhe wird ermittelt
		styledStringHeight += TextHandler.font().getCharHeight();

		// Font zurücksetzten
		TextHandler.textFont(globalFont);

		// Spacings werden resetet
		TextHandler.charSpacing(globalCharSpacing);
		TextHandler.wordSpacing(globalWordSpacing);
		TextHandler.lineSpacing(globalLineSpacing);

		if (style.getShadow(1) || style.getShadow(2) || style.getShadow(3)) {
			styledStringHeight++;
		}
		if (style.getShadow(7) || style.getShadow(6) || style.getShadow(5)) {
			styledStringHeight++;
		}

		if (style.getUnderline() != null) {
			styledStringHeight += style.getUnderline().underlineHeight + 1; // +1 weil der Unterstrich um 1 nach unten
																		// versetzt anfängt
		}

		return styledStringHeight;
	}

	
	
	
	public int getHeight() {
		int lines = text.split("\n").length;
		int styledStringHeight = 0;

		// global Font wird gespeichert
		Font globalFont = TextHandler.font();
		if (style.getFont() != null) {
			TextHandler.textFont(style.getFont());
		}

		// globale Spacings werden gespeichert
		int globalCharSpacing = TextHandler.getCharSpacing();
		int globalWordSpacing = TextHandler.getWordSpacing();
		int globalLineSpacing = TextHandler.getLineSpacing();

		// lokale Spacings werden, wenn nötig, genutzt
		if (style.getLocalCharSpacing() != -1) {
			TextHandler.charSpacing(style.getLocalCharSpacing());
		}
		if (style.getLocalWordSpacing() != -1) {
			TextHandler.wordSpacing(style.getLocalWordSpacing());
		}
		if (style.getLocalLineSpacing() != -1) {
			TextHandler.lineSpacing(style.getLocalLineSpacing());
		}

		// Texthöhe wird ermittelt
		styledStringHeight += TextHandler.font().getTextHeight(text);

		// Font zurücksetzten
		TextHandler.textFont(globalFont);

		// Spacings werden resetet
		TextHandler.charSpacing(globalCharSpacing);
		TextHandler.wordSpacing(globalWordSpacing);
		TextHandler.lineSpacing(globalLineSpacing);

		if (style.getShadow(1) || style.getShadow(2) || style.getShadow(3)) {
			styledStringHeight++;
		}
		if (style.getShadow(7) || style.getShadow(6) || style.getShadow(5)) {
			styledStringHeight++;
		}

		if (style.getUnderline() != null) {
			styledStringHeight += style.getUnderline().underlineHeight + 1; // +1 weil der Unterstrich um 1 nach unten
																		// versetzt anfängt
		}

		return (styledStringHeight + style.getLocalLineSpacing()) * lines - style.getLocalLineSpacing();
	}

	
	
	
	public int getHeight(int w) {
		return this.getLines(w).length * (this.getNativeHeight() + style().getLocalLineSpacing());
	}

	
	
	
	
	
	// --------------------------------------------------GET-Lines--------------------------------------------------
	public StyledString[] getLines() {
		String[] lines = text.split("\n");
		StyledString[] styledLines = new StyledString[lines.length];

		for (int i = 0; i < lines.length; i++) {
			styledLines[i] = new StyledString(lines[i], style(), alternativeStyle());
		}

		return styledLines;
	}

	
	
	
	public StyledString[] getLines(int w) {
		String text = "";
		for (int i = 0; i < this.text.length(); i++) {
			if (this.text.charAt(i) == '\n') {
				text += " \n ";
			} else {
				text += this.text.charAt(i);
			}
		}

		String[] words = text.split(" ");

		ArrayList<StyledString> lines = new ArrayList<StyledString>();

		int styledStringWidth = 0;
		String currentLine = "";
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals("\n")) {
				lines.add(new StyledString(currentLine, style(), alternativeStyle()));
				currentLine = "";
				styledStringWidth = 0;
				continue;
			}
			if (styledStringWidth + (new StyledString(words[i], style())).getWidth() > w) {
				lines.add(new StyledString(currentLine, style(), alternativeStyle()));
				currentLine = words[i] + " ";
				styledStringWidth = (new StyledString(words[i], style())).getWidth();
			} else {
				styledStringWidth += (new StyledString(words[i] + " ", style())).getWidth();
				currentLine += words[i] + " ";
			}
		}
		lines.add(new StyledString(currentLine, style(), alternativeStyle()));

		// String[] lines = split(text, '\n');
		StyledString[] styledLines = new StyledString[lines.size()];

		for (int i = 0; i < lines.size(); i++) {
			styledLines[i] = lines.get(i);
		}

		return styledLines;
	}
	
}