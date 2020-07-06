package ru.netology.web.page;


import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement increaseButton = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"] .button");
    private SelenideElement decreaseButton = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"] .button");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public CartTransfer toTransfer() {
        increaseButton.click();
        return new CartTransfer();
    }

    public CartTransfer fromTransfer() {
        decreaseButton.click();
        return new CartTransfer();
    }

    public int getBalance() {
        String balance = $(".list__item [data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]").getText();
        String[] substring = balance.split(",");
        String sub = substring[substring.length - 1];
        String s = sub.replaceAll("\\D+", "");
        return Integer.parseInt(s);
    }


    public int getExpectedBalance(int transfer) {
        return getBalance() + transfer;
    }

}
