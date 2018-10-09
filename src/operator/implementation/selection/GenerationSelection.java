package operator.implementation.selection;

import java.util.Comparator;
import java.util.List;

public class GenerationSelection<S extends Solution> implements SelectionOperator<List<S>, List<S>>
{
    private final Comparator<S> comparator;
    private final int maxPopulationSize;

    public GenerationSelection(Comparator<S> comparator, int maxPopulationSize)
    {
        this.comparator = comparator;
        this.maxPopulationSize = maxPopulationSize;
    }

    public List<S> execute(List<S> population)
    {
        population.sort(comparator);
        while(population.size() > maxPopulationSize)
            population.remove(0);
        return population;
    }
}