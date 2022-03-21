package com.cib.framework;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestReport{

	private static ExtentReports extentD;
	private static ExtentReports extentDwss;

	public static ExtentTest testParent;
	public static ExtentTest testParentWss;
	public static String reportPath;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testwss = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<Boolean> isPassed = new ThreadLocal<Boolean>();

	public static int passCountD = 0;
	public static int failCountD = 0;
	public static int passCountM = 0;
	public static int failCountM = 0;

	public static Map<String, TestReport> classReportMap = new HashMap<String, TestReport>();

	public static void initReport(String suiteName) throws IOException {
		Date d=new Date();
		String filename=d.toString().replace(":", "_").replace(" ", "_")+".html";
		String filenamess=d.toString().replace(":", "_").replace(" ", "_")+"WithScreenshots"+".html";
		String folderName=suiteName+"_"+d.toString().replace(":", "_").replace(" ", "_");
		extentD = new ExtentReports(System.getProperty("user.dir")+"//Reports//"+folderName+"//"+filename, true,DisplayOrder.NEWEST_FIRST);
		extentDwss = new ExtentReports(System.getProperty("user.dir")+"//Reports//"+folderName+"//"+filenamess, true,DisplayOrder.NEWEST_FIRST);
		reportPath=System.getProperty("user.dir")+"//Reports//"+folderName;

	}

	//	public TestReport(String testName, String description, String suiteName) {
	//		initParent(testName, description, suiteName);
	//	}

	public TestReport(String testName) {
		initParent(testName);
	}

	public static void log(LogStatus logStatus, String stepName) {
		test.get().log(logStatus, stepName);
		String path = "";
		String screenPass = ProjectConfig.getPropertyValue("screenPass");
		String screenFail = ProjectConfig.getPropertyValue("screenFail");
		String screenWarn = ProjectConfig.getPropertyValue("screenWarn");

		switch (logStatus) {
		case PASS:
			if (screenPass.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(logStatus, stepName);
			break;
		case FAIL:
			if (screenFail.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(logStatus, stepName);
			isPassed.set(false);
			// 
			break;
		case INFO:
		case SKIP:
			testwss.get().log(logStatus, stepName);
			break;
		case WARNING:
			if (screenWarn.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(logStatus, stepName);
			break;
		case ERROR:
			if (screenFail.equals("true")) {
				path = CommonDef.captureScreen();
				testwss.get().log(LogStatus.FAIL, stepName + testwss.get().addScreenCapture(path));
			} else
				testwss.get().log(LogStatus.FAIL, stepName);
			isPassed.set(false);

			break;
		case UNKNOWN:
			isPassed.set(false);
			logStatus = LogStatus.FAIL;
			if (screenWarn.equals("true")) {
				path =  CommonDef.captureScreen();
				testwss.get().log(logStatus, stepName + testwss.get().addScreenCapture(path));
			} else {
				testwss.get().log(logStatus, stepName + " ");
			}

			break;
		case FATAL:
			isPassed.set(false);
			testwss.get().log(logStatus, stepName);
			// System.out.println(isPassed.get());

			break;
		default:
			break;
		}

	}

	public static void logNoScreen(LogStatus logStatus, String stepName) {
		test.get().log(logStatus, stepName);
		switch (logStatus) {
		case PASS: case SKIP: case WARNING: case INFO:
			testwss.get().log(logStatus, stepName);
			break;
		case FAIL:
			testwss.get().log(logStatus, stepName);
			isPassed.set(false);
			// 
			break;
		case ERROR:
			testwss.get().log(LogStatus.FAIL, stepName);
			isPassed.set(false);

			break;
		case UNKNOWN:
			isPassed.set(false);
			logStatus = LogStatus.FAIL;
			testwss.get().log(logStatus, stepName + " ");

			break;
		case FATAL:
			isPassed.set(false);
			testwss.get().log(logStatus, stepName);
			// System.out.println(isPassed.get());

			break;
		default:
			break;
		}

	}

	//	public void initParent(String testName, String description, String suiteName) {
	//			testParent = extentD.startTest(testName, description);
	//			testParentWss = extentDwss.startTest(testName, description);
	//		
	//	}

	public void initParent(String testName) {
		testParent = extentD.startTest(testName);
		testParentWss = extentDwss.startTest(testName);

	}

	public static void initTest(String testName, String desc) {

		isPassed.set(true);
		test.set(getextent().startTest(testName, desc));
		testwss.set(getextentwss().startTest(testName, desc));
	}

	public static void assignCatogory(String Catogory) {
		test.get().assignCategory(Catogory);
		testwss.get().assignCategory(Catogory);
	}

	public static void endParent() {
		extentD.endTest(testParent);
		extentDwss.endTest(testParentWss);
	}

	public static void endReport() {
		extentD.flush();
		extentDwss.flush();
		extentD.close();
		extentDwss.close();
	}

	private static ExtentReports getextent() {
		return extentD;
	}

	private static ExtentReports getextentwss() {
		return extentDwss;
	}

	public static void appendParent() {
		testParent.appendChild(test.get());
		testParentWss.appendChild(testwss.get());
		if (isPassed.get()) {
			passCountD = passCountD + 1;
		} else {
			failCountD = failCountD + 1;
		}
		test.remove();
		testwss.remove();
		isPassed.remove();
	}
}