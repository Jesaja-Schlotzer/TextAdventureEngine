package gui;

import graphics.GraphicsConstants;
import graphics.img.Img;
import screen.fragmentRecorder.FragmentRecorderPool;
import screen.fragmentRecorder.RecordMode;

public interface SpecializedDrawable<T> {
	
	public static final SpecializedDrawable<Object> NULL = (x, y, typ) -> {};
	
	
	public void draw(int x, int y, T t);
	
	
	
	
	
	

	
	// Default Methoden
	
	public default Img asImg(T t) {
		FragmentRecorderPool.getInstance().begin("gui/SpecializedDrawable<T>/asImg(T)", RecordMode.ONLY_DRAW_ON_BUFFER);
		draw(GraphicsConstants.REF_SCREEN_SIZE_X+100, GraphicsConstants.REF_SCREEN_SIZE_Y+100, t);
		FragmentRecorderPool.getInstance().end("gui/SpecializedDrawable<T>/asImg(T)");
		System.out.println(FragmentRecorderPool.getInstance().getRecord("gui/Drawable/asImg(Drawable)").toString());
		return FragmentRecorderPool.getInstance().getRecordAndRemove("gui/SpecializedDrawable<T>/asImg(T)");
	}
	
	
	public default int getNativeWidth(T t) {
		return asImg(t).getNativeWidth();
	}
	
	public default int getWidth(T t) {
		return asImg(t).getWidth();
	}
	
	
	public default int getNativeHeight(T t) {
		return asImg(t).getNativeHeight();
	}
	
	public default int getHeight(T t) {
		return asImg(t).getHeight();
	}
	
	
	
	
	// Statische Methoden nicht möglich da 'T' nicht statisch ist
}
