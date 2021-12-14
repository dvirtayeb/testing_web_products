package project;
// Generated by Selenium IDE

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.w3c.dom.Text;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;
import java.net.MalformedURLException;
import java.net.URL;

public class TestOrderProducts {
	private WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dvir tayeb\\Documents\\Selnium\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		vars = new HashMap<String, Object>();
	}

	@AfterMethod
	public void tearDown() {
    driver.quit();
	}

	@Test
	public void test_addToCart() throws InterruptedException {
		// Test name: test_product
		// Step # | name | target | value
		int totalPrice=0;
		driver.get("https://www.demoblaze.com/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		totalPrice+=addProd("//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a", "//*[@id=\"tbodyid\"]/div[2]/div/a", driver);
		totalPrice+=addProd("//*[@id=\"tbodyid\"]/div[2]/div/div/h4/a","//*[@id=\"tbodyid\"]/div[2]/div/a",driver);
		totalPrice+=addProd("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a","//*[@id=\"tbodyid\"]/div[2]/div/a",driver);
		WebElement cart=driver.findElement(By.xpath("//*[@id=\"cartur\"]"));
		cart.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		int checkPrice=Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"totalp\"]")).getText());
		assertEquals(totalPrice, checkPrice);
	}
	private static int addProd(String xpath1,String xpath2,WebDriver driver) throws InterruptedException {
		int price=0;
		WebElement productName = driver.findElement(By.xpath(xpath1));
		productName.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement priceSector = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3"));
		String str=priceSector.getText();
		str=str.split(" ")[0];
		str=str.substring(1);
		price=Integer.parseInt(str);
		WebElement addProductToCart = driver.findElement(By.xpath(xpath2));
		addProductToCart.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		String alert_addedToCart = driver.switchTo().alert().getText();
		assertEquals(alert_addedToCart, "Product added", alert_addedToCart);
		alert.accept();
		driver.switchTo().defaultContent();
		WebElement home = driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a"));
		home.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return price;
		
	}
}


