package com.company.genetic_algorithm_find_sequence;

import java.util.Random;

// this is a chromosome
public class Individual {

	// in this example the genes are not binary values
	private int[] genes;
	private int fitness;
	private Random random; 
	
	public Individual() {
		this.genes = new int[Constants.CHROMOSOME_LENGTH];
		this.random = new Random();
	}
	
	public int getFitness() {
		
		if(fitness == 0) {
			for(int i=0;i<genes.length;++i)
				if(genes[i] == Constants.SOLUTION_SEQUENCE[i])
					fitness++;
		}
		
		return fitness;
	}
	
	public void generateIndividual() {
		for(int i=0;i<genes.length;++i) { 
			genes[i] = random.nextInt(Constants.CHROMOSOME_LENGTH);
		}	
	}
	
	public void setGene(int index, int value) {
		genes[index] = value;
		fitness = 0;
	}
	
	public int getGene(int index) {
		return genes[index];
	}
	
	@Override
	public String toString() {
		
		String s = "";
		
		for(int i=0;i<genes.length;++i)
			s += genes[i] + "-";
		
		return s;
	}
}





