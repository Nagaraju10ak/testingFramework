package com.cib.stepdefinitions;

import com.cib.framework.TestData;

import com.cib.helper.MPODDTestHelper;

import io.cucumber.java8.En;

public class MPODDTestSteps implements En {

	public MPODDTestSteps() {

		TestData data = new TestData();
		final MPODDTestHelper mPODDTestHelper = new MPODDTestHelper();

		Given("Login as CD01 and select location as {string} for {string}",
				(String location, String testCaseNumber) -> {

					data.assignTestcaseNumber(testCaseNumber);
					mPODDTestHelper.signInIntoMPODDApp(location);

				});

		When("Click on Inbound and click inward material with upload", () -> {

			mPODDTestHelper.clickOnInbound();

		});

		And("Material Details have been added Manually", () -> {

			mPODDTestHelper.enterTheDetailsInward();

		});

		Then("Record should be submitted successfully with proper pop up message", () -> {
			mPODDTestHelper.validateAlertMessage();

		});

		When("Click on Inbound and click Inbound create LR", () -> {

			mPODDTestHelper.clickOnInboundCreateLR();

		});

		And("Enter the details for create LR", () -> {

			mPODDTestHelper.enterTheDetails();

		});

		And("Enter the details for create LR with {string} as location", (String A0003) -> {

			mPODDTestHelper.enterTheDetailsForA003(A0003);

		});

		And("Record should be submitted successfully and LR no should be generated", () -> {

			mPODDTestHelper.validateLRMessage();

		});

		When("Click on Inbound and click Inbound Print LR", () -> {

			mPODDTestHelper.clickOnInboundPrintLR();

		});

		And("Click on Inbound and Inward Material at PlantCD", () -> {

			mPODDTestHelper.clickOnInMaterialPlant();

		});

		When("Login as {string}", (String location) -> {

			mPODDTestHelper.logout();
			mPODDTestHelper.signInIntoMPODDApp(location);

		});

		And("Click on Create Inward Entry option", () -> {

			mPODDTestHelper.clickOnInwardward();

		});

		And("Shipment is created for the LR created", () -> {

			mPODDTestHelper.validateShippment();

		});

		And("User will enter valid vehicle number in search box and Clicks on search button", () -> {

			mPODDTestHelper.enterVehicleNumberAndSearch();

		});

		And("User will enter valid vehicle number in search box and Clicks on search button for A0003", () -> {

			mPODDTestHelper.enterVehicleNumberAndSearchforA003();

		});

		And("Click on Create Shipment option", () -> {

			mPODDTestHelper.clickShipmentButton();

		});

		Then("LR related to entered vehical no should be displayed", () -> {

			mPODDTestHelper.validateLRnumber();

		});

		Then("LR related to entered vehical no should be displayed for A0003", () -> {

			mPODDTestHelper.validateLRnumberForA0003();

		});

	}

}
