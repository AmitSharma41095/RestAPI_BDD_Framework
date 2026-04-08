package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	//Executed everytime before @DeletePlace - only when placeid is null.
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		AddPlaceStepDefinition ap = new AddPlaceStepDefinition();
		
		//run only if place_id is null
		if(AddPlaceStepDefinition.place_id==null) { //static variable, therefore called with classname
			ap.add_place_api_payload_with("Amit House New", "German", "Uttam Nagar");
			ap.user_calls_with_http_request("AddPlaceAPI", "POST");
			ap.verify_place_id_created_maps_to_using("Amit House New", "GetPlaceAPI");
		}
		
		
	}

}
