package problem.implementation;

import problem.Problem;
import solution.DoubleSolution;
import utilities.Function;
import utilities.RandomGenerator;

public class ZDT4<S extends DoubleSolution<S>> implements Problem<S>
{
    private final Function<S> G_FUNCTION = new Function<>()
    {
        public double calculate(S solution)
        {
            double result = 0;
            for(int i = 1; i < solution.getGeneLength(); i ++)
            {
                double x = solution.getGene(i);
                result += x * x - 10.0 * Math.cos(4.0 * Math.PI * x);
            }
            return 1 + 10 * (solution.getGeneLength() - 1) + result;
        }
    };

    @SuppressWarnings("unchecked")
    private final Function<S>[] OBJECTIVE_FUNCTIONS = new Function[]
    {
        new Function<S>() {
            public double calculate(S solution) {
                return solution.getGene(0);
            }
        },
        new Function<S>() {
            public double calculate(S solution) {
                double g = G_FUNCTION.calculate(solution);
                double x1 = solution.getGene(0);
                return g * (1 - Math.sqrt(x1 / g));
            }
        }
    };

    private static final double VARIABLE_BOUNDS_LOW[] = new double[]{0, -5, -5};
    private static final double VARIABLE_BOUNDS_HIGH[] = new double[]{1, 5, 5};

    public int getNumberOfVariables()
    {
        return 3;
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
        for (int i = 0; i < getNumberOfVariables(); i ++)
        {
            double x = solution.getGene(i);
            solution.setGene(i, Double.max(ZDT4.VARIABLE_BOUNDS_LOW[i], Double.min(ZDT4.VARIABLE_BOUNDS_HIGH[i], x)));
        }
        for(int i = 0; i < getNumberOfObjectives(); i ++)
            solution.setObjective(i, OBJECTIVE_FUNCTIONS[i].calculate(solution));
    }
    
    public S createRandomSolution(S solution)
    {
        solution.setGeneLength(getNumberOfVariables());
        solution.setObjectivesCount(getNumberOfObjectives());
        solution.setConstraintsCount(getNumberOfConstraints());
        for(int i = 0; i < getNumberOfVariables(); i ++)
            solution.setGene(i, RandomGenerator.getRandomDouble(VARIABLE_BOUNDS_LOW[i], VARIABLE_BOUNDS_HIGH[i]));
        evaluate(solution);
        return solution;
    }
}