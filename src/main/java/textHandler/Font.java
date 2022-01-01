package textHandler;

import data.FileHandler;
import debugging.Debugger;
import graphics.GraphicsConstants;
import screen.ScreenHandler;

import java.util.ArrayList;
import java.util.function.UnaryOperator;


public class Font {
	
	// Constants
	
	public static final UnaryOperator<String> standardPath = (String path) -> 
		{
			return FileHandler.PROJECT_PATH + "Font\\" + path;
		};
			
	// End
			
			
		
	// Standard Fonts
	
	public static final Font PIXEL_FONT =	 	new Font(Font.standardPath.apply("pixelFont.font"));
	public static final Font BIG_PIXEL_FONT = 	new Font(Font.standardPath.apply("bigPixelFont.font"));
	
	public static final Font SYMBOL_FONT = 		new Font(Font.standardPath.apply("symbolFont.font"));
	
	public static final Font TEST_FONT = 		new Font(Font.standardPath.apply("testFont.font"));
	
	// End
	
			
			
	private String[] fontData;

	
	int maxCharWidth, maxCharHeight;
	int wordSpacing, charSpacing, lineSpacing;
	final int additionalSpaceTop, additionalSpaceBottom;

	
	private ArrayList<Char> chars = new ArrayList<Char>();

	
	private Char nullChar;

	
	
	public Font(String path) {
		this.fontData = FileHandler.loadStrings(path);

		System.out.println(fontData);
		
		this.maxCharWidth = 			Integer.parseInt((fontData[0].split("="))[1].replaceAll(" ", ""));
		this.maxCharHeight = 			Integer.parseInt((fontData[1].split("="))[1].replaceAll(" ", ""));
		this.additionalSpaceTop = 		Integer.parseInt((fontData[2].split("="))[1].replaceAll(" ", ""));
		this.additionalSpaceBottom = 	Integer.parseInt((fontData[3].split("="))[1].replaceAll(" ", ""));
		this.charSpacing = 				Integer.parseInt((fontData[4].split("="))[1].replaceAll(" ", ""));
		this.wordSpacing = 				Integer.parseInt((fontData[5].split("="))[1].replaceAll(" ", ""));
		this.lineSpacing = 				Integer.parseInt((fontData[6].split("="))[1].replaceAll(" ", ""));

		Debugger.addLogEntry("// -----------------------Font-----------------------");
		Debugger.addLogEntry("//" + path);
		Debugger.addLogEntry("//" + fontData[0] + " : " + maxCharWidth);
		Debugger.addLogEntry("//" + fontData[1] + " : " + maxCharHeight);
		Debugger.addLogEntry("//" + fontData[2] + " : " + additionalSpaceTop);
		Debugger.addLogEntry("//" + fontData[3] + " : " + additionalSpaceBottom);
		Debugger.addLogEntry("//" + fontData[4] + " : " + charSpacing);
		Debugger.addLogEntry("//" + fontData[5] + " : " + wordSpacing);
		Debugger.addLogEntry("//" + fontData[6] + " : " + lineSpacing);
		Debugger.addLogEntry("// ---------------------Ende-Font---------------------");
		Debugger.addLogEntry("");

		System.out.println("// -----------------------Font-----------------------");
		System.out.println("//" + path);
		System.out.println("//" + fontData[0] + " : " + maxCharWidth);
		System.out.println("//" + fontData[1] + " : " + maxCharHeight);
		System.out.println("//" + fontData[2] + " : " + additionalSpaceTop);
		System.out.println("//" + fontData[3] + " : " + additionalSpaceBottom);
		System.out.println("//" + fontData[4] + " : " + charSpacing);
		System.out.println("//" + fontData[5] + " : " + wordSpacing);
		System.out.println("//" + fontData[6] + " : " + lineSpacing);
		System.out.println("// ---------------------Ende-Font---------------------");
		System.out.println("");

		this.nullChar = new Char(fontData[7]);

		for (int i = 8; i < fontData.length; i++) {
			chars.add(new Char(fontData[i]));
		}
	}
	
	
	
	
	
	public int getMaxCharWidth() {
		return maxCharWidth;
	}
	
	
	public int getMaxCharHeight() {
		return maxCharHeight;
	}
	
	
	
	
	public int getWordSpacing() {
		return wordSpacing;
	}
	
	
	public int getCharSpacing() {
		return charSpacing;
	}
	
	
	public int getLineSpacing() {
		return lineSpacing;
	}
	
	
	
	public int getAdditionalSpaceTop() {
		return additionalSpaceTop;
	}
	
	
	public int getAdditionalSpaceBottom() {
		return additionalSpaceBottom;
	}
	
	
	
	
	
	
	
	
	
	

	void drawString(String text, final int X, final int Y) {
		if (text.equals("")) {
			return;
		}
		int x = X;
		int y = Y - additionalSpaceTop;

		for (int i = 0; i < text.length(); i++) {
			switch (text.charAt(i)) {
			case ' ':
				x += wordSpacing;
				continue;
			case '\n':
				x = (int) X;
				y += getCharHeight() + lineSpacing;
				continue;
			case TextHandler.ONE_SPACE:
				x += 1; // ACHTUNG jetzt nicht mehr "2" weil logik
				continue;
			}

			Char chr = getCharObj(text.charAt(i));

			for (int j = 0; j < chr.charWidth; j++) {
				for (int k = 0; k < maxCharHeight; k++) {
					if (chr.rawGridData[j][k]) {
						ScreenHandler.getInstance().setScreenBuffer(x, y, TextHandler.textColor);
					}
					y++;
				}
				y -= maxCharHeight;
				x++;
			}
			x += charSpacing;
		}
	}

	
	
	
	
	void drawString(String text, final int X1, final int Y1, final int X2, final int Y2) {
		if (text.equals("")) {
			return;
		}
		float x1 = X1;
		float y1 = Y1 - additionalSpaceTop;
		
		
		String[] lines = getLines(text, (Math.max(X2, maxCharWidth + wordSpacing) - X1) * GraphicsConstants.PIXEL_SIZE_X, TextHandler.getWrapWordPolicy());
		
		for(String line : lines) {

			if(y1 + getCharHeight() > Y2) {
				break;
			}
			
			
			for(int i = 0; i < line.length(); i++) {
				switch (line.charAt(i)) {
				
					case ' ':
						x1 += wordSpacing;
						continue;
					
					case TextHandler.ONE_SPACE:
						x1 += 1; // ACHTUNG jetzt nicht mehr "2" weil logik
						continue;
					
					default:
						Char chr = getCharObj(line.charAt(i));

						for (int j = 0; j < chr.charWidth; j++) {
							for (int k = 0; k < maxCharHeight; k++) {
								if (chr.rawGridData[j][k]) {
									ScreenHandler.getInstance().setScreenBuffer((int) x1, (int) y1, TextHandler.textColor);
								}
								y1 += 1;
							}
							y1 -= maxCharHeight;
							x1 += 1;
						}
						x1 += charSpacing;
				}
			}
			x1 = X1;
			y1 += getCharHeight() + lineSpacing;
		}
		
		/*
		for (int i = 0; i < text.length(); i++) {
			switch (text.charAt(i)) {
			case ' ':
				if (x1 + wordSpacing > x2) {
					x1 = X1;
					y1 += (getCharHeight() + lineSpacing);
				} else {
					x1 += wordSpacing;
				}
				continue;
			case '\n':
				x1 = X1;
				y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
				continue;
			case TextHandler.ONE_SPACE:
				if (x1 + 2 > x2) {
					x1 = X1;
					y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
				}
				x1 += 2;
				continue;
			}
			
			
			/* 													Passt der Buchstabe in die momentane Zeile
			 Char chr = getCharObj(text.charAt(i));

			 if(x1 + chr.charWidth > x2){
				 x1 = X1;
				 y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
			 }
			 */
			/*
			
			// 													Passt das Wort in die momentane Zeile
			String word = getNextWord(text.substring(i));
			if (word.length() > 0) {
				i += word.length() - 1;
			}

			if (x1 + getTextWidth(word) > x2) {
				x1 = X1;
				y1 += ((maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop)));
			}
			
			
			if (y1 + ((maxCharHeight - (additionalSpaceBottom + additionalSpaceTop))) > y2) {
				Debugger.addLogEntry("FEHLER - Font/drawText(String, float, float, float, float) - " + text + " passt nicht komplett in das angegebebene Rechteck");
				break;
			}

			for (int c = 0; c < word.length(); c++) {
				Char chr = getCharObj(word.charAt(c));

				for (int j = 0; j < chr.charWidth; j++) {
					for (int k = 0; k < maxCharHeight; k++) {
						if (chr.rawGridData[j][k]) {
							ScreenHandler.setScreenBuffer((int) x1, (int) y1, TextHandler.textColor);
						}
						y1 += 1;
					}
					y1 -= maxCharHeight;
					x1 += 1;
				}
				x1 += charSpacing;
			}
		}
	*/
	}
	
	
	
	

	public String getNextWord(String text) {
		String word = "";
		int i = 0;

		while (i < text.length()) {
			if (text.charAt(i) == ' ') {
				break;
			}
			
			if(text.charAt(i) == '\n') {
				if(word.equals("")) {
					return "\n";
				}
			}
			

			word += text.charAt(i);
			i++;
		}
		
		return word;
	}
	
	
	
	
	
	

	public int getNativeTextWidth(String text) {
		if (text.equals("")) {
			return 0;
		}
		
		int textWidth = 0;
		
		
		for (int i = 0; i < text.length(); i++) {
			switch (text.charAt(i)) {
			case ' ':
				textWidth += wordSpacing;
				continue;
				
				
			case '\n':
				continue;
				
				
			case TextHandler.ONE_SPACE:
				textWidth += 1; // ACHTUNG jetzt nicht mehr "2" weil logik
				continue;
				
			}
			
			textWidth += getCharObj(text.charAt(i)).charWidth + charSpacing;
		}
		
		textWidth -= charSpacing;
		
		return textWidth;
	}
	
	
	
	

	public int getTextWidth(String text) {
		if (text.equals("")) {
			return 0;
		}
		
		int longestLine = -1;
		int textWidth = 0;
		
		
		for (int i = 0; i < text.length(); i++) {
			switch (text.charAt(i)) {
			case ' ':
				textWidth += wordSpacing;
				continue;
				
				
			case '\n':
				if (longestLine < textWidth) {
					longestLine = textWidth;
					textWidth = 0;
				}
				continue;
				
				
			case TextHandler.ONE_SPACE:
				textWidth += 1; // ACHTUNG jetzt nicht mehr "2" weil logik
				continue;
				
				
			default:
				Char chr = getCharObj(text.charAt(i));
				textWidth += chr.charWidth + charSpacing;
			}
		}
		

		if (longestLine == -1) {
			longestLine = textWidth;
		}

		longestLine -= charSpacing; // charSpacing am Ende löschen
		
		return longestLine;
	}
	
	
	
	
	

	public int getTextWidth(String text, int w, boolean wrapWords) {
		return getTextWidth(TextHandler.getLongestString(getLines(text, w, wrapWords)));
	}
	
	
	
	
	
	
	public int getTextHeight(String text) {
		if (text.equals("")) {
			return 0;
		}
		
		int textHeight = getCharHeight() + lineSpacing;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '\n') {
				textHeight += getCharHeight() + lineSpacing;
			}
		}
		
		textHeight -= lineSpacing;

		return textHeight;
	}
	
	
	
	
	
	
	public int getTextHeight(String text, int w, boolean wrapWords) { 
		if (text.equals("")) {
			return 0;
		}
		
		w = Math.max(w / GraphicsConstants.PIXEL_SIZE_X, maxCharWidth + wordSpacing);
		
		int textHeight = getCharHeight() + lineSpacing;

		
		float x1 = 0;
		float y1 = 0;
		float x2 = w / GraphicsConstants.PIXEL_SIZE_X;
		
		
		
		if(wrapWords) {
			
			// Wörter werden in die nächste Zeile übertragen
			
			return countLines(text, w * GraphicsConstants.PIXEL_SIZE_X, true) * (getCharHeight() + lineSpacing) - lineSpacing;
			
			
			/*Was auch immer das geben sollte xD
			 * 
			 
			String[] words = getWords(text);
			
			
			for (String word : words) {
				if(word.equals("\n")) {
					x1 = 0;
					y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
					
				}else {
					if(x1 + getTextWidth(word) > x2) {
						x1 = getTextWidth(word);
						y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
						
						while(x1 > x2) {
							x1 -= x2;
							y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
						}
						
						
						
					}else {
						x1 += getTextWidth(word);
					}
					
					x1 = 0;
				}
			}
			
			if(x1 != 0) {
				y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
			}
			
			*/
		}else {
			
			// Wörter werden getrennt
			
			for (int i = 0; i < text.length(); i++) {
				switch (text.charAt(i)) {
			
				case ' ':
					/* TODO eig. unnötig weil wenn das nächste Wort kommt kommts eh in die nächst Zeile
					if (x1 + wordSpacing > x2) {
						x1 = 0;
						y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
					}
					 	*/
					x1 += wordSpacing;
					continue;
				
				
				case '\n':
					x1 = 0;
					y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
					continue;
				
				
				case TextHandler.ONE_SPACE:
					/* TODO eig. unnötig weil wenn das nächste Wort kommt kommt's eh in die nächst Zeile
					if (x1 + 2 > x2) {
						x1 = 0;
						y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
					}
					 */
					x1 += 1; // ACHTUNG jetzt nicht mehr "2" weil logik
						continue;
				}
			
			
				Char chr = getCharObj(text.charAt(i));
				
				if (x1 + chr.charWidth > x2) {
					x1 = 0;
					y1 += (maxCharHeight + lineSpacing - (additionalSpaceBottom + additionalSpaceTop));
				}
			
			
				x1 += chr.charWidth;
				x1 += charSpacing;
			}
		}
		System.out.println(y1);
		textHeight += y1;
		textHeight -= lineSpacing;

		return textHeight;
	}
	
	
	
	
	

	public int getCharWidth(char c) {
		return getCharObj(c).charWidth;
	}
	
	

	public int getCharWidth(Char c) {
		return c.charWidth;
	}

	
	
	
	public int getCharHeight() {
		return maxCharHeight - (additionalSpaceTop + additionalSpaceBottom);
	}
	
	
	
	
	

	public int countLines(String text, int w, boolean wrapWords) {
		w = Math.max(w, maxCharWidth + wordSpacing);
		
		String currentSubstring = "";
		//int lineCounter = 0;
		
		
		w /= GraphicsConstants.PIXEL_SIZE_X;
		
		ArrayList<String> lineList = new ArrayList<String>();

		if(wrapWords) {
			
			// Wörter werden in die nächste Zeile übertragen
			
			String[] words = getWords(text);
			for (String word : words) {
				if(word.equals("\n")) {
					//lines[lineCounter] = currentSubstring;
					lineList.add(currentSubstring);
					currentSubstring = "";
					//lineCounter++;
					
				}else {
					if(getTextWidth(currentSubstring + word) > w) {
						
						if(getTextWidth(word) > w) { // wenn wort zu lange für zeile
							String subWord = "";
							for(int i = 0; i < word.length(); i++) {
								
								if(getTextWidth(currentSubstring + subWord + word.charAt(i)) > w) {
									//lines[lineCounter] = currentSubstring + subWord;
									lineList.add(currentSubstring + subWord);
									currentSubstring = "";
									subWord = ""+word.charAt(i);
									//lineCounter++;
								}else {
									subWord += word.charAt(i);
								}
							}
							
							currentSubstring += subWord;
							
						}else {
							//lines[lineCounter] = currentSubstring;
							lineList.add(currentSubstring);
							currentSubstring = word;
							//lineCounter++;
						}
						
						
					}else {
						currentSubstring += word;
					}
				}
			}
			
			
		}else {
			
			// Wörter werden getrennt
			
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == '\n') { // evtl. muss "\n" auch noch auf "currentSubstring" geschrieben werden
					//lines[lineCounter] = currentSubstring;
					lineList.add(currentSubstring);
					currentSubstring = "";
					//lineCounter++;
					continue;
				}

				if (getTextWidth(currentSubstring + text.charAt(i)) > w) {
					//lines[lineCounter] = currentSubstring;
					lineList.add(currentSubstring);
					currentSubstring = "";
					//lineCounter++;
				}
				currentSubstring += text.charAt(i);
			}
		}

		if (/*lineCounter < lines.length &&*/ currentSubstring.equals("") == false) {
			lineList.add(currentSubstring);
		}

		/*
		for (int i = 0; i < lines.length; i++) { // TODO beobachten bissl strange
			if (lines[i] == null) {
				lines[i] = "";
			}
		}
		*/
		
		return lineList.size();
		
		
		
		//return (getTextHeight(text, w, wrapWords) + lineSpacing) / (getCharHeight() + lineSpacing);
	}
	
	
	
	
	
	
	
	
	
	

	public String[] getLines(String text, int w, boolean wrapWords) {
		w = Math.max(w, maxCharWidth + wordSpacing);
		
		String[] lines = new String[countLines(text, w, wrapWords)];
		
		String currentSubstring = "";
		int lineCounter = 0;
		
		
		w /= GraphicsConstants.PIXEL_SIZE_X;
		
		ArrayList<String> lineList = new ArrayList<String>();

		if(wrapWords) {
			
			// Wörter werden in die nächste Zeile übertragen
			
			String[] words = getWords(text);
			for (String word : words) {
				if(word.equals("\n")) {
					lines[lineCounter] = currentSubstring;
					lineList.add(currentSubstring);
					currentSubstring = "";
					lineCounter++;
					
				}else {
					if(getTextWidth(currentSubstring + word) > w) {
						
						if(getTextWidth(word) > w) { // wenn wort zu lange für zeile
							String subWord = "";
							for(int i = 0; i < word.length(); i++) {
								
								if(getTextWidth(currentSubstring + subWord + word.charAt(i)) > w) {
									lines[lineCounter] = currentSubstring + subWord;
									lineList.add(currentSubstring + subWord);
									currentSubstring = "";
									subWord = ""+word.charAt(i);
									lineCounter++;
								}else {
									subWord += word.charAt(i);
								}
							}
							
							currentSubstring += subWord;
							
						}else {
							lines[lineCounter] = currentSubstring;
							lineList.add(currentSubstring);
							currentSubstring = word;
							lineCounter++;
						}
						
						
					}else {
						currentSubstring += word;
					}
				}
			}
			
			
		}else {
			
			// Wörter werden getrennt
			
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == '\n') { // evtl. muss "\n" auch noch auf "currentSubstring" geschrieben werden
					lines[lineCounter] = currentSubstring;
					currentSubstring = "";
					lineCounter++;
					continue;
				}

				if (getTextWidth(currentSubstring + text.charAt(i)) > w) {
					lines[lineCounter] = currentSubstring;
					currentSubstring = "";
					lineCounter++;
				}
				currentSubstring += text.charAt(i);
			}
		}

		if (/*lineCounter < lines.length &&*/ currentSubstring.equals("") == false) {
			lines[lineCounter] = currentSubstring;
			lineList.add(currentSubstring);
		}

		for (int i = 0; i < lines.length; i++) { // TODO beobachten bissl strange
			if (lines[i] == null) {
				lines[i] = "";
			}
		}
		
		
		return lines;
		//return lineList.toArray(new String[lineList.size()]);
	}
	
	
	
	
	
	
	
	public int countWords(String text) {
		int wordCount = 1;
		
		for(int i = 0; i < text.length(); i++) {
			switch(text.charAt(i)) {
				case ' ':
					wordCount++;
					break;
					
					
				case '\n': // 2 mal für Zeilenumbruchserkennung
					wordCount++;
					wordCount++;
					break;
					
					
				case TextHandler.ONE_SPACE:
					wordCount++;
					break;
			}
		}
		
		return wordCount;
	}
	
	
	
	
	public String[] getWords(String text) {
		String[] words = new String[countWords(text)];
		int wordCount = 0;
		String currentWord = "";
		
		for(int i = 0; i < text.length(); i++) {
			switch(text.charAt(i)) {
				case ' ':
					currentWord += text.charAt(i);
					words[wordCount] = currentWord;
					currentWord = "";
					wordCount++;
					break;
					
					
				case '\n': // n' bissl komisch ? schau in countWords
					words[wordCount] = currentWord;
					currentWord = "";
					wordCount++;
					
					currentWord += text.charAt(i);
					words[wordCount] = currentWord;
					currentWord = "";
					wordCount++;
					break;
					
					
				case TextHandler.ONE_SPACE:
					currentWord += text.charAt(i);
					words[wordCount] = currentWord;
					currentWord = "";
					wordCount++;
					break;
					
				
				default:
					currentWord += text.charAt(i);
					break;
			}
		}
		
		words[wordCount] = currentWord;
		
		return words;
	}
	
	
	
	
	
	
	// TODO evtl auch bei anderen Methoden Version mit "wrapWord" anbieten
	public boolean doesTextFitInBounds(String text, int w, int h, boolean wrapWords) {
		return getTextHeight(text, w, wrapWords) < h / GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	public boolean doesTextFitInBounds(String text, int w, int h) {
		return getTextHeight(text, w, TextHandler.getWrapWordPolicy()) < h / GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	
	
	

	private Char getCharObj(char c) {
		for (Char chr : chars) {
			if (chr.assignedChar == c) {
				return chr;
			}
		}
		Debugger.addLogEntry("FEHLER - Texthandler/Font/getCharObj() - Unhinzugefügtes Zeichen: " + c + " | KeyCode: " + ((short) c));
		return nullChar;
	}
	
	
	
	
	
	

	class Char {

		char assignedChar;

		int charWidth;

		String charData;
		boolean[][] rawGridData;

		
		
		Char(String completeData) {
			this.assignedChar = completeData.charAt(0);

			this.charData = getShortenedCharData(completeData.split(" ")[1]);

			rawGridData = new boolean[charData.split(";").length][maxCharHeight];

			convertCharData();

			this.charWidth = rawGridData.length;
		}
		
		
		
		

		void convertCharData() {
			String[] rows = charData.split(";");

			for (int i = 0; i < rawGridData.length; i++) {
				String[] cols = rows[i].split(",");
				for (int j = 0; j < maxCharHeight; j++) {
					rawGridData[i][j] = (cols[j].equals("0") ? false : true);
				}
			}
		}
		
		

		String getShortenedCharData(String charData) {
			// Forwärts

			String shortenedCharData = "";
			String[] charDataInLines = charData.split(";");

			for (int i = 0; i < maxCharWidth; i++) {
				if (sum(charDataInLines[i])) {
					shortenedCharData += charDataInLines[i] + ";";
				}
			}
			shortenedCharData = shortenedCharData.substring(0, shortenedCharData.length() - 1);

			// Rückwärts

			charDataInLines = shortenedCharData.split(";");

			shortenedCharData = "";

			for (int i = charDataInLines.length - 1; i >= 0; i--) {
				if (sum(charDataInLines[i])) {
					shortenedCharData = charDataInLines[i] + ";" + shortenedCharData;
				}
			}
			shortenedCharData = shortenedCharData.substring(0, shortenedCharData.length() - 1);

			// println(split(shortenedCharData, ';').length);
			return shortenedCharData;
		}

		
		
		
		boolean sum(String data) {
			String[] splitedData = data.split(",");
			int counter = 0;

			for (int i = 0; i < splitedData.length; i++) {
				counter += Integer.parseInt(splitedData[i]);
			}

			if (counter == 0) {
				return false;
			}
			return true;
		}

	}

}