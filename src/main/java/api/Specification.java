package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Класс для создания базовой спецификации, экземпляра ожидаемого класса ответа по коду и их соответствия
 *
 */
public class Specification {
    /**
     * Создание спецификации по URL
     * @param url
     * @return
     */
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder().setBaseUri(url)
                .setContentType(ContentType.JSON).build();
    }

    /**
     * Создание HTTP-ответа по коду
     * @param code
     * @return
     */
    public static ResponseSpecification responseSpecification(int code){
        return new ResponseSpecBuilder()
                .expectStatusCode(code).build();
    }

    /**
     * Проверка соответствия полученного кода и ожидаемого
     * @param requestSpecification
     * @param responseSpecification
     */
    public static void ResponceCheck(RequestSpecification requestSpecification, ResponseSpecification responseSpecification){
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}
