package gui;

public interface Layout<T>{
	
	public void drawIdle(T t);
	
	public void drawFocused(T t);
	
	public void drawHover(T t);
	
	public void drawPressing(T t);
	
	public void drawLocked(T t);
	
	public void drawExtra(T t);
}
