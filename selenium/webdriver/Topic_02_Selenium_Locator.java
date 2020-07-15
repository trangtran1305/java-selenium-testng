package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Mở app Url
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	
	
	@Test
	public void TC_01_ID() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("id@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("email")).clear();
	}
	@Test
	public void TC_02_Class() throws InterruptedException {
		driver.findElement(By.className("validate-password")).sendKeys("123456");
		Thread.sleep(2000);
		driver.findElement(By.className("validate-password")).clear();
	}
	@Test
	public void TC_03_Xpath()throws InterruptedException {
		//name
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).sendKeys("123456");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).clear();
	}
	
	@Test
	public void TC_04_Name() throws InterruptedException{
		driver.findElement(By.name("login[username]")).sendKeys("name@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.name("login[username]")).clear();
	}
	@Test
	public void TC_05_Tagname() throws InterruptedException{
		int linkNumber= driver.findElements(By.tagName("a")).size();
		System.out.println("Sum link="+linkNumber);
		Thread.sleep(2000);
		
	}
	@Test
	//Chỉ làm việc với những đường link tuyệt đối
	public void TC_06_Link_Text()throws InterruptedException {
		//Click vào SITE MAP link
		driver.findElement(By.linkText("SITE MAP")).click();
		Thread.sleep(2000);
	}
	@Test //Chỉ làm việc với những đường link tương đối
	public void TC_07_Partial_Link_Text()throws InterruptedException {
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		//driver.findElement(By.partialLinkText("SEARCH")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void TC_08_Css()throws InterruptedException {
		//ID
		driver.findElement(By.cssSelector("#search")).sendKeys("may store");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#search")).clear();
	}
	
	@AfterClass
	public void afterClass() throws InterruptedException{
		driver.quit();
	}

}
