package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_UploadFile {
	WebDriver driver;
	String source_folder= System.getProperty("user.dir");
	String source_image1= source_folder+"\\upload_file\\Image_01.jpg";
	String source_image2= source_folder+"\\upload_file\\Image_02.jpg";
	String source_image3= source_folder+"\\upload_file\\Image_03.jpg";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",source_folder +"\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Upload_By_Sendkeys() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		WebElement upload= driver.findElement(By.xpath("//input[@type='file']"));
		upload.sendKeys(source_image1+"\n"+source_image2+"\n"+source_image3);
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Image_01.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Image_02.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='Image_03.jpg']")).isDisplayed());
		List<WebElement> startButton = driver.findElements(By.xpath("//span[text()='Start']/parent::button"));
		for(WebElement list: startButton) {
			list.click();
			sleepInSecond(10);
		}
		//verify ảnh hiển thị
		List<WebElement> images = driver.findElements(By.xpath("//img[contains(@src,'Image')]"));
		for(WebElement image:images) {
			Assert.assertTrue(isImageDisplayed(image));
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Image_01.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Image_02.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Image_03.jpg']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_by_AutoIT() {
		
	}
	@Test
	public void TC_03_Java_Robot() {
		
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isImageDisplayed(WebElement image){
	        
	        Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript
	        		("return arguments[0].complete && typeof arguments[0].naturalWidth !="
	        				+ " \"undefined\" && arguments[0].naturalWidth > 0", image);
	        if (!ImagePresent)return false;	        
	        else return true;
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