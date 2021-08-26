package com.company.genetic_algorithm_find_sequence;

public class App {

	public static void main(String[] args) {
		
		GeneticAlgorithm algorithm = new GeneticAlgorithm();
		
		Population population = new Population(100);
		population.initialize();
		
		int generationCounter = 0;
		
		while(population.getFittest().getFitness() != Constants.MAX_FITNESS) {
			++generationCounter;
			System.out.println("Generation: " + generationCounter + 
					" - fittest is: " + population.getFittest().getFitness());
			System.out.println(population.getFittest());
			population = algorithm.evolvePopulation(population);
		}
		
		System.out.println("Solution found!!!");
		System.out.println(population.getFittest());
	}
}
