package com.yatra.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YatraAutomationScript {
	public static void main(String[] args) {

		ChromeOptions ChromeOptions = new ChromeOptions();
		ChromeOptions.addArguments("--disable-notifications");

		// Step 1: Launch the browser!
		WebDriver wd = new ChromeDriver(ChromeOptions);
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(20));

		// step 2: load the page for us
		wd.get("https://www.yatra.com/");
		// Maximize the browser window
		wd.manage().window().maximize();
		
		//  check for pop up 
		By popUpLocator = By.xpath("//div[contains(@class,'style_popup')][1]");
		try {
		WebElement popUpElement= wait.until(ExpectedConditions.visibilityOfElementLocated(popUpLocator));
		WebElement crossButton =popUpElement.findElement(By.xpath(".//img[@alt='cross']"));
		wait.until(ExpectedConditions.elementToBeClickable(crossButton));
		crossButton.click();
		
		}
		catch (Exception e) {
			System.out.println("Pop up not shown on the screen!!!");
		}
		

		By departureDateButtonLocator = By
				.xpath("//div[@aria-label=\"Departure Date inputbox\" and  @role=\"button\" ]");
		WebElement departureDateButton = wait
				.until(ExpectedConditions.elementToBeClickable(departureDateButtonLocator));
		departureDateButton.click();

		WebElement currentmonthcalenderWebelement = selectTheMonthFromCalendar(wait, 0); // current month
		WebElement nextmonthcalenderWebelement = selectTheMonthFromCalendar(wait, 1); // next month
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		String lowestpriceForCurrentMonth=getmelowestprice(currentmonthcalenderWebelement);
		String lowestpriceForNextMonth=getmelowestprice(nextmonthcalenderWebelement);
		System.out.println(lowestpriceForCurrentMonth);
		System.out.println(lowestpriceForNextMonth);
		compareTwoMonthsPrices(lowestpriceForCurrentMonth,lowestpriceForNextMonth);

	}

	public static String getmelowestprice(WebElement monthWebElement) {

		// WebElement febCalendarWebElement = selectTheMonthFromCalendar(wait, 0);

		By priceLocator = By.xpath(".//span[contains(@class, \"custom-day-content\")]");

		List<WebElement> febDatesList = monthWebElement.findElements(priceLocator);

		int lowestPrice = Integer.MAX_VALUE;
		WebElement PriceElement = null;

		for (WebElement price : febDatesList) {
			String pricingString = price.getText();
			if (pricingString.length() > 0) {
				pricingString = pricingString.replace("₹", "").replace(",", "");
				System.out.println(pricingString);

				int priceInt = Integer.parseInt(pricingString);
				if (priceInt < lowestPrice) {
					lowestPrice = priceInt;
					PriceElement = price;
				}
			}
		}
		WebElement dateElement = PriceElement.findElement(By.xpath(".//..//.."));

		String result = dateElement.getAttribute("aria-label") + "---Price is Rs" + lowestPrice;
		return result;
	}

	public static WebElement selectTheMonthFromCalendar(WebDriverWait wait, int index) {
		By CalendarMonthsLocator = By.xpath("//div[@class=\"react-datepicker__month\"]");
		List<WebElement> calendarMonthsList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CalendarMonthsLocator));
		System.out.println(calendarMonthsList.size());

		WebElement monthCalendarWebelement = calendarMonthsList.get(index);
		return monthCalendarWebelement;
	} 
	public static void compareTwoMonthsPrices(String currentMonthPrice , String nextmonthprice ) {
		
		int currentMonthsRSIndex = currentMonthPrice.indexOf("Rs");
		int  nextMonthRSIndex= nextmonthprice.indexOf("Rs");
		System.out.println(currentMonthsRSIndex);
		System.out.println(nextMonthRSIndex);
		
		String currentPrice = currentMonthPrice.substring(currentMonthsRSIndex + 2);
		String nextprice = nextmonthprice.substring(nextMonthRSIndex + 2);
		
		
		int current = Integer.parseInt(currentPrice);
		int next = Integer.parseInt(nextprice);
		
		if(current <next) {
			System.out.println("The lowest price for two months is " + current);
		} else if(current == next) {
			System.out.println("Price is same for both months! choose whatever you prefer!!");
		}
		else {
			System.out.println("The lowest price for two months is " + next);

		}
		
	}
}
