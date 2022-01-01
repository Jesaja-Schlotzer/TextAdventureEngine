package graphics.img;

import graphics.Animation;

public class Imgs {
	
	public static Img title = new Img(Img.standardPath.apply("title.png"));
	
	public static Img fruehling = new Img(Img.standardPath.apply("kalender/fruehling.png"));
	public static Img sommer = new Img(Img.standardPath.apply("kalender/sommer.png"));
	public static Img herbst = new Img(Img.standardPath.apply("kalender/herbst.png"));
	public static Img winter = new Img(Img.standardPath.apply("kalender/winter.png"));
	
	
	public static Animation gameIntro = new Animation(Animation.standardPath.apply("intro/gameIntro/"));
	public static Animation loading = new Animation(Animation.standardPath.apply("loadingAnimation/"));
			
}
