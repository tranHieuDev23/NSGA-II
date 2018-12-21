package problem;

import solution.Solution;

@SuppressWarnings("rawtypes")
public interface Problem<S extends Solution>
{
    int getNumberOfVariables();
    int getNumberOfObjectives();
    int getNumberOfConstraints();

    void evaluate(S solution);
    S createRandomSolution(S result);
}