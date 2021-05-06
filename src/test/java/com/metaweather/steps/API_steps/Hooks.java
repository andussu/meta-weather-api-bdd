package com.metaweather.steps.API_steps;

import com.metaweather.utilities.ConfigurationReader;
import io.cucumber.java.Before;

import static io.restassured.RestAssured.baseURI;

public class Hooks {

    @Before
    public void setUpApi(){
        baseURI = ConfigurationReader.get("MW_base");
    }

}
