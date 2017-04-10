package pageMethods;
import pageObjects.LoginPage;
import org.openqa.selenium.*;
import cucumber.api.DataTable;

public class Login {
    
    public static void UserLogin(WebDriver driver, DataTable usercredentials){
    	for (java.util.Map<String, String> data : usercredentials.asMaps(String.class, String.class)) {
    	driver.get(LoginPage.url);
    	driver.findElement(LoginPage.signIn_link).click();
    	driver.findElement(LoginPage.emaiId).sendKeys(data.get("username"));
    	driver.findElement(LoginPage.password).sendKeys(data.get("password"));
    	driver.findElement(LoginPage.signIn_button).click();	
    	  	
    }
   }	
	
}
