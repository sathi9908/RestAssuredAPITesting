package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DataDrivenTest_AddNewEmployees 
{
	@Test(dataProvider="empdataprovider")
	void postEmployees(String ename,String esal,String eage)
	{
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest=RestAssured.given();
		
		//Here we created data which we can send along with the post request
		
		JSONObject requestparams=new JSONObject();
		
		requestparams.put("name",ename);
		requestparams.put("salary",esal);
		requestparams.put("age",eage);
	
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		// Add the Json to the body of the request
		
		httpRequest.body(requestparams.toJSONString());
		
		//POST REQUEST
		Response response=httpRequest.request(Method.POST,"/create");
		
		//capture response body to perform validations
		
		String responseBody=response.getBody().asString();
		System.out.println("The responsebody is:"+responseBody);
		
		Assert.assertEquals(responseBody.contains(ename),true);
		Assert.assertEquals(responseBody.contains(esal),true);
		Assert.assertEquals(responseBody.contains(eage),true);
		
		int statuscode=response.statusCode();
		Assert.assertEquals(statuscode,200);
		
		
		
		
	}
	
	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException
	{
		//Read data from excel
		String path=System.getProperty("user.dir")+"//src//test//java//datadriventesting//empdata.xlsx";
		
		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1",1);
		
		String empdata[][]=new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				empdata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}

		}
		
		
		
		//String empdata[][]={{"abc123","20000","20"},{"abc456","30000","30"},{"abc789","40000","40"}};
		
		return(empdata);
	}
}
