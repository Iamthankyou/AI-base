package com.company.genetic_algorithm_find_sequence;

public class Constants {

	private Constants() {
		
	}
	
	public static final int[] SOLUTION_SEQUENCE = {0,1,2,3,4,5,6,7,8,9};
	public static final double CROSSOVER_RATE = 0.005;
	public static final double MUTATION_RATE = 0.0015;
	public static final int TOURNAMENT_SIZE = 5;
	public static final int MAX_FITNESS = 10;
	public static final int CHROMOSOME_LENGTH = 10;
}

