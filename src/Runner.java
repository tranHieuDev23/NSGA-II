import java.util.ArrayList;

import algorithm.NSGAII;
import solution.implementation.NSGAIISolution;
import problem.implementation.ZDT1;

public class Runner
{
    public static void main(String[] args) {
        NSGAII nsgaii = new NSGAII(new ZDT1<>(), 100, 1000, 0.2);
        ArrayList<NSGAIISolution> result = nsgaii.execute();
    }
}