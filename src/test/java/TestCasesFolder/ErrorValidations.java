package TestCasesFolder;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ProductsPage;
import TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	WebDriver driver;

	@Test(groups = { "ErrorHandling" })
	public void LoginErrorValidation() throws InterruptedException, IOException {
		loginPage.loginToApplication("anubhavguptaa@gmail.com", "Anubhav@1122");
		AssertJUnit.assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
	}

	@Test(groups = { "ErrorHandling" })
	public void ProductErrorValidation() throws InterruptedException, IOException {
		ProductsPage productsCatelouge = loginPage.loginToApplication("anubhavgp11@gmail.com", "Anubhav@11");
		productsCatelouge.selectProducts(List.of("ADIDAS ORIGINAL", "ZARA COAT 3"));
		CartPage cartPage = productsCatelouge.goToCart();
		boolean res = cartPage.verifyCart(List.of("ADIDAS ORIGINAL", "ZARA COAT 3"));
		AssertJUnit.assertTrue(res);
	}
}
