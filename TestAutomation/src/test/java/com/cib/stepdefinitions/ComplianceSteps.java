package com.cib.stepdefinitions;

import com.cib.framework.TestData;
import com.cib.helper.ComplianceHelper;

import io.cucumber.java8.En;

public class ComplianceSteps implements En {

	public ComplianceSteps() {
		TestData data = new TestData();
		ComplianceHelper complianceHelper = new ComplianceHelper();
//
//		Given("user launches and logs into compliance application using valid credentials for {string} ",
//				(String testCaseNumber) -> {
//					data.assignComplianceTestcaseNumber(testCaseNumber);
//					complianceHelper.login();
//				});

		Given("user launches and logs into compliance application using valid credentials for {string}",
				(String string) -> {
					data.assignComplianceTestcaseNumber(string);
					complianceHelper.login();
				});

		Given("user clicks on menu icon", () -> {
			complianceHelper.clicOnMenuIcon();
		});

		Given("user navigate to create and assign trip page", () -> {
			complianceHelper.switchToAssignTrip();
		});

		Given("user selects site", () -> {
			complianceHelper.selectSite();
		});

		Given("user creates single trip card", () -> {
			complianceHelper.createSingleTrip();
		});
		Given("user navigates to assign trip page", () -> {
			complianceHelper.navigateToAssignTrip();
		});

		/*
		 * Given("user find based on shift time and date", () -> {
		 * complianceHelper.searchTrips(); });
		 */

		Given("user find trip based on shift time and date", () -> {
			complianceHelper.searchTrips();
		});
		Given("user finalizes the trip in compliance", () -> {
			complianceHelper.finalizeTrips();
		});

		Given("user click on assign", () -> {
			complianceHelper.assignTrip();
		});

		Given("user acknowledges the confirmation message", () -> {
			complianceHelper.confirmation();
		});

		Given("user navigates to trip board", () -> {
			complianceHelper.navigateToTripsBoard();
		});

		When("user selects shift time and shift date", () -> {
			complianceHelper.selectShiftAndName();
		});
		Then("user should verify the trip status", () -> {
			complianceHelper.verifytripBoard();
		});

	}
}