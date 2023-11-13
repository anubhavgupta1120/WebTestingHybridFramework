package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Resources.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.CSS, using = ".hero-primary")
	private WebElement confirmationText;

	public String getConfirmationText() {
		waitForWebElement(confirmationText);
		return confirmationText.getText();
	}
}
