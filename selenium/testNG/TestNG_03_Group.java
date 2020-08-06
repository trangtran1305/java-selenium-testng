package testNG;

import org.testng.annotations.Test;

public class TestNG_03_Group {
	@Test(groups = "user")
	public void TC01_Register() {
	}

	@Test(groups = "user")
	public void TC02_Login() {
	}

	@Test(groups = "product")
	public void TC03_NewProduct() {
	}

	@Test(groups = "product")
	public void TC04_EditProduct() {
	}

	@Test(groups = "product")
	public void TC05_ViewProduct() {
	}

	@Test(groups = "product")
	public void TC06_DeleteProduct() {
	}

	@Test(groups = "account")
	public void TC07_NewAccount() {
	}

	@Test(groups = "account")
	public void TC08_EditAcount() {
	}
}