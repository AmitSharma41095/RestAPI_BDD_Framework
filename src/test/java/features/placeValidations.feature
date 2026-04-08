Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add Place Api payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "scope" in response body is "APP"
And "status" in response body is "OK"
And Verify place_Id created maps to "<name>" using "GetPlaceAPI"
 
Examples:
|name 			|language	|address		|
|New House	|English	|US country	|
|Amit Place	|Hindi		|India			|

@DeletePlace
Scenario: Verify Delete Place Functionality is working fine.
Given DeletePlaceAPI payload
When user calls "DeletePlaceAPI" with "DELETE" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
 
