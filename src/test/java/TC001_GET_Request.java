import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC001_GET_Request {
	
	@Test
	void getweatherDetails()
	{
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response Object
		Response response=httpRequest.request(Method.GET,"/Hyderabad");
		
		//print response in console window
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		//status code validation
		int statuscode=response.getStatusCode();
		System.out.println("Status code is:"+statuscode);
		Assert.assertEquals(statuscode, 200);
		
		//status line verification
		String statusline=response.getStatusLine();
		System.out.println("status line is:"+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
		
		
		
	}

}
