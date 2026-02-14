package com.fast.automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FastAutomation {

	public static void main(String[] args) {

		ChromeOptions ChromeOptions = new ChromeOptions();
		ChromeOptions.addArguments("----start-maximized");
		WebDriver wd = new ChromeDriver(ChromeOptions);
		wd.get("https://fast.com/");
		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(20));

		By SpeedValueLocator = By.id("speed-value");
		By SpeedunitsLocator = By.id("speed-units");
		
            String className;
		while (true) {

			WebElement speedvalueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SpeedValueLocator));
			WebElement speedUnitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SpeedunitsLocator));

			System.out.println(speedvalueElement.getText() + "  " + speedUnitElement.getText());
			
			className = speedvalueElement.getAttribute("class");
			
			if(className  !=null && className.contains("succeeded")) {
				break;
			}
			
		}
		WebElement speedvalueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SpeedValueLocator));
		WebElement speedUnitElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SpeedunitsLocator));

		System.out.println("*********************FINAL SPEED ************");
		
		System.out.println(speedvalueElement.getText() + "  " + speedUnitElement.getText());

	}
}