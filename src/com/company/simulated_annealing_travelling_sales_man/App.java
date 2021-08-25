package com.company.simulated_annealing_travelling_sales_man;

public class App {

	public static void main(String[] args) {
		
		SimulatedAnnealing sa = new SimulatedAnnealing();
		
		for(int i=0;i<5000;i++) {
			City city = new City();
			Repository.addCity(city);
		}
		
		sa.simulate();
		
		System.out.println("The best solution: " + sa.getBest().getDistance());
		System.out.println(sa.getBest());
	}
}
