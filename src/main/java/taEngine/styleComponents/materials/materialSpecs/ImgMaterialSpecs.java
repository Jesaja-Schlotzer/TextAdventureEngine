package taEngine.styleComponents.materials.materialSpecs;

import graphics.img.ImgOperator;

public class ImgMaterialSpecs extends VisualMaterialSpecs{
	
	public ImgOperator imgOperator;
	
	
	
	public ImgMaterialSpecs(int x, int y) {
		super(x, y);
	}
	
	
	public ImgMaterialSpecs(int x, int y, ImgOperator imgOperator) {
		super(x, y);
		
		this.imgOperator = imgOperator;
	}
	
}
