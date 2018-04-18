package com.adidas.routes.api;

import java.util.LinkedList;

import com.adidas.routes.dijkstra.model.Vertex;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "RoutesResponse entity", description = "Response with two different routes: by time and by connections")
public class RoutesResponse {
	@ApiModelProperty(value = "Routes by time", required = false)
    private LinkedList<Vertex> routesByTime;
	@ApiModelProperty(value = "Routes by connections", required = false)
	 private LinkedList<Vertex> routesByConnections;
	
	public LinkedList<Vertex> getRoutesByTime() {
		return routesByTime;
	}
	public void setRoutesByTime(LinkedList<Vertex> routesByTime) {
		this.routesByTime = routesByTime;
	}
	public LinkedList<Vertex> getRoutesByConnections() {
		return routesByConnections;
	}
	public void setRoutesByConnections(LinkedList<Vertex> routesByConnections) {
		this.routesByConnections = routesByConnections;
	}
	
	@Override
	public String toString() {
		return "RoutesResponse [routesByTime=" + routesByTime + ", routesByConnections=" + routesByConnections + "]";
	}
	
}
