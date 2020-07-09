package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Technical {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Login_With_empty_EmailandPass() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("");

		driver.findElement(By.id("pass")).sendKeys("");

		driver.findElement(By.id("send2")).click();

		String emailErrorMsg = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");

		String passwordErrorMsg1 = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passwordErrorMsg1, "This is a required field.");
		Thread.sleep(2000);
	}

	@Test
	public void TC_02_login_with_Invalid_Email() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("12345@123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		String invalidErrorEmailMg = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(invalidErrorEmailMg, "Please enter a valid email address. For example johndoe@domain.com.");
		Thread.sleep(2000);

	}

	@Test
	public void TC_03_login_with_passwordlessthan6chars() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("1234");
		driver.findElement(By.id("send2")).click();

		String passwordErrorMsg3 = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordErrorMsg3, "Please enter 6 or more characters without leading or trailing spaces.");
		Thread.sleep(2000);
	}

	@Test
	public void TC_04_login_with_incorrect_password() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");

		driver.findElement(By.id("pass")).sendKeys("123123123");

		driver.findElement(By.id("send2")).click();

		String passwordErrorMsg4 = driver.findElement(By.xpath("//ul[@class='messages']//span")).getText();
		Assert.assertEquals(passwordErrorMsg4, "Invalid login or password.");
		Thread.sleep(2000);
	}

	@Test
	public void TC_05_login_with_valid_emailpassword() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");
        driver.findElement(By.id("send2")).click();
		String confimDisplay1 = driver
				.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(),'My Dashboard')]")).getText();
		Assert.assertEquals(confimDisplay1, "MY DASHBOARD");
		String confimDisplay2 = driver.findElement(By.xpath("//strong[text()='Hello, Automation Testing!']")).getText();
		Assert.assertEquals(confimDisplay2, "Hello, Automation Testing!");
		/*
		 * /Xpath lấy riêng "Automation Testing" //div[@class="col-1"]//p/text()[1]
		 * Xpath lấy riêng"automation13@gmail.com" //div[@class="col-1"]//p/text()[2]
		 */
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'Automation Testing')]"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'automation_13@gmail.com')]"))
				.isDisplayed());

		driver.findElement(By.xpath("//span[text()='Account']/parent::a")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Thread.sleep(2000);
	}

	@Test
	public void TC_06_RegisterToSystem() {
		// Mở ra Url
		driver.get("http://live.demoguru99.com/index.php/");

		// Click vào My Account link
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Click vào Create an Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Nhập dữ liệu vào các field bắt buộc: Name/ Email/ Password
		driver.findElement(By.name("firstname")).sendKeys("John");
		driver.findElement(By.id("lastname")).sendKeys("Kennedy");

		String email = "johnken" + randomNumber() + "@gmail.com";
		driver.findElement(By.className("validate-email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.name("confirmation")).sendKeys("123456");

		// Click vào Register button
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		String successMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");

		// Name
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box']//p[contains(text(),'John Kennedy')]")).isDisplayed());

		// Email
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='box']//p[contains(.,'" + email + "')]")).isDisplayed());

		// Click Account
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();

		// Click Logout
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		// Chờ cho element nào đó ở trên Home Page hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
