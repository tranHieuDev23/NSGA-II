import java.io.FileWriter;
import java.util.ArrayList;

import algorithm.NSGAII;
import solution.implementation.NSGAIISolution;
import problem.implementation.ZDT1;

public class Runner
{
    public static void main(String[] args) {
        NSGAII nsgaii = new NSGAII(new ZDT1<>(), 100, 1000, 0.2);
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