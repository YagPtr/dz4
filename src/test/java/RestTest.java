
import api.*;

import api.registration.Registration;
import api.registration.SuccessRegistration;
import api.registration.UnsuccessfullRegister;
import api.users.FullUser;
import api.users.PostPostUser;
import api.users.UserData;
import api.users.empty.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestTest {
    private final static String URL = "https://reqres.in";
    @Test
    @Step
    public void testGetPositive() {
        Specification.ResponceCheck(Specification.requestSpecification(URL),Specification.responseSpecification(200));
        FullUser fullUser =given()
                .when()
                .get("/api/users/3")
                .then()
                .log()
                .all()
                .extract().as(FullUser.class);

        UserData user=fullUser.getData();
        Assertions.assertEquals(user.getId(),3);
        Assertions.assertNotNull(user.getEmail());
        Assertions.assertNotNull(user.getAvatar());
        Assertions.assertNotNull(user.getLast_name());
        Assertions.assertNotNull(user.getFirst_name());
        Assertions.assertEquals(fullUser.getSupport().getText(),"Tired of writing endless social media content? Let Content Caddy generate it for you.");
        Assertions.assertEquals(fullUser.getSupport().getUrl(),"https://contentcaddy.io?utm_source=reqres&utm_medium=json&utm_campaign=referral");
    }
    @Test
    @Step
    public void testGetNegative() {
        Specification.ResponceCheck(Specification.requestSpecification(URL), Specification.responseSpecification(404));
        given()
                .when()
                .get("/api/users/23")
                .then()
                .log()
                .all();
    }

    @Test
    @Step
    public void testPostPositive() {
        Specification.ResponceCheck(Specification.requestSpecification(URL), Specification.responseSpecification(200));
        Integer id=4;
        String token="QpwL5tke4Pnpja7X4";
        Registration user = new Registration("eve.holt@reqres.in","pistol");
        SuccessRegistration reg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then()
                .log()
                .all()
                .extract()
                .as(SuccessRegistration.class);
        Assertions.assertNotNull(reg.getId());
        Assertions.assertNotNull(reg.getToken());
        Assertions.assertEquals(id,reg.getId());
        Assertions.assertEquals(token,reg.getToken());
    }

    @Test
    @Step
    public void testPostNegative() {
        Specification.ResponceCheck(Specification.requestSpecification(URL), Specification.responseSpecification(400));
        Registration user = new Registration("eve.holt@reqres.in","");
        UnsuccessfullRegister reg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then()
                .log()
                .all()
                .extract()
                .as(UnsuccessfullRegister.class);

        Assertions.assertNotNull(reg.getError());
        Assertions.assertEquals("Missing password",reg.getError());
    }

    @Test
    @Step
    public void testPutPositive() {
        Specification.ResponceCheck(Specification.requestSpecification(URL), Specification.responseSpecification(200));
        UserData user = new UserData(2,"janet.weaver@reqres.in","Janet","Weaver","https://reqres.in/img/faces/2-image.jpg");
        PostPostUser postuser=given().body(user).when().put("/api/users/2")
                .then().log().all().extract().as(PostPostUser.class);

        Assertions.assertNotNull(postuser.getUpdatedAt());
        Assertions.assertEquals(user.getEmail(),postuser.getEmail());
        Assertions.assertEquals(user.getId(),postuser.getId());
        Assertions.assertEquals(user.getFirst_name(),postuser.getFirst_name());
        Assertions.assertEquals(user.getLast_name(),postuser.getLast_name());
        Assertions.assertEquals(user.getAvatar(),postuser.getAvatar());

    }

    @Test
    @Step
    public void testPutNegative() {
        Specification.ResponceCheck(Specification.requestSpecification(URL), Specification.responseSpecification(200));
        EmptyUserAnswer postuser=given()
                .body("{}")
                .when()
                .put("/api/users/2")
                .then()
                .log()
                .all()
                .extract()
                .as(EmptyUserAnswer.class);

        Assertions.assertNotNull(postuser.getUpdatedAt());
    }

    @Test
    @Step
    public void testDeletePositive() {
        Specification.ResponceCheck(Specification.requestSpecification(URL), Specification.responseSpecification(204));
        given()
                .when()
                .delete("/api/users/2")
                .then()
                .log()
                .all();
    }
    @Test
    @Step
    public void testDeleteNegative() {
        Specification.ResponceCheck(Specification.requestSpecification(URL), Specification.responseSpecification(204));
        given()
                .when()
                .delete("/api/users/23")
                .then()
                .log()
                .all();
    }

}
