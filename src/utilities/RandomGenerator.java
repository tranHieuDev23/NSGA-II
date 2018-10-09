package utilities;

import java.util.Random;

public class RandomGenerator
{
    private static Random random = new Random();

    public static int getRandomInteger(int low, int high)
    {
        return random.nextInt(high - low + 1) + low;
    }

    public static double getRandomDouble(double low, double high)
    {
        return random.nextDouble() * (high - low) + low;
    }
}