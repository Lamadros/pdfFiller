package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

import static core.Concise.getDriver;
import static core.Helpers.*;

public class Dashboard extends Pages {

    private String url = "/forms.htm";
    private String title = "PDFfiller. On-line PDF form Filler, Editor, Type on PDF, Fill, Print, Email, Fax and Export";

    private By folders = By.cssSelector(".mf-sb-left div.mf-menuItem");
    private By documentsName = By.className("mf-doc__name");
    private By documentActions = By.className("mf-documents__action");
    private By activePopup = By.cssSelector(".modal-container.myforms-modal");
    private By folderListPopup = By.cssSelector(".modal-body .mf-menuItem__title");
    private By submitButtonPopup = By.cssSelector(".modal-container.myforms-modal .btn.-sm.-orange");
    private By folderName = By.className("mf-menuItem__title");
    private By countOfElementInFolder = By.className(".mf-counter");

    public Dashboard() {
        pageLoaded();
    }

    private enum Action {
        COPY("Copy"),
        RENAME("Rename"),
        MOVE_TO_TRASH("Move to Trash"),
        MOVE("Move"),
        TAG("Tag"),
        CLEAR("Clear"),
        TEMPLATE("Template"),
        ADD_BLANK_PAGES("Add Blank Pages"),
        MERGE("Merge"),
        TRANSFER_TO_ANOTHER_ACCOUNT("Transfer to another account"),
        REFRESH_DOC_LIST("Refresh your document list"),
        ROLE("Role Template");

        private String actionName;

        public String toString() {
            return actionName;
        }

        Action(String actionName) {
            this.actionName = actionName;
        }
    }

    public void pageLoaded() {
        waitUntilPageLoaded();
        getDriver().getCurrentUrl().contains(url);
        containsTitle(title);

    }

    @Step("Select folder {0}")
    public void selectFolder(String folderName) {
        select(folders, folderName);
        elementIsSelected(folderName);
    }

    private WebElement getWebElements(By locator) {
        WebElement elements = getDriver().findElement(locator);
        return elements;
    }

    private List<WebElement> getListOfElements(By locator) {
        List<WebElement> elements = getDriver().findElements(locator);
        return elements;
    }

    @Step("Assert {0} is selected.")
    void elementIsSelected(String elementName) {
        getWebElements(folders).findElement(By.xpath("//*[contains(text(),'" + elementName + "')]/parent::*")).getAttribute("class").contains("active");
    }

    @Step("Get documents name via WEB")
    public List<String> getDocumentsName() {
        List<String> documentName = new ArrayList<>();

        for (WebElement s : getListOfElements(documentsName)) {
            documentName.add(s.findElement(By.tagName("span")).getText());
        }

        return documentName;
    }

    public List<String> getListOfWebFolderName() {
        List<String> foldersName = new ArrayList<>();

        for (WebElement s : getListOfElements(folders)) {
            foldersName.add(s.findElement(folderName).getText());
        }

        return foldersName;
    }

    @Step("Move document - {0} to folder -  {1}")
    public void moveDocumentTo(String documentName, String FolderName) {
        selectDocument(documentName);
        madeActionOnSelectedDocument(Action.MOVE.toString());
        try {
            waitUntilVisible(activePopup);
        } catch (TimeoutException e) {
            madeActionOnSelectedDocument(Action.MOVE.toString());
        }
        selectFolderInPopup(folderListPopup, FolderName);
        elementIsSelected(FolderName);
        pressSubmitButton(submitButtonPopup);
        waitUntilDisappeared(activePopup);
    }

    private void pressSubmitButton(By locator) {
        find(locator).click();
    }

    private void select(By elementsLocator, String elementName) {
        searchText(elementsLocator, elementName).click();
    }

    private WebElement searchText(By elementsLocator, String elementName) {
        return getWebElements(elementsLocator).findElement(By.xpath("//*[contains(text(),'" + elementName + "')]"));
    }

    private WebElement searchExactText(By elementsLocator, String elementName) {
        return getWebElements(elementsLocator).findElement(By.xpath("//*[text()='" + elementName + "']"));
    }

    public void selectDocument(String documentName) {
        select(documentsName, documentName);
        elementIsSelected(documentName);
    }

    public void madeActionOnSelectedDocument(String actionName) {
        hover(searchExactText(documentActions, actionName));
        searchExactText(documentActions, actionName).click();
    }

    private void selectFolderInPopup(By elementsLocator, String elementName) {
        List<WebElement> test = getDriver().findElements(elementsLocator);
        for (WebElement s : test) {
            String name = s.getText();
            if (name.equals(elementName)) {
                s.click();
            }
        }
    }



}
