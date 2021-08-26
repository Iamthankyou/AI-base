package com.company.genetic_algorithm_find_sequence;

import java.util.Random;

public class GeneticAlgorithm {

	private Random random;
	
	public GeneticAlgorithm() {
		this.random = new Random();
	}
	
	public Population evolvePopulation(Population population) {
		
		Population newPopulation = new Population(population.size());
		
		// crossover
		for(int i=0;i<population.size();++i) {
			Individual first = randomSelection(population);
			Individual second = randomSelection(population);
			Individual child = crossover(first, second);
			newPopulation.saveIndividual(i, child);
		}
		
		// mutations
		for(int i=0;i<population.size();++i)
			mutate(newPopulation.getIndividual(i));
		
		return newPopulation;
	}
	
	private Individual crossover(Individual parent1, Individual parent2) {
		
		Individual child = new Individual();
		
		// consider the genes one by one
		for(int i=0;i<Constants.CHROMOSOME_LENGTH;++i) {
			if( Math.random() <= Constants.CROSSOVER_RATE) {
				child.setGene(i, parent1.getGene(i));
			} else {
				child.setGene(i, parent2.getGene(i));
			}
		}	
		
		return child;
	}
	
	private void mutate(Individual individual) {
		for(int i=0;i<Constants.CHROMOSOME_LENGTH;++i) {
			if(Math.random() <= Constants.MUTATION_RATE) { 	
				int gene = random.nextInt(Constants.SOLUTION_SEQUENCE.length);
				individual.setGene(i, gene);
			}
		}
	}
	
	// we are going to select 5 individuals from the population
	// ELITISM 
	private Individual randomSelection(Population population) {
		
		Population newPopulation = new Population(Constants.TOURNAMENT_SIZE);
		
		for(int i=0;i<Constants.TOURNAMENT_SIZE;++i) {
			int randomIndex = (int) (Math.random()*population.size());
			newPopulation.saveIndividual(i,population.getIndividual(randomIndex));
		}
		
		Individual fittest = newPopulation.getFittest();
		
		return fittest;
	}
}
