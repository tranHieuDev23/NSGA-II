import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NonDominatedSorting {
    public static void execute(Solution[] solutions)
    {
        int n = solutions.length;
        Queue<Integer> firstRankQueue = new LinkedList<>();
        Queue<Integer>[] dominating = new Queue[n];
        int[] dominatedCount = new int[n];

        for(int i = 0; i < n; i ++)
        {
            dominating[i] = new LinkedList<>();
            for(int j = 0; j < n; j ++)
            {
                if (solutions[i].dominate(solutions[j]))
                    dominating[i].add(j);
                if (solutions[j].dominate(solutions[i]))
                    dominatedCount[i] ++;
            }
            if (dominatedCount[i] == 0)
                firstRankQueue.add(i);
        }

        int round = 0;
        while(!firstRankQueue.isEmpty())
        {
            round ++;
            int siz = firstRankQueue.size();
            for(int i = 0; i < siz; i ++)
            {
                int id = firstRankQueue.poll();
                solutions[id].rank = round;
                while(!dominating[id].isEmpty())
                {
                    int dominatedId = dominating[id].poll();
                    if (-- dominatedCount[dominatedId] == 0)
                        firstRankQueue.add(dominatedId);
                }
            }
        }
    }
}