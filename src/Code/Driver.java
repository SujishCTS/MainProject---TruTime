package Code;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver {
    static String url, chromeDriver, fireFoxDriver, edgeDriver;
    static WebDriver driver = null;

    public void driverSetup() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select browser to Run: 1.chrome 2.firefox 3.Edge:");
        int browser = scanner.nextInt();
        scanner.close();
        loadProperties();
        if (browser == 1) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
            System.setProperty("webdriver.chrome.driver", chromeDriver);
            driver = new ChromeDriver(options);
        } else if (browser == 2) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\2269321\\eclipse-workspace\\returnOfFinal\\src\\Drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser == 3) {
            System.setProperty("webdriver.edge.driver", edgeDriver);
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--disable-features=PreloadPages");
            driver = new EdgeDriver(options);
        } else {
            System.out.println("Invalid browser name!");
            System.exit(0);
        }
        System.out.println("\nStep 1: Browser is opened.");
    }

    public static void loadProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                "C:\\Users\\2269321\\eclipse-workspace\\returnOfFinal\\src\\config.properties");
        prop.load(fis);
        url = prop.getProperty("url");
        chromeDriver = prop.getProperty("chromeDriverPath");
        fireFoxDriver = prop.getProperty("fireFoxDriverPath");
        edgeDriver = prop.getProperty("edgeDriverPath");
    }

    public void openUrl(){
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        System.out.println("\nStep 2: https://www.eduvidya.com/ opened.");
    }

    public void handlingAds() {
        if (driver.getCurrentUrl().contains("#google_vignette")) {
            driver.switchTo().frame("aswift_3");
            driver.switchTo().frame("ad_iframe");
            WebElement closeAd = driver.findElement(By.xpath("//*[@id='dismiss-button']"));
            closeAd.click();
            driver.switchTo().defaultContent();
        }
    }
}
