package pages;

import base.BaseTests;
import base.UtilityTests;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static base.BaseTests.driver;

public class AutocompletePage extends BaseTests {
    Faker faker;


    By AutocompleteBtn = By.cssSelector("div > li:nth-child(5) > a");
    By fullAddress = By.id("autocomplete");
    By streetAddress = By.id("street_number");
    By streetAddressnumber = By.id("route");
    By city = By.id("locality");
    By state = By.id("administrative_area_level_1");
    By zipcode = By.id("postal_code");
    By country = By.id("country");

    public void ClickAutoCompleteBtn() {
        driver.findElement(AutocompleteBtn).click();
    }

    public void FillAutocompleteForm() {
        faker = new Faker();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete")));
        driver.findElement(fullAddress).sendKeys(faker.address().fullAddress());
        driver.findElement(streetAddress).sendKeys(faker.address().streetAddress());
        driver.findElement(streetAddressnumber).sendKeys(faker.address().streetAddressNumber());
        driver.findElement(city).sendKeys(faker.address().city());
        driver.findElement(state).sendKeys(faker.address().state());
        driver.findElement(zipcode).sendKeys(faker.address().zipCode());
        driver.findElement(country).sendKeys(faker.address().country());
        String fullAddressField = driver.findElement(fullAddress).getText();
        String streetAddressField = driver.findElement(streetAddress).getText();
        String streetAddressField2 = driver.findElement(streetAddressnumber).getText();
        String cityField = driver.findElement(city).getText();
        String stateField = driver.findElement(state).getText();
        String zipcodeField = driver.findElement(zipcode).getText();
        String countryField = driver.findElement(country).getText();

        Map<String, String> formData = new HashMap<>();
        formData.put("Full Address", fullAddressField);
        formData.put("Street Address", streetAddressField);
        formData.put("Street Address Number", streetAddressField2);
        formData.put("City", cityField);
        formData.put("State", stateField);
        formData.put("Zipcode", zipcodeField);
        formData.put("Country", countryField);

    }


}
