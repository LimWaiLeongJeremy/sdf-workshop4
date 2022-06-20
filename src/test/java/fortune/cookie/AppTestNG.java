package fortune.cookie;

import org.testng.annotations.Test;

import fortune.cookie.server.App;
import fortune.cookie.server.ServerApp;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.Assert;

public class AppTestNG {
  @Test
  public void testCookie() {
    String randomCookie = App
            .getRandomCookie("/Users/jeremy/Desktop/practice/luckylucky/cookie_file.txt");
    Assert.assertNotNull(randomCookie);
  }

  @Test
  public void testing() {
    String randomCookie = ServerApp.main( ["12345 /Users/jeremy/Desktop/practice/luckylucky/cookie_file.txt"]);
            
    Assert.assertNotNull(randomCookie);
  }


  
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {

  }
}
