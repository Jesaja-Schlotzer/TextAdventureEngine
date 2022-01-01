package taEngine;

import graphics.colors.Color;
import graphics.colors.Palette;

public class TAColors {
	
	public static final Color WHITE = new Color("#FFFFDE");
	public static final Color LIGHT_GRAY = new Color("#989882");
	public static final Color MIDDLE_GRAY = new Color("#666660");
	public static final Color DARK_GRAY = new Color("#464646");
	
	public static final Color RED = new Color("#CD3939");
	public static final Color RED_DARK_GRAY_TRANSITION = new Color("#894C4C");
	
	
	public static final Color EDITOR_BACKGROUND = new Color("#AFAFA6");
	
	
	
	
	public static final Palette STANDARD_TA_PALETTE = new Palette(false,
			Color.TRANSPARENT,
			WHITE,
			LIGHT_GRAY,
			DARK_GRAY,
			RED,
			EDITOR_BACKGROUND);
}
