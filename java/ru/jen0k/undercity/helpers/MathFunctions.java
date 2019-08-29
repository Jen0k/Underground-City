package ru.jen0k.undercity.helpers;


public class MathFunctions
{
    public static double Lerp(double a, double b, double t)
    {
        return a + (b - a) * t;
    }

    public static double Cubic(double x)
    {
        return -2 * x * x * x + 3 * x * x;
    }

    public static double Cosine(double x)
    {
        return (1 - Math.cos(x * Math.PI)) / 2;
    }

    public static double Quintic(double x)
    {
        return x * x * x * (x * ( x * 6 - 15) + 10);
    }
}
