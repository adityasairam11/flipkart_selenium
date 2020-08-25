package stepDefinition;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.Package.ExcelUtility;
import helper.Package.SeleniumHelper;
import helper.Package.Setup;
import pom.Pages.Flipkartpages;

public class SmokeTestForCucumber extends Setup {

	SeleniumHelper sh = new SeleniumHelper();
	public static WebElement pwderror;
	WebDriverWait longwait;
	public static String xlfilepath = "C:\\Users\\Adhitya\\eclipse-workspace\\org.cucumb.selenium\\data\\flipkart.xlsx";
	public static ExcelUtility xl;
	public static HashMap<String, Integer> hmap = new HashMap<String, Integer>();
	public static String chosenitem;
	WebDriverWait shortwait;

	@Given("^Login to flipkart application with valid credentials$")
	public void Login_to_flipkart_application_with_valid_credentials() throws Throwable {

		browserchoice = "chrome";
		initdriver();
		driver.get("https://www.flipkart.com/");

	}

	@When("^I enter valid \"([^\"]*)\" and \"([^\"]*)\"$")
	public void I_enter_valid_credentials(String username, String passwd) throws Throwable {

		sh.sendtexttoElement(Flipkartpages.username, username);
		String windowTitle3 = driver.getTitle().toString();
		System.out.println("page title3 is" + windowTitle3);
		// sh.findElement(Flipkartpages.username);
		sh.sendtexttoElement(Flipkartpages.password, passwd);
		Thread.sleep(1000);
		SeleniumHelper.takeScreenShotMethod("image" + sh.timestamp());
		sh.clickElement(Flipkartpages.loginbutton);
		// driver.findElement(By.name("q"));
		// *[@id="container"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input
		try {

			WebDriverWait shortwait = new WebDriverWait(driver, 10);
			String abc = "My Account";
			// Thread.sleep(5000);
			shortwait.until(ExpectedConditions.invisibilityOfElementLocated(Flipkartpages.username));
			shortwait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_2aUbKa")));
			/*
			 * WebElement uname = driver.findElement(By.className("_2aUbKa")); String
			 * a1=uname.getText(); System.out.println(a1+"---");
			 */
			shortwait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("_2aUbKa"), abc));
			System.out.println("abcd");
		} catch (NoSuchElementException e) {
			// pwderror = driver.findElement(By.className("ZAtlA-"));
			System.out.println("login fail");
		} catch (ElementNotFoundException e) {
			// pwderror = driver.findElement(By.className("ZAtlA-"));
			System.out.println("login fail");
		} catch (Exception e) {
			// pwderror = driver.findElement(By.className("ZAtlA-"));
			System.out.println("login fail");
			tearUP();
			System.exit(0);
		}

	}

	@Then("^I validate the outcome wherein user logs in successfully$")
	public void I_validate_the_outcomes_wherein_user_logs_in_successfully() throws Throwable {

		String windowTitle2 = driver.getTitle().toString();
		System.out.println("page title2 is" + windowTitle2);
		String expectedTitle = "Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!";
		assertEquals(windowTitle2, expectedTitle);
	}

	@Then("^I search for a \"([^\"]*)\" and collect the data$")
	public void i_search_for_a_and_collect_the_data(String product) throws Throwable {

		shortwait = new WebDriverWait(driver, 10);
		shortwait.until(ExpectedConditions.visibilityOfElementLocated(Flipkartpages.searchbutton));
		sh.sendtexttoElement(Flipkartpages.searchbutton, product + Keys.ENTER);
		shortwait.until(ExpectedConditions.visibilityOfElementLocated(Flipkartpages.sortbyPopularity));
		sh.clickElement(Flipkartpages.sortbyPopularity);
		shortwait.until(ExpectedConditions.visibilityOfElementLocated(Flipkartpages.sortbyPopularity));
		SeleniumHelper.takeScreenShotMethod("image" + sh.timestamp());
		shortwait.until(ExpectedConditions.visibilityOfElementLocated(Flipkartpages.totalResults));
		String totalcount = driver.findElement(Flipkartpages.totalResults).getText();
		System.out.println("totalcount::" + totalcount);
		totalcount = totalcount.substring(12).replaceAll(" ", "").replaceAll("[^\\d-]", "").substring(0, 2);
		System.out.println("totalcountnew::" + totalcount);

		xl = new ExcelUtility(xlfilepath);

		int b = Integer.parseInt(totalcount);
		b = b + 1;
		for (int i = 2; i <= b; i++) {

			WebElement item = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[2]/div[1]/div[2]/div["
					+ i + "]/div/div/div/a/div[2]/div[1]/div[1]"));
			String productname = item.getText();
			System.out.println(productname);
			
			WebElement price = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[2]/div[1]/div[2]/div["
					+ i + "]/div/div/div/a/div[2]/div[2]/div[1]/div/div[1]"));
			String priceamount = price.getText().replaceAll("[^0-9]", "");
			int priceamt = Integer.parseInt(priceamount);
			
			System.out.println(priceamount);
			try {
				driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[2]/div[1]/div[2]/div[" + i
						+ "]/div/div/div/a/div[1]/div[1]/div[2]/span"));
				System.out.println("item not in stock");
				// hmap.put(productname, priceamt);
				xl.putCellData("Sheet1", "Productname", i - 1, productname);
				xl.putCellData("Sheet1", "Price", i - 1, priceamount);
				xl.putCellData("Sheet1", "Availability", i - 1, "Out of stock");
			} catch (NoSuchElementException e) {
				System.out.println("1 fail");
			} catch (ElementNotFoundException e) {
				System.out.println("2 fail");
			} catch (Exception e) {
				System.out.println("3 fail");
				// if the item is in stock add to map
				hmap.put(productname, priceamt);
				xl.putCellData("Sheet1", "Productname", i - 1, productname);
				xl.putCellData("Sheet1", "Price", i - 1, priceamount);
				xl.putCellData("Sheet1", "Availability", i - 1, "In stock");
			}
			

		}

		for (Entry<String, Integer> entry : hmap.entrySet()) {
			System.out.println(entry.getKey() + "::" + entry.getValue());

		}

	}

	@Then("^I want to sort the results and choose the product with lowest price$")
	public void i_want_to_sort_the_results_and_choose_the_product_with_lowest_price() throws Throwable {

		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(hmap.entrySet());

		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		System.out.println("The sorted result1 is::" + list.get(0));
		chosenitem = list.get(0).getKey().toString();
		System.out.println("The sorted result2 is::" + chosenitem);
		// chosenitem = chosenitem.substring(0,chosenitem.indexOf("="));
		// System.out.println("The sorted result3 is::" +chosenitem);

	}

	@Then("^I want to search the chosen product and add to cart$")
	public void i_want_to_search_the_chosen_product_and_add_to_cart() throws Throwable {

		
		try{
			
		shortwait = new WebDriverWait(driver, 10);		
		WebDriverWait newwindowwait = new WebDriverWait(driver, 10);
		shortwait.until(ExpectedConditions.visibilityOfElementLocated(Flipkartpages.searchbutton));
		driver.findElement(Flipkartpages.searchbutton).clear();
		sh.sendtexttoElement(Flipkartpages.searchbutton, Keys.chord(Keys.CONTROL,"a", Keys.DELETE)+chosenitem + Keys.ENTER);
		shortwait.until(ExpectedConditions.visibilityOfElementLocated(Flipkartpages.selectedproduct));
		sh.clickElement(Flipkartpages.selectedproduct);
		//Thread.sleep(10000);
		sh.multiplewindowhandler();
		newwindowwait.until(ExpectedConditions.elementToBeClickable(Flipkartpages.addtocart));
		/*String add2cart = driver.findElement(Flipkartpages.addtocart).getText();
		System.out.println(add2cart + " add2cart txt");*/
		sh.clickElement((Flipkartpages.addtocart));
		}catch(org.openqa.selenium.TimeoutException e) {
			e.printStackTrace();
			System.out.println("element not present");
			sh.findElement(Flipkartpages.notifyme);
			System.out.println("Unfortunately, Out of stock!!");
		}catch(org.openqa.selenium.NoSuchElementException e) {
			System.out.println("element not present");
			sh.findElement(Flipkartpages.notifyme);
			System.out.println("Unfortunately, Out of stock!!");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("fail!!!!");
		}
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		System.out.println("after adding to cart  :"+driver.getTitle());
		try {
		shortwait.until(ExpectedConditions.visibilityOfElementLocated(Flipkartpages.addedproduct));
		String actualproduct = driver.findElement(Flipkartpages.addedproduct).getText();
		assertEquals(actualproduct, chosenitem);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@After
	public void tearUP() {
		 driver.close();
		 driver.quit();
	}

}
