package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class Step extends Composite {

	public static final int FIRST = 1;
	public static final int LAST = 2;

	private String text;
	private String description;
	private Color activeColor;
	private Color inactiveColor;
	private Color activeTextColor;
	private Color activeDescriptionColor;
	private Color inactiveTextColor;
	private Color inactiveDescriptionColor;

	public Step(Composite parent, int style) {
		super(parent, style);
		init();
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Color getActiveColor() {
		return activeColor == null ? getDisplay().getSystemColor(SWT.COLOR_BLUE) : activeColor;
	}

	public void setActiveColor(Color activeColor) {
		this.activeColor = activeColor;
	}

	public Color getInactiveColor() {
		return inactiveColor == null ? getDisplay().getSystemColor(SWT.COLOR_GRAY) : inactiveColor;
	}

	public void setInactiveColor(Color inactiveColor) {
		this.inactiveColor = inactiveColor;
	}

	public Color getActiveTextColor() {
		return activeTextColor == null ? getDisplay().getSystemColor(SWT.COLOR_WHITE) : activeTextColor;
	}

	public void setActiveTextColor(Color activeTextColor) {
		this.activeTextColor = activeTextColor;
	}

	public Color getActiveDescriptionColor() {
		return activeDescriptionColor == null ? getDisplay().getSystemColor(SWT.COLOR_BLACK) : activeDescriptionColor;
	}

	public void setActiveDescriptionColor(Color activeDescriptionColor) {
		this.activeDescriptionColor = activeDescriptionColor;
	}

	public Color getInactiveTextColor() {
		return inactiveTextColor == null ? getDisplay().getSystemColor(SWT.COLOR_BLACK) : inactiveTextColor;
	}

	public void setInactiveTextColor(Color inactiveTextColor) {
		this.inactiveTextColor = inactiveTextColor;
	}

	public Color getInactiveDescriptionColor() {
		return inactiveDescriptionColor == null ? getDisplay().getSystemColor(SWT.COLOR_GRAY) : inactiveDescriptionColor;
	}

	public void setInactiveDescriptionColor(Color inactiveDescriptionColor) {
		this.inactiveDescriptionColor = inactiveDescriptionColor;
	}

	protected void init() {
		addListener(SWT.Paint, e -> paint(e));
	}

	protected void paint(Event e) {
		GC gc = e.gc;
		setupGC(gc);
		final Rectangle area = getClientArea();
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_GRAY));
		drawStep(gc, area);
	}

	protected void drawStep(GC gc, final Rectangle area) {
		drawBeginningLine(gc, area);
		drawEndLine(gc, area);
		drawCircle(gc, area);
		drawContent(gc, area);
		drawDescription(gc, area);
	}

	protected void drawEndLine(GC gc, final Rectangle area) {
		if (!isLastStep()) {
			if (isVertical())
				gc.drawLine(area.width / 2, area.height / 2 + 20, area.width / 2, area.height);
			else
				gc.drawLine(area.width / 2 + 20, area.height / 2, area.width, area.height / 2);
		}
	}

	protected void drawBeginningLine(GC gc, final Rectangle area) {
		if (!isFirstStep()) {
			if (isVertical())
				gc.drawLine(area.width / 2, 0, area.width / 2, area.height / 2 - 20);
			else
				gc.drawLine(0, area.height / 2, area.width / 2 - 20, area.height / 2);
		}
	}

	protected void drawCircle(GC gc, Rectangle area) {
		if (isEnabled())
			gc.setBackground(getActiveColor());
		else
			gc.setBackground(getInactiveColor());
		gc.fillOval(area.width / 2 - 16, area.height / 2 - 16, 32, 32);
	}

	protected void drawContent(GC gc, Rectangle area) {
		if (isEnabled())
			gc.setForeground(getActiveTextColor());
		else
			gc.setForeground(getInactiveTextColor());
		Point extent = gc.stringExtent(getText());
		gc.drawText(getText(), area.width / 2 - extent.x / 2, area.height / 2 - extent.y / 2);
	}

	protected void drawDescription(GC gc, Rectangle area) {
		String description = getDescription();
		if (description != null && description.length() > 0) {
			if (isEnabled())
				gc.setForeground(getActiveDescriptionColor());
			else
				gc.setForeground(getInactiveDescriptionColor());
			Point textExtent = gc.textExtent(description);
			if (isVertical())
				gc.drawText(description, area.width / 2 + 48, area.height / 2 - textExtent.y / 2, true);
			else
				gc.drawText(description, area.width / 2 - textExtent.x / 2, area.height / 2 + 32, true);
		}
	}

	private boolean isVertical() {
		return (getStyle() & SWT.VERTICAL) == SWT.VERTICAL;
	}

	private boolean isLastStep() {
		return (getStyle() & LAST) == LAST;
	}

	private boolean isFirstStep() {
		return (getStyle() & FIRST) == FIRST;
	}

	protected void setupGC(GC gc) {
		gc.setFont(getFont());
		gc.setAdvanced(true);
		gc.setTextAntialias(SWT.ON);
		gc.setAntialias(SWT.ON);
	}

}
