package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.ExtentManager;

import java.time.Duration;
import java.util.Base64;

public class ExtentHooks {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    @Before
    public void setup(Scenario scenario) {
        // Initialize ExtentReports if not already initialized
        if (extent == null) {
            extent = ExtentManager.createInstance("target/extent-report.html");
        }

        // Create a new ExtentTest instance for each scenario
        ExtentTest test = extent.createTest(scenario.getName());
        extentTest.set(test);

        // Initialize WebDriver for each thread
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driverThread.set(driver);

        test.info("Browser initialized for scenario: " + scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        WebDriver driver = driverThread.get();
        ExtentTest test = extentTest.get();

        // Check if the step failed
        if (scenario.isFailed()) {
            try {
                // Capture screenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                String screenshotBase64 = ts.getScreenshotAs(OutputType.BASE64);

                // Attach screenshot to the report
                test.addScreenCaptureFromBase64String(screenshotBase64, "Failed Step Screenshot");
                test.fail("Step failed.");
            } catch (Exception e) {
                test.warning("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = driverThread.get();
        ExtentTest test = extentTest.get();

        // Log scenario result
        if (scenario.isFailed()) {
            test.fail("Scenario failed: " + scenario.getName());
        } else {
            test.pass("Scenario passed: " + scenario.getName());
        }

        System.out.println("New Update");
        // Quit WebDriver and clean up
        if (driver != null) {
            driver.quit();
            driverThread.remove();
            test.info("Browser closed.");
        }

        // Flush the ExtentReports
        if (extent != null) {
            extent.flush();
        }
    }
}
