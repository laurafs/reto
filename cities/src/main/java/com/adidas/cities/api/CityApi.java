package com.adidas.cities.api;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.cities.dto.City;
import com.adidas.cities.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "cities", description = "Cities API. This API allows to get the info of a city", produces = "application/json")
public class CityApi {
	private final static Logger LOGGER = LoggerFactory.getLogger(CityApi.class);

	@Autowired
	CityService contactService;
	@Autowired
	Mapper mapper;
	
    @RequestMapping(value="/citiesByOrig/{city}", method=RequestMethod.GET)
    @ApiOperation(value = "Get cities by origin", notes = "Returns the info of a city.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns the info of a city")
    })
    public ResponseEntity<List<City>> getByOrigin(@PathVariable(value="city") String city){
    	LOGGER.info("/citiesByOrig");
    	List<City> cities = contactService.getCitiesByOrig(city);
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }
}	
