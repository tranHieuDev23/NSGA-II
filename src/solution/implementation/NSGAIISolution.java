package solution.implementation;

import solution.DoubleSolution;

public class NSGAIISolution implements DoubleSolution<NSGAIISolution>
{
    private double[] gene, objectives, constraints;
    public int rank;
    public double crowdingDistance;

    public NSGAIISolution() {}

    public NSGAIISolution(int geneLength, int objectivesCount, int constraintsCount)
    {
        setGeneLength(geneLength);
        setObjectivesCount(objectivesCount);
        setConstraintsCount(constraintsCount);
    }

    public int getGeneLength()
    {
        return gene.length;
    }

    public void setGeneLength(int length)
    {
        gene = new double[length];
    }

    public Double getGene(int position)
    {
        return gene[position];
    }

    public void setGene(int position, Double value)
    {
        gene[position] = value;
    }

    public int getObjectivesCount()
    {
        return objectives.length;
    }

    public void setObjectivesCount(int number)
    {
        objectives = new double[number];
    }

    public double getObjective(int objectiveId)
    {
        return objectives[objectiveId];
    }

    public void setObjective(int objectiveId, double value)
    {
        objectives[objectiveId] = value;
    }

    public int getConstraintsCount()
    {
        return constraints.length;
    }

    public void setConstraintsCount(int number)
    {
        constraints = new double[number];
    }

    public double getConstraint(int constraintId)
    {
        return constraints[constraintId];
    }

    public void setConstraint(int constraintId, double value)
    {
        constraints[constraintId] = value;
    }

    public boolean dominate(NSGAIISolution target)
    {
        boolean result = false;
        for(int position = 0; position < objectives.length; position ++)
        {
            if (objectives[position] > target.objectives[position])
                result = true;
            if (objectives[position] < target.objectives[position])
                return false;
        }
        return result;
    }

    public NSGAIISolution copy()
    {
        NSGAIISolution copied = new NSGAIISolution();
        copied.gene = this.gene.clone();
        copied.objectives = this.objectives.clone();
        copied.constraints = this.constraints.clone();
        return copied;
    }
}