package com.adidas.cities.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.cities.dto.City;
import com.adidas.cities.dao.CityRepository;

@Service
public class CityService {

	@Autowired
	CityRepository dao;
	
	@PersistenceContext
	private EntityManager em;

	
	@Autowired
    private Mapper mapper;
  
	/**
	 * Returns the city info
	 * @param city
	 * @return
	 */
	public List<City> getCitiesByOrig(String city) {
        final List<City> citiesList = new ArrayList<City>();
        TypedQuery query = em.createQuery("select a from City a where a.city = ?1", City.class);
        query.setParameter(1, city);

        return query.getResultList();
    }
   
	/**
	 * Returns all the cities
	 * @return
	 */
	public List<City> getCities() {
        final List<City> citiesList = new ArrayList<City>();
        dao.findAll().forEach(cityEntity -> {
        	citiesList.add(mapper.map(cityEntity, City.class));
        });
        return citiesList;
    }
	
 	public City save(City city){
 		return dao.saveAndFlush(city);
 	}
}