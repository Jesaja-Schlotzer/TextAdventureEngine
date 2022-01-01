package screen;


public interface Screen {
	
	public void draw();
	
	public void mouseClick();
	
	public void mouseWheel(float wheelRotation);
	
	public void keyPressed(short keyCode);
	
	public void keyReleased();
	
	
	public void open();
	
	public void close();
}
