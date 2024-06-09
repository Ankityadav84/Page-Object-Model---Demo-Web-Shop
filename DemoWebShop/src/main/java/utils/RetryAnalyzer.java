package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count = 0;
	int retryCount = 1;

	/*	NOTE -
	1. If we use BeforeMethod in Base class then retry analyzer open new chrome window.
	2. If we use BeforeTest in Base class then retry analyzer retry on same chrome window.*/
	public boolean retry(ITestResult result) {

		while(count<retryCount)
		{
			count++;
			return true;
		}

		return false;
	}
}
