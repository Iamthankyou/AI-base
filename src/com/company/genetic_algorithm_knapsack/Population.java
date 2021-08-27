package com.company.genetic_algorithm_knapsack;

public class Population {

	private Individual[] individuals;
	private int[] weights;
	private int[] values;
	
	public Population(int populationSize, int[] weights, int[] values) {
		individuals = new Individual[populationSize];
		this.weights = weights;
		this.values = values;
	}
	
	public void initialize() {
		for(int i=0;i<individuals.length;++i) {
			Individual individual = new Individual(weights, values);
			individual.generateIndividual();
			saveIndividual(i, individual);
		}
	}
	
	public Individual getFittest() {
		
		Individual fittest = individuals[0];
		
		for(int i=1;i<individuals.length;++i)
			if(individuals[i].getFitness() >= fittest.getFitness())
				fittest = individuals[i];
		
		return fittest;
	}
	
	public int size() {
		return individuals.length;
	}
	
	public Individual getIndividual(int index) {
		return individuals[index];
	}

	public void saveIndividual(int i, Individual individual) {
		individuals[i] = individual;
	}
}
