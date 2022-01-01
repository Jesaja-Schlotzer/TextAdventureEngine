package gui;

import graphics.GraphicsConstants;
import input.MouseHandler;


public class HoverArea {
	private int x, y, w, h;
	private Drawable drawable = Drawable.NULL;
	
	
	public HoverArea(HoverArea ha) {
		setBounds(ha.x, ha.y, ha.w, ha.h);
		setDrawable(ha.drawable);
	}
	
	
	
	public HoverArea(int x, int y, int w, int h, Drawable drawable) {
		setBounds(x, y, w, h);
		setDrawable(drawable);
	}
	
	
	
	
	
	
	public void setBounds(int x, int y, int w, int h) {
		setPos(x, y);
		setSize(w, h);
	}
	
	
	
	
	public void setPos(int x, int y) {
		this.x = x/ GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y/ GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	public void setSize(int w, int h) {
		w = w / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		h = h / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		
		this.w = Math.max(w, 1);
		this.h = Math.max(h, 1);
	}
	
	
	
	
	public void setDrawable(Drawable drawable) {
		if(drawable == null) {
			this.drawable = Drawable.NULL;
			return;
		}
		this.drawable = drawable;
	}
	
	
	
	
	public Drawable getDrawable() {
		return drawable;
	}
	
	
	
	public void draw() {
		if(MouseHandler.overButton(x, y, x+w, y+h)) {
			drawable.draw(0, 0);
		}
	}
}