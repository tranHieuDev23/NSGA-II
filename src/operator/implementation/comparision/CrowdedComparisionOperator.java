package comparator.implementation.comparision;

import java.util.Comparator;
import solution.implementation.NSGAIISolution;

public class CrowdedComparisionOperator implements Comparator<NSGAIISolution>
{
    public int compare(NSGAIISolution s1, NSGAIISolution s2)
    {
        if (s1.rank != s2.rank)
            return - Integer.compare(s1.rank, s2.rank);
        return Double.compare(s1.crowdingDistance, s2.crowdingDistance);
    }
}