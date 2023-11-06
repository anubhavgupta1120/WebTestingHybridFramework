package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Resources.AbstractComponents;

public class LoginPage extends AbstractComponents{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This is page factory design Pattern (PageFactory.initElements(driver, this)
	// -> we put this inside constructor)

	@FindBy(how = How.CSS, using = "#userEmail")
	private WebElement emailElement;

	@FindBy(how = How.CSS, using = "#userPassword")
	private WebElement passwordElement;

	@FindBy(how = How.CSS, using = "#login")
	private WebElement loginElement;

	public ProductsPage loginToApplication(String email, String password) {
		try {
			emailElement.sendKeys(email);
			passwordElement.sendKeys(password);
			loginElement.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ProductsPage(driver);

	}
	
	public void goToBaseUrl() {
		// baseUrl
		driver.get("https://rahulshettyacademy.com/client");
	}

}
