package taEngine.editor;

import data.FileHandler;
import graphics.Painter;
import gui.Drawable;
import gui.SpecializedDrawable;
import input.MouseHandler;
import taEngine.TAColors;
import taEngine.game.DecisionObj;
import textHandler.Font;
import textHandler.TextHandler;

import java.util.ArrayList;


public class Group{
	
	
	public static Group DEBUG_NULL_GROUP = new Group("");
	
	
	
	// DecWay Visualisations
	
	static final Drawable[][] DECWAY_SCALING_VISUALISATIONS = {
			
			// Index für "Only Group - small"
			{
				// "Only Group - small"  -  Nur ein (1) DecWay ist gesetzt
				(x, y) -> {
					Painter.drawPoint(x, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+5, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+10, y, 95, 5, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawPoint(x+105, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+110, y, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - small"  -  Zwei (2) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawPoint(x, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+5, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+10, y, 45, 5, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawPoint(x+55, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+60, y, 45, 5, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawPoint(x+105, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+110, y, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - small"  -  Drei (3) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawPoint(x, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+5, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+10, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+15, y, 25, 5, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawPoint(x+40, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+45, y, 25, 5, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawPoint(x+70, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+75, y, 25, 5, Editor.DECISION_WAY_COLORS[2]); // Gelb
					Painter.drawPoint(x+100, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+105, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+110, y, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - small"  -  Vier (4) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawPoint(x, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+5, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+10, y, 20, 5, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawPoint(x+30, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+35, y, 20, 5, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawPoint(x+55, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+60, y, 20, 5, Editor.DECISION_WAY_COLORS[2]); // Gelb
					Painter.drawPoint(x+80, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+85, y, 20, 5, Editor.DECISION_WAY_COLORS[3]); // Grün
					Painter.drawPoint(x+105, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+110, y, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - small"  -  Fünf (5) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawPoint(x, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+5, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+10, y, 15, 5, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawPoint(x+25, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+30, y, 15, 5, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawPoint(x+45, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+50, y, 15, 5, Editor.DECISION_WAY_COLORS[2]); // Gelb
					Painter.drawPoint(x+65, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+70, y, 15, 5, Editor.DECISION_WAY_COLORS[3]); // Grün
					Painter.drawPoint(x+85, y, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+90, y, 15, 5, Editor.DECISION_WAY_COLORS[4]); // Blau
					Painter.drawPoint(x+105, y, TAColors.MIDDLE_GRAY);
					Painter.drawPoint(x+110, y, TAColors.MIDDLE_GRAY);
				}
			},
			
			
			
			
			
			// Index für "Only Group - 2x"
			{
				// "Only Group - 2x"  -  Ein (1) DecWay ist gesetzt
				(x, y) -> {
					Painter.drawRect(x, y, 20, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+20, y, 190, 10, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawRect(x+210, y, 20, 10, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - 2x"  -  Zwei (2) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawRect(x, y, 20, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+20, y, 90, 10, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawRect(x+110, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+120, y, 90, 10, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawRect(x+210, y, 20, 10, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - 2x"  -  Drei (3) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawRect(x, y, 30, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+30, y, 50, 10, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawRect(x+80, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+90, y, 50, 10, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawRect(x+140, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+150, y, 50, 10, Editor.DECISION_WAY_COLORS[2]); // Gelb
					Painter.drawRect(x+200, y, 30, 10, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - 2x"  -  Vier (4) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawRect(x, y, 20, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+20, y, 40, 10, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawRect(x+60, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+70, y, 40, 10, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawRect(x+110, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+120, y, 40, 10, Editor.DECISION_WAY_COLORS[2]); // Gelb
					Painter.drawRect(x+160, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+170, y, 40, 10, Editor.DECISION_WAY_COLORS[3]); // Grün
					Painter.drawRect(x+210, y, 20, 10, TAColors.MIDDLE_GRAY);
				},
				
				// "Only Group - 2x"  -  Fünf (5) DecWay sind gesetzt
				(x, y) -> {
					Painter.drawRect(x, y, 20, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+20, y, 30, 10, Editor.DECISION_WAY_COLORS[0]); // Rot
					Painter.drawRect(x+50, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+60, y, 30, 10, Editor.DECISION_WAY_COLORS[1]); // Orange
					Painter.drawRect(x+90, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+100, y, 30, 10, Editor.DECISION_WAY_COLORS[2]); // Gelb
					Painter.drawRect(x+130, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+140, y, 30, 10, Editor.DECISION_WAY_COLORS[3]); // Grün
					Painter.drawRect(x+170, y, 10, 10, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+180, y, 30, 10, Editor.DECISION_WAY_COLORS[4]); // Blau
					Painter.drawRect(x+210, y, 20, 10, TAColors.MIDDLE_GRAY);
				}
			},
			
			
			
			
			
			
			// Index für "Group & DecWays - small"
			{
				// Ein (1) DecWay
				(x, y) -> {
					Painter.drawRect(x, y, 255, 55, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.drawString("1", x+125, y+10, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					Painter.drawRect(x+5, y+40, 245, 10, Editor.DECISION_WAY_COLORS[0]);
				},
				
				
				// Zwei (2) DecWays
				(x, y) -> {
					Painter.drawRect(x, y, 255, 55, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.drawString("1       2", x+65, y+10, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					Painter.drawRect(x+5, y+40, 120, 10, Editor.DECISION_WAY_COLORS[0]);
					Painter.drawRect(x+125, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+130, y+40, 120, 10, Editor.DECISION_WAY_COLORS[1]);
				},
				
				
				// Drei (3) DecWays
				(x, y) -> {
					Painter.drawRect(x, y, 255, 55, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.drawString("1  "+TextHandler.ONE_SPACE+"  2    3", x+45, y+10, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					Painter.drawRect(x+5, y+40, 80, 10, Editor.DECISION_WAY_COLORS[0]);
					Painter.drawRect(x+85, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+90, y+40, 75, 10, Editor.DECISION_WAY_COLORS[1]);
					Painter.drawRect(x+165, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+170, y+40, 80, 10, Editor.DECISION_WAY_COLORS[2]);
				},
				
				
				// Vier (4) DecWays
				(x, y) -> {
					Painter.drawRect(x, y, 255, 55, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.drawString("1   2 "+TextHandler.space(2)+" 3 "+TextHandler.space(2)+" 4", x+35, y+10, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					Painter.drawRect(x+5, y+40, 60, 10, Editor.DECISION_WAY_COLORS[0]);
					Painter.drawRect(x+65, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+70, y+40, 55, 10, Editor.DECISION_WAY_COLORS[1]);
					Painter.drawRect(x+125, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+130, y+40, 55, 10, Editor.DECISION_WAY_COLORS[2]);
					Painter.drawRect(x+185, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+190, y+40, 60, 10, Editor.DECISION_WAY_COLORS[3]);
				},
				
				
				// Vier (5) DecWays
				(x, y) -> {
					Painter.drawRect(x, y, 255, 55, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.drawString("1 "+TextHandler.ONE_SPACE+" 2  3  4  5", x+25, y+10, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					Painter.drawRect(x+5, y+40, 45, 10, Editor.DECISION_WAY_COLORS[0]);
					Painter.drawRect(x+50, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+55, y+40, 45, 10, Editor.DECISION_WAY_COLORS[1]);
					Painter.drawRect(x+100, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+105, y+40, 45, 10, Editor.DECISION_WAY_COLORS[2]);
					Painter.drawRect(x+150, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+155, y+40, 45, 10, Editor.DECISION_WAY_COLORS[3]);
					Painter.drawRect(x+200, y+5, 5, 45, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x+205, y+40, 45, 10, Editor.DECISION_WAY_COLORS[4]);
				}
				
			}
	};
		
		
	
	
	
	// Group Drawables
	
	static final GroupDrawable[] GROUP_SCALING_VISUALISATIONS = new GroupDrawable[] {
			
			// Only Group - small
			(int x, int y, Group g) -> {
					Painter.drawRect(x, y, 115, 45, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.textAlign(TextHandler.RIGHT);
					TextHandler.drawString(g.getGroupNr()+1, x+105, y+10, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					TextHandler.textAlign(TextHandler.LEFT);
					if(Editor.SHOW_DECWAY_EXTENSION) {
						DECWAY_SCALING_VISUALISATIONS[0][g.getDecWayCount()-1].draw(x, y+45);
					}
				},
			
			
			
			// Only Group - 2x
			(int x, int y, Group g) -> {
					//Painter.drawRect(x, y, 230, 90, 2, Color.TRANSPARENT, TAColors.MIDDLE_GRAY);
					Painter.drawRect(x, y, 230, 90, 2, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.textAlign(TextHandler.RIGHT);
					TextHandler.drawString(g.getGroupNr()+1, x+210, y+20, TAColors.DARK_GRAY, Font.BIG_PIXEL_FONT);
					TextHandler.textAlign(TextHandler.LEFT);
					if(Editor.SHOW_DECWAY_EXTENSION) {
						DECWAY_SCALING_VISUALISATIONS[1][g.getDecWayCount()-1].draw(x, y+90);
					}
				},
			
			
			
			// Group & DecWays - small
			(int x, int y, Group g) -> {
					Painter.drawRect(x, y, 255, 55, TAColors.WHITE, TAColors.MIDDLE_GRAY);
					TextHandler.drawString("gruppe", x+15, y+15, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					TextHandler.textAlign(TextHandler.RIGHT);
					TextHandler.drawString(g.getGroupNr()+1, x+240, y+15, TAColors.DARK_GRAY, Font.PIXEL_FONT);
					TextHandler.textAlign(TextHandler.LEFT);
					if(Editor.SHOW_DECWAY_EXTENSION) {
						DECWAY_SCALING_VISUALISATIONS[2][g.getDecWayCount()-1].draw(x, y+50);
					}
			}
			
	};
	
	
	
	
	// End

	String text;
	int posX, posY;
	int xOff, yOff;
	
	static int groupVisualisationWidth = 51*5, groupVisualisationHeight = 21*5;
	
	boolean move;
	
	ArrayList<DecisionObj> decObj = new ArrayList<DecisionObj>();
	
	int groupNr, editingDecision;
	
	
	
	public Group(String Text){
	    this.text = Text;
	    
	    this.groupNr = Editor.groupList.size();
	    
	    this.posX = -Editor.EDITOR_POSX+500;
	    this.posY = 450-Editor.EDITOR_POSY+(Editor.groupList.size()*10);
	    
	    addDecisionObj("", groupNr, -1);
	  }
	
	
	
	
	

	void draw(){
		GROUP_SCALING_VISUALISATIONS[Editor.ZOOM_LEVEL].draw(posX+Editor.EDITOR_POSX, posY+Editor.EDITOR_POSY, this);
		
		
	    TextHandler.textColor(TAColors.DARK_GRAY);
	    
	    for(int i = 0; i < decObj.size(); i++){
	    	if(decObj.get(i).getFollowingGroup() != -1){
	    		//Painter.drawPoint(this.posX+Editor.EDITOR_POSX+(25+(i*(Group.groupVisualisationWidth/decObj.size()))), this.posY+Editor.EDITOR_POSY + Group.groupVisualisationHeight, (Editor.SHOW_COLORED_LINES ? Editor.DECISION_WAY_COLORS[i] : TAColors.DARK_GRAY));
	    		Painter.drawLine(this.posX+Editor.EDITOR_POSX+(25+(i*(Group.groupVisualisationWidth/decObj.size()))), 
	    				this.posY+Editor.EDITOR_POSY /*+ GraphicsConstants.PIXEL_SIZE_Y*/ + Group.groupVisualisationHeight, 
	    				Editor.groupList.get(decObj.get(i).getFollowingGroup()).posX+Editor.EDITOR_POSX+127, 
	    				Editor.groupList.get(decObj.get(i).getFollowingGroup()).posY+Editor.EDITOR_POSY, 
	    				(Editor.SHOW_COLORED_LINES ? Editor.DECISION_WAY_COLORS[i] : TAColors.DARK_GRAY));
	    		
	    		
	    		/*
	    		Painter.drawTriangle(this.posX+Editor.EDITOR_POSX+(i*(Group.groupVisualisationWidth/decObj.size())), 
	    				this.posY+Editor.EDITOR_POSY + Group.groupVisualisationHeight, 
	    				Editor.groupList.get(decObj.get(i).getFollowingGroup()).posX+Editor.EDITOR_POSX+127, 
	    				Editor.groupList.get(decObj.get(i).getFollowingGroup()).posY+Editor.EDITOR_POSY,
	    				this.posX+Editor.EDITOR_POSX+((i+1)*(Group.groupVisualisationWidth/decObj.size())),
	    				this.posY+Editor.EDITOR_POSY + Group.groupVisualisationHeight,
	    				(Editor.SHOW_COLORED_LINES ? Editor.DECISION_WAY_COLORS[i] : TAColors.DARK_GRAY));
	    		*/
	    	}
	    }
	}

	
	
	
	void move() {
		if (MouseHandler.isPressed(MouseHandler.LEFT_MOUSE_BUTTON) && move) {
			move = true;
		} else if (MouseHandler.isPressed(MouseHandler.LEFT_MOUSE_BUTTON)
						&& MouseHandler.overButton(posX + Editor.EDITOR_POSX, posY + Editor.EDITOR_POSY,
						posX + Editor.EDITOR_POSX + GROUP_SCALING_VISUALISATIONS[Editor.ZOOM_LEVEL].getWidth(this), // + group[decObj.size() - 1].getWidth(),
						posY + Editor.EDITOR_POSY + GROUP_SCALING_VISUALISATIONS[Editor.ZOOM_LEVEL].getHeight(this))){ // + group[decObj.size() - 1].getHeight())) {
			move = true;
			xOff = MouseHandler.mouseX - posX;
			yOff = MouseHandler.mouseY - posY;
		} else {
			move = false;
		}

		if (move) {
			posX = (MouseHandler.mouseX - (xOff)) / 5 * 5;
			posY = (MouseHandler.mouseY - (yOff)) / 5 * 5;

		}
	}

	


	
	
	
	int checkDecWays() {
		for (int i = 0; i < decObj.size(); i++) {
			if (MouseHandler.overButton(posX + (i * (groupVisualisationWidth / decObj.size())) + Editor.EDITOR_POSX,
					posY + 50 + Editor.EDITOR_POSY,
					posX + (groupVisualisationWidth / decObj.size()) + (i * (groupVisualisationWidth / decObj.size())) + Editor.EDITOR_POSX, 
					posY + groupVisualisationHeight + Editor.EDITOR_POSY)) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	
	public void addDecisionObj(String Text, int RelatingGroup, int FollowingGroup) {
		decObj.add(new DecisionObj(Text, RelatingGroup, FollowingGroup));
	}

	void editDecisionObj() {

	}

	void removeDecisionObj(int index) {
		decObj.remove(index);
	}
	
	
	
	
	

	public int getGroupNr() {
		return groupNr;
	}
	
	
	public String getGroupText() {
		return text;
	}
	
	
	public int getDecWayCount() {
		return decObj.size();
	}
	
	
	public DecisionObj[] getDecWays() {
		return decObj.toArray(new DecisionObj[getDecWayCount()]);
	}
	
	
	

	
	
	
	
	
	
	public static void updateZoom() {
		Group.groupVisualisationWidth  = Group.GROUP_SCALING_VISUALISATIONS[Editor.ZOOM_LEVEL].getWidth(Editor.groupList.get(0));//Group.DEBUG_NULL_GROUP);
		Group.groupVisualisationHeight = Group.GROUP_SCALING_VISUALISATIONS[Editor.ZOOM_LEVEL].getHeight(Editor.groupList.get(0));//Group.DEBUG_NULL_GROUP);
		
		FileHandler.saveImage(Group.GROUP_SCALING_VISUALISATIONS[Editor.ZOOM_LEVEL].asImg(DEBUG_NULL_GROUP), "png", "D:\\Desktop\\TAEDEBUG_img"+Editor.ZOOM_LEVEL+".png");
	}
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "game.Group[GruppenNr="+groupNr+",Entscheidungsweg-Anzahl="+decObj.size()+"]@"+hashCode();
	}
	
	
	@Override
	public int hashCode() {
		return groupNr << 8 + decObj.size(); // TODO lol wtf xD needs makeover
	}
	
	
	
	@Override
	public boolean equals(Object obj) { // TODO instanceof and null check
		return this.toString().equals(((Group) obj).toString());
	}
	
	
	
	
	
	
	
	
	
	private interface GroupDrawable extends SpecializedDrawable<Group>{}
}
