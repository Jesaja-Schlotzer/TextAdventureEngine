package taEngine.styleComponents.materials;

public abstract class VisualMaterial extends Material{

	public VisualMaterial(int id) {
		super(id);
	}
	
	
	
	
	public abstract <T> void draw(T materialSpecs);
}