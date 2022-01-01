package data;

import debugging.Debugger;
import graphics.img.Img;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;


public class FileHandler {

	// Constants

	public static final String PROJECT_PATH = System.getProperty("user.dir") + "\\assets\\";
	//public static final String PROJECT_PATH = "D:\\Programme\\Eclipse\\Workspace\\TextAdvEngine\\res\\";
	//public static final String PROJECT_PATH = "C:\\Programme\\Eclipse Workspace\\TextAdvEngine\\res\\";
	
	// End	
	
	
	
	// Standard Dateiendungen

	public static final String TXT = ".txt";
	public static final String PNG = ".png";

	// Imbiss Dateiendungen
	public static final String FONT = ".font";
	public static final String UNDERLINE = ".underline";
	public static final String HELPSITE = ".helpsite";
	public static final String EVENT = ".event";
	public static final String LANG = ".lang";
	public static final String SAVE = ".save";
	public static final String CURSOR = ".cursor";

	public static final String MASCHINE = ".maschine";
	public static final String PRODUCT = ".prod";

	// End

	
	
	
	// loadStrings

	public static String[] loadStrings(File file) {
		if (!file.exists()) {
			Debugger.addLogEntry("FEHLER - FileHandler/loadStrings(File) - Datei " + file.getPath() + " existiert nicht.");
			return new String[] {"null"};
		}
		
		ArrayList<String> lines = new ArrayList<String>();

		try {
			Scanner sc = new Scanner(file, StandardCharsets.UTF_8);

			while (sc.hasNext()) {
				lines.add(sc.nextLine());
			}

			String[] linesAsArray = new String[lines.size()];

			for (int i = 0; i < lines.size(); i++) {
				linesAsArray[i] = lines.get(i);
			}
			
			sc.close();
			
			return linesAsArray;

		} catch (IOException e) {
			e.printStackTrace();
		}

		Debugger.addLogEntry("FEHLER - data.FileHandler/loadStrings(File) - Datei konnte nicht geladen werden");
		return new String[] {"null"};
	}
	
	
	
	public static String[] loadStrings(String path) {
		File file = new File(path);
		return loadStrings(file);
	}

	
	
	
	
	// saveStrings
	
	public static void saveStrings(String path, String[] lines) {
		saveStrings(new File(path), lines);
	}
	
	
	public static void saveStrings(File file, String[] lines) {
		if(lines.length == 0) {
			Debugger.addLogEntry("FEHLER - data.FileHandler/saveStrings(File, String[]) - \"lines\" ist leer");
			lines = new String[] {""};
		}
		
		
		try {
			FileWriter writer = new FileWriter(file, false);
			for(int i = 0; i < lines.length-1 ; i++) {
				writer.write(lines[i] + System.getProperty("line.separator"));
			}
			writer.write(lines[lines.length-1]);
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	

	public static void saveStrings(String path, ArrayList<String> lines) {
		saveStrings(new File(path), lines);
	}
	
	
	public static void saveStrings(File file, ArrayList<String> lines) {
		if(lines.size() == 0) {
			lines.add("");
		}
		
		
		try {
			FileWriter writer = new FileWriter(file, false);
			for(int i = 0; i < lines.size()-1 ; i++) {
				writer.write(lines.get(i) + System.getProperty("line.separator"));
			}
			writer.write(lines.get(lines.size()-1));
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	// loadImage
	public static Image loadImage(String path) {
		Debugger.addLogEntry(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(path);
		}
		return (Image) img;
	}

	
	
	
	// saveImages
	public static void saveImage(Img img, String type, String path) {
		saveImage(img.asAwtImage(), type, path);
	}
	
	
	public static void saveImage(Image img, String type, String path) {
		BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		bufferedImage.getGraphics().drawImage(img, 0, 0, null);
		try {
			ImageIO.write(bufferedImage, type, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	// File Searching/Listing

	public static int countFilesInDirectoryWithoutSubPaths(String directoryPath) {
		return Objects.requireNonNull(new File(directoryPath).listFiles()).length;
	}
	
	

	public static int countFilesInDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		int counter = 0;
		for (File file : directory.listFiles()) {
			if (file.isFile()) {
				counter++;
			}
			if (file.isDirectory()) {
				counter += countFilesInDirectory(file.getPath());
			}
		}
		return counter;
	}
	
	
	
	

	public static File[] getFilesInDirectoryWithoutSubPaths(String directoryPath) {
		return new File(directoryPath).listFiles();
	}
	
	
	
	public static File[] getFilesInDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		File[] files = new File[countFilesInDirectory(directoryPath)];
		int counter = 0;
		for (File file : directory.listFiles()) {
			if (file.isFile()) {
				files[counter] = file;
				counter++;
			}
			if (file.isDirectory()) {
				File[] subPaths = getFilesInDirectory(file.getPath());
		        System.arraycopy(subPaths, 0, files, counter, subPaths.length);
		        counter += subPaths.length;
			}
		}
		return files;
	}
	
	
	
	
	public static int countFilesInDirectory(String directoryPath, Predicate<File> fileFilter) {
	    return Arrays.stream(getFilesInDirectory(directoryPath)).filter(fileFilter).toArray(File[]::new).length;
	}
	
	
	public static File[] getFilesInDirectory(String directoryPath, Predicate<File> fileFilter) {
	    return Arrays.stream(getFilesInDirectory(directoryPath)).filter(fileFilter).toArray(File[]::new);
	}
	
	
	
	
	
	
	public static String[] getFilepathsInDirectory(String directoryPath){
		File directory = new File(directoryPath);
	    String[] paths = new String[countFilesInDirectory(directoryPath)];
	    int counter = 0;
	    for(File file : directory.listFiles()){
	    	paths[counter] = file.getName();
	    	counter++;
	    	if(file.isDirectory()){
	    		String[] subPaths = getFilepathsInDirectory(file.getPath());
	    		System.arraycopy(subPaths, 0, paths, counter, subPaths.length);
	    		counter += subPaths.length;
	    	}
	    }
	    return paths;
	}
	
	
	
	public static String[] getFilepathsInDirectory(String directoryPath, String ExtensionToCompare){
		File directory = new File(directoryPath);
	    String extensionToCompare = ExtensionToCompare;
	    
	    String[] paths = new String[countFilesInDirectory(directoryPath)];
	    int counter = 0;
	    for(File file : directory.listFiles()){
	    	if(file.isFile() && file.getPath().split("[.]")[file.getPath().split("[.]").length-1].equals(extensionToCompare)){
	    		paths[counter] = file.getName();
	    		counter++;
	    	}
	    	if(file.isDirectory()){
	    		String[] subPaths = getFilepathsInDirectory(file.getPath(), extensionToCompare);
	    		System.arraycopy(subPaths, 0, paths, counter, subPaths.length);
	    		counter += subPaths.length;
	    	}
	    }
	    return paths;
	}
	
	
	
	
	
	
	public static File[] chooseFilesAndFolders(String title, File currentDirectory, boolean multiSelectionPolicy, int fileSelectionMode, FileFilter fileFilter) {
		JFileChooser fc = new JFileChooser(currentDirectory);
		
		fc.setDialogTitle(title);
		
		System.out.println(title);
		fc.setMultiSelectionEnabled(multiSelectionPolicy);
		fc.setFileSelectionMode(fileSelectionMode);
		
		
		if(fileFilter != null) {
			fc.setFileFilter(fileFilter);
		}
		
		
		switch(fc.showOpenDialog(null)) {
			case JFileChooser.APPROVE_OPTION:
				return fc.getSelectedFiles();	
			default:
				Debugger.addLogEntry("FEHLER - data/FileHandler/chooseFilesAndFolders - Keine Datei ausgewählt");
				return new File[] {null};
		}
	}
	
	
	public static File chooseFile(String title, File currentDirectory, FileFilter ff) {
		return chooseFilesAndFolders(title, currentDirectory, false, JFileChooser.FILES_ONLY, ff)[0];
	}
	
	public static File[] chooseFiles(String title, File currentDirectory, FileFilter ff) {
		return chooseFilesAndFolders(title, currentDirectory, false, JFileChooser.FILES_ONLY, ff);
	}
	
	
	public static File chooseFolder(String title, File currentDirectory, FileFilter ff) {
		return chooseFilesAndFolders(title, currentDirectory, false, JFileChooser.DIRECTORIES_ONLY, ff)[0];
	}
	
	public static File[] chooseFolders(String title, File currentDirectory, FileFilter ff) {
		return chooseFilesAndFolders(title, currentDirectory, false, JFileChooser.DIRECTORIES_ONLY, ff);
	}
	
	
	
	// TODO Save...
	public static void chooseSaveDirForFilesAndFolders(String title, File currentDirectory, File file) {
		JFileChooser fc = new JFileChooser(currentDirectory);
		
		fc.setDialogTitle(title);
		
		
		fc.setSelectedFile(file);
		
		
		switch(fc.showSaveDialog(null)) {
			case JFileChooser.APPROVE_OPTION:
				//return fc.getSelectedFiles();	
			default:
				Debugger.addLogEntry("FEHLER - data/FileHandler/chooseFilesAndFolders - Keine Datei ausgewählt");
				//return new File[] {null};
		}
	}
	
	/*
	public static void chooseSaveFile(String title, File currentDirectory, FileFilter ff) {
		chooseSaveDirForFilesAndFolders(title, currentDirectory, false, JFileChooser.FILES_ONLY, ff)[0];
	}
	
	public static void chooseSaveFiles(String title, File currentDirectory, FileFilter ff) {
		chooseSaveDirForFilesAndFolders(title, currentDirectory, false, JFileChooser.FILES_ONLY, ff);
	}
	
	
	public static void chooseSaveFolder(String title, File currentDirectory, FileFilter ff) {
		chooseSaveDirForFilesAndFolders(title, currentDirectory, false, JFileChooser.DIRECTORIES_ONLY, ff)[0];
	}
	
	public static void chooseSaveFolders(String title, File currentDirectory, FileFilter ff) {
		chooseSaveDirForFilesAndFolders(title, currentDirectory, false, JFileChooser.DIRECTORIES_ONLY, ff);
	}
	*/
}
