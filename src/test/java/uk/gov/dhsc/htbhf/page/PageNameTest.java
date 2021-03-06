package uk.gov.dhsc.htbhf.page;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static uk.gov.dhsc.htbhf.page.ToggleName.ADDRESS_LOOKUP;

class PageNameTest {

    @CsvSource({
            "ARE_YOU_PREGNANT, are you pregnant",
            "CHECK_ANSWERS, check answers",
            "CHILD_DATE_OF_BIRTH, enter your childrens dates of birth",
            "DECISION, decision",
            "DATE_OF_BIRTH, enter date of birth",
            "DO_YOU_HAVE_CHILDREN, do you have children under four years old",
            "EMAIL_ADDRESS, email address",
            "ENTER_CODE, enter code",
            "MANUAL_ADDRESS, manual address",
            "NAME, enter name",
            "NATIONAL_INSURANCE_NUMBER, enter national insurance number",
            "PHONE_NUMBER, phone number",
            "SCOTLAND, do you live in Scotland",
            "IN_SCOTLAND, I live in Scotland",
            "SEND_CODE, send code",
            "TERMS_AND_CONDITIONS, terms and conditions",
            "HOW_IT_WORKS, How it works",
            "ELIGIBILITY, Eligibility",
            "WHAT_YOU_GET, What you’ll get",
            "WHAT_YOU_CAN_BUY, What you can buy",
            "USING_YOUR_CARD, Using your card",
            "APPLY, Apply for Healthy Start",
            "REPORT_A_CHANGE, Report a change",
            "COOKIES, cookies",
            "SERVER_ERROR, Server Error",
            "PRIVACY_NOTICE, privacy notice",
            "PAGE_NOT_FOUND, page not found"
    })
    @ParameterizedTest
    void shouldGetPageName(PageName pageName, String name) {
        assertThat(PageName.toPageName(name)).isEqualTo(pageName);
    }

    @Test
    void shouldFailToGetPageName() {
        Throwable thrown = catchThrowableOfType(() -> PageName.toPageName("foo"), IllegalArgumentException.class);
        assertThat(thrown).hasMessage("No page name found for name: foo");
    }

    @Test
    void shouldHaveNoToggle() {
        assertThat(PageName.HOW_IT_WORKS.hasToggle()).isFalse();
        assertThat(PageName.POSTCODE.hasToggle()).isTrue();
    }

    @Test
    void shouldReturnNoToggle() {
        assertThat(PageName.HOW_IT_WORKS.getToggle()).isEmpty();
        assertThat(PageName.POSTCODE.getToggle()).hasValue(ADDRESS_LOOKUP);
    }
}
