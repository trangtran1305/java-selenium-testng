package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_JavascripExcutor {
	WebDriver driver;
	WebElement element;
	JavascriptExecutor js;
	

	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		js= (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Javascript_Excutor() {
		//lấy domain live.demoguru99.com
		js.executeScript("window.location='http://live.demoguru99.com/'");
		String domain1=(String) js.executeScript("return document.domain");
		Assert.assertEquals(domain1,"live.demoguru99.com");
		//lấy URL http://live.demoguru99.com/
		String url=(String) js.executeScript("return document.URL");
		Assert.assertEquals(url,"http://live.demoguru99.com/");
		//click
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[text()='Mobile']")));
		WebElement samsungCart=driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button"));
		js.executeScript("arguments[0].click();",samsungCart);
		//inner text
		String innerText=(String)js.executeScript("return document.documentElement.innerText;");
		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
		//nevigate lại trang đầu
		js.executeScript("history.go(0);");
		//click
		WebElement CustomerService=driver.findElement(By.xpath("//a[text()='Customer Service']"));
		js.executeScript("arguments[0].click();",CustomerService);
		//lấy tittle page
		Assert.assertEquals(js.executeScript("return document.title", CustomerService),"Customer Service");
		//scroll tới phần tử
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//input[@id='newsletter']")));
		String innerText2=(String)js.executeScript("return document.documentElement.innerText;");
		Assert.assertTrue(innerText2.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		              
		//navigate trang khác
		js.executeScript("window.location='http://demo.guru99.com/v4/';");
		String domain2=(String) js.executeScript("return document.domain");
		Assert.assertEquals(domain2,"demo.guru99.com");
	}

	@Test
	public void TC_02_Remove_Atribute() {
		
	}
	@Test
	public void TC_03_Create_An_Account() {
		
	}
	// Browser
		public Object executeForBrowser(String javaSript) {
			return js.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) js.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			js.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));
			js.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));
			js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}