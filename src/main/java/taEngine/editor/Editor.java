package taEngine.editor;

import data.FileHandler;
import graphics.Painter;
import graphics.colors.Color;
import graphics.colors.Palette;
import gui.EventButton;
import gui.ScrollArea;
import gui.TextArea;
import input.KeyPredicate;
import input.MouseHandler;
import screen.ScreenHandler;
import screen.Screens;
import taEngine.TAColors;
import taEngine.TAImgs;
import taEngine.editor.logSystem.EditorLogSystem;
import taEngine.game.Game;
import textHandler.Font;
import textHandler.Style;
import textHandler.StyledString;
import textHandler.TextHandler;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;



public final class Editor {
	
	// Constants
	
	public static final Color[] DECISION_WAY_COLORS = new Color[] {new Color("#e93841"), new Color("#f1892d"), new Color("#ffe947"), new Color("#5ae150"), new Color("#4d80c9")};
	
	
	
	
	// End
	
	
	// Setting-Variables
	
	
	public static boolean SHOW_COLORED_LINES = true; // TODO Kack name
	public static boolean SHOW_DECWAY_EXTENSION = true; // TODO Kack name & evtl unnötig
	
	
	
	// End
	
	
	
	static boolean MOVE;

	static int EDITOR_POSX, EDITOR_POSY;
	static int EDITOR_XOFF, EDITOR_YOFF;

	static int ZOOM_LEVEL = 2;
	
	
	
	
	public static ArrayList<Group> groupList = new ArrayList<Group>();

	
	
	static Group currentGroup;

	
	
	static String[] loadedEditorFile;
	
	
	
	static boolean decWayConnectionIsSet;
	static int connectionGroup, connectionDecWay;
	
	
	static boolean fileExtIsOpen;
	
	
	
	static ScrollArea groupText = new ScrollArea(795, 50, 340, 165, "")
									.setColors(TAColors.DARK_GRAY, TAColors.WHITE, TAColors.LIGHT_GRAY)
									.setFocusColors(TAColors.DARK_GRAY, TAColors.WHITE, TAColors.MIDDLE_GRAY)
									.setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY)
									.setFocusScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY)
									.addTextSupplier();
	
	
	static ScrollArea decWayText = new ScrollArea(1355, 20, 340, 115, "")
									.setColors(TAColors.DARK_GRAY, TAColors.WHITE, TAColors.LIGHT_GRAY)
									.setFocusColors(TAColors.DARK_GRAY, TAColors.WHITE, TAColors.MIDDLE_GRAY)
									.setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY)
									.setFocusScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY)
									.addTextSupplier();
	
	
	static TextArea followingGroupField = new TextArea(1430, 160, 115, 45)
									.setLimitTextInsideBounds(true)
									.setPalette(new Palette(TAColors.DARK_GRAY, TAColors.MIDDLE_GRAY, TAColors.LIGHT_GRAY, TAColors.LIGHT_GRAY, TAColors.WHITE))
									.addTextSupplier(KeyPredicate.KEY_PREDICATE_ONLY_NUMBERS.andThen(KeyPredicate.KEY_PREDICATE_BACKSPACE));
	
	
	static EventButton followingGroupTFSubmit = new EventButton(1550, 160, new StyledString("A", new Style().setFont(Font.SYMBOL_FONT).setTextColor(TAColors.DARK_GRAY)), () -> currentGroup.decObj.get(currentGroup.editingDecision).setFollowing(Integer.parseInt(followingGroupField.getText())))
									.setPalette(new Palette(TAColors.DARK_GRAY, TAColors.LIGHT_GRAY, TAColors.MIDDLE_GRAY, TAColors.RED, TAColors.WHITE, TAColors.RED, Color.TRANSPARENT));
	
	
	
	
	
	

	
	public static void drawEditor(){
		//ListenerPool.addListener(followingGroupField::getText, text -> true, () -> System.out.println("lol"));
		
		Painter.drawBackground(TAColors.EDITOR_BACKGROUND);
	    
	    TextHandler.drawString(EDITOR_POSX+" "+EDITOR_POSY, 10, 235);
	    
	    TextHandler.drawString(connectionGroup, 10, 375);
	    TextHandler.drawString(connectionDecWay, 10, 425);
	    TextHandler.drawString(decWayConnectionIsSet, 10, 475);
	  
	    TextHandler.drawString(ZOOM_LEVEL, 10, 575);
	        
	        
	    for(Group group : groupList){
	    	group.draw();
	    }
	    
	    if(decWayConnectionIsSet){
	    	Painter.drawLine(groupList.get(connectionGroup).posX+EDITOR_POSX+(25+(connectionDecWay*50)), 
	    			groupList.get(connectionGroup).posY+EDITOR_POSY+Group.groupVisualisationHeight,
	    			MouseHandler.mouseX, 
	    			MouseHandler.mouseY, 
	    			(Editor.SHOW_COLORED_LINES ? Editor.DECISION_WAY_COLORS[connectionDecWay] : TAColors.DARK_GRAY));
	    }
	    
	    Painter.drawImage(TAImgs.editorGui, 0, 0);
	    
		
		    
	    		
	    moveEditor();
	    
	    
	    
	    
	    if(currentGroup != null) {
	    	Painter.drawRect(1190, 5+(currentGroup.editingDecision*45), 5, 40, TAColors.WHITE);
			if(currentGroup.decObj.size() != 5){
				Painter.drawRect(1155, 45+(currentGroup.decObj.size()*45), 40, 45*(4-currentGroup.decObj.size()), TAColors.WHITE);
				Painter.drawImage(TAImgs.plus, 1155, 5+(currentGroup.decObj.size()*45));
			}
			
			
			
	    	currentGroup.move();
	    	
	    	
	    	followingGroupTFSubmit.draw();
	    	
	    	
	    	TextHandler.textColor(TAColors.DARK_GRAY);
			TextHandler.drawString(currentGroup.groupNr+1, 485, 75);
			
			TextHandler.drawString(currentGroup.decObj.get(currentGroup.editingDecision).getText(), 1365, 30);
			TextHandler.drawString(currentGroup.decObj.get(currentGroup.editingDecision).getFollowingGroup(), 1440, 170);
	    }else {
	    	Painter.drawRect(1155, 5, 40, 220, TAColors.WHITE);
	    }
	    
	    
	    groupText.draw();
	    
	    decWayText.draw();
	    
	    followingGroupField.draw();
	    
	    
		if(fileExtIsOpen){
			Painter.drawImage(TAImgs.editorFileExt, 0, 240);
		}
		
		
		EditorLogSystem.getInstance().draw();
	}
	
	
	
	
	
	public static void handleMouseClick(){
		if(MouseHandler.overButton(0, 0, 1920, 230)) { // Größe des Hubs
			
			// Oberer Teil
			if(!fileExtIsOpen){
				if(MouseHandler.overButton(15, 15, 100, 60)){ // Exit
					ScreenHandler.getInstance().requestNextScreen(Screens.main);
				}
		      
				if(MouseHandler.overButton(120, 15, 195, 60)){ // ?
		        
				}
		      
				if(MouseHandler.overButton(15, 85, 120, 130)){ // Datei
					fileExtIsOpen = true;
				}
		      
				if(MouseHandler.overButton(140, 85, 195, 130)){ // Einstellungen
					
				}
		      
				if(MouseHandler.overButton(20, 160, 190, 215)){ // Spielen
					Game.editorQuickPlay(groupList.toArray(new Group[groupList.size()]));
				}
		      
		          
				if(MouseHandler.overButton(220, 15, 460, 110)){ // Gruppe hinzufügen
					groupList.add(new Group(""));
				}
		      
				if(MouseHandler.overButton(220, 120, 440, 215)){ // Gruppe entfernen
		        
				}
		      
				if(MouseHandler.overButton(490, 140, 765, 195)){ // Seitenansicht
					ScreenHandler.getInstance().requestNextScreen(Screens.preview);
				}
		      
		      
				if(MouseHandler.overButton(490, 160, 535, 205)){ // Farben
					
				}
		      
				if(currentGroup != null) {
					groupText.handleMouseClick();
					decWayText.handleMouseClick();
					followingGroupField.handleMouseClick();
					
					
					for(int i = 0; i < currentGroup.decObj.size(); i++){
						if(MouseHandler.isPressed(MouseHandler.LEFT_MOUSE_BUTTON) && MouseHandler.overButton(1150, i*45, 1195, 50+(i*45))){ // Wege
							currentGroup.decObj.get(currentGroup.editingDecision).setText(decWayText.getContent().asString());
							currentGroup.editingDecision = i;
							decWayText.setContent(currentGroup.decObj.get(currentGroup.editingDecision).getText(), decWayText.getW(), decWayText.getH());
						}
					}
			          
					if(MouseHandler.overButton(1150, currentGroup.decObj.size()*45, 1195, 50+(currentGroup.decObj.size()*45))){ // Wege
						currentGroup.addDecisionObj("", currentGroup.groupNr, -1);
					}
					
					
					
					followingGroupTFSubmit.handleMouseClick();
				}
				
				
			}else /*if(fileExtIsOpen)*/{
				if(MouseHandler.overButton(15, 255, 135, 300)){ // laden
					FileHandler.chooseFile(".editor Datei auswählen", new File("Dateien/Editor-Dateien/"), new FileNameExtensionFilter("Editordatei", "editor"));
				}
				if(MouseHandler.overButton(15, 310, 205, 355)){ // speichern
					//saveStrings(loadedEditorFile[0], loadedEditorFile);
				}
				if(MouseHandler.overButton(15, 365, 245, 410)){ // exportieren
					//exportAsGame();
				}
		      
				if(!MouseHandler.overButton(0, 240, 260, 325)){
					fileExtIsOpen = false;
				}
			}
		}else {
			

			
			// Unterer Teil
			
			
			// Gui & currentGroup wird resetet/gespeichert
			if(currentGroup != null) {
				currentGroup.text = groupText.getContent().asString();
				groupText.setContent("", groupText.getW(), groupText.getH());
				groupText.setFocus(false);
				
				
				currentGroup.decObj.get(currentGroup.editingDecision).setText(decWayText.getContent().asString());
				decWayText.setContent("", decWayText.getW(), decWayText.getH());
				
				if(!followingGroupField.getText().equals("")) {
					currentGroup.decObj.get(currentGroup.editingDecision).setFollowing(Integer.parseInt(followingGroupField.getText()));
				}
				followingGroupField.setText("");
				followingGroupField.setFocus(false);
				
				
				
				currentGroup = null;
			}
			
			for(Group group : groupList){
				if(MouseHandler.isPressed(MouseHandler.LEFT_MOUSE_BUTTON /*| MouseHandler.RIGHT_MOUSE_BUTTON*/) && MouseHandler.overButton(group.posX, group.posY, Group.groupVisualisationWidth, Group.groupVisualisationHeight)) {
					currentGroup = group;
					
					groupText.setContent(currentGroup.text, groupText.getW(), groupText.getH());
					decWayText.setContent(currentGroup.decObj.get(currentGroup.editingDecision).getText(), decWayText.getW(), decWayText.getH());
					followingGroupField.setText(currentGroup.decObj.get(currentGroup.editingDecision).getFollowingGroup()+"");
					
					
					if(decWayConnectionIsSet && connectionGroup != currentGroup.groupNr) {
						groupList.get(connectionGroup).decObj.get(connectionDecWay).setFollowing(currentGroup.groupNr);
						
						decWayConnectionIsSet = false;
						connectionGroup = -1;
						connectionDecWay = -1;
					}
				}
			}
			
			
			if(currentGroup != null) {
				if(currentGroup.checkDecWays() != -1){
					connectionDecWay = currentGroup.checkDecWays();
					connectionGroup = currentGroup.groupNr;
					decWayConnectionIsSet = true;
				}
			}else {
				decWayConnectionIsSet = false;
				connectionGroup = -1;
				connectionDecWay = -1;
			}
			
			
			
			
			EditorLogSystem.getInstance().handleMouseClick();
		}
	}
	
	
	
	

	public static void handleMouseWheel(float wheelRotation) {
		if(MouseHandler.overButton(0, 0, 1920, 230)) {
			groupText.handleMouseWheel(wheelRotation);
			decWayText.handleMouseWheel(wheelRotation);
			
			return;
		}
		
		
		
		if (wheelRotation == 1 && Editor.ZOOM_LEVEL > 2) {
			Editor.ZOOM_LEVEL--;
			Group.updateZoom();
		}
		if (wheelRotation == -1 && Editor.ZOOM_LEVEL < 2) {
			Editor.ZOOM_LEVEL++;
			Group.updateZoom();
		}
		
		
		EditorLogSystem.getInstance().handleMouseWheel(wheelRotation);
	}
	
	
	
	
	
	
	private static void moveEditor(){
		if(MouseHandler.isPressed(MouseHandler.MOUSE_WHEELE) && MOVE){
			MOVE = true;
		}else if(MouseHandler.isPressed(MouseHandler.MOUSE_WHEELE) && MouseHandler.overButton(0, 230, 1920, 1080-230)){
			  MOVE = true;
			  EDITOR_XOFF = MouseHandler.mouseX - EDITOR_POSX;
			  EDITOR_YOFF = MouseHandler.mouseY - EDITOR_POSY;
		}else{
			MOVE = false;
		}
		  
		if(MOVE){
			EDITOR_POSX = (MouseHandler.mouseX - EDITOR_XOFF) / 5 * 5;
			EDITOR_POSY = (MouseHandler.mouseY - EDITOR_YOFF) / 5 * 5;
		}
	}
	
	
	
	
	
	
	
}
