package com.cib.framework;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class DriverFactory {
	private static ThreadLocal<WebDriver> currentDriver = new ThreadLocal<WebDriver>();

	public static ThreadLocal<AppiumDriver<MobileElement>> currentAppDriver = new ThreadLocal<AppiumDriver<MobileElement>>();

	public static WebDriver getCurrentDriver() {
		WebDriver driver = currentDriver.get();

		if (driver != null) {
			return driver;
		} else {
			return null;
		}
	}

	public static AppiumDriver<MobileElement> getCurrentAppDriver() {
		System.out.println(currentAppDriver);
		AppiumDriver<MobileElement> appdriver = currentAppDriver.get();
		System.out.println(appdriver);
		if (appdriver != null) {
			return appdriver;
		} else {
			return null;
		}
	}

	public static void driverInit(String browser) {
		switch (browser.toUpperCase()) {
		case "FIREFOX":
			currentDriver.set(new DesktopDriver().getRemoteDriver(browser));
			break;
		case "CHROME":
			currentDriver.set(new DesktopDriver().getRemoteDriver(browser));
			break;
		case "IE":
			currentDriver.set(new DesktopDriver().getRemoteDriver(browser));
			break;
		case "SAFARI":
			currentDriver.set(new DesktopDriver().getRemoteDriver(browser));
			break;
		case "EDGE":
			currentDriver.set(new DesktopDriver().getRemoteDriver(browser));
			break;
		default:
			// System.out.println("Unknown Driver");
		}
	}

	public static void appDriverInit(String appName) {
		switch (appName.toUpperCase()) {
		case "COMPLIANCE":
			currentAppDriver.set(new DesktopDriver().getMobileAppDriver("compliance"));
			break;
		case "DRIVER":
			currentAppDriver.set(new DesktopDriver().getMobileAppDriver("driver"));
			break;

		default:
			currentAppDriver.set(new DesktopDriver().getMobileAppDriver("compliance"));
		}
	}

	public static void closeDriver() {

		try {
			WebDriver driver = currentDriver.get();
			if (driver != null) {
				// getCurrentDriver().close();
				getCurrentDriver().quit();
			}
			currentDriver.remove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}