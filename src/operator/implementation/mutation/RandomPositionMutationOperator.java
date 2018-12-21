package operator.implementation.mutation;

import operator.MutationOperator;
import solution.DoubleSolution;
import utilities.RandomGenerator;

public class RandomPositionMutationOperator<S extends DoubleSolution<S>> implements MutationOperator<S>
{
    private final double VARIABLE_BOUND_LOW;
    private final double VARIABLE_BOUND_HIGH;

    public RandomPositionMutationOperator(double VARIABLE_BOUND_LOW, double VARIABLE_BOUND_HIGH)
    {
        this.VARIABLE_BOUND_LOW = VARIABLE_BOUND_LOW;
        this.VARIABLE_BOUND_HIGH = VARIABLE_BOUND_HIGH;
    }

    public S execute(S solution)
    {
        S mutatedSolution = solution.copy();
        int mutationPoint = RandomGenerator.getRandomInteger(0, mutatedSolution.getGeneLength() - 1);
        mutatedSolution.setGene(mutationPoint, RandomGenerator.getRandomDouble(VARIABLE_BOUND_LOW, VARIABLE_BOUND_HIGH));
        return mutatedSolution;
    }
}