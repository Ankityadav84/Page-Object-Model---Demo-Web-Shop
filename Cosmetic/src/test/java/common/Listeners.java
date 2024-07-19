package common;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import utilities.screenshotUtil;

public class Listeners extends screenshotUtil implements ITestListener {

	// Methods -
	public void onTestStart(ITestResult result) {
		System.setProperty("org.uncommons.reportng.title","Selenium Report");
		Reporter.log("Method name is = " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		Reporter.log("Status of execution is = " + result.getStatus());
	}
	
	public void onTestFailure(ITestResult result) {					
		System.out.println("Test failed - screenshot captured");
		try {
			String screenshotfilename = getScreenshot();
			 System.setProperty("org.uncommons.reportng.escape-output", "false");
		        
		        // DYNAMIC PATH-
		        Reporter.log("<a href="+System.getProperty("user.dir").replace(" ", "_")+"/screenshot/"+screenshotfilename+".png"+">Fail Test Results</a>");
		        System.out.println("Report Generated");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
        // STATIC PATH -
        // Reporter.log("<a href="+"C:/Users/Cloud%20Analogy/OneDrive/Testing/MaryKay/screenshot/"+screenshotfilename+".png"+">Fail Test Results</a>");
    }

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}
