package com.cib.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlytePageObjects {

	WebDriver driver;

	public AlytePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//option/parent::select")
	public WebElement siteDrpDwn;
	@FindBy(xpath = "option [text()='Synergy Communications - SC DCO Site']")
	public WebElement validateselectedsite;
	@FindBy(xpath = "(//i[@class=\"icon-home photo-InductionIcon\"])[2]")
	public WebElement webTrips;
	@FindBy(xpath = "//span[text()='Single Trip']")
	public WebElement webSingleTrip;
	@FindBy(xpath = "//span[text()='Routing']")
	public WebElement webRouting;
	@FindBy(xpath = "//span[text()='Upload Trips']")
	public WebElement webUploadTrips;

	@FindBy(name = "externalId")
	public WebElement webExternalId;
	@FindBy(id = "tripType")
	public WebElement webTripType;
	@FindBy(name = "fromDp")
	public WebElement date;
	@FindBy(id = "shiftTime")
	public WebElement webShiftTime;
	@FindBy(id = "guard")
	public WebElement webGuard;
	@FindBy(id = "inputPassword4")
	public WebElement webVehicleNum;
	@FindBy(xpath = "//select[@formcontrolname='vehicleType']")
	public WebElement webVehModel;
	@FindBy(name = "employeeCount")
	public WebElement webEmpCount;
	@FindBy(name = "area")
	public WebElement webArea;
	@FindBy(name = "landmark")
	public WebElement landmark;
	@FindBy(xpath = "//button[text()='Add Single Trip']")
	public WebElement singleTripButton;
	@FindBy(xpath = "//div[@aria-label='Trip Created Successfully']")
	public WebElement webTripSucess;
	@FindBy(xpath = "//span[text()='TripBoard']")
	public WebElement webTripBoard;

	@FindBy(id = "username")
	public WebElement userNameLogin;
	@FindBy(xpath = "//input[@type='password']")
	public WebElement passwordLogin;
	@FindBy(xpath = "//button[text()='SIGN IN']")
	public WebElement signin;
	@FindBy(xpath = "//button[text()='Find']")
	public WebElement webFind;
	@FindBy(xpath = "//mat-select[@aria-label='Shift Name']")
	public WebElement shiftDrpDwn;
	@FindBy(name = "tripDate")
	public WebElement datePick;
	@FindBy(xpath = "//span[text()=' 10:00  (Login) ']")
	public WebElement selectDrpDwn;
	@FindBy(xpath = "//span[text()='1001']")
	public WebElement webValidateTripID;

	@FindBy(xpath = "//span[text()='1001']/ancestor::div[1]/ancestor::div[1]/following-sibling::div/div/div/div/div/i")
	public WebElement webVehicleType;
	@FindBy(xpath = "//select[@placeholder='Select Vehicle Category']")
	public WebElement webVehcileTypeSelector;

	@FindBy(xpath = "//button[text()=' Done']")
	public WebElement webClickOk;
	@FindBy(xpath = "//div[@aria-label='Vehicle type updated successfully']")
	public WebElement webSucess;
	@FindBy(xpath = "(//span[text()='1001']/ancestor::div[1]/ancestor::div[1]/following-sibling::div/div/div/div/div/span)[2]")
	public WebElement validateVehicle;
	@FindBy(xpath = "//span[text()='1001']/ancestor::div[1]/ancestor::div[1]/following-sibling::div/div/div/div/div/div//input")
	public WebElement webVehcileAssign;
	@FindBy(xpath = "//div[@class='form-row']/div/input")
	public WebElement webPlateNum;
	@FindBy(xpath = "//button[text()='Done']")
	public WebElement webClickDone;

	@FindBy(xpath = "//div[@aria-label='Vehicle Assigned Successfully']")
	public WebElement webToastMessageAssig;
	@FindBy(xpath = "//button[text()='Finalize']")
	public WebElement webFinalize;
	@FindBy(xpath = "//span[text()='1001']/preceding-sibling::input[1]")
	public WebElement webCheckbox;
	@FindBy(xpath = "//div[contains(@aria-label,'Route(s) finalised for id')]")
	public WebElement webRouteFinalize;
	@FindBy(xpath = "//mat-select[@aria-label='Items per page:']/div/div[2]")
	public WebElement clickOnPage;
	@FindBy(xpath = "//span[text()='100']")
	public WebElement selectRows;
	@FindBy(xpath = "//strong[text()='1001']/parent::td/following-sibling::td[text()=' Login ']")
	public WebElement ext_id;
	@FindBy(xpath = "//strong[text()='1001']/parent::td/following-sibling::td[text()=' 10:00 ']")
	public WebElement loginTime;
	@FindBy(xpath = "//strong[text()='1001']/parent::td/following-sibling::td/span")
	public WebElement plateNum;
	@FindBy(xpath = "//strong[text()='1001']/parent::td/following-sibling::td/span[text()='Pending Acceptance']")
	public WebElement status;
	@FindBy(name = "tripDate")
	public WebElement toDate;
	@FindBy(xpath = "//button[text()=' OK']")
	public WebElement clickOk;

	@FindBy(name = "toDate")
	public WebElement webToDate;
	@FindBy(name = "fromDp")
	public WebElement webFromDate;
	@FindBy(xpath = "//button[text()='Upload Route']")
	public WebElement webUploadButton;
	@FindBy(xpath = "//button[text()='Done']")
	public WebElement webDoneButton;
//	@FindBy(xpath="//input[@type='file']") public WebElement webUploadOption;
	@FindBy(xpath = "//label[text()='Choose file']")
	public WebElement webUploadOption;
	@FindBy(xpath = "//div[contains(@aria-label,'Route(s) uploaded for Login')]")
	public WebElement webUploadMessage;
}
