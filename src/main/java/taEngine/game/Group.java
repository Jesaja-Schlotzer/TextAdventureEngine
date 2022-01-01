package taEngine.game;


import taEngine.styleComponents.Layout;


public final class Group {
	
	final String text;
	
	final DecisionObj[] decObj;

	final int groupNr;
	
	
	final Layout layout = Layout.STANDARD_LAYOUT;
	
	

	// Für später
	
	// Background background;
	
	
	Group(int groupNr, String groupText, DecisionObj[] decObjs){
		this.groupNr = groupNr;
		this.text = groupText;
		this.decObj = decObjs;
	}
	
	
	Group(String[] groupData, int groupNr){
		this.text = groupData[0].split("[;]")[0];
		
		this.decObj = new DecisionObj[Integer.parseInt(groupData[0].split("[;]")[1])];
		
		for(int i = 0; i < decObj.length; i++) {
			this.decObj[i] = new DecisionObj(groupData[i+1].split("[;]")[0], groupNr, Integer.parseInt(groupData[i+1].split("[;]")[1]));
		}
		
		
		this.groupNr = groupNr;
	}
	
	
	
	@Override
	public String toString() {
		return "game.Group[GruppenNr="+groupNr+",Entscheidungsweg-Anzahl="+decObj.length+",Layout="+layout.toString()+"]@"+hashCode();
	}
	
	
	@Override
	public int hashCode() {
		return groupNr << 8 + decObj.length;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(this.toString().equals(((Group) obj).toString())) {
			return true;
		}else {
			return false;
		}
	}
}
