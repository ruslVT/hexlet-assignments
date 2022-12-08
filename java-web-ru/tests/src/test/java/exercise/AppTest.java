package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;

    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateUserPositive() {

        User expectedUser = new User("Ruslan", "Tuaev", "b@b.com", "123456");

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", expectedUser.getFirstName() )
                .field("lastName", expectedUser.getLastName())
                .field("email", expectedUser.getEmail())
                .field("password", expectedUser.getPassword())
                .asString();

        assertThat(response.getStatus()).isEqualTo(302);

        User actualUser = new QUser()
                .firstName.equalTo("Ruslan")
                .findOne();

        assertThat(actualUser.getFirstName()).isEqualTo(expectedUser.getFirstName());
        assertThat(actualUser.getLastName()).isEqualTo(expectedUser.getLastName());
        assertThat(actualUser.getEmail()).isEqualTo(expectedUser.getEmail());
        assertThat(actualUser.getPassword()).isEqualTo(expectedUser.getPassword());

    }

    @Test
    void testCreateUserNegative() {

        User expectedUser = new User("", "", "bbb@rrr", "12q");

        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", expectedUser.getFirstName())
                .field("lastName", expectedUser.getLastName())
                .field("email", expectedUser.getEmail())
                .field("password", expectedUser.getPassword())
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);

        User incorrectUser = new QUser()
                .email.equalTo(expectedUser.getEmail())
                .findOne();
        assertThat(incorrectUser).isNull();

        String body = response.getBody();
        assertThat(body).contains("Имя не должно быть пустым");
        assertThat(body).contains("Фамилия не должна быть пустой");
        assertThat(body).contains("Должно быть валидным email");
        assertThat(body).contains("Пароль должен содержать не менее 4 символов");

        assertThat(body).contains("bbb@rrr");

    }
    // END
}
