package resources;

public enum APIEnumResources {
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json"),
	UpdatePlaceAPI("maps/api/place/update/json");
	
	private String resourceName;

	//parametrized constructor to get enum resources
	APIEnumResources(String resourceName){
		this.resourceName=resourceName;
	}
	
	public String getResourceName() {
		return resourceName;
	}

}
