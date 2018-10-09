package operator;

public interface Operator<Input, Output>
{
    Output execute(Input input);
}