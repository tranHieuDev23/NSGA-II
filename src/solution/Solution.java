package solution;

public interface Solution<S extends Solution<S>, N extends Number>
{
    int getGeneLength();
    N getGene(int position);
    void setGene(int position, N value);

    int getObjectivesCount();
    double getObjective(int objectiveId);

    int getConstraintsCount();
    double getConstraint(int constraintId);

    S copy();
}