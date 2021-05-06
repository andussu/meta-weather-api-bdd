package com.metaweather.steps.API_steps;

import com.metaweather.POJO.Location;
import com.metaweather.POJO.LocationAndDay;
import com.metaweather.utilities.ApiUtils;
import com.metaweather.utilities.DateUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RetrieveWeatherSteps {
    Integer woeid;
    Response response = null;

    @Given("the user is a MetaWeather client")
    public void the_user_is_a_MetaWeather_client() {
        //Hooks handle baseURI already and no authorization is needed as API is public
        //otherwise this step would generate and send needed Tokens/Credentials for authentication header
        System.out.println(baseURI);
    }

    @When("the user looks up the weather for {string} and date of {string}")
    public void theUserLooksUpTheWeatherForAndDateOf(String location, String date) {
        //format date for URI
        String reqDay = new DateUtils().processDate(date);

        //find the woeID first
        woeid = new ApiUtils().getWoeid(location);
        System.out.println(location + "'s woeID is " + woeid);

        //ready for response
        response = new ApiUtils().getLocationByDate(String.valueOf(woeid), reqDay);
    }

    @When("the user looks up the weather for {string} and date of {string} by manipulating URI")
    public void theUserLooksUpTheWeatherForAndDateOfByManipulatingURI(String hardCodedWoeid, String date) {
        //format date for URI
        String reqDay = new DateUtils().processDate(date);

        //ready for response
        response = new ApiUtils().getLocationByDate(hardCodedWoeid, reqDay);
    }

    @Then("the endpoint should return status {int}")
    public void the_endpoint_should_return_status(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the payload data should reflect the date of {string}")
    public void the_payload_data_should_reflect_the_date_of(String date) {
        //format date for payload assertion
        String reqDate = new DateUtils().swapDateFormat(new DateUtils().processDate(date));
        response.then().assertThat().body("applicable_date", everyItem(equalTo(reqDate)));
    }

    @And("the content type should be {string}")
    public void theContentTypeShouldBe(String contentType) {
        response.then().assertThat().contentType(contentType.toLowerCase());
    }

    @And("the payload {string} should not be null")
    public void thePayloadShouldNotBeNull(String id) {
        response.then().assertThat().body(id, everyItem(notNullValue()));
    }

    @And("the payload data should reflect that location was not found")
    public void thePayloadDataShouldReflectThatLocationWasNotFound() {
        response.then().assertThat().body("detail", equalTo("Not found."));
    }

}