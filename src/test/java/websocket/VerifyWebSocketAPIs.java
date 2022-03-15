package websocket;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import report.ExtentTestManager;

import java.util.NoSuchElementException;

public class VerifyWebSocketAPIs {
    SocketServiceData context;

    @BeforeTest
    public void createContext(){
        context=new SocketServiceData();
        context.URI = "wss://demo.piesocket.com/v3/channel_1?api_key=oCdCMcMPQpbvNjUIzqtvF1d2X2okWpDQj4AwARJuAgtjhzKxVEjQU6IdCjwm&notify_self";
        context.timeOut=5;
        context.expectedMessage="This is a test";
        context.actualMessage="This is a test";
    }

    @Test
    public void verifyWebSocketAPI()
    {
        ExtentTestManager.getTest().setDescription("Verify the user is able to run the verifyWebSocketAPI");
        ExtentTestManager.getTest().assignAuthor("Balaji G");
        ExtentTestManager.getTest().assignCategory("Smoke");
        try
        {
            SocketServiceData responseContext=WebClient.getInstance().connectAndListen(context);
            Assert.assertEquals(responseContext.statusCode,1000,"Status code is different");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Status code is 1000");
        }
        catch(Exception e)
        {
            ExtentTestManager.getTest().log(LogStatus.FAIL, e.getMessage());
        }
        ExtentTestManager.endTest();
    }

    @Test
    public void verifyWebSocketAPI_TimeOutsAutomatically(){
        ExtentTestManager.getTest().setDescription("Verify the user is able to run the verifyWebSocketAPI_TimeOutsAutomatically");
        ExtentTestManager.getTest().assignAuthor("Balaji G");
        ExtentTestManager.getTest().assignCategory("Regression");
        try
        {
            context.actualMessage="Invalid message";
            SocketServiceData responseContext=WebClient.getInstance().connectAndListen(context);
            Assert.assertEquals(responseContext.statusCode,1006,"Status code is different");
            ExtentTestManager.getTest().log(LogStatus.PASS, "Status code is 1006");
        }
        catch(Exception e)
        {
            ExtentTestManager.getTest().log(LogStatus.FAIL, e.getMessage());
        }
        ExtentTestManager.endTest();
    }
}