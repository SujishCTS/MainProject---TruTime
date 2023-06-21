package Test;

import java.io.IOException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import Pages.TruTime;
import Pages.BeCognizant_ProfileDetails;
import Pages.LoginPage;

public class TestCases {
	private BeCognizant_ProfileDetails beCognizantPage = new BeCognizant_ProfileDetails();
	private LoginPage loginPage = new LoginPage();
	private TruTime truTimePage = new TruTime();
	public static ExtentReports extendreports;
	public static ExtentTest extendTest;
	
	@Test(priority = 1)
	public void setDriverMethod() throws IOException{
		loginPage.setDriver();
	    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
	    System.getProperty("user.dir") + "\\reports\\extentReport.html");
	    htmlReporter.config().setDocumentTitle("TruTime Report");
	    htmlReporter.config().setReportName("Test report");
	    extendreports = new ExtentReports();
	    extendreports.attachReporter(htmlReporter);
	}
	
	@Test(priority = 2)
	public void loginPageMethod() throws Exception {
		extendTest = extendreports.createTest("Module:1 login page ");
	    try {
	    	loginPage.loginPage();
	        extendTest.log(Status.INFO, "Logged In Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 3)
	public void becognizantMethod() throws Exception {
		extendTest = extendreports.createTest("Module:2 Retrieve User details ");
	    try {
	    	beCognizantPage.becognizant();
	        extendTest.log(Status.INFO, "Navigate to user detail page successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}

	@Test(priority = 4)
	public void detailsGetTextMethod() throws Exception {
	    try {
	    	beCognizantPage.detailsGetText();
	        extendTest.log(Status.INFO, "Retrieved the user details Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 5)
	public void scrollMethod() throws Exception {
		extendTest = extendreports.createTest("Module:3 Validate Trutime details");
	    try {
	    	truTimePage.scroll();
	        extendTest.log(Status.INFO, "Navigate to One cognizant page Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 6)
	public void searchTruTimeMethod() throws Exception {
	    try {
	    	truTimePage.searchTruTime();
	        extendTest.log(Status.INFO, "Search for Trutime Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 7)
	public void getDaysFromLocalSysMethod() throws Exception {
	    try {
			truTimePage.getDaysFromLocalSys();
	        extendTest.log(Status.INFO, "Get Days from LocalSystem Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 8)
	public void getDaysFromTrutimeMethod() throws Exception {
	    try {
			truTimePage.getDaysFromTrutime();
	        extendTest.log(Status.INFO, "Get Days from Trutime Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 9)
	public void compareDatesMethod() throws Exception {
	    try {
			truTimePage.compareDates();
	        extendTest.log(Status.INFO, "Compared the Dates from truetime and localsystem Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 10)
	public void checkCurrentDateMethod() throws Exception {
	    try {
			truTimePage.checkCurrentDate();
	        extendTest.log(Status.INFO, "Checked the current date Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}
	
	@Test(priority = 11)
	public void validateLastTopUpDateMethod() throws Exception {
	    try {
			truTimePage.validateLastTopUpDate();
	        extendTest.log(Status.INFO, "Validate the last top-up date Successfully");
	    } catch (Exception e) {
	        extendTest.log(Status.FAIL, e.getMessage());
	        throw new Exception(e);
	    }
	}

	@Test(priority = 12)
	public void driverClose() throws InterruptedException, IOException {
	    extendreports.flush();
	    truTimePage.driverClose();
	}

}
