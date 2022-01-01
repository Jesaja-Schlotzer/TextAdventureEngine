package graphics.img;

import data.FileHandler;
import debugging.Debugger;
import graphics.Painter;
import graphics.colors.Color;
import util.EventTimer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;


public class Img {
	
	
	// Constants
	
	public static final UnaryOperator<String> standardPath = (String path) -> {
			return FileHandler.PROJECT_PATH + "images\\staticImages\\" + path;
		};
		
		
	public static final int STANDARD_TTL = 7500;
	
	
	
	
	
	public static Img EMPTY_IMG = new Img(new ImgRaster(new Color[][] {{}}));
	
	
	// End
	
	// TODO nur zum Debuggen
	private String sauce;
	
	
	private ImgRasterSupplier imgRasterSupplier = ImgRasterSupplier.STANDARD;
	
	private ImgRaster imgRaster;
	
	protected EventTimer imgClearTimerRef;
	
	
	
	
	
	public Img(Supplier<Img> imgSupplier) {
		this.imgRasterSupplier = () -> imgSupplier.get().getImgRaster();
		
		this.sauce = imgSupplier.toString();
	}
	
	
	
	public Img(ImgRasterSupplier imgRasterSupplier) {
		this.imgRasterSupplier = imgRasterSupplier;
		
		this.sauce = imgRasterSupplier.toString();
	}
	
	
	
	
	public Img(String path) {
		this.imgRasterSupplier = () -> ImgRaster.loadImageRaster(path);
		
		this.sauce = path;
	}
	
	
	
	
	public Img(Image img) {
		// TODO Image in tempImages abspeichern,
		// dann path setzten
		
		// Das Ganze macht warscheinlich auch bei this(ImgRaster) sinn
		
		// Und bei Programmende natürlich den ganzen Ordner leeren
	}
	
	
	
	
	public Img(ImgRaster imgRaster) {
		this.imgRasterSupplier = () -> imgRaster;
		
		this.sauce = imgRaster.toString();
	}
	
	
	
	
	
	
	
	public void loadImg() {
		imgRaster = imgRasterSupplier.get();
		
		
		if(imgRaster == null) {
			Debugger.addLogEntry("FEHLER - graphics.Img/initImg() - ImgRaster konnte nicht geladen werden.");
			return;
		}
		
		imgClearTimerRef = new EventTimer(Img.STANDARD_TTL, this::clear);
		
		Painter.imgClearTimer.add(imgClearTimerRef);
		
		
		ImgLoadThreadPool.getInstance().removeFromCurrentTasksList(this);
	}
	
	
	
	public void clear() {
		imgRaster = null;
		Painter.imgClearTimer.remove(imgClearTimerRef);
		imgClearTimerRef = null;
	}
	
	
	
	
	
	public void restartImgClearTimer() {
		imgClearTimerRef.startTimer();
	}
	

	
	
	public int getWidth() {
		/* OLD (BUT GOLD ?!?)
		if (!isLoaded()) {
			Img.addToQueue(this);
			return 0;
		}else {
			return imgRaster.getWidth();
		}
		*/
		
		if(!isLoaded()) {
			loadImg();
		}
		return imgRaster.getWidth();
	}
	
	
	
	
	public int getNativeWidth() {
		/* OLD (BUT GOLD ?!?)
		if (!isLoaded()) {
			Img.addToQueue(this);
			return 0;
		}else {
			return imgRaster.getNativeWidth();
		}
		*/
		
		if(!isLoaded()) {
			loadImg();
		}
		return imgRaster.getNativeWidth();
	}
	

	
	
	
	
	public int getHeight() {
		/* OLD (BUT GOLD ?!?)
		if (!isLoaded()) {
			Img.addToQueue(this);
			return 0;
		}else {
			return imgRaster.getHeight();
		}
		*/
		
		if(!isLoaded()) {
			loadImg();
		}
		return imgRaster.getHeight();
	}
	
	
	public int getNativeHeight() {
		/* OLD (BUT GOLD ?!?)
		if (!isLoaded()) {
			Img.addToQueue(this);
			return 0;
		}else {
			return imgRaster.getNativeHeight();
		}
		*/
		
		if(!isLoaded()) {
			loadImg();
		}
		return imgRaster.getNativeHeight();
	}
	
	
	
	
	
	
	
	
	public Color getColor(int x, int y) {
		if(!isLoaded()) {
			return Color.DEBUG;
		}
		
		if(x < 0 || x > getNativeWidth()-1 || y < 0 || y > getNativeHeight()-1) {
			return Color.DEBUG;
		}
		
		return imgRaster.getRaster()[x][y];
	}
	
	
	
	
	public boolean isLoaded() {
		return imgRaster != null;
	}
	
	
	
	public ImgRaster getImgRaster(){
		if(!isLoaded()) {
			loadImg();
		}
		return imgRaster;
	}
	
	
	
	
	public Image asAwtImage() {
		BufferedImage bi = new BufferedImage(getNativeWidth(), getNativeHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < getImgRaster().getRaster().length; x++) {
			for (int y = 0; y < getImgRaster().getRaster()[0].length; y++) {
				bi.setRGB(x, y, getImgRaster().getRaster()[x][y].toInt());
			}
		}
		//bi.flush();
		
		return (Image) bi;
	}
	
	
	
	
	
	
	public Img subImage(int x, int y, int w, int h) {
		if(x == 0 && y == 0 && w >= getNativeWidth() && h >= getNativeHeight()) {
			return this;
		}
		
		if(x >= getNativeWidth() || y >= getNativeHeight()) {
			return new Img(new ImgRaster(new Color[][] {{}}));
		}
		
		w = Math.min(w, getNativeWidth()-x);
		h = Math.min(h, getNativeHeight()-y);
		
		Color[][] data = new Color[w][h];
		
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				data[i][j] = getImgRaster().getRaster()[x+i][y+j];
			}
		}
		
		return new Img(new ImgRaster(data));
	}
	
	
	
	
	
	public Img asGray() {
		if (!isLoaded()) {
			loadImg();
		}
		Color[][] grayImgData = getImgRaster().getRaster();

		for (int i = 0; i < grayImgData.length; i++) {
			for (int j = 0; j < grayImgData[0].length; j++) {
				grayImgData[i][j] = grayImgData[i][j].asGray();
			}
		}

		return new Img(new ImgRaster(grayImgData));
	}
	
	
	
	
	
	
	
	public Img limitImg(int limit) {
		if (!isLoaded()) {
			loadImg();
		}
		Color[][] grayImgData = getImgRaster().getRaster();

		for (int i = 0; i < grayImgData.length; i++) {
			for (int j = 0; j < grayImgData[0].length; j++) {
				grayImgData[i][j] = grayImgData[i][j].limitColor(limit);
			}
		}

		return new Img(new ImgRaster(grayImgData));
	}
	
	
	
	
	
	
	
	
	public static Color[] getOccurringColors(Image img) {
		return getOccurringColors(new Img(img));
	}
	
	
	
	public static Color[] getOccurringColors(Img img) {
		ArrayList<Color> occurringColors = new ArrayList<Color>();

		for (int i = 0; i < img.getNativeWidth(); i++) {
			for (int j = 0; j < img.getNativeHeight(); j++) {
				boolean newColor = true;
				for (Color c : occurringColors) {
					if (img.getImgRaster().getRaster()[i][j].toInt() == c.toInt()) {
						newColor = false;
					}
				}
				if (newColor) {
					occurringColors.add(img.getImgRaster().getRaster()[i][j]);
				}
			}
		}

		return (Color[]) occurringColors.toArray();
	}
	
	
	
	
	
	
	
	
	public Img replaceColor(Color from, Color to) {
		Color[][] imgRaster = getImgRaster().getRaster();
		
		for (int i = 0; i < getNativeWidth(); i++) {
			for (int j = 0; j < getNativeHeight(); j++) {
				if(getColor(i, j).toInt() == from.toInt()) {
					imgRaster[i][j] = to;
				}else {
					imgRaster[i][j] = getColor(i, j);
				}
			}
		}
			
		
		return new Img(new ImgRaster(imgRaster));
	}
	
	
	
	
	
	
	
	// Img Scaling Stuff
	
	
	public static Img scaleImg(Img sourceImg, float scaleRatio) { // scaleRatio == 1 == noScaling  |  scaleRatio < 1 == downScaling  |  scaleRatio > 1 == upScaling  |  scaleRatio < 0 
		return sourceImg.scaleImg(scaleRatio);
	}
	
	
	
	public static Img scaleImg(Img sourceImg, int scaleWidth, int scaleHeight) {
		return sourceImg.scaleImg(scaleWidth, scaleHeight);
	}
	
	
	
	
	
	public Img scaleImg(float scaleRatio) {
		scaleRatio = Math.abs(scaleRatio);
		return scaleImg((int) (getNativeWidth()*scaleRatio), (int) (getNativeHeight()*scaleRatio));
	}
	
	
	public Img scaleImg(int scaleWidth, int scaleHeight) {
		if(scaleWidth <= 0 || scaleHeight <= 0) {
			return Img.EMPTY_IMG;
		}
		
		Color[][] temp = new Color[scaleWidth][scaleHeight];
		    
		int x_ratio = (int) ((getNativeWidth() << 16) / scaleWidth) + 1;
		int y_ratio = (int) ((getNativeHeight() << 16) / scaleHeight) + 1;
		
		int x2, y2;
		
		for (int i = 0; i < scaleHeight; i++) {
			for (int j = 0; j < scaleWidth; j++) {
				x2 = ((j*x_ratio) >> 16);
				y2 = ((i*y_ratio) >> 16);
				temp[j][i] = getImgRaster().getRaster()[x2][y2];
			}                
		}     
		    
		return new Img(new ImgRaster(temp));
	}
	
	
	
	
	
	@Override
	public String toString() {
		if(isLoaded()) {
			return "Img["+imgRaster.toString()+"]";
		}else {
			return "Img[ImgRaster=NOT_LOADED]";
		}
	}
	
	
	
	
	
	
	
	
	private interface ImgRasterSupplier extends Supplier<ImgRaster>{
		
		static final ImgRasterSupplier STANDARD = () -> new ImgRaster(new Color[][] {{}});
	}
}