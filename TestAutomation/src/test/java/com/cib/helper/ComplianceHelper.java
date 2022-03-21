package com.cib.helper;

import com.cib.pages.CompliancePage;

public class ComplianceHelper {

	public void login() throws InterruptedException {
		new CompliancePage().login();
	}

	public void clicOnMenuIcon() {
		new CompliancePage().clickOnMenu();
	}

	public void switchToAssignTrip() {
		new CompliancePage().navigateToCreateAndAssignTripPage();
	}

	public void selectSite() {
		new CompliancePage().selectSiteInRouting();
	}

	public void createSingleTrip() {
		new CompliancePage().creatSingleTripCard();
	}
	
	public void navigateToAssignTrip() {
		new CompliancePage().navigateToAssignTrip();
	}
	
	public void searchTrips() {
		new CompliancePage().findTrips();
	}
	
	public void assignTrip() {
		new CompliancePage().assignTrip();
	}
	
	public void finalizeTrips() {
		new CompliancePage().finalizeTrip();
	}
	
	public void confirmation() {
		new CompliancePage().confirMation();
	}
	
	public void navigateToTripsBoard() {
		new CompliancePage().switchToTripsBoard();
	}

	public void selectShiftAndName() {
		new CompliancePage().selectShiftAndName();
	}
	
	public void verifytripBoard() {
		new CompliancePage().verifyTripBoard();
	}
}
