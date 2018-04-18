package com.adidas.cities.it;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.adidas.cities.dto.City;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityApiIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getCitiesTest() {
    	String token = login();
    	
    	assertNotEquals(token, "");
        
    	HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(headers);
        
    	ResponseEntity<List<City>> cityResponse =
    	     restTemplate.exchange("http://127.0.0.1:8080/citiesByOrig/City A",
    	    		 HttpMethod.GET, entity, new ParameterizedTypeReference<List<City>>() {
    	     });
    	List<City> cities = cityResponse.getBody();
        assertEquals(cities.size(), 2);
    }
    
    @Test
    public void getEmptyCityTest() {
    	String token = login();
    	
    	assertNotEquals(token, "");
        
    	HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(headers);
       
        
    	ResponseEntity<List<City>> cityResponse =
    	     restTemplate.exchange("http://127.0.0.1:8080/citiesByOrig/test",
    	    		 HttpMethod.GET, entity, new ParameterizedTypeReference<List<City>>() {
    	     });
    	List<City> cities = cityResponse.getBody();
        assertEquals(cities.size(), 0);
    }
    
    private String login() {
    	Map<String, String> params = new HashMap<String, String>();
        params.put("username", "user1");
        params.put("password", "123abc");
        String token = "";
        
        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:8080/login", params, String.class );
        if (response.getStatusCode().compareTo(HttpStatus.OK)==0) {
        	token = response.getHeaders().get("Authorization").get(0);  
        }
    
        return token;
    }

}