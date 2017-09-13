package tests;

import api.API;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.LoginPage;
import ru.yandex.qatools.allure.annotations.Title;
import testconfig.BaseTest;

import static core.Helpers.assertContains;
import static core.Helpers.assertEquals;

@Title("PdfFiller verification")
public class PdfFillerTest extends BaseTest {

    @Title("First Test")
    @Test
    public void test1() {

        LoginPage lp = new LoginPage();
        lp.open();
        lp.login("fxt80922@posdz.com", "q123456_");
        Dashboard dashboard = new Dashboard();
        dashboard.selectFolder("All Documents");
        API api = new API();
        assertEquals(dashboard.getDocumentsName(), api.document().getDocumentsName());
    }

    @Title("Second Test")
    @Test
    public void test2() {
        API api = new API();
        api.document().createDocumentFromUrl("https://www.irs.gov/pub/irs-pdf/fw9.pdf");
        LoginPage lp = new LoginPage();
        lp.open();
        lp.login("fxt80922@posdz.com", "q123456_");
        Dashboard dashboard = new Dashboard();
        dashboard.selectFolder("All Documents");
        assertContains(dashboard.getDocumentsName(), "fw9");
    }

    @Title("Third Test")
    @Test
    public void test3() {
        API api = new API();
        api.folder().createFolder("ZhgutTest");
        LoginPage lp = new LoginPage();
        lp.open();
        lp.login("fxt80922@posdz.com", "q123456_");
        Dashboard dashboard = new Dashboard();
        assertContains(dashboard.getListOfWebFolderName(), "ZhgutTest");
        dashboard.moveDocumentTo("Host a Fillable Form", "ZhgutTest");
        Assert.assertEquals(api.folder().getDocumentCount("ZhgutTest"),1);


    }
}
