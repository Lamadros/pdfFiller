package testconfig;

import com.jayway.restassured.RestAssured;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.yandex.qatools.allure.annotations.Attachment;

import static api.ConciseAPI.setHeader;
import static core.Concise.*;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        setDriver(new FirefoxDriver());

        /*For Windows users:

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        setDriver(new ChromeDriver());*/
        setBaseUrl("https://www.pdffiller.com");
        setHeader("Authorization","Bearer 2HIiuvv4X4NMaDG09iXrg6IRmvFkAyPdBQuVR58Y");
        setHeader("Content-Type", "application/json");
        RestAssured.baseURI = "https://api.pdffiller.com";
        RestAssured.basePath = "/v1";

        getDriver().manage().window().maximize();

    }

    @AfterMethod
    public void ThreadDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot(byte[] dataForScreenshot) {
        return dataForScreenshot;
    }
}
