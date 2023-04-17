package com.Homework;



import com.cydeo.Pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class Homework5 extends SpartanTestBase {


    @Test
    public void postWithString(){

        String requestBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Thor\",\n" +
                "  \"phone\": 3563218769\n" +
                "}";

     given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/api/spartans");


    }
    @Test
    public void postWithMAp(){

        Map<String, Object> spartan = new LinkedHashMap<>();

        spartan.put("name","Monday");
        spartan.put("gender","Female");
        spartan.put("phone",3434566778L);

        given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans");

    }

    @Test
    public void post3(){

        Map<String, Object> spartan = new LinkedHashMap<>();

        spartan.put("name","Mark Weber");
        spartan.put("gender","Female");
        spartan.put("phone",3434566778L);

        int id =given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans")
                .then()
                .extract().jsonPath().getInt("id"); //serialization

        given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then()
                .body("name", is ("Mark Weber"))
                .body("gender", is ("Female")); //deserialization

    }

    @Test
    public void postwithPOJO(){

        Spartan spartan1 = new Spartan();
        spartan1.setName("ahmet");
        spartan1.setGender("Male");
        spartan1.setPhone(2132134555);

        given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan1)
                .when().post("/api/spartans");


    }
    @Test
    public void putwithPOJO(){

        Spartan spartan1 = new Spartan();
        spartan1.setName("Mehmet");
        spartan1.setGender("Male");
        spartan1.setPhone(2132155555);

        given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan1)
                .and().pathParam("id",912)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);


    }
}
