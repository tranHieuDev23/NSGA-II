package operator;

import java.util.ArrayList;

import solution.Solution;

public interface CrossoverOperator<S extends Solution> extends Operator<ArrayList<S>, ArrayList<S>> 
{
    int getNumberOfRequiredParents();
    int getNumberOfGeneratedOffsprings();
}