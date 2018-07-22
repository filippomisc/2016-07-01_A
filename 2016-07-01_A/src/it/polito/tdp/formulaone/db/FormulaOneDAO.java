package it.polito.tdp.formulaone.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.formulaone.model.Circuit;
import it.polito.tdp.formulaone.model.Constructor;
import it.polito.tdp.formulaone.model.Driver;
import it.polito.tdp.formulaone.model.Season;


public class FormulaOneDAO {

	public List<Integer> getAllYearsOfRace() {
		
		String sql = "SELECT distinct year FROM races ORDER BY year" ;
		
		try {
			Connection conn = ConnectDB.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet rs = st.executeQuery() ;
			
			List<Integer> list = new ArrayList<>() ;
			while(rs.next()) {
				list.add(rs.getInt("year"));
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	public List<Season> getAllSeasons() {
		
		String sql = "SELECT year, url FROM seasons ORDER BY year" ;
		
		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			List<Season> list = new ArrayList<>();
			while(rs.next()) {
				list.add(new Season(Year.of(rs.getInt("year")), rs.getString("url")));
			}
			
			conn.close();
			return list ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Circuit> getAllCircuits() {

		String sql = "SELECT circuitId, name FROM circuits ORDER BY name";

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			List<Circuit> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Circuit(rs.getInt("circuitId"), rs.getString("name")));
			}

			conn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	
	public List<Constructor> getAllConstructors() {

		String sql = "SELECT constructorId, name FROM constructors ORDER BY name";

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			List<Constructor> constructors = new ArrayList<>();
			while (rs.next()) {
				constructors.add(new Constructor(rs.getInt("constructorId"), rs.getString("name")));
			}

			conn.close();
			return constructors;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	public List<Driver> getDriversOfYear(Year anno){
		
String sql = "Select DISTINCT drivers.*\n" + 
		"from drivers, races, results \n" + 
		"where races.year = ?\n" + 
		"and results.raceId = races.raceId\n" + 
		"and results.driverId = drivers.driverId\n" + 
		"and results.position is not null\n";

		try {
			Connection conn = ConnectDB.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno.getValue());
			
			
			ResultSet rs = st.executeQuery();

			List<Driver> drivers = new ArrayList<>();
			while (rs.next()) {

				
				
drivers.add(new Driver(rs.getInt("driverId"), 
		rs.getString("driverRef"), 
		rs.getInt("number"), 
		rs.getString("code"), 
		rs.getString("forename"), 
		rs.getString("surname"), 
		rs.getDate("dob").toLocalDate(),
		rs.getString("nationality"), 
		rs.getString("url")));	
		
			
	
			}

			conn.close();
			return drivers;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Query Error");
		}
	}
	
	
	public int getDriverPair(Year anno, int driverId1, int driverId2){
		

		
		
		String sql = "select count(*) as cnt " + 
				"from results as r1, results as r2, races " + 
				"where r1.driverId=? " + 
				"and r2.driverId=? " + 
				"and r1.raceId = r2.raceId " + 
				"and races.raceId = r1.raceId " + 
				"and races.year = ? " + 
				"and r1.position is not null " + 
				"and r2.position is not null " + 
				"and r1.position < r2.position ";

				try {
					Connection conn = ConnectDB.getConnection();

					PreparedStatement st = conn.prepareStatement(sql);
					st.setInt(1, driverId1);
					st.setInt(2, driverId1);
					st.setInt(3, anno.getValue());
					
				
					ResultSet rs = st.executeQuery();

					rs.next();
					
					int peso = rs.getInt("cnt");
					
					conn.close();
					return peso;
					
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("SQL Query Error");
				}
			}
			
			
	
}
