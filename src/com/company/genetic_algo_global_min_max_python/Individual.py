import random
import math

from Constants import Constants

class Individual:
    def __init__(self):
        self.genes = {}
        self.fitness = 0
    
    def generateIndividual(self):
        for i in range(0,Constants.CHROMOSOME_LENGTH):
            self.genes[i] = random.randint(0,1)
    
    def f(self,x):
        return Constants.EQUATION(x)
    
    def genesToDouble(self):
        base = 1
        geneInDouble = 0
        largest = 2**Constants.GENE_LENGTH

        for i in range(0,Constants.GENE_LENGTH):
            if self.genes[i] == 1:
                geneInDouble += base
            base = base * 2

        value = Constants.RANGE[0] + (geneInDouble/largest) * (Constants.RANGE[1] - Constants.RANGE[0])

        
        # 2^10=1024 / 1024 -> [0,10]
        # geneInDouble = geneInDouble / 102.4
        return value
    
    def getFitness(self):
        geneInDouble = self.genesToDouble()
        return self.f(geneInDouble)
    
    def getGene(self,index):
        return self.genes[index]
    
    def setGene(self, index, value):
        self.genes[index] = value
        self.fitness = 0

if __name__ == '__main__':
    f = Individual()

    f.generateIndividual()


