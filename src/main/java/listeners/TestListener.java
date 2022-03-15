package listeners;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import report.ExtentManager;
import report.ExtentTestManager;

public class TestListener implements ITestListener
{
    public void onTestSuccess(ITestResult iTestResult)
    {
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
    }

    @SuppressWarnings({ "rawtypes", "static-access" })
    public void onTestFailure(ITestResult iTestResult)
    {
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test case failed due to - "+iTestResult.getThrowable().getMessage());
    }

    public void onTestSkipped(ITestResult iTestResult)
    {
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped - " +iTestResult.getThrowable().getMessage());
    }

    public void onTestStart(ITestResult iTestResult)
    {
        ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult)
    {}

    public void onStart(ITestContext iTestContext)
    {}

    public void onFinish(ITestContext iTestContext)
    {
        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();
    }
}