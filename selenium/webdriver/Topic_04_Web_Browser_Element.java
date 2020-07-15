package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Element {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}


	@Test
	public void TC_01_getCurrentURL() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_getTitle() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_navigate() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04_getPageSource() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		String registerPageSource = driver.getPageSource();
		Assert.assertTrue(registerPageSource.contains("Create an Account"));

	}

	@Test
	public void TC_05_isElementDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed())
			driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");

		if (driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed())
			driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");

		if (driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed())
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
	}

	@Test
	public void TC_06_isElementEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (driver.findElement(By.xpath("//select[@id='job1']")).isEnabled())
			System.out.println("Job Role 01 is enabled");

		if (!driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled())
			System.out.println("Slider-02 is disabled");

	}

	@Test
	public void TC_07_isElementSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
        driver.findElement(By.id("development")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
        Assert.assertTrue( driver.findElement(By.id("development")).isSelected());
        driver.findElement(By.xpath("//input[@id='under_18']")).click();
        driver.findElement(By.id("development")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
        Assert.assertFalse( driver.findElement(By.id("development")).isSelected());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}