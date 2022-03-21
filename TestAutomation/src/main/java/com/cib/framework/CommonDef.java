package com.cib.framework;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class CommonDef {

	// static WebDriver driver = DriverFactory.getCurrentDriver();
	static WebDriver driver;

	public static String captureScreen() {
		String path;
		File trgtPath = null;
		WebDriver augmentedDriver = null;
		try {
			augmentedDriver = new Augmenter().augment(DriverFactory.getCurrentDriver());
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

	public static void select(WebElement elem, String keysToSend) {
		try {
			Select obj = new Select(elem);
			obj.selectByVisibleText(keysToSend);
			Thread.sleep(1500);
			TestReport.log(LogStatus.PASS, "Text is entered successfully :" + keysToSend);
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Text not entered successfully " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void highlight(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getCurrentDriver();
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border:2px solid red;');",
					element);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForPageLoad() {
		driver = DriverFactory.getCurrentDriver();
		ExpectedCondition<Boolean> expect = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, 120);
		try {
			wait.until(expect);
		} catch (Exception e) {
			Assert.fail();
			e.printStackTrace();
			TestReport.log(LogStatus.INFO, "Page Load Condition failed. Continuing with test");
		}
	}

	public static WebElement findElement(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			return driver.findElement(by);
		} catch (Exception E) {
			System.out.println(E.toString());
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			E.printStackTrace();
			TestReport.log(LogStatus.ERROR, "Element not located in the page :" + E.getMessage());
			return null;
		}
	}

	public static boolean isElementPresent(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			driver.findElement(by);
			return true;
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			return false;
		}
	}

	public static boolean isElementPresent(String element) {
		try {
			WebElement elem = findElement(By.xpath(element));
			elem.isEnabled();
			return true;
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			return false;
		}
	}

	public static boolean isElementPresent(WebElement elem) {
		try {
			return elem.isEnabled();
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			return false;
		}
	}

	public static List<WebElement> findElements(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			return driver.findElements(by);
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Elements not located in the page");
			return null;
		}
	}

	/**
	 * Used to find multiple elements in Payments module
	 */
	public static ArrayList<String> findElementsText(By by) {
		try {
			ArrayList<String> mylist = new ArrayList<String>();
			List<WebElement> elems = findElements(by);
			for (WebElement elem : elems) {
				mylist.add(elem.getText());
			}
			return mylist;
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Elements not located in the page");
			return null;
		}
	}

	public static void click(String element) {
		try {
			WebElement elem = findElement(By.xpath(element));
			highlight(elem);
			elem.click();
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page");
		} catch (Exception E) {
			Assert.fail();
			E.printStackTrace();
			TestReport.log(LogStatus.ERROR, "Elements not located in the page");
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void click(By by) {
		try {
			WebElement elem = findElement(by);
			highlight(elem);
			elem.click();
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page");
		} catch (Exception E) {
			E.printStackTrace();
			Assert.fail();
			TestReport.log(LogStatus.ERROR, "Elements not located in the page");
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void clickThrowError(By by, String elemname) {
		driver = DriverFactory.getCurrentDriver();
		WebElement elem = driver.findElement(by);// findElement(by);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", elem);
		TestReport.log(LogStatus.INFO, "Element successfully clicked in the page: " + elemname);
	}

	public static void clickJS(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement elem = findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page");
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Elements not located in the page");
		}
	}

	public static void clickJS(By by, String elemname) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement elem = findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Elements not located in the page: " + elemname);
		}
	}

	public static void clickJS(WebElement elem, String elemname) {
		try {
			driver = DriverFactory.getCurrentDriver();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Elements not located in the page: " + elemname);
		}
	}

	public static void clickJSNoError(By by, String elemname) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement elem = driver.findElement(by);// findElement(by);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void click(By by, String elemname) {
		try {
			waitClickable(by);
			WebElement elem = findElement(by);
			elem.click();
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page: " + elemname);
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void clickNoError(By by, String elemname) {
		try {
			driver = DriverFactory.getCurrentDriver();
			Thread.sleep(10000);
			driver.findElement(by).click();
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page: " + elemname);
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void click(WebElement elem) {
		try {
			elem.click();
			TestReport.logNoScreen(LogStatus.INFO, "Element successfully clicked in the page");
		} catch (Exception E) {
			System.out.println(E.toString());
			Assert.fail();
			TestReport.log(LogStatus.FAIL, "Elements not located in the page");
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void click(WebElement elem, String elementname) {
		try {
			highlight(elem);
			elem.click();
			TestReport.logNoScreen(LogStatus.INFO, elementname + " successfully clicked in the page");
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.FAIL, "Elements not located in the page: " + elementname);
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void clickbyValue(String value) {
		try {
			String xpath = "//*[contains(text(),'" + value + "')]";
			WebElement elem = DriverFactory.getCurrentDriver().findElement(By.xpath(xpath));
			elem.click();
			TestReport.logNoScreen(LogStatus.INFO, value + " successfully is selected");
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.FAIL, "Elements not located in the page: " + value);
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static Boolean ObjectExist(By LocatorValue) {
		Boolean ObjectExist = null;

		driver = DriverFactory.getCurrentDriver();

		waitVisibleNoError(LocatorValue, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
		try {
			Thread.sleep(1500);
			if (driver.findElement(LocatorValue).isDisplayed()) {
				ObjectExist = true;
				System.out.println(ObjectExist);
			} else
				ObjectExist = false;
		} catch (Exception E) {
			// ObjectExist = false;
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
		}
		return ObjectExist;
	}

	public static void sendKeys(By by, String keysToSend) {
		try {
			waitVisible(by);
			WebElement t = findElement(by);
			t.click();
			// CommonMethods.wait(2);
			t.sendKeys(keysToSend);
			TestReport.log(LogStatus.INFO, keysToSend + " is entered successfully");
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Text not entered successfully");
		}
	}

	public static void sendKeysEnter() {
		try {
			driver = DriverFactory.getCurrentDriver();
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Text not entered successfully");
		}
	}

	public static void uploadFile(String fileName) {
		try {
			StringSelection stringSelection = new StringSelection(fileName);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, stringSelection);

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			TestReport.logNoScreen(LogStatus.INFO, fileName + " file uploaded successfully");
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.FAIL, "Unable to upload file successfully");
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void sendKeys(WebElement elem, String keysToSend) {
		try {
			if ((keysToSend != null) | (keysToSend != "")) {
				elem.sendKeys(keysToSend);
				TestReport.log(LogStatus.INFO, keysToSend + " is entered successfully");
			}
		} catch (Exception E) {
			System.out.println(E.toString());
			Assert.fail();
			TestReport.log(LogStatus.FAIL, keysToSend + " is not entered successfully");
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void waitClick(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Element not clickable");
		}
	}

	public static void waitVisible(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			System.out.println("");
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			// wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitVisible(By by, int sec) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Element not visible");
		}
	}

	public static void waitVisible(By by, String name) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.FAIL, "Element is visible :" + e.getMessage());
		}
	}

	public static void waitInVisible(By by, String name) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			TestReport.log(LogStatus.PASS, "Element not visible :" + name);
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.ERROR, "Element is visible :" + name);
		}
	}

	public static void waitClickable(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
			System.out.println("Element is clickable " + by);
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.ERROR, "Element not clickable");
		}
	}

	public static void waitClickable(By by, String elemname) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.ERROR, "Element not clickable");
		}

	}

	public static boolean waitVisibleNoError(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			// WebDriverWait wait = new WebDriverWait(driver,
			// Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			// TestReport.log(LogStatus.ERROR, "Element not visible");
			Assert.fail();
			return false;
		}
	}

	public static void waitVisibleNoError(By by, int sec) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, sec);
			// WebDriverWait wait = new WebDriverWait(driver,
			// Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			// TestReport.log(LogStatus.ERROR, "Element not visible");
			Assert.fail();
		}
	}

	public static void waitForAttribute(By by, String attr, String value) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			// WebDriverWait wait = new WebDriverWait(driver,
			// Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.attributeContains(by, attr, value));
		} catch (Exception e) {
			Assert.fail();
			TestReport.log(LogStatus.ERROR, "Element has not switched to the correct attribute to continue");
		}
	}

	public static void waitClickableNoError(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TestReport.log(LogStatus.ERROR, "Element not visible");
			Assert.fail();
		}
	}

	public static void waitClickableNoError(By by, int time) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TestReport.log(LogStatus.ERROR, "Element not visible");
			Assert.fail();
		}
	}

	public static void waitVisible(WebElement elem) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			System.out.println(e.toString());
			TestReport.log(LogStatus.ERROR, "Element not visible");
			Assert.fail();
		}
	}

	public static void waitVisible(WebElement elem, String name) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Element not visible :" + name);
		}
	}

	public static void clearText(WebElement elem) {
		elem.clear();
		// driver=DriverFactory.getCurrentDriver();
		// Actions action = new Actions(driver);
		// action.sendKeys(Keys.CONTROL).sendKeys("A").sendKeys(Keys.DELETE).build().perform();
	}
	
	public static void clearTextAction(WebElement elem) {
		
		 driver=DriverFactory.getCurrentDriver();
		 elem.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}

	public static void waitClickable(WebElement elem) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			System.out.println(e.toString());
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Element not clickable");
		}
	}

	public static void waitClickable(WebElement elem, String elemname) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(ProjectConfig.getPropertyValue("timeout")));
			wait.until(ExpectedConditions.visibilityOf(elem));
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Element not clickable: " + elemname);
		}
	}

	public static void moveClick(By by, String elemname) {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.moveToElement(findElement(by)).click().build().perform();
	}

	public static void moveClick(WebElement elem, String elemname) {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.moveToElement(elem).click().build().perform();
	}

	public static void moveToElement(By by) {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.moveToElement(findElement(by)).perform();
	}

	public static void moveToElement(By by, String elemname) {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.moveToElement(findElement(by)).perform();
	}

	public static void moveToElement(WebElement elem, String elemname) {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.moveToElement(elem).perform();
	}
	public static void moveToElementAndClick(WebElement elem ) {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.moveToElement(elem).click().perform();
	}

	public static void moveToElementNoLog(WebElement elem) {
		try {
			driver = DriverFactory.getCurrentDriver();
			Actions action = new Actions(driver);
			action.moveToElement(elem).perform();
		} catch (Exception E) {
			System.out.println(E.toString());
			System.out.println("Underlying exception: " + E.getCause());
			Assert.fail();
		}
	}

	public static void KeyDown() {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.DOWN).perform();
	}

	public static void moveToElementClickNoFail(By by, String elemname) {
		driver = DriverFactory.getCurrentDriver();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(by)).click().build().perform();
	}

	public static String getText(WebElement policyStatus) {
		try {
			return policyStatus.getText();
		} catch (Exception E) {
			System.out.println(E.toString());
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "UNABLE TO GET TEXT FROM ELEMENT");
		}
		return null;
	}

	public static String getTextNoError(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			return driver.findElement(by).getText();
		} catch (Exception E) {
			// TestReport.log(LogStatus.FAIL, "UNABLE TO GET TEXT FROM
			// ELEMENT");
			Assert.fail();
		}
		return null;
	}

	public static String getText(By by, String elem) {
		try {
			return findElement(by).getText();
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "UNABLE TO GET TEXT FROM ELEMENT: " + elem);
		}
		return null;
	}

	public static void assertContainsData(By by) {
		waitVisible(by);
		try {
			WebElement elem = findElement(by);
			if (!elem.getText().isEmpty())
				TestReport.log(LogStatus.PASS, "Data is populated in the Element");
			else
				TestReport.log(LogStatus.FAIL, "Data is not populated in the Element");
		} catch (Exception e) {
			TestReport.log(LogStatus.ERROR, "Error in accessing data");
			Assert.fail();
		}
	}

	public static void assertContainsData(By by, String string) {
		try {
			WebElement elem = findElement(by);
			if (elem.getText().isEmpty())
				TestReport.log(LogStatus.FAIL, "Element " + elem + "has no data");
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error accessing element data");
			Assert.fail();
		}
	}

	public static boolean assertContainsNoError(WebElement elem, String text) {
		try {
			if (elem.getText().contains(text)) {
				TestReport.log(LogStatus.PASS, "Text is populated in the Element: " + text);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return false;
	}

	public static void assertEquals(By by, String text) {
		try {
			Thread.sleep(2000l);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		waitVisible(by);
		try {
			WebElement elem = findElement(by);
			if (elem.getText().trim().equals(text.trim()))
				TestReport.log(LogStatus.PASS, "Text is populated in the Element: " + text);
			else
				TestReport.log(LogStatus.FAIL, "Text is not populated in the Element: " + text);
		} catch (Exception e) {
			TestReport.log(LogStatus.ERROR, "Error in accessing Text");
			Assert.fail();
		}
	}

	public static String getTextOfElement(By by) {
		waitVisible(by);
		try {
			WebElement elem = findElement(by);
			return elem.getText().toString().trim();
		} catch (Exception e) {
			TestReport.log(LogStatus.ERROR, "Error in returning Text");
			Assert.fail();
			return null;
		}
	}

	public static void assertEqualsComparison(String actVal, String expVal) {
		try {
			if (expVal.trim().equals(actVal.trim()))
				TestReport.log(LogStatus.PASS, "Expected value is equal to Actual value. Actual Value is : " + actVal
						+ " .Expected Value is : " + expVal);
			else
				TestReport.log(LogStatus.FAIL, "Expected value is not equal to Actual value. Actual Value is : "
						+ actVal + " .Expected Value is : " + expVal);
		} catch (Exception e) {
			TestReport.log(LogStatus.ERROR, "Error in asserting");
			Assert.fail();
		}
	}

	public static boolean assertEqualsComparisonStr(String actVal, String expVal) {
		try {
			boolean flag = false;
			if (expVal.equalsIgnoreCase(actVal)) {
				flag = true;
				// TestReport.log(LogStatus.PASS, "Expected value is equal to
				// Actual value. Actual Value is : " + actVal
				// + " .Expected Value is : " + expVal);
			} else {
				flag = false;
				// TestReport.log(LogStatus.FAIL, "Expected value is not equal
				// to Actual value. Actual Value is : " + actVal
				// + " .Expected Value is : " + expVal);
			}
			return flag;

		} catch (Exception e) {
			TestReport.log(LogStatus.ERROR, "Error in asserting");
			Assert.fail();
			return false;
		}
	}

	public static void t24Validation(String key, String actVal, String expVal) {
		try {
			if (expVal.equals(actVal))
				TestReport.log(LogStatus.PASS, "Expected value " + key + " is equal to Actual value. Actual Value is : "
						+ actVal + " .Expected Value is : " + expVal);
			else if ((key.equalsIgnoreCase("REASON.TEXT:1:1")) && (actVal.contains(expVal)))
				TestReport.log(LogStatus.PASS, "Expected value " + key + " is equal to Actual value. Actual Value is : "
						+ actVal + " .Expected Value is : " + expVal);
			else
				TestReport.log(LogStatus.FAIL,
						"Expected value " + key + " is not equal to Actual value. Actual Value is : " + actVal
								+ " .Expected Value is : " + expVal);
		} catch (Exception e) {
			TestReport.log(LogStatus.ERROR, "Error in validating");
			Assert.fail();
		}
	}

	public static void assertEqualsIgnoreCaseComparison(By by, String expVal) {
		waitVisible(by);
		try {
			WebElement elem = findElement(by);
			String actVal = elem.getText().trim();
			if (actVal.equalsIgnoreCase(expVal))
				TestReport.log(LogStatus.PASS, "Expected value is equal to Actual value. Actual Value is : " + actVal
						+ " .Expected Value is : " + expVal);
			else
				TestReport.log(LogStatus.FAIL, "Expected value is not equal to Actual value. Actual Value is : "
						+ actVal + " .Expected Value is : " + expVal);
			Assert.fail();
		} catch (Exception e) {
			TestReport.log(LogStatus.ERROR, "Error in asserting");
			Assert.fail();
		}
	}

	public static boolean assertContains(By by, String text) {
		try {
			WebElement elem = findElement(by);
			try {
				moveToElementNoLog(elem);

			} catch (Exception e) {

			}
			if (elem.getText().trim().contains(text)) {
				// System.out.println(elem.getText());
				// System.out.println(text);
				TestReport.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getText());
				return true;
			} else {
				TestReport.log(LogStatus.FAIL,
						"Text is not populated in the Element: " + text + " ||actual|| " + elem.getText());
				Assert.fail();
				return false;
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertContainsOnAttribute(By by, String text, String Attribute) {
		try {
			WebElement elem = findElement(by);
			String attrbdata = elem.getAttribute(Attribute);
			try {
				moveToElementNoLog(elem);
			} catch (Exception e) {

			}
			if (attrbdata.contains(text)) {
				TestReport.log(LogStatus.PASS, "Text is populated in the Attribute");
				return true;
			} else {
				TestReport.log(LogStatus.FAIL, "Text is not populated in the Attribute");
				Assert.fail();
				return false;
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertElemsContains(By by, String text) {
		try {
			List<WebElement> elems = findElements(by);
			for (WebElement elem : elems) {
				try {
					moveToElementNoLog(elem);
				} catch (Exception e) {

				}
				if (elem.getText().contains(text)) {
					TestReport.log(LogStatus.PASS,
							"Text is populated in the Element: " + text + " ||actual|| " + elem.getText());
					return true;
				}
			}
			TestReport.log(LogStatus.FAIL, "Text is not populated in the Elements: " + text);
			Assert.fail();
			return false;
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertElemsContainsWarn(By by, String text) {
		try {
			List<WebElement> elems = findElements(by);
			for (WebElement elem : elems) {
				try {
					moveToElementNoLog(elem);
				} catch (Exception e) {

				}
				if (elem.getText().contains(text)) {
					TestReport.log(LogStatus.PASS,
							"Text is populated in the Element: " + text + " ||actual|| " + elem.getText());
					return true;
				}
			}
			TestReport.log(LogStatus.WARNING, "Text is not populated in the Elements: " + text);
			return false;
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertInnerHTMLContains(By by, String text) {
		try {
			WebElement elem = findElement(by);
			if (elem.getAttribute("innerHTML").contains(text)) {
				TestReport.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getAttribute("innerHTML"));
				return true;
			} else {
				TestReport.log(LogStatus.FAIL, "Text is not populated in the Element: " + text + " ||actual|| "
						+ elem.getAttribute("innerHTML"));
				Assert.fail();
				return false;
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertInnerHTMLContainsNoError(By by, String text) {
		try {
			WebElement elem = findElement(by);
			if (elem.getAttribute("innerHTML").contains(text)) {
				TestReport.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getAttribute("innerHTML"));
				return true;
			} else {
				TestReport.log(LogStatus.INFO, "Text is not populated in the Element: " + text + " ||actual|| "
						+ elem.getAttribute("innerHTML"));
				return false;
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertInnerHTMLContainsNoError(WebElement elem, String text) {
		try {
			if (elem.getAttribute("innerHTML").contains(text)) {
				TestReport.log(LogStatus.PASS,
						"Text is populated in the Element: " + text + " ||actual|| " + elem.getAttribute("innerHTML"));
				return true;
			} else {
				TestReport.log(LogStatus.INFO, "Text is not populated in the Element: " + text + " ||actual|| "
						+ elem.getAttribute("innerHTML"));
				return false;
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertContains(WebElement elem, String text) {
		try {
			if (elem.getText().contains(text)) {
				TestReport.log(LogStatus.PASS, "Text is populated in the Element: " + text);
				return true;
			} else {
				System.out.println(elem.getText());
				TestReport.log(LogStatus.FAIL, "Text is not populated in the Element: " + text);
				Assert.fail();
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static void assertContains(By by, String text, String passmsg) {
		try {
			WebElement elem = findElement(by);
			if (elem.getText().contains(text))
				TestReport.log(LogStatus.PASS, passmsg + " - Text is populated in the Element: " + text);
			else
				TestReport.log(LogStatus.FAIL, passmsg + " - Text is not populated in the Element: " + text);
			Assert.fail();
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
	}

	public static void assertContains(String actVal, String expVal) {
		try {
			if (actVal.contains(expVal))
				TestReport.log(LogStatus.PASS, actVal + " -- Actual Value contains Expected Value -- " + expVal);
			else
				TestReport.log(LogStatus.FAIL, actVal + " -- Actual Value doesn't contain Expected Value -- " + expVal);
			Assert.fail();
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
	}

	public static void scrollDown() {
		try {
			driver = DriverFactory.getCurrentDriver();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			// jse.executeScript("window.scrollTo(0,
			// document.body.scrollHeight)");
			jse.executeScript("scroll(0, 700);");
		} catch (Exception E) {
			Assert.fail();
		}
	}

	public static void scrollUp() {
		try {

			driver = DriverFactory.getCurrentDriver();

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, -250);");
		} catch (Exception E) {
			Assert.fail();
		}
	}
	
	public static void scrollLeft() {
		try {

			driver = DriverFactory.getCurrentDriver();

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("scroll(0, 0);");
		} catch (Exception E) {
			Assert.fail();
		}
	}

	public static void findElementsClick(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			List<WebElement> elm = driver.findElements(by);
			for (WebElement s : elm) {
				clickJS(s);
			}
		} catch (Exception E) {
			TestReport.log(LogStatus.ERROR, "Elements not located in the page");
			Assert.fail();
		}
	}

	public static int findElementsCount(By by) {
		int itmcnt = 0;
		try {
			driver = DriverFactory.getCurrentDriver();
			List<WebElement> elm = driver.findElements(by);
			for (WebElement s : elm) {
				itmcnt = itmcnt + 1;
			}
		} catch (Exception E) {
			TestReport.log(LogStatus.ERROR, "Elements not located in the page");
			Assert.fail();
		}
		return itmcnt;
	}

	public static void clickJS(WebElement elem) {
		try {
			driver = DriverFactory.getCurrentDriver();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elem);
			TestReport.log(LogStatus.PASS, "Element successfully clicked in the page");
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Elements not located in the page");
		}
	}

	public static void isDisplayed(By by, String cont) {
		try {
			driver = DriverFactory.getCurrentDriver();
			waitVisible(by);
			WebElement element = driver.findElement(by);
			element.isDisplayed();
			TestReport.log(LogStatus.PASS, "Element Available in the page " + cont);
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Element not Available in the page " + e.getMessage());
		}
	}

	public static String getAttribute(By by, String attribute) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = driver.findElement(by);
			return element.getAttribute(attribute);
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Error getting attribure value of an element " + e.getMessage());
			return null;
		}
	}

	public static String getAttribute(WebElement element, String attribute) {
		try {
			return element.getAttribute(attribute);
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Error getting attribure value of an element " + e.getMessage());
			return null;
		}
	}

	public static void assertEquals(String attribute, String value) {
		try {
			if (attribute.equalsIgnoreCase(value))
				TestReport.log(LogStatus.PASS, "Element has the correct attribute");
			else
				TestReport.log(LogStatus.FAIL, "Element dooesn't has the correct attribure");
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Error getting attribure value of an element " + e.getMessage());
		}
	}

	public static void clickJquery(String query) {
		driver = DriverFactory.getCurrentDriver();
		((JavascriptExecutor) driver).executeScript("return jQuery('" + query + "').get(0)");
	}

	public static void scollToPageEnd() {
		driver = DriverFactory.getCurrentDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}

	public static void switchNewWindow() {
		driver = DriverFactory.getCurrentDriver();

		try {
			Set<String> newwindows = driver.getWindowHandles();
			String CurWindow = (String) newwindows.toArray()[newwindows.size() - 1];
			driver.switchTo().window(CurWindow);
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Unable to Navigate New Window : " + E.getMessage());
		}
	}

	public static void switch_Window() {
		driver = DriverFactory.getCurrentDriver();

		try {
			Set<String> win = driver.getWindowHandles();
			int i = 0;
			for (String w : win) {
				if (i == 1) {
					driver.switchTo().window(w);
					TestReport.log(LogStatus.PASS, "Switch window");
					break;
				}
				if (i == 0) {
					driver.switchTo().window(w);
					// String parent=w;
					i = i + 1;
					// TestReport.log(LogStatus.PASS, "Switch window");
				}

			}

		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.ERROR, "Unable Switch the Window: " + e.getMessage());
		}
	}

	public static void brokenLinkValidation(String URL) {
		// System.out.println("inside broken link");
		int response = getResponseCode(URL);
		if (response == 200) {
			// System.out.println("working 200");
			TestReport.logNoScreen(LogStatus.PASS, URL + "-URL is working");
		} else {
			// System.out.println("not working NOT 200");
			TestReport.logNoScreen(LogStatus.FAIL, URL + "-URL is not working");
			Assert.fail();
		}
	}

	public static int getResponseCode(String urlString) {
		try {
			URL u = new URL(urlString);
			HttpURLConnection h = (HttpURLConnection) u.openConnection();
			h.setRequestMethod("GET");
			h.connect();
			if (h.getResponseCode() == 301 || h.getResponseCode() == 302 || h.getResponseCode() == 307) {
				String newUrl = h.getHeaderField("Location");
				u = new URL(newUrl);
				h = (HttpURLConnection) u.openConnection();
				h.setRequestMethod("GET");
				h.connect();
			}
			return h.getResponseCode();
		} catch (MalformedURLException e) {
			TestReport.log(LogStatus.FAIL, "MalformedURLException" + e.getMessage());
			Assert.fail();
			return -1;
		} catch (IOException e) {
			TestReport.log(LogStatus.FAIL, "MalformedURLException" + e.getMessage());
			Assert.fail();
		}
		return 0;
	}

	public static void assertContainsData(WebElement web, String string) {
		try {
			if (web.getText().isEmpty())
				TestReport.log(LogStatus.FAIL, "Element " + web + "has no data");
			Assert.fail();
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error accessing element data");
			Assert.fail();
		}
	}

	public static void reloadPage() {
		try {
			driver = DriverFactory.getCurrentDriver();
			driver.navigate().refresh();
			waitForPageLoad();
		} catch (Exception e) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Error reloading page");
		}
	}

	public static void dropdown(WebElement ele, String keysToSend) {
		if (!(keysToSend == null)) {
			try {
				Select sel = new Select(ele);
				sel.selectByVisibleText(keysToSend);
				TestReport.logNoScreen(LogStatus.INFO, keysToSend + " Selected successfully");
			} catch (Exception E) {
				System.out.println(E.toString());
				Assert.fail();
				DriverFactory.getCurrentDriver().quit();
				E.printStackTrace();
				TestReport.log(LogStatus.FAIL, "Text not entered successfully");
			}
		} else {
			TestReport.logNoScreen(LogStatus.INFO, keysToSend);
		}
	}
	
	public static void dropdown(By by , String keysToSend) {
		if (!(keysToSend == null)) {
			try {
				WebElement ele = findElement(by);
				Select sel = new Select(ele);
				sel.selectByVisibleText(keysToSend);
				TestReport.logNoScreen(LogStatus.INFO, keysToSend + " Selected successfully");
			} catch (Exception E) {
				System.out.println(E.toString());
				Assert.fail();
				DriverFactory.getCurrentDriver().quit();
				E.printStackTrace();
				TestReport.log(LogStatus.FAIL, "Text not entered successfully");
			}
		} else {
			TestReport.logNoScreen(LogStatus.INFO, keysToSend);
		}
	}

	public static void selectfromDropdownlist(List<WebElement> list, String value) {
		if (!(value == null)) {
			try {
				for (int i = 0; i <= list.size() - 1; i++) {
					if (list.get(i).getText().contains(value)) {
						list.get(i).click();
						break;
					}
				}
			} catch (Exception E) {
				Assert.fail();
				DriverFactory.getCurrentDriver().quit();
				E.printStackTrace();
				TestReport.log(LogStatus.FAIL, "Text not entered successfully");
			}
		} else {
			TestReport.logNoScreen(LogStatus.INFO, value);
		}
	}

	public static void tabEnter() {
		try {
			driver = DriverFactory.getCurrentDriver();
			Actions act = new Actions(driver);
			act.sendKeys(Keys.TAB);
			// System.out.println("tab entered");
			TestReport.log(LogStatus.INFO, "Tab key is entered successfully");
		} catch (Exception E) {
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
			TestReport.log(LogStatus.FAIL, "Tab key is not entered successfully");
		}
	}

	public static void Keys_TAB(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			driver.findElement(by).sendKeys(Keys.TAB);
			TestReport.log(LogStatus.INFO, "Tab key is entered successfully");
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Tab key is not entered successfully");
			Assert.fail();
		}
	}
	
	public static void Keys_BACKSPACE(WebElement ele) {
		try {
			driver = DriverFactory.getCurrentDriver();
			ele.sendKeys(Keys.BACK_SPACE);
			TestReport.log(LogStatus.INFO, "Tab key is entered successfully");
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Tab key is not entered successfully");
			Assert.fail();
		}
	}

	public static List<String> getDropdownValues(By by) {
		WebElement element = findElement(by);
		Select sel = new Select(element);
		// return
		// sel.getOptions().stream().map(e->e.getText()).collect(Collectors.toList());
		return null;
	}

	public static void selectByValue(WebElement clienAddrState, String value) {
		try {
			if (!value.isEmpty() | !(value == null)) {
				Select sel = new Select(clienAddrState);
				sel.selectByValue(value);
				TestReport.log(LogStatus.INFO, "Value is selected successfully :" + value);
			}
		} catch (Exception E) {
			E.printStackTrace();
			TestReport.log(LogStatus.FAIL, "Value is not selected successfully");
			Assert.fail();
		}
	}

	public static void selectByText(By by, String value) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			// element.click();
			Select sel = new Select(element);
			sel.selectByVisibleText(value);
			TestReport.log(LogStatus.INFO, "Value is selected successfully :" + value);
		} catch (Exception E) {
			E.printStackTrace();
			TestReport.log(LogStatus.FAIL, "Value is not selected successfully");
			Assert.fail();
		}
	}

	public static void selectByIndex(By by, int index) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			Select sel = new Select(element);
			sel.selectByIndex(index);
			TestReport.log(LogStatus.INFO,
					"Value is selected successfully :" + sel.getFirstSelectedOption().getText().toString());
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Value is not selected successfully");
			Assert.fail();
		}
	}

	public static int getAllOptionsSize(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			Select sel = new Select(element);
			return sel.getOptions().size();
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Size is 0");
			return 0;
		}
	}

	public static List<WebElement> getAllOptions(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			Select sel = new Select(element);
			return sel.getOptions();
		} catch (Exception E) {
			return null;
		}
	}

	public static String getSelectedOptions(By by) {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			Select sel = new Select(element);
			return sel.getFirstSelectedOption().getText().toString();
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Value is not fetched");
			return "";
		}
	}

	public static List<String> getOptions(By by) {
		// WebDriver driver = DriverFactory.getCurrentDriver();
		WebElement element = findElement(by);
		Select sel = new Select(element);
		// return
		// sel.getOptions().stream().map(e->e.getText().trim()).collect(Collectors.toList());
		return null;
	}

	public static String autoPopulateValidationflow(By by) {
		try {
			return CommonDef.getAttribute(by, "value");
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Value is not fetched");
			return "";
		}
	}

	public static boolean isEnabled(By by) {
		driver = DriverFactory.getCurrentDriver();
		waitVisible(by);
		WebElement element = driver.findElement(by);
		return element.isEnabled();
	}

	public static void getJSDropdown(String dropDown, String elementID) throws Exception {
		driver = DriverFactory.getCurrentDriver();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("js select");
		String dropdownScript = "var select = window.document.getElementById('" + dropDown
				+ "'); for(var i = 0; i < select.options.length; i++){if(select.options[i].text == '" + elementID
				+ "'){ select.options[i].selected = true; } }";

		Thread.sleep(2000);
		System.out.println("dropdownscript");
		executor.executeScript(dropdownScript);
		Thread.sleep(2000);

		String clickScript = "if (" + "\"createEvent\"" + " in document) {var evt = document.createEvent("
				+ "\"HTMLEvents\"" + ");     evt.initEvent(" + "\"change\"" + ", false, true); " + dropDown
				+ ".dispatchEvent(evt); } else " + dropDown + ".fireEvent(" + "\"onchange\"" + ");";

		executor.executeScript(clickScript);
	}

	public static boolean assertDevoidOnAttribute(By by, String text, String Attribute) {
		try {
			WebElement elem = findElement(by);
			String attrbdata = elem.getAttribute(Attribute);
			try {
				moveToElementNoLog(elem);
			} catch (Exception e) {

			}
			if (!attrbdata.contains(text)) {
				TestReport.log(LogStatus.PASS, "Text is not populated in the Attribute");
				return true;
			} else {
				TestReport.log(LogStatus.FAIL, "Text is populated in the Attribute");
				Assert.fail();
				return false;
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean assertDevoidOnAttributeStr(By by, String text, String Attribute) {
		try {
			WebElement elem = findElement(by);
			String attrbdata = elem.getAttribute(Attribute);
			try {
				moveToElementNoLog(elem);
			} catch (Exception e) {

			}
			if (attrbdata.contains(text)) {
				TestReport.log(LogStatus.PASS, "Text is populated in the Attribute");
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			TestReport.log(LogStatus.FAIL, "Error in accessing Text : " + e.getMessage());
			Assert.fail();
		}
		return false;
	}

	public static boolean isSelected(By by) {
		boolean flag = false;
		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			flag = element.isSelected();
			TestReport.log(LogStatus.INFO, "Value is selected");
			return flag;
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Value is not selected");
			Assert.fail();
			return flag;
		}
	}

	public static boolean isSelected(WebElement webElement) {
		boolean flag = false;
		try {
			// WebDriver driver = DriverFactory.getCurrentDriver();
			flag = webElement.isSelected();
			TestReport.log(LogStatus.INFO, "Value is selected");
			return flag;
		} catch (Exception E) {
			TestReport.log(LogStatus.FAIL, "Value is not selected");
			Assert.fail();
			return flag;
		}
	}

	public static boolean JSisEnabled(String button) {
		driver = DriverFactory.getCurrentDriver();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		System.out.println("Inside JS");
		String buttonenabled = "var select = window.document.getElementByXPath('" + button + "').disabled;";
		System.out.println(button);
		Boolean response = (Boolean) executor.executeScript(buttonenabled);
		if (response == true) {
			System.out.println("inside if");
			TestReport.log(LogStatus.INFO, "Button is Disabled");
			return response;
		} else {
			System.out.println("inside else");
			TestReport.log(LogStatus.INFO, "Button is Enabled");
			return response;
		}
	}

	public static void SwitchFrameByIDName(String IDName) {

		try {
			driver = DriverFactory.getCurrentDriver();
			driver.switchTo().frame(IDName);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public static void SwitchFrameByWebElement(By by) {

		try {

			driver = DriverFactory.getCurrentDriver();

			WebElement element = findElement(by);
			driver.switchTo().frame(element);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	public static void SwitchToDefaultWebPage() {

		try {
			driver = DriverFactory.getCurrentDriver();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public static void SetAttribute(By by, String attr_name, String attr_value) {

		try {
			driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, "attr_name",
					"attr_value");
			TestReport.log(LogStatus.INFO, "Value entered in attribute");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	public static void Action_sendkeys(By by, String Value) {

		try {
			driver = DriverFactory.getCurrentDriver();

			WebElement element = findElement(by);

			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.click();
			actions.sendKeys(Value);
			actions.build().perform();
			TestReport.log(LogStatus.INFO, "Value entered in attribute");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void alert_Accept() {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			TestReport.log(LogStatus.INFO, "Accepted the alert");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized String alert_getText() {
		String text=null;
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent());
			 text = driver.switchTo().alert().getText();
			TestReport.log(LogStatus.INFO, "Accepted the alert");
			return text;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	public static synchronized void alert_Dismiss() {
		try {
			driver = DriverFactory.getCurrentDriver();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().dismiss();
			TestReport.log(LogStatus.INFO, "Dismissed the alert");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized boolean isAlertPresent() {
		try {
			driver = DriverFactory.getCurrentDriver();
			driver.switchTo().alert();
			TestReport.log(LogStatus.INFO, "Switched to Alert");
			return true;
		} catch (NoAlertPresentException e) {
			Assert.fail();
			return false;
		}
	}

	public static void waitForAngular() {
		WebDriver driver = DriverFactory.getCurrentDriver();

		final String script = "var callback = arguments[arguments.length - 1];\n" + "var rootSelector = \'body\';\n"
				+ "var el = document.querySelector(rootSelector);\n" + "\n" + "try {\n" + "    if (angular) {\n"
				+ "        window.angular.getTestability(el).whenStable(callback);\n" + "    }\n" + "    else {\n"
				+ "        callback();\n" + "    }\n" + "} catch (err) {\n" + "    callback(err.message);\n" + "}";

		((JavascriptExecutor) driver).executeAsyncScript(script, new Object[0]);
	}

	public static void scrollToJs(By by) {
		try {
			WebDriver driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static boolean ischecked(By by) {
		boolean b = false;
		try {
			// WebDriver driver = DriverFactory.getCurrentDriver();
			WebElement element = findElement(by);
			String str = element.getAttribute("checked");
			System.out.println("inside ischeked");
			if (str.equalsIgnoreCase("true")) {
				System.out.println("Checkbox selected");
				b = true;
				return b;
			} else {
				b = false;
				return b;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return b;
	}

	public static Boolean IsdisplayedElem(By LocatorValue) {
		Boolean ObjectExist = null;
		driver = DriverFactory.getCurrentDriver();

		if (driver.findElement(LocatorValue).isDisplayed()) {
			ObjectExist = true;
			System.out.println("is displaying");
			return ObjectExist;
		} else {
			ObjectExist = false;
			return ObjectExist;
		}
	}

	public static boolean verifyElementAbsent(By locator) throws Exception {
		try {
			driver = DriverFactory.getCurrentDriver();
			driver.findElement(locator);
			System.out.println("Element Present");
			return false;
		} catch (NoSuchElementException e) {
			System.out.println("Element absent");
			Assert.fail();
			return true;
		}
	}

	public static String getCurrentURL() {
		try {
			driver = DriverFactory.getCurrentDriver();
			return driver.getCurrentUrl();
		} catch (Exception e) {
			Assert.fail();
			return null;
		}
	}

	public static void navigateBack() {
		try {
			driver = DriverFactory.getCurrentDriver();
			driver.navigate().back();
		} catch (Exception e) {
			return;
		}
	}

	public static void refresh() {
		try {
			driver = DriverFactory.getCurrentDriver();
			driver.navigate().refresh();
		} catch (Exception e) {
			return;
		}
	}

	public static void writeAccProperty(String TestScenarioID, String AccNO) {
		Properties prop = new Properties();
		InputStream input = null;
		FileOutputStream fr = null;
		String filename = "src/main/resources/AccountInfo.properties";
		try {
			input = new FileInputStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}
			prop.load(input);
			prop.setProperty(TestScenarioID, AccNO);
			fr = new FileOutputStream(filename);
			prop.store(fr, "Storing Account Number Into Property File.");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void mouseClick() {
		try {
			Actions actions = new Actions(DriverFactory.getCurrentDriver());
			actions.moveByOffset(100, 150).click().build().perform();
			actions.moveByOffset(0, 0).build().perform();
			TestReport.log(LogStatus.INFO, "Element successfully clicked in the page");
		} catch (Exception E) {
			E.printStackTrace();
			TestReport.log(LogStatus.ERROR, "Elements not located in the page");
			Assert.fail();
			DriverFactory.getCurrentDriver().quit();
		}
	}

	public static void driverClose() {
		driver = DriverFactory.getCurrentDriver();
		driver.close();
	}
}
