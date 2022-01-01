package graphics.img;

import java.util.function.UnaryOperator;

public interface ImgOperator extends UnaryOperator<Img>{
	
	// apply-Methode in UnaryOperator
	
	
	// Default-Methoden
	
	public default ImgOperator andThen(ImgOperator imgOperator) {
		return (Img img) -> imgOperator.apply(apply(img));
	}
	
	
	// Standard-Operatoren
	
	public static final ImgOperator NULL = img -> img;
	
	
	public static final ImgOperator SCALE_2X = img -> img.scaleImg(2);
	public static final ImgOperator SCALE_3X = img -> img.scaleImg(3);
	public static final ImgOperator SCALE_4X = img -> img.scaleImg(4);
	
	
	public static final ImgOperator AS_GRAY = Img::asGray;
	
}
