package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;

public class RestClient {

	// Get Method without Headers:
	public JSONObject get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // hit get URL
		
		// a. Status Code:
					int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
					System.out.println("Status Code ---->" + statusCode);
	// c. All Headers
					Header[] headersArray = closeableHttpResponse.getAllHeaders();
					HashMap<String, String> allHeaders = new HashMap<String, String>();
					for (Header header : headersArray) {
						allHeaders.put(header.getName(), header.getValue());
					}

					System.out.println("headers Array-->" + allHeaders);
					

					// b. JSON String
					String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
					JSONObject responseJason = new JSONObject(responseString);
					//System.out.println("Response JASON from API--->" + responseJason);

					
		
		return responseJason;
		}
		
		
		
	}
	

