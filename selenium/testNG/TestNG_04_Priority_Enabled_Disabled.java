
package testNG;

import org.testng.annotations.Test;

public class TestNG_04_Priority_Enabled_Disabled {
	@Test(groups = "user", priority=1)
	public void Register() {
		
	}

	@Test(groups = "user",priority=2)
	public void Login() {
	}

	@Test(groups = "product",priority=3, description="Register to create new user")
	public void NewProduct() {
	}

	@Test(groups = "product",priority=4, enabled= false)
	public void EditProduct() {
	}

	@Test(groups = "product",priority=5)
	public void ViewProduct() {
	}

	@Test(groups = "product",priority=6)
	public void DeleteProduct() {
	}

	@Test(groups = "account",priority=7)
	public void NewAccount() {
	}

	@Test(groups = "account",priority=8)
	public void EditAcount() {
	}
}

