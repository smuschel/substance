# Substance Widgets

## What is it?
Substance is (supposed to become) a collection of SWT widgets based on Material Design elements. 
Okay, right now it consists of only the MultiSteps Widget. Depending on me being more or less bored, additional widgets 
may be added in the future.

## Usage Example
```java
		MultiSteps ms = MultiStepsBuilder
				.withConfiguration(
						MultiStepConfiguration
							.create()
							.orientation(MultiSteps.HORIZONTAL)
							.parent(parent)
							)
				.withFirstStep("1", "Address \r\nDetails")
				.withStep("2", "Order Data")
				.withStep("3", "Payment Details")
				.withStep("4", "Shipment")
				.withLastStep("5", "Checkout")
				.build();
		ms.setActiveStep(0);
```
