package com.company.genetic_algorithm_knapsack;

import java.util.Random;

// this is a chromosome
public class Individual {

	// the genes are not binary values
	// [0, 1, 1, 0, 0]
	private int[] genes;
	private int[] weights;
	private int[] values;
	private int fitness;
	private Random random; 
	
	public Individual(int[] weights, int[] values) {
		this.weights = weights;
		this.values = values;
		this.genes = new int[values.length];
		this.random = new Random();
	}
	
	public int getFitness() {
		// MAX value but <= weight, if not, penaty !
		int weight = 0;
		int value = 0;
		
		for(int i=0;i<genes.length;++i) {
			if(genes[i] == 1) {
				weight += weights[i];
				value += values[i];
			}
		}
		
		if(weight <= Constants.KNAPSACK_CAPACITY) 
			return value;
		
		return Integer.MIN_VALUE;
	}
	
	public void generateIndividual() {
		for(int i=0;i<genes.length;++i) { 
			genes[i] = random.nextInt(2);
		}	
	}
	
	public void setGene(int index, int value) {
		genes[index] = value;
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





