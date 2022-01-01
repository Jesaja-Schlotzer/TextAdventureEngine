package gui;

import debugging.Debugger;
import graphics.Animation;
import graphics.GraphicsConstants;
import graphics.colors.Color;
import graphics.img.Img;
import input.MouseHandler;
import textHandler.StyledString;
import textHandler.TextHandler;

import java.util.Arrays;


public class Tree {
	private int x, y;
	private Object[] options;
	private GCC selected = GCC.NULL;
	private Color textColor = Color.BLACK; // TODO setColor + mehr Farben für "Übersichts Sachen"

	private int subXOffset = 5 * GraphicsConstants.PIXEL_SIZE_X;

	
	
	
	public Tree(Tree t) {
		setPos(t.x, t.y);
		this.options = t.options.clone();
		//setColors(textColor);
	}
	
	
	
	public Tree(int x, int y, Object... options) {
		setPos(x, y);

		setContent(options);
	}
	
	
	

	public void setContent(Object... options) {
		if(options.length == 0) {
			options = new Object[] {GCC.NULL};
			Debugger.addLogEntry("FEHLER - gui.Tree/setContent(Object...) - Object... ist leer");
		}
		
		
		this.options = options;

		for (int i = 0; i < options.length; i++) {
			String name = options[i].getClass().getSimpleName();

			switch (name) {
			case "Object[]":
				if(((Object[]) options[i]).length == 0) {
					break;
				}
				options[i] = new SubTree((Object[]) options[i]);
				break;

			case "String":
				options[i] = new GCC((String) options[i]);
				break;
			case "StyledString":
				options[i] = new GCC((StyledString) options[i]);
				break;
			case "Img":
				options[i] = new GCC((Img) options[i]);
				break;
			case "Animation":
				options[i] = new GCC((Animation) options[i]);
				break;
			case "GCC":
				options[i] = (GCC) options[i];
				break;
			default:
				Debugger.addLogEntry("FEHLER - Gui/Tree/Tree() - Objekt " + name + " nicht unterstützt!");
			}
		}
	}

	
	
	
	
	public void draw() {
		TextHandler.textAlign(TextHandler.LEFT, TextHandler.TOP);
		int yoff = y;
		for (int i = 0; i < options.length; i++) {
			switch (options[i].getClass().getSimpleName()) {
			case "GCC":
				((GCC) options[i]).draw(x, yoff, textColor);
				yoff += ((GCC) options[i]).getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;

			case "SubTree":
				SubTree subTree = (SubTree) options[i];
				subTree.title.draw(x, yoff, textColor);
				if (subTree.open) {
					drawSubTree(subTree, x + subXOffset,
							yoff + (3 * GraphicsConstants.PIXEL_SIZE_Y) + subTree.title.getHeight());
					yoff += getTreeHeight(subTree.options);
				}
				yoff += subTree.title.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;
			}
		}
	}

	
	
	
	
	private void drawSubTree(SubTree subTree, int startXoff, int startYoff) {
		int xoff = startXoff;
		int yoff = startYoff;
		for (int i = 0; i < subTree.options.length; i++) {
			switch (subTree.options[i].getClass().getSimpleName()) {
			case "GCC":
				((GCC) subTree.options[i]).draw(xoff, yoff, textColor);
				yoff += ((GCC) subTree.options[i]).getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;

			case "SubTree":
				SubTree subSubTree = (SubTree) subTree.options[i];
				subSubTree.title.draw(xoff, yoff, textColor);
				if (subSubTree.open) {
					drawSubTree(subSubTree, xoff + subXOffset,
							yoff + (3 * GraphicsConstants.PIXEL_SIZE_Y) + subSubTree.title.getHeight());
					yoff += getTreeHeight(subSubTree.options);
				}
				yoff += subSubTree.title.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;
			}
		}
	}
	
	
	

	public void handleMouseClick() {
		int yoff = y;
		for (int i = 0; i < options.length; i++) {
			switch (options[i].getClass().getSimpleName()) {
			case "GCC":
				if (MouseHandler.overButton(x - GraphicsConstants.PIXEL_SIZE_X,
						yoff - GraphicsConstants.PIXEL_SIZE_Y,
						x + ((GCC) options[i]).getWidth() + GraphicsConstants.PIXEL_SIZE_X,
						yoff + ((GCC) options[i]).getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
					selected = (GCC) options[i];
				}
				yoff += ((GCC) options[i]).getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;

			case "SubTree":
				SubTree subTree = (SubTree) options[i];
				if (subTree.open) {
					checkSubTree(subTree, x + subXOffset,
							yoff + (3 * GraphicsConstants.PIXEL_SIZE_Y) + subTree.title.getHeight());
					if (MouseHandler.overButton(x - GraphicsConstants.PIXEL_SIZE_X,
							yoff - GraphicsConstants.PIXEL_SIZE_Y,
							x + subTree.title.getWidth() + GraphicsConstants.PIXEL_SIZE_X,
							yoff + subTree.title.getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
						subTree.open = false;
					}
					yoff += getTreeHeight(subTree.options);
				} else if (MouseHandler.overButton(x - GraphicsConstants.PIXEL_SIZE_X,
						yoff - GraphicsConstants.PIXEL_SIZE_Y,
						x + subTree.title.getWidth() + GraphicsConstants.PIXEL_SIZE_X,
						yoff + subTree.title.getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
					subTree.open = true;
				}
				yoff += subTree.title.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;
			}
		}
	}

	
	
	
	
	private void checkSubTree(SubTree subTree, int startXoff, int startYoff) {
		int xoff = startXoff;
		int yoff = startYoff;
		for (int i = 0; i < subTree.options.length; i++) {
			switch (subTree.options[i].getClass().getSimpleName()) {
			case "GCC":
				if (MouseHandler.overButton(xoff - GraphicsConstants.PIXEL_SIZE_X,
						yoff - GraphicsConstants.PIXEL_SIZE_Y,
						xoff + ((GCC) subTree.options[i]).getWidth() + GraphicsConstants.PIXEL_SIZE_X,
						yoff + ((GCC) subTree.options[i]).getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
					selected = (GCC) subTree.options[i];
				}
				yoff += ((GCC) subTree.options[i]).getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;

			case "SubTree":
				SubTree subSubTree = (SubTree) subTree.options[i];
				if (subSubTree.open) {
					checkSubTree(subSubTree, xoff + subXOffset,
							yoff + (3 * GraphicsConstants.PIXEL_SIZE_Y) + subSubTree.title.getHeight());
					if (MouseHandler.overButton(xoff - GraphicsConstants.PIXEL_SIZE_X,
							yoff - GraphicsConstants.PIXEL_SIZE_Y,
							xoff + subSubTree.title.getWidth() + GraphicsConstants.PIXEL_SIZE_X,
							yoff + subSubTree.title.getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
						subSubTree.open = false;
					}
					yoff += getTreeHeight(subSubTree.options);
				} else if (MouseHandler.overButton(xoff - GraphicsConstants.PIXEL_SIZE_X,
						yoff - GraphicsConstants.PIXEL_SIZE_Y,
						xoff + subSubTree.title.getWidth() + GraphicsConstants.PIXEL_SIZE_X,
						yoff + subSubTree.title.getHeight() + GraphicsConstants.PIXEL_SIZE_Y)) {
					subSubTree.open = true;
				}
				yoff += subTree.title.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_X);
				break;
			}
		}
	}

	
	
	
	private int getTreeHeight(Object[] options) {
		int yoff = 0;
		for (int i = 0; i < options.length; i++) {
			switch (options[i].getClass().getSimpleName()) {
			case "GCC":
				yoff += ((GCC) options[i]).getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;
			case "SubTree":
				SubTree subTree = (SubTree) options[i];
				if (subTree.open) {
					yoff += getTreeHeight(subTree.options);
				}
				yoff += subTree.title.getHeight() + (2 * GraphicsConstants.PIXEL_SIZE_Y);
				break;
			}
		}
		return yoff + (2 * GraphicsConstants.PIXEL_SIZE_Y);
	}

	
	
	
	
	
	
	
	
	
	
	public void setPos(int x, int y) {
		this.x = x / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		this.y = y / GraphicsConstants.PIXEL_SIZE_Y * GraphicsConstants.PIXEL_SIZE_Y;
	}
	
	
	
	public Tree setSubfolderXOffset(int offset) {
		this.subXOffset = offset / GraphicsConstants.PIXEL_SIZE_X * GraphicsConstants.PIXEL_SIZE_X;
		
		return this;
	}
	
	
	public GCC getSelected() {
		if (selected == null) {
			return GCC.NULL;
		}
		return selected;
	}

	
	
	
	
	
	
	
	
	
	private class SubTree {
		Object[] options;
		boolean open;
		GCC title;

		SubTree(Object[] options) {
			for (int i = 0; i < options.length; i++) {
				String name = options[i].getClass().getSimpleName();
				switch (name) {
				case "Object[]":
					options[i] = new SubTree((Object[]) options[i]);
					break;

				case "String":
					options[i] = new GCC((String) options[i]);
					break;
				case "StyledString":
					options[i] = new GCC((StyledString) options[i]);
					break;
				case "Img":
					options[i] = new GCC((Img) options[i]);
					break;
				case "Animation":
					options[i] = new GCC((Animation) options[i]);
					break;
				case "GCC":
					options[i] = (GCC) options[i];
					break;
				default:
					Debugger.addLogEntry("FEHLER - Gui/Tree/Tree() - Objekt " + name + " nicht unterstützt!");
				}
			}

			this.title = (GCC) options[0];
			this.options = Arrays.copyOfRange(options, 1, options.length);
		}
	}
}
