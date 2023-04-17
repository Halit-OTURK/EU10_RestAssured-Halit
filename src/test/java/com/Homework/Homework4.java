package com.Homework;




import com.cybertek.Pojo.F1Drivers;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class Homework4 {

    @BeforeAll
    public static void init() {

        baseURI = "http://ergast.com/api/f1/";
    }

    @Test
    public void withHamcrest() {

        Response response = given().accept(ContentType.XML)
                .and().pathParam("driverId", "alonso")
                .when().get("/drivers/{driverId}")
                .then().statusCode(200)
                .contentType(ContentType.XML).log().all()
                .body("MRData.DriverTable.Driver.GivenName", is("Fernando"))
                .body("MRData.DriverTable.Driver.FamilyName", is("Alonso"))
                .body("MRData.DriverTable.Driver.Nationality", is("Spanish"))
                .extract().response();


        /*

        Given accept type is json
        - And path param driverId is alonso
        - When user send request /drivers/{driverId}.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 1
        - And givenName is Fernando
        - And familyName is Alonso
        - And nationality is Spanish
         */


    }

    @Test
    public void withMAP() {

        Map<String, Object> driver1 = given()
                .when().get("/drivers.json")
                .then().statusCode(200)
                .extract().response().path("MRData.DriverTable.Drivers[0]");


        System.out.println(driver1);

        assertThat(driver1.get("driverId"), is("abate"));
        assertThat(driver1.get("givenName"), is("Carlo"));
        assertThat(driver1.get("familyName"), is("Abate"));

        /*

        Given accept type is json
        - And path param driverId is alonso
        - When user send request /drivers/{driverId}.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 1
        - And givenName is Fernando
        - And familyName is Alonso
        - And nationality is Spanish
         */

    }

    @Test
    public void withPOJO() {

        F1Drivers f1Driver = given()
                .when().get("/drivers.json")
                .then().statusCode(200)
                .extract().jsonPath().getObject("MRData.DriverTable.Drivers[0]", F1Drivers.class);


        System.out.println(f1Driver);

        assertThat(f1Driver.getDriverId(), is("abate"));
        assertThat(f1Driver.getFamilyName(), is("Abate"));
        assertThat(f1Driver.getNationality(), is("Italian"));
        assertThat(f1Driver.getDateOfBirth(), is("1932-07-10"));


    }

    @Test
    public void withPOJO2() {

        Response response = given()
                .when().get("/drivers.json")
                .then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        F1Drivers f1Driver = jsonPath.getObject("MRData.DriverTable.Drivers[0]", F1Drivers.class);

        System.out.println(f1Driver);


        List<Map<String, Object>> drivers = jsonPath.getList("MRData.DriverTable.Drivers");


        System.out.println(drivers.get(2));

        System.out.println(drivers.get(3).get("driverId"));
    }

    @Test
    public void withList() {

        Response response = given()
                .when().get("/drivers.json")
                .then().statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        List<Map<String, Object>> drivers = jsonPath.getList("MRData.DriverTable.Drivers");


        System.out.println(drivers.get(2));

        System.out.println(drivers.get(3).get("driverId"));

        assertThat(drivers.get(3).get("driverId"), is("adams"));
    }


}
