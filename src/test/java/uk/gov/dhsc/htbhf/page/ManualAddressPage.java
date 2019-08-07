package uk.gov.dhsc.htbhf.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.dhsc.htbhf.page.component.InputField;

/**
 * Page object for the enter manual address page
 */
public class ManualAddressPage extends SubmittablePage {

    public static final String ADDRESS_LINE_1_INPUT_ID = "address-line-1";
    public static final String ADDRESS_LINE_2_INPUT_ID = "address-line-2";
    public static final String TOWN_OR_CITY_INPUT_ID = "town-or-city";
    public static final String POSTCODE_INPUT_ID = "postcode";
    private final InputField line1InputField;
    private final InputField line2InputField;
    private final InputField townOrCityInputField;
    private final InputField postcodeInputField;

    public ManualAddressPage(WebDriver webDriver, String baseUrl, WebDriverWait wait) {
        super(webDriver, baseUrl, wait);
        line1InputField = new InputField(webDriver, ADDRESS_LINE_1_INPUT_ID);
        line2InputField = new InputField(webDriver, ADDRESS_LINE_2_INPUT_ID);
        townOrCityInputField = new InputField(webDriver, TOWN_OR_CITY_INPUT_ID);
        postcodeInputField = new InputField(webDriver, POSTCODE_INPUT_ID);
    }

    @Override
    String getPath() {
        return "/manual-address";
    }

    @Override
    String getPageName() {
        return "address";
    }

    @Override
    String getPageTitle() {
        return "GOV.UK - What’s your address?";
    }

    public void enterAddressLine1(String line1) {
        line1InputField.enterValue(line1);
    }

    public void enterAddressLine2(String line2) {
        line2InputField.enterValue(line2);
    }

    public void enterTownOrCity(String townOrCity) {
        townOrCityInputField.enterValue(townOrCity);
    }

    public void enterPostcode(String postcode) {
        postcodeInputField.enterValue(postcode);
    }
}