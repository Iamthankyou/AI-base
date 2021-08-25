package com.company.simulated_annealing;

import java.util.Random;

public class SimulatedAnnealing {

	private Random randomGenerator;
	// the actual state (state = x)
	private double currentCoordinateX;
	// this is the next state (neighbor)
	private double nextCoordinateX;
	private double bestCoordinateX;
	
	public SimulatedAnnealing() {
		this.randomGenerator = new Random();
	}
	
	public void findOptimum() {
		
		double temp = Constants.MAX_TEMPERATURE;
		
		while(temp > Constants.MIN_TEMPERATURE) {
			
			// generate the neigboring state at random
			nextCoordinateX = getRandomX();
			
			// calculate the energy values
			double currentEnergy = getEnergy(currentCoordinateX);
			double newEnergy = getEnergy(nextCoordinateX);
			
			if(acceptanceProbability(currentEnergy, newEnergy, temp) > Math.random())
				currentCoordinateX = nextCoordinateX;
				
			if(f(currentCoordinateX) > f(bestCoordinateX))
				bestCoordinateX = currentCoordinateX;
			
			temp = temp * (1-Constants.COOLING_RATE);
		}
		
		System.out.println("Global extremum is: x="+bestCoordinateX +  
				" f(x) = " + f(bestCoordinateX));
	}
	
	// return a double value in the range [-2,2]
	private double getRandomX() {
		return randomGenerator.nextDouble()*(Constants.MAX_COORDINATE_X - 
				Constants.MIN_COORDINATE_X) + Constants.MIN_COORDINATE_X;
	}

	private double getEnergy(double x) {
		return f(x);
	}
	
	private double f(double x) {
		return (x-0.3)*(x-0.3)*(x-0.3)-5*x+x*x-2;
	}
	
	// this is the Metropolis-function acceptance Probability
	private double acceptanceProbability(double actualEnergy, double newEnergy, double temp) {
		
		// if the new state is better then we take that new state
		if(newEnergy > actualEnergy) return 1.0;
		
		// if the new solution is worse, calculate an acceptance probability
		// T is small: we accept worse solutions with lower probability !!!
		return Math.exp((actualEnergy - newEnergy) / temp);	
	}
}







