package com.cib.pages;

import org.openqa.selenium.By;

import com.cib.framework.CommonDef;
import com.cib.framework.DriverFactory;
import com.cib.framework.ProjectConfig;
import com.cib.framework.TestReport;
import com.cib.objects.AlytePageObjects;

import com.relevantcodes.extentreports.LogStatus;

public class AlytePage {
	AlytePageObjects alytePageObjects = new AlytePageObjects(DriverFactory.getCurrentDriver());

	public void selectsite() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Select the site from drop-down-------------");
		try {

			CommonDef.dropdown(alytePageObjects.siteDrpDwn, "Synergy Communications - SC DCO Site");
			Thread.sleep(5000);
			TestReport.log(LogStatus.PASS, "Succesfully selected site");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to select site " + e.getMessage());
		}
	}

	public void selectsiteOPS() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Select the site from drop-down-------------");
		try {

			CommonDef.dropdown(alytePageObjects.siteDrpDwn, "Synergy Communications - SC OPS site");
			Thread.sleep(5000);
			TestReport.log(LogStatus.PASS, "Succesfully selected site");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to select site " + e.getMessage());
		}
	}

	public void validatSelectedSite() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ValidateSelectedSite-------------");
		try {
			TestReport.log(LogStatus.PASS, "Succesfully selected site");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to select site " + e.getMessage());
		}
	}

	public void clickOnSingleTrips() {
		TestReport.logNoScreen(LogStatus.INFO, "-----------Click on the Single Trip-------------");
		try {
			CommonDef.click(alytePageObjects.webTrips);
			CommonDef.click(alytePageObjects.webSingleTrip);
			TestReport.log(LogStatus.PASS, "User able to click on single trip");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to click on single trip " + e.getMessage());
		}
	}

	public void clickOnRouting() {
		TestReport.logNoScreen(LogStatus.INFO, "-----------Click on the Routing Trip-------------");
		try {
			CommonDef.click(alytePageObjects.webTrips);
			CommonDef.click(alytePageObjects.webRouting);
			TestReport.log(LogStatus.PASS, "User able to click on Routing trip");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to click on routing trip " + e.getMessage());
		}
	}

	public void NavigateUploadTrips() {
		TestReport.logNoScreen(LogStatus.INFO, "-----------Click on the Upload Trips-------------");
		try {
			// Thread.sleep(3000);
			CommonDef.click(alytePageObjects.webTrips);
			CommonDef.click(alytePageObjects.webUploadTrips);
			TestReport.log(LogStatus.PASS, "User able to click on Upload trip");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to click on Upload trip " + e.getMessage());
		}
	}

	public void enterSingleTripDetails(String Vehicle_Type, String Ext_Trip_ID, String Date) {
		TestReport.logNoScreen(LogStatus.INFO, "-----------Enter all single trip details-------------");
		try {
			CommonDef.dropdown(alytePageObjects.webTripType, "Login");
			CommonDef.dropdown(alytePageObjects.webShiftTime, "10:00");
			CommonDef.clearText(alytePageObjects.date);
			CommonDef.sendKeys(alytePageObjects.date, Date);
			CommonDef.dropdown(alytePageObjects.webGuard, "No");
			CommonDef.dropdown(alytePageObjects.webVehModel, Vehicle_Type);
			CommonDef.sendKeys(alytePageObjects.webExternalId, Ext_Trip_ID);
			CommonDef.sendKeys(alytePageObjects.webEmpCount, "3");
			CommonDef.sendKeys(alytePageObjects.webVehicleNum, "MH01ZA1234");
			TestReport.log(LogStatus.PASS, "User able to enter the details");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to enter details in single trip page" + e.getMessage());
		}
	}

	public void clickOnAddSingleTrip() {
		TestReport.logNoScreen(LogStatus.INFO, "-----------Click on the add Single Trip button-------------");
		try {
			Thread.sleep(3000);
			CommonDef.click(alytePageObjects.singleTripButton);

			TestReport.log(LogStatus.PASS, "User able to click on add single trip");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to click on add single trip " + e.getMessage());
		}
	}

	public void validateTripeCreatedMessage() {
		TestReport.logNoScreen(LogStatus.INFO,
				"-----------Validating the toast message post adding the signle trip-------------");
		try {
			String text = CommonDef.getText(alytePageObjects.webTripSucess);
			if (text.equals("Trip Created Successfully"))
				TestReport.log(LogStatus.INFO, "Toast message validation successful");
			else {
				throw new Exception("text is not equal");
			}
			TestReport.log(LogStatus.PASS, "Toast message validation successful");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Toast message validation Unsuccessful " + e.getMessage());
		}
	}

	public void clickOnTripBoard() {
		TestReport.logNoScreen(LogStatus.INFO, "-----------Click on Trip board-------------");
		try {
			CommonDef.click(alytePageObjects.webTrips);
			CommonDef.click(alytePageObjects.webTripBoard);

			TestReport.log(LogStatus.PASS, "User able to click on Trip board");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to click on the trip board " + e.getMessage());
		}
	}

	public void enterUserNameInLoginPage() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Enter UserName -------------");
		try {
			String userName = ProjectConfig.getPropertyValue("Alyteusername");
			CommonDef.sendKeys(alytePageObjects.userNameLogin, userName);
			TestReport.log(LogStatus.PASS, "Successfully verified search result");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to enter UserName " + e.getMessage());
		}
	}

	public void enterPassWordInLoginPage() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Enter Password -------------");
		try {
			String password = ProjectConfig.getPropertyValue("Alytepassword");
			CommonDef.sendKeys(alytePageObjects.passwordLogin, password);
			TestReport.log(LogStatus.PASS, "Successfully verified search result");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to enter password " + e.getMessage());
		}
	}

	public void clickOnSignInPage() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ClickOnSignIn -------------");
		try {
			CommonDef.click(alytePageObjects.signin);
			TestReport.log(LogStatus.PASS, "Successfully clicked on the button");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessfully clicked on signin " + e.getMessage());
		}

	}

	public void verifyTheDetails(String Ext_Trip_ID, String PlateNumber, String Date) {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Enter UserName -------------");
		try {

			String ext_id = "//strong[text()='" + Ext_Trip_ID + "']/parent::td/following-sibling::td[text()=' Login ']";
			String loginTime = "//strong[text()='" + Ext_Trip_ID
					+ "']/parent::td/following-sibling::td[text()=' 10:00 ']";
			String plateNum = "//strong[text()='" + Ext_Trip_ID + "']/parent::td/following-sibling::td/span";
			String status = "//strong[text()='" + Ext_Trip_ID
					+ "']/parent::td/following-sibling::td/span[text()='Pending Acceptance']";
			System.out.println(ext_id);

			CommonDef.clearTextAction(alytePageObjects.toDate);
			Thread.sleep(2000);
			CommonDef.sendKeys(alytePageObjects.toDate, Date);
			Thread.sleep(2000);
			CommonDef.click(alytePageObjects.clickOk);
			CommonDef.assertContains(By.xpath(ext_id), "Login");
			CommonDef.assertContains(By.xpath(loginTime), "10:00");
			CommonDef.assertContains(By.xpath(plateNum), PlateNumber);
			CommonDef.assertContains(By.xpath(status), "PENDING ACCEPTANCE");
			TestReport.log(LogStatus.PASS, "Successfully verified search result");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to verify search result " + e.getMessage());
		}
	}

	public void enterFromToDates(String FromDate, String ToDate) {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Select the from date and to date -------------");
		try {
			String webFromDate = "//div[contains(@aria-label,'" + FromDate + "')]";
			String webToDate = "//div[contains(@aria-label,'" + ToDate + "')]";
			String calender = "//div[@class='input-group-append']";
			String calender2 = "(//div[@class='input-group-append'])[2]";
			System.out.println(webFromDate);
			CommonDef.click(By.xpath(calender));
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webFromDate));
			CommonDef.click(By.xpath(calender2));
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webToDate));
			TestReport.log(LogStatus.PASS, "Successfully entered from date and to date");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to enter from and to dates " + e.getMessage());
		}
	}

	public void clickOnUploadTrips() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click on Upload Button -------------");
		try {

			Thread.sleep(3000);

			CommonDef.click(alytePageObjects.webUploadButton);
			CommonDef.click(alytePageObjects.webUploadOption);
			CommonDef.uploadFile("C:\\Users\\anusha\\Desktop\\Route Upload Sample with Issues.xlsx");
			Thread.sleep(5000);
			CommonDef.click(alytePageObjects.webDoneButton);
			TestReport.log(LogStatus.PASS, "Successfully clicked on Upload button");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to click on Upload button " + e.getMessage());
		}
	}

	public void validateUploadMessage() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click on Upload Button -------------");
		try {
			String text = CommonDef.getText(alytePageObjects.webUploadMessage);
			if (text.contains("Route(s) uploaded for Login"))
				TestReport.log(LogStatus.INFO, "Successfully clicked on Upload button");
			else {
				throw new Exception();
			}

			TestReport.log(LogStatus.PASS, "Successfully clicked on Upload button");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to click on Upload button " + e.getMessage());
		}
	}

	String Ext_Trip_ID;

	public void clickOnFind(String Date) {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------Click on the find button post entering the shift time and date -------------");
		try {

			Ext_Trip_ID = ProjectConfig.getPropertyValue("Ext_Trip_ID");
			String webValidateTripID = "//span[text()='" + Ext_Trip_ID + "']";
			System.out.println(webValidateTripID);
			Thread.sleep(5000);

			CommonDef.clearText(alytePageObjects.datePick);
			CommonDef.clearTextAction(alytePageObjects.datePick);
			Thread.sleep(3000);
			System.out.println(Date);
			CommonDef.sendKeys(alytePageObjects.datePick, Date);
			Thread.sleep(3000);
			CommonDef.click(alytePageObjects.shiftDrpDwn);
			Thread.sleep(3000);
			CommonDef.click(alytePageObjects.selectDrpDwn);
			CommonDef.click(alytePageObjects.webFind);

			TestReport.log(LogStatus.PASS, "Successfully clicked on the find button");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessfully clicked on find button " + e.getMessage());
		}

	}

	public void validateTripID(String Ext_Ref_id) {
		TestReport.logNoScreen(LogStatus.INFO, "------------validate the trip id created -------------");
		try {
			String webValidateTripID = "//span[text()='" + Ext_Ref_id + "']";
			System.out.println(webValidateTripID);
			System.out.println(alytePageObjects.webValidateTripID);
			String text = CommonDef.getText(By.xpath(webValidateTripID), webValidateTripID);
			System.out.println(Ext_Ref_id);
			if (text.equals(Ext_Ref_id))
				TestReport.logNoScreen(LogStatus.INFO, "------------validated the trip id -------------");

			else {
				throw new Exception("text is not equal");
			}
			TestReport.log(LogStatus.PASS, "Successfully validated the toast message with trip id");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "UnSuccessfully validated the toast message with trip id " + e.getMessage());
		}
	}

	public void clickOnVehcileType(String Ext_Trip_ID) {
		TestReport.logNoScreen(LogStatus.INFO, "------------Click on the vehcile Type in the widget -------------");
		try {
			String webValidateTripID = "//span[text()='" + Ext_Trip_ID
					+ "']/ancestor::div[1]/ancestor::div[1]/following-sibling::div/div/div/div/div/i";
			System.out.println(webValidateTripID);
			CommonDef.click(By.xpath(webValidateTripID));
			TestReport.log(LogStatus.PASS, "Successfully clicked on the vehcile Type in the widget");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL,
					"Unsuccessfully clicked on the vehcile Type in the widget " + e.getMessage());
		}

	}

	public void selectVehicleType() {
		TestReport.logNoScreen(LogStatus.INFO, "------------Select the vechcile type -------------");
		try {
			CommonDef.dropdown(alytePageObjects.webVehcileTypeSelector, "SEDAN");
			CommonDef.click(alytePageObjects.webClickOk);
			TestReport.log(LogStatus.PASS, "Successfully Selected the vechcile type");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessfully Selected the vechcile type " + e.getMessage());
		}

	}

	public void validateTheToastMessage() {
		TestReport.logNoScreen(LogStatus.INFO, "------------validate the toast message for assignment -------------");
		try {
			String text = CommonDef.getText(alytePageObjects.webSucess);
			if (text.equals("Vehicle type updated successfully"))
				TestReport.logNoScreen(LogStatus.INFO,
						"------------the toast message for assignment is validated -------------");
			else {
				throw new Exception("text is not equal");
			}
			TestReport.log(LogStatus.PASS, "Successfully validated the toast message");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessful the toast message for assignment" + e.getMessage());
		}

	}

	public void validateVehicleWidget(String Ext_Trip_ID) {
		TestReport.logNoScreen(LogStatus.INFO, "------------validate the widget for vehcile -------------");
		try {
			Thread.sleep(3000);
			String validateVehicle = "(//span[text()='" + Ext_Trip_ID
					+ "']/ancestor::div[1]/ancestor::div[1]/following-sibling::div/div/div/div/div/span)[2]";
			String text = CommonDef.getText(By.xpath(validateVehicle), validateVehicle);

			if (text.equals("SEDAN"))
				TestReport.logNoScreen(LogStatus.INFO,
						"------------the widget for vehcile type is successful -------------");
			else {
				throw new Exception("text is not equal");
			}
			TestReport.log(LogStatus.PASS, "Vehcile type is correct");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Vehcile type is in-correct " + e.getMessage());
		}

	}

	public void clickOnVehicleAssignment(String plateNumber, String Ext_Trip_ID) {
		TestReport.logNoScreen(LogStatus.INFO, "------------ClickOnSignIn -------------");
		try {
			Thread.sleep(3000);

			String webVehcileAssign = "//span[text()='" + Ext_Trip_ID
					+ "']/ancestor::div[1]/ancestor::div[1]/following-sibling::div/div/div/div/div/div//input";
			CommonDef.click(By.xpath(webVehcileAssign));

			CommonDef.sendKeys(alytePageObjects.webPlateNum, plateNumber);
			CommonDef.click(alytePageObjects.webClickDone);
			TestReport.log(LogStatus.PASS, "Successfully clicked on the button");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessfully clicked on signin " + e.getMessage());
		}

	}

	public void validateVehcileAssignment() {
		TestReport.logNoScreen(LogStatus.INFO, "------------validate the vehicle assignment -------------");
		try {
			String text = CommonDef.getText(alytePageObjects.webToastMessageAssig);
			System.out.println(text);
			if (text.contains("Vehicle Assigned Successfully"))
				TestReport.logNoScreen(LogStatus.INFO, "------------ClickOnSignIn -------------");
			else {
				throw new Exception("text is not equal");
			}
			TestReport.log(LogStatus.PASS, "Successfully assigned vehcile");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessfully assignmed vehcile " + e.getMessage());
		}

	}

	public void clickOnFinalize(String Ext_Trip_ID) {
		TestReport.logNoScreen(LogStatus.INFO, "------------Click on the finalize button -------------");
		try {
			Thread.sleep(3000);

			String webCheckbox = "//span[text()='" + Ext_Trip_ID + "']/preceding-sibling::input[1]";
			CommonDef.click(By.xpath(webCheckbox));

			CommonDef.click(alytePageObjects.webFinalize);

			TestReport.log(LogStatus.PASS, "Successfully clicked on the finalize button");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessfully clicked on finalized button " + e.getMessage());
		}

	}

	public void validateTheToastMessageRouteFinalize() {
		TestReport.logNoScreen(LogStatus.INFO, "------------Validating the toast message for finalize -------------");
		try {
			Thread.sleep(3000);
			String text = CommonDef.getText(alytePageObjects.webRouteFinalize);
			System.out.println(text);
			if (text.contains("Route(s) finalised for id"))
				TestReport.logNoScreen(LogStatus.INFO, "------------ClickOnSignIn -------------");
			else {
				throw new Exception("text is not equal");
			}
			TestReport.log(LogStatus.PASS, "Successful toast message for finalize");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unsuccessful toast message for finalize " + e.getMessage());
		}

	}
}
