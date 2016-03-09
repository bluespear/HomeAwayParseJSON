package com.screening.ParseAPI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class QueryWithID {
	private String uri;
	private URL url;
	private Long id = 0L;
	private String street = "";
	private String zip = "";
	private String state = "";
	private String city = "";
	private String name = "";
	
	public QueryWithID(String uri) throws IOException, ParseException {
		this.uri = uri;
		
		url = new URL(uri);
		try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))){
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(in);
			JSONObject jsonObject = (JSONObject) obj;
			
			JSONObject jobj = (JSONObject) jsonObject.get("alt_fuel_station");
			
			if (jobj != null) {
				name = (String) jobj.get("station_name");
				street = (String) jobj.get("street_address");
				zip = (String) jobj.get("zip");
				state = (String) jobj.get("state");
				city = (String) jobj.get("city");
			}
		}     
	}
	
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getZip() {
		return zip;
	}
	
	public String getState() {
		return state;
	}
	
	public String getCity() {
		return city;
	}
}
