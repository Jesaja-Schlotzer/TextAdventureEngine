package data;

public class Settings {
	

	public static boolean debug;
	
	
	static {
		String[] settingData = FileHandler.loadStrings(FileHandler.PROJECT_PATH + "settings.txt");
		
	}
}
