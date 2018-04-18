package com.adidas.routes.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.routes.calculator.Calculator;
import com.adidas.routes.errorhandling.AppException;
import com.adidas.routes.properties.Configuration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "routes", description = "Routes API. This API allows to get a route between 2 cities", produces = "application/json")
public class RoutesApi {
	private final static Logger LOGGER = LoggerFactory.getLogger(RoutesApi.class);

	Calculator calculator = new Calculator();
	
    @RequestMapping(value="/routes/{origin}/{destiny}", method=RequestMethod.GET)
    @ApiOperation(value = "Get Routes", notes = "Returns two routes from one city to another.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Returns two routes")
    })
    public ResponseEntity<RoutesResponse> getRoutes(@PathVariable(value="origin") String origCity, 
    		@PathVariable(value="destiny") String destCity) throws AppException{
    	LOGGER.info("/routes");
    	RoutesResponse routes = new RoutesResponse();
    	try {
    		routes = calculator.getRoutes(origCity, destCity);
    	}catch(Exception e) {
    		LOGGER.info("Exception: "+e);
    		throw new AppException(Configuration.getInstance().getProperty("error.when.calculating")+e.getMessage()); 
    	}
    	
    	return new ResponseEntity<RoutesResponse>(routes, HttpStatus.OK);
    }
    
    @RequestMapping(value=" /test", method=RequestMethod.GET)
    public ResponseEntity<String> test() throws AppException{
      	return new ResponseEntity<String>("OK!!", HttpStatus.OK);
    }
    
    
}
