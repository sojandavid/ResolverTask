package framework

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class WebDriverManager {

    static WebDriver initDriver(boolean headless = false) {
        try{
            return new ChromeDriver();
        }catch(Exception e){
            println("Failed to start the browser")
            e.printStackTrace()
        }
    }

    static void closeBrowser(WebDriver driver) {
        try{
            driver.close()
            driver.quit()
        }catch(Exception e){
            println("Failed to quit the browser and webdriver")
            e.printStackTrace()
        }
    }

}