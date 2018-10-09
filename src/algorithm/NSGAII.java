package algorithm;

import java.util.ArrayList;

import operator.implementation.comparision.CrowdedComparisionOperator;
import operator.CrossoverOperator;
import operator.MutationOperator;
import operator.SelectionOperator;
import operator.implementation.crossover.BLXCrossoverOperator;
import operator.implementation.mutation.RandomPositionMutationOperator;
import operator.implementation.selection.BinaryTournamentSelectionOperator;
import operator.implementation.selection.GenerationSelection;
import problem.Problem;
import solution.Solution;
import solution.implementation.NSGAIISolution;
import utilities.CrowdingDistanceCalculation;
import utilities.NonDominatedSort;
import utilities.RandomGenerator;

public class NSGAII
{
    private static final CrowdedComparisionOperator ccomparator = new CrowdedComparisionOperator();
    private final GenerationSelection<NSGAIISolution> generationSelection;

    private Problem<NSGAIISolution> problem;
    private SelectionOperator<ArrayList<NSGAIISolution>, NSGAIISolution> selectionOperator;
    private CrossoverOperator<NSGAIISolution> crossoverOperator;
    private MutationOperator<NSGAIISolution> mutationOperator;
    private int maxPopulationSize, numberOfGeneration;
    private double mutationRate;

    public NSGAII(Problem<NSGAIISolution> problem, int maxPopulationSize, int numberOfGeneration, double mutationRate)
    {
        this.problem = problem;
        this.maxPopulationSize = maxPopulationSize;
        this.numberOfGeneration = numberOfGeneration;
        this.mutationRate = mutationRate;
        this.generationSelection = new GenerationSelection<>(NSGAII.ccomparator, maxPopulationSize);

        // Default operators
        this.selectionOperator = new BinaryTournamentSelectionOperator<>(NSGAII.ccomparator);
        this.crossoverOperator = new BLXCrossoverOperator<>();
        this.mutationOperator = new RandomPositionMutationOperator<>(0, 1);
    }

    public void setSelectionOperator(SelectionOperator<ArrayList<NSGAIISolution>, NSGAIISolution> selectionOperator)
    {
        this.selectionOperator = selectionOperator;
    }

    public void setCrossoverOperator(CrossoverOperator<NSGAIISolution> crossoverOperator)
    {
        this.crossoverOperator = crossoverOperator;
    }

    public void setMutationOperator(MutationOperator<NSGAIISolution> mutationOperator)
    {
        this.mutationOperator = mutationOperator;
    }

    public ArrayList<NSGAIISolution> execute()
    {
        // Initialize population
        ArrayList<NSGAIISolution> population = new ArrayList<>();
        for (int i = 0; i < maxPopulationSize; i ++)
            population.add(problem.createRandomSolution());

        for(int generation = 1; generation <= numberOfGeneration; generation ++)
        {
            System.out.println("Generation " + generation + "/" + numberOfGeneration);

            // Preparation
            NonDominatedSort.execute(population);
            CrowdingDistanceCalculation.execute(population);
            
            // Reproduce
            ArrayList<NSGAIISolution> offspringPopulation = new ArrayList<>();
            while(offspringPopulation.size() < maxPopulationSize)
            {
                ArrayList<NSGAIISolution> parents = new ArrayList<>();
                for (int j = 0; j < crossoverOperator.getNumberOfRequiredParents(); j ++)
                    parents.add(selectionOperator.execute(population));
                ArrayList<NSGAIISolution> offsprings = crossoverOperator.execute(parents);
                for (NSGAIISolution solution : offsprings)
                    if (RandomGenerator.getRandomDouble(0, 1) <= mutationRate)
                        solution = mutationOperator.execute(solution);
                offspringPopulation.addAll(offsprings);
            }

            // Weed out unfit solutions
            ArrayList<NSGAIISolution> combinedPopulation = new ArrayList<>();
            combinedPopulation.addAll(population);
            combinedPopulation.addAll(offspringPopulation);
            population = generationSelection.execute(combinedPopulation);
        }

        return population;
    }
}