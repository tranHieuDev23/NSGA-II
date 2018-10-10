import java.io.FileWriter;
import java.util.ArrayList;

import algorithm.NSGAII;
import operator.implementation.mutation.RandomPositionMutationOperator;
import solution.implementation.NSGAIISolution;
import problem.implementation.SCH;

public class Runner
{
    public static void main(String[] args) {
        NSGAII nsgaii = new NSGAII(new SCH<>(), 250, 1000, 0.2);
        nsgaii.setMutationOperator(new RandomPositionMutationOperator<>(-1e3, 1e3));
        ArrayList<NSGAIISolution> result = nsgaii.execute();
        try {
            FileWriter variableWriter = new FileWriter("VAR.CSV");
            FileWriter functionWriter = new FileWriter("FUN.CSV");
            for (NSGAIISolution solution : result) {
                variableWriter.write(solution.rank + " " + solution.crowdingDistance + "\n");
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