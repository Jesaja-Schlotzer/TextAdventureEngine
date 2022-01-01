package gui;

import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import input.MouseHandler;
import textHandler.TextHandler;

public class Progressbar {
	
	// Constants
	
	public static final int ADDITIONAL_INTERACTION_SPACE = 1; // 1 == 1*PIXEL_SIZE_Y 
				
	// End
			
			
			
	private int x, y, barLength, barWidth;
	private double progress; // 0 - 1
	private Color frameColor, barColor, backgroundColor;

	private HoverArea hoverArea;

	
	
	
	public Progressbar(Progressbar pb) {
		setPos(pb.x, pb.y);
		setBarLength(pb.barLength);
		setBarWidth(pb.barWidth);
		setColors(pb.frameColor, pb.barColor, pb.backgroundColor);
		this.hoverArea = new HoverArea(pb.hoverArea);
	}
	
	
	public Progressbar(int x, int y, int barLength) {	
		setPos(x, y);
		setBarLength(barLength);
		setBarWidth(3*GraphicsConstants.PIXEL_SIZE_Y);

		setColors(Color.ORANGE, Color.GREEN, Color.YELLOW);

		
		hoverArea = new HoverArea(x-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_X), y-(ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), barLength+(2*ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_X), barWidth+(2*ADDITIONAL_INTERACTION_SPACE*GraphicsConstants.PIXEL_SIZE_Y), new Drawable() {
			public Label l = new Label(0, 0, "");
			
			public void draw(int x, int y) {
				l.setPos(MouseHandler.mouseX, MouseHandler.mouseY-((TextHandler.font().getCharHeight()+3) * GraphicsConstants.PIXEL_SIZE_Y));
				l.setContent((int) (progress*100)+"%");
				
				l.draw();
			}
		});
	}
	
	

	
	
	public Progressbar setColors(Color frameColor, Color barColor, Color backgroundColor) {
		this.frameColor = frameColor;
		this.barColor = barColor;
		this.backgroundColor = backgroundColor;
		
		return this;
	}

	
	
	
	
	public void setProgress(float value) {
		this.progress = value;
		progress = Math.min(Math.max(progress, 0), 1);
	}

	
	public void setProgress(int percentage) {
		progress = percentage/100d;
		progress = Math.min(Math.max(progress, 0), 1);
	}
	
	
	
	
	public void updateProgress(double value) {
		progress += value;
		progress = Math.min(Math.max(progress, 0), 1);
	}
	
	
	public void updateProgress(int percentage) {
		progress += percentage/100d;
		progress = Math.min(Math.max(progress, 0), 1);
	}
	
	
	
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	public Progressbar setBarLength(int barLength) {
		this.barLength = Math.max(barLength, 5*GraphicsConstants.PIXEL_SIZE_X);
		
		return this;
	}
	
	
	public Progressbar setBarWidth(int barWidth) {
		this.barWidth = Math.max(barWidth, 3*GraphicsConstants.PIXEL_SIZE_X);
		
		return this;
	}
	
	
	
	
	public double getProgress() {
		return progress;
	}
	
	

	public void draw() {
		Painter.drawRect(x, y, barLength, barWidth, backgroundColor, frameColor);
		Painter.drawRect(x + GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y,
				(int) (progress * (barLength-(2*GraphicsConstants.PIXEL_SIZE_X))),
				barWidth - (2*GraphicsConstants.PIXEL_SIZE_Y), barColor);

		hoverArea.draw();
	}

}
