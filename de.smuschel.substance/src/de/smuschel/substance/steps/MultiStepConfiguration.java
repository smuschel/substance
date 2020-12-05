package de.smuschel.substance.steps;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;

public class MultiStepConfiguration {

	private Color activeColor;
	private Color inactiveColor;
	private Color activeTextColor;
	private Color inactiveTextColor;
	private Color activeDescriptionColor;
	private Color inactiveDescriptionColor;
	private Font activeFont;
	private Font inactiveFont;
	private Font activeDescriptionFont;
	private Font inactiveDescriptionFont;
	private int orientation;
	private Composite parent;

	protected MultiStepConfiguration() {

	}

	public static MultiStepConfiguration create() {
		return new MultiStepConfiguration();
	}

	public MultiStepConfiguration activeColor(Color activeColor) {
		this.activeColor = activeColor;
		return this;
	}

	public MultiStepConfiguration inactiveColor(Color inactiveColor) {
		this.inactiveColor = inactiveColor;
		return this;
	}

	public MultiStepConfiguration activeTextColor(Color activeTextColor) {
		this.activeTextColor = activeTextColor;
		return this;
	}

	public MultiStepConfiguration inactiveTextColor(Color inactiveTextColor) {
		this.inactiveTextColor = inactiveTextColor;
		return this;
	}

	public MultiStepConfiguration activeDescriptionColor(Color activeDescriptionColor) {
		this.activeDescriptionColor = activeDescriptionColor;
		return this;
	}

	public MultiStepConfiguration inactiveDescriptionColor(Color inactiveDescriptionColor) {
		this.inactiveDescriptionColor = inactiveDescriptionColor;
		return this;
	}

	public MultiStepConfiguration orientation(int orientation) {
		this.orientation = orientation;
		return this;
	}

	public MultiStepConfiguration parent(Composite parent) {
		this.parent = parent;
		return this;
	}

	Font getActiveFont() {
		return activeFont;
	}

	public MultiStepConfiguration activeFont(Font activeFont) {
		this.activeFont = activeFont;
		return this;
	}

	Font getInactiveFont() {
		return inactiveFont;
	}

	public MultiStepConfiguration inactiveFont(Font inactiveFont) {
		this.inactiveFont = inactiveFont;
		return this;
	}

	Font getActiveDescriptionFont() {
		return activeDescriptionFont;
	}

	public MultiStepConfiguration activeDescriptionFont(Font activeDescriptionFont) {
		this.activeDescriptionFont = activeDescriptionFont;
		return this;
	}

	Font getInactiveDescriptionFont() {
		return inactiveDescriptionFont;
	}

	public MultiStepConfiguration inactiveDescriptionFont(Font inactiveDescriptionFont) {
		this.inactiveDescriptionFont = inactiveDescriptionFont;
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

	int getOrientation() {
		return orientation;
	}

	Composite getParent() {
		return parent;
	}

}
