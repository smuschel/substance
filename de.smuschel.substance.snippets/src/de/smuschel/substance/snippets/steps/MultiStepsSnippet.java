package de.smuschel.substance.snippets.steps;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.smuschel.substance.steps.MultiStepsConfiguration;
import de.smuschel.substance.steps.MultiSteps;
import de.smuschel.substance.steps.MultiStepsBuilder;

public class MultiStepsSnippet {

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));

		Color blue = new Color(display, 70, 130, 180);
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(display.getSystemFont()).setStyle(SWT.BOLD);
		Font boldFont = boldDescriptor.createFont(display);
		MultiSteps ms = MultiStepsBuilder
				.withConfiguration(
						MultiStepsConfiguration.create()
							.orientation(MultiSteps.HORIZONTAL)
							.parent(shell)
							.activeColor(blue)
							.activeDescriptionFont(boldFont)
							.lineWidth(2)
							.linePadding(8)
							.lineColor(blue)
						)
				.withFirstStep("1", "Address \r\nDetails")
				.withStep("2", "Order Data")
				.withStep("3", "Payment Details")
				.withStep("4", "Shipment")
				.withLastStep("5", "Checkout")
				.build();
		ms.setActiveStep(0);
		GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		ms.setLayoutData(gd);

		Composite content = new Composite(shell, SWT.BORDER);
		content.setBackground(display.getSystemColor(SWT.COLOR_GRAY));
		GridData contentGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		content.setLayoutData(contentGridData);
		content.setLayout(new FillLayout());

		final Label label = new Label(content, SWT.NONE);
		label.setFont(boldFont);

		Composite buttonBar = new Composite(shell, SWT.NONE);
		buttonBar.setLayout(new RowLayout());
		Button prev = new Button(buttonBar, SWT.PUSH);
		prev.setText("<<");
		prev.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ms.previous();
			}

		});

		Button done = new Button(buttonBar, SWT.PUSH);
		done.setText("Done");
		done.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ms.completeCurrentStep();
			}

		});

		Button next = new Button(buttonBar, SWT.PUSH);
		next.setText(">>");
		next.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ms.next();
			}

		});

		ms.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				label.setText("Selected Step changed: index = " + e.data);
			}
		});

		shell.setSize(700, 350);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		blue.dispose();
		boldFont.dispose();
		display.dispose();
	}

}
