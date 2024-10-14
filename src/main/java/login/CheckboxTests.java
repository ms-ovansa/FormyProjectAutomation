package login;

import base.BaseTests;
import base.UtilityTests;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.CheckboxesPage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CheckboxTests extends BaseTests {

    CheckboxesPage checkboxesPage = new CheckboxesPage();
    @Test
    public void testCheckboxes() throws FileNotFoundException, IOException, ParseException {
        driver.get("http://formy-project.herokuapp.com/");

        UtilityTests.testTitle("Verify that checkboxes can be selected");
         checkboxesPage.clickCheckBoxBtn();
        checkboxesPage.clickCheckbox();
        checkboxesPage.unClickCheckbox();

    }
}
