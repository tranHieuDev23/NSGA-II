import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Demo
{
    private static final int GENE_LENGTH = 3;
    private static final int POPULATION_SIZE = 250;
    private static final int MAX_GENERATION = 100000;
    private static final double MUTATION_RATE = .3;

    private static Solution[] population = new Solution[POPULATION_SIZE];
    private static Solution[] offspringPopulation = new Solution[POPULATION_SIZE];

    private static CrowdedComparisionOperator comparator = new CrowdedComparisionOperator();

    private static void intializePopulation()
    {
        for(int i = 0; i < POPULATION_SIZE; i ++)
        {
            double[] gene = new double[GENE_LENGTH];
            for(int j = 0; j < gene.length; j ++)
                gene[j] = RandomGenerator.getRandomDouble(Solution.VARIABLE_BOUND_LOW, Solution.VARIABLE_BOUND_HIGH);
            population[i] = new Solution(gene);
        }
    }

    private static void reproduce()
    {
        for(int i = 0; i < POPULATION_SIZE; i ++)
        {
            Solution parent1 = BinaryTournament.execute(population);
            Solution parent2 = BinaryTournament.execute(population);
            Solution offspring = Solution.crossover(parent1, parent2);
            if (RandomGenerator.getRandomDouble(0, 1) <= MUTATION_RATE)
                offspring = Solution.mutate(offspring);
            offspringPopulation[i] = offspring;
        }
    }

    private static void selection()
    {
        Solution[] combinedPopulation = new Solution[POPULATION_SIZE * 2];
        for(int i = 0; i < POPULATION_SIZE; i ++)
            combinedPopulation[i] = population[i];
        for(int i = POPULATION_SIZE; i < POPULATION_SIZE * 2; i ++)
            combinedPopulation[i] = offspringPopulation[i - POPULATION_SIZE];

        NonDominatedSorting.execute(combinedPopulation);
        CrowdingDistanceCalculation.calculate(combinedPopulation);
        Arrays.sort(combinedPopulation, comparator);
        
        for(int i = POPULATION_SIZE; i < POPULATION_SIZE * 2; i ++)
            population[i - POPULATION_SIZE] = combinedPopulation[i];
    }

    public static void main(String[] args) {
        intializePopulation();
        int generationCount = 0;
        while(generationCount < MAX_GENERATION)
        {
            generationCount ++;
            System.out.print("Generation " + generationCount + ":\n");
            reproduce();
            selection();
        }

        try {
            PrintWriter varWriter = new PrintWriter("VAR.CSV");
            for (Solution s : population)
            {
                varWriter.println(s.rank + " " + s.crowdingDistance);
                for(int i = 0; i < s.getGeneLength(); i ++)
                    varWriter.print(s.getGene(i) + " ");
                varWriter.println();
            }
            varWriter.flush();
            varWriter.close();

            PrintWriter objWriter = new PrintWriter("FUN.CSV");
            for (Solution s : population)
            {
                for(int i = 0; i < s.getObjectiveCount(); i ++)
                    objWriter.print(s.getObjective(i) + " ");
                objWriter.println();
            }
            objWriter.flush();
            objWriter.close();
        } catch (Exception e) {

        }
    }
}