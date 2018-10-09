package operator.implementation.selection;

import java.util.Comparator;

import operator.SelectionOperator;
import solution.Solution;
import utilities.RandomGenerator;

public class BinaryTournamentSelectionOperator<S extends Solution> implements SelectionOperator<List<S>, S>
{
    private final Comparator<S> comparator;

    public BinaryTournamentSelectionOperator(Comparator<S> comparator)
    {
        this.comparator = comparator;
    }

    public List<S> execute(List<S> solutions)
    {
        if (solutions.length == 1)
            return solutions;
        int id1 = RandomGenerator.getRandomInteger(0, solutions.length - 1);
        int id2;
        do
            id2 = RandomGenerator.getRandomInteger(0, solutions.length - 1);
        while(id2 == id1);
        return comparator.compare(solutions[id1], solutions[id2]) == 1? solutions[id1] : solutions[id2];
    }
}