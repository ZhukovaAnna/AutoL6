package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String balance;
        private String card;
    }

    public static CardInfo getCardInfo(String balance) {
        return new CardInfo(balance, "5559000000000002");
    }

    public static CardInfo getReturnCardInfo(CardInfo original) {
        return new CardInfo(original.balance, "5559 0000 0000 0001");
    }

}

