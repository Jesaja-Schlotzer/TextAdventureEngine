package taEngine.styleComponents.materials;

import graphics.colors.Color;

public class ColorMaterial extends Material{
	
	private Color color;
	
	public ColorMaterial(int id, Color color) {
		super(id);
		
		this.color = color;
	}

}
