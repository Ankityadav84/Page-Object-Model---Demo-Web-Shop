package common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod, ChromeDriver driver) 
	{
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
