package helper.Package;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Setup {

	public static String browserchoice;
	public static WebDriver driver;
	public static org.openqa.selenium.support.events.EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public static void initdriver() {

		if (browserchoice == "chrome") {

			String chromedriverpath = "C:\\Users\\Adhitya\\Desktop\\chrome driver\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromedriverpath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}

		else if (browserchoice == "IE")

		{

			String iepath = "C:\\Users\\Adhitya\\Desktop\\IE driver\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", iepath);
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}
		
		e_driver = new org.openqa.selenium.support.events.EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}
}
