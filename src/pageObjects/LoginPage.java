package pageObjects;

import org.openqa.selenium.By;

public class LoginPage {
	
	public static String url = "http://qa-test.avenuecode.com";
	public static By signIn_link = By.linkText("Sign In");
	public static By emaiId = By.id("user_email");
	public static By password = By.id("user_password");	
	public static By signIn_button = By.name("commit");
}
