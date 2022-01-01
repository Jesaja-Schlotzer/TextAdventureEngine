package taEngine.styleComponents.materials;

import graphics.Painter;
import graphics.img.Img;
import taEngine.styleComponents.materials.materialSpecs.ImgMaterialSpecs;


public class ImgMaterial extends VisualMaterial{
	
	private Img img;
	
	public ImgMaterial(int id, Img img) {
		super(id);
		
		this.img = img;
	}

	
	
	@Override
	public <T> void draw(T materialSpecs) {
		
	}


	public void draw(ImgMaterialSpecs ims) {
		Painter.drawImage(img, ims.x, ims.y);
		ImgMaterialSpecs i = new ImgMaterialSpecs(0, 0);
	}





}
