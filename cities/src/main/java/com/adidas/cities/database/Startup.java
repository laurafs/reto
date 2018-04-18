package com.adidas.cities.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.adidas.cities.dao.CityRepository;
import com.adidas.cities.dto.City;
 
 
@Component
public class Startup implements ApplicationListener<ApplicationReadyEvent> {
	private final static Logger LOGGER = LoggerFactory.getLogger(Startup.class);

    @Autowired
    private CityRepository cityRepository;
    
    public static final String SEPARATOR=";";
    
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    	LOGGER.info("Initializing database");
        try {
			this.loadDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    private void loadDataBase() throws IOException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        
    	BufferedReader br = null;
        
        try {
        	br =new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/static/database.csv")));
        	String line = br.readLine();
        	while (null!=line) {
        	   	String [] fields = line.split(SEPARATOR);
              	City c = new City();
  				c.setCity(fields[0]);
  				c.setDestinyCity(fields[1]);
  				c.setDepartureTime(sdf.parse(fields[2]));
  				c.setArrivalTime(sdf.parse(fields[3]));
  				cityRepository.save(c);
  				line = br.readLine();
        	}           
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
           if (null!=br) {
              br.close();
           }
        }
    }
 
}