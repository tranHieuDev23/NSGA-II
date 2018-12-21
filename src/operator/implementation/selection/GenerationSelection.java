package operator.implementation.selection;

import java.util.Comparator;
import java.util.ArrayList;

import operator.SelectionOperator;
import solution.Solution;

@SuppressWarnings("rawtypes")
public class GenerationSelection<S extends Solution> implements SelectionOperator<ArrayList<S>, ArrayList<S>>
{
    private final Comparator<S> comparator;
    private final int maxPopulationSize;

    public GenerationSelection(Comparator<S> comparator, int maxPopulationSize)
    {
        this.comparator = comparator;
        this.maxPopulationSize = maxPopulationSize;
    }

    public ArrayList<S> execute(ArrayList<S> population)
    {
        population.sort(comparator);
        while(population.size() > maxPopulationSize)
            population.remove(0);
        return population;
    }
}