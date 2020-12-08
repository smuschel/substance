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

/**
 * A MultiSteps widget, inspired by Material UI Stepper widget and others
 * (https://material-ui.com/components/steppers/).
 *
 * Supports horizontal and vertical layout.
 *
 * @author Simon Muschel
 *
 */
public class MultiSteps extends Canvas {

	/** Style constant for horizontal layout */
	public static final int HORIZONTAL = 1;
	/** Style constant for vertical layout */
	public static final int VERTICAL = 2;
	/** Style constant to mark step as first step */
	public static final int FIRST = 4;
	/** Style constant to mark step as last step */
	public static final int LAST = 8;

	private int activeStep;
	private List<SelectionListener> selectionListeners;

	/**
	 * Constructs a new instance of this class given its parent and a style value
	 * describing its behavior and appearance.
	 * <p>
	 * The style value is one of the style constants defined in this class. The
	 * style constants <code>HORIZONTAL</code> and <code>VERTICAL</code> can be used
	 * with this class. Other constants from class <code>SWT</code> may also be
	 * used.
	 * </p>
	 *
	 * @param parent a composite control which will be the parent of the new
	 *               instance (cannot be null)
	 * @param style  the style of control to construct
	 */
	public MultiSteps(Composite parent, int style) {
		super(parent, style);
		selectionListeners = new ArrayList<>();
		init();
	}

	/*
	 * Register required event listeners
	 */
	protected void init() {
		addListener(SWT.Paint, e -> paint(e));
		addListener(SWT.MouseUp, e -> click(e));
	}

	/*
	 * Click handler. Called upon MouseUp Events
	 *
	 * This handler checks, wether the click event has occured inside the active
	 * area of a Step and if so, marks that Step as the active Step.
	 *
	 * The clickable area of a Step lies inside the circle area.
	 */
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

	/*
	 * Paints the widget. Painting of Steps is delegated to the Step class. Only the
	 * available client area for each Step is calculated here
	 */
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

	/**
	 * Activates to desired Step.
	 *
	 * Registered Listeners are notified of the changed active Step.
	 *
	 * @param index the 0-based index of the Step to be activated
	 * @throws ArrayIndexOutOfBoundsExcpetion
	 */
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

	/**
	 * Activate the next Step. No-Op if current Step is the last one.
	 *
	 */
	public void next() {
		checkWidget();
		if (getSteps().length > activeStep + 1)
			setActiveStep(activeStep + 1);
	}

	/**
	 * Activate the previous Step. No-Op if current Step is the first one.
	 *
	 */
	public void previous() {
		checkWidget();
		if (activeStep - 1 >= 0)
			setActiveStep(activeStep - 1);
	}

	/**
	 * Add a SelectionListener that gets notified, if the active Step changes.
	 *
	 * @param listener an instance of SelectionListener
	 */
	public void addSelectionListener(SelectionListener listener) {
		checkWidget();
		selectionListeners.add(listener);
	}

	/**
	 * Remove a registered SelectionListener from this widgets list of listeners.
	 *
	 * @param listener the listener instance to be removed from this widgets list
	 */
	public void removeSelectionListener(SelectionListener listener) {
		checkWidget();
		selectionListeners.remove(listener);
	}

	/*
	 * Fire s SelectionChange event - registered SelectionListeners are notified of
	 * the selection change.
	 */
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

	/**
	 * Compute the size required to paint this widget. Height is determined by the
	 * Step with the maximum height required.
	 *
	 */
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

	/**
	 * Mark the currently active/selected Step as complete. This Step will display a
	 * check mark.
	 *
	 */
	public void completeCurrentStep() {
		checkWidget();
		Step current = getSteps()[activeStep];
		current.setCompleted(true);
		next();
		update();
		redraw();
	}

	/*
	 * Get the list of Steps, which are a child of this widget.
	 */
	private Step[] getSteps() {
		Control[] children = getChildren();
		List<Step> steps = new ArrayList<>();
		for (Control c : children) {
			if (c instanceof Step)
				steps.add((Step) c);
		}
		return steps.toArray(new Step[] {});
	}

}
