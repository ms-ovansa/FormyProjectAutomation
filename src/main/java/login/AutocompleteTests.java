package login;

import base.BaseTests;
import base.UtilityTests;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.AutocompletePage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AutocompleteTests extends BaseTests {


    AutocompletePage autocompletePage = new AutocompletePage();

    @Test
    public void testAutocomplete() throws FileNotFoundException, IOException, ParseException {
        UtilityTests.testTitle("Fill in user address details");
        autocompletePage.ClickAutoCompleteBtn();
        autocompletePage.FillAutocompleteForm();

    }
}
