import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC003_GET_Request {
	@Test
	void googleMapTest()
	{
		
		//specify base URI
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response Object
		Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyDspvIzR3rKKQQiD04scYe-xc_d1tIuXpk");
		
		//print response in console window
		
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		
		//validating headers
		
	String contentType=response.header("Content-Type");// capture details of Content-Type header
	System.out.println("Content Type is:"+contentType);
	Assert.assertEquals(contentType,"application/xml; charset=UTF-8");
	
	String contentEncoding=response.header("Content-Encoding");// capture details of Content-Type header
	System.out.println("Content encoding is:"+contentEncoding);
	Assert.assertEquals(contentEncoding,"gzip");
	
	}


}
