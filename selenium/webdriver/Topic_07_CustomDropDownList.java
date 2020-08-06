package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_CustomDropDownList {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// explicitWait
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		// impicitWait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInDropDown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[2]")).getText(), "19");

	}

	@Test
	public void TC_02_Angular() {
		driver.get("https://bit.ly/2UV2vYi");
		selectItemInDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Football");
		selectItemInDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getHiddenText("select[id='games_hidden'] option"), "Golf");

	}

	@Test
	public void TC_03_ReacJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropDown("//div[@id='root']/div/div", "//span[@class='text']", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='text']")).getText(), "Matt");

	}

	@Test
	public void TC_04_VueJs() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropDown("//span[@class='caret']", "//li/a", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "Second Option");

	}

	@Test
	public void TC_05_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sendkeyToEditDropDown("//div[@id='default-place']/input", "Audi");
		Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Audi");
		sleepInSecond(2);
		driver.findElement(By.xpath("//div[@id='default-place']/input")).clear();
		sendkeyToEditDropDown("//div[@id='default-place']/input", "Fiat");
		Assert.assertEquals(getHiddenText("#default-place li.es-visible"), "Fiat");
	}

	@Test
	public void TC_06_Multiple_Select() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void selectItemInDropDown(String parentLocator, String ItemsLocator, String expectedItem) {
		// Chọn element để xổ ra toàn bộ list
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(1);
		// Chờ hiển thị hết phần tử trong list
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ItemsLocator)));

		// Lấy ra danh sách các phần tử trong list
		List<WebElement> allItems = driver.findElements(By.xpath(ItemsLocator));

		// Duyệt từng phần tử trong list nếu là phần tử cần tìm thì click, còn không thì
		// duyệt tiếp
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				// Cuộn scroll tới vị trí item
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(2);
				explicitWait.until(ExpectedConditions.elementToBeClickable(item));
				item.click();
				sleepInSecond(2);
				break;
			}
		}

	}

	@Test
	public String getHiddenText(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");

	}

	@Test
	public void sendkeyToEditDropDown(String locator, String value) {
		driver.findElement(By.xpath(locator)).sendKeys(value);
		sleepInSecond(2);
		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
		sleepInSecond(2);

	}

	@Test
	public void sleepInSecond(long time) {
		try {
			// dead Wait
			Thread.sleep(1000 * time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
