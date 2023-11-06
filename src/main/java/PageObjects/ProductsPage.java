package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Resources.AbstractComponents;

public class ProductsPage extends AbstractComponents {

	WebDriver driver;

	public ProductsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This is page factory design Pattern (PageFactory.initElements(driver, this)
	// -> we put this inside constructor)

	@FindBy(how = How.XPATH, using = "//div[@class = 'card-body']")
	private List<WebElement> productListElement;

	@FindBy(how = How.CSS, using = ".ng-animating")
	private WebElement animationElement;

	By toastElement = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() throws InterruptedException {
		waitTillListOfElementsVisible(productListElement);
		return productListElement;

	}

	public void selectProducts(List<String> products) throws InterruptedException {
		for (String product : products) {
			WebElement productElement = getProductList().stream()
					.filter(s -> s.findElement(By.xpath(".//b")).getText().equalsIgnoreCase(product)).findFirst()
					.orElse(null);
			productElement.findElement(By.xpath("./button[text() = ' Add To Cart']")).click();
			Thread.sleep(4000);
		}
	}

}
