package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

public class MultiSteps extends Canvas {

	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	public static final int FIRST = 4;
	public static final int LAST = 8;

	private int activeStep;

	public MultiSteps(Composite parent, int style) {
		super(parent, style);
		init();
	}

	protected void init() {
		addListener(SWT.Paint, e -> paint(e));
	}

	protected void paint(Event e) {
		GC gc = e.gc;
		Rectangle area = getClientArea();
		int offsetPerStep = isVertical() ? area.height / getChildren().length : area.width / getChildren().length;
		int offset = 0;
		for (Control c : getChildren()) {
			Step step = (Step) c;
			if (isVertical())
				step.draw(gc, new Rectangle(0, offset, area.width, offsetPerStep));
			else
				step.draw(gc, new Rectangle(offset, area.y, offsetPerStep, area.height));
			offset += offsetPerStep;
		}
	}

	protected boolean isVertical() {
		return (getStyle() & VERTICAL) == VERTICAL;
	}

	public void setActiveStep(int index) {
		Step old = (Step) getChildren()[activeStep];
		old.setEnabled(false);

		Step newStep = (Step) getChildren()[index];
		newStep.setEnabled(true);
		activeStep = index;

		update();
		redraw();
	}

	public void next() {
		if (getChildren().length > activeStep + 1)
			setActiveStep(activeStep + 1);
	}

	public void previous() {
		if (activeStep - 1 >= 0)
			setActiveStep(activeStep - 1);
	}

}
