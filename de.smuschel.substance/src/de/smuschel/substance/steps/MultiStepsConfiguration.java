package de.smuschel.substance.steps;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;

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

	public static MultiStepsConfiguration create() {
		return new MultiStepsConfiguration();
	}

	public MultiStepsConfiguration activeColor(Color activeColor) {
		this.activeColor = activeColor;
		return this;
	}

	public MultiStepsConfiguration inactiveColor(Color inactiveColor) {
		this.inactiveColor = inactiveColor;
		return this;
	}

	public MultiStepsConfiguration activeTextColor(Color activeTextColor) {
		this.activeTextColor = activeTextColor;
		return this;
	}

	public MultiStepsConfiguration inactiveTextColor(Color inactiveTextColor) {
		this.inactiveTextColor = inactiveTextColor;
		return this;
	}

	public MultiStepsConfiguration activeDescriptionColor(Color activeDescriptionColor) {
		this.activeDescriptionColor = activeDescriptionColor;
		return this;
	}

	public MultiStepsConfiguration inactiveDescriptionColor(Color inactiveDescriptionColor) {
		this.inactiveDescriptionColor = inactiveDescriptionColor;
		return this;
	}

	public MultiStepsConfiguration lineColor(Color lineColor) {
		this.lineColor = lineColor;
		return this;
	}

	public MultiStepsConfiguration orientation(int orientation) {
		this.orientation = orientation;
		return this;
	}

	public MultiStepsConfiguration parent(Composite parent) {
		this.parent = parent;
		return this;
	}

	Font getActiveFont() {
		return activeFont;
	}

	public MultiStepsConfiguration activeFont(Font activeFont) {
		this.activeFont = activeFont;
		return this;
	}

	Font getInactiveFont() {
		return inactiveFont;
	}

	public MultiStepsConfiguration inactiveFont(Font inactiveFont) {
		this.inactiveFont = inactiveFont;
		return this;
	}

	Font getActiveDescriptionFont() {
		return activeDescriptionFont;
	}

	public MultiStepsConfiguration activeDescriptionFont(Font activeDescriptionFont) {
		this.activeDescriptionFont = activeDescriptionFont;
		return this;
	}

	Font getInactiveDescriptionFont() {
		return inactiveDescriptionFont;
	}

	public MultiStepsConfiguration inactiveDescriptionFont(Font inactiveDescriptionFont) {
		this.inactiveDescriptionFont = inactiveDescriptionFont;
		return this;
	}

	public MultiStepsConfiguration circleRadius(int circleRadius) {
		this.circleRadius = circleRadius;
		return this;
	}

	public MultiStepsConfiguration linePadding(int linePadding) {
		this.linePadding = linePadding;
		return this;
	}

	public MultiStepsConfiguration descriptionOffset(int descriptionOffset) {
		this.descriptionOffset = descriptionOffset;
		return this;
	}

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
