package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_06_Parameter_Multibrowser {
	WebDriver driver;
	@Parameters("browser") //chạy trên nhiều browser
	@BeforeClass
	public void beforeClass(String Value) {
		if(Value.equals("firefox")) {
		driver= new FirefoxDriver();
		}
		else if(Value.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",".\\browserDriver\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}

	@Test(invocationCount=3) //lặp 3 lần
	public void TC_01() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("selenium123@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.cssSelector("#send2")).click();
		driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Log Out')]")).click();
}

	

	@AfterClass
	public void afterClass() {
	}

}
