package Resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.CartPage;
import PageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Different Types of Expected Wait Conditions
	
	public void waitTillListOfElementsVisible(List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void waitTillInvisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void scrollInToView(WebElement element) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor)driver);
		jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
		waitForWebElement(element);
	}
	
	
	
	// handeling navigation bar Elements
	
	@FindBy(how = How.CSS, using = "[routerlink*='cart']")
	WebElement cartElement;
	@FindBy(how = How.CSS, using = "[routerlink*='myorders']")
	WebElement orderElement;
	
	public CartPage goToCart() {
		cartElement.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOrders() {
		orderElement.click();
		return new OrderPage(driver);
	}
	
	public String getProperty(String key) throws IOException {
		Properties properties = new Properties();
		FileInputStream FIS = new FileInputStream(
				"/Users/anubhavgupta/Documents/eclipse-workspace(Java)/WebTesting_Hybrid_Framework/Resources/GlobalData.properties");
		properties.load(FIS);
		return properties.getProperty(key);
	}
	
}
