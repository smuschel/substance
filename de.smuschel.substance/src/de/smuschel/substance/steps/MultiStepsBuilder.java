package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;

/** A Builder to construct <code>MultiSteps</code> widgets.
 *
 * @author Simon Muschel
 *
 */
public class MultiStepsBuilder {

	// Configuration model
	private MultiStepsConfiguration configuration;
	// the created and configured MultiSteps instance
	private MultiSteps multiSteps;

	protected MultiStepsBuilder(MultiStepsConfiguration configuration) {
		this.configuration = configuration;
		multiSteps = new MultiSteps(configuration.getParent(), configuration.getOrientation());
	}

	/** Add the configuration model for the MultiSteps widget. This method constructs a MultiStepsBuilder
	 * instance
	 *
	 * @param configuration configuration model instance
	 * @return the MultiStepsBuilder instance.
	 */
	public static MultiStepsBuilder withConfiguration(MultiStepsConfiguration configuration) {
		return new MultiStepsBuilder(configuration);
	}

	/** Creates and configures the first Step widget
	 *
	 * @param text text to be displayed inside the circle
	 * @param description description for this Step
	 * @return the MultiStepsBuilder instance
	 */
	public MultiStepsBuilder withFirstStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | MultiSteps.FIRST | SWT.NONE);
		step.setText(text);
		step.setDescription(description);
		configure(step);
		return this;
	}

	/** Creates and configures the last Step widget
	 *
	 * @param text text to be displayed inside the circle
	 * @param description description for this Step
	 * @return the MultiStepsBuilder instance
	 */
	public MultiStepsBuilder withLastStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | MultiSteps.LAST | SWT.NONE);
		step.setText(text);
		step.setDescription(description);
		configure(step);
		return this;
	}

	/** Creates and configures a Step widget
	 *
	 * @param text text to be displayed inside the circle
	 * @param description description for this Step
	 * @return
	 */
	public MultiStepsBuilder withStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | SWT.NONE);
		step.setText(text);
		step.setDescription(description);
		configure(step);
		return this;
	}

	/** Operation returns the MultiSteps widget
	 *
	 * @return the constructed and configured MultiSteps widget
	 */
	public MultiSteps build() {
		return multiSteps;
	}

	// Configure the given Step instance based on the configuration model
	private void configure(Step step) {
		step.setActiveColor(configuration.getActiveColor());
		step.setInactiveColor(configuration.getInactiveColor());
		step.setActiveTextColor(configuration.getActiveTextColor());
		step.setActiveDescriptionColor(configuration.getActiveDescriptionColor());
		step.setInactiveTextColor(configuration.getInactiveTextColor());
		step.setInactiveDescriptionColor(configuration.getInactiveDescriptionColor());
		step.setLineColor(configuration.getLineColor());

		step.setActiveFont(configuration.getActiveFont());
		step.setActiveDescriptionFont(configuration.getActiveDescriptionFont());
		step.setInactiveFont(configuration.getInactiveFont());
		step.setInactiveDescriptionFont(configuration.getInactiveDescriptionFont());

		step.setEnabled(false);

		step.setCircleRadius(configuration.getCircleRadius());
		step.setLinePadding(configuration.getLinePadding());
		step.setDescriptionOffset(configuration.getDescriptionOffset());
		step.setLineWidth(configuration.getLineWidth());
	}
}
