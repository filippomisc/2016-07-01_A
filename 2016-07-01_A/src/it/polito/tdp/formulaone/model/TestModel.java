package it.polito.tdp.formulaone.model;

import java.time.Year;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		m.createGraph(Year.of(2006));
		
	}

}
