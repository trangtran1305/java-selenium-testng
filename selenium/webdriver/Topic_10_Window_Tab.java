package webdriver;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Window_Tab {
	WebDriver driver;
	JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		sleepInSecond(5);
		WebElement popup = driver.findElement(By.xpath("//div[@class='modal-content']"));
		Assert.assertTrue(popup.isDisplayed());
		driver.findElement(By.xpath("//button[@class='close']")).click();
		sleepInSecond(5);
		Assert.assertFalse(popup.isDisplayed());
	}

	public void TC_02_Fixed_Popup() {
		driver.get("https://bni.vn/");
		WebElement popup = driver.findElement(By.xpath("//div[@id='sg-popup-content-wrapper-1236']"));
		Assert.assertTrue(popup.isDisplayed());
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();

	}

	public void TC_03_Random_Popup() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(15);
		boolean status = driver.findElement(By.xpath("//div[@id='mailch-bg']/div")).isDisplayed();
		if (status)
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
		sleepInSecond(5);
		driver.findElement(By.xpath("//section[@id='search-2']/form/label/input")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']/form/label/input")).sendKeys(Keys.ENTER);

		sleepInSecond(5);
		List<WebElement> listContent = driver.findElements(By.xpath("//div[@class='post-content']//h3"));
		for (WebElement list : listContent) {
			System.out.println(list.getText());
			Assert.assertTrue(list.getText().contains("Selenium"));
		}

	}

	public void TC_04_Iframe() {
		driver.get("https://kyna.vn/");
		sleepInSecond(10);

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='k-footer']//iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(), "169K likes");

		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));
		driver.findElement(By.xpath("//textarea")).sendKeys("Selenium");
		driver.findElement(By.xpath("//textarea")).sendKeys(Keys.ENTER);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='infomation-section scroll pdx-10']")).isDisplayed());

		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Java");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Assert.assertTrue(driver.findElement(By.xpath(".//h1[contains(text(),'java')]")).isDisplayed());

	}

	public void TC_05_Windows_Tab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		String parentID = driver.getWindowHandle();
		switchToWindowbyID(parentID);
		Assert.assertEquals(driver.getTitle(), "Google");
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindowbyTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindowbyTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		closeChildWindows(parentID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	public void TC_06_Windows_Tab() {
		driver.get("https://kyna.vn/");
		boolean status = driver.findElement(By.xpath("//div[@class='fancybox-skin']")).isDisplayed();
		if (status)
			driver.findElement(By.xpath("//a[@title='Close']")).click();
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//img[@alt='facebook']/parent::a")));
		String parentID = driver.getWindowHandle();
		switchToWindowbyID(parentID);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		driver.switchTo().window(parentID);
		js.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//img[@alt='android-app-icon']/parent::a")));
		switchToWindowbyTitle("Kyna 2 - Apps on Google Play");
		Assert.assertEquals(driver.getTitle(), "Kyna 2 - Apps on Google Play");
		driver.switchTo().window(parentID);
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='u_0_1']")));
		js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[text()='Kyna.vn']")));
		driver.switchTo().defaultContent();
		closeChildWindows(parentID);

	}

	@Test
	public void TC_07_Windows_Tab() {
		driver.get("http://live.demoguru99.com/index.php/");
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Sony Xperia has been added to comparison list.");
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		String parentID= driver.getWindowHandle();
		switchToWindowbyID(parentID);
		Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
		closeChildWindows(parentID);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Alert alert= driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The comparison list was cleared.");
	}
	

	public void sleepInSecond(long time) {
		try {
			// dead Wait
			Thread.sleep(1000 * time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Window mở 2 tab: switch bằng ID
	public void switchToWindowbyID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}

		}

	}

	// Window mở nhiều hơn 2 tab: switch bằng Title
	public void switchToWindowbyTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}

	}

	// Close childWindows
	public void closeChildWindows(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}