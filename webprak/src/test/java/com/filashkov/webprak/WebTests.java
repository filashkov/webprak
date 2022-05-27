package com.filashkov.webprak;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

// import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebTests {
    @Test
    void test_title() {
        System.setProperty("webdriver.chrome.driver", "C:\\repos\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        assertEquals("Фирма \"Ро и Ко\". Список услуг", driver.getTitle());
        driver.get("http://localhost:8080/about_company_page");
        assertEquals("О компании", driver.getTitle());
        driver.quit();
    }

    // Войти как клиент и посмотреть свои контракты
    @Test
    void log_as_client() {
        System.setProperty("webdriver.chrome.driver", "C:\\repos\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/");

        WebElement signin = driver.findElement(By.id("signinPage"));
        signin.click();

        String client_login = "jukov";
        String client_password = "qwerty";

        WebElement login_field = driver.findElement(By.id("user_login"));
        WebElement password_field = driver.findElement(By.id("user_password"));
        WebElement signin_button = driver.findElement(By.id("client_submit"));
        login_field.sendKeys(client_login);
        password_field.sendKeys(client_password);
        signin_button.click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        WebElement my_lk = driver.findElement(By.id("lkClientLink"));
        my_lk.click();

        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> cells = table.findElements(By.tagName("tr"));
        boolean found = false;
        for (WebElement elem : cells) {
            List<WebElement> info = elem.findElements(By.tagName("td"));
            if (info.size() == 0) {
                continue;
            }
            // Мы заходим на страницу личного кабинета пользователя - клиента.
            // База данных заполнена таким образом, что описание контракта имеет значение "jgl"
            // А цена должна быть 200
            if (Objects.equals(info.get(0).findElement(By.tagName("span")).getText(), "jgl")) {
                if (Objects.equals(info.get(6).findElement(By.tagName("span")).getText(), "200")) {
                    found = true;
                    break;
                }
            }
        }
        assertEquals(true, found);
    }

    // Зайти как сотрудник со второй попытки, добавить информацию о новом сотруднике
    // Затем её обновить, и удалить нового сотрудника
    @Test
    void log_as_admin_insert_update_delete() {
        System.setProperty("webdriver.chrome.driver", "C:\\repos\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1024,768));
        driver.get("http://localhost:8080/");

        WebElement signin = driver.findElement(By.id("signinPage"));
        signin.click();

        String client_login = "niks";
        String client_password = "dsfhgjhkjl";
        String wrong_password = "wrong_password";

        WebElement login_field = driver.findElement(By.id("employee_login"));
        WebElement password_field = driver.findElement(By.id("employee_password"));
        WebElement signin_button = driver.findElement(By.id("employee_submit"));

        login_field.sendKeys(client_login);
        password_field.sendKeys(wrong_password);
        signin_button.click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Пароль неправильный, мы должны остаться на странице регистрации
        assertEquals(driver.getTitle(), "Вход и регистрация");

        login_field = driver.findElement(By.id("employee_login"));
        password_field = driver.findElement(By.id("employee_password"));
        signin_button = driver.findElement(By.id("employee_submit"));

        login_field.sendKeys(client_login);
        password_field.sendKeys(client_password);
        signin_button.click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // При правильном вводе пароля - на главную страницу
        assertEquals("Фирма \"Ро и Ко\". Список услуг", driver.getTitle());

        // Теперь передём на страницу сотрудников и добавим нового
        WebElement slk = driver.findElement(By.id("staffPanelLink"));
        slk.click();

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        WebElement newemployee = driver.findElement(By.id("addStaffButton"));
        newemployee.click();

        WebElement employee_fullname = driver.findElement(By.id("employee_fullname"));
        WebElement employee_address = driver.findElement(By.id("employee_address"));
        WebElement employee_phone_number = driver.findElement(By.id("employee_phone_number"));
        WebElement employee_email = driver.findElement(By.id("employee_email"));
        WebElement employee_login = driver.findElement(By.id("employee_login"));
        WebElement employee_password = driver.findElement(By.id("employee_password"));
        WebElement employee_post = driver.findElement(By.id("employee_post"));
        WebElement employee_is_admin = driver.findElement(By.id("employee_is_admin"));
        WebElement register_button = driver.findElement(By.id("submitButton"));

        employee_fullname.sendKeys("Тестовое имя");
        employee_address.sendKeys("Тестовый адрес");
        employee_phone_number.sendKeys("1234567890");
        employee_email.sendKeys("test@test.com");
        employee_login.sendKeys("test");
        employee_password.sendKeys("test");
        employee_post.sendKeys("test");
        employee_is_admin.sendKeys("1");

        register_button.click();

        // После регистрации администратором нового сотрудника происходит автоматический переход на страницу со списком всех сотрудников

        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        // Проверяем, что всё добавилось

        WebElement table = driver.findElement(By.tagName("table"));
        List<WebElement> cells = table.findElements(By.tagName("tr"));
        boolean found = false;
        for (WebElement elem : cells) {
            List<WebElement> info = elem.findElements(By.tagName("td"));
            if (info.size() == 0) {
                continue;
            }
            if (Objects.equals(info.get(0).findElement(By.tagName("span")).getText(), "Тестовое имя")
                    && Objects.equals(info.get(2).findElement(By.tagName("span")).getText(), "Тестовый адрес")
                    && Objects.equals(info.get(3).findElement(By.tagName("span")).getText(), "1234567890")
                    && Objects.equals(info.get(4).findElement(By.tagName("span")).getText(), "test@test.com")
                    && Objects.equals(info.get(5).findElement(By.tagName("span")).getText(), "test")
                    && Objects.equals(info.get(6).findElement(By.tagName("span")).getText(), "test")
                    && Objects.equals(info.get(7).findElement(By.tagName("span")).getText(), "test")
                    && Objects.equals(info.get(8).findElement(By.tagName("span")).getText(), "1")) {
                found = true;
                WebElement modify_button = info.get(9).findElement(By.id("editEmployeeButton"));
                WebElement delete_button = info.get(10).findElement(By.id("deleteEmployeeButton"));

                modify_button.click();

                driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

                WebElement employee_fullname_new = driver.findElement(By.id("employee_fullname"));
                employee_fullname_new.sendKeys(" и фамилия");
                WebElement register_button_new = driver.findElement(By.id("submitButton"));
                register_button_new.click();
                break;
            }
        }
        assertEquals(true, found);
        found = false;

        // Мы поменяли имя у тестового сотрудника, проверим, что оно действительно изменилось,
        // и удалим тестового сотрудника
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        table = driver.findElement(By.tagName("table"));
        cells = table.findElements(By.tagName("tr"));
        for (WebElement elem : cells) {
            List<WebElement> info = elem.findElements(By.tagName("td"));
            if (info.size() == 0) {
                continue;
            }
            if (Objects.equals(info.get(0).findElement(By.tagName("span")).getText(), "Тестовое имя и фамилия")
                    && Objects.equals(info.get(2).findElement(By.tagName("span")).getText(), "Тестовый адрес")
                    && Objects.equals(info.get(3).findElement(By.tagName("span")).getText(), "1234567890")
                    && Objects.equals(info.get(4).findElement(By.tagName("span")).getText(), "test@test.com")
                    && Objects.equals(info.get(5).findElement(By.tagName("span")).getText(), "test")
                    && Objects.equals(info.get(6).findElement(By.tagName("span")).getText(), "test")
                    && Objects.equals(info.get(7).findElement(By.tagName("span")).getText(), "test")
                    && Objects.equals(info.get(8).findElement(By.tagName("span")).getText(), "1")) {
                found = true;
                WebElement modify_button = info.get(9).findElement(By.id("editEmployeeButton"));
                WebElement delete_button = info.get(10).findElement(By.id("deleteEmployeeButton"));

                delete_button.click();
                break;
            }
        }
        assertEquals(true, found);

        // Теперь проверим, что после всех операций, в таблице осталось 3 сотрудника
        table = driver.findElement(By.tagName("table"));
        cells = table.findElements(By.tagName("tr"));
        for (WebElement elem : cells) {
            List<WebElement> info = elem.findElements(By.tagName("td"));
            if (info.size() == 0) {
                continue;
            }
            if (!(Objects.equals(info.get(1).findElement(By.tagName("span")).getText(), "1")
                    || Objects.equals(info.get(1).findElement(By.tagName("span")).getText(), "2")
                    || Objects.equals(info.get(1).findElement(By.tagName("span")).getText(), "3"))) {
                found = false;
                break;
            }
        }
        assertEquals(true, found);
    }

    @Test
    void empty() {
        System.setProperty("webdriver.chrome.driver", "C:\\repos\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/");
    }
}
