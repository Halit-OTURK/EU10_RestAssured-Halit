package com.cybertek.Pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.util.*;

@Getter //from lombok dependency
@Setter //from lombok dependency
@ToString //from lombok dependency
@JsonIgnoreProperties(ignoreUnknown = true)  //this is from jackson dependency
public class Regions {

    @JsonProperty("items")
    private List<Region> Items ;
    private int count;
}
