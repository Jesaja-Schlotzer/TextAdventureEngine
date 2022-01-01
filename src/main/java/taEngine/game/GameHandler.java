package taEngine.game;

import data.FileHandler;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public final class GameHandler {

	
	// Allgemeines
	
	
	
	
	
	public static String loadedSavePath;
	public static String[] loadedGame = {"null"}, loadedSave;
	
	public static int selectedSave;
	
	
	
	
	
	
	
	
	// Wenn der "loadGame-" Screen geöffnet wird
	
	public static String[] getGames(){
		String path = FileHandler.PROJECT_PATH+"Dateien/Spiele";
		File file = new File(path);
		
		String[] files = file.list();
		ArrayList<String> games = new ArrayList<String>();
		  
		for(int i = 0; i < files.length; i++){
			if(files[i].split("[.]")[1].equals("game")){
				games.add(files[i]);
			}
		}
		  
		String[] gamesInArray = new String[games.size()];
		for(int i = 0; i < games.size(); i++){
			gamesInArray[i] = games.get(i);
		}
		return gamesInArray;
	}
	
	
	
	
	
	// Wenn "spiel importieren" gedrückt wird
	
	public static void importGame() {
		File srcFile = FileHandler.chooseFile("Spiel importieren", null, new FileNameExtensionFilter("Game-Datei importieren", "game"));
		try {
			Files.copy(srcFile.toPath(), new File(FileHandler.PROJECT_PATH+"Dateien/Spiele").toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public static String[] getSave(){
		String[] save = new String[5];
		
		for(int i = 0; i < 5; i++){
			save[i] = loadedSave[(selectedSave*5)+i];
		}
		return save;
	}
	
	
	
	
	
	
	public static void saveTheGame(){
		loadedSave[selectedSave*5+1] = LocalDate.now().getDayOfMonth() + "." + LocalDate.now().getMonthValue() + "." +  LocalDate.now().getYear();
		loadedSave[selectedSave*5+2] = LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
		loadedSave[selectedSave*5+3] = calcPlayTime(loadedSave[selectedSave*5+3], (((int) System.currentTimeMillis()/1000) - Game.sessionStartTime));
		loadedSave[selectedSave*5+4] = Game.currentGroup+"";
		
		Game.sessionStartTime = (int) System.currentTimeMillis()/1000;
		
		FileHandler.saveStrings(loadedSavePath, loadedSave);
		loadedSave = FileHandler.loadStrings(loadedSavePath);
	}
	
	
	
	
	private static String calcPlayTime(String currentPlayTime, int sessionPlayTimeInSeconds) {
		int secs = Integer.parseInt(currentPlayTime.split("[:]")[2]);
		int mins = Integer.parseInt(currentPlayTime.split("[:]")[1]);
		int hours = Integer.parseInt(currentPlayTime.split("[:]")[0]);
		
		/*
		secs += sessionPlayTimeInSeconds;
		secs = secs % 60;
		
		mins += (sessionPlayTimeInSeconds - (secs % 60)) / 60;
		mins = mins % 60;
		
		hours += (sessionPlayTimeInSeconds - (secs % 60) - (sessionPlayTimeInSeconds - (mins % 60) * 60)) / 60;
		*/
		
		hours += sessionPlayTimeInSeconds / 60 / 60;
		mins += (sessionPlayTimeInSeconds - (sessionPlayTimeInSeconds / 60 / 60 * 60 * 60)) / 60;
		secs += sessionPlayTimeInSeconds - ((sessionPlayTimeInSeconds - (sessionPlayTimeInSeconds / 60 / 60 * 60 * 60)) / 60 + (sessionPlayTimeInSeconds / 60 / 60 * 60 * 60));
		
		String s = (secs > 9 ? secs+"" : "0"+secs);
		String m = (mins > 9 ? mins+"" : "0"+mins);
		String h = hours+"";
		
		System.out.println(sessionPlayTimeInSeconds);
		System.out.println(h+":"+m+":"+s);
		
		return h+":"+m+":"+s;
	}
	
	
	
	
	
	
	
	
	public static void loadGameData(String gamePath) {
		
		// .game Datei wird geladen
		GameHandler.loadedGame = FileHandler.loadStrings("assets/Dateien/Spiele/"+gamePath);
		
		if(GameHandler.loadedGame[0].equals("null")) {
			return;
		}
		
		
		Game.initNewGame(Integer.parseInt(GameHandler.loadedGame[3]));
		
        
		GameHandler.loadedSavePath = "assets/Dateien/Spiele/"+gamePath.substring(0, gamePath.length()-5)+".save";
        
        if(FileHandler.loadStrings(GameHandler.loadedSavePath)[0].equals("null")){
        	File f = new File(GameHandler.loadedSavePath);
        	f.getParentFile().mkdirs();
        	try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        GameHandler.loadedSave = FileHandler.loadStrings(GameHandler.loadedSavePath);
	}
	
	
	
	
	
	public static void loadGame(String[] fullData) {
		if(fullData != null) {
			
			char splitChar = ';';
			
			int line = 4;
			
			
			for(int i = 0; i < Integer.parseInt(fullData[3]); i++){
				String[] groupLines = new String[1+Integer.parseInt(loadedGame[line].split("["+splitChar+"]")[1])];
				
				groupLines[0] = fullData[line];
				
				for(int j = 1; j <= Integer.parseInt(loadedGame[line].split("["+splitChar+"]")[1]); j++){
					groupLines[j] = fullData[line+j];
				}
				
				Game.groupList[i] = new Group(groupLines, i);
				
				line += Integer.parseInt(loadedGame[line].split("["+splitChar+"]")[1]) + 1;
				
				System.out.println(Game.groupList[i].toString());
			}
		}
	}
}
