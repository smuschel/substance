package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class MultiSteps extends Composite {

	private int activeStep;

	public MultiSteps(Composite parent, int style) {
		super(parent, style);
	}

	public void createContent() {
		GridLayout gl = new GridLayout(isVertical() ? 1 : getChildren().length, true);
		gl.horizontalSpacing = 0;
		gl.verticalSpacing = 0;
		setLayout(gl);

		for (Control step : getChildren()) {
			GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
			step.setLayoutData(gd);
		}

	}

	protected boolean isVertical() {
		return (getStyle() & SWT.VERTICAL) == SWT.VERTICAL;
	}

	public void setActiveStep(int index) {
		for (Control c : getChildren()) {
			Step step = (Step) c;
			step.setEnabled(false);
		}
		((Step) getChildren()[index]).setEnabled(true);
		activeStep = index;
	}

	public void next() {
		if (getChildren().length > activeStep + 1)
			setActiveStep(++activeStep);
	}

	public void previous() {
		if (activeStep - 1 >= 0)
			setActiveStep(--activeStep);
	}

}
