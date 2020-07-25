package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_User_Interaction {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String rootfolder=System.getProperty("user.dir");
	String javascriptPath=rootfolder+"\\dragAndDrop\\drag_and_drop_helper.js";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor= (JavascriptExecutor) driver;
		action= new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	public void TC_01_Hover_to_Element() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids']"))).perform();;
		sleepInSecond(2);
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();;
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).getText(),"Kids Home Bath");
		
	}
		
	
	public void TC_02_Click_and_Hold_multiple() {
		String[] expectedItems= {"1","2","3","4"};
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listItems= driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
		sleepInSecond(3);
		List<WebElement> selectedItems= driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		for(WebElement item: selectedItems) {
			System.out.println(item.getText());;
		}
		ArrayList<String> actualItems = new ArrayList<String>();
		for(WebElement item: selectedItems) {
			actualItems.add(item.getText());
		}
		Assert.assertEquals(expectedItems,(Object[])actualItems.toArray());
	}
	
	public void TC_03_ClicK_and_Hold_random() {
		String[] expectedItems= {"1","3","6","11"};
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listItems= driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(listItems.get(0)).click(listItems.get(2)).click(listItems.get(5)).click(listItems.get(10)).perform();
		sleepInSecond(3);
		List<WebElement> selectedItems= driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		for(WebElement item: selectedItems) {
			System.out.println(item.getText());;
		}
		ArrayList<String> actualItems = new ArrayList<String>();
		for(WebElement item: selectedItems) {
			actualItems.add(item.getText());
		}
		Assert.assertEquals(expectedItems,(Object[])actualItems.toArray());
	}

	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(),"Hello Automation Guys!");
	}
	
	public void TC_05_Right_Click(){
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//p/span"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
		WebElement hoverElement= driver.findElement(By.xpath("//li[contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]/span[text()='Quit']"));
		Assert.assertTrue(hoverElement.isDisplayed());
		hoverElement.click();
		
	}
	public void TC_06_Drag_and_Drop() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceElement=driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetElement=driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(sourceElement, targetElement).perform();
		Assert.assertEquals(targetElement.getText(),"You did great!");
	}
	public void TC_07_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFile(javascriptPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		// B to A
		jsExecutor.executeScript(java_script);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
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