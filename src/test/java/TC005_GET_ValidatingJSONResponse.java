import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC005_GET_ValidatingJSONResponse {
	@Test
	void getweatherDetails()
	{
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response Object
		Response response=httpRequest.request(Method.GET,"/Delhi");
		
		//print response in console window
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		Assert.assertEquals(responseBody.contains("Delhi"), true);
		
		
		
	}

}
