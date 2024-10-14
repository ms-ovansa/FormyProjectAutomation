package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.ThreadLocal;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTests {

	public static WebDriver driver;
	public static ExtentReports reports;
	public static ExtentSparkReporter  htmlReporter;
	public WebDriverWait wait;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testInfo = new ThreadLocal<ExtentTest>();
	public static String toAddress;

	@BeforeSuite
	public void setUp() {
		setupExtentReports();
		setupBrowsers("chrome");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://formy-project.herokuapp.com/");
		
		//LoginPage lp = new LoginPage();
	}

	public static void setupExtentReports() {
		reports = new ExtentReports();
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/extent-report.html");
		reports.attachReporter(htmlReporter);
	}


	public static void setupBrowsers(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
		reports.flush();
	}

	@BeforeMethod(description = "fetch test cases name")
	public void register(Method method) throws InterruptedException {
		ExtentTest parent = reports.createTest(getClass().getSimpleName());
		parentTest.set(parent);
		ExtentTest child = parentTest.get().createNode(method.getName());
		testInfo.set(child);
	}
	
	@AfterMethod(description = "to display the result after each test method")
	public void captureStatus(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = captureScreenshot(result.getName());
			testInfo.get().fail(result.getThrowable());
			testInfo.get().addScreenCaptureFromPath(screenshotPath, result.getName() + " - Screenshot");

		} else if (result.getStatus() == ITestResult.SKIP) {
			testInfo.get().skip(result.getThrowable());
		} else {
			testInfo.get().pass(result.getName() + " Test passed");
		}
	}

	public String captureScreenshot(String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + timestamp + ".png";

		FileUtils.copyFile(source, new File(screenshotPath));

		return screenshotPath;
	}
}