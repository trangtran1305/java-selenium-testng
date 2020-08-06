package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Selenium_Webdriver_Wait {
	WebDriver driver;
	WebElement element;
	WebDriverWait wait;
	String folder= System.getProperty("user.dir");
	String source_image1= folder+"\\upload_file\\Image_01.jpg";
	String source_image2= folder+"\\upload_file\\Image_02.jpg";
	String source_image3= folder+"\\upload_file\\Image_03.jpg";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",folder+"\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		//implicit wait
		//driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		wait= new WebDriverWait(driver,60);
		driver.manage().window().maximize();
		
	}
	/* Các trạng thái element:
	(1)Xuất hiện trên UI + có trong DOM
	(2)Không xuất hiện trên UI + có trong DOM
	   +do element bị ẩn 
	   +chưa load kịp trang
    (3)Không xuất hiện trên UI + không có trong DOM
       +do chuyển trang
       +do sau một vài thao tác,DOM thay đổi trạng thái/page load lại
     =>element ko còn cả trên UI và trong DOM 
	*/		
	
	public void TC_01_Visible(){
		//(1) Phải xuất hiện trên UI: element.isDisplayed()=true
		//wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	
	public void TC_02_Invisible() {
		//(2)+(3) Không xuất hiện trên UI:element.isDisplayed()=false
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void TC_03_Presence() {
		//(1)+(2) Phải có trong DOM
		//wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void TC_04_Staleness() {
		//(3) Không có trong DOM
		//wait.until(ExpectedConditions.stalenessOf(element));
	}
	
	public void TC_05_findElement() {
		/*case1: Không tìm thấy Element nào
		+báo fail, throw ra exception: No such element
		+tìm element theo chu kì 0,5s cho đến hết timeout của implicit
		case2: Có 1 element
		case3: Nhiều hơn 1 element-> thao tác với element đầu tiên
		*/
		
	}
	
	public void TC_06_findElements() {
		/*case1: Không tìm thấy Element nào
		+báo fail, throw ra exception: No such element
		+tìm element theo chu kì 0,5s cho đến khi tìm thấy ho hết timeout của implicit
		case2: Có 1 element-> đưa vào list có 1 element
		case3: Có n element-> đưa vào list có n element
		*/
		
	}
	
	public void TC_07_Static_Wait() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		Thread.sleep(6000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
	}
	
	public void TC_08_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
		
	}

	public void TC_09_Explicit_Wait_Invisible() {
		//check điều kiện element theo chu kì 0.5s cho đến khi thỏa mãn hoặc hết timeout
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']//img")));
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
		
	}
	
	public void TC_10_Explicit_Wait_Visible() {
		//check điều kiện/trạng thái element theo chu kì 0.5s cho đến khi thỏa mãn hoặc hết timeout
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4")));
		driver.manage().timeouts().implicitlyWait(6,TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(),"Hello World!");
		
	}
	
	public void TC_11_Explicit_Wait() {
		driver.get("https://bit.ly/explicit-wait");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadCalendar1']")));
		WebElement selectedDate=driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(selectedDate.getText(),"No Selected Dates to display.");
		driver.findElement(By.xpath("//a[text()='1']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='1']")));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),"Wednesday, July 1, 2020");
	}
	@Test
	public void TC_12_Fluent_Wait() {
	
		
	
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}