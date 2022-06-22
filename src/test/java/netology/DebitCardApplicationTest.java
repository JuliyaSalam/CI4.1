package netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class DebitCardApplicationTest {
    SelenideElement form = $x("//form[contains(@class, form)]");
    SelenideElement notification = $x("//div[@data-test-id=\"notification\"]");

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSubmitRequest() {
        Configuration.holdBrowserOpen = true;

        form.$x(".//span[@data-test-id=\"city\"]//input").setValue("Барнаул");
        //  $("//input[@type=\"date\"]").setValue("2022-06-19");
        form.$x(".//span[@data-test-id=\"name\"]//input").setValue("Юлия");
        form.$x(".//span[@data-test-id=\"phone\"]//input").setValue("+79600000000");
        form.$x(".//label[@data-test-id=\"agreement\"]").click();
        form.$x(".//span[contains(text(), 'Забронировать')]//ancestor::button").click();
        notification.should(visible, Duration.ofSeconds(15));
        notification.$x(".//div[@class='notification__title']").shouldHave(exactText("Успешно!"));
        notification.$x(".//div[@class='notification__content']").shouldHave(exactText("Встреча успешно забронирована на "
        + form.$x(".//span[@data-test-id='date']//input").getValue()));
    }
/*
    @Test
    void shouldSubmitRequestNotValid1() {

        $("[data-test-id=name] input").setValue("Juliya");
        $("[data-test-id=phone] input").setValue("+79600000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[class=input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSubmitRequestNotValid2() {

        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79600000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestNotValid3() {

        $("[data-test-id=name] input").setValue("Юлия");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSubmitRequestNotValid4() {

        $("[data-test-id=name] input").setValue("Юлия");
        $("[data-test-id=phone] input").setValue("+7960000000000000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSubmitRequestNotValid5() {

        $("[data-test-id=name] input").setValue("Юлия");
        $("[data-test-id=phone] input").setValue("+79600000000");
        // $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

 */
}
