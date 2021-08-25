package com.company.simulated_annealing_travelling_sales_man;

public class SimulatedAnnealing {
	
	private SingleTour actualState;
	private SingleTour nextState;
	private SingleTour bestState;
	
	public void simulate() {
		
		double temp = Constants.MAX_TEMPERATURE;
		
		actualState = new SingleTour();
		actualState.generateIndividual();
		bestState = new SingleTour(actualState.getTour());
		
		System.out.println("Initial solution distance: " + actualState.getDistance());
		
		while( temp > Constants.MIN_TEMPERATURE) {
			
			nextState = generateNeighborState(actualState);
			
			double currentEnergy = actualState.getDistance();
			double neighborEnergy = nextState.getDistance();
			
			if(acceptanceProbability(currentEnergy, neighborEnergy, temp) > Math.random())
				actualState = new SingleTour(nextState.getTour());
			
			if(actualState.getDistance() < bestState.getDistance())
				bestState = new SingleTour(actualState.getTour());
			
			temp *= 1 - Constants.COOLING_RATE;
		}	
	}

	// we swap 2 cities randomly
	private SingleTour generateNeighborState(SingleTour actualState) {
		
		SingleTour newState = new SingleTour(actualState.getTour());
		
		int randomIndex1 = (int) (Math.random()*newState.getTourSize());
		int randomIndex2 = (int) (Math.random()*newState.getTourSize());
		
		City city1 = newState.getCity(randomIndex1);
		City city2 = newState.getCity(randomIndex2);
		
		newState.setCity(randomIndex1, city2);
		newState.setCity(randomIndex2, city1);
		
		return newState;
	}
	
	public SingleTour getBest() {
		return bestState;
	}
	
	// this is the Metropolis-function
	private double acceptanceProbability(double currentEnergy, double neighbourEnergy, double temp) {
		
		if(neighbourEnergy < currentEnergy ) return 1.0;
		
		return Math.exp((currentEnergy-neighbourEnergy)/temp);
	}
}









