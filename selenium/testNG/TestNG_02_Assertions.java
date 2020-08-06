package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG_02_Assertions {
  @Test
  public void TC01() {
	  String testingFramework1= "testNG";
	  String testingFramework2="JUnit";
	  Assert.assertFalse(testingFramework1.equals(testingFramework2));
	  Assert.assertNotEquals(testingFramework1,testingFramework2);
	  Object object= null;
	  Assert.assertNull(object);
	  object= 15;
	  object="Automation";
	  Assert.assertNotNull(object);
			  
  }
}
