package events;

import screen.Screen;
import screen.ScreenHandler;

@FunctionalInterface
public interface Event{

	// Constants
	
	public static final Event NULL = () -> {};
	
	public static Event generateScreenRequestEvent(Screen screen) {
		return () -> ScreenHandler.getInstance().requestNextScreen(screen);
	}
	
	
	
	// End
	
	
	
	public void start();
}
