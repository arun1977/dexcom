package steps;

import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class EventCreationSteps {
    @When("^An event is created\\.$")
    public void anEventIsCreated() throws Throwable {
        AppiumDriver appiumDriver = getAppium();
        if (!validateScreens(appiumDriver)) {
            throw new Exception("ERROR - Required screens not present in the app, aborting test");
        }

        WebElement element = appiumDriver.findElement(By.id("eventCell-${eventID}"));
        if (element.isDisplayed()) {
            System.out.println("Event Dispayed in table with ID: " +element.getText());
        }
        element = appiumDriver.findElement(By.id("eventCell-${eventID}-Name"));
        if (element.isDisplayed()) {
            System.out.println("Event Dispayed in table with Name: " +element.getText());
        }
        element = appiumDriver.findElement(By.id("eventCell-${eventID}-Type"));
        if (element.isDisplayed()) {
            System.out.println("Event Dispayed in table with Type: " +element.getText());
        }
    }

    @When("^An event is updated\\.$")
    public void anEventIsUpdated() throws Throwable {
        AppiumDriver appiumDriver = getAppium();
        if (!validateScreens(appiumDriver)) {
            throw new Exception("ERROR - Required screens not present in the app, aborting test");
        }
    }

    private AppiumDriver getAppium(){
        AppiumDriver appiumDriver = null;
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "iOS");
        desiredCapabilities.setCapability("deviceName", "iPhone6");
        desiredCapabilities.setCapability("browserName", "Browser");
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AppiumDriver(url, desiredCapabilities);
        }

        catch (MalformedURLException mu) {
            System.out.println("Exception occurred: " +mu.getMessage());
        }
        return appiumDriver;
    }

    private boolean validateScreens(AppiumDriver appium) {
        WebElement element = appium.findElement(By.id("mainView"));
        if (!element.isDisplayed()) {
            System.out.println("ERROR - Main screen not displayed");
            return false;
        }
        element = appium.findElement(By.id("tableView"));
        if (!element.isDisplayed()){
            System.out.println("ERROR - Table view not displayed");
            return false;
        }
        return true;
    }
}
