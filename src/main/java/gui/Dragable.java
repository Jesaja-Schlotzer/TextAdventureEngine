package gui;

import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.img.Img;
import input.MouseHandler;
import textHandler.StyledString;
import textHandler.TextHandler;

public class Dragable {
	private int x, y, w, h, xoff, yoff;
	private boolean dragging;
	private GCC content = GCC.NULL;
	
	
	
	public Dragable(Dragable da) {
		this.content = new GCC(da.content);
		setPos(da.x, da.y);
	}
	
	
	
	
	public Dragable(String string) {
		this(new GCC(string));
	}
	
	public Dragable(int x, int y, String string) {
		this(x, y, new GCC(string));
	}
	
	
	
	public Dragable(StyledString styledString) {
		this(new GCC(styledString));
	}
	
	public Dragable(int x, int y, StyledString styledString) {
		this(x, y, new GCC(styledString));
	}
	
	
	
	public Dragable(Img img) {
		this(new GCC(img));
	}
	
	public Dragable(int x, int y, Img img) {
		this(x, y, new GCC(img));
	}
	
	
	
	public Dragable(Animation animation) {
		this(new GCC(animation));
	}
	
	public Dragable(int x, int y, Animation animation) {
		this(x, y, new GCC(animation));
	}
	
	
	
	public Dragable(Drawable drawable) {
		this(new GCC(drawable));
	}
	
	public Dragable(int x, int y, Drawable drawable) {
		this(x, y, new GCC(drawable));
	}
	
	
	
	
	public Dragable(GCC gcc) {
		this.content = (gcc == null ? GCC.NULL : gcc);
		setSize(content.getWidth(), content.getHeight());
	}
	
	public Dragable(int x, int y, GCC gcc) {
		setPos(x, y);
		this.content = (gcc == null ? GCC.NULL : gcc);
		setSize(content.getWidth(), content.getHeight());
	}
	
	
	
	
	
	
	public void setContent(String string){
		setContent(string);
	}
	
	
	public void setContent(StyledString styledString){
		setContent(styledString);
	}
	
	
	public void setContent(Img img){
		setContent(img);
	}
	
	
	public void setContent(Animation animation){
		setContent(animation);
	}
	
	
	public void setContent(Drawable drawable){
		setContent(drawable);
	}
	
	
	
	public void setContent(GCC gcc){
		this.content = (gcc == null ? GCC.NULL : gcc);
		setSize(content.getWidth(), content.getHeight());
	}
	
	
	
	
	
	
	public Dragable setBounds(int x, int y, int w, int h) {
		setPos(x, y);
		setSize(w, h);
		
		return this;
	}
	
	
	
	public Dragable setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		return this;
	}
	
	
	
	public Dragable setSize(int w, int h) {
		w = w  / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		h = h  / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		this.w = Math.max(w, content.getWidth());
		this.h = Math.max(h, content.getHeight());
		
		return this;
	}
	
	
	
	
	
	public void draw() {
		content.draw(x, y, TextHandler.getTextColor());
		
		if(MouseHandler.mousePressed && dragging) {
			dragging = true;
		}else if((MouseHandler.mousePressed && MouseHandler.overButton(x, y, x+w, y+h))) {
			xoff = MouseHandler.mouseX - x;
			yoff = MouseHandler.mouseY - y;
			dragging = true;
		}else{
			dragging = false;
		}
		
		if (dragging) {
			setPos((MouseHandler.mouseX - xoff), (MouseHandler.mouseY - yoff));
		}
	}
}
