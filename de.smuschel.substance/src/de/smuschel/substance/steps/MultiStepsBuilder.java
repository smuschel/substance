package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;

public class MultiStepsBuilder {

	private MultiStepsConfiguration configuration;
	private MultiSteps multiSteps;

	protected MultiStepsBuilder(MultiStepsConfiguration configuration) {
		this.configuration = configuration;
		multiSteps = new MultiSteps(configuration.getParent(), configuration.getOrientation());
	}

	public static MultiStepsBuilder withConfiguration(MultiStepsConfiguration configuration) {
		return new MultiStepsBuilder(configuration);
	}

	public MultiStepsBuilder withFirstStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | MultiSteps.FIRST | SWT.NONE);
		step.setText(text);
		step.setDescription(description);
		configure(step);
		return this;
	}

	public MultiStepsBuilder withLastStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | MultiSteps.LAST | SWT.NONE);
		step.setText(text);
		step.setDescription(description);
		configure(step);
		return this;
	}

	public MultiStepsBuilder withStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | SWT.NONE);
		step.setText(text);
		step.setDescription(description);
		configure(step);
		return this;
	}

	public MultiSteps build() {
		return multiSteps;
	}

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
