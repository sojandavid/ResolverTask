package pages

import framework.WebDriverManager
import groovy.json.JsonSlurper
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class BasePage {

    WebDriver driver
    WebDriverWait wait
    def allConfigData;
    def configData;
    static final int timeOutSeconds = 30

    BasePage(WebDriver driver) {
        allConfigData = new JsonSlurper().parse(new File("""src/test/resources/test-inputs/Configuration.json"""))
        this.driver = driver;
        PageFactory.initElements(driver, this)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSeconds))
    }

    void goToPageByUrl(String url) {
        driver.get(url)
        driver.manage().window().maximize()
    }

    void closeBrowser() {
        WebDriverManager.closeBrowser(driver)
    }

    void clickWebElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element))
        element.click()
    }

    void fillInText(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element))
        element.clear()
        element.sendKeys(value)
    }

    static boolean verifyElementIsDisplayed(WebElement element) {
        return element.isDisplayed()
    }

    static boolean verifyElementIsEnabled(WebElement element) {
        return element.isEnabled()
    }

    static boolean verifyElementIsDisabled(WebElement element) {
        return !element.isEnabled()
    }

    String getElementText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element))
        return element.getText().trim()
    }

    List<String> getElementTexts(List<WebElement> elements) {
        return elements.collect{getElementText(it) }
    }

    void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element))
    }

    static boolean findValueExistInList(List<String> values, value) {
        return value in values
    }
}
