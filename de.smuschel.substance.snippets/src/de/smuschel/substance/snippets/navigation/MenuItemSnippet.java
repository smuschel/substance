package de.smuschel.substance.snippets.navigation;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.smuschel.substance.navigation.MenuItem;

public class MenuItemSnippet {

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));

		Color blue = new Color(display, 70, 130, 180);
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(display.getSystemFont()).setStyle(SWT.BOLD);
		Font boldFont = boldDescriptor.createFont(display);

		MenuItem item = new MenuItem(shell, SWT.BORDER);
		GridData gd = new GridData();
		gd.widthHint = 200;
		gd.heightHint = 50;
		item.setFont(boldFont);
		item.setLayoutData(gd);

		MenuItem item2 = new MenuItem(shell, SWT.BORDER);
		GridData gd2 = new GridData();
		gd2.widthHint = 400;
		gd2.heightHint = 50;
		item2.setFont(boldFont);
		item2.setLayoutData(gd2);

		MenuItem item3 = new MenuItem(shell, SWT.BORDER);
		GridData gd3 = new GridData();
		gd3.widthHint = 600;
		gd3.heightHint = 50;
		item3.setFont(boldFont);
		item3.setLayoutData(gd3);

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
