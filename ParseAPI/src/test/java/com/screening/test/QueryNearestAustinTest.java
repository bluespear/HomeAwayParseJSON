package com.screening.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.screening.ParseAPI.App;
import com.screening.ParseAPI.QueryWithID;

public class QueryNearestAustinTest {
	
	Long id;
	String apiKey;
	
	@BeforeTest
	public void setup() {
		apiKey = "api_key=RIxGdAvcQlwN5WsDjeMMB4KnDpQ2DTYxCrWtgg7A";
	}
	
	@Test
	public void checkStationName() throws IOException, ParseException {
		String api = "https://api.data.gov/nrel/alt-fuel-stations/v1/nearest.json";
		String locator = "location=Austin+TX";
		String uri = api + "?" + apiKey + "&" + locator;
		App query = new App(uri);
		String name = query.getName();
		id = query.getId();
		assertEquals("COA FACILITIES", name);
	}
  
	@Test (dependsOnMethods = { "checkStationName" })
	public void checkAddress() throws IOException, ParseException {
		String api = "https://api.data.gov/nrel/alt-fuel-stations/v1/";
		String qid = id + "" + ".json";
		String uri = api + qid + "?" + apiKey;
		
		QueryWithID query = new QueryWithID(uri);
		
		String street = query.getStreet();
		String city = query.getCity();
		String state = query.getState();
		String zip = query.getZip();
		
		String address = street + ", " + city + ", " + state + ", " + zip;
		
		assertEquals("900 Barton Springs Rd, Austin, TX, 78704", address);
		
	}
}
