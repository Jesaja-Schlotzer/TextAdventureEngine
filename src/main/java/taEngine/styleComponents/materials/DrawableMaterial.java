package taEngine.styleComponents.materials;

import gui.Drawable;

public class DrawableMaterial extends VisualMaterial{

	private Drawable drawable;
	
	public DrawableMaterial(int id, Drawable drawable) {
		super(id);
		
		this.drawable = drawable;
	}

	
	
	

	public void draw(int x, int y) {
		drawable.draw(x, y);
	}


	@Override
	public <T> void draw(T materialSpecs) {

	}
}
