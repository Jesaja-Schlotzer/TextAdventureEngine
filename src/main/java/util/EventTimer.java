package util;

import events.Event;

public class EventTimer extends Timer {

	private Event event;
	
	
	
	public EventTimer(EventTimer timer) {
		super(timer);
		setEvent(timer.event);
	}
	
	
	
	public EventTimer(int timeInMilliseconds, Event event) {
		super(timeInMilliseconds);
		setEvent(event);
	}
	
	
	public EventTimer(int timeInMilliseconds, int type, Event event) {
		super(timeInMilliseconds, type);
		setEvent(event);
	}

	
	
	public void setEvent(Event event) {
		this.event = (event == null ? Event.NULL : event);
	}
	
	public Event event() {
		return event;
	}

	
	
	public boolean proceedTimer() {
		if (super.stoped) {
			if (super.type == RUN_ONCE) {
				return true;
			}
			return false;
		}

		if (super.proceedTimer()) {
			stopTimer();
			event.start();
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EventTimer) {
			return ((EventTimer) obj).toString().equals(this.toString());
		}
		return false;
	}
	
	
	
	@Override
	public String toString() {
		return "EventTimer["+super.toString()+","+event.toString()+"]";
	}
}
