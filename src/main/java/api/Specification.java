package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    public static RequestSpecification requestSpecification(String url){
        return new RequestSpecBuilder().setBaseUri(url)
                .setContentType(ContentType.JSON).build();
    }

    public static ResponseSpecification responseSpecification(int code){
        return new ResponseSpecBuilder()
                .expectStatusCode(code).build();
    }

    public static void ResponceCheck(RequestSpecification requestSpecification, ResponseSpecification responseSpecification){
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }
}
