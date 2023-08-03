package Code;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class EduVidya extends Driver {
    public void clickOnSchoolsLink() throws InterruptedException {
        WebElement schoolsLink = driver.findElement(By.linkText("Schools"));
        if (schoolsLink != null) {
            schoolsLink.click();
            System.out.println("\nStep 3: Clicked on \"Schools\" link in the Menu bar");
            TimeUnit.SECONDS.sleep(2);
        } else {
            System.out.println("\nStep 3: Schools link not found.");
        }
        handlingAds();
    }

    public void selectCourseType() throws InterruptedException {
            TimeUnit.SECONDS.sleep(5);
            driver.findElement(By.id("ddl_Category")).click();
            Select categoryDropdown = new Select(driver.findElement(By.id("ddl_Category")));
            categoryDropdown.selectByVisibleText("CBSE");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("\nStep 4: Clicked on \"Course-Type\" dropdown and select “CBSE” from the list.");
    }

    public void selectCity() throws InterruptedException {
        driver.findElement(By.id("ddl_City")).click();
        Select cityDropdown = new Select(driver.findElement(By.id("ddl_City")));
        cityDropdown.selectByVisibleText("Pune");
        System.out.println("\nStep 5: Clicked on \"City\" dropdown and select “Pune” from the list.");
        TimeUnit.SECONDS.sleep(2);
    }

    public void clickOnSearchButton() throws InterruptedException {
        driver.findElement(By.id("btnSearch")).click();
        System.out.println("\nStep 6: Clicked on \"Search\" button.");
        TimeUnit.SECONDS.sleep(2);
    }

    public void verifySearchResults() {
        if (driver.getPageSource().contains("Schools Search"))
            System.out.println("\nStep 7: Search results are displayed.");
        else
            System.out.println("\nStep 7: Search results are not displayed");
    }

   // public void closeDriver() {
        //    driver.quit();
       // System.out.println("\nStep 8: Browser is closed.");
    //}
}

