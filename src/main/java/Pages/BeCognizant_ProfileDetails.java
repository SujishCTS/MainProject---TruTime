package Pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utils.Base;

public class BeCognizant_ProfileDetails extends Base {

	private String name, role, location, mailId, nativeLocation;

	private By profileIcon_xpath = By.id("O365_MainLink_MePhoto");
	private By viewAccount_xpath = By.xpath("//*[text()='View account']");
	private By feedBackCancelButton_xpath = By.xpath("//span[text()='Cancel']");
	private By profileName_xpath = By.xpath("//div[@class='ms-tileTitle jp5gtNMEAE13AiVWiqfE ms-pii']");
	private By profileRole_xpath = By.xpath("//div[@class='Ad9lhGsItMv_g43JPFJP']//div[3]/div[1]");
	private By profileLocation_xpath = By.xpath("//div[@class='Ad9lhGsItMv_g43JPFJP']//div[3]/div[2]");
	private By profileNativeLocation_xpath = By.xpath(
			"//div[@class='ms-section HE7iQW1JeMW1uDbkMnOF']/div[3]/div[@class='q8Zr7zQAYPz8xWyrl891']/div[@class='i4QYME4ny7lt2dMvj9YG']");
	private By profileEmailId_xpath = By.xpath(
			"//div[@class='ms-section HE7iQW1JeMW1uDbkMnOF']/div[1]/div[@class='q8Zr7zQAYPz8xWyrl891']/div[@class='i4QYME4ny7lt2dMvj9YG']");

	public void becognizant() throws InterruptedException {
		TimeUnit.SECONDS.sleep(2);
		wait.until(ExpectedConditions.elementToBeClickable(profileIcon_xpath)).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(viewAccount_xpath))).click();
		handler(1);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(feedBackCancelButton_xpath)).click();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void detailsGetText() throws InterruptedException, IOException {
		name = wait.until(ExpectedConditions.presenceOfElementLocated(profileName_xpath)).getText();
		role = wait.until(ExpectedConditions.presenceOfElementLocated(profileRole_xpath)).getText();
		location = wait.until(ExpectedConditions.presenceOfElementLocated(profileLocation_xpath)).getText();
		mailId = wait.until(ExpectedConditions.presenceOfElementLocated(profileEmailId_xpath)).getText();
		nativeLocation = wait.until(ExpectedConditions.presenceOfElementLocated(profileNativeLocation_xpath)).getText();

		printProfileDetails();
		screenshot("user information");
		TimeUnit.SECONDS.sleep(5);
		driver.close();
	}

	private void printProfileDetails() throws IOException, InterruptedException {
		System.out.println("ACCOUNT DETAILS :\n");
		System.out.println("Name - " + name);
		System.out.println("Role - " + role);
		System.out.println("Email Id - " + mailId);
		System.out.println("Base Location - " + location);
		System.out.println("Native Location - " + nativeLocation);
		System.out.println("\n===============================================");
		screenshot("user information");
	}
}
