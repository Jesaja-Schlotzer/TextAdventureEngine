package graphics;

import data.FileHandler;
import graphics.img.Img;

import java.io.File;
import java.util.function.UnaryOperator;

public class Animation {
	
	// Constants
	
	public static final UnaryOperator<String> standardPath = (String path) -> 
		{
			return FileHandler.PROJECT_PATH + "images\\staticImages\\" + path;
		};
	
	
	public static final int PLAY_ONCE = 0;
	public static final int PLAY_LOOP = 1;
	
	public static final float STANDARD_ANIMATION_SPEED = 0.025f;
	
	// End
	
	
	private String path;
	private Img[] imgs;
	public float animationProgress, animationSpeed;

	
	private int type, maxWidth, maxHeight;
	private boolean isFinished;

	
	public Animation(String path, float animationSpeed) {
		this(path);
		this.animationSpeed = animationSpeed;
	}
	
	

	public Animation(String path) {
		this.path = path;
		
		imgs = new Img[FileHandler.countFilesInDirectory(path, (File file) -> {return (file.isFile() && file.getAbsolutePath().split("[.]")[file.getAbsolutePath().split("[.]").length-1].equals("png"));})];
		
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = new Img(this.path + FileHandler.getFilepathsInDirectory(path, "png")[i]);
		}

		this.type = PLAY_ONCE;

		calcSize();
		
		
		this.animationSpeed = STANDARD_ANIMATION_SPEED;
	}

	
	
	public Animation(String path, int type, float animationSpeed) {
		this(path, type);
		this.animationSpeed = animationSpeed;
	}

	
	
	public Animation(String path, int type) {
		this(path);
		this.type = type;
	}

	
	
	public Animation(Img[] imgs, float animationSpeed) {
		this(imgs);
		this.animationSpeed = animationSpeed;
	}

	
	
	public Animation(Img[] imgs) {
		this.imgs = imgs;
		this.type = PLAY_ONCE;

		calcSize();
		
		
		this.animationSpeed = STANDARD_ANIMATION_SPEED;
	}
	
	

	public Animation(Img[] imgs, int type, float animationSpeed) {
		this(imgs, type);
		this.animationSpeed = animationSpeed;
	}

	
	
	public Animation(Img[] imgs, int type) {
		this.imgs = imgs;
		this.type = type;

		calcSize();
		
		
		this.animationSpeed = STANDARD_ANIMATION_SPEED;
	}
	
	
	
	

	public Animation setAnimationSpeed(float animationSpeed) {
		this.animationSpeed = animationSpeed;
		
		return this;
	}
	
	
	
	public Animation setAnimationProgress(float animationProgress) {
		this.animationProgress = animationProgress;
		
		return this;
	}
	
	
	

	private void calcSize() {
		for (int i = 0; i < imgs.length; i++) {
			if (imgs[i].getWidth() > maxWidth) {
				maxWidth = imgs[i].getWidth();
			}
			if (imgs[i].getHeight() > maxHeight) {
				maxHeight = imgs[i].getHeight();
			}
		}
	}

	
	
	public int getWidth() {
		return maxWidth;
	}
	

	public int getHeight() {
		return maxHeight;
	}
	
	

	public void reset() {
		animationProgress = 0;
		setFinished(false);
	}
	
	
	
	
	public Img[] getImgs() {
		return imgs;
	}



	public boolean isFinished() {
		return isFinished;
	}



	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	
	
	
	
	

	
	public Animation subAnimation(int x, int y, int w, int h) {
		Img[] imgs = getImgs();
		
		for(Img img : imgs) {
			img = img.subImage(x, y, w, h);
		}
		
		return new Animation(imgs).setAnimationSpeed(animationSpeed).setAnimationProgress(animationProgress);
	}
	
	
	
	
	
	public Animation asGray() {
		Img[] imgs = getImgs();

		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = imgs[i].asGray();
		}

		return new Animation(imgs);
	}
	
	
	
	public Animation limitAnimation(int limit) {
		Img[] imgs = getImgs();

		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = imgs[i].limitImg(limit);
		}

		return new Animation(imgs);
	}
	
}
