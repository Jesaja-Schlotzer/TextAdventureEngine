package taEngine.game;

import taEngine.editor.Editor;
import taEngine.editor.logSystem.EditorLogSystem;

public class DecisionObj {
	private int relatingGroup; // TODO eig. unnötig
	
	private String text;
	
	private int followingGroup;
	
	
	
	
	public DecisionObj(String text, int relatingGroup, int followingGroup){
		this.followingGroup = followingGroup;
		this.text = text;
		
		this.relatingGroup = relatingGroup;
	}
	
	
	
	
	
	public int getRelatingGroup() {
		return relatingGroup;
	}
	
	
	public String getText() {
		return text;
	}
	
	
	public int getFollowingGroup() {
		return followingGroup;
	}
	
	
	
	
	
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	public void setFollowing(int followingGroup) { // TODO früher oder später anders machen weil groupList.size() nicht direkt mit den GroupNr'n in Verbindung steht
		if(followingGroup < 0 || followingGroup > Editor.groupList.size()-1) {
			EditorLogSystem.getInstance().addWarning("Angegebene Gruppe existiert nicht. GroupNr:"+followingGroup);
		}else {
			this.followingGroup = followingGroup;
		}
	}
	
	
}







