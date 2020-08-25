package helper.Package;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
	
	SeleniumHelper sh = new SeleniumHelper();

	@Override
	public void onTestStart(ITestResult result) {
		
		System.out.println("Test case started. The results are" + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	
		System.out.println("Test case succeeded. The results are" + result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		System.out.println("Test case failed. The results are" + result.getName());
		SeleniumHelper.takeScreenShotMethod("Image_Failed"+sh.timestamp());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		System.out.println("Test case skipped. The results are" + result.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
 