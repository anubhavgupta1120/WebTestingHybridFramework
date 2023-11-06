package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Resources.AbstractComponents;

public class PaymentPage extends AbstractComponents {
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This is page factory design Pattern (PageFactory.initElements(driver, this)
	// -> we put this inside constructor)

	@FindBy(how = How.CSS, using = "[placeholder='Select Country']")
	private WebElement enterCountryElement;
	@FindBy(how = How.XPATH, using = "//section[contains(@class, \"ta-result\")]//button")
	private List<WebElement> countryList;
	@FindBy(how = How.CSS, using = ".action__submit")
	private WebElement placeOrderbtn;

	public void selectCountry(String country) {
		enterCountryElement.sendKeys(country);
		waitTillListOfElementsVisible(countryList);
		countryList.stream().filter(s -> s.getText().equalsIgnoreCase(country)).findFirst().orElse(null).click();
	}
	
	public ConfirmationPage placeOrder() {
		placeOrderbtn.click();
		return new ConfirmationPage(driver);
	}

}
