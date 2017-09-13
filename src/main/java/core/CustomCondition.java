package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomCondition {

    public static ExpectedCondition<Boolean> textToBe(final By locator, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            public Boolean apply(WebDriver driver) {
                try {
                    this.currentValue = driver.findElement(locator).getText();
                    return this.currentValue.equals(value);
                } catch (Exception var3) {
                    return false;
                }
            }

            public String toString() {
                return String.format("text to be \"%s\". Current text: \"%s\"", value, this.currentValue);
            }
        };
    }

}
