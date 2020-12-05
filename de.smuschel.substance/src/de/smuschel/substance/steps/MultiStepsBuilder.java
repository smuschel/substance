package de.smuschel.substance.steps;

import org.eclipse.swt.SWT;

public class MultiStepsBuilder {

	private MultiStepConfiguration configuration;
	private MultiSteps multiSteps;

	protected MultiStepsBuilder(MultiStepConfiguration configuration) {
		this.configuration = configuration;
		multiSteps = new MultiSteps(configuration.getParent(), configuration.getOrientation());
	}

	public static MultiStepsBuilder withConfiguration(MultiStepConfiguration configuration) {
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

		step.setActiveFont(configuration.getActiveFont());
		step.setActiveDescriptionFont(configuration.getActiveDescriptionFont());
		step.setInactiveFont(configuration.getInactiveFont());
		step.setInactiveDescriptionFont(configuration.getInactiveDescriptionFont());
		step.setEnabled(false);
	}
}
