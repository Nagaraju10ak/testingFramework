package com.cib.pages;

import org.openqa.selenium.By;

import com.cib.framework.CommonDef;
import com.cib.framework.DriverFactory;
import com.cib.framework.ProjectConfig;
import com.cib.framework.TestData;
import com.cib.framework.TestReport;
import com.cib.objects.MPODDObjects;
import com.relevantcodes.extentreports.LogStatus;

public class MPODDPage {

	MPODDObjects mPODDObjects = new MPODDObjects(DriverFactory.getCurrentDriver());

	public static String text;

	public void selectInvoice() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			
			Thread.sleep(3000);
			CommonDef.sendKeys(mPODDObjects.searchInvNum, TestData.get("InvNo"));
			CommonDef.sendKeysEnter();
			Thread.sleep(3000);
			String invoiceNumberValidate = CommonDef.getText(mPODDObjects.getInvoiceNumber);
			if(invoiceNumberValidate.equals(TestData.get("InvNo")))
			{
			CommonDef.click(mPODDObjects.selectInvoice);
			CommonDef.click(mPODDObjects.createLR);
			Thread.sleep(2000);
			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
			}
			else
			{
				throw new Exception();
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void validateLRMessage() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			Thread.sleep(3000);
			this.text = CommonDef.getText(mPODDObjects.LRsuccessMessage);
			System.out.println(text);
			if (text.contains("LR No : "))
				TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
			else
				throw new Exception();

			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void enterTheDetails() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			String vehicleType = TestData.get("VehicleType");
			String route = TestData.get("Route");
			String webvehicleType = "//div[text()='" + vehicleType + "']";
			String webroute = "//div[text()='" + route + "']";

			Thread.sleep(3000);
			CommonDef.dropdown(mPODDObjects.issueLocation, "CD02 - CHAKAN CROSS DOCK");
			CommonDef.dropdown(mPODDObjects.associateTransport, TestData.get("TransporterAssociateTransporter"));
			CommonDef.dropdown(mPODDObjects.serviceFreight, TestData.get("ServiceFreightType"));

			CommonDef.sendKeys(mPODDObjects.vehicleType, vehicleType.substring(0, 4));
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webvehicleType));
			System.out.println("test");
			CommonDef.sendKeys(mPODDObjects.route, route.substring(0, 5));
			CommonDef.Keys_BACKSPACE(mPODDObjects.route);
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webroute));

			CommonDef.sendKeys(mPODDObjects.vehicleNum, TestData.get("VehicleNo"));

			CommonDef.click(mPODDObjects.createLRbutton);
			Thread.sleep(5000);

			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void enterTheDetailsForA0003(String A0003) {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			String vehicleType = TestData.get("VehicleType");
			String route = TestData.get("Route");
			String webvehicleType = "//div[text()='" + vehicleType + "']";
			String webroute = "//div[text()='" + route + "']";

			Thread.sleep(3000);
			CommonDef.dropdown(mPODDObjects.issueLocation, A0003);
			CommonDef.dropdown(mPODDObjects.associateTransport, TestData.get("TransporterAssociateTransporter"));
			CommonDef.dropdown(mPODDObjects.serviceFreight, TestData.get("ServiceFreightType"));

			CommonDef.sendKeys(mPODDObjects.vehicleType, vehicleType.substring(0, 4));
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webvehicleType));
			System.out.println("test");

			CommonDef.sendKeys(mPODDObjects.route, route.substring(0, 5));
			CommonDef.Keys_BACKSPACE(mPODDObjects.route);
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webroute));

			CommonDef.sendKeys(mPODDObjects.vehicleNum, TestData.get("VehicleNoA003"));

			CommonDef.click(mPODDObjects.createLRbutton);
			Thread.sleep(5000);

			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void clickOnInbound() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			Thread.sleep(3000);
			CommonDef.moveToElement(By.xpath("//a[text()='Inbound']"));
			Thread.sleep(2000);
			CommonDef.click(By.xpath("//a[text()='Inward Material with Upload']"));
			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void clickOnInboundCreateLR() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			Thread.sleep(3000);
			CommonDef.moveToElement(By.xpath("//a[text()='Inbound']"));
			Thread.sleep(2000);
			CommonDef.click(By.xpath("//a[text()='Inbound  - Create LR']"));

			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void clickOnInboundPrintLR() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			Thread.sleep(3000);
			CommonDef.moveToElement(By.xpath("//a[text()='Inbound']"));

			Thread.sleep(2000);
			CommonDef.click(By.xpath("//a[text()='Inbound -  Print LR']"));

			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void clickOnInMaterialPlant() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Click On Inbound-------------");
		try {
			Thread.sleep(3000);
			CommonDef.moveToElement(By.xpath("//a[text()='Inbound']"));
			Thread.sleep(2000);
			CommonDef.click(By.xpath("//a[contains(text(),'Inward Material at Plant')]"));

			TestReport.log(LogStatus.PASS, "Successfully clicked on the Inbound material i");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable  clicked on the Inbound material  " + e.getMessage());
		}
	}

	public void signInIntoMPODDApp(String location) {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			String userName = ProjectConfig.getPropertyValue("Mpoddusername");
			String password = ProjectConfig.getPropertyValue("Mpoddpassword");
			CommonDef.sendKeys(mPODDObjects.userNameLogin, userName);
			CommonDef.sendKeys(mPODDObjects.passwordLogin, password);
			CommonDef.click(mPODDObjects.signin);
			Thread.sleep(2000);
			CommonDef.dropdown(mPODDObjects.area, location);
			CommonDef.click(mPODDObjects.clickCotinue);
			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}

	public void logout() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			CommonDef.click(mPODDObjects.logout);
			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}

	public void enterTheDetailsInward() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			// Thread.sleep(3000);
			String route = TestData.get("Route");
			String vehicleType = TestData.get("VehicleType");
			String supplierName = TestData.get("SupplierName");
			String productDesc = TestData.get("ProductDesc");
			String lrDate = TestData.get("LRDate");
			String invDate = TestData.get("InvDate");

			String webroute = "//div[text()='" + route + "']";
			String webvehicleType = "//div[text()='" + vehicleType + "']";
			String webSupplierName = "//div[text()='" + supplierName + "']";
			String webproductDesc = "//div[text()='" + productDesc + "']";
			String weblrDate = "//div[contains(@title,'" + lrDate + "')]";
			String webInvDate = "//div[contains(@title,'" + invDate + "')]";

			CommonDef.dropdown(mPODDObjects.associateTransporter, TestData.get("TransporterAssociateTransporter"));
			Thread.sleep(2000);
			CommonDef.dropdown(mPODDObjects.ServiceFreightType, TestData.get("ServiceFreightType"));

			System.out.println(TestData.get(webInvDate));
			CommonDef.sendKeys(mPODDObjects.Route, route.substring(0, 5));
			CommonDef.Keys_BACKSPACE(mPODDObjects.Route);
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webroute));

			System.out.println("test");
			System.out.println(vehicleType);

			CommonDef.sendKeys(mPODDObjects.vehicleType, vehicleType.substring(0, 4));
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webvehicleType));
			System.out.println("test");

			CommonDef.sendKeys(mPODDObjects.vehicleNum, TestData.get("VehicleNo"));
			Thread.sleep(2000);
			CommonDef.click(mPODDObjects.LRNo);
			CommonDef.sendKeys(mPODDObjects.LRNo, "123");
			System.out.println(TestData.get("LRNo"));

			Thread.sleep(2000);
			CommonDef.click(mPODDObjects.LRDate);

			CommonDef.sendKeys(mPODDObjects.LRDate, "2022-03");
			Thread.sleep(2000);
			CommonDef.waitClickable(By.xpath(weblrDate));
			CommonDef.click(By.xpath(weblrDate));
			Thread.sleep(2000);

			CommonDef.click(mPODDObjects.LRNo);
			CommonDef.sendKeys(mPODDObjects.LRNo, TestData.get("LRNo"));

			Thread.sleep(2000);
			CommonDef.sendKeys(mPODDObjects.supplierName, supplierName.substring(0, 5));
			Thread.sleep(2000);
			CommonDef.click(By.xpath(webSupplierName));
			CommonDef.sendKeys(mPODDObjects.productDesc, productDesc.substring(0, 5));
			CommonDef.Keys_BACKSPACE(mPODDObjects.productDesc);
			Thread.sleep(3000);
			CommonDef.sendKeys(mPODDObjects.productDesc, productDesc.substring(0, 5));
			CommonDef.click(By.xpath(webproductDesc));

			CommonDef.sendKeys(mPODDObjects.InvNo, TestData.get("InvNo"));
			Thread.sleep(2000);
			CommonDef.sendKeys(mPODDObjects.InvDate, "2022-03");
			Thread.sleep(2000);
			CommonDef.waitClickable(By.xpath(webInvDate));
			CommonDef.click(By.xpath(webInvDate));
			Thread.sleep(2000);
			CommonDef.sendKeys(mPODDObjects.InvValue, TestData.get("InvValue"));
			CommonDef.sendKeys(mPODDObjects.InvNo, TestData.get("InvNo"));
			Thread.sleep(2000);
			CommonDef.dropdown(mPODDObjects.DestinationPlant, TestData.get("DestinationPlant"));

			CommonDef.sendKeys(mPODDObjects.buyerRequestID, TestData.get("BuyerRequestID"));
			CommonDef.sendKeys(mPODDObjects.triggerQTY, TestData.get("TriggerQty"));
			CommonDef.sendKeys(mPODDObjects.units, TestData.get("Units"));
			CommonDef.sendKeys(mPODDObjects.noOfBoxes, TestData.get("NoOfBoxes"));
			CommonDef.sendKeys(mPODDObjects.typeOfPacking, TestData.get("TypeOfPacking"));
			CommonDef.sendKeys(mPODDObjects.incoTerm, TestData.get("IncoTerm"));
			CommonDef.sendKeys(mPODDObjects.ASNNo, TestData.get("ASNNo"));
			CommonDef.sendKeys(mPODDObjects.actualWt, TestData.get("ActualWt"));
			CommonDef.sendKeys(mPODDObjects.CFT, TestData.get("CFT"));
			CommonDef.sendKeys(mPODDObjects.CFTWt, TestData.get("CFTWt"));
			CommonDef.sendKeys(mPODDObjects.billableWt, TestData.get("BilliableWt"));

			CommonDef.click(mPODDObjects.addButton);
			Thread.sleep(2000);
			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
			CommonDef.click(mPODDObjects.submitButton);

		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}

	public void validateAlertMessage() {
		TestReport.logNoScreen(LogStatus.INFO, "------------ Enter Password -------------");
		try {
			Thread.sleep(3000);
			String alert_getText = CommonDef.alert_getText();
			System.out.println("abcccc" + alert_getText);
			CommonDef.alert_Accept();
			if (alert_getText.contains("Data saved successfully"))
				TestReport.logNoScreen(LogStatus.INFO, "------------ Enter Password -------------");
			else
				throw new Exception();
			TestReport.log(LogStatus.PASS, "Successfully verified search result");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable to enter password " + e.getMessage());
		}

	}

	public void clickOnInwardward() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			Thread.sleep(3000);
			CommonDef.click(mPODDObjects.selectAll);

			CommonDef.click(mPODDObjects.submitCreateShipment);
			Thread.sleep(2000);

			TestReport.log(LogStatus.INFO, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}

	public void validateShippment() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			Thread.sleep(1000);
			CommonDef.alert_Accept();
			Thread.sleep(2000);
			String alert_getText = CommonDef.alert_getText();
			System.out.println(alert_getText);
			if (alert_getText.contains("Invoice Numbers:  Inwarded Successfully"))
				TestReport.logNoScreen(LogStatus.INFO, "------------ Message is successful " + alert_getText);

			else
				throw new Exception();
			Thread.sleep(2000);

			CommonDef.alert_Accept();
			Thread.sleep(2000);
			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}

	public void enterVehicleNumberAndSearch() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			Thread.sleep(3000);

			CommonDef.sendKeys(mPODDObjects.vehicleNumber, TestData.get("VehicleNo"));
			CommonDef.click(mPODDObjects.searchButton);
			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into applicationaa " + e.getMessage());
		}
	}

	public void enterVehicleNumberAndSearchForA0003() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			Thread.sleep(3000);

			CommonDef.sendKeys(mPODDObjects.vehicleNumber, TestData.get("VehicleNoA003"));
			CommonDef.click(mPODDObjects.searchButton);
			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into applicationa " + e.getMessage());
		}
	}

	public void clickShipmentButton() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			Thread.sleep(3000);

			CommonDef.click(mPODDObjects.createShipment);
			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}

	public void validateLRnumber() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			System.out.println(text);
			String LRnumber = text.replaceAll("[^0-9]", "");
			System.out.println(LRnumber);
			String webLRNo = "//span[text()='" + LRnumber + "']";
			String LRnoText = CommonDef.getText(By.xpath(webLRNo), "");
			System.out.println(LRnoText);
			if (LRnoText.contains(webLRNo))
				TestReport.logNoScreen(LogStatus.INFO,
						"------------ Login into mPODD application using userName and Password -------------");
			else
				throw new Exception();

			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}

	public void validateLRnumberForA0003() {
		TestReport.logNoScreen(LogStatus.INFO,
				"------------ Login into mPODD application using userName and Password -------------");
		try {
			System.out.println(text);
			String LRnumber = text.replaceAll("[^0-9]", "");
			System.out.println(LRnumber);
			String webLRNo = "//span[text()='" + LRnumber + "']";
			String LRnoText = CommonDef.getText(By.xpath(webLRNo), "");
			System.out.println(LRnoText);
			if (LRnoText.contains(webLRNo))
				TestReport.logNoScreen(LogStatus.INFO,
						"------------ Login into mPODD application using userName and Password -------------");
			else
				throw new Exception();

			TestReport.log(LogStatus.PASS, "Successfully Logged into Application");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Unable login into application " + e.getMessage());
		}
	}
	
	
	
	

}