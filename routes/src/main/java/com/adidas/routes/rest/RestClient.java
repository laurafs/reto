package com.adidas.routes.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.adidas.routes.dto.City;
import com.adidas.routes.errorhandling.AppException;
import com.adidas.routes.properties.Configuration;

/**
 * Request the list of city connections to the cities component. 
 * 
 * @author laurafs
 *
 */
public class RestClient {
	public static List<City> getCityData(String city) throws AppException{
		RestTemplate restTemplate = new RestTemplate();
		
		//Login
		Map<String, String> params = new HashMap<String, String>();
        params.put("username", Configuration.getInstance().getProperty("cities.rest.user"));
        params.put("password", Configuration.getInstance().getProperty("cities.rest.password"));
        String token = "";
        
        String server = Configuration.getInstance().getProperty("cities.rest.server");
        String login = Configuration.getInstance().getProperty("cities.rest.login");
        String citiesByOrig = Configuration.getInstance().getProperty("cities.rest.citiesByOrig");
        
        ResponseEntity<String> response = restTemplate.postForEntity(server+login, params, String.class );
        if (response.getStatusCode().compareTo(HttpStatus.OK)==0) {
        	token = response.getHeaders().get("Authorization").get(0);  
        }else {
        	throw new AppException(Configuration.getInstance().getProperty("error.not.allowed"));
        }
    

        //Once we have login into cities api, we include the token in the header.
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(headers);
        
    	ResponseEntity<List<City>> cityResponse;
    	try {
    		cityResponse =
    	        restTemplate.exchange(server+citiesByOrig+"/"+city,
    	                    HttpMethod.GET, entity, new ParameterizedTypeReference<List<City>>() {
    	            });
    	}catch (RestClientException e) {
    		throw new AppException(Configuration.getInstance().getProperty("error.no.response")); 
    	}
    	List<City> cities = cityResponse.getBody();
    	
    	return cities;
	}
}
