package operator;

import java.util.List;

public interface CrossoverOperator<S extends Solution> extends Operator<List<S>, List<S>> 
{
    int getNumberOfRequiredParents();
    int getNumberOfGeneratedOffsprings();
}