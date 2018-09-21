package artIDCraftDemonstration;
import org.testng.Assert;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import GenericLib.Extent_Reports;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class ValidateResponseStatusCode extends Extent_Reports
{
	int statusCode;
	@Test
	@Parameters(value={"restURI"})
	public void PositiveResponseStatusCode(String restURI) throws Exception
	{   
		
		RestAssured.baseURI = restURI +"/planetary/apod?api_key=DEMO_KEY";//"https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"; //http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(RestAssured.baseURI);

		
		statusCode = response.getStatusCode();
		System.out.println("correct status code returned " + statusCode);
		Extent_Reports.executionLog("PASS", "correct status code returned " + statusCode);
		Assert.assertEquals(statusCode , 200 , "Correct status code returned");
	}
}
