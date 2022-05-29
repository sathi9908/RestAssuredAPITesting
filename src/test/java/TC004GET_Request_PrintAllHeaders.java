import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC004GET_Request_PrintAllHeaders {
	@Test
	public void googleMapTest()
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
		
		
		Headers allheaders=response.headers();// capture all the headers from response
		
		for(Header header:allheaders)
		{
			System.out.println(header.getName()+"    "+header.getValue());
			
		}

}
}
