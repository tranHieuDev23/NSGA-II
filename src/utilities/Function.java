package utilities;

import solution.Solution;

@SuppressWarnings("rawtypes")
public interface Function<S extends Solution>
{
    double calculate(S solution);
}