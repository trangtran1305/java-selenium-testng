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

public class Topic_05_Texbox_Texarea {
	WebDriver driver;
	String userIdValue, passwordValue;
	String loginPageCurrentUrl;
	String name = "Selenium Online";
	String dateofBirth = "10/01/2000";
	String address = "123 Address";
	String city = "Ho Chi Minh";
	String state = "Thu Duc";
	String pin = "123456";
	String phone = "0123456789";
	String mail = "selenium" + randomNumber() + "@gmail.com";
	String password = "123456";
	
	By customerNameTexbox = By.name("name");
	By dateofBirthTextbox = By.name("dob");
	By addressTextarea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By mobileNumberTexbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		loginPageCurrentUrl = driver.getCurrentUrl();
	}

	@Test
	public void TC_01_Register() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("changkopuchi@gmail.com");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		userIdValue = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Assert.assertEquals(userIdValue, "mngr272526");
		passwordValue = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		Assert.assertEquals(passwordValue, "EtehAbE");
		Thread.sleep(2000);
	}

	@Test
	public void TC_02_Login() throws InterruptedException {
		driver.get(loginPageCurrentUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIdValue);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordValue);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		Thread.sleep(2000);

	}

	@Test
	public void TC_03_NewCustomer_editCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(customerNameTexbox).sendKeys(name);
		driver.findElement(dateofBirthTextbox).sendKeys(dateofBirth);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileNumberTexbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(mail);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
		String customerID = driver.findElement(By.xpath("//*[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerID);
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(addressTextarea).clear();
		driver.findElement(addressTextarea).sendKeys("123\n Address");
		//
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