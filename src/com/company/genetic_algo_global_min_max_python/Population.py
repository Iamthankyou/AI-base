from Individual import Individual


class Population:
    def __init__(self, populationSize):
        self.individuals = [Individual()]*populationSize

    def initialize(self):
        for i in range(0,len(self.individuals)):
            newIndividual = Individual()
            newIndividual.generateIndividual()
            self.saveIndividual(i, newIndividual)
    
    def getIndividual(self, index):
        return self.individuals[index]
    
    def getFitestIndividual(self):
        fittest = self.individuals[0]

        for i in range(1,len(self.individuals)):
            if self.getIndividual(i).getFitness() < fittest.getFitness():
                fittest = self.getIndividual(i)
        
        return fittest
    
    def size(self):
        return len(self.individuals)
    
    def saveIndividual(self, index, individual):
        self.individuals[index] = individual