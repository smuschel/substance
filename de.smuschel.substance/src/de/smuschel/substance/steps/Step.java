package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

/**
 * A Step used in the MultiSteps widget
 *
 * @author Simon Muschel
 *
 */
public class Step extends Composite {

	// Radius of the circle (the active area of a Step)
	private static final int CIRCLE_RADIUS = 16;
	// Line padding - distance between end/beginning of line and circls
	private static final int LINE_PADDING = 4;
	// Offset of description text
	private static final int DESCRIPTION_OFFSET = 48;
	// Width of line connecting Steps
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

	/**
	 * Constructs a Step widget. The supported styles are the styles defined in
	 * class <code>MultiSteps</code>: - FIRST - LAST - HORIZONTAL - VERTICAL
	 *
	 * @param parent the parent element (should be a MultiSteps widget)
	 * @param style  Style constant
	 */
	public Step(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * Set the text painted inside the circle
	 *
	 * @param text The text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Get the text painted inside the circle. If Step is completed, returns a check
	 * mark (Unicode \u2713)
	 *
	 * @return Current text
	 */
	public String getText() {
		if (isCompleted())
			return "\u2713";
		return text;
	}

	/**
	 * Set the description text for this Step
	 *
	 * @param description Step description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get this Steps description
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the active color. Either returns the Color instance passed in via
	 * <code>setActiveColor</code> or, if that is <code>null</code> returns
	 * SWT.COLOR_BLUE
	 *
	 * @return SWT color object
	 */
	public Color getActiveColor() {
		return activeColor == null ? getDisplay().getSystemColor(SWT.COLOR_BLUE) : activeColor;
	}

	/**
	 * Set the active color
	 *
	 * @param activeColor SWT color object
	 */
	public void setActiveColor(Color activeColor) {
		this.activeColor = activeColor;
	}

	/**
	 * Get the inactive color. Either returns the Color instance passed in via
	 * <code>setInactiveColor</code> or, if that is <code>null</code> returns
	 * SWT.COLOR_GRAY
	 *
	 * @return SWT color object
	 */
	public Color getInactiveColor() {
		return inactiveColor == null ? getDisplay().getSystemColor(SWT.COLOR_GRAY) : inactiveColor;
	}

	/**
	 * Set the inactive color
	 *
	 * @param inactiveColor SWT color object
	 */
	public void setInactiveColor(Color inactiveColor) {
		this.inactiveColor = inactiveColor;
	}

	/**
	 * Get the active text color. Either returns the Color instance passed in via
	 * <code>setActiveTextColor</code> or, if that is <code>null</code> returns
	 * SWT.COLOR_WHITE
	 *
	 * @return SWT color object
	 */
	public Color getActiveTextColor() {
		return activeTextColor == null ? getDisplay().getSystemColor(SWT.COLOR_WHITE) : activeTextColor;
	}

	/**
	 * Set the active text color
	 *
	 * @param activeTextColor SWT color object
	 */
	public void setActiveTextColor(Color activeTextColor) {
		this.activeTextColor = activeTextColor;
	}

	/**
	 * Get the active description color. Either returns the Color instance passed in
	 * via <code>setActiveDescriptionColor</code> or, if that is <code>null</code>
	 * returns SWT.COLOR_BLACK
	 *
	 * @return SWT color object
	 */
	public Color getActiveDescriptionColor() {
		return activeDescriptionColor == null ? getDisplay().getSystemColor(SWT.COLOR_BLACK) : activeDescriptionColor;
	}

	/**
	 * Set the active description color
	 *
	 * @param activeDescriptionColor SWT color object
	 */
	public void setActiveDescriptionColor(Color activeDescriptionColor) {
		this.activeDescriptionColor = activeDescriptionColor;
	}

	/**
	 * Get the inactive text color. Either returns the Color instance passed in via
	 * <code>setInctiveTextColor</code> or, if that is <code>null</code> returns
	 * SWT.COLOR_BLACK
	 *
	 * @return SWT color object
	 */
	public Color getInactiveTextColor() {
		return inactiveTextColor == null ? getDisplay().getSystemColor(SWT.COLOR_BLACK) : inactiveTextColor;
	}

	/**
	 * Set the inactive text color
	 *
	 * @param inactiveTextColor SWT color object
	 */
	public void setInactiveTextColor(Color inactiveTextColor) {
		this.inactiveTextColor = inactiveTextColor;
	}

	/**
	 * Get the inactive description color. Either returns the Color instance passed
	 * in via <code>setInctiveDescriptionColor</code> or, if that is
	 * <code>null</code> returns SWT.COLOR_GRAY
	 *
	 * @return SWT color object
	 */
	public Color getInactiveDescriptionColor() {
		return inactiveDescriptionColor == null ? getDisplay().getSystemColor(SWT.COLOR_GRAY)
				: inactiveDescriptionColor;
	}

	/**
	 * Set the color used to paint the line connecting two Steps.
	 *
	 * @param lineColor SWT color object
	 */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * Get the line color. Either returns the Color instance passed in via
	 * <code>setLineColor</code> or, if that is <code>null</code> returns
	 * SWT.COLOR_GRAY
	 *
	 * @return SWT color object
	 */
	public Color getLineColor() {
		return lineColor == null ? getDisplay().getSystemColor(SWT.COLOR_GRAY) : lineColor;
	}

	/**
	 * Get the font used to paint the text inside the circle
	 *
	 * @return SWT font object
	 */
	public Font getActiveFont() {
		return activeFont;
	}

	/**
	 * Set the font used to paint the text inside the circle
	 *
	 * @param activeFont SWT font object
	 */
	public void setActiveFont(Font activeFont) {
		this.activeFont = activeFont;
	}

	/**
	 * Get the font used to paint the description text
	 *
	 * @return SWT font object
	 */
	public Font getActiveDescriptionFont() {
		return activeDescriptionFont;
	}

	/**
	 * Set the font used to paint the description text
	 *
	 * @param activeDescriptionFont SWT font object
	 */
	public void setActiveDescriptionFont(Font activeDescriptionFont) {
		this.activeDescriptionFont = activeDescriptionFont;
	}

	/**
	 * Get the font used to paint the text inside the circle for inactive Steps
	 *
	 * @return SWT font object
	 */
	public Font getInactiveFont() {
		return inactiveFont;
	}

	/**
	 * Set the font used to paint the text inside the circle for inactive Steps
	 *
	 * @param inactiveFont SWT font object
	 */
	public void setInactiveFont(Font inactiveFont) {
		this.inactiveFont = inactiveFont;
	}

	/**
	 * Get the font used to paint the description text for inactive Steps
	 *
	 * @return SWT font object
	 */
	public Font getInactiveDescriptionFont() {
		return inactiveDescriptionFont;
	}

	/**
	 * Set the font used to paint the description text for inactive Steps
	 *
	 * @param inactiveDescriptionFont SWT font object
	 */
	public void setInactiveDescriptionFont(Font inactiveDescriptionFont) {
		this.inactiveDescriptionFont = inactiveDescriptionFont;
	}

	/**
	 * Set the color used to paint the description text of inactive Steps
	 *
	 * @param inactiveDescriptionColor SWT color object
	 */
	public void setInactiveDescriptionColor(Color inactiveDescriptionColor) {
		this.inactiveDescriptionColor = inactiveDescriptionColor;
	}

	/**
	 * Set the circle's radius
	 *
	 * @param radius radius in pixels
	 */
	public void setCircleRadius(int radius) {
		this.circleRadius = radius;
	}

	/**
	 * Set the description text's offset. Recommendation: 3 * circle radius
	 *
	 * @param offset offset in pixels
	 */
	public void setDescriptionOffset(int offset) {
		this.descriptionOffset = offset;
	}

	/**
	 * Line padding - the distance between beginning/end of line and circle
	 *
	 * @param padding padding in pixels
	 */
	public void setLinePadding(int padding) {
		this.linePadding = padding;
	}

	/**
	 * Width of the line connecting Steps
	 *
	 * @param width width in pixels
	 */
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
				? new Rectangle(area.x, area.y + area.height / 2 - getCircleRadius(), 2 * getCircleRadius(),
						2 * getCircleRadius())
				: new Rectangle(area.x + area.width / 2 - getCircleRadius(), area.y, 2 * getCircleRadius(),
						2 * getCircleRadius());
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
				gc.drawLine(getCircleRadius(), area.y + area.height / 2 + getCircleRadius() + getLinePadding(),
						getCircleRadius(), area.y + area.height);
			else
				gc.drawLine(area.x + area.width / 2 + getCircleRadius() + getLinePadding(), getCircleRadius(),
						area.x + area.width, getCircleRadius());
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
			gc.fillOval(area.x, area.y + area.height / 2 - getCircleRadius(), 2 * getCircleRadius(),
					2 * getCircleRadius());
		else
			gc.fillOval(area.x + area.width / 2 - getCircleRadius(), area.y, 2 * getCircleRadius(),
					2 * getCircleRadius());
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
				gc.drawText(description, area.x + area.width / 2 - textExtent.x / 2, area.y + getDescriptionOffset(),
						true);
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

	/**
	 * Mark this Step as completed
	 *
	 * @param completed true, if Step is complete; false otherwise
	 */
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
