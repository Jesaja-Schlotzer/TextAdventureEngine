package gui;

public class LayoutAdapter<T> implements Layout<T>{
	
	@Override
	public void drawIdle(T t) {}
	
	@Override
	public void drawFocused(T t) {}
	
	@Override
	public void drawHover(T t) {}
	
	@Override
	public void drawPressing(T t) {}
	
	@Override
	public void drawLocked(T t) {}
	
	@Override
	public void drawExtra(T t) {}
}