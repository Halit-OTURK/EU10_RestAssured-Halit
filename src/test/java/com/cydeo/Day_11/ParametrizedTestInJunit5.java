package com.cydeo.Day_11;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static io.restassured.RestAssured.*;

public class ParametrizedTestInJunit5 {


    @ParameterizedTest
    @ValueSource(ints = {1,3,5,6,7,9,10,5,21,32})
    public void testMultipleNumbers(int number){

        System.out.println("number = " + number);
        Assertions.assertTrue(number > 5);

    }

    @ParameterizedTest
    @ValueSource(strings = {"lets","go","my","friend"})
    public void testMultipleNames(String names){

        System.out.println("names = " + names);
        Assertions.assertTrue(!names.isEmpty());

    }
    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200

    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void zipTaskHalit(int zipcodes){

given().accept(ContentType.JSON).and().
        get("https://api.zippopotam.us/us/"+zipcodes).
        then().statusCode(200).log().all();

    }
    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void zipCodeTestJamal(int zipCode){

        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipcode",zipCode) //we got zipcode from valueSource
                .log().all() //request log
                .when()
                .get("/us/{zipcode}")
                .then().log().all() //response log
                .statusCode(200);

    }
}
