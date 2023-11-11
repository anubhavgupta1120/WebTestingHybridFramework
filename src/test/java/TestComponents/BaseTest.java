package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		String browserName = System.getProperty("Browser") != null ? System.getProperty("Browser")
				: properties.getProperty("Browser");
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

	// Used to take data from json file.
	public List<HashMap<String, String>> getJsonData(String JsonPath) throws IOException {
		// convert json file to string
		String jsonContent = FileUtils.readFileToString(new File(JsonPath), StandardCharsets.UTF_8);
		// String to HashMap conversion (Jackson databind maven repo required)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> result = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return result;
	}

	public String getScreenShot(String TestCaseName, WebDriver driver) throws IOException {
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String pathOfSS = "/Users/anubhavgupta/Documents/eclipse-workspace(Java)/WebTesting_Hybrid_Framework/Screenshots/"
				+ TestCaseName + ".png";
		FileUtils.copyFile(sourceFile, new File(pathOfSS));
		return pathOfSS;
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
