import java.util.List;

public class BinaryTournament
{
    private static CrowdedComparisionOperator comparator = new CrowdedComparisionOperator();

    public static Solution execute(Solution[] solutions)
    {
        if (solutions.length == 1)
            return solutions[0];
        int id1 = RandomGenerator.getRandomInteger(0, solutions.length - 1);
        int id2;
        do
            id2 = RandomGenerator.getRandomInteger(0, solutions.length - 1);
        while(id2 == id1);
        return comparator.compare(solutions[id1], solutions[id2]) == 1? solutions[id1] : solutions[id2];
    }
}