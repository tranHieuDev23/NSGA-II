package problem.implementation;

import problem.Problem;
import solution.DoubleSolution;
import utilities.Function;
import utilities.RandomGenerator;

public class ZDT1<S extends DoubleSolution> implements Problem<S>
{
    private final Function<S> G_FUNCTION = new Function<>()
    {
        public double calculate(S solution)
        {
            double result = 0;
            for(int i = 1; i < s.getGeneLength(); i ++)
                result += s.getGene(i);
            return 1 + result * 9 / (s.getGeneLength() - 1);
        }
    };

    private final Function[] OBJECTIVE_FUNCTIONS = new Function[]
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
                return g + (1 - Math.sqrt(x1 / g));
            }
        }
    };

    private static final double VARIABLE_BOUNDS_LOW[] = new double[]{0, 0, 0};
    private static final double VARIABLE_BOUNDS_HIGH[] = new double[]{1, 1, 1};

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
        for(int i = 0; i < getNumberOfObjectives() - 1; i ++)
            solution.setObjective(i, OBJECTIVE_FUNCTIONS[i].calculate(solution));
    }
    
    public S createRandomSolution()
    {
        S result = new S(getNumberOfVariables(), getNumberOfObjectives(), getNumberOfConstraints());
        for(int i = 0; i < getNumberOfVariables(); i ++)
            result.setGene(i, RandomGenerator.getRandomDouble(VARIABLE_BOUNDS_LOW[i], VARIABLE_BOUNDS_HIGH[i]));
        evaluate(result);
        return result;
    }
}