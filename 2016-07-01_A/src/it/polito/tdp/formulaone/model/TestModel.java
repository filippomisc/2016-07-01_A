package it.polito.tdp.formulaone.model;

import java.time.Year;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		int season = 2016;
		
		m.createGraph(Year.of(season));
		
		Driver best = m.getBestDriverOf();
		
		System.out.println(String.format("Il miglior pilota della stagione %d è %s %s (%s)", season, best.getForename(), best.getSurname(), best.getNationality()));
	}

}
