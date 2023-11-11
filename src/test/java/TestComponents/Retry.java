package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int maxrun = 4;
	int count = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (count <= maxrun) {
			count++;
			return true;
		} else {
			return false;
		}

	}

}
