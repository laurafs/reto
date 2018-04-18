package com.adidas.routes.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
 
@Entity
@ApiModel(value = "City entity", description = "Complete data of a city")
public class City {
	@Id
    @GeneratedValue
    @ApiModelProperty(value = "The id of the city", required = false)
    Long id;
	@ApiModelProperty(value = "The origin city", required = false)
	String city;
	@ApiModelProperty(value = "The destiny city", required = false)
	String destinyCity;
	@ApiModelProperty(value = "The departure time", required = false)
	Date departureTime;
	@ApiModelProperty(value = "The arrival time", required = false)
	Date arrivalTime;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDestinyCity() {
		return destinyCity;
	}
	public void setDestinyCity(String destinyCity) {
		this.destinyCity = destinyCity;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public City() {
		super();
	}
	public City(Long id, String city, String destinyCity, Date departureTime, Date arrivalTime) {
		super();
		this.id = id;
		this.city = city;
		this.destinyCity = destinyCity;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
	
	@Override
	public String toString() {
		return "City [id=" + id + ", city=" + city + ", destinyCity=" + destinyCity + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + "]";
	}
	
}