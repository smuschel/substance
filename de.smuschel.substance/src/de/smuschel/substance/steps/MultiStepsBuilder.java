package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;

public class MultiStepsBuilder {

	private MultiStepConfiguration configuration;
	private MultiSteps multiSteps;

	protected MultiStepsBuilder(MultiStepConfiguration configuration) {
		this.configuration = configuration;
		multiSteps = new MultiSteps(configuration.getParent(), configuration.getOrientation() | SWT.NONE);
	}

	public static MultiStepsBuilder withConfiguration(MultiStepConfiguration configuration) {
		return new MultiStepsBuilder(configuration);
	}

	public MultiStepsBuilder withFirstStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | Step.FIRST | SWT.NONE);
		step.setText(text);
		step.setDescription(description);
		configure(step);
		return this;
	}

	public MultiStepsBuilder withLastStep(String text, String description) {
		Step step = new Step(multiSteps, configuration.getOrientation() | Step.LAST | SWT.NONE);
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
		multiSteps.createContent();
		return multiSteps;
	}

	private void configure(Step step) {
		step.setActiveColor(configuration.getActiveColor());
		step.setInactiveColor(step.getInactiveColor());
		step.setActiveTextColor(configuration.getActiveTextColor());
		step.setActiveDescriptionColor(configuration.getActiveDescriptionColor());
		step.setInactiveTextColor(configuration.getInactiveTextColor());
		step.setInactiveDescriptionColor(configuration.getInactiveDescriptionColor());
	}
}
