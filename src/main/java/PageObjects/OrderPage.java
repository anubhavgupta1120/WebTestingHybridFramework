package PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Resources.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//tr[@class = 'ng-star-inserted']//td[2]")
	private List<WebElement> orderList;

	public boolean verifyOrdersDisplay(List<String> products) {
		waitTillListOfElementsVisible(orderList);
		Boolean match = false;
		try {
			for (String product : products) {
				match = orderList.stream().anyMatch(s -> s.getText().equalsIgnoreCase(product));
				if(match == false) {
					return match;
				}

			}
			return match;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return match;
	}

}
