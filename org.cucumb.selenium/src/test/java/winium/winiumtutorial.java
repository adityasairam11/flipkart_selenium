package winium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class winiumtutorial {
	
	public static void main(String[] args) throws MalformedURLException {
		
		DesktopOptions option = new DesktopOptions();
		
		option.setApplicationPath("C:\\Windows\\System32\\calc.exe");
		
		WiniumDriver driver =  new WiniumDriver(new URL("http://localhost:9999"),option);
		
		driver.findElement(By.name("7")).click();
		
		driver.findElement(By.name("Multiply")).click();
		
		//number 8
		driver.findElement(By.id("138")).click();
		
		driver.findElement(By.name("Equals")).click();
		
		String result = driver.findElement(By.id("150")).getAttribute("Name");
		
		System.out.println(result);
		
		
		
		
		
		
	}

}
