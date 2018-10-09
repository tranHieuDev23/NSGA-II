package solution;

public interface Solution<S extends Solution<S>, N extends Number>
{
    int getGeneLength();
    void setGeneLength(int length);
    N getGene(int position);
    void setGene(int position, N value);

    int getObjectivesCount();
    void setObjectivesCount(int number);
    double getObjective(int objectiveId);
    void setObjective(int objectiveId, double value);

    int getConstraintsCount();
    void setConstraintsCount(int number);
    double getConstraint(int constraintId);
    void setConstraint(int constraintId, double value);

    boolean dominate(S target);

    S copy();
}