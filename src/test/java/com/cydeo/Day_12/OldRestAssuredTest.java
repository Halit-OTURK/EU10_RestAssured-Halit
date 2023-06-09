package com.cydeo.Day_12;

import com.cydeo.utilities.*;
import io.restassured.filter.log.*;
import io.restassured.http.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OldRestAssuredTest extends SpartanNewBase {

    @Test
    public void getAllSpartan() {

        given().accept(ContentType.JSON).and().
                auth().basic("admin", "admin").
                log().all().
                when().
                get("/spartans").then()
                .statusCode(200).
                contentType(ContentType.JSON)
                .body("id[0]", is(1))
                .body("id[5]", is(6))
                .log().all();
        // firstly call all spartan then check 0 index id = 1 and 5 index is=6
    }

    /*
           in previous version of Restassured, the given when then style
           was actually written in given expect when format.
           it will assert all the result and give one answer and does not fail whole thing
           if first one fail unlike new structure.

        */
    @Test
    public void oldSyntaxtest() {
        given().accept(ContentType.JSON).
                auth().basic("admin", "admin").
                log().all().
                expect().
                statusCode(200).
                and()
                .contentType(ContentType.JSON).
                body("id[0]", is(1))
                .body("id[5]", is(6))
                .logDetail(LogDetail.ALL)
                .when().
                get("/spartans");
        // old type query second way of same task
    }

}
