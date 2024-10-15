package login;

import base.BaseTests;
import base.UtilityTests;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.CheckboxesPage;
import pages.FileUploadPage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUploadTests extends BaseTests {
    FileUploadPage fileUploadPage = new FileUploadPage();

    @Test
    public void testFileUpload() throws FileNotFoundException, IOException, ParseException, InterruptedException {
        driver.get("http://formy-project.herokuapp.com/");

        UtilityTests.testTitle("File upload");
        fileUploadPage.clickFileUploadBtn();
        fileUploadPage.uploadFile();
        fileUploadPage.resetUpload();

    }
}
