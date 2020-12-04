package de.smuschel.substance.steps;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;

public class MultiStepConfiguration {

	private Color activeColor;
	private Color inactiveColor;
	private Color activeTextColor;
	private Color inactiveTextColor;
	private Color activeDescriptionColor;
	private Color inactiveDescriptionColor;
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
