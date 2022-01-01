package graphics;


import events.Event;
import graphics.colors.Color;
import graphics.img.Img;
import util.EventTimer;
import screen.ScreenHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Painter {
	
	// Constants
	
	//public static final int STANDARD_TTL = 10000;
	
	// End
	
	
	
	
	// Formen und Ähnliches

	
	// Background
	public static void drawBackground(Color c) {
		if (c.isTransparent()) {
			return;
		}
		
		for (int x = 0; x < GraphicsConstants.REF_SCREEN_SIZE_X; x += GraphicsConstants.PIXEL_SIZE_X) {
			for (int y = 0; y < GraphicsConstants.REF_SCREEN_SIZE_Y; y += GraphicsConstants.PIXEL_SIZE_Y) {
				drawPoint(x, y, c);
			}
		}
	}

	
	
	
	
	
	// Point
	public static void drawPoint(int x, int y, Color c) {
		if (c.isTransparent()) {
			return;
		}
		
		ScreenHandler.getInstance().setScreenBuffer(x / GraphicsConstants.PIXEL_SIZE_X, y / GraphicsConstants.PIXEL_SIZE_Y, c);
	}

	
	
	
	
	
	// Line
	public static void drawLine(int x1, int y1, int x2, int y2, Color c) {
		if (c.isTransparent()) {
			return;
		}

		x1 /= GraphicsConstants.PIXEL_SIZE_X;
		y1 /= GraphicsConstants.PIXEL_SIZE_Y;
		x2 /= GraphicsConstants.PIXEL_SIZE_X;
		y2 /= GraphicsConstants.PIXEL_SIZE_Y;

		int d = 0;
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);

		int ix = x1 < x2 ? 1 : -1;
		int iy = y1 < y2 ? 1 : -1;

		int x = x1;
		int y = y1;

		if (dx >= dy) {
			while (true) {
				drawPoint(x * GraphicsConstants.PIXEL_SIZE_X, y * GraphicsConstants.PIXEL_SIZE_Y, c);
				if (x == x2)
					break;
				x += ix;
				d += dy * 2;
				if (d > dx) {
					y += iy;
					d -= dx * 2;
				}
			}
		} else {
			while (true) {
				drawPoint(x * GraphicsConstants.PIXEL_SIZE_X, y * GraphicsConstants.PIXEL_SIZE_Y, c);
				if (y == y2) {
					break;
				}
				y += iy;
				d += dx * 2;
				if (d > dy) {
					x += ix;
					d -= dy * 2;
				}
			}
		}
	}

	
	
	
	
	
	
	
	// Rect
	public static void drawRect(int x, int y, int w, int h, Color color) {
		if (color.isTransparent()) {
			return;
		}

		for (int i = 0; i < w; i += GraphicsConstants.PIXEL_SIZE_X) {
			for (int j = 0; j < h; j += GraphicsConstants.PIXEL_SIZE_Y) {
				drawPoint(x + i, y + j, color);
			}
		}
	}

	
	
	
	public static void drawRect(int x, int y, int w, int h, Color backgroundColor, Color frameColor) {
		if (!backgroundColor.isTransparent()) {
			drawRect(x + GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y, w - (2 * GraphicsConstants.PIXEL_SIZE_X), h - (2 * GraphicsConstants.PIXEL_SIZE_Y), backgroundColor); // background
		}
		
		if (!frameColor.isTransparent()) {
			drawRect(x, y, w, GraphicsConstants.PIXEL_SIZE_Y, frameColor); // oben
			drawRect(x, y + h - GraphicsConstants.PIXEL_SIZE_Y, w, GraphicsConstants.PIXEL_SIZE_Y, frameColor); // unten
			drawRect(x, y + GraphicsConstants.PIXEL_SIZE_Y, GraphicsConstants.PIXEL_SIZE_X, h - 2*GraphicsConstants.PIXEL_SIZE_Y, frameColor); // links
			drawRect(x + w - GraphicsConstants.PIXEL_SIZE_X, y + GraphicsConstants.PIXEL_SIZE_Y, GraphicsConstants.PIXEL_SIZE_X, h - 2*GraphicsConstants.PIXEL_SIZE_Y, frameColor); // rechts
		}
		
	}
	
	
	
	
	
	public static void drawRect(int x, int y, int w, int h, int borderWidth, Color backgroundColor, Color frameColor) {
		if (!frameColor.isTransparent()) {
			drawRect(x, y, w, h, frameColor); // background
			
		}
		
		if (!backgroundColor.isTransparent()) {
			drawRect(x + (GraphicsConstants.PIXEL_SIZE_X * borderWidth), y + (GraphicsConstants.PIXEL_SIZE_Y * borderWidth), w - (2 * borderWidth * GraphicsConstants.PIXEL_SIZE_X), h - (2 * borderWidth * GraphicsConstants.PIXEL_SIZE_Y), backgroundColor); // background
		}
	}

	
	
	
	
	
	
	
	// Triangle
	public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color c) {
		if (c.isTransparent()) {
			return;
		}

		drawFilledPolygon(c, c, x1, y1, x2, y2, x3, y3);
		
		//drawLine(x1, y1, x2, y2, c);
		//drawLine(x2, y2, x3, y3, c);
		//drawLine(x3, y3, x1, y1, c);
	}
	
	
	

	public static void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Color inner, Color frame) {
		drawFilledPolygon(inner, frame, x1, y1, x2, y2, x3, y3);
	}
	
	


	
	
	
	
	
	// Polygon
	public static void drawPolygon(Color c, int... positions) {
		if (c.isTransparent()) {
			return;
		}

		for (int i = 0; i < positions.length - (positions.length % 2) - 2; i += 2) {
			drawLine(positions[i], positions[i + 1], positions[i + 2], positions[i + 3], c);
		}
		drawLine(positions[positions.length - (positions.length % 2) - 2],
				positions[positions.length - (positions.length % 2) - 1], positions[0], positions[1], c);
	}

	
	
	
	
	public static void drawFilledPolygon(Color inner, Color frame, int... positions) {
		for (int i = 0; i < positions.length; i++) {
			if (i % 2 == 0) {
				positions[i] /= GraphicsConstants.PIXEL_SIZE_X;
			} else {
				positions[i] /= GraphicsConstants.PIXEL_SIZE_Y;
			}
		}

		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MIN_VALUE;

		for (int i = 0; i < positions.length; i++) {
			if (i % 2 == 0) {
				if (positions[i] < minX) {
					minX = positions[i];
				}
				if (positions[i] > maxX) {
					maxX = positions[i];
				}
			} else {
				if (positions[i] < minY) {
					minY = positions[i];
				}
				if (positions[i] > maxY) {
					maxY = positions[i];
				}
			}
		}

		int deltaX = maxX - minX;
		int deltaY = maxY - minY;

		boolean[][] grid = new boolean[deltaX + 3][deltaY + 3];
		
		
		int[] temp = new int[positions.length+2];
	    System.arraycopy(positions, 0, temp, 0, Math.min(positions.length+2, positions.length));
	   
	    temp[temp.length-2] = temp[0];
	    temp[temp.length-1] = temp[1];
	    
	    positions = temp;
		
		for (int i = 0; i < positions.length - (positions.length % 2) - 2; i += 2) {
			int x1 = positions[i] - minX + 1;
			int y1 = positions[i + 1] - minY + 1;
			int x2 = positions[i + 2] - minX + 1;
			int y2 = positions[i + 3] - minY + 1;

			int d = 0;
			int dx = Math.abs(x2 - x1);
			int dy = Math.abs(y2 - y1);

			int ix = x1 < x2 ? 1 : -1;
			int iy = y1 < y2 ? 1 : -1;

			int x = x1;
			int y = y1;

			if (dx >= dy) {
				while (true) {
					grid[x][y] = true;
					if (x == x2)
						break;
					x += ix;
					d += dy * 2;
					if (d > dx) {
						y += iy;
						d -= dx * 2;
					}
				}
			} else {
				while (true) {
					grid[x][y] = true;
					if (y == y2) {
						break;
					}
					y += iy;
					d += dx * 2;
					if (d > dy) {
						x += ix;
						d -= dy * 2;
					}
				}
			}
		}

		if (!inner.isTransparent()) {
			grid = fillShape(grid);

			for (int x = 0; x < grid.length; x++) {
				for (int y = 0; y < grid[0].length; y++) {
					if (!grid[x][y]) {
						drawPoint((minX + x - 1) * GraphicsConstants.PIXEL_SIZE_X, (minY + y - 1) * GraphicsConstants.PIXEL_SIZE_Y, inner);
					}
				}
			}
		}

		if (frame.isTransparent()) {
			return;
		}

		for (int i = 0; i < positions.length - (positions.length % 2) - 2; i += 2) {
			int x1 = positions[i];
			int y1 = positions[i + 1];
			int x2 = positions[i + 2];
			int y2 = positions[i + 3];

			int d = 0;
			int dx = Math.abs(x2 - x1);
			int dy = Math.abs(y2 - y1);

			int ix = x1 < x2 ? 1 : -1;
			int iy = y1 < y2 ? 1 : -1;

			int x = x1;
			int y = y1;

			if (dx >= dy) {
				while (true) {
					drawPoint(x * GraphicsConstants.PIXEL_SIZE_X, y * GraphicsConstants.PIXEL_SIZE_Y, frame);
					if (x == x2)
						break;
					x += ix;
					d += dy * 2;
					if (d > dx) {
						y += iy;
						d -= dx * 2;
					}
				}
			} else {
				while (true) {
					drawPoint(x * GraphicsConstants.PIXEL_SIZE_X, y * GraphicsConstants.PIXEL_SIZE_Y, frame);
					if (y == y2) {
						break;
					}
					y += iy;
					d += dx * 2;
					if (d > dy) {
						x += ix;
						d -= dy * 2;
					}
				}
			}
		}
	}

	
	
	
	
	private static boolean[][] fillShape(boolean[][] grid) {

		Queue<Point> queue = new LinkedList<Point>();
		queue.add(new Point(0, 0));

		while (!queue.isEmpty()) {
			Point p = queue.remove();

			if (p.x >= 0 && p.x < grid.length && p.y >= 0 && p.y < grid[0].length) {
				if (!grid[p.x][p.y]) {
					grid[p.x][p.y] = true;

					queue.add(new Point(p.x + 1, p.y));
					queue.add(new Point(p.x - 1, p.y));
					queue.add(new Point(p.x, p.y + 1));
					queue.add(new Point(p.x, p.y - 1));
				}
			}
		}

		return grid;
	}

	
	
	
	
	
	
	
	
	// Circle
	public static void drawCircleContour(int centerX, int centerY, int radius, Color c) {
		if (c.isTransparent()) {
			return;
		}

		int x = radius;
		int y = 0;

		drawPoint(x + centerX, y + centerY, c);

		if (radius > 0) {
			drawPoint(x + centerX, -y + centerY, c);
			drawPoint(y + centerX, x + centerY, c);
			drawPoint(-y + centerX, x + centerY, c);
		}

		int P = 1 - radius;
		while (x > y) {
			y++;

			if (P <= 0) {
				P += 2 * y + 1;
			} else {
				x--;
				P += 2 * y - 2 * x + 1;
			}

			if (x < y) {
				break;
			}

			drawPoint(x + centerX, y + centerY, c);
			drawPoint(-x + centerX, y + centerY, c);
			drawPoint(x + centerX, -y + centerY, c);
			drawPoint(-x + centerX, -y + centerY, c);

			if (y != x) {
				drawPoint(y + centerX, x + centerY, c);
				drawPoint(-y + centerX, x + centerY, c);
				drawPoint(y + centerX, -x + centerY, c);
				drawPoint(-y + centerX, -x + centerY, c);

			}
		}
	}

	
	
	
	public static void drawCircle(int centerX, int centerY, int radius, Color c) {
		if (c.isTransparent()) {
			return;
		}

		drawCircleContour(centerX, centerY, radius, c);
		for (int i = GraphicsConstants.PIXEL_SIZE_X; i < radius; i += GraphicsConstants.PIXEL_SIZE_X) {
			drawCircleContour(centerX, centerY, radius - i, c);
		}
	}
	
	
	

	public static void drawCircle(int centerX, int centerY, int radius, Color inner, Color frame) {
		if (!inner.isTransparent()) {
			for (int i = GraphicsConstants.PIXEL_SIZE_X; i < radius; i += GraphicsConstants.PIXEL_SIZE_X) {
				drawCircleContour(centerX, centerY, radius - i, inner);
			}
		}

		if (!frame.isTransparent()) {
			drawCircleContour(centerX, centerY, radius, frame);
		}
	}

	
	
	
	public static void drawFilledCircle(int centerX, int centerY, int radius, Color frame, Color inner) {
		radius = Math.abs(radius);
		centerX /= GraphicsConstants.PIXEL_SIZE_X;
		centerY /= GraphicsConstants.PIXEL_SIZE_Y;
		radius /= GraphicsConstants.PIXEL_SIZE_X;

		int x = radius;
		int y = 0;

		boolean[][] grid = new boolean[radius * 2 + 3][radius * 2 + 3];

		if (radius > 0) {
			grid[-x + radius + 1][radius + 1] = true;
			grid[x + radius + 1][radius + 1] = true;
			grid[radius + 1][-x + radius + 1] = true;
			grid[radius + 1][x + radius + 1] = true;
		}

		int P = 1 - radius;
		while (x > y) {
			y++;

			if (P <= 0) {
				P += 2 * y + 1;
			} else {
				x--;
				P += 2 * y - 2 * x + 1;
			}

			if (x < y) {
				break;
			}

			grid[x + radius + 1][y + radius + 1] = true;
			grid[-x + radius + 1][y + radius + 1] = true;
			grid[x + radius + 1][-y + radius + 1] = true;
			grid[-x + radius + 1][-y + radius + 1] = true;

			if (y != x) {
				grid[y + radius + 1][x + radius + 1] = true;
				grid[-y + radius + 1][x + radius + 1] = true;
				grid[y + radius + 1][-x + radius + 1] = true;
				grid[-y + radius + 1][-x + radius + 1] = true;
			}
		}

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j]) {
					drawPoint((centerX - radius + i - 1) * GraphicsConstants.PIXEL_SIZE_X, (centerY - radius + j - 1) * GraphicsConstants.PIXEL_SIZE_Y,
							frame);
				}
			}
		}

		grid = fillShape(grid);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!grid[i][j]) {
					drawPoint((centerX - radius + i - 1) * GraphicsConstants.PIXEL_SIZE_X, (centerY - radius + j - 1) * GraphicsConstants.PIXEL_SIZE_Y,
							inner);
				}
			}
		}
	}

	
	
	
	
	
	
	
	
	// Rings
	public static void drawRings(int centerX, int centerY, int radius, int offset, Color c) {
		if (c.isTransparent()) {
			return;
		}

		drawCircleContour(centerX, centerY, radius, c);
		for (int i = GraphicsConstants.PIXEL_SIZE_X; i < radius; i += offset) {
			drawCircleContour(centerX, centerY, radius - i, c);
		}
	}
	
	
	

	public static void drawRings(int centerX, int centerY, int radius, int offset, Color inner, Color frame) {
		if (!inner.isTransparent()) {
			for (int i = GraphicsConstants.PIXEL_SIZE_X; i < radius; i += offset) {
				drawCircleContour(centerX, centerY, radius - i, inner);
			}
		}

		if (!frame.isTransparent()) {
			drawCircleContour(centerX, centerY, radius, frame);
		}
	}

	
	
	
	
	
	

	// Img

	
	
	public static void drawImage(Img img, int x, int y, int w, int h) {
		drawImage(img.subImage(0, 0, w/GraphicsConstants.PIXEL_SIZE_X, h/GraphicsConstants.PIXEL_SIZE_Y), x, y);
	}
	
	
	
	public static void drawImage(Img img, int x, int y) {
		if (!img.isLoaded()) {
			//ImgLoadThreadPool.getInstance().addLoadTask(img);
			long time1 = System.currentTimeMillis();
			img.loadImg();
			System.out.println(img + ": " + time1 + " | " + System.currentTimeMillis() + " => " + (-time1 + System.currentTimeMillis()));
			//return;
		}
		
		
		x /= GraphicsConstants.PIXEL_SIZE_X;
		y /= GraphicsConstants.PIXEL_SIZE_Y;

		for (int xoff = 0; xoff < img.getImgRaster().getRaster().length; xoff++) {
			for (int yoff = 0; yoff < img.getImgRaster().getRaster()[0].length; yoff++) {
				ScreenHandler.getInstance().setScreenBuffer(x + xoff, y + yoff, img.getImgRaster().getRaster()[xoff][yoff]);
			}
		}
		
		img.restartImgClearTimer();
	}
	
	
	
	
	
	
	
	/*
	 * Unnötig weil drawImage(img.subImage(x, y, w, h), X, Y)
	 *
	public static void drawSubImage(Img img, int X, int Y, int startPixelX, int startPixelY, int endPixelX, int endPixelY) {
		int posX = X / GraphicsConstants.PIXEL_SIZE_X;
		int posY = Y / GraphicsConstants.PIXEL_SIZE_Y;

		
		if (!img.isLoaded()) {
			Img.addToQueue(img);
			return;
		}
		
		if(startPixelX > img.getImgRaster().getRaster().length || startPixelY > img.getImgRaster().getRaster()[0].length) {
			img.imgClearTimerRef.startTimer();
			return;
		}
		
		endPixelX = Math.min(endPixelX, img.getImgRaster().getRaster().length);
		endPixelY = Math.min(endPixelY, img.getImgRaster().getRaster()[0].length);
		
		for (int x = 0; x < endPixelX-startPixelX; x++) {
			for (int y = 0; y < endPixelY-startPixelY; y++) {
				
				ScreenHandler.setScreenBuffer(posX + x, posY + y, img.getImgRaster().getRaster()[x + startPixelX][y + startPixelX]);
			}
		}
		
		img.imgClearTimerRef.startTimer();
	}
	*/
	
	
	
	
	
	
	public static void drawLoopedAnimation(Animation animation, int x, int y, int w, int h) {
		drawLoopedAnimation(animation.subAnimation(0, 0, w/GraphicsConstants.PIXEL_SIZE_X, h/GraphicsConstants.PIXEL_SIZE_Y), x, y);
	}
	
	

	public static void drawLoopedAnimation(Animation animation, int x, int y) {
		int imgAmount = animation.getImgs().length;

		animation.animationProgress += animation.animationSpeed;
		int imgNr = (int) Math.floor(animation.animationProgress);

		if (imgNr >= imgAmount) {
			animation.animationProgress = 0;
			imgNr = 0;
		}

		drawImage(animation.getImgs()[imgNr], x, y);
	}

	
	
	
	
	
	public static void drawAnimation(Animation animation, int x, int y, int w, int h, Event followingEvent) {
		drawAnimation(animation.subAnimation(0, 0, w/GraphicsConstants.PIXEL_SIZE_X, h/GraphicsConstants.PIXEL_SIZE_Y), x, y, followingEvent);
	}
	
	
	
	public static void drawAnimation(Animation animation, int x, int y, Event followingEvent) {
		int imgAmount = animation.getImgs().length;

		animation.animationProgress += animation.animationSpeed;
		int imgNr = (int) Math.floor(animation.animationProgress);

		if (imgNr < imgAmount) {
			drawImage(animation.getImgs()[imgNr], x, y);
		} else {
			animation.animationProgress = 0;
			animation.setFinished(true);
			followingEvent.start();
		}
	}

	
	
	
	
	
	
	
	// Img clear handling


	public static ArrayList<EventTimer> imgClearTimer = new ArrayList<>();
	
	
	
	public static void checkImgClearTimers() {
		for (EventTimer eventTimer : imgClearTimer) {
			eventTimer.proceedTimer();
		}
		
		System.out.println("ICT: " + imgClearTimer.size());
	}

}
