package util;

public class Timer {
	
	// Constants 
	
	public static final int RUN_ONCE = 0;
	public static final int RUN_LOOP = 1;
	
	// End
	

	protected int type;

	private int startTime;
	private int currentTime;
	private int targetTime;

	protected boolean stoped;
	
	
	
	public Timer(Timer timer) {
		this.type = timer.type;
		this.targetTime = timer.targetTime;
	}
	
	

	public Timer(int timeInMilliseconds) {
		targetTime = timeInMilliseconds;
		type = RUN_ONCE;
	}
	
	public Timer(int timeInMilliseconds, int type) {
		targetTime = timeInMilliseconds;
		this.type = RUN_ONCE;
		setType(type);
	}
	
	
	
	
	
	public void setType(int type) {
		if(type == RUN_ONCE || type == RUN_LOOP) {
			this.type = type;
		}
	}
	
	
	

	
	public void startTimer() {
		startTime = (int) System.currentTimeMillis();
		currentTime = 0;
		stoped = false;
	}

	
	public void stopTimer() {
		stoped = true;
	}
	
	

	public long currentTime() {
		return currentTime;
	}
	
	

	public boolean proceedTimer() {
		if (stoped) {
			return false;
		}
		if (currentTime >= targetTime) {
			if (type == RUN_LOOP) {
				startTimer();
				return true;
			} else if (type == RUN_ONCE) {
				stopTimer();
				return true;
			}
		}

		int nowMillis = (int) System.currentTimeMillis();
		currentTime = nowMillis - startTime;
		return false;
	}
	
	
	public boolean isRunning() {
		return !stoped;
	}
	
	
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Timer) {
			return ((Timer) obj).toString().equals(this.toString());
		}
		return false;
	}
	
	
	
	@Override
	public String toString() {
		return "Timer[Type="+(type == 0 ? "RUN_ONCE" : "RUN_LOOP")+",Time="+targetTime+"]";
	}
}
