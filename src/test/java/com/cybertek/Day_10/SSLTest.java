package com.cybertek.Day_10;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
public class SSLTest {

    @Test
    public void test1(){
        given().
                relaxedHTTPSValidation(). //even if it doesn't have valid certificate I want to send request
                                          // if we don't use this code we give
                                          // "unable to find valid certification path to requested target" error
                when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();
    }

    @Test
    public void keyStore(){

        given()
                .keyStore("pathtofile","password")
                .when().get("apiurl");

    }
}
