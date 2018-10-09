package utilities;

import solution.Solution;

public interface Function<S extends Solution>
{
    double calculate(S solution);
}