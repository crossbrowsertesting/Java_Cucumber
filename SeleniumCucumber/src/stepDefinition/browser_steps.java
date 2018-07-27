package stepDefinition;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import org.apache.commons.codec.binary.Base64;
import java.io.OutputStreamWriter;

public class browser_steps {	
	static String  username = "YourUserName";
static String authkey = "YourAuthKey";
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
		Assert.assertEquals(size, 6);
		
		setScore("pass");
		driver.quit();
		}catch(AssertionError ae) {
			setScore("fail");
			driver.quit();
			System.out.println(ae.getMessage());
		}
	}
	
	   public void setScore(String score) {//need to change later to not be bool
	        String payload = "{\"action\": \"set_score\", \"score\": \"" + score + "\"}";
	        makeRequest("PUT", payload, "https://crossbrowsertesting.com/api/v3/selenium/"+driver.getSessionId());
	        
	        System.out.println("https://crossbrowsertesting.com/api/v3/selenium/"+driver.getSessionId());
	        System.out.println(payload);
	        
	    }
	    
	
	  private void makeRequest(String requestMethod, String payload, String baseUrl) {
			URL url;
			String auth = "";

		        if (username != null && authkey != null) {
		            auth = "Basic " + Base64.encodeBase64String((username + ":" + authkey).getBytes());
		        }
		        try {
		            url = new URL(baseUrl);
		            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		            conn.setRequestMethod(requestMethod);
		            conn.setDoOutput(true);
		            conn.setRequestProperty("Authorization", auth);
		            conn.setRequestProperty("Content-Type", "application/json");
		            conn.setRequestProperty("Accept", "application/json");
		            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
		            osw.write(payload);
		            osw.flush();
		            osw.close();
		            System.out.print(conn.getResponseMessage());
		        } catch (Exception e) {
		        	System.out.println(e.getMessage());
		        }
			}
}

