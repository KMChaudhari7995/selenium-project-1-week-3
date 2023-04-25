package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String menu;
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setBaseUrl() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        //1.1
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //1.2
        mouseHoverAndClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //1.3 and //1.4

        List<WebElement> beforeSelectElementList = driver.findElements(By.xpath("//div/h2[@class='product-title']"));
        List<String> beforeSelectElementList1 = new ArrayList<>();
        for (WebElement list : beforeSelectElementList) {
            beforeSelectElementList1.add(String.valueOf(list.getText()));
        }

        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");
        List<WebElement> afterSelectElementList = driver.findElements(By.xpath("//h2[@class='product-title']"));
        List<String> afterSelectElementList1 = new ArrayList<>();
        for (WebElement list : afterSelectElementList) {
            afterSelectElementList1.add(String.valueOf(list.getText()));
        }

        Collections.sort(beforeSelectElementList1);
        //  Collections.reverse(afterSelectElementList1);
        Assert.assertEquals("Product is not displayed ", beforeSelectElementList1, afterSelectElementList1);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //2.1
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        //mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //2.2
        mouseHoverAndClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        //2.3
        selectFromDropDownMenu(By.xpath("//select[@id='products-orderby']"), "Name: A to Z");
        //2.4
 /*   Thread.sleep(1000);
    clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        clickOnElement(By.xpath("//div[@class='product-item' and @data-productid='1']//button[@xpath='1'])"));
 */
        //2.4
        Thread.sleep(2000);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        //2.5
        String expectedText = "Build your own computer";
        WebElement actualText = driver.findElement(By.xpath("//div[@class='product-name']"));
        String actualText1 = actualText.getText();
        Assert.assertEquals("Error Message,Product is not added to card", expectedText, actualText1);
        //2.6
        selectFromDropDownMenu(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        //2.7
        selectFromDropDownMenu(By.xpath("/html[1]/body[1]/div[6]/div[3]/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[6]/dl[1]/dd[2]/select[1]"), "8GB [+$60.00]");
        //2.8
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        //2.9
        /*        clickOnElement(By.xpath("//label[normalize-space()='Vista Premium"));*/
        clickOnElement(By.xpath("//label[text()='Vista Premium [+$60.00]']"));
        //2.10
        //clickOnElement(By.xpath("//label[normalize-space()='Microsoft Office"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        //2.11
        String expectedMessage = "$1,475.00";
        String actualMessage = driver.findElement(By.xpath("//span[text()='$1,475.00']")).getText();
        verifyElementMethod(expectedMessage, actualMessage);
        //2.12
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        //2.13
        String expectedMessage1 = "The product has been added to your shopping cart";
        String actualMessage1 = driver.findElement(By.xpath("//div[@class='bar-notification success']/p")).getText();
        verifyElementMethod(expectedMessage1, actualMessage1);
        //
        Thread.sleep(1000);
        clickOnElement(By.xpath("//div[@class='bar-notification success']/span"));
        //2.14
        mouseHoverOnElement(By.xpath("//span[@class='cart-label']"));
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        //2.15
        String expectedCart = "Shopping cart";
        String actualCart = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
        verifyElementMethod(expectedCart, actualCart);
        Thread.sleep(2000);
        //2.16
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//button[@class='button-2 update-cart-button']"));
        //2.17
        String expectedTotal = "$2,950.00";
        String actualTotal = driver.findElement(By.xpath("//td[@class='subtotal']/span[text()='$2,950.00']")).getText();
        verifyElementMethod(expectedTotal, actualTotal);
        //2.18
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //2.19
        clickOnElement(By.xpath("//button[@id='checkout']"));
        //2.20
        String expectedWelcome = "Welcome, Please Sign In!";
        String actualWelcome = driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText();
        verifyElementMethod(expectedWelcome, actualWelcome);
        //2.21
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));
        //2.22
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "km");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Cahudhari");
        sendTextToElement(By.id("BillingNewAddress_Email"), "km799512@gmail.com");
        selectFromDropDownMenu(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "84 B Queen");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "wd17 2fc");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07424677891");
        //2.23
        Thread.sleep(2000);
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']/button[@class='button-1 new-address-next-step-button']"));
        //2.24
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        //2.25
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        //2.26
        selectRadioButton(By.xpath("//input[@id='paymentmethod_1']"));
        //2.27
        //selectFromDropDownMenu(By.xpath("//select[@id='CreditCardType']"), "Master card");
        Thread.sleep(1000);
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        //2.28
        sendTextToElement(By.id("CardholderName"), "Miss km Chaudhari");
        sendTextToElement(By.id("CardNumber"), "3256754579765121");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireMonth']"), "07");
        selectFromDropDownMenu(By.xpath("//select[@id='ExpireYear']"), "2026");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "333");
        //Click on “CONTINUE”
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        // Verify “Payment Method” is “Credit Card”
        String expectedPaymenMethod = "Credit Card";
        String actualPaymentMethod = driver.findElement(By.xpath("//li[@class='payment-method']/span[@class='value']")).getText();
        Thread.sleep(1000);
        Assert.assertEquals("not credit card", expectedPaymenMethod, actualPaymentMethod);
        // Verify “Shipping Method” is “Next Day Air”
        String expectedShippingMethod = "Next Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//li[@class='shipping-method']/span[@class='value']"));
        Thread.sleep(1000);
        Assert.assertEquals("not next day air", expectedShippingMethod, actualShippingMethod);
        // Verify Total is “$2,950.00”
        String expectedTotalAmount = "$2,950.00";
        String actualTotalAmount = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        // Click on “CONFIRM”
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));
        //define expected
        String expectedThankYou = "Thank you";
        Thread.sleep(1000);
        String actualThankYou = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        //Verify the Text “Thank You”
        Thread.sleep(1000);
        Assert.assertEquals("Thank you not displayed", expectedThankYou, actualThankYou);
        //define expected
        String expectedSuccessfullyProcessed = "Your order has been successfully processed!";
        String actualSuccessfullyProcessed = getTextFromElement(By.xpath("//div[@class='section order-completed']/div[@class='title']/strong"));
        // Verify the message “Your order has been successfully processed!”
        Assert.assertEquals("Not processed", expectedSuccessfullyProcessed, actualSuccessfullyProcessed);
        // Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));
        // Verify the text “Welcome to our store”
        String expectedWelcomeMessage = "Welcome to our store";
        String actualWelcomeMessage = getTextFromElement(By.xpath("//div[@class='topic-block-title']/h2"));
        //Verify the message “Your order has been successfully processed!”
        Thread.sleep(1000);
        Assert.assertEquals("Not successfully processed", expectedWelcomeMessage, actualWelcomeMessage);
    }



    @After
    public void tearDown()
    {
         driver.close();
    }
}
