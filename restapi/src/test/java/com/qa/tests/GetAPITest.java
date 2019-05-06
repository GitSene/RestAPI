package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

import io.restassured.internal.path.json.JSONAssertion;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	JSONObject body;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		// https://reqres.in/api/users

		url = serviceUrl + apiUrl;

	}

	@Test
	public void AssertinTotalcountTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		body = restClient.get(url);
	     Assert.assertEquals(body.getInt("total"), 12,"value is not as expected\n"+ body);
	  

		
	}
	
	@Test
	public void AssertinDataTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		body = restClient.get(url);
		JSONArray data= new JSONArray();
		data=body.getJSONArray("data");
		JSONObject singleData= new JSONObject();
		singleData=data.getJSONObject(2);
		int id=singleData.getInt("id");
		String first_name = singleData.getString("first_name");
		String last_name = singleData.getString("last_name");
		String avatar = singleData.getString("avatar");

		
	     Assert.assertEquals(first_name, "Emma");
	     Assert.assertEquals(last_name, "Wong");
	    // Assert.assertEquals(avatar, "solomon" );
	  

		
	}
	
	@Test
	public void AssertinDataWithNoBugHiddingTest() throws JSONException {
		restClient = new RestClient();
		try {
			body = restClient.get(url);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray data= new JSONArray();
		data=body.getJSONArray("data");
		JSONObject singleData= new JSONObject();
		//singleData.remove("avator")
		singleData=data.getJSONObject(2);
		//int id=singleData.getInt("id");
		
		JSONObject expectedData= new JSONObject();
		
		expectedData.put("id", 3);
		expectedData.put("first_name", "Emma");
		expectedData.put("last_name", "Wong");
		expectedData.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg");

		 
	      
	        JSONAssert.assertEquals(expectedData, singleData, JSONCompareMode.LENIENT);
	    }

	}
	
	// This is done
	
	
