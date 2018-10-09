package utilities;

public interface Function<S extends Solution>
{
    double calculate(S solution);
}