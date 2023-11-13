package StepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import PageObjects.CartPage;
import PageObjects.ConfirmationPage;
import PageObjects.LoginPage;
import PageObjects.OrderPage;
import PageObjects.PaymentPage;
import PageObjects.ProductsPage;
import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	public LoginPage loginPage;
	public ProductsPage productsPage;
	public CartPage cartPage;
	public PaymentPage paymentPage;
	public ConfirmationPage confirmationPage;
	public OrderPage orderPage;

	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() {
		// Write code here that turns the phrase above into concrete actions
		try {
			loginPage = launchApplication();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Given("^logged in with username (.*) and password (.*)$")
	public void logged_in_with_username_and_password(String email, String password) {
		// Write code here that turns the phrase above into concrete actions
		productsPage = loginPage.loginToApplication(email, password);
	}

	@When("^I add products ([^\"]*) to cart$") // for list use this regex
	public void i_add_products_to_cart(String products) {
		// Write code here that turns the phrase above into concrete actions
		String[] value = products.split(",");
		try {
			productsPage.selectProducts(Arrays.asList(value));
			cartPage = productsPage.goToCart();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("^checkout ([^\"]*) and place the order$") // for list use this regex
	public void checkout_and_place_the_order(String products) {
		// Write code here that turns the phrase above into concrete actions
		String[] value = products.split(",");
		assertTrue(cartPage.verifyCart(Arrays.asList(value)));
		paymentPage = cartPage.checkOut();
		paymentPage.selectCountry("India");
		confirmationPage = paymentPage.placeOrder();

	}

	@Then("^\"([^\"]*)\" message displayed on confirmation page$")
	public void message_displayed_on_confirmation_page(String confirmationText) {
		// Write code here that turns the phrase above into concrete actions
		assertTrue(confirmationPage.getConfirmationText().equalsIgnoreCase(confirmationText));
	}

	@And("^I moved to the Order Page$")
	public void I_moved_to_the_Order_Page() {
		orderPage = productsPage.goToOrders();
	}

	@Then("^([^\"]*) should be displayed on the page$")
	public void should_be_displayed_on_the_page(String products) {
		String[] values = products.split(",");
		assertTrue(orderPage.verifyOrdersDisplay(Arrays.asList(values)));
	}

	@And("^close the browser$")
	public void close_the_browser() {
		tearDown();
	}

	@Then("^\"([^\"]*)\" message should be displayed$")
	public void message_should_be_displayed(String toastMessage) {
		assertEquals(loginPage.getErrorMessage(), toastMessage);
		
	}
}
