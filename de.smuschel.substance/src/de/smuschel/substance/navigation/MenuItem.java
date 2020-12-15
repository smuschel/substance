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
	private final Color animationBackgroundDefault = new Color(220, 220, 220);
	private final Color hoverBackgroundDefault = new Color(240, 240, 240);
	private Color animationBackground;
	private Color hoverBackground;
	private ClickBackgroundAnimation clickBackgroundAnimation;
	private String text;
	private Image image;
	private Color background;
	private int innerPadding = 5;
	private int imageTextPadding = 5;

	public MenuItem(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	protected void init() {
		background = getBackground();
		addListener(SWT.Paint, e -> paint(e));
		addListener(SWT.MouseUp, e -> click(e));
		addListener(SWT.MouseEnter, e -> enter(e));
		addListener(SWT.MouseExit, e -> exit(e));

		setCapture(true);
	}

	protected void enter(Event e) {
		background = getHoverBackground();
		redraw();
	}

	protected void exit(Event e) {
		background = getBackground();
		redraw();
	}

	protected void click(Event e) {
		if (e.button == 1) {
			clickBackgroundAnimation = new ClickBackgroundAnimation(e.x, e.y);
			clickBackgroundAnimation.startAnimation();
		}
	}

	protected void paint(Event e) {
		GC gc = e.gc;
		Rectangle area = getClientArea();

		drawAnimationLayer(gc, area);
		drawTextLayer(gc, area);
	}

	protected void drawTextLayer(GC gc, Rectangle area) {
		int x = innerPadding;
		if (image != null) {
			x += imageTextPadding + image.getBounds().width;
			gc.drawImage(image, 5, (area.height - image.getBounds().height) / 2);
		}
		Point textArea = gc.textExtent(text);
		gc.drawText(text, x, area.height / 2 - textArea.y / 2, true);
	}

	protected void drawAnimationLayer(GC gc, Rectangle area) {
		if (clickBackgroundAnimation != null && !clickBackgroundAnimation.animationFinished()) {
			clickBackgroundAnimation.draw(gc, area);
		} else {
			gc.setBackground(background);
			gc.fillRectangle(area);
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		hoverBackgroundDefault.dispose();
		animationBackgroundDefault.dispose();
		clickBackgroundAnimation.dispose();
	}


	@Override
	public void setBackground(Color color) {
		super.setBackground(color);
		background = color;
	}

	public void setHoverBackground(Color hoverBackground) {
		this.hoverBackground = hoverBackground;
	}

	public void setAnimationBackground(Color animationBackground) {
		this.animationBackground = animationBackground;
	}

	protected Color getAnimationBackground() {
		return animationBackground == null ? animationBackgroundDefault : animationBackground;
	}

	protected Color getHoverBackground() {
		return hoverBackground == null ? hoverBackgroundDefault : hoverBackground;
	}

	private class ClickBackgroundAnimation implements Runnable {
		static final int ALPHA_MAX = 255;
		static final int TIMER_INTERVALL = 35;
		static final int RADIUS_INCREMENT = 10;
		static final int ALPHA_DECREMENT = 10;
		
		private int clickXPos;
		private int clickYPos;
		private int radius = 0;
		private GC animationGc;
		private int alpha = ALPHA_MAX;
		private int alphaStep = ALPHA_DECREMENT;
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
			if (animationGc != null)
				animationGc.dispose();
			animationGc = new GC(image);
		}

		void draw(GC gc, Rectangle area) {
			animationGc.setAlpha(ALPHA_MAX);
			animationGc.setBackground(background);
			animationGc.fillRectangle(area);
			animationGc.setAlpha(alpha);
			int x = clickXPos - radius;
			int y = clickYPos - radius;

			animationGc.setBackground(getAnimationBackground());
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
			radius += RADIUS_INCREMENT;
			if (!MenuItem.this.isDisposed())
				MenuItem.this.redraw();

			Rectangle area = getClientArea();
			calculateAlphaReductionPerStep(area);
			if (farSideReached(clickXPos, radius, area.width) && farSideReached(clickYPos, radius, area.height)) {
				animationFinished = true;
				return false;
			}

			alpha -= alphaStep;
			if (alpha < 1)
				alpha = 1;
			return true;
		}

		void calculateAlphaReductionPerStep(Rectangle area) {
			alphaStep = (clickXPos < area.width / 2) ? ALPHA_MAX / ((area.width - clickXPos) / RADIUS_INCREMENT)
					: ALPHA_MAX / (clickXPos / RADIUS_INCREMENT);
		}

		boolean farSideReached(int location, int radius, int dimension) {
			if (location < dimension / 2) {
				return location + radius > dimension;
			} else {
				return (location - radius) < 0;
			}
		}

		public void dispose() {
			if (image != null)
				image.dispose();
			if (animationGc != null)
				animationGc.dispose();
		}
	}

}
