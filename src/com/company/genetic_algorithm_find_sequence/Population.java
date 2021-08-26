package com.company.genetic_algorithm_find_sequence;

public class Population {

	private Individual[] individuals;
	
	public Population(int populationSize) {
		individuals = new Individual[populationSize];
	}
	
	public void initialize() {
		for(int i=0;i<individuals.length;++i) {
			Individual individual = new Individual();
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
