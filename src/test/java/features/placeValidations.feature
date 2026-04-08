Feature: Validating Place API's

Scenario: Verify if Place is being successfully added using AddPlaceAPI
Given Add Place Api payload
When user calls "AddPlaceAPI" with POST http request
Then the API call got success with status code 200
And "scope" in response body is "APP"
And "status" in response body is "OK"
 
 
 
