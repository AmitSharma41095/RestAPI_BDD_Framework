package resources;

import java.util.ArrayList;

import pojoClasses.AddPlace;
import pojoClasses.AddPlace_Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		//Create the payload using java object
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setAddress(address);
		ap.setWebsite("http://google.com");
		ap.setLanguage(language);

		ArrayList<String> setTypeList = new ArrayList<String>();
		setTypeList.add("Shoe");
		setTypeList.add("Park");
		ap.setTypes(setTypeList);

		AddPlace_Location apl = new AddPlace_Location();
		apl.setLat(-38.383494);
		apl.setLng(33.427362);
		ap.setLocation(apl);
		
		return ap;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}
	
}
