package taEngine.styleComponents.materials;

import graphics.Animation;
import graphics.Painter;

public class AnimationMaterial extends VisualMaterial{

	private Animation animation;
	
	public AnimationMaterial(int id, Animation animation) {
		super(id);
		
		this.animation = animation;
	}


	public void draw(int x, int y) {
		Painter.drawLoopedAnimation(animation, x, y);
	}


	@Override
	public <T> void draw(T materialSpecs) {

	}
}
