package com.cib.helper;

import com.cib.pages.AlytePage;

public class AlyteTestHelper {

	public void selectSite() {
		new AlytePage().selectsite();
	}

	public void selectsiteOPS() {
		new AlytePage().selectsiteOPS();
	}

	public void validateSelectedSite() {
		new AlytePage().validatSelectedSite();
	}

	public void clickOnSingleTrip() {
		new AlytePage().clickOnSingleTrips();
	}

	public void NavigateUploadTrips() {
		new AlytePage().NavigateUploadTrips();
	}

	public void enterSingleTripDetails(String Vehicle_Type, String Ext_Trip_ID, String Date) {
		new AlytePage().enterSingleTripDetails(Vehicle_Type, Ext_Trip_ID, Date);
	}

	public void clickOnAddSingleTrip() {
		new AlytePage().clickOnAddSingleTrip();
	}

	public void validateTripeCreatedMessage() {
		new AlytePage().clickOnAddSingleTrip();
	}

	public void clickOnRouting() {
		new AlytePage().clickOnRouting();
	}

	public void clickOnTripBoard() {
		new AlytePage().clickOnTripBoard();
	}

	public void enterUserNameInLoginPage() {
		new AlytePage().enterUserNameInLoginPage();
	}

	public void enterPasswordInLoginPafe() {
		new AlytePage().enterPassWordInLoginPage();
	}

	public void clickonsignin() {
		new AlytePage().clickOnSignInPage();
	}

	public void enterUserNameInLoginPage(String Date) {
		new AlytePage().clickOnFind(Date);
	}

	public void validateTripId(String Ext_Ref_id) {
		new AlytePage().validateTripID(Ext_Ref_id);
	}

	public void clickVehcileType(String Ext_Trip_ID) {
		new AlytePage().clickOnVehcileType(Ext_Trip_ID);
	}

	public void selectVehicleType() {
		new AlytePage().selectVehicleType();
	}

	public void validateToastMessage() {
		new AlytePage().validateTheToastMessage();
	}

	public void validateVehicleWidget(String Ext_Trip_ID) {
		new AlytePage().validateVehicleWidget(Ext_Trip_ID);
	}

	public void clickOnVehicleAssignment(String plateNumber, String Ext_Trip_ID) {
		new AlytePage().clickOnVehicleAssignment(plateNumber, Ext_Trip_ID);
	}

	public void validateVehcileAssignment() {
		new AlytePage().validateVehcileAssignment();
	}

	public void validateTheToastMessageRouteFinalize() {
		new AlytePage().validateTheToastMessageRouteFinalize();
	}

	public void clickOnFinalize(String Ext_Trip_ID) {
		new AlytePage().clickOnFinalize(Ext_Trip_ID);
	}

	public void verifyTheDetails(String Ext_Trip_ID, String PlateNumber, String Date) {
		new AlytePage().verifyTheDetails(Ext_Trip_ID, PlateNumber, Date);
	}

	public void enterFromToDates(String FromDate, String ToDate) {
		new AlytePage().enterFromToDates(FromDate, ToDate);
	}

	public void clickOnUploadTrips() {
		new AlytePage().clickOnUploadTrips();
	}

	public void validateUploadMessage() {
		new AlytePage().validateUploadMessage();
	}

}
