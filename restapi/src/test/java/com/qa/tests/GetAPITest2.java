package com.qa.tests;



import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest2 extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	JSONObject closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		// https://reqres.in/api/users

		url = serviceUrl + apiUrl;

	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		 JSONObject response = restClient.get(url);
	     Assert.assertEquals(response.getInt("total"), 12,"value is not as expected\n"+ response);
		 //Assert.asser
		// a. Status Code:
			int statusCode = ((HttpResponse) closeableHttpResponse).getStatusLine().getStatusCode();
			System.out.println("Status Code ---->" + statusCode);
			Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_OK, "Status Code is not matching actula="+ statusCode + " expected=" + RESPONSE_STATUS_CODE_OK);

			// b. JSON String
			String responseString = EntityUtils.toString(((HttpResponse) closeableHttpResponse).getEntity(), "UTF-8");
			JSONObject responseJason = new JSONObject(responseString);
			//System.out.println("Response JASON from API--->" + responseJason);

			// c. All Headers
			Header[] headersArray = ((HttpMessage) closeableHttpResponse).getAllHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			for (Header header : headersArray) {
				allHeaders.put(header.getName(), header.getValue());
			}

			System.out.println("headers Array-->" + allHeaders);
			
	}
	}
