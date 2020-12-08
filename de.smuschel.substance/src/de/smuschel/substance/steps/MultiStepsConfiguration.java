package de.smuschel.substance.steps;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;

/**
 * This class implements the configuration model of the MultiStep widget. All
 * Step instances share the same configuration.
 *
 * @author Simon Muschel
 *
 */
public class MultiStepsConfiguration {

	private Color activeColor;
	private Color inactiveColor;
	private Color activeTextColor;
	private Color inactiveTextColor;
	private Color activeDescriptionColor;
	private Color inactiveDescriptionColor;
	private Color lineColor;
	private Font activeFont;
	private Font inactiveFont;
	private Font activeDescriptionFont;
	private Font inactiveDescriptionFont;
	private int orientation;
	private Composite parent;
	private int circleRadius = -1;
	private int linePadding = -1;
	private int descriptionOffset = -1;
	private int lineWidth = -1;

	protected MultiStepsConfiguration() {

	}

	/**
	 * Create an instance of the configuration
	 *
	 * @return an instance of this class
	 */
	public static MultiStepsConfiguration create() {
		return new MultiStepsConfiguration();
	}

	/**
	 * The color used to paint the circle background for active Steps
	 *
	 * @param activeColor SWT Color object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration activeColor(Color activeColor) {
		this.activeColor = activeColor;
		return this;
	}

	/**
	 * The color used to paint the circle background for inactive Steps
	 *
	 * @param inactiveColor SWT Color object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration inactiveColor(Color inactiveColor) {
		this.inactiveColor = inactiveColor;
		return this;
	}

	/**
	 * The color used to paint the text inside the circle for active Steps
	 *
	 * @param activeTextColor SWT Color object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration activeTextColor(Color activeTextColor) {
		this.activeTextColor = activeTextColor;
		return this;
	}

	/**
	 * The color used to paint the text inside the circle for inactive Steps
	 *
	 * @param inactiveTextColor SWT Color object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration inactiveTextColor(Color inactiveTextColor) {
		this.inactiveTextColor = inactiveTextColor;
		return this;
	}

	/**
	 * The color used to paint the description text for active Steps
	 *
	 * @param activeDescriptionColor SWT Color object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration activeDescriptionColor(Color activeDescriptionColor) {
		this.activeDescriptionColor = activeDescriptionColor;
		return this;
	}

	/**
	 * The color used to paint the description text for inactive Steps
	 *
	 * @param inactiveDescriptionColor SWT Color object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration inactiveDescriptionColor(Color inactiveDescriptionColor) {
		this.inactiveDescriptionColor = inactiveDescriptionColor;
		return this;
	}

	/**
	 * The color used to paint the line connecting two Steps
	 *
	 * @param lineColor SWT Color object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration lineColor(Color lineColor) {
		this.lineColor = lineColor;
		return this;
	}

	/**
	 * Orientation for the MultiStep widget. Possible values are either
	 * <code>MultiStep.HORIZONTAL</code> or <code>MultiSteps.VERTICAL</code>
	 *
	 * @param orientation the style constant for the widget's orientation
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration orientation(int orientation) {
		this.orientation = orientation;
		return this;
	}

	/**
	 * Set the parent Composite for the MultiSteps widget
	 *
	 * @param parent parent Composite
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration parent(Composite parent) {
		this.parent = parent;
		return this;
	}

	Font getActiveFont() {
		return activeFont;
	}

	/**
	 * Set the font used to paint the active Step text (inside the circle)
	 *
	 * @param activeFont SWT font object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration activeFont(Font activeFont) {
		this.activeFont = activeFont;
		return this;
	}

	Font getInactiveFont() {
		return inactiveFont;
	}

	/**
	 * Set the font used to paint the inactive Step text (inside the circle)
	 *
	 * @param inactiveFont SWT font object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration inactiveFont(Font inactiveFont) {
		this.inactiveFont = inactiveFont;
		return this;
	}

	Font getActiveDescriptionFont() {
		return activeDescriptionFont;
	}

	/**
	 * Set the font used to paint the active Step description text
	 *
	 * @param activeDescriptionFont SWT font object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration activeDescriptionFont(Font activeDescriptionFont) {
		this.activeDescriptionFont = activeDescriptionFont;
		return this;
	}

	Font getInactiveDescriptionFont() {
		return inactiveDescriptionFont;
	}

	/**
	 * Set the font used to paint the inactive Step description text
	 *
	 * @param inactiveDescriptionFont SWT font object
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration inactiveDescriptionFont(Font inactiveDescriptionFont) {
		this.inactiveDescriptionFont = inactiveDescriptionFont;
		return this;
	}

	/**
	 * Set the radius for the circle
	 *
	 * @param circleRadius radius in pixels
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration circleRadius(int circleRadius) {
		this.circleRadius = circleRadius;
		return this;
	}

	/**
	 * Set the padding for the line connecting Steps. The padding is the distance
	 * between the end of the line and the circle circumference.
	 *
	 * @param linePadding padding in pixels
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration linePadding(int linePadding) {
		this.linePadding = linePadding;
		return this;
	}

	/**
	 * Offset for the description text. Recommendation is to set this value to 3 *
	 * circleRadius.
	 *
	 * @param descriptionOffset offset in pixels
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration descriptionOffset(int descriptionOffset) {
		this.descriptionOffset = descriptionOffset;
		return this;
	}

	/**
	 * Width of the line that connects Steps
	 *
	 * @param lineWidth width in pixels
	 * @return the configuration instance
	 */
	public MultiStepsConfiguration lineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
		return this;
	}

	Color getActiveColor() {
		return activeColor;
	}

	Color getInactiveColor() {
		return inactiveColor;
	}

	Color getActiveTextColor() {
		return activeTextColor;
	}

	Color getInactiveTextColor() {
		return inactiveTextColor;
	}

	Color getActiveDescriptionColor() {
		return activeDescriptionColor;
	}

	Color getInactiveDescriptionColor() {
		return inactiveDescriptionColor;
	}

	Color getLineColor() {
		return lineColor;
	}

	int getOrientation() {
		return orientation;
	}

	Composite getParent() {
		return parent;
	}

	int getCircleRadius() {
		return circleRadius;
	}

	int getLinePadding() {
		return linePadding;
	}

	int getDescriptionOffset() {
		return descriptionOffset;
	}

	int getLineWidth() {
		return lineWidth;
	}
}
