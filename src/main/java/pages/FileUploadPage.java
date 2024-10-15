package pages;

import base.BaseTests;
import org.openqa.selenium.By;
import org.testng.Assert;


public class FileUploadPage extends BaseTests {
    By fileUploadBtn = By.cssSelector("li:nth-child(12) a:nth-child(1)");
    By fileInputId = By.id("file-upload-field");
    By resetBtn = By.cssSelector("button[class='btn btn-warning btn-reset']");

    public static String filedir = System.getProperty("user.dir") + "/src/main/resources/photo.jpg";

    public void clickFileUploadBtn() {
        driver.findElement(fileUploadBtn).click();

    }

    public void uploadFile() throws InterruptedException {
        driver.findElement(fileInputId).sendKeys(filedir);

    }

    public void resetUpload() {
        driver.findElement(resetBtn).click();
        String actualFileName = driver.findElement(By.id("file-upload-field")).getText();
        String expectedFileName = "";
        Assert.assertEquals(actualFileName, expectedFileName, "File reset failed");
    }
}
