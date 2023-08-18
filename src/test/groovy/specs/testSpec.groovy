package specs

import framework.WebDriverManager
import org.openqa.selenium.WebDriver
import pages.TestPage
import spock.lang.Specification

class testSpec extends Specification{


    private static WebDriver driver;
    private static TestPage testPage;

    void setupSpec() {
        println "Execution Started"
        driver = WebDriverManager.initDriver();
        testPage = new TestPage(driver)
        testPage.goToPageByUrl()

    }

    void setup() {
    }

    void cleanup() {
    }

    void cleanupSpec() {
        testPage.closeBrowser()
        println "Execution Completed"
    }

    def "User can validate email, address and login fields@Test01"() {
        given: "User is on homePage"
        with(testPage) {
            //Navigate to the home page
            isOnTestPage()
        }

        expect: "Verify the email address, password and login fields are present "
        verifyAll(testPage) {
            //Assert that both the email address and password inputs are present as well as the login button
            verifyElementIsDisplayed(emailAddressInputBox)
            verifyElementIsDisplayed(passwordInputBox)
            verifyElementIsDisplayed(signInButton)
        }

        and:"User enters the username and password values on the Test Page"
        with(testPage) {
            //Enter in an email address and password combination into the respective fields
            fillInUserName()
            fillInPassword()
            clickWebElement(signInButton)
        }
    }

    def "User can validate list items and its badge values@Test02"() {
        given: "User is on homePage"
        with(testPage) {
            //Navigate to the home page
            isOnTestPage()
        }

        expect: "Verify there are three values inside the list, second item value set to 'Listen Item 2' and the second item badge value is 6"
        verifyAll(testPage) {
            //In the test 2 div, assert that there are three values in the listgroup
            verifyTest2ListItemsValues()
            //Assert that the second list item's value is set to "List Item 2"
            getElementTexts(test2ListItemElement)[1] == test2ListItemValues[1]
            //Assert that the second list item's badge value is 6
            getElementTexts(test2ListBadgeElement)[1] == test2ListBadgeValues[1]
        }
    }

    def "User can validate dropdown and its options@Test03"() {
        given: "User is on homePage"
        with(testPage) {
            //Navigate to the home page
            isOnTestPage()
        }

        expect: "Verify Option 1 is the default selected value"
        verifyAll(testPage) {
            //In the test 3 div, assert that "Option 1" is the default selected value
            getElementText(test3DropdownButton) == test3DropdownValues[0]
        }

        when: "User click the Test 3 dropdown"
        with(testPage) {
            clickWebElement(test3DropdownButton)
        }

        then: "Verify the dropdown is enabled"
        verifyAll(testPage) {
            verifyElementIsDisplayed(test3DropdownOption3)
        }

        and: "User select the option 3 on the dropdown"
        with(testPage) {
            //Select "Option 3" from the select list
            clickWebElement(test3DropdownOption3)
        }

        then: "Verify the Option 3 is selected from the dropdown"
        verifyAll(testPage) {
            getElementText(test3DropdownElement) == test3DropdownValues[2]
        }
    }

    def "User can validate button 1 is enabled and button 2 is disabled@Test04"() {
        given: "User is on homePage"
        with(testPage) {
            //Navigate to the home page
            isOnTestPage()
        }

        expect: "Verify Button 1 is enabled and Button is disabled"
        verifyAll(testPage) {
            //In the test 4 div, assert that the first button is enabled
            verifyElementIsEnabled(test4Button1)
            //the second button is disabled
            verifyElementIsDisabled(test4Button2)
        }
    }

    def "User can validate error message is displayed@Test05"() {
        given: "User is on homePage"
        with(testPage) {
            //Navigate to the home page
            isOnTestPage()
        }

        and: "User wait for button to be displayed"
        with(testPage) {
            //In the test 5 div, wait for a button to be displayed (note: the delay is random)
            waitForElementToBeClickable(test5Button)
        }

        when: "User click the test5 Button"
        with(testPage) {
            //click the button
            clickWebElement(test5Button)
        }

        then: "Verify the error message is displayed"
        verifyAll(testPage) {
            //Once you've clicked the button, assert that a success message is displayed
            getElementText(test5ErrorMessage) == "You clicked a button!"
            //Assert that the button is now disabled
            verifyElementIsDisabled(test5Button)
        }

    }

    def "User can validate the table@Test06"() {
        given: "User is on homePage"
        with(testPage) {
            //Navigate to the home page
            isOnTestPage()
        }

        expect: "Verify all the cell values exist on the table"
        verifyAll(testPage) {
            //Write a method that allows you to find the value of any cell on the grid
            findValueExistInList(getTableColumnValues("First Name"), "Brietta")
            findValueExistInList(getTableColumnValues("First Name"), "Griff")
            findValueExistInList(getTableColumnValues("First Name"), "Thadeus")
            findValueExistInList(getTableColumnValues("Last Name"), "Tinmouth")
            findValueExistInList(getTableColumnValues("Last Name"), "Carswell")
            findValueExistInList(getTableColumnValues("Last Name"), "Posselt")
            findValueExistInList(getTableColumnValues("App Name"), "Cardguard")
            findValueExistInList(getTableColumnValues("App Name"), "It")
            findValueExistInList(getTableColumnValues("App Name"), "Ventosanzap")
            //Use the method to find the value of the cell at coordinates 2, 2 (staring at 0 in the top left corner)
            getCellValueByRow(3,"App Name") == "Ventosanzap"
            getElementText(getTableCellElement("App Name", "Ventosanzap")) == "Ventosanzap"
        }
    }

}


