package graphics.colors;

import java.util.ArrayList;
import java.util.Arrays;


public class Palette{
	Color[] paletteColors;
	final boolean isEditable;
	
	
	
	public Palette(boolean editability, Color... colors) {
		paletteColors = Arrays.stream(colors).filter((Color c) -> c != null).toArray(Color[]::new);
		isEditable = editability;
	}
	
	public Palette(Color... colors) {
		this(true, colors);
	}
	
	
	
	public Palette(boolean editability, String... colorValues) {
		paletteColors = Arrays.stream(colorValues).toArray(Color[]::new);
		isEditable = editability;
	}
	
	public Palette(String... colorValues) {
		this(true, colorValues);
	}
	
	
	
	public Palette(boolean editability, java.awt.Color... colors) {
		paletteColors = Arrays.stream(colors).filter((java.awt.Color c) -> c != null).toArray(Color[]::new);
		isEditable = editability;
	}
	
	public Palette(java.awt.Color... colors) {
		this(true, colors);
	}
	
	
	
	
	public Palette(boolean editability, Object[] objs) {
		ArrayList<Color> colors = new ArrayList<Color>();
		
		for(Object obj : objs) {
			if(obj instanceof Color) {
				colors.add((Color) obj);
			}else if(obj instanceof String) {
				Color c = new Color((String) obj);
				if(c != null){
					colors.add(c);
				}
			}else if(obj instanceof java.awt.Color) {
				Color c = new Color((java.awt.Color) obj);
				if(c != null){
					colors.add(c);
				}
			}else if(obj instanceof Palette) {
				colors.addAll(Arrays.asList(((Palette) obj).paletteColors));
			}
		}
		
		paletteColors = colors.toArray(Color[]::new);
		
		isEditable = editability;
	}
	
	
	
	public Palette(Object[] objs) {
		this(true, objs);
	}
	
	
	
	
	
	
	
	
	public Palette setColors(Color... colors) {
		if(!isEditable) {
			return this;
		}
		
		
		paletteColors = Arrays.stream(colors).filter((Color c) -> c != null).toArray(Color[]::new);
		
		return this;
	}
	
	
	
	
	public Palette setColors(String... colorValues) {
		if(!isEditable) {
			return this;
		}
		
		
		paletteColors = Arrays.stream(colorValues).toArray(Color[]::new);

		return this;
	}

	
	

	public Palette setColors(java.awt.Color... colors) {
		if(!isEditable) {
			return this;
		}
		
		
		paletteColors = Arrays.stream(colors).filter((java.awt.Color c) -> c != null).toArray(Color[]::new);

		return this;
	}
	
	
	
	
	public Palette setColors(Object... objs) {
		if(!isEditable) {
			return this;
		}
		
		
		ArrayList<Color> colors = new ArrayList<Color>();
		
		for(Object obj : objs) {
			if(obj instanceof Color) {
				colors.add((Color) obj);
			}else if(obj instanceof String) {
				Color c = new Color((String) obj);
				if(c != null){
					colors.add(c);
				}
			}else if(obj instanceof java.awt.Color) {
				Color c = new Color((java.awt.Color) obj);
				if(c != null){
					colors.add(c);
				}
			}else if(obj instanceof Palette) {
				colors.addAll(Arrays.asList(((Palette) obj).paletteColors));
			}
		}
		
		paletteColors = colors.toArray(Color[]::new);
		

		return this;
	}
	
	
	
	
	
	
	
	public Palette setColor(int index, Color c) {
		if(!isEditable) {
			return this;
		}
		
		
		if(index >= 0 && index < paletteColors.length) {
			paletteColors[index] = c;
		}

		return this;
	}
	
	
	
	
	public Palette setColor(int index, String hex) {
		if(!isEditable) {
			return this;
		}
		
		
		if(index >= 0 && index < paletteColors.length) {
			paletteColors[index] = new Color(hex);
		}

		return this;
	}
	
	
	
	
	public Palette setColor(int index, java.awt.Color c) {
		if(!isEditable) {
			return this;
		}
		
		
		if(index >= 0 && index < paletteColors.length) {
			paletteColors[index] = new Color(c);
		}

		return this;
	}
	
	
	
	
	
	
	
	public Color[] getColors() {
		return paletteColors;
	}
	
	
	
	public Color getColor(int index) {
		if(index >= 0 && index < paletteColors.length) {
			return paletteColors[index];
		}
		return Color.DEBUG;
	}
	
	
	
	
	
	
	public Palette copy() {
		return new Palette(this.paletteColors);
	}
	
	
	
	
	
	public Palette append(Color color) {
		if(!isEditable) {
			return this;
		}
		
		
		Color[] newPaletteColors = new Color[paletteColors.length+1];
		
		for(int i = 0; i < paletteColors.length; i++) {
			newPaletteColors[i] = paletteColors[i];
		}
		
		newPaletteColors[paletteColors.length] = color;
		
		
		paletteColors = newPaletteColors;
		
		
		return this;
	}
	
	
	
	public Palette appendAll(Color... colors) {
		if(!isEditable) {
			return this;
		}
		
		
		Color[] newPaletteColors = new Color[paletteColors.length+colors.length];
		
		for(int i = 0; i < paletteColors.length; i++) {
			newPaletteColors[i] = paletteColors[i];
		}
		
		
		for(int i = paletteColors.length; i < colors.length; i++) {
			newPaletteColors[paletteColors.length] = colors[i-paletteColors.length];
		}
		
		
		paletteColors = newPaletteColors;
		
		
		return this;
	}
	
	
	
	
	
	
	public Palette remove(int index) {
		if(!isEditable) {
			return this;
		}
		
		
		if(index < 0 || index >= paletteColors.length) {
			return this;
		}
		
		
		Color[] newPaletteColors = new Color[paletteColors.length-1];
		
		int counter = 0;
		for(int i = 0; i < paletteColors.length; i++) {
			if(i != index) {
				newPaletteColors[counter] = paletteColors[i];
				counter++;
			}
		}
		
		
		paletteColors = newPaletteColors;
		
		
		return this;
	}
	
	
	
	
	public Palette removeAll(int... indices) {
		if(!isEditable) {
			return this;
		}
		
		
		ArrayList<Color> newPaletteColors = new ArrayList<Color>();
		
		
		for(int i = 0; i < paletteColors.length; i++) {
			for(int index : indices) {
				if(i != index) {
					newPaletteColors.add(paletteColors[i]);
				}
			}
		}
		
		
		paletteColors = newPaletteColors.toArray(new Color[newPaletteColors.size()]);
		
		
		return this;
	}
	
	
	
	
	
	
	
	
	
	
	// Preset - Paletten
	
	public static Palette STANDARD_PALETTE = new Palette(false,
			Color.BLACK, 		// 0
			Color.RED, 			// 1
			Color.ORANGE, 		// 2
			Color.LIGHT_ORANGE, // 3
			Color.YELLOW, 		// 4
			Color.GREEN, 		// 5
			Color.SHADOW_GRAY);	// 6
	
	
	
	
	public static Palette NULL_PALETTE = new Palette(Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR,
													 Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR,
													 Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR,
													 Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR, Color.NO_COLOR);
			
}
