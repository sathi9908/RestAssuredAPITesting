import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_POST_Request {
	
	@Test
	void RegistrationSuccessful()
	{
		
		//specify base URI
		RestAssured.baseURI="http://restapi.demoqa.com/customer";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Request paylaod sending along with post request
		
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("FirstName","JhonXYZ");
		requestParams.put("LastName","XYZJhon");
		requestParams.put("UserName","JhonXYZ");
		requestParams.put("Password","JhonXYZxyx");
		requestParams.put("Email","JhonXYZ@gmail.com");
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString());// attach above data to the request
		
		//Response Object
		Response response=httpRequest.request(Method.POST,"/register");
		
		
		//print response in console window
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		//status code validation
		int statuscode=response.getStatusCode();
		System.out.println("Status code is:"+statuscode);
		Assert.assertEquals(statuscode, 201);
		
		//success code validation
		String successCode=response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
		
	}


}
