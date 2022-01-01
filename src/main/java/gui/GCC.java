package gui;

import debugging.Debugger;
import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import graphics.img.ImgOperator;
import textHandler.StyledString;
import textHandler.TextHandler;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;


public class GCC{ // GuiContentContainer
	
	// Constants
	
	public static GCC NULL = new GCC();
	
	// End
	
	
	
	public int type;
	private Object content;
	// Idee für die Zukunft: alternative content, dieser muss warscheinlich den gleichen typ wie content haben
	
	
	
	
	// Für NULL-GCC-Object
	private GCC() {
		content = null;
		type = Gui.NULL;
	}
	
	
	
	public GCC(GCC gcc) {
		if(gcc == null) {
			this.content = null;
			this.type = Gui.NULL;
		}else {
			this.content = gcc.content;
			this.type = gcc.type;
		}
	}
	
	
	
	
	public GCC(String text) {
		if(text == null) {
			this.content = null;
			this.type = Gui.NULL;
		}else {
			content = text;
			type = Gui.STRING;
		}
	}
	
	

	public GCC(StyledString styledString) {
		if(styledString == null) {
			this.content = null;
			this.type = Gui.NULL;
		}else {
			content = styledString;
			type = Gui.STYLED_STRING;
		}
	}
	
	

	public GCC(Img img) {
		if(img == null) {
			this.content = null;
			this.type = Gui.NULL;
		}else {
			content = img;
			type = Gui.IMG;
		}
	}
	

	
	public GCC(Animation animation) {
		if(animation == null) {
			this.content = null;
			this.type = Gui.NULL;
		}else {
			content = animation;
			type = Gui.ANIMATION;
		}
	}
	
	
	
	public GCC(Drawable drawable) {
		if(drawable == null) {
			this.content = null;
			this.type = Gui.NULL;
		}else {
			content = drawable;
			type = Gui.DRAWABLE;
		}
	}
	
	

	
	
	
	public GCC(Object obj) {
		if(obj == null) {
			this.content = null;
			this.type = Gui.NULL;
		}else {
			if(obj instanceof String) {
				content = (String) obj;
				type = Gui.STRING;
			}else if(obj instanceof StyledString) {
				content = (StyledString) obj;
				type = Gui.STYLED_STRING;
			}else if(obj instanceof Img) {
				content = (Img) obj;
				type = Gui.IMG;
			}else if(obj instanceof Animation) {
				content = (Animation) obj;
				type = Gui.ANIMATION;
			}else if(obj instanceof Drawable){
				content = (Drawable) obj;
				type = Gui.DRAWABLE;
			}else {
				content = null;
				type = Gui.NULL;
				Debugger.addLogEntry("FEHLER - gui.GCC/GCC(Object) - Der Typ des Objects + " + obj + " ist nicht valide");
			}
		}
	}
	
	
	
	
	
	
	public void setContent(String text) {
		if(text == null) return;
		content = text;
		type = Gui.STRING;
	}

	
	
	public void setContent(StyledString styledString) {
		if(styledString == null) return;
		content = styledString;
		type = Gui.STYLED_STRING;
	}
	

	
	public void setContent(Img img) {
		if(img == null) return;
		content = img;
		type = Gui.IMG;
	}
	
	

	public void setContent(Animation animation) {
		if(animation == null) return;
		content = animation;
		type = Gui.ANIMATION;
	}
	
	
	
	public void setContent(Drawable drawable) {
		if(drawable == null) return;
		content = drawable;
		type = Gui.DRAWABLE;
	}
	
	
	
	
	
	public void setContent(GCC gcc) {
		if(gcc == null) return;
		this.content = gcc.content;
		this.type = gcc.type;
	}
	
	
	
	
	
	public void setContent(Object obj) {
		if(obj instanceof String) {
			setContent((String) obj);
		}else if(obj instanceof StyledString) {
			setContent((StyledString) obj);
		}else if(obj instanceof Img) {
			setContent((Img) obj);
		}else if(obj instanceof Animation) {
			setContent((Animation) obj);
		}else if(obj instanceof Drawable) {
			setContent((Drawable) obj);
		}else {
			content = NULL;
			type = Gui.NULL;
			Debugger.addLogEntry("FEHLER - gui.GCC/GCC(Object) - Der Typ des Objects + " + obj + " ist nicht valide");
		}
	}
	
	
	
	
	public int getWidth() {
		return getNativeWidth() * GraphicsConstants.PIXEL_SIZE_X;
	}

	
	
	
	public int getNativeWidth() {
		switch (type) {
		case Gui.STRING:
			return TextHandler.font().getTextWidth((String) content);
		case Gui.STYLED_STRING:
			return ((StyledString) content).getWidth();
		case Gui.IMG:
			return ((Img) content).getWidth() / GraphicsConstants.PIXEL_SIZE_X;
		case Gui.ANIMATION:
			return ((Animation) content).getWidth() / GraphicsConstants.PIXEL_SIZE_X;
		case Gui.DRAWABLE:
			return ((Drawable) content).getNativeWidth();
		}

		return 0;
	}

	
	
	
	public int getWidth(int w) {
		return getNativeWidth(w) * GraphicsConstants.PIXEL_SIZE_X;
	}

	
	
	
	
	public int getNativeWidth(int w) {
		switch (type) {
		case Gui.STRING:
			return TextHandler.font().getTextWidth((String) content, w, true);
		case Gui.STYLED_STRING:
			return ((StyledString) content).getWidth(w);
		case Gui.IMG:
			return ((Img) content).getWidth() / GraphicsConstants.PIXEL_SIZE_X;
		case Gui.ANIMATION:
			return ((Animation) content).getWidth() / GraphicsConstants.PIXEL_SIZE_X;
		case Gui.DRAWABLE:
			return ((Drawable) content).getNativeWidth();
		}

		return 0;
	}

	
	


	
	public int getHeight() {
		return getNativeHeight() * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	

	public int getNativeHeight() {
		switch (type) {
		case Gui.STRING:
			return TextHandler.font().getTextHeight((String) content);
		case Gui.STYLED_STRING:
			return ((StyledString) content).getHeight();
		case Gui.IMG:
			return ((Img) content).getHeight() / GraphicsConstants.PIXEL_SIZE_Y;
		case Gui.ANIMATION:
			return ((Animation) content).getHeight() / GraphicsConstants.PIXEL_SIZE_Y;
		case Gui.DRAWABLE:
			return ((Drawable) content).getNativeHeight();
		}

		return 0;
	}

	
	
	
	public int getHeight(int h) {
		return getNativeHeight(h) * GraphicsConstants.PIXEL_SIZE_Y;
	}

	
	
	
	
	public int getNativeHeight(int w) {
		switch (type) {
		case Gui.STRING:
			return TextHandler.font().getTextHeight((String) content, w, true);
		case Gui.STYLED_STRING:
			return ((StyledString) content).getHeight(w);
		case Gui.IMG:
			return ((Img) content).getHeight() / GraphicsConstants.PIXEL_SIZE_Y;
		case Gui.ANIMATION:
			return ((Animation) content).getHeight() / GraphicsConstants.PIXEL_SIZE_Y;
		case Gui.DRAWABLE:
			return ((Drawable) content).getNativeHeight();
		}

		return 0;
	}

	
	
	
	public void draw(int x, int y, int w, int h, Color c) {
		x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		w = w / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		h = h / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		
		switch (type) {
		case Gui.STRING:
			TextHandler.drawString((String) content, x, y, w, h, c);
			break;
		case Gui.STYLED_STRING:
			TextHandler.drawStyledString((StyledString) content, x, y, w, h);
			break;
		case Gui.IMG:
			Painter.drawImage((Img) content, x, y, w, h);
			break;
		case Gui.ANIMATION:
			Painter.drawLoopedAnimation((Animation) content, x, y, w, h);
			break;
		case Gui.DRAWABLE:
			((Drawable) content).draw(x, y); // TODO subDrawable(x, y, w, h)
			break;
		}
	}

	
	
	
	
	public void draw(int x, int y, int w, Color c) {
		draw(x, y, w, Integer.MAX_VALUE, c);
	}

	
	
	
	
	public void draw(int x, int y, Color c) {
		x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
		
		
		switch (type) {
		case Gui.STRING:
			TextHandler.drawString((String) content, x, y, c);
			break;
		case Gui.STYLED_STRING:
			TextHandler.drawStyledString((StyledString) content, x, y);
			break;
		case Gui.IMG:
			Painter.drawImage((Img) content, x, y);
			break;
		case Gui.ANIMATION:
			Painter.drawLoopedAnimation((Animation) content, x, y);
			break;
		case Gui.DRAWABLE:
			((Drawable) content).draw(x, y);
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	public Object getContent() {
		switch (type) {
		case Gui.STRING:
			return (String) content;
		case Gui.STYLED_STRING:
			return (StyledString) content;
		case Gui.IMG:
			return (Img) content;
		case Gui.ANIMATION:
			return (Animation) content;
		case Gui.DRAWABLE:
			return ((Drawable) content);
		}

		return NULL;
	}

	
	
	
	
	
	public String asString() {
		return (type == Gui.STRING ? (String) content : "");
	}

	
	
	public StyledString asStyledString() {
		return (type == Gui.STYLED_STRING ? (StyledString) content : null);
	}

	
	
	public Img asImg() {
		return (type == Gui.IMG ? (Img) content : null);
	}

	
	
	public Animation asAnimation(){
		return (type == Gui.ANIMATION ? (Animation) content : null);
	}
	
	
	
	public Drawable asDrawable() {
		return (type == Gui.DRAWABLE ? (Drawable) content : null);
	}
	
	
	
	
	
	
	public GCC asGray() { // TODO evtl. was für Drawable machen
		switch (type) {
		case Gui.STRING:
			return new GCC(this);
		case Gui.STYLED_STRING:
			return new GCC(asStyledString().getGrayStyledString());
		case Gui.IMG:
			return new GCC(asImg().asGray());
		case Gui.ANIMATION:
			return new GCC(asAnimation().asGray());
		case Gui.DRAWABLE:
			return new GCC(((Drawable) content).operate(ImgOperator.AS_GRAY));
		}

		return new GCC(this);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString(){
		return "GCC[type="+type+",content="+content.toString()+"]";
	}
	
	
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(!(obj instanceof GCC)) {
			return false;
		}else {
			if(((GCC) obj).type == this.type && ((GCC) obj).content.equals(content)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	// Statisches
	
	

	public static GCC[] toGCCArray(String... strings) {
		GCC[] contents = new GCC[strings.length];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = new GCC(strings[i]);
		}
		return contents;
	}
	

	
	public static GCC[] toGCCArray(StyledString... styledStrings) {
		GCC[] contents = new GCC[styledStrings.length];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = new GCC(styledStrings[i]);
		}
		return contents;
	}
	
	

	public static GCC[] toGCCArray(Img... imgs) {
		GCC[] contents = new GCC[imgs.length];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = new GCC(imgs[i]);
		}
		return contents;
	}

	
	
	public static GCC[] toGCCArray(Animation... animations) {
		GCC[] contents = new GCC[animations.length];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = new GCC(animations[i]);
		}
		return contents;
	}
	
	
	
	public static GCC[] toGCCArray(Drawable... drawables) {
		GCC[] contents = new GCC[drawables.length];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = new GCC(drawables[i]);
		}
		return contents;
	}
	
	
	
	
	
	public static GCC[] toGCCArray(GCC... gccs) {
		GCC[] contents = new GCC[gccs.length];
		for (int i = 0; i < contents.length; i++) {
			contents[i] = new GCC(gccs[i]);
		}
		return contents;
	}

	
	
	
	
	
	public static boolean containsString(GCC... gccs) {
		for (GCC gcc : gccs) {
			if (gcc.type == Gui.STRING) {
				return true;
			}
		}
		return false;
	}

	
	
	public static boolean containsStyledString(GCC... gccs) {
		for (GCC gcc : gccs) {
			if (gcc.type == Gui.STYLED_STRING) {
				return true;
			}
		}
		return false;
	}

	
	
	public static boolean containsImg(GCC... gccs) {
		for (GCC gcc : gccs) {
			if (gcc.type == Gui.IMG) {
				return true;
			}
		}
		return false;
	}

	
	
	public static boolean containsAnimation(GCC... gccs) {
		for (GCC gcc : gccs) {
			if (gcc.type == Gui.ANIMATION) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public static boolean containsDrawable(GCC... gccs) {
		for (GCC gcc : gccs) {
			if (gcc.type == Gui.DRAWABLE) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	public static int getWidest(GCC... gccs) {
		int max = 0;
		for(GCC gcc : gccs) {
			if(gcc.getWidth() > max) {
				max = gcc.getWidth();
			}
		}
		return max;
	}
	
	
	
	public static int getHighest(GCC... gccs) {
		int max = 0;
		for(GCC gcc : gccs) {
			if(gcc.getHeight() > max) {
				max = gcc.getHeight();
			}
		}
		return max;
	}
	
	
	
	
	
	
	
	
	// Filter
	
	// Object[] to GCC[]  +  validation & casting
	
	public static GCC[] cast(Object... objectArray) {
		return Arrays.stream(objectArray).filter(GCC.VALIDATION_FILTER).map(GCC.RETURN_VALID_OBJECT).toArray(GCC[]::new);
	}
	
	
	
	
	public static Predicate<Object> VALIDATION_FILTER =  (Object obj) -> 
		{
			return (obj instanceof GCC || obj instanceof String || obj instanceof StyledString || obj instanceof Img || obj instanceof Animation || obj instanceof Drawable);
		};
	
		
	public static Function<Object, GCC> RETURN_VALID_OBJECT = (Object obj) -> 
		{
			return new GCC(obj);
		};
	
	
}