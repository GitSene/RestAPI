package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public int RESPONSE_STATUS_CODE_OK = 200;
	public int RESPONSE_STATUS_CODE_SERVERERRO = 500;
	public int RESPONSE_STATUS_CODE_CLIENTERROR = 400;
	public int RESPONSE_STATUS_CODE_CREATED = 201;


	
	
	public Properties prop;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");

			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}
}
