package com.cib.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MPODDObjects {

	WebDriver driver;

	public MPODDObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//input[@type='checkbox'])[2]")
	public WebElement selectInvoice;
	@FindBy(id="ctl00_ContentPlaceHolder1_gdMaterialInwardLR_ctl02_lblInvoiceNumber")
	public WebElement getInvoiceNumber;
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnAllocate")
	public WebElement createLR;
	@FindBy(id = "ctl00_ContentPlaceHolder1_ddlIssueLocation")
	public WebElement issueLocation;
	@FindBy(id = "ctl00_ContentPlaceHolder1_ddlAssociateTransporter")
	public WebElement associateTransport;
	@FindBy(id = "ctl00_ContentPlaceHolder1_ddlServiceFreightType")
	public WebElement serviceFreight;
	@FindBy(id = "ctl00_ContentPlaceHolder1_txtVehicleType")
	public WebElement vehicleType;
	@FindBy(id = "ctl00_ContentPlaceHolder1_txtRoute")
	public WebElement route;
	@FindBy(id = "ctl00_ContentPlaceHolder1_txtVehicleNo")
	public WebElement vehicleNum;
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnOK")
	public WebElement createLRbutton;
	@FindBy(id = "ctl00_ContentPlaceHolder1_lblmsg")
	public WebElement LRsuccessMessage;
	@FindBy(xpath = "//a[text()='Inbound']")
	public WebElement webInbound;
	@FindBy(xpath = "//a[text()='Inward Material with Upload']")
	public WebElement webInboundMaterial;
	@FindBy(id = "txtUserName")
	public WebElement userNameLogin;
	@FindBy(id = "txtPassword")
	public WebElement passwordLogin;
	@FindBy(id = "btnLogin")
	public WebElement signin;
	@FindBy(id = "ddlLocation")
	public WebElement area;
	@FindBy(id = "btnContinue")
	public WebElement clickCotinue;
	@FindBy(id = "ctl00_btnLogout")
	public WebElement logout;
	@FindBy(xpath = "//select[@class='select-control']")
	public WebElement associateTransporter;
	@FindBy(id = "ctl00_ContentPlaceHolder1_ddlServiceFreightType")
	public WebElement ServiceFreightType;
	@FindBy(id = "ctl00_ContentPlaceHolder1_txtRoute")
	public WebElement Route;

	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtLRNo")
	public WebElement LRNo;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtLRDate")
	public WebElement LRDate;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_divwidthSupplierCode")
	public WebElement supplierCode;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtSupplierName")
	public WebElement supplierName;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtProductCode")
	public WebElement productCd;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtProductDesc")
	public WebElement productDesc;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtInvNo")
	public WebElement InvNo;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtInvDate")
	public WebElement InvDate;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtInvValue")
	public WebElement InvValue;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_ddlDestinationPlantFooter")
	public WebElement DestinationPlant;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtBuyerRequestID")
	public WebElement buyerRequestID;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtTriggerQty")
	public WebElement triggerQTY;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtReceivedQty")
	public WebElement receiveQTY;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtUnits")
	public WebElement units;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtNoofBoxes")
	public WebElement noOfBoxes;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtTypeofPackaging")
	public WebElement typeOfPacking;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtIncoTerm")
	public WebElement incoTerm;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtASNNo")
	public WebElement ASNNo;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtGross_Wt")
	public WebElement actualWt;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtCFT")
	public WebElement CFT;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtCFT_Wt")
	public WebElement CFTWt;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl03_txtBillable_Wt")
	public WebElement billableWt;
	@FindBy(xpath = "//a[text()='Add']")
	public WebElement addButton;
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnSubmit")
	public WebElement submitButton;
	
	@FindBy(id="ctl00_ContentPlaceHolder1_gdMaterialInwardLR_ctl01_txtInvoice_Number")
	public WebElement searchInvNum;

	@FindBy(id = "ctl00_ContentPlaceHolder1_btnSubmit")
	public WebElement submitCreateShipment;
	@FindBy(id = "ctl00_ContentPlaceHolder1_grvMaterialInward_ctl01_checkAll")
	public WebElement selectAll;

	@FindBy(id = "ctl00_ContentPlaceHolder1_txtVehicleNo")
	public WebElement vehicleNumber;
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnSearch")
	public WebElement searchButton;
	@FindBy(id = "ctl00_ContentPlaceHolder1_btnCreateShipment")
	public WebElement createShipment;

}
