package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Utils.Base;
import Utils.ExcelUtil;

public class TruTime extends Base {

	private ArrayList<String> trutimeDateList, systemDates;

	private LocalDate currentDate_system;
	private DateTimeFormatter dateFormat;
	private By trutimedates_xpath = By.xpath("//div[@class='weekContainer']//div[contains(@class,'dayHeadr')]");
	private By lastTopUpDate_xpath = By.xpath("/html/body/main/div/div/div[1]/div[1]/div[5]/div[1]/div[2]/p/span");
	private By searchField_xpath = By.xpath("//input[@placeholder='Search this site...']");
	private By resultTruTime_xpath = By.xpath("//div[@class='appsResultText' and text()='TruTime ']");
	private By oneCognizantLink = By.xpath("//div[@title='OneCognizant']");

	// check week details in TruTime with system calendar
	// check current date is present in the TruTime
	// check month and year is correct
	// check last topUp date is correct

	public void scroll() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		handler(0);
		Actions actions = new Actions(driver);
		String scrollXpath = "//div[@data-automation-id='CanvasLayout']/div[2]";
		actions.moveToElement(driver.findElement(By.xpath(scrollXpath))).perform();
		wait.until(ExpectedConditions.elementToBeClickable(oneCognizantLink)).click();

	}

	public void searchTruTime() throws InterruptedException, IOException {
		handler(1);
		int maximumNoOfTries = 1;		
		try {
			WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchField_xpath));
			search.sendKeys("TruTime");
			TimeUnit.SECONDS.sleep(2);
			screenshot("Tru Time search");
			wait.until(ExpectedConditions.elementToBeClickable(resultTruTime_xpath)).click();
		} catch (TimeoutException e) {
			maximumNoOfTries++;
			if (maximumNoOfTries == 3)
				throw new NoSuchElementException();

			driver.navigate().refresh();
			searchTruTime();
		}
	}

	public void getDaysFromTrutime() throws IOException, InvalidFormatException, AWTException {
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"appFrame\"]")));
		List<WebElement> tempDateList = driver.findElements(trutimedates_xpath);
		trutimeDateList = new ArrayList<String>();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);

		for (WebElement date : tempDateList)
			trutimeDateList.add(date.getText());
	}

	public void getDaysFromLocalSys() {
		DayOfWeek monday = DayOfWeek.MONDAY;
		currentDate_system = LocalDate.now();
		dateFormat = DateTimeFormatter.ofPattern("EE, dd MMM");
		systemDates = new ArrayList<String>();
		LocalDate daysOfTheWeek = null;
		String day = currentDate_system.getDayOfWeek().toString();
		for (int i = 0; i < 7; i++) {
			// If today is Sunday, it will give previous week details because starting day
			// of the week is Monday.
			if (day.equals("SUNDAY")) {
				daysOfTheWeek = currentDate_system.with(monday).plusDays(i - 1).plusWeeks(1);
				systemDates.add(daysOfTheWeek.format(dateFormat));
			} else {
				daysOfTheWeek = currentDate_system.with(monday).plusDays(i - 1);
				systemDates.add(daysOfTheWeek.format(dateFormat));
			}
		}
	}

	public void compareDates() throws InterruptedException, IOException {
		System.out.println("VALIDATE TRUTIME WEEK DETAILS WITH SYSTEM CALENDAR\n");
		System.out.println("== TruTime Dates =========== System Calendar ==");

		for (int i = 0; i < trutimeDateList.size(); i++)
			System.out.println("    " + trutimeDateList.get(i) + "               " + systemDates.get(i));

		System.out.println(trutimeDateList.equals(systemDates)
				? "\nThe dates on both Trutime and the local system are consistent for the entire week. \n"
				: "\nThe dates on both Trutime and the local system are not consistent for the entire week. \n");
		System.out.println("===============================================");
		ExcelUtil.write(trutimeDateList, systemDates);
	}

	public void checkCurrentDate() {
		System.out.println("VALIDATE CURRENT DATE, MONTH & LAST TOPUP DATE IN THE TRUTIME APPLICATION \n");
		// Current Date is present
		boolean checkCurrentDateIsDisplayed = driver
				.findElement(By.xpath("//div[text()='" + currentDate_system.format(dateFormat) + "']")).isDisplayed();
		System.out.println(checkCurrentDateIsDisplayed ? "Current date is Present in the TruTime Application"
				: "Current date is not present in the TruTime Application");

		// Current Month and Year is present
		String truTimeCurrentMonthAndYear = driver.findElement(By.xpath("//*[@class='ui-datepicker-title']")).getText();
		String systemCalendarMonthAndYear = currentDate_system.getMonth() + " " + currentDate_system.getYear();
		boolean checkCurrentMonthAndYearIsPresent = truTimeCurrentMonthAndYear
				.equalsIgnoreCase(systemCalendarMonthAndYear);
		System.out.println(
				checkCurrentMonthAndYearIsPresent ? "Current Month And Year is Present in the TruTime Application"
						: "Current Month And Year is not Present in the TruTime Application");
	}

	public void validateLastTopUpDate() {
		String lastTopUpDate = driver.findElement(lastTopUpDate_xpath).getText();
		boolean validate_lastTopUpDate = lastTopUpDate.equals(currentDate_system.minusDays(15).format(dateFormat));
		System.out.println(validate_lastTopUpDate ? "TruTime Backdated Topup date is Present and verified."
				: "LocalBackdated topup and trutime backdated topup dates are not equal");
		System.out.println("\n===============================================");
	}

}
