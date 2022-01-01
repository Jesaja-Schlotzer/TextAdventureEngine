package textHandler;

import data.FileHandler;
import graphics.colors.Color;
import graphics.img.Img;
import graphics.img.ImgRaster;

import java.util.function.UnaryOperator;

public class Underline {
	
	// Constants
	
	public static final UnaryOperator<String> standardPath = (String path) -> 
		{
			return FileHandler.PROJECT_PATH + "data\\Font\\Underlines\\" + path;
		};
	
	// End
	
	
	
	public static final int MONOCHROME = 0;
	public static final int GRAY_SCALE = 1;
	public static final int POLYCHROME = 2;
	
	// End
	
	

	// Standard-Underlines
	
	public static final Underline NORMAL_UNDERLINE = 		new Underline(standardPath.apply("gerade.png"));
	public static final Underline NORMAL_LOWER_UNDERLINE =	new Underline(standardPath.apply("gerade_tiefer.png"));
	public static final Underline THICK_UNDERLINE = 		new Underline(standardPath.apply("doppelt_gerade.png"));
	public static final Underline DOUBLE_UNDERLINE = 		new Underline(standardPath.apply("doppelt_mit_spalt.png"));
	public static final Underline DOTED_1_UNDERLINE = 		new Underline(standardPath.apply("gepunktet.png"));
	public static final Underline DOTED_2_UNDERLINE = 		new Underline(standardPath.apply("gestrichelt_2px.png"));
	public static final Underline DOTED_3_UNDERLINE = 		new Underline(standardPath.apply("gestrichelt_3px.png"));
	public static final Underline DOTED_12_UNDERLINE = 		new Underline(standardPath.apply("gestrichelt_1-2px.png"));
	public static final Underline TOOTHED_UNDERLINE = 		new Underline(standardPath.apply("gezackt.png"));
	
	
	
	
	
	private String path;
	protected Color[][] underlineData;
	protected int underlineWidth, underlineHeight;
	private int colorMode;

	
	
	public Underline(String path) {
		this.path = path;

		this.underlineData = ImgRaster.loadImageRaster(path).getRaster();
		
		underlineWidth = underlineData.length;
		underlineHeight = underlineData[0].length;

		colorMode = POLYCHROME;
	}
	
	

	public Underline(String path, int colorMode) {
		this(path);
		this.colorMode = colorMode;
	}

	
	
	
	// copy-Konstruktor
	public Underline(Underline underline) {
		this.path = underline.path;
		this.underlineData = underline.underlineData;
		this.underlineWidth = underline.underlineWidth;
		this.underlineHeight = underline.underlineHeight;
		this.colorMode = underline.colorMode;
	}

	
	
	public int getColorMode() {
		return colorMode;
	}
	

	
	
	public Underline setColorMode(int colorMode) {
		this.colorMode = colorMode;
		if (this.colorMode == POLYCHROME) {
			this.underlineData = ImgRaster.loadImageRaster(path).getRaster();
		}
		if (this.colorMode == GRAY_SCALE) {
			this.underlineData = new Img(ImgRaster.loadImageRaster(path)).asGray().getImgRaster().getRaster();
		}
		if (this.colorMode == MONOCHROME) {
			this.underlineData = new Img(ImgRaster.loadImageRaster(path)).limitImg(256).getImgRaster().getRaster();
		}
		return this;
	}

}