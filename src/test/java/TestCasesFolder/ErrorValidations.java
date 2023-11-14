package TestCasesFolder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ProductsPage;
import Resources.ReadData;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class ErrorValidations extends BaseTest {
	WebDriver driver;

	@Test(retryAnalyzer = Retry.class, groups = { "ErrorHandling" })
	public void LoginErrorValidation() throws InterruptedException, IOException {
		loginPage.loginToApplication("anubhavguptaa@gmail.com", "Anubhav@1122");
		assertEquals(loginPage.getErrorMessage(), "Incorrect email or password.");
	}

	@Test(retryAnalyzer = Retry.class, groups = { "ErrorHandling" }, dataProvider = "dp")
	public void ProductErrorValidation(String email, String password, String products) throws InterruptedException, IOException {
		String[] values = products.split(",");
		ProductsPage productsCatelouge = loginPage.loginToApplication(email, password);
		productsCatelouge.selectProducts(Arrays.asList(values));
		CartPage cartPage = productsCatelouge.goToCart();
		boolean res = cartPage.verifyCart(List.of("ADIDAS ORIGINAL", "ZARA COAT "));
		assertFalse(res);
	}

	@DataProvider
	public Object[][] dp() throws IOException {
		return ReadData.readExcelData(
				"/Users/anubhavgupta/Documents/eclipse-workspace(Java)/WebTesting_Hybrid_Framework/Data/ErrorValidation.xlsx",
				"ProductValidation");
	}

}
