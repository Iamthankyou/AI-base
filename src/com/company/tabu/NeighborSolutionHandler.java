package com.company.tabu;

import java.util.List;

public class NeighborSolutionHandler {

	// find state but Avoid tabu (forbiddent)
	public State getBestNeighbor(State[][] states, List<State> neighborStates, List<State> tabuStates) {
		
		// remove tabu
		neighborStates.removeAll(tabuStates);
		
		// go to the middle state
		if(neighborStates.size()==0) return states[100][100];
		
		//algorithm find min neighborStates
		State bestSolution = neighborStates.get(0);
		
		for(int i=1;i<neighborStates.size();i++)
			if(neighborStates.get(i).getZ()<bestSolution.getZ())
				bestSolution = neighborStates.get(i);
		
		System.out.println("Best solution is: " + bestSolution);
		
		return bestSolution;

	}
}