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
		for (int i = 0; i < getChildren().length; i++) {
			Step step = (Step) getChildren()[i];
			if (step.getActiveArea().contains(clickLocation)) {
				setActiveStep(i);
			}
		}
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

		fireSelectionChanged(newStep, activeStep);

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

	public void addSelectionListener(SelectionListener listener) {
		selectionListeners.add(listener);
	}

	public void removeSelectionListener(SelectionListener listener) {
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
		for (Control c : getChildren()) {
			Step step = (Step) c;
			Point size = step.computeSize();
			if (isVertical()) {
				width = Math.max(width, size.x);
			} else {
				height = Math.max(height, size.y);
			}
		}
		return new Point(width, height);
	}

}
