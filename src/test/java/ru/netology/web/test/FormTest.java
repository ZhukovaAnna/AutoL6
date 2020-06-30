package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CartTransfer;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class FormTest {
    @Test
    void shouldTransferMoney() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.toTransfer();
        val cardTransfer = new CartTransfer();
        cardTransfer.Transfer(DataHelper.getCardInfo(String.valueOf(200)));
    }

    @Test
    void shouldErrorIfCardFieldEmpty() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.toTransfer();
        val cardTransfer = new CartTransfer();
        val cardTransferInfo = new DataHelper.CardInfo("200","");
        cardTransfer.invalidTransfer(cardTransferInfo);
    }

    @Test
    void shouldErrorIfAmountEmpty() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.toTransfer();
        val cardTransfer = new CartTransfer();
        val cardTransferInfo = new DataHelper.CardInfo("","5559000000000002");
        cardTransfer.invalidTransfer(cardTransferInfo);
    }

    @Test
    void shouldBeErrorWhenCardIrrelevant() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.fromTransfer();
        val cardTransfer = new CartTransfer();
        val cardTransferInfo = new DataHelper.CardInfo("200","5559000000000005");
        cardTransfer.invalidTransfer(cardTransferInfo);
    }

    @Test
    void shouldNothingTransferIfZero() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.toTransfer();
        val cartTransfer = new CartTransfer();
        val cartTransferInfo = DataHelper.getCardInfo(String.valueOf(0));
        cartTransfer.Transfer(cartTransferInfo);

    }

    @Test
    void shouldErrorIfAmountMore() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        dashboardPage.toTransfer();
        val cartTransfer = new CartTransfer();
        cartTransfer.invalidTransfer(DataHelper.getCardInfo(String.valueOf(15000)));
    }

}
