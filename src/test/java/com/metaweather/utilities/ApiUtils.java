package com.metaweather.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    Integer woeid;

    public Integer getWoeid(String location) {
        Response response = given().accept(ContentType.JSON).and()
                                    .queryParam("query", location).and()
                            .when().log().uri()
                                     .get("/location/search/");

        Assert.assertEquals("Checking response status code 200", response.statusCode(), 200);

        try {
            woeid = response.path("woeid[0]");
        } catch (NullPointerException e) {
            System.out.println("This location is currently unavailable.");
        }
        return woeid;
    }

    public Response getLocationByDate(String woeid, String reqDay){
        return  given().accept(ContentType.JSON).and()
                       .pathParam("location", woeid)
                .when()
                       .get("/location/{location}/" + reqDay);
    }

}