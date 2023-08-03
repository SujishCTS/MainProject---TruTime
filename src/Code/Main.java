package Code;
import java.io.IOException;
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        EduVidya obj = new EduVidya();
        obj.driverSetup();
        obj.openUrl();
        obj.clickOnSchoolsLink();
        obj.selectCourseType();
        obj.selectCity();
        obj.clickOnSearchButton();
        obj.verifySearchResults();
        //obj.closeDriver();
    }
}
