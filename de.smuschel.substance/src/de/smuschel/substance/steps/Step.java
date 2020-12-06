package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

public class Step extends Composite {

	private static final int CIRCLE_RADIUS = 16;
	private static final int LINE_PADDING = 4;
	private static final int DESCRIPTION_OFFSET = 48;
	private static final int LINE_WIDTH = 1;

	private String text;
	private String description;
	private Color activeColor;
	private Color inactiveColor;
	private Color activeTextColor;
	private Color activeDescriptionColor;
	private Color inactiveTextColor;
	private Color inactiveDescriptionColor;
	private Color lineColor;
	private Font activeFont;
	private Font activeDescriptionFont;
	private Font inactiveFont;
	private Font inactiveDescriptionFont;
	private Rectangle area;
	private boolean completed;

	private int circleRadius;
	private int linePadding;
	private int descriptionOffset;
	private int lineWidth;

	public Step(Composite parent, int style) {
		super(parent, style);
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		if (isCompleted())
			return "\u2713";
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
		return inactiveDescriptionColor == null ? getDisplay().getSystemColor(SWT.COLOR_GRAY)
				: inactiveDescriptionColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Color getLineColor() {
		return lineColor == null ? getDisplay().getSystemColor(SWT.COLOR_GRAY) : lineColor;
	}

	public Font getActiveFont() {
		return activeFont;
	}

	public void setActiveFont(Font activeFont) {
		this.activeFont = activeFont;
	}

	public Font getActiveDescriptionFont() {
		return activeDescriptionFont;
	}

	public void setActiveDescriptionFont(Font activeDescriptionFont) {
		this.activeDescriptionFont = activeDescriptionFont;
	}

	public Font getInactiveFont() {
		return inactiveFont;
	}

	public void setInactiveFont(Font inactiveFont) {
		this.inactiveFont = inactiveFont;
	}

	public Font getInactiveDescriptionFont() {
		return inactiveDescriptionFont;
	}

	public void setInactiveDescriptionFont(Font inactiveDescriptionFont) {
		this.inactiveDescriptionFont = inactiveDescriptionFont;
	}

	public void setInactiveDescriptionColor(Color inactiveDescriptionColor) {
		this.inactiveDescriptionColor = inactiveDescriptionColor;
	}

	public void setCircleRadius(int radius) {
		this.circleRadius = radius;
	}

	public void setDescriptionOffset(int offset) {
		this.descriptionOffset = offset;
	}

	public void setLinePadding(int padding) {
		this.linePadding = padding;
	}

	public void setLineWidth(int width) {
		this.lineWidth = width;
	}

	protected Font getTextFont() {
		Font f = isEnabled() ? activeFont : inactiveFont;
		return f == null ? getFont() : f;
	}

	protected Font getDescriptionFont() {
		Font f = isEnabled() ? activeDescriptionFont : inactiveDescriptionFont;
		return f == null ? getFont() : f;
	}

	protected Color getBackgroundColor() {
		if (isEnabled() || isCompleted())
			return getActiveColor();
		return getInactiveColor();
	}

	protected Color getForegroundColor() {
		if (isEnabled() || isCompleted())
			return getActiveTextColor();
		return getInactiveTextColor();
	}

	protected Color getDescriptionForegroundColor() {
		if (isEnabled())
			return getActiveDescriptionColor();
		return getInactiveDescriptionColor();
	}

	protected int getCircleRadius() {
		return circleRadius == -1 ? CIRCLE_RADIUS : circleRadius;
	}

	protected int getDescriptionOffset() {
		return descriptionOffset == -1 ? DESCRIPTION_OFFSET : descriptionOffset;
	}

	protected int getLinePadding() {
		return linePadding == -1 ? LINE_PADDING : linePadding;
	}

	protected int getLineWidth() {
		return lineWidth == -1 ? LINE_WIDTH : lineWidth;
	}

	Rectangle getActiveArea() {
		Rectangle circleArea = isVertical()
				? new Rectangle(area.x, area.y + area.height / 2 - getCircleRadius(), 2 * getCircleRadius(), 2 * getCircleRadius())
				: new Rectangle(area.x + area.width / 2 - getCircleRadius(), area.y, 2 * getCircleRadius(), 2 * getCircleRadius());
		return circleArea;
	}

	protected void draw(GC gc, Rectangle area) {
		Color fg = gc.getForeground();
		Color bg = gc.getBackground();
		Font f = gc.getFont();
		int lw = gc.getLineWidth();

		this.area = area;
		setupGC(gc);
		gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_GRAY));
		drawStep(gc, area);

		gc.setLineWidth(lw);
		gc.setFont(f);
		gc.setForeground(fg);
		gc.setBackground(bg);
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
			int lw = gc.getLineWidth();
			gc.setForeground(getLineColor());
			gc.setLineWidth(getLineWidth());
			if (isVertical())
				gc.drawLine(getCircleRadius(), area.y + area.height / 2 + getCircleRadius() + getLinePadding(), getCircleRadius(),
						area.y + area.height);
			else
				gc.drawLine(area.x + area.width / 2 + getCircleRadius() + getLinePadding(), getCircleRadius(), area.x + area.width,
						getCircleRadius());
			gc.setLineWidth(lw);
		}
	}

	protected void drawBeginningLine(GC gc, final Rectangle area) {
		if (!isFirstStep()) {
			int lw = gc.getLineWidth();
			gc.setForeground(getLineColor());
			gc.setLineWidth(getLineWidth());
			if (isVertical())
				gc.drawLine(getCircleRadius(), area.y, getCircleRadius(),
						area.y + area.height / 2 - getCircleRadius() - getLinePadding());
			else
				gc.drawLine(area.x, getCircleRadius(), area.x + area.width / 2 - getCircleRadius() - getLinePadding(),
						getCircleRadius());
			gc.setLineWidth(lw);
		}
	}

	protected void drawCircle(GC gc, Rectangle area) {
		gc.setBackground(getBackgroundColor());
		if (isVertical())
			gc.fillOval(area.x, area.y + area.height / 2 - getCircleRadius(), 2 * getCircleRadius(), 2 * getCircleRadius());
		else
			gc.fillOval(area.x + area.width / 2 - getCircleRadius(), area.y, 2 * getCircleRadius(), 2 * getCircleRadius());
	}

	protected void drawContent(GC gc, Rectangle area) {
		gc.setForeground(getForegroundColor());
		gc.setFont(getTextFont());
		Point extent = gc.stringExtent(getText());
		if (isVertical())
			gc.drawText(getText(), area.x + getCircleRadius() - extent.x / 2, area.y + area.height / 2 - extent.y / 2);
		else
			gc.drawText(getText(), area.x + area.width / 2 - extent.x / 2, area.y + getCircleRadius() - extent.y / 2);
	}

	protected void drawDescription(GC gc, Rectangle area) {
		String description = getDescription();
		if (description != null && description.length() > 0) {
			gc.setForeground(getDescriptionForegroundColor());
			gc.setFont(getDescriptionFont());
			Point textExtent = gc.textExtent(description);
			if (isVertical())
				gc.drawText(description, area.x + getDescriptionOffset(), area.y + area.height / 2 - textExtent.y / 2,
						true);
			else
				gc.drawText(description, area.x + area.width / 2 - textExtent.x / 2, area.y + getDescriptionOffset(), true);
		}
	}

	private boolean isVertical() {
		return (getStyle() & MultiSteps.VERTICAL) == MultiSteps.VERTICAL;
	}

	private boolean isLastStep() {
		return (getStyle() & MultiSteps.LAST) == MultiSteps.LAST;
	}

	private boolean isFirstStep() {
		return (getStyle() & MultiSteps.FIRST) == MultiSteps.FIRST;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	private boolean isCompleted() {
		return completed;
	}

	protected void setupGC(GC gc) {
		gc.setFont(getFont());
		gc.setAdvanced(true);
		gc.setTextAntialias(SWT.ON);
		gc.setAntialias(SWT.ON);
	}

	Point computeSize() {
		final GC gc = new GC(getDisplay());
		Point textExtent = gc.textExtent(description);
		gc.dispose();
		if (isVertical())
			return new Point(textExtent.x + getDescriptionOffset(), -1);
		return new Point(-1, getDescriptionOffset() + textExtent.y);
	}

}
