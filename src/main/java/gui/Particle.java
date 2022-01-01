package gui;

import graphics.GraphicsConstants;

import java.util.function.DoubleConsumer;

public class Particle { // TODO WIP

	private DoubleConsumer drawable;
	private int x, y, w, h;
	
	private double time;
	private double timeSteps = 0.1;
	
	
	
	public Particle(Particle p) {
		this.drawable = p.drawable;
		setBounds(p.x, p.y, p.w, p.h);
		this.timeSteps = p.timeSteps;
	}
	
	
	
	public Particle(DoubleConsumer drawable) {
		this.drawable = drawable;
		setBounds(0, 0, GraphicsConstants.REF_SCREEN_SIZE_X, GraphicsConstants.REF_SCREEN_SIZE_Y);
	}
	
	
	public Particle(DoubleConsumer drawable, int x, int y) {
		this.drawable = drawable;
		setBounds(x, y, GraphicsConstants.REF_SCREEN_SIZE_X, GraphicsConstants.REF_SCREEN_SIZE_Y);
	}
	
	
	public Particle(DoubleConsumer drawable, int x, int y, int w, int h) {
		this.drawable = drawable;
		setBounds(x, y, w, h);
	}
	
	
	
	
	public void reset() {
		time = 0d;
	}
	
	
	
	
	
	
	public void drawAndUpdate() {
		draw();
		update();
	}
	
	
	
	public void draw() {
		drawable.accept(time);
	}
	
	
	
	public void update() {
		time += timeSteps;
	}
	
	
	
	
	
	
	public void setDrawable(DoubleConsumer drawable) {
		this.drawable = drawable;
	}
	
	
	
	
	public void setBounds(int x, int y, int w, int h) {
		setPos(x, y);
		setSize(w, h);
	}
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	public void setSize(int w, int h) {
		setWidth(w);
		setHeight(h);
	}
	
	
	public void setWidth(int w) {
		w = w / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.w = Math.abs(w);
	}
	
	public void setHeight(int h) {
		h = h / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		this.h = Math.abs(h);
	}
	
	
	
	
	
	
	public DoubleConsumer getDrawable() {
		return drawable;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
}
