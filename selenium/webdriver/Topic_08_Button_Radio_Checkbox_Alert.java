package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox_Alert {
	WebDriver driver;
	JavascriptExecutor js;
	WebElement loginButton;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		js= (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
//testcase
	@Test
	public void TC_01_Button() {
		//Truy cập trang
		driver.get("https://www.fahasa.com/customer/account/create?attempt=2");
		//click to Đăng nhập
		driver.findElement(By.xpath("//ul[@id='popup-login-tab_list']/li[1]/a")).click();
		//Verify nút Đăng nhập is disable
		loginButton=driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		Assert.assertFalse(loginButton.isEnabled());
		//sendkey username, password
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456");
		sleepInSecond(3);
		//Verify nút đăng nhập is enable
		Assert.assertTrue(loginButton.isEnabled());
		//refresh lại trang=> html fresh lại=> giá trị các biến element cũng bị thay đổi
		driver.navigate().refresh();
		driver.findElement(By.xpath("//ul[@id='popup-login-tab_list']/li[1]/a")).click();
		loginButton=driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
		Assert.assertFalse(loginButton.isEnabled());
		//Remove thuộc tính disable của button đăng nhập =>Hạn chế sử dụng
		js.executeScript("arguments[0].removeAttribute('disabled')",loginButton);
		//click button Đăng nhập
		driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).click();
		sleepInSecond(5);
		//Verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")).getText(),"Thông tin này không thể để trống");
	}

	@Test
	public void TC_02_Default_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		WebElement checkbox=driver.findElement(By.xpath("//input[@id='eq5']"));
		//check 
		if(!checkbox.isSelected()) checkbox.click();
		Assert.assertTrue(checkbox.isSelected());
		//uncheck
		checkbox.click();
		Assert.assertFalse(checkbox.isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		WebElement radio=driver.findElement(By.xpath("//input[@id='engine3']"));
		radio.click();
		if(!radio.isSelected()) radio.click();
		
		
		
	}
	@Test
	public void TC_03_Custom_Checkbox_Radio() {
		//custom radio
		driver.get("https://material.angular.io/components/radio/examples");
		WebElement summerRadio=driver.findElement(By.xpath("//mat-radio-button[@id='mat-radio-4']//input"));
		js.executeScript("arguments[0].click()",summerRadio);
		Assert.assertTrue(summerRadio.isSelected());
		//custom checkbox
		driver.get("https://material.angular.io/components/checkbox/examples");
		WebElement checkbox1=driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']//input"));
		js.executeScript("arguments[0].click()",checkbox1);
		WebElement checkbox2=driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-2']//input"));
		js.executeScript("arguments[0].click()",checkbox2);
		Assert.assertTrue(checkbox1.isSelected());
		Assert.assertTrue(checkbox2.isSelected());
		js.executeScript("arguments[0].click()",checkbox1);
		js.executeScript("arguments[0].click()",checkbox2);
		Assert.assertFalse(checkbox1.isSelected());
		Assert.assertFalse(checkbox2.isSelected());
		
	}
	@Test
	public void TC_04_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		Alert alert= driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS Alert");
		alert.accept();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");
		
		
		
	}
	@Test
	public void TC_05_Confirm_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		Alert alert= driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS Confirm");
		alert.dismiss();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
		
		
	}
	@Test
	public void TC_06_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		Alert alert= driver.switchTo().alert();
		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		alert.sendKeys("changtran");
		alert.accept();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: changtran");
		
	}
	@Test
	public void TC_07_Authentication_Alert_byLink() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//p")).getText(),"Congratulations! You must have the proper credentials.");
	
	}
	@Test
	public void TC_08_Authentication_Alert_AutoIT() throws Throwable {
		String rootFolder= System.getProperty("user.dir");
		String firefoxAuthen= rootFolder+"\\autoScript\\authen_firefox.exe";
		Runtime.getRuntime().exec(new String[] {firefoxAuthen,"admin","admin"});
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//p")).getText(),"Congratulations! You must have the proper credentials.");
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


public void sleepInSecond(long time) {
	try {
		// dead Wait
		Thread.sleep(1000 * time);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}
}