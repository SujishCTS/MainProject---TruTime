package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Base {

	public static RemoteWebDriver driver;
	public static WebDriverWait wait;
	public static Properties properties;

	public void setDriver() throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\properties\\Config.properties"));
		chromeDriverSetup();
		openUrl();
	}

	private void chromeDriverSetup() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	private void openUrl() throws IOException {
		driver.get(properties.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
	}

	public void handler(int windowIndex) {
		Set<String> windowHandling = driver.getWindowHandles();
		ArrayList<String> window = new ArrayList<String>(windowHandling);
		driver.switchTo().window(window.get(windowIndex));
	}

	public void screenshot(String imageName) throws IOException, InterruptedException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotfile = screenshot.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshotfile,
				new File(System.getProperty("user.dir") + "\\Screenshot\\" + imageName + ".png"));
	}

	public void driverClose() throws InterruptedException, IOException {
		TimeUnit.SECONDS.sleep(5);
		driver.quit();
	}
	
	
	
}
