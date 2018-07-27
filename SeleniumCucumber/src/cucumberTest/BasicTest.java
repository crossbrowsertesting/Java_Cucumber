package cucumberTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.lang.Exception;

public class BasicTest {
	static String  username = "Nathaniel.Stokes@Smartbear.com";
	static String authkey = "u5cc514b61f3e691";
	static RemoteWebDriver driver;

	public static void main(String[] args) {
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("name", "Basic Example");
		capabilities.setCapability("build", "1.0");
				capabilities.setCapability("browserName", "Chrome");
				capabilities.setCapability("platform", "Windows 7");

				capabilities.setCapability("screen_resolution", "1366x768");
				capabilities.setCapability("username", username);
				capabilities.setCapability("password", authkey);
		// TODO Auto-generated method stub

				try {
			driver = new RemoteWebDriver(new URL("http://hub.crossbrowsertesting.com:80/wd/hub"), capabilities);
				}catch(Exception ex){}
	}

}
