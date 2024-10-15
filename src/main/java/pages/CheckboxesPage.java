package pages;

import base.BaseTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckboxesPage extends BaseTests {

    By checkBoxBtn = By.cssSelector(" div > li:nth-child(7) > a");

    By checkboxID = (By.id("checkbox-1"));
    public void clickCheckBoxBtn() {
        driver.findElement(checkBoxBtn).click();
    }

    public void clickCheckbox() {
        WebElement checkbox =  driver.findElement(checkboxID);
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected(),"Checkbox is not Selected");
    }

    public void unClickCheckbox() {
        WebElement checkbox =  driver.findElement(checkboxID);

        checkbox.click();
        Assert.assertFalse(checkbox.isSelected(),"Checkbox is Selected");
    }

}
