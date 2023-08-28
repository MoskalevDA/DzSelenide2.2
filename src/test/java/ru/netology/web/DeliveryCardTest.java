package ru.netology.web;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));

    }

    @Test
    public void formCardTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String newDate = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(newDate);
        $("[data-test-id='name'] input").setValue("Ивае Петров");
        $("[data-test-id='phone'] input").setValue("+79126668703");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='notification']").
                shouldBe(Condition.visible, Duration.ofSeconds(15)).
                shouldBe(Condition.text("Встреча успешно забронирована на " + newDate));


    }

    @Test
    public void calendarAutocompleteTest() {
        open("http://localhost:9999/");
       $("[data-test-id='date']").click();
        $("[data-test-id='city'] input").setValue("Ек");
        $(byText("Екатеринбург")).click();

    }


}
