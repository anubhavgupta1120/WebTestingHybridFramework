package TestCasesFolder;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.ConfirmationPage;
import PageObjects.OrderPage;
import PageObjects.PaymentPage;
import PageObjects.ProductsPage;
import TestComponents.BaseTest;

public class E_Commerce extends BaseTest {
	WebDriver driver;

	@Test
	public void submitOrder() throws InterruptedException, IOException {
		ProductsPage productsPage = loginPage.loginToApplication("anubhavgupta@gmail.com", "Anubhav@11");
		productsPage.selectProducts(List.of("ZARA COAT 3"));
		CartPage cartPage = productsPage.goToCart();
		assertTrue(cartPage.verifyCart(List.of("ZARA COAT 3")));
		PaymentPage paymentPage = cartPage.checkOut();
		paymentPage.selectCountry("India");
		ConfirmationPage confirmationPage = paymentPage.placeOrder();
		assertTrue(confirmationPage.getConfirmationText().equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// To verify the orders Page (Checking the order places is visible in Order section)

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() {
		ProductsPage productsPage = loginPage.loginToApplication("anubhavgupta@gmail.com", "Anubhav@11");
		OrderPage orderPage = productsPage.goToOrders();
		assertTrue(orderPage.verifyOrdersDisplay(List.of("ZARA COAT 3")));

	}

}
