package stepDefinition;
import java.net.URL;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;


public class browser_steps {	
	static String  username = "Nathaniel.Stokes@Smartbear.com";
static String authkey = "u5cc514b61f3e691";
static RemoteWebDriver driver;

	public browser_steps() {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("version", "67x64");
		capabilities.setCapability("screen_resolution", "1366x768");
		capabilities.setCapability("username", username);
		capabilities.setCapability("password", authkey);
// TODO Auto-generated method stub

		try {
	driver = new RemoteWebDriver(new URL("http://hub.crossbrowsertesting.com:80/wd/hub"), capabilities);
		}catch(Exception ex){System.out.println(ex.toString());}
		
		
	}
	@Given("^I visit a ToDo app$")
	public void  openUpToDoApp()   throws Throwable{
		
		

		driver.get("http://crossbrowsertesting.github.io/todo-app.html");
		
		
		
	}
	
	
	@When("^I click some ToDos$")
	public void clickTodos() throws Throwable{
		
		
		driver.findElement(By.name("todo-4")).click();
		driver.findElement(By.name("todo-5")).click();
	}  
	
	@Then("^I add another ToDo to our list$")
	public void addButton() throws Throwable
	{
		driver.findElement(By.id("addbutton")).click();
	}
	
	
	@When("^I archive my old ToDos$")
	public void findArchive() throws Throwable
	{
		
		driver.findElement(By.linkText("archive")).click();
	}
	
	@Then("^I should have 4 ToDos$")
	public void assertSize() throws Throwable
	{
		
		int size = driver.findElements(By.className("ng-pristine")).size();
		try {
		Assert.assertEquals(size,6);
		
		//Handle your pass case
		
		driver.quit();
		}catch(AssertionError ae) {
			
		//Handle your fail case
			driver.quit();
			System.out.println(ae.getMessage());
		}
	}
	
	  
}
