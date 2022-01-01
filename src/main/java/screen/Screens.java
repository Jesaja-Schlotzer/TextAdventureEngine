package screen;


import data.FileHandler;
import events.Event;
import game.scenes.MainMenuScene;
import graphics.*;
import graphics.colors.Color;
import graphics.colors.Palette;
import gui.*;
import input.MouseHandler;
import taEngine.TAColors;
import taEngine.TAImgs;
import taEngine.editor.Editor;
import taEngine.game.Game;
import taEngine.game.GameHandler;
import textHandler.Font;
import textHandler.Style;
import textHandler.TextHandler;

import java.time.LocalDate;
import java.time.LocalTime;


public class Screens {
	
	public static void init() { // TODO Warscheinlich erstmal unnötig
		//Painter.drawBackground(Color.BLACK);
	}



	
	// Blackboard
	
	// TODO "Billige" Buttons durch TBs oder EBs austauschen

	
	
	
	public static Screen main = new Screen() {
		EventButton gameButton = 	 new EventButton(60, 365, "spiel laden",   	() -> ScreenHandler.getInstance().requestNextScreen(loadGame)).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#50b04c", "#449741"}));//.applyOperator(ImgOperator.SCALE_3X);
		EventButton editorButton = 	 new EventButton(60, 535, "editor öffnen", 	() -> ScreenHandler.getInstance().requestNextScreen(editor)  ).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#4fad85", "#429270"}));//.applyOperator(ImgOperator.SCALE_3X);
		EventButton settingsButton = new EventButton(60, 710, "einstellungen", 	() -> ScreenHandler.getInstance().requestNextScreen(main)	 ).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#82827a", "#6d6d65"}));//.applyOperator(ImgOperator.SCALE_3X);
		EventButton exitButton = 	 new EventButton(60, 885, "beenden",			() -> ScreenHandler.getInstance().requestNextScreen(exit)	 ).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#cd3939", "#b82d2d"}));//.applyOperator(ImgOperator.AS_GRAY);
		
		public void draw() {
			Painter.drawBackground(TAColors.WHITE);
			
			gameButton.draw();
			editorButton.draw();
			settingsButton.draw();
			exitButton.draw();

			TextHandler.drawString(MainMenuScene.currentFPS, 1800, 15);
		}
		
		
		public void mouseClick() {
			ScreenHandler.getInstance().requestNextScreen(editor);
			gameButton.handleMouseClick();
			
			editorButton.handleMouseClick();
			
			settingsButton.handleMouseClick();
			
			exitButton.handleMouseClick();
		}
		
		
		public void mouseWheel(float wheelRotation) {
			
		}
		
		
		
		public void keyPressed(short keyCode) {
			
		}


		
		public void keyReleased() {
			
		}

		
		
		public void open() {
			
		}


		public void close() {
			
		}
		
	};
	
	
	
	
	
	
	public static Screen loadGame = new Screen() {
		EventButton exitButton = 		new EventButton(25, 25, "exit", () -> ScreenHandler.getInstance().requestNextScreen(main)).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#cd3939", "#b32828"}));
		EventButton importGameButton = 	new EventButton(445, 30, "spiel importieren", () -> GameHandler.importGame()).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#4c82b0", "#42739c"}));
		EventButton loadGameButton = 	new EventButton(1110, 855, "spiel laden", () -> ScreenHandler.getInstance().requestNextScreen(gameMenu)).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#50b04c", "#459a41"}));
		
		
		ScrollArea gameDescriptionArea = new ScrollArea(1000, 360, 765, 470, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.LIGHT_GRAY);
		
		
		
		@Override
		public void draw() {
			Painter.drawImage(TAImgs.loadScreen, 0, 0);
		    
			exitButton.draw();
			
			importGameButton.draw();
		    
			TextHandler.setWrapWordPolicy(true);
		    TextHandler.textColor(TAColors.DARK_GRAY);
		    TextHandler.textFont(Font.BIG_PIXEL_FONT);
		    
		    String[] files = GameHandler.getGames();
		    int imgYOff = 240;
		    for(int i = 0; i < files.length; i++){
		    	Painter.drawImage(TAImgs.frame, 155, imgYOff-65);
		    	TextHandler.textAlign(TextHandler.LEFT);
		    	TextHandler.drawString(files[i].split("[.]")[0].toLowerCase(), 170, imgYOff-50);
		    	imgYOff += 75;
		    }
		    
		    
		    if(!GameHandler.loadedGame[0].equals("null")){
		    	TextHandler.textAlign(TextHandler.CENTER);
		    	TextHandler.drawString(GameHandler.loadedGame[0], 1377, 230);
		    	TextHandler.textFont(Font.PIXEL_FONT);
		    		
		    	TextHandler.textAlign(TextHandler.LEFT);
		    	TextHandler.drawString("von "+GameHandler.loadedGame[1], 1025, 320);
		    	gameDescriptionArea.draw();
		    	
		    	loadGameButton.draw();
		    }
		    
		    TextHandler.textFont(Font.PIXEL_FONT);
		}
		

		
		
		@Override
		public void mouseClick() {
			exitButton.handleMouseClick();
			    
			importGameButton.handleMouseClick();
			
			
			String[] files = GameHandler.getGames();
			
			int imgYOff = 175;
			for(int i = 0; i < files.length; i++){
				if(MouseHandler.overButton(155, imgYOff, 980, imgYOff+75)){
					GameHandler.loadGameData(files[i]);
					
					if(!GameHandler.loadedGame[0].equals("null")){
						gameDescriptionArea.setContent(GameHandler.loadedGame[2], 765, 470);
					}
				}
				imgYOff += 75;
			}
			
			    
			if(GameHandler.loadedGame != null){
				loadGameButton.handleMouseClick();
				
				gameDescriptionArea.handleMouseClick();
			}
		}

		
		
		@Override
		public void mouseWheel(float wheelRotation) {
			if(GameHandler.loadedGame != null) {
				gameDescriptionArea.handleMouseWheel(wheelRotation);
			}
		}
		
		

		@Override
		public void keyPressed(short keyCode) {
			
		}

		@Override
		public void keyReleased() {
			
		}

		@Override
		public void open() {
			
		}

		@Override
		public void close() {
			
		}
		
	};
	
	
	
	
	
	public static Screen gameMenu = new Screen() {
		EventButton exitButton = 		new EventButton(25, 25, "exit", () -> ScreenHandler.getInstance().requestNextScreen(loadGame)).setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#cd3939", "#b32828"}));
		
		private int selectedSave;
		
		private ScrollArea gameDescriptionArea = new ScrollArea(180, 370, 700, 895, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.LIGHT_GRAY);
		
		
		TextField[] saveNameTextFields = {
				new TextField(995, 345, true).setWidth(325).setPalette(Palette.NULL_PALETTE.copy().setColor(0, TAColors.DARK_GRAY)),
				new TextField(995, 565, true).setWidth(325).setPalette(Palette.NULL_PALETTE.copy().setColor(0, TAColors.DARK_GRAY)),
				new TextField(995, 785, true).setWidth(325).setPalette(Palette.NULL_PALETTE.copy().setColor(0, TAColors.DARK_GRAY))
		};
		
		
		ToggleButton startGameButton = 	new ToggleButton(1340, 345, "laden").setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#50b04c", "#459a41"}));
		Switch renameSaveButton = 		new Switch(1340, 395, "umbennenen", "fertig").setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#4c82b0", "#42739c"}));
		
		ButtonNotification sureToDeleteNotification = new ButtonNotification("willst du diesen speicherstand\nwirklich löschen",new String[] {"ja", "nein"}, new Event[] {this::deleteSave, Event.NULL}).setPalette(TAColors.STANDARD_TA_PALETTE);
		
		ToggleButton deleteSaveButton = new ToggleButton(1340, 445, "löschen").setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#cd3939", "#b32828"}));
		
		
		
		
		@Override
		public void draw() {
		    Painter.drawImage(TAImgs.loadedScreen, 0, 0);
		    
		    
		    exitButton.draw();
		    
		    // Linke Seite
		    TextHandler.textFont(Font.BIG_PIXEL_FONT);
		    TextHandler.drawStyledString("speicherstände", new Style(Style.STANDARD_HEADER_STYLE).setTextColor(TAColors.DARK_GRAY).setUnderlineColor(TAColors.DARK_GRAY).setAlign(TextHandler.CENTER).setAdditionalUnderlineLength(1), 1335, 230);
		    
		    TextHandler.textColor(TAColors.DARK_GRAY);
		    
		    TextHandler.drawStyledString(GameHandler.loadedGame[0], new Style(Style.STANDARD_HEADER_STYLE).setTextColor(TAColors.DARK_GRAY).setUnderlineColor(TAColors.DARK_GRAY).setAlign(TextHandler.CENTER).setAdditionalUnderlineLength(1), 550, 230);
		    TextHandler.textFont(Font.PIXEL_FONT);
		    
		    TextHandler.textAlign(TextHandler.LEFT);
		    TextHandler.drawString("von "+GameHandler.loadedGame[1], 200, 320);
		    gameDescriptionArea.draw();
		    
		    
		    // Rechte Seite
		    
		    for(int i = 0; i < GameHandler.loadedSave.length/5; i++){
		    	// Formular-Struktur
		    	Painter.drawRect(990, 340+(i*220), 335, 155, Color.NO_COLOR, TAColors.LIGHT_GRAY);
		    	Painter.drawRect(995, 390+(i*220), 325, 5, Color.NO_COLOR, TAColors.LIGHT_GRAY);
		    	Painter.drawRect(995, 440+(i*220), 325, 5, Color.NO_COLOR, TAColors.LIGHT_GRAY);
		    	Painter.drawRect(1200, 395+(i*220), 5, 45, Color.NO_COLOR, TAColors.LIGHT_GRAY);
		    	TextHandler.drawString("spielzeit:", 1005, 455+(i*220));
		    	
		    	// Formular-Inhalt
		    	TextHandler.textAlign(TextHandler.RIGHT);
		    	TextHandler.drawString(GameHandler.loadedSave[1+(i*5)], 1190, 405+(i*220)); // Date
		    	TextHandler.drawString(GameHandler.loadedSave[2+(i*5)].split("[:]")[0]+":"+GameHandler.loadedSave[2+(i*5)].split("[:]")[1], 1305, 405+(i*220)); // Time
		    	TextHandler.drawString(GameHandler.loadedSave[3+(i*5)], 1310, 455+(i*220)); // played Time
		    	TextHandler.textAlign(TextHandler.LEFT);
		      
		    	saveNameTextFields[i].draw();
		    }
		    
		    
		    // "neuer Spielstand" - Button
		    if(GameHandler.loadedSave.length/5 != 3){
		    	Painter.drawRect(990, 340+((GameHandler.loadedSave.length/5)*220), 335, 115, Color.NO_COLOR, TAColors.LIGHT_GRAY);
		    	
		    	Painter.drawRect(1010, 395+((GameHandler.loadedSave.length/5)*220), 45, 5, Color.NO_COLOR, TAColors.DARK_GRAY);
		    	Painter.drawRect(1030, 375+((GameHandler.loadedSave.length/5)*220), 5, 45, Color.NO_COLOR, TAColors.DARK_GRAY);
		    	
		    	TextHandler.drawString("neuer", 1070, 365+((GameHandler.loadedSave.length/5)*220));
		    	TextHandler.drawString("spielstand", 1070, 405+((GameHandler.loadedSave.length/5)*220));
		    }
		    
		    
		    
		    // laden / umbenennen / löschen
		    if(GameHandler.loadedSave.length/5 > 0) {
		    	startGameButton.draw();
		    	renameSaveButton.draw();
		    	deleteSaveButton.draw();
		    }
		    
		    
		    sureToDeleteNotification.draw();
		}

		
		
		
		
		@Override
		public void mouseClick() {
			if(sureToDeleteNotification.isOpen()) {
				sureToDeleteNotification.handleMouseClick();
				return;
			}
			
			
			exitButton.handleMouseClick();
			
			
			
			gameDescriptionArea.handleMouseClick();
			
			
			for(int i = 0; i < GameHandler.loadedSave.length/5; i++){
				if(MouseHandler.overButton(990, 340+(i*220), 1325, 495+(i*220))){ // savegame selection
					//save[selectedSave].isFocused = false;
					renameSaveButton.setSelectedIndex(0);
					saveNameTextFields[selectedSave].setFocus(false);
					
					selectedSave = i;
					
					startGameButton.setPos(1340, 345+(i*220));
					renameSaveButton.setPos(1340, 395+(i*220));
					deleteSaveButton.setPos(1340, 445+(i*220));
				}
			}
			
			
			if(GameHandler.loadedSave.length/5 != 0){
				
				// Laden
				if(startGameButton.handleMouseClick()){
			        GameHandler.loadedSave[selectedSave*5+1] = LocalDate.now().getDayOfMonth() + "." + LocalDate.now().getMonthValue() + "." +  LocalDate.now().getYear();
			        GameHandler.loadedSave[selectedSave*5+2] = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
			        ScreenHandler.getInstance().requestNextScreen(game);
				}
			    
			    
				
				// Umbenennen
				renameSaveButton.handleMouseClick();
				
				
				if(!saveNameTextFields[selectedSave].isFocused() && renameSaveButton.getSelectedIndex() == 1) {
					saveNameTextFields[selectedSave].setFocus(true);
				}else if(saveNameTextFields[selectedSave].isFocused()){
					saveNameTextFields[selectedSave].setFocus(false);
					
					GameHandler.loadedSave[selectedSave*5] = saveNameTextFields[selectedSave].getText();
					FileHandler.saveStrings(GameHandler.loadedSavePath, GameHandler.loadedSave);
				}
				
				
				
			    // Löschen
				if(deleteSaveButton.handleMouseClick()) {
					sureToDeleteNotification.open();
					
					renameSaveButton.setSelectedIndex(0);
					saveNameTextFields[selectedSave].setFocus(false);
				}
			    
			    
			    
			    
			}
			
			
			
			// Neuer Spielstand
			if(GameHandler.loadedSave.length/5 < 3 && MouseHandler.overButton(990, 340+((GameHandler.loadedSave.length/5)*220), 1325, 455+((GameHandler.loadedSave.length/5)*220))){ // Neu
				//save[selectedSave].isFocused = false;
				String[] longSave = new String[GameHandler.loadedSave.length+5];
				for(int i = 0; i < GameHandler.loadedSave.length; i++){
					longSave[i] = GameHandler.loadedSave[i];
				}
				longSave[GameHandler.loadedSave.length] = "neu";
				longSave[GameHandler.loadedSave.length+1] = LocalDate.now().getDayOfMonth() + "." + LocalDate.now().getMonthValue() + "." +  LocalDate.now().getYear();
				longSave[GameHandler.loadedSave.length+2] = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
				longSave[GameHandler.loadedSave.length+3] = "0:00:00";
				longSave[GameHandler.loadedSave.length+4] = "0";
				
				saveNameTextFields[GameHandler.loadedSave.length/5].setText(longSave[GameHandler.loadedSave.length]);
				
				FileHandler.saveStrings(GameHandler.loadedSavePath, longSave);
				GameHandler.loadedSave = FileHandler.loadStrings(GameHandler.loadedSavePath);
			    
				
				renameSaveButton.setSelectedIndex(0);
				saveNameTextFields[selectedSave].setFocus(false);
				
				selectedSave = GameHandler.loadedSave.length/5-1;
			}
		}
		
		
		

		
		
		@Override
		public void mouseWheel(float wheelRotation) {
			gameDescriptionArea.handleMouseWheel(wheelRotation);
		}

		
		
		@Override
		public void keyPressed(short keyCode) {
			for(TextField tf : saveNameTextFields) {
				tf.handleKeyInput(keyCode);
			}
		}
		
		

		@Override
		public void keyReleased() {
			
		}

		
		
		@Override
		public void open() {
			gameDescriptionArea.setContent(GameHandler.loadedGame[2], 700, 895);
			
			// Setzten der TextField Texte
			int counter = 0;
	        for(int j = 0; j < GameHandler.loadedSave.length/5; j++){
	        	saveNameTextFields[counter].setText(GameHandler.loadedSave[j*5]);
	        	counter++;
	        }
	        
	        
	        selectedSave = 0;
		}
		
		

		@Override
		public void close() {
			renameSaveButton.setSelectedIndex(0);
			saveNameTextFields[selectedSave].setFocus(false);
				
		}
		
		
		
		
		
		private void deleteSave() {
			if(GameHandler.loadedSave.length/5 != 0){
				String[] shortSave = new String[GameHandler.loadedSave.length-5];
				int counter = 0;
				for(int i = 0; i < GameHandler.loadedSave.length/5; i++){
					if(i != selectedSave){
						for(int j = 0; j < 5; j++){
							shortSave[counter] = GameHandler.loadedSave[(i*5)+j];
							counter++;
						}
					}
				}
				FileHandler.saveStrings(GameHandler.loadedSavePath, shortSave);
				GameHandler.loadedSave = FileHandler.loadStrings(GameHandler.loadedSavePath);
	          
				if(selectedSave == GameHandler.loadedSave.length/5 && selectedSave > 0){
					selectedSave--;
				}
			}
		}
		
	};
	
	
	
	
	
	
	public static Screen game = new Screen() {
		
		ToggleButton menuButton = new ToggleButton(15, 15, "menü").setLayout(ToggleButton.TAE_MENU_LAYOUT).setPalette(new Palette(new Object[] {Color.WHITE, "#989882", "#86866f"}));
		
		Drawable menuButtonDrawable = (x, y) -> {
			Painter.drawRect(x, y, 120, 55, TAColors.WHITE, TAColors.DARK_GRAY);
			TextHandler.drawString("menü", x+15, y+15, TAColors.DARK_GRAY);
		};
		
		Drawable saveButtonDrawable = (x, y) -> {
			Painter.drawRect(x, y, 200, 55, TAColors.WHITE, TAColors.DARK_GRAY);
			TextHandler.drawString("speichern", x+15, y+15, TAColors.DARK_GRAY);
		};
		
		Drawable saveAndQuitButtonDrawable = (x, y) -> {
			Painter.drawRect(x, y, 210, 55, TAColors.WHITE, TAColors.DARK_GRAY);
			Painter.drawRect(x+205, y, 185, 55, TAColors.WHITE, TAColors.RED);
			
			
			Painter.drawRect(x+195, y+25, 10, 5, TAColors.DARK_GRAY);
			Painter.drawRect(x+210, y+25, 10, 5, TAColors.RED);
			
			Painter.drawPoint(x+205, y, TAColors.RED_DARK_GRAY_TRANSITION);
			
			Painter.drawPoint(x+205, y+5, TAColors.WHITE);
			Painter.drawPoint(x+205, y+10, TAColors.WHITE);
			
			Painter.drawRect(x+205, y+15, 5, 25, TAColors.RED_DARK_GRAY_TRANSITION);
			
			Painter.drawPoint(x+205, y+40, TAColors.WHITE);
			Painter.drawPoint(x+205, y+45, TAColors.WHITE);
			
			Painter.drawPoint(x+205, y+50, TAColors.RED_DARK_GRAY_TRANSITION);
			
			
			TextHandler.drawString("speichern", x+15, y+15, TAColors.DARK_GRAY);
			TextHandler.drawString("beenden", x+230, y+15, TAColors.RED);
		};
		
		Drawable quitButtonDrawable = (x, y) -> {
			Painter.drawRect(x, y, 175, 55, TAColors.WHITE, TAColors.RED);
			TextHandler.drawString("beenden", x+15, y+15, TAColors.RED);
		};
		
		
		boolean gameMenuIsOpen;
		
		
		
		
		@Override
		public void draw() {
			Painter.drawRect(0, 0, 1920, 1080, TAColors.WHITE, TAColors.DARK_GRAY);
		    
			if(!gameMenuIsOpen) {
				menuButton.draw();
			}
			
			
			Game.draw();
		    
			
			
			
		    if(gameMenuIsOpen){
		    	//Painter.drawBackground(Color.getColor(70, 70, 70, 60));
		    	
		    	
		    	if(MouseHandler.overButton(15, 15, 135, 70)){ // Menü
		    		menuButtonDrawable.draw(15, 20);
				}else {
					menuButtonDrawable.draw(15, 15);
				}
		    	
				if(MouseHandler.overButton(140, 15, 340, 70)){ // Speichern
					saveButtonDrawable.draw(140, 20);
				}else {
					saveButtonDrawable.draw(140, 15);
				}
				
				if(MouseHandler.overButton(345, 15, 735, 70)){ // Speichern + Beenden
					saveAndQuitButtonDrawable.draw(345, 20);
				}else {
					saveAndQuitButtonDrawable.draw(345, 15);
				}
				
				if(MouseHandler.overButton(740, 15, 915, 70)){ // Beenden
					quitButtonDrawable.draw(740, 20);
				}else {
					quitButtonDrawable.draw(740, 15);
				}
		    }
		}
		
		

		@Override
		public void mouseClick() {
			if(MouseHandler.overButton(15, 15, 135, 70)){ // Menü
				gameMenuIsOpen = !gameMenuIsOpen;
			}
			if(MouseHandler.overButton(140, 15, 340, 70)){ // Speichern
				GameHandler.saveTheGame();
			}
			if(MouseHandler.overButton(345, 15, 735, 70)){ // Speichern + Beenden
				GameHandler.saveTheGame();
				gameMenuIsOpen = false;
				ScreenHandler.getInstance().requestNextScreen(gameMenu);
			}
			if(MouseHandler.overButton(740, 15, 915, 70)){ // Beenden
				gameMenuIsOpen = false;
				ScreenHandler.getInstance().requestNextScreen(gameMenu);
			}
			    
			Game.handleMouseClick();
		}

		
		
		
		@Override
		public void mouseWheel(float wheelRotation) {
			Game.handleMouseWheel(wheelRotation);
		}
		
		

		@Override
		public void keyPressed(short keyCode) {
			
		}
		
		

		@Override
		public void keyReleased() {
			
		}
		
		
		
		@Override
		public void open() {
			/*
			GameHandler.loadGame(GameHandler.loadedGame);
			
			Game.startGame();
			*/
		}

		
		
		@Override
		public void close() {
			
		}
		
	};
	
	
	
	
	
	
	
	
	
	
	public static Screen editor = new Screen() {
		
		@Override
		public void draw() {
			Editor.drawEditor();
		}
		

		@Override
		public void mouseClick() {
			Editor.handleMouseClick();
		}

		@Override
		public void mouseWheel(float wheelRotation) {
			Editor.handleMouseWheel(wheelRotation);
		}

		@Override
		public void keyPressed(short keyCode) {
			
		}

		@Override
		public void keyReleased() {
			
		}

		@Override
		public void open() {
			
		}

		@Override
		public void close() {
			
		}
		
	};
	
	
	
	public static Screen preview = new Screen() {

		@Override
		public void draw() {
			
		}

		@Override
		public void mouseClick() {
			
		}

		@Override
		public void mouseWheel(float wheelRotation) {
			
		}

		@Override
		public void keyPressed(short keyCode) {
			
		}

		@Override
		public void keyReleased() {
			
		}

		@Override
		public void open() {
			ScreenHandler.getInstance().requestNextScreen(editor);
		}

		@Override
		public void close() {
			
		}
		
	};
	
	
	
	
	public static Screen exit = new Screen() {
		
		
		
		public void draw() {
			Painter.drawBackground(Color.WHITE);
			
		}

		
		
		public void mouseClick() {
			
		}

		
		public void mouseWheel(float wheelRotation) {
			
		}

		
		
		public void keyPressed(short keyCode) {
			
		}
		
		

		public void keyReleased() {
			
		}

		
		
		public void open() {
			//exitNotification.start();
			System.exit(0);
		}

		
		
		public void close() {
			
		}
		
	};
	
	
	
	
	
	
	
}
