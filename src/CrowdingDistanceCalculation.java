import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CrowdingDistanceCalculation
{
    private static class ObjectiveComparator implements Comparator<Solution> 
    {
        private int objective;

        public void setObjective(int objective)
        {
            this.objective = objective;
        }

        public int compare(Solution s1, Solution s2)
        {
            return Double.compare(s1.getObjective(objective), s2.getObjective(objective));
        }
    };

    private static ObjectiveComparator comparator = new ObjectiveComparator();

    public static void calculate(Solution[] solutions)
    {
        int n = solutions.length;
        int objectiveCount = solutions[0].getObjectiveCount();
        
        for(int o = 0; o < objectiveCount; o ++)
        {
            comparator.setObjective(o);
            Arrays.sort(solutions, comparator);
            solutions[0].crowdingDistance = solutions[n - 1].crowdingDistance = Double.POSITIVE_INFINITY;
            double fMin = solutions[0].getObjective(o);
            double fMax = solutions[n - 1].getObjective(o);
            for (int i = 1; i < solutions.length - 1; i ++)
                solutions[i].crowdingDistance += (solutions[i + 1].getObjective(o) - solutions[i - 1].getObjective(o)) / (fMax - fMin);
        }
    }
}