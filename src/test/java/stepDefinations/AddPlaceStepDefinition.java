package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIEnumResources;
import resources.TestDataBuild;
import resources.Utils;

public class AddPlaceStepDefinition extends Utils {
	
	//Defined variables globally so they can be accesible to all the methods.
	RequestSpecification request;
	Response response;
	String place_id;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Api payload with {string} {string} {string}")
	public void add_place_api_payload_with(String name, String language, String address) throws IOException {
	    
		//placed all this code in resources -->  TestBuild.java
		
		//request and response specifications are coming from resources -->  Utils.java
//		requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
//		responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		System.out.println("********** Add Place **********************");
		request = given().spec(RequestSpecification()).body(data.addPlacePayload(name,language,address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourceName, String httpMethod) {
		
		APIEnumResources resource = APIEnumResources.valueOf(resourceName);
		if(httpMethod.equalsIgnoreCase("POST")) {
			response = request.when().post(resource.getResourceName()).then().spec(ResponseSpecification()).extract().response();
		}else if(httpMethod.equalsIgnoreCase("GET")) {
			response = request.when().get(resource.getResourceName()).then().spec(ResponseSpecification()).extract().response();
		}else if(httpMethod.equalsIgnoreCase("PUT")) {
			response = request.when().put(resource.getResourceName()).then().spec(ResponseSpecification()).extract().response();
		}else if(httpMethod.equalsIgnoreCase("DELETE")) {
			response = request.when().delete(resource.getResourceName()).then().spec(ResponseSpecification()).extract().response();
		}else {
			Assert.fail("Incorrect Http method found !!");
		}
		
//		response = request.when().post("maps/api/place/add/json")
//				.then().spec(ResponseSpecification()).extract().response();

		System.out.println("POST Response body : "+response.asString());
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
//		JsonPath js = new JsonPath(response.asString());
		assertEquals(getJsonPath(response, key), value);

//		String place_id = js.getString("place_id");
		
	}
	
	@Then("Verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resourceName) throws IOException {
		place_id = getJsonPath(response, "place_id");
		System.out.println("Place id : "+place_id);
		
		request = given().spec(RequestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resourceName,"GET");
		String Actualname = getJsonPath(response, "name");
		
		assertEquals(Actualname, name);
		
		
	}
	

}
