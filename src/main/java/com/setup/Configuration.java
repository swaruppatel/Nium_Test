package com.setup;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Configuration {

    public static WebDriver driver;
    public static String url;
    public static String browserName;

    public static void getBrowser() throws IOException {
        FileInputStream file = new FileInputStream("src/main/resources/TestData.properties");
        Properties properties = new Properties();
        properties.load(file);
        browserName = properties.getProperty("browser");
        url = properties.getProperty("url");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
//            options.addArguments("--headless");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("Start-maximized");
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver = driver;
    }


    public static List<List<String>> flipkartFetchIpadPrice(int n) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement loginPopup = driver.findElement(By.xpath("//div/button[text()='✕']"));
        wait.until(ExpectedConditions.elementToBeClickable(loginPopup));
        loginPopup.click();
        WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search for products, brands and more']"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.sendKeys("ipad", Keys.ENTER);
        WebElement sortElem = driver.findElement(By.xpath("//div[text()='Price -- High to Low']"));
        sortElem.click();
        WebElement grid = driver.findElement(By.cssSelector("[data-id='TABG2DM2GGUQH4XF']"));
        List<WebElement> items = driver.findElements(By.xpath("//div[@class='_3pLy-c row']//div[@class='col col-7-12']/div[text()]"));
        List<WebElement> prices = driver.findElements(By.cssSelector("div[class='_25b18c']"));

//        LinkedHashMap<String, String> map = new LinkedHashMap();
        List<List<String>> al = new ArrayList();
        for (int i = 0; i < items.size(); i++) {
            WebElement price = prices.get(i).findElement(By.cssSelector("div[class='_30jeq3 _1_WHN1']"));
            if (i + 1 <= n) {
                new WebDriverWait(driver, 100).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(items.get(i)));
                try {

                    al.add(new ArrayList(Arrays.asList(items.get(i).getText(), price.getText())));
                } catch (StaleElementReferenceException e) {
                    price = prices.get(i).findElement(By.cssSelector("div[class='_30jeq3 _1_WHN1']"));
                    System.out.println("Searching for element: " + price);
                }
                al.add(new ArrayList(Arrays.asList(items.get(i).getText(), prices.get(i).getText())));
//                map.put(items.get(i).getText(), prices.get(i).getText());
            } else {
                break;
            }
        }
        return al;
    }
}