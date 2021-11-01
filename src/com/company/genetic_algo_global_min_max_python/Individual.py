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
        # print(str(x) + ":",end="")
        # print((x-2)*(x-2))
        return (x-3)*(x-3)
    
    def genesToDouble(self):
        base = 1
        geneInDouble = 0

        for i in range(0,Constants.GENE_LENGTH):
            if self.genes[i] == 1:
                geneInDouble += base
            base = base * 2
        
        # 2^10=1024 / 1024 -> [0,10]
        geneInDouble = geneInDouble / 102.4
        # print(geneInDouble)
        return geneInDouble
    
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


