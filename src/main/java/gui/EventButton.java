package gui;

import events.Event;
import graphics.Animation;
import graphics.colors.Palette;
import graphics.img.Img;
import graphics.img.ImgOperator;
import textHandler.StyledString;


public class EventButton extends ToggleButton {
	private Event buttonEvent = Event.NULL;
	
	
	
	
	public EventButton(EventButton eb) {
		super(eb);
		this.buttonEvent = eb.buttonEvent;
	}
	
	
	
	
	
	public EventButton(ToggleButton toggleButton, Event event) {
		super(toggleButton);
		setEvent(event);
	}
	
	
	public EventButton(Event event, ToggleButton toggleButton) {
		super(toggleButton);
		setEvent(event);
	}
	
	
	
	
	public EventButton(int x, int y, String text, Event event) {
		super(x, y, text);
		setEvent(event);
	}
	
	

	public EventButton(int x, int y, StyledString styledString, Event event) {
		super(x, y, styledString);
		setEvent(event);
	}

	
	
	public EventButton(int x, int y, Img img, Event event) {
		super(x, y, img);
		setEvent(event);
	}
	
	

	public EventButton(int x, int y, Animation animation, Event event) {
		super(x, y, animation);
		setEvent(event);
	}
	
	
	
	public EventButton(int x, int y, Drawable drawable, Event event) {
		super(x, y, drawable);
		setEvent(event);
	}
	

	
	
	public EventButton(int x, int y, GCC gcc, Event event) {
		super(x, y, gcc);
		setEvent(event);
	}
	
	
	
	
	
	public void setEvent(Event event) {
		buttonEvent = (event == null ? Event.NULL : event);
	}
	
	
	
	
	
	@Override
	public EventButton setWindowAlign(int align) {
		super.setWindowAlign(align);
		
		return this;
	}
	
	
	
	@Override
	public EventButton lock() {
		super.lock();
		
		return this;
	}
	
	
	@Override
	public EventButton unlock() {
		super.unlock();
		
		return this;
	}
	
	
	
	
	@Override
	public EventButton setBounds(int x, int y, int w, int h) {
		super.setBounds(x, y, w, h);
		
		return this;
	}
	
	
	
	@Override
	public EventButton setPos(int x, int y) {
		super.setPos(x, y);
		
		return this;
	}
	
	
	
	@Override
	public EventButton setSize(int w, int h) {
		super.setSize(w, h);
		
		return this;
	}
	
	
	@Override
	public EventButton setWidth(int w) {
		super.setWidth(w);
		
		return this;
	}
	
	
	@Override
	public EventButton setHeight(int h) {
		super.setHeight(h);
		
		return this;
	}
	
	
	
	
	@Override
	public EventButton setPalette(Palette palette) {
		super.setPalette(palette);
		
		return this;
	}
	
	
	
	@Override
	public EventButton setLayout(Layout<ToggleButton> layout) {
		super.setLayout(layout);
		
		return this;
	}
	
	
	
	@Override
	public EventButton applyOperator(ImgOperator imgOperator) {
		super.applyOperator(imgOperator);
		
		return this;
	}
	
	
	
	
	public boolean handleMouseClick() {
		if (super.handleMouseClick()) {
			buttonEvent.start();
			return true;
		}else {
			return false;
		}
	}

}
