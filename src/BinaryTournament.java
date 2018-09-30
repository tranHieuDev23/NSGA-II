import java.util.List;
import java.util.Random;

public class BinaryTournament
{
    private static CrowdedComparisionOperator comparator = new CrowdedComparisionOperator();

    public static Solution execute(Solution[] solutions)
    {
        if (solutions.length == 1)
            return solutions[0];
        Random rn = new Random();
        int id1 = rn.nextInt(solutions.length);
        int id2;
        do
            id2 = rn.nextInt(solutions.length);
        while(id2 == id1);
        return comparator.compare(solutions[id1], solutions[id2]) == 1? solutions[id1] : solutions[id2];
    }
}