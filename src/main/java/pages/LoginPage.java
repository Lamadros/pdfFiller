package pages;

import core.Helpers;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static core.Helpers.find;

public class LoginPage extends Pages {
    private String title = "Log In - PDFfiller";
    private By loginName = By.id("form-login-email");
    private By pass = By.id("form-login-password");
    private By loginBlock = By.id("login-block-form");
    private By submitButton = By.id("form-login-submit");
    private String url = "/en/login.htm";

    @Step("Сheck that the page is loaded")
    public void pageLoad() {
        waitUntilPageLoaded();
        containsTitle(title);
    }

    @Step("Login as {0}, with pass {1}")
    public void login(String username, String password) {
        find(loginName).sendKeys(username);
        find(pass).sendKeys(password);
        find(submitButton).click();
    }

    @Step("Open Login Page")
    public void open() {
        Helpers.open(url);
        pageLoad();
    }
}
