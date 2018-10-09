package operator.implementation.crossover;

import java.util.ArrayList;

import operator.CrossoverOperator;
import solution.DoubleSolution;
import utilities.RandomGenerator;

public class BLXCrossoverOperator<S extends DoubleSolution<S>> implements CrossoverOperator<S>
{
    private static final double BLX_ALPHA_PARAMETER = .5;

    public ArrayList<S> execute(ArrayList<S> parents)
    {
        S offspring = parents.get(0).copy();
        for(int i = 0; i < offspring.getGeneLength(); i ++)
        {
            double d = Math.abs(parents.get(0).getGene(i) - parents.get(1).getGene(i));
            double low = Double.min(parents.get(0).getGene(i), parents.get(1).getGene(i)) - BLXCrossoverOperator.BLX_ALPHA_PARAMETER * d;
            double high = Double.max(parents.get(0).getGene(i), parents.get(1).getGene(i)) + BLXCrossoverOperator.BLX_ALPHA_PARAMETER * d;
            offspring.setGene(i, RandomGenerator.getRandomDouble(low, high));
        }
        ArrayList<S> result = new ArrayList<>();
        result.add(offspring);
        return result;
    }

    public int getNumberOfRequiredParents()
    {
        return 2;
    }

    public int getNumberOfGeneratedOffsprings()
    {
        return 1;
    }
}