package debugging;

import graphics.Painter;
import graphics.colors.Color;
import graphics.img.Img;
import util.Timer;

public class DebugShapeCreator {

	
	class DebugShape {
		int x, y, type;
		Color c = new Color("#ff00ff");
		Timer ttlTimer;

		void draw() {
			// hier passiert nix
		}
	}

	
	
	
	class DebugPoint extends DebugShape { // ----------Point----------

		DebugPoint(int x, int y) {
			ttlTimer = new Timer(Debugger.TTL_IN_MILLIS);
			ttlTimer.startTimer();
			this.x = x;
			this.y = y;
		}

		void draw() {
			Painter.drawPoint(x, y, c);
		}
	}

	
	
	
	class DebugLine extends DebugShape { // ----------Line----------
		int x2, y2;

		DebugLine(int x, int y, int x2, int y2) {
			ttlTimer = new Timer(Debugger.TTL_IN_MILLIS);
			ttlTimer.startTimer();
			this.x = x;
			this.y = y;
			this.x2 = x2;
			this.y2 = y2;
		}

		void draw() {
			Painter.drawLine(x, y, x2, y2, c);
		}
	}
	
	
	

	class DebugRect extends DebugShape { // ----------Rect----------
		int w, h;

		DebugRect(int x, int y, int w, int h) {
			ttlTimer = new Timer(Debugger.TTL_IN_MILLIS);
			ttlTimer.startTimer();
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}

		void draw() {
			Painter.drawRect(x, y, w, h, Color.TRANSPARENT, c);
		}
	}

	
	
	
	class DebugCircle extends DebugShape { // ----------Circle----------
		int radius;

		DebugCircle(int x, int y, int radius) {
			ttlTimer = new Timer(Debugger.TTL_IN_MILLIS);
			ttlTimer.startTimer();
			this.x = x;
			this.y = y;
			this.radius = radius;
		}

		void draw() {
			Painter.drawCircle(x, y, radius, c, Color.TRANSPARENT);
		}
	}
	
	
	
	
	
	
	class DebugImage extends DebugShape{
		Img img;

		DebugImage(int x, int y, Img img) {
			ttlTimer = new Timer(Debugger.TTL_IN_MILLIS);
			ttlTimer.startTimer();
			this.x = x;
			this.y = y;
			this.img = img;
		}

		void draw() {
			Painter.drawImage(img, x, y);
		}
	}
}