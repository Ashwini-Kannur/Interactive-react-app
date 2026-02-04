package Day5;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReportPage implements ITestListener{
	public ExtentSparkReporter sparkReporter;//UI of the report
	public ExtentReports extent; //populates common information on the report in statement like format
	public ExtentTest test; //creating test case entries in the report and update the status of the test methods
	
	public void onStart(ITestContext context) {
		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("OS", "Windows");
	}
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName()); //creates new entry in the report
		test.log(Status.PASS,"Test Case Passed is"+ result.getName()); //update status pass/fail
		
	}
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case Failed is"+ result.getName());
	}
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case Skipped is"+ result.getName());
		
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
