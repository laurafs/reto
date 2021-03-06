package com.adidas.routes.dijkstra.model;

/**
 * Represents an Edge in the Graph
 * 
 * @author laurafs
 *
 */
public class Edge  {
    private final int id;
    private final Vertex source;
    private final Vertex destination;
    private final Long weight;

    public Edge(int id, Vertex source, Vertex destination, Long weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public Long getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}