package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static core.Concise.getBaseUrl;
import static core.Concise.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Helpers {

    @Step("Open {0}")
    public static void open(String url) {

        getDriver().get(getBaseUrl() + url);
    }

    public static WebElement find(By elementLocator) {
        return toWaitElement(visibilityOfElementLocated(elementLocator));
    }

    private static WebElement toWaitElement(ExpectedCondition<WebElement> conditionToWaitElement) {
        return assertThat(conditionToWaitElement);
    }

    private static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    private static <V> V assertThat(ExpectedCondition<V> condition, long timeout) {
        return new WebDriverWait(getDriver(), timeout).until(condition);
    }

    public static void shouldBeClickable(By selector){
        WebDriverWait waitUntil = new WebDriverWait(getDriver(), Configuration.timeout);
        waitUntil.until(ExpectedConditions.elementToBeClickable(selector));
    }

    public static void waitUntilVisible(By selector){
        WebDriverWait waitUntil = new WebDriverWait(getDriver(), Configuration.timeout);
        waitUntil.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public static void waitUntilDisappeared( By selector) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Configuration.timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(selector));
    }

    public static void shouldBeVisible(By selector) {
        find(selector).isDisplayed();
    }

    @Step("Assert that names of documents in API and WEB are equals")
    public static void assertEquals(List v1, List v2) {
        Assert.assertTrue(v1.equals(v2));
    }

    @Step("Assert presented of element {1}")
    public static void assertContains(List v1, String v2) {

    }

    @Step("Assert equals of element.")
    public static void assertEquals(int v1, int v2) {
        Assert.assertEquals(v1, v2);
    }












}
