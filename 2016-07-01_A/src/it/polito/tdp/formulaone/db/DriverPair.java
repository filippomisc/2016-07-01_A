package it.polito.tdp.formulaone.db;

public class DriverPair {
	
	private int driverId1;
	private int driverId2;
	private int peso;
	
	
	public DriverPair(int driverId1, int driverId2, int peso) {
		this.driverId1 = driverId1;
		this.driverId2 = driverId2;
		this.peso = peso;
	}


	public int getDriverId1() {
		return driverId1;
	}


	public void setDriverId1(int driverId1) {
		this.driverId1 = driverId1;
	}


	public int getDriverId2() {
		return driverId2;
	}


	public void setDriverId2(int driverId2) {
		this.driverId2 = driverId2;
	}


	public int getPeso() {
		return peso;
	}


	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	
	

}
