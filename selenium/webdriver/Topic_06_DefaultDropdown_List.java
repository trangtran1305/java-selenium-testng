package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_DefaultDropdown_List {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//testcase
	@Test
	public void TC_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());
		select.selectByValue("automation");
		select.selectByValue("manual");
		select.selectByValue("desktop");

		Assert.assertEquals(select.getAllSelectedOptions().size(), 3);
		List<WebElement> optionSelectElement = select.getAllSelectedOptions();
		for (WebElement option : optionSelectElement)
			System.out.println(option.getText());
		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
		Thread.sleep(2000);
	}

	@Test
	public void TC_02() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("John");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Wick");
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("1");
		Assert.assertEquals(select.getOptions().size(), 32);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getOptions().size(), 13);
		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getOptions().size(), 112);
		String email = "johnwick" + randomNumber() + "@gmail.com";
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='My account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");
		Thread.sleep(2000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public static int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}