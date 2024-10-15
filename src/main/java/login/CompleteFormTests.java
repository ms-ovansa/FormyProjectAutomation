package login;

import base.BaseTests;
import base.UtilityTests;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.CompleteFormPage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CompleteFormTests extends BaseTests {
    CompleteFormPage completeFormPage = new CompleteFormPage();

    @Test
    public void testCompleteForm() throws FileNotFoundException, IOException, ParseException {
        driver.get("http://formy-project.herokuapp.com/");

        UtilityTests.testTitle("Fill user form");
        completeFormPage.clickCompleteFormBtn();
        completeFormPage.fillForm();
        completeFormPage.clickSubmitBtn();
    }
}
