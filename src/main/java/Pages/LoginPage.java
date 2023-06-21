package Pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utils.Base;

public class LoginPage extends Base {

	By emailId_xpath = By.xpath("//input[@type='email']");
	By emailId_NextButton_xpath = By.xpath("//input[@type='submit']");
	By password_xpath = By.name("passwd");
	By signInButton_xpath = By.id("idSIButton9");
	By staySignedInButton_xpath = By.xpath("//input[@value='Yes']");

	public void loginPage() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(emailId_xpath)).sendKeys(properties.getProperty("emailId"));
		wait.until(ExpectedConditions.elementToBeClickable(emailId_NextButton_xpath)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(password_xpath))
				.sendKeys(properties.getProperty("password"));
		TimeUnit.SECONDS.sleep(1);
		wait.until(ExpectedConditions.elementToBeClickable(signInButton_xpath)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(staySignedInButton_xpath)).click();
	}
}
