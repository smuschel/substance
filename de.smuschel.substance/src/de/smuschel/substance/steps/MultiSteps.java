package de.smuschel.substance.steps;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
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
	private List<SelectionListener> selectionListeners;

	public MultiSteps(Composite parent, int style) {
		super(parent, style);
		selectionListeners = new ArrayList<>();
		init();
	}

	protected void init() {
		addListener(SWT.Paint, e -> paint(e));
		addListener(SWT.MouseUp, e -> click(e));
	}

	protected void click(Event e) {
		Point clickLocation = new Point(e.x, e.y);
		Step[] steps = getSteps();
		for (int i = 0; i < steps.length; i++) {
			Step step = steps[i];
			if (step.getActiveArea().contains(clickLocation)) {
				setActiveStep(i);
			}
		}
	}

	protected void paint(Event e) {
		GC gc = e.gc;
		Rectangle area = getClientArea();
		Step[] steps = getSteps();
		int offsetPerStep = isVertical() ? area.height / steps.length : area.width / steps.length;
		int offset = 0;
		for (Step step : steps) {
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
		checkWidget();
		Step old = getSteps()[activeStep];
		old.setEnabled(false);

		Step newStep = getSteps()[index];
		newStep.setEnabled(true);
		activeStep = index;

		fireSelectionChanged(newStep, activeStep);

		update();
		redraw();
	}

	public void next() {
		checkWidget();
		if (getSteps().length > activeStep + 1)
			setActiveStep(activeStep + 1);
	}

	public void previous() {
		checkWidget();
		if (activeStep - 1 >= 0)
			setActiveStep(activeStep - 1);
	}

	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		selectionListeners.add(listener);
	}

	public void removeSelectionListener(SelectionListener listener) {
		checkWidget();
		selectionListeners.remove(listener);
	}

	protected void fireSelectionChanged(Step step, int index) {
		Event e = new Event();
		e.item = step;
		e.data = index;
		e.widget = this;
		SelectionEvent event = new SelectionEvent(e);
		for (SelectionListener listener : selectionListeners) {
			listener.widgetSelected(event);
		}
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		int height = hHint;
		int width = wHint;
		for (Step step : getSteps()) {
			Point size = step.computeSize();
			if (isVertical()) {
				width = Math.max(width, size.x);
			} else {
				height = Math.max(height, size.y);
			}
		}
		return new Point(width, height);
	}

	public void completeCurrentStep() {
		checkWidget();
		Step current = getSteps()[activeStep];
		current.setCompleted(true);
		next();
		update();
		redraw();
	}

	private Step[] getSteps() {
		Control[] children = getChildren();
		List<Step> steps = new ArrayList<>();
		for (Control c : children) {
			if (c instanceof Step)
				steps.add((Step)c);
		}
		return steps.toArray(new Step[] {});
	}

}
