package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.TestDataBuild;
import resources.Utils;

public class AddPlaceStepDefinition extends Utils {
	
	//Defined variables globally so they can be accesible to all the methods.
	RequestSpecification request;
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Api payload")
	public void add_place_api_payload() throws FileNotFoundException {
	    
		//placed all this code in resources -->  TestBuild.java
		
		//request and response specifications are coming from resources -->  Utils.java
//		requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
//		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		System.out.println("********** Add Place **********************");
		request = given().spec(RequestSpecification()).body(data.addPlacePayload());
	}
	
	@When("user calls {string} with POST http request")
	public void user_calls_with_post_http_request(String string) {
		response = request.when().post("maps/api/place/add/json")
				.then().spec(ResponseSpecification()).extract().response();

		System.out.println("POST Response body : "+response.asString());
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		JsonPath js = new JsonPath(response.asString());
		assertEquals(js.getString(key).toString(), value);

		String place_id = js.getString("place_id");
		System.out.println("Place id : "+place_id);
	}
	

}
