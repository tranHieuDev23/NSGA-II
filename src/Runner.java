import java.io.FileWriter;
import java.util.ArrayList;

import algorithm.NSGAII;
import operator.implementation.mutation.RandomPositionMutationOperator;
import solution.implementation.NSGAIISolution;
import utilities.RealtimePlotter;
import problem.implementation.ZDT3;

public class Runner
{
    private static final long DELAY_DURATION = 10000;
    public static void main(String[] args) {
        NSGAII nsgaii = new NSGAII(new ZDT3<>(), 500, 300, 0.2, new RealtimePlotter("NSGA-II"));
        nsgaii.setMutationOperator(new RandomPositionMutationOperator<>(-1e3, 1e3));
        long start = System.currentTimeMillis();
        while(start >= System.currentTimeMillis() - DELAY_DURATION); // do nothing
        
        ArrayList<NSGAIISolution> result = nsgaii.execute();
        try {
            FileWriter variableWriter = new FileWriter("VAR.CSV");
            FileWriter functionWriter = new FileWriter("FUN.CSV");
            for (NSGAIISolution solution : result) {
                for (int i = 0; i < solution.getGeneLength(); i ++)
                    variableWriter.write(solution.getGene(i).toString() + " ");
                variableWriter.write('\n');
                for (int i = 0; i < solution.getObjectivesCount(); i ++)
                    functionWriter.write(solution.getObjective(i) + " ");
                functionWriter.write('\n');
            }
            variableWriter.close();
            functionWriter.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}