package data;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

public class ClipboardHandler {

	private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static final Clipboard clipboard = toolkit.getSystemClipboard();

	public static String getTextFromClipboard() {
		try {
			return (String) clipboard.getData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "FEHLER: Clipboard konnte nicht geladen werden.";
	}
}