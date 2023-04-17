package com.cydeo.Pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class F1Drivers {

    private String driverId;
    private String url;

    private String givenName;
    private String familyName;

    private String dateOfBirth;

    private String nationality;


}

/*
"driverId": "abate",
                    "url": "http://en.wikipedia.org/wiki/Carlo_Mario_Abate",
                    "givenName": "Carlo",
                    "familyName": "Abate",
                    "dateOfBirth": "1932-07-10",
                    "nationality": "Italian"
 */