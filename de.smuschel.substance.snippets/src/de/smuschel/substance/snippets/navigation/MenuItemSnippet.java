package de.smuschel.substance.snippets.navigation;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.smuschel.substance.navigation.MenuItem;

public class MenuItemSnippet {

	public static void main(final String[] args) {
		final Display display = new Display();
		final Shell shell = new Shell(display);
		GridLayout gl = new GridLayout(1, false);
		gl.verticalSpacing = 0;
		shell.setLayout(gl);

		Color blue = new Color(display, 70, 130, 180);
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(display.getSystemFont()).setStyle(SWT.BOLD);
		Font boldFont = boldDescriptor.createFont(display);
		Image calendar = new Image(display, MenuItemSnippet.class.getResourceAsStream("calendar.png"));
		Image cart = new Image(display, MenuItemSnippet.class.getResourceAsStream("cart.png"));
		MenuItem item = new MenuItem(shell, SWT.NONE);
		item.setText("Menu Item 1");
		item.setImage(calendar);
		GridData gd = new GridData();
		gd.widthHint = 200;
		gd.heightHint = 40;
		item.setFont(boldFont);
		item.setLayoutData(gd);

		MenuItem item2 = new MenuItem(shell, SWT.NONE);
		item2.setText("Menu Item 2");
		item2.setImage(cart);
		item2.setBackground(display.getSystemColor(SWT.COLOR_RED));
		item2.setHoverBackground(new Color(display, 255, 100, 100));
		item2.setAnimationBackground(new Color(display, 255, 50, 50));
		item2.setForeground(display.getSystemColor(SWT.COLOR_WHITE));
		GridData gd2 = new GridData();
		gd2.widthHint = 250;
		gd2.heightHint = 40;
		item2.setFont(boldFont);
		item2.setLayoutData(gd2);

		MenuItem item3 = new MenuItem(shell, SWT.NONE);
		item3.setText("Menu Item 3");
		GridData gd3 = new GridData();
		gd3.widthHint = 200;
		gd3.heightHint = 40;
		item3.setFont(boldFont);
		item3.setLayoutData(gd3);

		MenuItem item4 = new MenuItem(shell, SWT.MIN);
		item4.setText("Menu Item 4");
		item4.setImage(calendar);
		GridData gd4 = new GridData();
		gd4.widthHint = 34;
		gd4.heightHint = 40;
		item4.setFont(boldFont);
		item4.setLayoutData(gd4);

		shell.setSize(700, 350);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		blue.dispose();
		boldFont.dispose();
		calendar.dispose();
		cart.dispose();
		display.dispose();
	}

}
