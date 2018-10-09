package operator.implementation.crossover;

import operator.CrossoverOperator;
import solution.DoubleSolution;
import random.RandomGenerator;

public class BLXCrossoverOperator<S extends DoubleSolution<S>> implements CrossoverOperator<S>
{
    private static final double BLX_ALPHA_PARAMETER = .5;

    S execute(List<S> parents)
    {
        S offspring = new S();
        for(int i = 0; i < newGene.length; i ++)
        {
            double d = Math.abs(parents.get(0).gene[i] - parents.get(1).gene[i]);
            double low = Double.min(parents.get(0).gene[i], parents.get(1).gene[i]) - BLXCrossoverOperator.BLX_ALPHA_PARAMETER * d;
            double high = Double.max(parents.get(0).gene[i], parents.get(1).gene[i]) + BLXCrossoverOperator.BLX_ALPHA_PARAMETER * d;
            offspring.setGene(i, RandomGenerator.getRandomDouble(low, high));
        }
        return offspring;
    }
}