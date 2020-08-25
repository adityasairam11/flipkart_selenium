package helper.Package;


import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import helper.Package.Setup;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


import javax.imageio.ImageIO;

 

public class SeleniumHelper extends Setup {

	// WebDriverWait wait = new WebDriverWait(driver, 20);

	public void findElement(By elementname) {

		try {

			driver.findElement(elementname);
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			System.out.println(elementname + "not present");
		}

		catch (ElementNotFoundException e) {

			System.out.println(elementname + "not found");

		} catch (StaleElementReferenceException e) {

			System.out.println(elementname + "stale element");

		}

	}

	public void clickElement(By elementname) {

		try {

			driver.findElement(elementname).click();
		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			System.out.println(elementname + "not present");
		}

		catch (ElementNotFoundException e) {

			System.out.println(elementname + "not found");

		} catch (StaleElementReferenceException e) {

			System.out.println(elementname + "stale element");

		}

	}

	public void sendtexttoElement(By elementname, String inputtext) {

		try {

			driver.findElement(elementname).sendKeys(inputtext);
		}

		catch (NoSuchElementException e) {

			System.out.println(elementname + "not present");
		}

		catch (ElementNotFoundException e) {

			System.out.println(elementname + "not found");

		} catch (StaleElementReferenceException e) {

			System.out.println(elementname + "stale element");

		}

	}

	public void ExplicitWait(By elementname) {

	}
	
	public static void takeScreenShotMethod(String picturename){
	    try{
	        Thread.sleep(1000);
	        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        ImageIO.write(image, "jpg", new File("C:\\Users\\Adhitya\\Desktop\\screenshots\\"+picturename+".jpg"));
	        //ImageIO.write(image, "jpg", new File(JavaHelper.filepath+picturename+".jpg"));
	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
	public String timestamp() {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
		return timeStamp;
		

	}
	
	public void multiplewindowhandler() {
		
		String parentwindow = driver.getWindowHandle();
		String childwindow;
		
		Set<String> s =driver.getWindowHandles();
		System.out.println("number of windows:" +s);		

			// Now iterate using Iterator
			Iterator<String> I1= s.iterator();

			while(I1.hasNext())
			{

				childwindow = I1.next();


			if(!parentwindow.equals(childwindow))
			{
			driver.switchTo().window(childwindow);

			System.out.println(driver.switchTo().window(childwindow).getTitle());

			//driver.close();
			}

			}
			//switch to the parent window
			//driver.switchTo().window(parent);

			}
		
		}
