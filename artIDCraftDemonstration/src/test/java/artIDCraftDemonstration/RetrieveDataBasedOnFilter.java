package artIDCraftDemonstration;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericLib.Extent_Reports;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RetrieveDataBasedOnFilter extends Extent_Reports
{
	int expectedremainingLimit=0,elementcount;
	
	String remainingLimit,limit,responseBody,contentType;
	
	@Test
	@Parameters(value={"restURI","element_count"})
	public void ValidateDataDetails(String restURI,int element_count) throws Exception
	{   
		
		RestAssured.baseURI = restURI +"/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=DEMO_KEY";

		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, RestAssured.baseURI);
		
		if(response.statusCode() == 200)
		{
		
			responseBody = response.getBody().asString();
			System.out.println("Response Body is =>  " + responseBody);
			Extent_Reports.executionLog("INFO", "Response:"+responseBody);
			
			 contentType = response.header("Content-Type");
			 Extent_Reports.executionLog("INFO","Content-Type:"+ contentType);
	 
		
			 limit =  response.header("X-RateLimit-Limit");
			 System.out.println("X-RateLimit-Limit: " + limit);
			 Extent_Reports.executionLog("INFO","X-RateLimit-Limit:"+ limit);
		
			 remainingLimit = response.header("X-RateLimit-Remaining");
			 
			 Extent_Reports.executionLog("INFO","X-RateLimit-Remaining:"+ remainingLimit);
			 if(Integer.parseInt(remainingLimit) > 0)
			 {
				 
				 JsonPath jsonPathEvaluator = response.jsonPath();

				  elementcount = jsonPathEvaluator.get("element_count");
				  Extent_Reports.executionLog("PASS","Verifying Element Count");
				  Assert.assertEquals(elementcount, element_count);
				  

			 }
			 else
			 {
				 Extent_Reports.executionLog("FAIL","Reached DEMO_KEY Rate Limits.");
				 
			 }
			
		}

	}

}