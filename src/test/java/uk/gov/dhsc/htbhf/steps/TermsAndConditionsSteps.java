package uk.gov.dhsc.htbhf.steps;

import io.cucumber.java.en.When;
import uk.gov.dhsc.htbhf.page.CheckAnswersPage;
import uk.gov.dhsc.htbhf.page.TermsAndConditionsPage;

/**
 * Steps for the Terms and Conditions page after the claimant has checked their details.
 */
public class TermsAndConditionsSteps extends BaseSteps {

    private CheckAnswersPage checkAnswersPage;
    private TermsAndConditionsPage termsAndConditionsPage;

    @When("/^I accept the terms and conditions and submit my application$/")
    public void acceptTermsAndConditionsAndSubmitApplication() {
        wireMockManager.setupWireMockMappingsWithStatus("ELIGIBLE");
        checkAnswersPage = getPages().getCheckAnswersPage();
        checkAnswersPage.clickContinue();
        acceptTsAndCsAndSubmitApplication();
    }

    private void acceptTsAndCsAndSubmitApplication() {
        termsAndConditionsPage = getPages().getTermsAndConditionsPage();
        termsAndConditionsPage.clickAcceptTermsAndConditionsCheckBox();
        termsAndConditionsPage.clickContinue();
    }

}
