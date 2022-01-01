package graphics.img;

import data.FileHandler;
import debugging.Debugger;
import graphics.GraphicsConstants;
import graphics.colors.Color;
import graphics.colors.ColorUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;


public class ImgRaster {
	private Color[][] rasterData;
	
	
	public ImgRaster(ImgRaster ir) {
		this.rasterData = ir.rasterData;
	}
	
	public ImgRaster(Color[][] rasterData) {
		/* TODO noch nicht so nice
		System.out.println("\n\n\n\n\nVorher:");
		Stream.of(rasterData).parallel().forEach(arr -> Stream.of(arr).parallel().forEach(System.out::println));
		
		Stream.of(rasterData).parallel().forEach(arr -> Stream.of(arr).parallel().forEach(c -> c = (c != null ? c : Color.NO_COLOR)));
		System.out.println("\n\nNachher");
		Stream.of(rasterData).parallel().forEach(arr -> Stream.of(arr).parallel().forEach(System.out::println));

		System.out.println("\n\n\n\n\n");
		*/
		
		for(int i = 0; i < rasterData.length; i++) {
			for(int j = 0; j < rasterData[0].length; j++) {
				rasterData[i][j] = (rasterData[i][j] != null ? rasterData[i][j] : Color.NO_COLOR);
			}
		}
		
		
		this.rasterData = rasterData;
	}
	
	
	
	
	public int getWidth() {
		return rasterData.length * GraphicsConstants.PIXEL_SIZE_X;
	}
	
	
	public int getNativeWidth() {
		return rasterData.length;
	}
	
	
	
	public int getHeight() {
		return rasterData[0].length * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	public int getNativeHeight() {
		return rasterData[0].length;
	}
	
	
	
	public Color[][] getRaster(){
		return rasterData;
	}
	
	
	
	
	public static ImgRaster loadImageRaster(String path) {
		Color[][] loadedRasterData;
		
		File tempImg = new File(path);
		
		if (!tempImg.exists()) {
			Debugger.addLogEntry("FEHLER - Maler/Bild/initImg() - Datei existiert nicht. Pfad: " + path);
			return null;
		}

		BufferedImage img = (BufferedImage) FileHandler.loadImage(path);
		
		loadedRasterData = new Color[img.getWidth()][img.getHeight()];
		for (int i = 0; i < loadedRasterData.length; i++) {
			for (int j = 0; j < loadedRasterData[0].length; j++) {
				loadedRasterData[i][j] = ColorUtils.intToColor(img.getRGB(i, j));
			}
		}
		
		return new ImgRaster(loadedRasterData);
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "ImgRaster[Width="+getNativeWidth()+",Height="+getNativeHeight()+"]";
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ImgRaster){
			if(((ImgRaster) obj).getNativeWidth() == this.getNativeWidth() && ((ImgRaster) obj).getNativeHeight() == this.getNativeHeight()) {
				if(Arrays.deepEquals(rasterData, ((ImgRaster) obj).rasterData)) {
					return true;
				}
			}
		}
		return false;
	}
}
