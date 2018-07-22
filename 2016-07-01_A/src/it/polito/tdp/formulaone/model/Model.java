package it.polito.tdp.formulaone.model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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
					
					Graphs.addEdge(this.graph, d1, d2, peso);
					
					
					System.out.println(d1.getDriverId() + " "+ d2.getDriverId() + " " + peso);
				}
				
			}
			
			
		}
		System.out.println("Archi: " + this.graph.edgeSet().size());

		
			
			
			
		}
	
	
	


}
