package it.polito.tdp.formulaone.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.formulaone.db.DriverPair;
import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	FormulaOneDAO dao;
	List<Integer> anni;
	List<Driver> driversOfSeason;
	
	SimpleDirectedWeightedGraph<Driver, DefaultWeightedEdge> graph;

	public Model() {

		dao = new FormulaOneDAO();
		
		anni = new ArrayList<>(dao.getAllYearsOfRace());
		
		driversOfSeason = new ArrayList<>();
				
		
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
	}

	public List<Integer> getAnni() {
		return anni;
	}
	
	public void createGraph(Year anno) {
		
		this.driversOfSeason = dao.getDriversOfYear(anno);
		
		Graphs.addAllVertices(this.graph, driversOfSeason);
		
		System.out.println("vertici: " + this.graph.vertexSet().size());
		
		for (Driver d1 : this.graph.vertexSet()) {
			for (Driver d2 : this.graph.vertexSet()) {
				
				if(!d1.equals(d2)) {//inserire un if per evitare che crei il grafo con peso = 0
					
					int peso = dao.getDriverPair(anno, d1.getDriverId(), d2.getDriverId());
					if(peso!=0) 
						Graphs.addEdge(this.graph, d1, d2, peso);
						
					
					
					System.out.println(d1.getDriverId() + " "+ d2.getDriverId() + " " + peso);
				}
				
			}
			
			
		}
		System.out.println("Archi: " + this.graph.edgeSet().size());	
		}
	
	
	public Driver getBestDriverOf() {
		
		Driver result = null;
		int migliore = Integer.MIN_VALUE;
		
		for(Driver driver : this.graph.vertexSet()) {
			int sum = 0;
			// Itero sugli archi uscenti
			for (DefaultWeightedEdge e : graph.outgoingEdgesOf(driver)) {
				sum += graph.getEdgeWeight(e);
			}
			
			// Itero sugli archi entranti
			for (DefaultWeightedEdge e : graph.incomingEdgesOf(driver)) {
				sum -= graph.getEdgeWeight(e);
			}
			
		
		if(sum > migliore || result == null) {
			result = driver;
			migliore =sum;
			
			if(result == null)
				throw new RuntimeException("BestDriver not found!");
		}
		}
		return result;
		
	}


}
