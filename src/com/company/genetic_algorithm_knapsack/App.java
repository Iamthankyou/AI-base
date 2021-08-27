package com.company.genetic_algorithm_knapsack;

public class App {

	public static void main(String[] args) {
		
		int[] weights = {5,7,9,2};
		int[] values = {10,13,19,4};
		
		GeneticAlgorithm algorithm = new GeneticAlgorithm(weights, values);
		
		Population population = new Population(100, weights, values);
		population.initialize();
		
		int generationCounter = 0;
		
		while(generationCounter < 10) {
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
