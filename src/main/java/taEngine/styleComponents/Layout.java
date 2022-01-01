package taEngine.styleComponents;


public class Layout {
	
	// STANDARD-LAYOUT
	
	public static final Layout STANDARD_LAYOUT = new Layout("15,80,1890,550;"
														+	"30,735,1860,50;"
														+   "30,785,1860,50;"
														+   "30,840,1860,50;"
														+   "30,895,1860,50;"
														+   "30,950,1860,50");
	
	
	
	/*		Layout
	 *  Ist dafür zuständig 
	 *  wo Texte angezeigt
	 *  werden
	 */
	
	
	
	private Bounds storyTextBounds;
	private Bounds[] decisionWayBounds = new Bounds[5];
	
	
	public Layout(String data) {
		String[] seperateBounds = data.split("[;]");
		
		storyTextBounds = new Bounds(seperateBounds[0]);
		
		for(int i = 1; i < seperateBounds.length; i++) {
			decisionWayBounds[i-1] = new Bounds(seperateBounds[i]);
		}
	}
	
	
	
	
	
	public Bounds storyTextBounds() {
		return storyTextBounds;
	}
	
	
	public Bounds decisionWayBounds(int i) {
		if(i < 0 || i > 4) {
			return decisionWayBounds[4];
		}
		return decisionWayBounds[i];
	}
	
	
	
	
	@Override
	public String toString() {
		return "Layout[ST="+storyTextBounds.toString()+",DW1="+decisionWayBounds[0].toString()+",DW2="+decisionWayBounds[1].toString()+",DW3="+decisionWayBounds[2].toString()+",DW4="+decisionWayBounds[3].toString()+",DW5="+decisionWayBounds[4].toString()+"]";
	}
	
	
	
	public class Bounds{
		public final int x, y, w, h;
		
		Bounds(String boundsData){
			String[] seperateValues = boundsData.split("[,]");
			
			this.x = Integer.parseInt(seperateValues[0]);
			this.y = Integer.parseInt(seperateValues[1]);
			this.w = Integer.parseInt(seperateValues[2]);
			this.h = Integer.parseInt(seperateValues[3]);
		}
		
		
		
		@Override
		public String toString() {
			return "Bounds[x="+x+",y="+y+",w="+w+",h="+h+"]@"+hashCode();
		}
		
		@Override
		public int hashCode() {
			return x << 24 + y << 16 + w << 8 + h;
		}
	}
}
