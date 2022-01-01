package events;

import util.EventTimer;

import java.util.ArrayList;

public class EventTimerPool {

	
	private EventTimerPool() {};
	
	
	private static EventTimerPool instance;
	
	
	public static EventTimerPool getInstance() {
		if(instance == null) {
			instance = new EventTimerPool();
		}
		return instance;
	}
	
	/*
	 * TODO später in extra Thread machen
	 */
	
	
	
	ArrayList<EventTimer> timerList = new ArrayList<EventTimer>();
	
	
	
	
	@SuppressWarnings("unchecked")
	
	public void proceedTimers() {
	ArrayList<EventTimer> tempList = (ArrayList<EventTimer>) timerList.clone();
		for(EventTimer timer : tempList) {
			timer.proceedTimer();
			if(!timer.isRunning()) {
				timerList.remove(timer);
			}
		}
	}
	
	
	
	
	public void addTimer(EventTimer timer) {
		timer.startTimer();
		timerList.add(timer);
	}
	
	
	
	public void addTimer(int timeInMillis, Event event) {
		timerList.add(new EventTimer(timeInMillis, event));
		timerList.get(timerList.size()-1).startTimer();
	}
	
	
	public void addTimer(int timeInMillis, int type, Event event) {
		timerList.add(new EventTimer(timeInMillis, type, event));
		timerList.get(timerList.size()-1).startTimer();
	}
	
	
	
	public void addTimerIfAbsent(EventTimer timer) {
		if(!timerList.contains(timer)){
			addTimer(timer);
		}
	}
}
