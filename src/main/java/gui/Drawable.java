package gui;


import graphics.GraphicsConstants;
import graphics.img.Img;
import graphics.img.ImgOperator;
import screen.fragmentRecorder.FragmentRecorderPool;
import screen.fragmentRecorder.RecordMode;


//@FunctionalInterface
public interface Drawable {
	
	public static final Drawable NULL = (x, y) -> {};
	
	
	public void draw(int x, int y);
	
	
	
	

	
	
	
	// Default Methoden
	
	
	// Operator-Methode
	
	
	public default OperatedDrawable operate(ImgOperator imgOperator) {
		return new OperatedDrawable(this.asSpecializedDrawable(null), imgOperator, GCC.NULL);
	}
	
	
	
	
	public default Img asImg() {
		return asImg(this);
	}
	
	
	public default int getNativeWidth() {
		return getNativeWidth(this);
	}
	
	public default int getWidth() {
		return getWidth(this);
	}
	
	
	public default int getNativeHeight() {
		return getNativeHeight(this);
	}
	
	public default int getHeight() {
		return getHeight(this);
	}
	
	
	
	
	public default <T> SpecializedDrawable<T> asSpecializedDrawable(T t){
		return (x, y, type) -> draw(x, y);
	}
	
	
	
	
	// Statische Methoden 
	
	public static Img asImg(Drawable drawable) {
		FragmentRecorderPool.getInstance().begin("gui/Drawable/asImg(Drawable)", RecordMode.ONLY_DRAW_ON_BUFFER | RecordMode.DEBUG_MODE_OFF);
		drawable.draw(GraphicsConstants.REF_SCREEN_SIZE_X+1, GraphicsConstants.REF_SCREEN_SIZE_Y+1);
		FragmentRecorderPool.getInstance().end("gui/Drawable/asImg(Drawable)");
		return FragmentRecorderPool.getInstance().getRecordAndRemove("gui/Drawable/asImg(Drawable)");
	}
	
	
	
	public static int getNativeWidth(Drawable drawable) {
		return asImg(drawable).getNativeWidth();
	}
	
	
	public static int getWidth(Drawable drawable) {
		return asImg(drawable).getWidth();
	}
	
	
	
	public static int getNativeHeight(Drawable drawable) {
		return asImg(drawable).getNativeHeight();
	}
	
	
	public static int getHeight(Drawable drawable) {
		return asImg(drawable).getHeight();
	}
}
