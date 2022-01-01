package gui;

import graphics.Animation;
import graphics.colors.Palette;
import graphics.img.Img;
import textHandler.Style;
import textHandler.StyledString;


public class Switch {
	private int selected, minWidth = -1;
	private GCC[] options;
	private ToggleButton button;


	
	
	public Switch(Switch s) {
		setWidth(s.minWidth);
		this.options = GCC.toGCCArray(s.options);
		this.button = new ToggleButton(s.button);
	}
	
	
	
	public Switch(int x, int y, String... options) {
		setContent(options);
		button = new ToggleButton(x, y, options[0]);
	}
	
	
	public Switch(int x, int y, StyledString... options) {
		setContent(options);
		button = new ToggleButton(x, y, options[0]);
	}
	
	
	
	public Switch(int x, int y, String[] options, Style style) {
		setContent(options, style);
		button = new ToggleButton(x, y, new StyledString(options[0], (style == null ? new Style() : style)));
	}
	
	
	public Switch(int x, int y, String[] options, Style style, Style alternativeStyle) {
		setContent(options, style, alternativeStyle);
		button = new ToggleButton(x, y, new StyledString(options[0], (style == null ? new Style() : style), (alternativeStyle == null ? new Style() : alternativeStyle)));
	}
	
	
	
	public Switch(int x, int y, Img... options) {
		setContent(options);
		button = new ToggleButton(x, y, options[0]);
	}

	
	public Switch(int x, int y, Animation... options) {
		setContent(options);
		button = new ToggleButton(x, y, options[0]);
	}
	
	
	
	

	public Switch(int x, int y, GCC... options) {
		setContent(options);
		button = new ToggleButton(x, y, options[0]);
	}
	
	
	
	
	public Switch(int x, int y, Object... options) {
		this(x, y, GCC.cast(options));
	}
	
	
	
	
	

	public int getSelectedIndex() {
		return selected;
	}

	
	
	public GCC getSelectedObject() {
		return options[selected];
	}
	
	
	

	public void setSelectedIndex(int index) {
		if (index >= 0 && index < options.length) {
			selected = index;
			button.setContent(options[selected]);
		}
	}
	
	

	public int getIndex(GCC keyObj) {
		for (int i = 0; i < options.length; i++) {
			if (options[i].equals(keyObj)) {
				return i;
			}
		}
		return -1;
	}
	
	
	

	public GCC getOption(int index) {
		if(index >= 0 && index < options.length) {
			return options[index];
		}else {
			return GCC.NULL;
		}
	}
	
	

	
	
	
	public void setContent(String... options) {
		setContent(GCC.toGCCArray(options));
	}
	
	

	public void setContent(StyledString... options) {
		setContent(GCC.toGCCArray(options));
	}

	
	
	public void setContent(Style style, String... options) {
		if(options.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}
		
		StyledString[] newOptions = new StyledString[options.length];
		for (int i = 0; i < newOptions.length; i++) {
			newOptions[i] = new StyledString(options[i], (style == null ? new Style() : style));
		}
		setContent(GCC.toGCCArray(newOptions));
	}

	
	
	public void setContent(Style style, Style alternativeStyle, String... options) {
		if(options.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}
		
		StyledString[] newOptions = new StyledString[options.length];
		for (int i = 0; i < newOptions.length; i++) {
			newOptions[i] = new StyledString(options[i], (style == null ? new Style() : style), (alternativeStyle == null ? new Style() : alternativeStyle));
		}
		setContent(GCC.toGCCArray(newOptions));
	}
	
	

	public void setContent(Img... options) {
		setContent(GCC.toGCCArray(options));
	}
	
	

	public void setContent(Animation... options) {
		setContent(GCC.toGCCArray(options));
	}
	
	

	
	public void setContent(GCC... options) {
		if(options.length == 0) {
			this.options = new GCC[] {GCC.NULL};
		}else {
			this.options = options;
		}
	}
	
	
	
	
	public void setContent(Object... options) {
		setContent(GCC.cast(options));
	}
	
	
	


	
	
	public Switch setWindowAlign(int align) {
		this.button.setWindowAlign(align);
		
		return this;
	}
	
	
	
	
	
	public Switch lock() {
		this.button.lock();
		
		return this;
	}

	
	
	public Switch unlock() {
		this.button.unlock();
		
		return this;
	}
	
	
	
	
	public Switch setBounds(int x, int y, int w, int h) {
		this.button.setBounds(x, y, w, h);
		
		return this;
	}
	
	
	
	
	public Switch setPos(int x, int y) {
		this.button.setPos(x, y);
		
		return this;
	}
	
	
	
	
	public Switch setSize(int w, int h) {
		this.button.setSize(w, h);
		
		return this;
	}
	
	
	
	
	public Switch setWidth(int w) {
		this.button.setWidth(w);
		
		return this;
	}
	
	
	
	
	public Switch setHeight(int h) {
		this.button.setHeight(h);
		
		return this;
	}

	
	
	
	
	public Switch setPalette(Palette palette) {
		this.button.setPalette(palette);
		
		return this;
	}
	
	
	
	
	
	public Switch setLayout(Layout<ToggleButton> layout) {
		this.button.setLayout(layout);
		
		return this;
	}
	
	
	
	
	
		
		
	
	public ToggleButton getButton() {
		return button;
	}
	
	
	
	
	
	
	public void draw() {
		button.draw();
	}
	
	

	public void handleMouseClick() {
		if (button.handleMouseClick()) {
			selected++;
			if (selected >= options.length) {
				selected = 0;
			}
			
			button.setContent(options[selected]);
			
			if (minWidth != -1) {
				button.setWidth(minWidth);
			}
		}
	}
}
