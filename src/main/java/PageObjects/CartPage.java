package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Resources.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = ".totalRow button")
	private WebElement checkOutElement;
	@FindBy(how = How.CSS, using = ".cartSection h3")
	private List<WebElement> cartProductList;

	public Boolean verifyCart(List<String> products) {
		waitTillListOfElementsVisible(cartProductList);
		Boolean match = false;
		try {
			for (String product : products) {
				match = cartProductList.stream()
						.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product));

			}
			return match;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return match;

	}

	public PaymentPage checkOut() {
		checkOutElement.click();
		return new PaymentPage(driver);
	}
}
