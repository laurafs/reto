package com.adidas.routes.calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.adidas.routes.api.RoutesResponse;
import com.adidas.routes.dijkstra.engine.DijkstraAlgorithm;
import com.adidas.routes.dijkstra.model.Edge;
import com.adidas.routes.dijkstra.model.Graph;
import com.adidas.routes.dijkstra.model.Vertex;
import com.adidas.routes.dto.City;
import com.adidas.routes.errorhandling.AppException;
import com.adidas.routes.properties.Configuration;
import com.adidas.routes.rest.RestClient;
import com.adidas.routes.utils.DateUtils;

/**
 * Calculates route with less number of connections and route with less time spent to travel.
 * 
 * @author laurafs
 *
 */
public class Calculator {

	private List<Vertex> nodes;
	private List<Edge> edges;
	private List<Edge> edgesByTime;

	    
	public RoutesResponse getRoutes(String origin, String destiny) throws Exception{
		List<City> cityInfo = new ArrayList<City>();
		List<String> citiesToProcess = new ArrayList<String>();
		RoutesResponse paths = new RoutesResponse();
		nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        edgesByTime = new ArrayList<Edge>();
        int i = 0;
        
        if (origin.compareTo(destiny)==0) {
        	throw new AppException(Configuration.getInstance().getProperty("error.origin.destiny"));
        }
        citiesToProcess.add(origin); //List of cities to process to get their info of
        							//next cities and times.
		Vertex location = new Vertex(origin, origin);
		nodes.add(location);
		while (i<citiesToProcess.size()) {
			String city = citiesToProcess.get(i);
			i++;
			if (city.compareTo(destiny)!=0) { //If it is the destiny city, we don't need its info.
				cityInfo = RestClient.getCityData(city); //gets the info of the city (all the destinations 
														//from this city and times. 
				if (cityInfo.size()==0) {
					throw new AppException(Configuration.getInstance().getProperty("error.no.info")+city); 
				}
				for (City c:cityInfo) {
					Vertex location2 = new Vertex(c.getDestinyCity(), c.getDestinyCity());
					if (!nodes.contains(location2)) {
						nodes.add(location2);
			        }
					// edge with value 1, to calculate less number of connections route
		            Edge lane = new Edge(i,nodes.get(nodes.indexOf(new Vertex(c.getCity(), c.getCity()))), nodes.get(nodes.indexOf(location2)), 1L);
		            edges.add(lane);
		            
		            Long minutes =  DateUtils.totalMinutes(c.getDepartureTime(), c.getArrivalTime());
		            // edge with value the time to travel between the cities, to calculate less time route
		            Edge laneWithTime = new Edge(i,nodes.get(nodes.indexOf(new Vertex(c.getCity(), c.getCity()))), nodes.get(nodes.indexOf(location2)), minutes);
		            edgesByTime.add(laneWithTime);
		            if (!citiesToProcess.contains(c.getDestinyCity())) {
		            	citiesToProcess.add(c.getDestinyCity());
					}				
				}
			}
		}
		
		//Path by less number of cities visited
		Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(location);
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(nodes.indexOf(new Vertex(destiny, destiny))));
        paths.setRoutesByConnections(path);
        
        //Path by less time spent
  		graph = new Graph(nodes, edgesByTime);
        dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(location);
        LinkedList<Vertex> path2 = dijkstra.getPath(nodes.get(nodes.indexOf(new Vertex(destiny, destiny))));
        paths.setRoutesByTime(path2);
        
        return paths;	    
	}
}
