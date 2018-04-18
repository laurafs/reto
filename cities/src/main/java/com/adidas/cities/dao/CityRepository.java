package com.adidas.cities.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adidas.cities.dto.City;


public interface CityRepository extends JpaRepository<City, Long> {
 
}