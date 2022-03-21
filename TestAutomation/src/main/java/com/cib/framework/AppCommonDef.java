package com.cib.framework;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppCommonDef {

	static AppiumDriver<MobileElement> driver;
 static int explicitWait=10;
 
 public static String captureScreen() {
		String path;
		File trgtPath = null;
		AppiumDriver<MobileElement> augmentedDriver = null;
		try {
			augmentedDriver = new DriverFactory().getCurrentAppDriver();
			File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			path = TestReport.reportPath + "/" + source.getName();
			// System.out.println(path);
			trgtPath = new File(path);

			FileUtils.copyFile(source, trgtPath);
			return source.getName();// trgtPath.getAbsolutePath();
		} catch (Exception e) {
			Assert.fail();
			return "";
		}
		// return trgtPath.getAbsolutePath();
	}

	public static void waitForVisibility(MobileElement mobileElement) {
		driver=DriverFactory.getCurrentAppDriver();
		WebDriverWait wait = new WebDriverWait(driver,explicitWait);
		wait.until(ExpectedConditions.visibilityOf(mobileElement));

}

	public static void click(MobileElement mobileElement) {
		driver=DriverFactory.getCurrentAppDriver();
		waitForVisibility(mobileElement);
		MobileElement el = mobileElement;
		//el.click();
		el.click();
	}
	
	public static void sendKeys(MobileElement mobileElement, String txt) throws InterruptedException {
		 if(driver!=null) {
		 System.out.println("entering text");
		//waitForVisibility(mobileElement);
	Thread.sleep(4000);
		mobileElement.sendKeys(txt);
		System.out.println("enered text");
	}else {
	System.out.println("driver is null");	
	}
	}
	public static String getAttribute(MobileElement mobileElement, String attribute) {
		waitForVisibility(mobileElement);
		
		 return mobileElement.getAttribute(attribute);
	}
	
	public static void clickInApp(WebElement ele) {
		driver=DriverFactory.getCurrentAppDriver();
		ele.click();
		
	}
}
