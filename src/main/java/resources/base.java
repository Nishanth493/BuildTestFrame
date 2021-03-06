package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class base {

	public static WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"/Users/gouravdas/git/BuildTestFrame/src/main/java/resources/Untitled1");

		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/gouravdas/chromedriver");
			driver = new ChromeDriver();

		}

		if (browserName.equals("firebox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/gouravdas/geckodriver");
			driver = new FirefoxDriver();

		}

		if (browserName.equals("IE")) {

			System.setProperty("webdriver.MicrosoftWebDriver.driver",
					"/Users/gouravdas/chromedriver/MicrosoftWebDriver");
			driver = new InternetExplorerDriver();

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;

	}

	public void getScreenShot(String result) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("/Users/gouravdas/BuildTestFrame" + result + "screenshot.png"));
	}

}
