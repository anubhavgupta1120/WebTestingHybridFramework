package TestCasesFolder;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest_1 {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Login in the page (Sending details and Clicking on the login button)
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("anubhavgupta@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("Anubhav@11");
		driver.findElement(By.cssSelector("#login")).click();

		// Adding products to cart

		List<WebElement> products = driver.findElements(By.cssSelector("div [class = 'card-body']"));
		WebElement productName = products.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		productName.findElement(By.cssSelector("button:last-of-type")).click();

		// some codition before proceed further

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// clicking on add to cart
		Thread.sleep(6000);
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[routerlink*='cart']"))));
//		WebElement element = driver.findElement(By.cssSelector("[routerlink*='cart']"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("argument[0].click();", element);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

		//
		
		//Connection con = DriverManager.getConnection(dbUrl,username,password);
		//Class.forName("com.mysql.jdbc.Driver");
		//Statement stmt = con.createStatement();
		//stmt.executeQuery();

	}

}
