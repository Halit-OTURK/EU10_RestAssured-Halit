package com.cydeo.Day_11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CsvSourceParametrizedTest {
    // Test first number + second number = third number
//            1, 3 , 4
//            2, 3 , 5
//            8, 7 , 15
//            10, 9 , 19

    @ParameterizedTest
    @CsvSource({"1, 3 , 4",
            "2, 3 , 5",
            "8, 7 , 15",
            "10, 9 , 19"})
    // @Test we don't use this annotation
    public void additionTest(int num1, int num2, int sum) {
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);

        assertThat(num1 + num2, equalTo(sum));
    }

// Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    public void cityTest(String state, String city) {
        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int placeNumber = given().accept(ContentType.JSON).
                and().baseUri("https://api.zippopotam.us").
                when().
                pathParam("state", state).
                when().log().uri().
                pathParam("city", city).
                when().get("/us/{state}/{city}").
                then().statusCode(200)
                .body("places.'place name'", everyItem(containsStringIgnoringCase(city))).
                extract().jsonPath().getList("places").size();

        System.out.println("placeNumber = " + placeNumber);
    }

}

