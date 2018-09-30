import java.util.Comparator;

public class CrowdedComparisionOperator implements Comparator<Solution>
{
    public int compare(Solution s1, Solution s2)
    {
        if (s1.rank != s2.rank)
            return - Integer.compare(s1.rank, s2.rank);
        return Double.compare(s1.crowdingDistance, s2.crowdingDistance);
    }
}