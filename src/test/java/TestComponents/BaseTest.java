package TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PageObjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {
		// Invoking properties file of Resource Folder
		// Change path if you want to run it in Your system
		/**
		 * -----------------------------------------------------------------------------------------------------------
		 **/
		Properties properties = new Properties();
		FileInputStream FIS = new FileInputStream(
				"/Users/anubhavgupta/Documents/eclipse-workspace(Java)/WebTesting_Hybrid_Framework/Resources/GlobalData.properties");
		properties.load(FIS);
		/**
		 * -----------------------------------------------------------------------------------------------------------
		 **/
		String browserName = properties.getProperty("Browser");
		if (browserName.contains("chrome")) {

			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
			// driver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("firefox")) {

			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--disable-notifications");
			driver = new FirefoxDriver(options);
		}

		else if (browserName.equalsIgnoreCase("edge")) {

			EdgeOptions options = new EdgeOptions();
			options.addArguments("--disable-notifications");
			driver = new EdgeDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToBaseUrl();
		return loginPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
