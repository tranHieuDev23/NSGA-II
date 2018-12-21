package problem.implementation;

import problem.Problem;
import solution.DoubleSolution;
import utilities.Function;
import utilities.RandomGenerator;

public class SCH<S extends DoubleSolution<S>> implements Problem<S>
{
    private static final double VARIABLE_BOUND_LOW = -1e3;
    private static final double VARIABLE_BOUND_HIGH = 1e3;

    @SuppressWarnings("unchecked")
    private final Function<S>[] OBJECTIVE_FUNCTIONS = new Function[]
    {
        new Function<S>()
        {
            public double calculate(S s)
            {
                return s.getGene(0) * s.getGene(0);
            }
        },
        new Function<S>()
        {
            public double calculate(S s)
            {
                return (s.getGene(0) - 2) * (s.getGene(0) - 2);
            }
        }
    };

    
    public int getNumberOfVariables()
    {
        return 1;
    }
    
    public int getNumberOfObjectives()
    {
        return OBJECTIVE_FUNCTIONS.length;
    }

    public int getNumberOfConstraints()
    {
        return 0;
    }

    public void evaluate(S solution)
    {
        for(int i = 0; i < OBJECTIVE_FUNCTIONS.length; i ++)
            solution.setObjective(i, OBJECTIVE_FUNCTIONS[i].calculate(solution));
    }
    
    public S createRandomSolution(S result)
    {
        result.setGeneLength(getNumberOfVariables());
        result.setObjectivesCount(getNumberOfObjectives());
        result.setConstraintsCount(getNumberOfConstraints());
        for(int i = 0; i < result.getGeneLength(); i ++)
            result.setGene(i, RandomGenerator.getRandomDouble(VARIABLE_BOUND_LOW, VARIABLE_BOUND_HIGH));
        evaluate(result);
        return result;
    }
}