package Day5;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

@Listeners
public class myListner implements ITestListener {
	public void onStart(ITestContext context){
		System.out.println("this is on start");
	}
	public void onTestStart(ITestResult result) {
		System.out.println("this is on test start");
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("this is on test success");
	}
	public void onTestFailure(ITestResult result) {
		System.out.println("this is on test failure");
	}
	
	public void onTestSkipped(ITestResult result) {
		System.out.println("this is on test skip");
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("this is on test finish");
	}
	
	
}
