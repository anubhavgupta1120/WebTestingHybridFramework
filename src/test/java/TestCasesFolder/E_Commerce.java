package TestCasesFolder;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import PageObjects.PaymentPage;
import PageObjects.ProductsPage;

public class E_Commerce {
	WebDriver driver;

	@Test
	public void Test() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.goToBaseUrl();
		ProductsPage productsPage = loginPage.loginToApplication("anubhavgupta@gmail.com", "Anubhav@11");
		productsPage.selectProducts(List.of("ZARA COAT 3"));
		CartPage cartPage = productsPage.goToCart();
		assertTrue(cartPage.verifyCart(List.of("ZARA COAT 3")));
		PaymentPage paymentPage = cartPage.checkOut();
		paymentPage.selectCountry("India");
		ConfirmationPage confirmationPage = paymentPage.placeOrder();
		assertTrue(confirmationPage.getConfirmationText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@BeforeTest
	public void initialize() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterTest
	public void terminate() {
		driver.quit();
	}
}
