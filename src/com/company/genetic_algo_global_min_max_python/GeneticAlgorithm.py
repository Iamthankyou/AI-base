import random
from Constants import Constants
from Individual import Individual
from Population import Population

class GeneticAlgorithm:
    
    def evolvePopulation(self, population):
        newPopulation = Population(population.size())

        for i in range(0,population.size()):
            firstIndividual = self.randomSelection(population)
            secondIndividual = self.randomSelection(population)
            newIndividual = self.crossover(firstIndividual, secondIndividual)
            newPopulation.saveIndividual(i, newIndividual)
        
        for i in range(0, newPopulation.size()):
            self.mutate(newPopulation.getIndividual(i))
        
        return newPopulation
    
    def mutate(self, individual):
        for i in range(0, Constants.CHROMOSOME_LENGTH):
            if random.random() <= Constants.MUTATION_RATE:
                gene = random.randint(0,1)
                individual.setGene(i,gene)
    
    def crossover(self, firstIndividual, secondIndividual):
        newSolution = Individual()

        for i in range(Constants.CHROMOSOME_LENGTH):
            if random.random() <= Constants.CROSSOVER_RATE:
                newSolution.setGene(i, firstIndividual.getGene(i))
            else:
                newSolution.setGene(i, secondIndividual.getGene(i))

        return newSolution
    
    def randomSelection(self, population):
        newPopulation = Population(Constants.TOURNAMENT_SIZE)

        for i in range(0, Constants.TOURNAMENT_SIZE):
            randomIndex = random.randint(0, population.size()-1)
            newPopulation.saveIndividual(i, population.getIndividual(randomIndex))
        
        fittestIndividual = newPopulation.getFitestIndividual()
        return fittestIndividual

if __name__ == '__main__':
    geneticAlgorithm = GeneticAlgorithm()
    
    population = Population(100)
    population.initialize()

    generatorCounter = 0

    while generatorCounter != Constants.SIMULATION_LENGTH:
        generatorCounter+=1
        print("Generation: " + str(generatorCounter) + "- fittest is: y=" + str(population.getFitestIndividual().getFitness())+" with x="+str(population.getFitestIndividual().genesToDouble()))
        population = geneticAlgorithm.evolvePopulation(population)
    
    print("Solution found !")
    print(population.getFitestIndividual().getFitness())