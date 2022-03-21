package com.cib.stepdefinitions;

import com.cib.framework.TestData;
import com.cib.helper.AlyteTestHelper;

import io.cucumber.java8.En;

public class AlyteTestSteps implements En {

	public AlyteTestSteps() {

		TestData data = new TestData();
		final AlyteTestHelper alyteTestHelper = new AlyteTestHelper();

		When("Select site from top right Site dropdown", () -> {

			alyteTestHelper.selectSite();

		});

		Given("Login into Alyte application with userName and passWord as {string}", (String testCaseNumber) -> {

			data.assignTestcaseNumber(testCaseNumber);
			alyteTestHelper.enterUserNameInLoginPage();
			alyteTestHelper.enterPasswordInLoginPafe();
			alyteTestHelper.clickonsignin();
		});

		When("Select site from top right Site dropdown OCTANCE OPS", () -> {

			alyteTestHelper.selectsiteOPS();

		});
		Then("Site should be selected and displayed in the field", () -> {

			alyteTestHelper.validateSelectedSite();

		});

		When("Navigate to Single Trips page", () -> {

			alyteTestHelper.clickOnSingleTrip();

		});

		When("Navigate to Upload Trips", () -> {

			alyteTestHelper.NavigateUploadTrips();

		});

		And("Enter single trip details with type as {string} with trip id as {string} Date as {string}",
				(String Vehicle_Type, String Ext_Trip_ID, String Date) -> {

					alyteTestHelper.enterSingleTripDetails(Vehicle_Type, Ext_Trip_ID, Date);

				});

		And("Click the ADD SINGLE TRIP button", () -> {

			alyteTestHelper.clickOnAddSingleTrip();

		});

		Then("Trip Created Successfully toast mesasge displayed to user", () -> {

			alyteTestHelper.validateTripeCreatedMessage();

		});

		When("Navigate to Routing page", () -> {

			alyteTestHelper.clickOnRouting();

		});

		When("Navigate to TripBoard", () -> {

			alyteTestHelper.clickOnTripBoard();

		});

		And("Select the details and click the Find button {string}", (String Date) -> {

			alyteTestHelper.enterUserNameInLoginPage(Date);

		});

		Then("Trip with ID mentioned should be present {string}", (String Ext_Ref_id) -> {

			alyteTestHelper.validateTripId(Ext_Ref_id);

		});

		When("Click the Vehicle Type icon edit icon for {string}", (String Ext_Trip_ID) -> {

			alyteTestHelper.clickVehcileType(Ext_Trip_ID);

		});

		And("Select Sedan from the dropdown and click the Done button", () -> {

			alyteTestHelper.selectVehicleType();

		});

		Then("Vehicle type updated Successfully toast mesasge displayed to user", () -> {

			alyteTestHelper.validateToastMessage();

		});

		And("SED should be diaplayed on trip widget for {string}", (String Ext_Trip_ID) -> {

			alyteTestHelper.validateVehicleWidget(Ext_Trip_ID);

		});

		When("Select required vehicle {string} for {string}", (String plateNumber, String Ext_Trip_ID) -> {

			alyteTestHelper.clickOnVehicleAssignment(plateNumber, Ext_Trip_ID);

		});

		Then("Vehicle Assigned Successfully toast mesasge displayed to user", () -> {

			alyteTestHelper.validateVehcileAssignment();

		});

		And("Vehicle number shown on widge", () -> {

			alyteTestHelper.validateVehcileAssignment();

		});

		When("Select the checkbox on top left of widget and click Finalize button for {string}",
				(String Ext_Trip_ID) -> {

					alyteTestHelper.clickOnFinalize(Ext_Trip_ID);

				});

		Then("Routes finalize for trip id trip_id toast message displayed to user", () -> {

			alyteTestHelper.validateTheToastMessageRouteFinalize();

		});

		Then("verify Shift type Plate no Currect status  details against our external ID {string} {string} and {string}",
				(String Ext_Trip_ID, String PlateNumber, String Date) -> {

					alyteTestHelper.verifyTheDetails(Ext_Trip_ID, PlateNumber, Date);

				});
		And("Select From and To date drom datepicker {string} {string}", (String FromDate, String ToDate) -> {

			alyteTestHelper.enterFromToDates(FromDate, ToDate);

		});

		And("Click on Upload Trips", () -> {

			alyteTestHelper.clickOnUploadTrips();

		});

		Then("Routes uploaded for Login with External Id toast message shown to user", () -> {

			alyteTestHelper.validateUploadMessage();

		});

	}
}
