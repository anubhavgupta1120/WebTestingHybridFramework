package TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReport;

public class Listeners extends BaseTest implements ITestListener {
	ExtentReports reports = ExtentReport.createExtentReport(
			"/Users/anubhavgupta/Documents/eclipse-workspace(Java)/WebTesting_Hybrid_Framework/Reports/Index.html",
			"Anubhav Gupta", "Web UI Testing");
	ExtentTest test;
	WebDriver driver;
	
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();  // Thread safe -- To manage the concurrency issue when we
																// run Test on parallel executions


	@Override
	public void onTestStart(ITestResult result) {
		
		test = reports.createTest(result.getMethod().getMethodName());
		threadLocal.set(test); // will give unique thread id to Each Test Object
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		threadLocal.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		threadLocal.get().fail(result.getThrowable());
		//ScreenShot
		
		String pathString = "";
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			pathString = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		threadLocal.get().addScreenCaptureFromPath(pathString, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		reports.flush();
	}

}
