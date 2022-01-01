package util;

public class Counter {
	
	// Constants 
	
	public static final int RUN_ONCE = 0;
	public static final int RUN_LOOP = 1;
	
	// End
	
	
	private int type;

	private float startValue;
	private float stepValue;
	private float targetValue;

	private float currentValue;

	private boolean stoped;

	
	
	public Counter(float targetValue) {
		this(0, 1, targetValue);
		this.type = RUN_ONCE;
	}

	
	public Counter(float stepValue, float targetValue) {
		this(0, stepValue, targetValue);
		this.type = RUN_ONCE;
	}
	

	public Counter(float startValue, float stepValue, float targetValue) {
		this.startValue = startValue;
		this.stepValue = stepValue;
		this.targetValue = targetValue;
		this.type = RUN_ONCE;
	}
	

	public Counter(float targetValue, int type) {
		this(0, 1, targetValue);
		this.type = type;
	}
	

	public Counter(float stepValue, float targetValue, int type) {
		this(0, stepValue, targetValue);
		this.type = type;
	}
	

	public Counter(float startValue, float stepValue, float targetValue, int type) {
		this.startValue = startValue;
		this.stepValue = stepValue;
		this.targetValue = targetValue;
		this.type = type;
	}
	
	

	public void startCounter() {
		currentValue = startValue;
		stoped = false;
	}

	
	public void stopCounter() {
		stoped = true;
	}

	
	
	public float currentValue() {
		return currentValue;
	}

	
	public boolean proceedCounter() {
		if (stoped) {
			return false;
		}
		if (startValue >= targetValue) {
			if (type == RUN_LOOP) {
				startCounter();
				return true;
			} else if (type == RUN_ONCE) {
				stopCounter();
				return true;
			}
		}

		currentValue += stepValue;
		return false;
	}
}
