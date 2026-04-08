Feature: Validating Place API's

Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add Place Api payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "scope" in response body is "APP"
And "status" in response body is "OK"
 
Examples:
|name 		|language	|address	|
|New House	|English	|US country	|
|Amit Place	|Hindi		|India		|

 
