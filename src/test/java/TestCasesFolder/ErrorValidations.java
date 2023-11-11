package TestCasesFolder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ProductsPage;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidations extends BaseTest {
	WebDriver driver;

	@Test(retryAnalyzer = Retry.class, groups = { "ErrorHandling" })
	public void LoginErrorValidation() throws InterruptedException, IOException {
		loginPage.loginToApplication("anubhavguptaa@gmail.com", "Anubhav@1122");
		assertEquals(loginPage.getErrorMessage(), "Incorrect emmail or password.");
	}

	@Test(retryAnalyzer = Retry.class, groups = { "ErrorHandling" })
	public void ProductErrorValidation() throws InterruptedException, IOException {
		ProductsPage productsCatelouge = loginPage.loginToApplication("anubhavgp11@gmail.com", "Anubhav@11");
		productsCatelouge.selectProducts(List.of("ADIDAS ORIGINAL", "ZARA COAT 3"));
		CartPage cartPage = productsCatelouge.goToCart();
		boolean res = cartPage.verifyCart(List.of("ADIDAS ORIGINAL", "ZARA COAT 3"));
		assertTrue(res);
	}
}
