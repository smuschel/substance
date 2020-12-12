package de.smuschel.substance.navigation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class MenuItem extends Canvas {
	private Color animationBackgroundDark = new Color(220, 220, 220);
	private ClickBackgroundAnimation clickBackgroundAnimation;

	public MenuItem(Composite parent, int style) {
		super(parent, style);
		init();
	}

	protected void init() {
		addListener(SWT.Paint, e -> paint(e));
		addListener(SWT.MouseUp, e -> click(e));
	}

	protected void click(Event e) {
		clickBackgroundAnimation = new ClickBackgroundAnimation(e.x, e.y);
		clickBackgroundAnimation.startAnimation();
	}

	protected void paint(Event e) {
		GC gc = e.gc;
		Rectangle area = getClientArea();

		drawAnimationLayer(gc, area);
		drawTextLayer(gc, area);
	}

	protected void drawTextLayer(GC gc, Rectangle area) {
		Point textArea = gc.textExtent("Klick mich!");
		gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
		gc.drawText("Klick mich!", area.width / 2 - textArea.x / 2, area.height / 2 - textArea.y / 2, true);
	}

	protected void drawAnimationLayer(GC gc, Rectangle area) {
		if (clickBackgroundAnimation != null && !clickBackgroundAnimation.animationFinished()) {
			clickBackgroundAnimation.draw(gc, area);
		} else {
			gc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		animationBackgroundDark.dispose();
	}

	private class ClickBackgroundAnimation implements Runnable {

		static final int TIMER_INTERVALL = 30;
		private int clickXPos;
		private int clickYPos;
		private int radius = 0;
		private GC animationGc;
		private int alpha = 255;
		private int alphaStep = 10;
		private Image image;
		private boolean animationFinished;

		public ClickBackgroundAnimation(int x, int y) {
			this.clickXPos = x;
			this.clickYPos = y;
		}

		public void startAnimation() {
			init();
			getDisplay().timerExec(ClickBackgroundAnimation.TIMER_INTERVALL, this);
		}

		public boolean animationFinished() {
			return animationFinished;
		}

		protected void init() {
			if (image != null)
				image.dispose();
			image = new Image(getDisplay(), getClientArea());
			animationFinished = false;
			animationGc = new GC(image);
		}

		public void draw(GC gc, Rectangle area) {
			animationGc.setAlpha(255);
			animationGc.setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
			animationGc.fillRectangle(area);
			animationGc.setAlpha(alpha);
			int x = clickXPos - radius;
			int y = clickYPos - radius;

			animationGc.setBackground(animationBackgroundDark);
			animationGc.fillOval(x, y, 2 * radius, 2 * radius);

			if (image != null)
				gc.drawImage(image, 0, 0);
		}

		@Override
		public void run() {
			if (animate())
				MenuItem.this.getDisplay().timerExec(TIMER_INTERVALL, this);
		}

		boolean animate() {
			radius += 10;
			if (!MenuItem.this.isDisposed())
				MenuItem.this.redraw();

			Rectangle area = getClientArea();
			alphaStep = (clickXPos < area.width / 2) ? (area.width - clickXPos) / 10 : clickXPos / 10;
			if (farSideReached(clickXPos, radius, area.width)
					&& farSideReached(clickYPos, radius, area.height)) {
				animationFinished = true;
				return false;
			}

			alpha -= alphaStep;
			if (alpha < 1)
				alpha = 1;
			return true;
		}

		boolean farSideReached(int location, int radius, int dimension) {
			if (location < dimension / 2) {
				return location + radius > dimension;
			} else {
				return (location - radius) < 0;
			}
		}
	}

}
