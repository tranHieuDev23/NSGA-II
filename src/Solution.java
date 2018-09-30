import java.util.Random;

public class Solution
{
    private static final double BLX_ALPHA_PARAMETER = .5;
    private static final double VARIABLE_BOUND_LOW = 0;
    private static final double VARIABLE_BOUND_HIGH = 0;

    private static final Random RANDOM_GENERATOR = new Random();

    private static Function gFunction = new Function() {
        public double calculate(Solution solution) {
            double sum = 0;
            for(int i = 1; i < solution.gene.length - 1; i ++)
                sum += solution.gene[i];
            return 1.0 + sum / (solution.gene.length - 1);
        }
    };
    private static Function[] objectiveFunctions = new Function[]{
        new Function() {
            public double calculate(Solution solution) {
                return solution.gene[0];
            }
        },
        new Function() {
            public double calculate(Solution solution) {
                double g = gFunction.calculate(solution);
                return g * (1 - Math.sqrt(solution.gene[0] / g));
            }
        }
    };

    private double[] gene;
    private double[] objectives;
    public int rank;
    public double crowdingDistance;

    private void calculateObjectives()
    {
        this.objectives = new double[objectiveFunctions.length];
        for(int i = 0; i < objectiveFunctions.length; i ++)
            objectives[i] = objectiveFunctions[i].calculate(this);
    }

    public Solution(double[] gene)
    {
        this.gene = gene;
        calculateObjectives();
    }

    public Solution(Solution solution)
    {
        this.gene = solution.gene.clone();
        this.objectives = solution.objectives.clone();
    }

    public int getGeneLength()
    {
        return gene.length;
    }

    public double getGene(int geneId)
    {
        return gene[geneId];
    }

    public void setGene(int geneId, double value)
    {
        gene[geneId] = value;
        calculateObjectives();
    }

    public double getObjective(int objectiveId)
    {
        return objectives[objectiveId];
    }

    public int getObjectiveCount()
    {
        return objectives.length;
    }

    public boolean dominate(Solution solution)
    {
        boolean result = false;
        for(int i = 0; i < objectives.length; i ++)
        {
            if (this.objectives[i] > solution.objectives[i])
                return false;
            if (this.objectives[i] < solution.objectives[i])
                result = true;
        }
        return result;
    }

    private static double getRandomDouble(double low, double high)
    {
        return RANDOM_GENERATOR.nextDouble() * (high - low) + low;
    }

    public static Solution crossover(Solution parent1, Solution parent2) 
    {
        double[] newGene = new double[parent1.gene.length];
        for(int i = 0; i < newGene.length; i ++)
        {
            double d = Math.abs(parent1.gene[i] - parent2.gene[i]);
            double low = Double.max(
                Double.min(parent1.gene[i], parent2.gene[i]) - BLX_ALPHA_PARAMETER * d,
                VARIABLE_BOUND_LOW
            );
            double high = Double.min(
                Double.max(parent1.gene[i], parent2.gene[i]) + BLX_ALPHA_PARAMETER * d,
                VARIABLE_BOUND_HIGH
            );
            newGene[i] = getRandomDouble(low, high);
        }
        return new Solution(newGene);
    }

    public static Solution mutate(Solution solution)
    {
        Solution mutatedSolution = new Solution(solution);
        Random rn = new Random();
        int mutationPoint = rn.nextInt(solution.gene.length);
        mutatedSolution.setGene(mutationPoint, getRandomDouble(VARIABLE_BOUND_LOW, VARIABLE_BOUND_HIGH));
        return mutatedSolution;
    }
}