package taEngine.game;

import graphics.Painter;
import graphics.colors.Color;
import gui.ScrollArea;
import input.MouseHandler;
import screen.ScreenHandler;
import screen.Screens;
import taEngine.TAColors;
import textHandler.TextHandler;

public final class Game {
	
	public static Group[] groupList;
	
	static int sessionStartTime;
	
	static int currentGroup;
	// TODO currentGroup von int zu Group wechseln
	
	
	
	static ScrollArea storyTextArea = new ScrollArea(0, 0, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY);
	static ScrollArea[] decisionWayTextAreas = {
				new ScrollArea(0, 0, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY), 
				new ScrollArea(0, 0, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY), 
				new ScrollArea(0, 0, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY),
				new ScrollArea(0, 0, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY),
				new ScrollArea(0, 0, "").setColors(TAColors.DARK_GRAY, Color.NO_COLOR, Color.NO_COLOR).setScrollBarColors(Color.NO_COLOR, TAColors.DARK_GRAY)
			};
	
	
	
	public static void editorQuickPlay(taEngine.editor.Group[] groups) { // only temporary
		initNewGame(groups.length);
		
		for(int i = 0; i < groupList.length; i++) {
			groupList[i] = new Group(groups[i].getGroupNr(), groups[i].getGroupText(), groups[i].getDecWays());
		}
		
		currentGroup = 0;
		
		initNextGroup();
		
		ScreenHandler.getInstance().requestNextScreen(Screens.game);
	}
	
	
	
	public static void initNewGame(int groupCount) {
		groupList = new Group[groupCount];
		
		
	}
	
	
	
	public static void startGame() {
		sessionStartTime = (int) System.currentTimeMillis()/1000;
		currentGroup = Integer.parseInt(GameHandler.getSave()[4]);
		
		initNextGroup();
	}
	
	
	
	
	public static Group getCurrentGroup() {
		return groupList[currentGroup];
	}
	
	
	
	public static int getCurrentGroupNr() {
		return currentGroup;
	}
	
	public static int getSessionStartTime() {
		return sessionStartTime;
	}
	
	
	
	
	
	
	
	public static void initNextGroup() {
		Group g = getCurrentGroup();
		storyTextArea.setContent(g.text);
		storyTextArea.setBounds(g.layout.storyTextBounds().x, g.layout.storyTextBounds().y, g.layout.storyTextBounds().w, g.layout.storyTextBounds().h);
		
		for(int i = 0; i < getCurrentGroup().decObj.length; i++) {
			decisionWayTextAreas[i].setContent(g.decObj[i].getText());
			decisionWayTextAreas[i].setBounds(g.layout.decisionWayBounds(i).x, g.layout.decisionWayBounds(i).y, g.layout.decisionWayBounds(i).w, g.layout.decisionWayBounds(i).h);
		}
	}
	
	
	
	
	
	public static void draw() {
		TextHandler.lineSpacing(3);
		storyTextArea.draw();
		TextHandler.lineSpacing(2);
		
		for(int i = 0; i < getCurrentGroup().decObj.length; i++) {
			/*Nur temporär*/ Painter.drawRect(20, 730+(i*50), 1870, 55, Color.NO_COLOR, TAColors.MIDDLE_GRAY);
			decisionWayTextAreas[i].draw();
		}
	}
	
	
	
	public static void handleMouseClick() {
		for(int i = 0; i < getCurrentGroup().decObj.length; i++) {
			if(MouseHandler.overButton(
					
						getCurrentGroup().layout.decisionWayBounds(i).x, 
						getCurrentGroup().layout.decisionWayBounds(i).y,
						getCurrentGroup().layout.decisionWayBounds(i).x + getCurrentGroup().layout.decisionWayBounds(i).w,
						getCurrentGroup().layout.decisionWayBounds(i).y + getCurrentGroup().layout.decisionWayBounds(i).h					
					)) {
				
				Game.currentGroup = getCurrentGroup().decObj[i].getFollowingGroup();
				
				initNextGroup();
			}
		}
	}
	
	
	
	public static void handleMouseWheel(float wheelRotation) {
		storyTextArea.handleMouseWheel(wheelRotation);
		
		for(int i = 0; i < getCurrentGroup().decObj.length; i++) {
			decisionWayTextAreas[i].handleMouseWheel(wheelRotation);
		}
	}
	
	
	
	
	
	static class Save {
		
		
		
		
		
		static void loadSave(String path) {
			
		}
		
		
	}
	
}
