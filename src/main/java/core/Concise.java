package core;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class Concise {
    private static Map<Thread, WebDriver> webDriverMap = new HashMap<>();
    private static String baseUrl;

    public static WebDriver getDriver() {
        return webDriverMap.get(Thread.currentThread());
    }

    public static void setDriver(WebDriver driver) {
        webDriverMap.put(Thread.currentThread(), driver);
    }

    public static void setBaseUrl(String baseUrl) {
        Concise.baseUrl = baseUrl;
    }

    public static String getBaseUrl() {

        return baseUrl;
    }
}
