package com.cydeo.utilities;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class SpartanNewBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://3.216.30.92";
        port = 7000;
        basePath = "/api";


    }

    @AfterAll
    public static void close() {
        reset();
    }
}
