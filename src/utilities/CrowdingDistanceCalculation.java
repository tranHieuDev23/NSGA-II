package utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

import solution.implementation.NSGAIISolution;

public class CrowdingDistanceCalculation
{
    private static class ObjectiveComparator implements Comparator<NSGAIISolution> 
    {
        private int objective;

        public void setObjective(int objective)
        {
            this.objective = objective;
        }

        public int compare(NSGAIISolution s1, NSGAIISolution s2)
        {
            return Double.compare(s1.getObjective(objective), s2.getObjective(objective));
        }
    };

    private static ObjectiveComparator comparator = new ObjectiveComparator();

    public static void execute(ArrayList<NSGAIISolution> solutions)
    {
        for(NSGAIISolution s : solutions)
            s.crowdingDistance = 0;

        int n = solutions.size();
        int objectiveCount = solutions.get(0).getObjectivesCount();
        
        for(int o = 0; o < objectiveCount; o ++)
        {
            comparator.setObjective(o);
            solutions.sort(comparator);
            solutions.get(0).crowdingDistance = solutions.get(n - 1).crowdingDistance = Double.POSITIVE_INFINITY;
            double fMin = solutions.get(0).getObjective(o);
            double fMax = solutions.get(n - 1).getObjective(o);
            for (int i = 1; i < solutions.size() - 1; i ++)
                solutions.get(i).crowdingDistance += (solutions.get(i + 1).getObjective(o) - solutions.get(i - 1).getObjective(o)) / (fMax - fMin);
        }
    }
}