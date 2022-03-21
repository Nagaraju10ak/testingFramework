package com.cib.stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.cib.framework.DriverFactory;
import com.cib.framework.TestReport;

public class Hook {

	Scenario scenario;
	@Before
	public void setup(Scenario scenario){
		this.scenario=scenario;
	}
	
	@After
	public void tearDown(){
		if(scenario.isFailed()){
			scenario.log(scenario.getName());
			scenario.attach(((TakesScreenshot)DriverFactory.getCurrentDriver()).getScreenshotAs(OutputType.BYTES), "", "image/jpeg");
			
		}else{
			scenario.attach(((TakesScreenshot)DriverFactory.getCurrentDriver()).getScreenshotAs(OutputType.BYTES), "", "image/jpeg");
			
		}
		TestReport.appendParent();
		TestReport.endParent();
		TestReport.endReport();
		DriverFactory.getCurrentDriver().quit();
	
	}
}