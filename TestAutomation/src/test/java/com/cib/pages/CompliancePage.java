package com.cib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.cib.framework.AppCommonDef;
import com.cib.framework.AppTestReport;
import com.cib.framework.CommonDef;
import com.cib.framework.DriverFactory;
import com.cib.framework.ProjectConfig;
import com.cib.framework.TestData;
import com.cib.framework.TestReport;
//import com.cib.objects.AlytePageObjects;
//import com.cib.objects.ComplianceAppObjects;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CompliancePage {
	AppiumDriver<MobileElement> appDriver;

	public String userName = "com.mahindra.vaayu.compliance:id/etMobileNumber";

	public String password = "com.mahindra.vaayu.compliance:id/etPassword";

	public String login = "com.mahindra.vaayu.compliance:id/etPassword";

	public String menuIcon = "//android.widget.ImageButton[@content-desc='Open navigation drawer']";

	public String createAndAssignTrip = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[2]/android.widget.CheckedTextView";

	public String selectSiteDropDown = "com.mahindra.vaayu.compliance:id/btnSelectSite";

	public String selectDocSite = "android:id/text1";

	public String createSingleTrip = "com.mahindra.vaayu.compliance:id/btnCreateSingleTrip";

	public String selectTripType = "com.mahindra.vaayu.compliance:id/btnSelectTripType";

	public String selectTripTypeLogin = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[1]";

	public String selectShiftTime = "com.mahindra.vaayu.compliance:id/btnSelectShiftTime";

	public String selectShiftTime8 = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[1]";

	public String externalId = "com.mahindra.vaayu.compliance:id/edtCustTripId";

	public String createSingleTripButton = "com.mahindra.vaayu.compliance:id/btnCreateTrip";

	public String assignTrip = "com.mahindra.vaayu.compliance:id/btnPerformRouting";

	public String dateInTripList = "com.mahindra.vaayu.compliance:id/btnDate";

	public String okInCalender = "android:id/button1";

	public String selectShiftInTripList = "com.mahindra.vaayu.compliance:id/btnSelectShift";

	public String selectShift8 = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[1]";

	public String findInTripsList = "com.mahindra.vaayu.compliance:id/btnFind";

	public String assign = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.TextView[2]";

	public String vechicleNumber = "com.mahindra.vaayu.compliance:id/vehicle_id";

	public String assignVechicle = "com.mahindra.vaayu.compliance:id/btnPair";

	public String finaize = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[5]/android.widget.TextView[2]";

	public String reason = "com.mahindra.vaayu.compliance:id/edtReason";

	public String yesInConfirmation = "com.mahindra.vaayu.compliance:id/btnYes";

	public String doneInConfirmation = "com.mahindra.vaayu.compliance:id/btnDone";

	public String tripBoards = "com.mahindra.vaayu.compliance:id/btnTripBoard";

	public String shiftDateInTripBoard = "com.mahindra.vaayu.compliance:id/btnShiftDate";

	public String shiftTypeInTripBoard = "com.mahindra.vaayu.compliance:id/btnShiftType";

	public String shiftTypeLoginInTripBoard = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView[1]";

	public String okInTripBoard = "com.mahindra.vaayu.compliance:id/btnOk";

	public String tripCardData = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout";

	public String vechicleNumberTripsBoardList = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView[2]";

	public String tripStatus = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[6]/android.widget.TextView[2]";

	public void login() throws InterruptedException {
		appDriver = DriverFactory.getCurrentAppDriver();
		System.out.println(appDriver);
		appDriver.findElement(By.id(userName)).sendKeys(ProjectConfig.getPropertyValue("complianceUsername"));
		appDriver.findElement(By.id(password)).sendKeys(ProjectConfig.getPropertyValue("compliancePassword"));
		appDriver.findElement(By.id(login)).click();
		AppTestReport.log(LogStatus.PASS, "Successfully loged in'");
	}

	public void clickOnMenu() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.xpath(login)).click();
	}

	public void navigateToCreateAndAssignTripPage() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.xpath(createAndAssignTrip)).click();

	}

	public void selectSiteInRouting() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.id(selectSiteDropDown)).click();
		appDriver.findElement(By.id(selectDocSite)).click();
	}

	public void creatSingleTripCard() {

		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.id(createSingleTrip)).click();
		appDriver.findElement(By.xpath(selectTripTypeLogin)).click();
		appDriver.findElement(By.id(selectShiftTime)).click();
		appDriver.findElement(By.xpath(selectShiftTime8)).click();
		appDriver.findElement(By.id(externalId)).click();
		appDriver.findElement(By.id(createSingleTripButton)).click();
	}

	public void navigateToAssignTrip() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.id(assignTrip)).click();
	}

	public void findTrips() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.id(dateInTripList)).click();
		appDriver.findElement(By.id(okInCalender)).click();
		appDriver.findElement(By.id(selectShiftInTripList)).click();
		appDriver.findElement(By.xpath(selectShift8)).click();
		appDriver.findElement(By.id(findInTripsList)).click();
	}

	public void assignTrip() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.xpath(assign)).click();
		appDriver.findElement(By.id(vechicleNumber)).click();
		appDriver.findElement(By.id(assignVechicle)).click();
	}

	public void finalizeTrip() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.xpath(finaize)).click();
	}

	public void confirMation() {

		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.id(reason)).click();
		appDriver.findElement(By.id(yesInConfirmation)).click();
		appDriver.findElement(By.id(doneInConfirmation)).click();

	}

	public void switchToTripsBoard() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.id(tripBoards)).click();
	}

	public void selectShiftAndName() {
		appDriver = DriverFactory.getCurrentAppDriver();
		appDriver.findElement(By.id(shiftDateInTripBoard)).click();
		appDriver.findElement(By.id(shiftTypeInTripBoard)).click();
		appDriver.findElement(By.xpath(shiftTypeLoginInTripBoard)).click();
		appDriver.findElement(By.id(okInTripBoard)).click();

	}

	public void verifyTripBoard() {
		appDriver = DriverFactory.getCurrentAppDriver();
		String vechicleNumber = appDriver.findElement(By.xpath(vechicleNumberTripsBoardList)).getText();
		String tripStstus = appDriver.findElement(By.xpath(tripStatus)).getText();
		if (vechicleNumber.equalsIgnoreCase(ProjectConfig.getPropertyValue("complianceVehicleNumber"))
				&& tripStstus.equalsIgnoreCase(ProjectConfig.getPropertyValue("complianceTripStatus"))) {
			System.out.println("verified trip");
		}
	}
}
