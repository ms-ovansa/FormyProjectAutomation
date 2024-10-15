package pages;

import base.BaseTests;
import base.UtilityTests;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CompleteFormPage extends BaseTests {
    Faker faker;

    By completeFormBtn = By.cssSelector("li:nth-child(18) a:nth-child(1)");
    By firstName = By.id("first-name");
    By lastname = By.id("last-name");
    By jobTitle = By.id("job-title");
    By education = By.id("radio-button-1");
    By gender = By.id("checkbox-2");
    By experience = By.id("select-menu");
    By datePicker = By.id("datepicker");
    By submitBtn = By.cssSelector("a[role='button']");

    public void clickCompleteFormBtn() {
        driver.findElement(completeFormBtn).click();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[normalize-space()='Complete Web Form']")));
        String ActualValue = driver.findElement(By.cssSelector("div[class='container'] h1")).getText();
        String ExpectedValue = "Complete Web Form";
        Assert.assertEquals(ActualValue, ExpectedValue);
        UtilityTests.testTitle(ActualValue);
    }

    public void fillForm() {
        faker = new Faker();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("first-name")));
        driver.findElement(firstName).sendKeys(faker.name().firstName());
        driver.findElement(lastname).sendKeys(faker.name().lastName());
        driver.findElement(jobTitle).sendKeys(faker.job().title());
        driver.findElement(education).click();
        driver.findElement(gender).click();
        Select jobTitle = new Select(driver.findElement(experience));
        jobTitle.selectByValue("3");
        driver.findElement(datePicker).click();
        driver.findElement(datePicker).clear();
        driver.findElement(datePicker).sendKeys("11/26/2023");

    }

    public void clickSubmitBtn() {
        driver.findElement(submitBtn).click();
        String ActualValue = driver.findElement(By.cssSelector("h1[align='center']")).getText();
        String ExpectedValue = "Thanks for submitting your form";
        Assert.assertEquals(ActualValue, ExpectedValue);
        UtilityTests.testTitle(ActualValue);

    }
}
