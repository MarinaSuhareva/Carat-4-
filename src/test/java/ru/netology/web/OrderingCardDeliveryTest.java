package ru.netology.web;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderingCardDeliveryTest {
    String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @Test
    void successfullyTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Санкт-Петербург");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Марина Цветаева-Огарева");
        $("[data-test-id = 'phone'] input").setValue("+79876543210");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на " + date));

    }

    @Test
    void typoCityTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Санкт-Питербург");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Марина Цветаева");
        $("[data-test-id = 'phone'] input").setValue("+79999999999");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        //$(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        //$("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на " + date));
        $("[data-test-id='city'] .input__sub").should(exactText("Доставка в выбранный город недоступна"));
    }


    @Test
    void typoCityTest2() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Новосибирск ");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Марина Цветаева");
        $("[data-test-id = 'phone'] input").setValue("+78888888888");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        //$(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        //$("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на " + date));
        $("[data-test-id='city'] .input__sub").should(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void checkBoxTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Санкт-Петербург");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Марина Цветаева");
        $("[data-test-id = 'phone'] input").setValue("+79999999999");
        //$("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        //$(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        //$("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на " + date));
        $(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).
                should(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }


    @Test
    void phoneTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Новосибирск");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Марина Цветаева");
        $("[data-test-id = 'phone'] input").setValue("+80000000000");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").
                should(exactText("Встреча успешно забронирована на " + date));

    }

    @Test
    void nameTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Новосибирск");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Марина");
        $("[data-test-id = 'phone'] input").setValue("+79876543210");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").
                should(exactText("Встреча успешно забронирована на " + date));

    }

    @Test
    void nameTest2() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Новосибирск");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Фёдорова Оксана");
        $("[data-test-id = 'phone'] input").setValue("+79876543210");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        // $(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        // $("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на " + date));
        $("[data-test-id='name'] .input__sub").
                should(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    void latinAlphabetTest() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Новосибирск");
        $x("//input[@placeholder='Дата встречи']").setValue(date);
        $("[data-test-id = 'name'] input").setValue("Marina Marina");
        $("[data-test-id = 'phone'] input").setValue("+79876543210");
        $("[data-test-id = 'agreement']").click();
        $x("//span[@class='button__text']").click();
        //$(withText("Успешно!")).should(visible, Duration.ofSeconds(15));
        //$("[data-test-id=notification] .notification__content").should(exactText("Встреча успешно забронирована на " + date));
        $("[data-test-id='name'] .input__sub").
                should(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
}
