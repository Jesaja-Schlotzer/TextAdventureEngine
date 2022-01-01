package gui;

import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.img.ImgOperator;

public class OperatedDrawable {
	private SpecializedDrawable<GCC> drawable = (x, y, gcc) -> {};
	private ImgOperator imgOperator = img -> img;
	private GCC gcc = GCC.NULL;
	
	
	public OperatedDrawable(SpecializedDrawable<GCC> drawable, ImgOperator imgOperator, GCC gcc) {
		if(drawable != null) {
			this.drawable = drawable;
		}
		
		if(imgOperator != null) {
			this.imgOperator = imgOperator;
		}
		
		if(gcc != null) {
			this.gcc = gcc;
		}
	}
	
	
	
	public void draw(int x, int y) {
		Painter.drawImage(imgOperator.apply(drawable.asImg(gcc)), x, y);
	}
	
	
	
	
	
	
	public void setSpecializedDrawable(SpecializedDrawable<GCC> drawable) {
		if(drawable != null) {
			this.drawable = drawable;
		}
	}
	
	
	public void setImgOperator(ImgOperator imgOperator) {
		if(imgOperator != null) {
			this.imgOperator = imgOperator;
		}
	}
	
	
	public void setGCC(GCC gcc) {
		if(gcc != null) {
			this.gcc = gcc;
		}
	}
	
	
	
	
	public SpecializedDrawable<GCC> getSpecializedDrawable() {
		return drawable;
	}
	
	
	public ImgOperator getImgOperator() {
		return imgOperator;
	}
	
	
	public GCC getGCC() {
		return gcc;
	}
	
	
	
	
	public OperatedDrawable andThen(ImgOperator imgOperator) {
		return new OperatedDrawable(drawable, imgOperator.andThen(imgOperator), gcc);
	}
	
	
	
	
	public int getNativeWidth() {
		return imgOperator.apply(drawable.asImg(gcc)).getNativeWidth();
	}
	
	
	public int getNativeHeight() {
		return imgOperator.apply(drawable.asImg(gcc)).getNativeHeight();
	}
	
	
	
	public int getWidth() {
		return imgOperator.apply(drawable.asImg(gcc)).getNativeWidth() * GraphicsConstants.PIXEL_SIZE_X;
	}
	
	
	public int getHeight() {
		return imgOperator.apply(drawable.asImg(gcc)).getNativeHeight() * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
}
