package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.How

class TestPage extends BasePage{

    private String url;
    private String username;
    private String password;
    List<String> test2ListBadgeValues = ["3", "6", "9"]
    List<String> test2ListItemValues = ["List Item 1 3", "List Item 2 6", "List Item 3 9"]
    List<String> test3DropdownValues = ["Option 1", "Option 2", "Option 3"]
    List<String> columnNames = ["First Name", "Last Name", "App Name"]

    @FindBy(how = How.CSS, using = "button[type='Submit']")
    WebElement signInButton
    @FindBy(how = How.ID, using = "inputEmail")
    WebElement emailAddressInputBox
    @FindBy(how = How.ID, using = "inputPassword")
    WebElement passwordInputBox
    @FindBy(how = How.XPATH, using = "//div[@id='test-2-div']//ul/li/span")
    List<WebElement> test2ListBadgeElement
    @FindBy(how = How.XPATH, using = "//div[@id='test-2-div']//ul/li")
    List<WebElement> test2ListItemElement
    @FindBy(how = How.XPATH, using = "//div[@class='dropdown']/button")
    WebElement test3DropdownButton
    @FindBy(how = How.XPATH, using = "//div[@class='dropdown']/button")
    WebElement test3DropdownElement
    @FindBy(how = How.XPATH, using = "//div[@class='dropdown-menu show']/a[text()='Option 3']")
    WebElement test3DropdownOption3
    @FindBy(how = How.XPATH, using = "//div[@id='test-4-div']/button[1]")
    WebElement test4Button1
    @FindBy(how = How.XPATH, using = "//div[@id='test-4-div']/button[2]")
    WebElement test4Button2
    @FindBy(how = How.ID, using = "test5-button")
    WebElement test5Button
    @FindBy(how = How.ID, using = "test5-alert")
    WebElement test5ErrorMessage
    @FindBy(how = How.XPATH, using = "//table//tbody//tr//td[1]")
    WebElement test6TableColumnElements
    //    @FindBy(how = How.ID, using = "inputPassword")
//    WebElement passwordInputBox
    //    @FindBy(how = How.ID, using = "inputPassword")
//    WebElement passwordInputBox
    //    @FindBy(how = How.ID, using = "inputPassword")
//    WebElement passwordInputBox
    //    @FindBy(how = How.ID, using = "inputPassword")
//    WebElement passwordInputBox
    //    @FindBy(how = How.ID, using = "inputPassword")
//    WebElement passwordInputBox
    //    @FindBy(how = How.ID, using = "inputPassword")
//    WebElement passwordInputBox
    //    @FindBy(how = How.ID, using = "inputPassword")
//    WebElement passwordInputBox




    TestPage(WebDriver driver) {
        super(driver)
        configData = allConfigData["dev"]
        url = configData["url"]
        username = configData["username"]
        password = configData["password"]
    }

    void goToPageByUrl() {
        File absolutePath = new File(url)
        url = "file:///"+absolutePath.getAbsolutePath()
        goToPageByUrl(url);
    }

    boolean isOnTestPage() {
        return signInButton.isDisplayed()
    }

    void fillInUserName() {
        fillInText(emailAddressInputBox, username)
    }

    void fillInPassword() {
        fillInText(passwordInputBox, password)
    }

    boolean verifyTest2ListItemsValues() {
        getElementTexts(test2ListBadgeElement) == test2ListBadgeValues
    }

    List<String> getTableColumnValues(String columnName) {
        int getColumnNumber = columnNames.indexOf(columnName)
        List<WebElement> webElements = driver.findElements(By.xpath("//table//tbody//tr//td[${getColumnNumber+1}]"))
        return getElementTexts(webElements)
    }

    WebElement getTableCellElement(String columnName, String cellValue) {
        int getColumnNumber = columnNames.indexOf(columnName)
        List<WebElement> webElements = driver.findElements(By.xpath("//table//tbody//tr//td[${getColumnNumber+1}]"))
        for(WebElement element: webElements)
        {
            if(getElementText(element) == cellValue) {
                 return element
            }
         }
    }

    String getCellValueByRow(int rowNumber, String columnName) {
        int getColumnNumber = columnNames.indexOf(columnName)
        WebElement webElement = driver.findElement(By.xpath("//table//tbody//tr[${rowNumber}]//td[${getColumnNumber+1}]"))
        return getElementText(webElement)
    }

}
