package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CartTransfer {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement balance = $("[data-test-id = amount] input");
    private SelenideElement from = $("[data-test-id = from] input");
    private SelenideElement transferButton = $("[data-test-id = action-transfer]");
    private SelenideElement error = $("[data-test-id = error-notification]");

    public CartTransfer(){
        heading.shouldBe(visible);
    }

    public DashboardPage transfer(DataHelper.CardInfo info) {
        balance.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        balance.doubleClick().sendKeys(Keys.DELETE);
        balance.setValue(info.getBalance());
        from.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        from.doubleClick().sendKeys(Keys.DELETE);
        from.setValue(info.getCard());
        transferButton.click();
        return new DashboardPage();
    }

    public void invalidTransfer(DataHelper.CardInfo info) {
        balance.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        balance.doubleClick().sendKeys(Keys.DELETE);
        balance.setValue(info.getBalance());
        from.setValue(info.getCard());
        from.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        from.doubleClick().sendKeys(Keys.DELETE);
        transferButton.click();
        error.shouldBe(visible);
    }
}

