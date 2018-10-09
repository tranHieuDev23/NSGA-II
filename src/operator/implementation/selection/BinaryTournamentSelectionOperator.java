package operator.implementation.selection;

import java.util.Comparator;
import java.util.ArrayList;

import operator.SelectionOperator;
import solution.Solution;
import utilities.RandomGenerator;

public class BinaryTournamentSelectionOperator<S extends Solution> implements SelectionOperator<ArrayList<S>, S>
{
    private final Comparator<S> comparator;

    public BinaryTournamentSelectionOperator(Comparator<S> comparator)
    {
        this.comparator = comparator;
    }

    public S execute(ArrayList<S> solutions)
    {
        if (solutions.size() == 1)
            return solutions.get(0);
        int id1 = RandomGenerator.getRandomInteger(0, solutions.size() - 1);
        int id2;
        do
            id2 = RandomGenerator.getRandomInteger(0, solutions.size() - 1);
        while(id2 == id1);
        return comparator.compare(solutions.get(id1), solutions.get(id2)) == 1? solutions.get(id1) : solutions.get(id2);
    }
}