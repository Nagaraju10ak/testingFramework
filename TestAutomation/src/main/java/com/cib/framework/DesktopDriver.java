package com.cib.framework;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DesktopDriver {

	static WebDriver remoteDriver = null;

	static AppiumDriver<MobileElement> complianceDriver = null;

	public static WebDriver getRemoteDriver(String browser) {

		try {
			if (browser.toUpperCase().contains("FIREFOX")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				remoteDriver = new FirefoxDriver();
			}
			if (browser.toUpperCase().contains("CHROME")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

				ChromeOptions options = new ChromeOptions();

				options.addArguments("test-type");
				options.addArguments("--ignore-ssl-errors");
				options.addArguments("--ignore-certificate-errors");

				remoteDriver = new ChromeDriver(options);
			}
			if (browser.toUpperCase().contains("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
				remoteDriver = new InternetExplorerDriver();
			}
			if (browser.toUpperCase().contains("EDGE")) {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
				remoteDriver = new EdgeDriver();
			}
			remoteDriver.manage().deleteAllCookies();
			remoteDriver.manage().window().maximize();
			TestReport.log(LogStatus.INFO, "Browser Initiated successfully");
			remoteDriver.get(ProjectConfig.getPropertyValue("Mpoddurl")); //Mpoddurl  Alyteurl
			remoteDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			TestReport.log(LogStatus.FATAL, "Browser Initiation Failed :" + e.getMessage());
		}
		return remoteDriver;
	}

	public static AppiumDriver<MobileElement> getMobileAppDriver(String app) {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

		try {
			if (app.equalsIgnoreCase("complianceApp")) {
				File apk = new File(System.getProperty("user.dir") + "\\Resources\\APK\\QA_Compliance.apk");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				capabilities.setCapability("app", apk.getAbsolutePath());
				capabilities.setCapability("appPackage", "com.mahindra.vaayu.compliance");
				capabilities.setCapability("appWaitActivity", "com.mahindra.vaayu.compliance.ui.login.LoginActivity");
				capabilities.setCapability("appActivity", "com.mahindra.vaayu.compliance.ui.splash.SplashActivity");
				complianceDriver = new AndroidDriver(new URL("http:0.0.0.0:4723/wd/hub"), capabilities);
				System.out.println("compliance app driver initialised");
			}

			if (app.equalsIgnoreCase("driverApp")) {
				File apk = new File(System.getProperty("user.dir") + "\\Resources\\APK\\moovedriver-qa-debug.apk");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Test");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
				capabilities.setCapability("app", apk.getAbsolutePath());
				capabilities.setCapability("appPackage", "com.mahindra.app.pts.driver");
				capabilities.setCapability("appWaitActivity", "com.mahindra.vaayu.compliance.ui.login.LoginActivity");
				capabilities.setCapability("appActivity", "in.co.moove.moovedriver.screens.splash.SplashActivity");
				capabilities.setCapability("udid", "e347d0be7d26");
				capabilities.setCapability("noReset", "true");
				complianceDriver = new AndroidDriver(new URL("http:0.0.0.0:4723/wd/hub"), capabilities);
				System.out.println("compliance app driver initialised");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TestReport.log(LogStatus.FATAL, "Mobile Initiation Failed :" + e.getMessage());
		}
		return complianceDriver;
	}
}