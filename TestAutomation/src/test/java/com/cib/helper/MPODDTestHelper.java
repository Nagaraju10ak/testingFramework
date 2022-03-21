package com.cib.helper;

import com.cib.pages.MPODDPage;

public class MPODDTestHelper {

	public void enterTheDetails() {
		new MPODDPage().selectInvoice();
		new MPODDPage().enterTheDetails();
	}

	public void enterTheDetailsForA003(String A0003) {
		new MPODDPage().selectInvoice();
		new MPODDPage().enterTheDetailsForA0003(A0003);
	}

	public void validateLRMessage() {
		new MPODDPage().validateLRMessage();

	}

	public void clickOnInbound() {
		new MPODDPage().clickOnInbound();
	}

	public void clickOnInboundCreateLR() {
		new MPODDPage().clickOnInboundCreateLR();
	}

	public void clickOnInboundPrintLR() {
		new MPODDPage().clickOnInboundPrintLR();
	}

	public void clickOnInMaterialPlant() {
		new MPODDPage().clickOnInMaterialPlant();
	}

	public void signInIntoMPODDApp(String location) {
		new MPODDPage().signInIntoMPODDApp(location);
	}

	public void logout() {
		new MPODDPage().logout();
	}

	public void enterTheDetailsInward() {
		new MPODDPage().enterTheDetailsInward();
	}

	public void validateAlertMessage() {
		new MPODDPage().validateAlertMessage();
	}

	public void enterVehicleNumberAndSearchForA0003() {
		new MPODDPage().enterVehicleNumberAndSearchForA0003();
	}

	public void validateLRnumberForA0003() {
		new MPODDPage().validateLRnumberForA0003();
	}

	public void clickOnInwardward() {
		new MPODDPage().clickOnInwardward();
	}

	public void validateShippment() {
		new MPODDPage().validateShippment();
	}

	public void enterVehicleNumberAndSearch() {
		new MPODDPage().enterVehicleNumberAndSearch();
	}

	public void enterVehicleNumberAndSearchforA003() {
		new MPODDPage().enterVehicleNumberAndSearchForA0003();
	}

	public void clickShipmentButton() {
		new MPODDPage().clickShipmentButton();
	}

	public void validateLRnumber() {
		new MPODDPage().validateLRnumber();
	}

}
