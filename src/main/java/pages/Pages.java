package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.Concise.getDriver;

public abstract class Pages {
    protected static final Logger logger = Logger.getLogger(Pages.class);

    private String urlBase;

    protected boolean waitUntilPageLoaded() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), 60);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");
                }
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean containsTitle(String title) {
        try {
            getDriver().getTitle().contains(title);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public static void hover(WebElement element) {
        actions().moveToElement(element).perform();
    }

    public static Actions actions() {
        return new Actions(getDriver());
    }
}
