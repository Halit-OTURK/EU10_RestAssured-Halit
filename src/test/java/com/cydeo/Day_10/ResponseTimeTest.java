package com.cydeo.Day_10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void Test1(){
        Response response = given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON)
                .get("api/spartans/")
                .then().time(both(greaterThan(500L)).and(lessThanOrEqualTo(11000L))).
                extract().response();

        System.out.println("response.getTime() = " + response.getTime());

    }

}
