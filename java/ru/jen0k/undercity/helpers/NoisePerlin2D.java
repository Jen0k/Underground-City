package ru.jen0k.undercity.helpers;

import javax.vecmath.Vector2d;
import java.util.Random;

public class NoisePerlin2D
{
    public NoisePerlin2D(long seedRandom, int vectorsCount)
    {
        this.seedRandom = seedRandom;
        this.random = new Random(seedRandom);
        this.vectorsCount = vectorsCount;
        this.pregenVectors = new Vector2d[vectorsCount];

        double angleStep = Math.PI * 2 / vectorsCount;
        for (int i = 0; i < vectorsCount; i++)
        {
            this.pregenVectors[i] = new Vector2d(Math.cos(angleStep * i), Math.sin(angleStep * i));
        }
    }

    private long seedRandom;
    private int vectorsCount;
    private Random random;
    private Vector2d[] pregenVectors;

    private Vector2d GetVectorForPoint(long x, long y)
    {
        random.setSeed(x ^ y);
        //random.setSeed(random.nextInt() + y);

        return pregenVectors[Math.abs(random.nextInt()) % vectorsCount];
    }

    public double Noise2D(double dx, double dy)
    {
        long left = (long)Math.floor(dx);
        long top = (long)Math.floor(dy);

        double localX = dx - left;
        double localY = dy - top;

        Vector2d gradientLeftTop = GetVectorForPoint(left, top);
        Vector2d gradientLeftBottom = GetVectorForPoint(left, top + 1);
        Vector2d gradientRightTop = GetVectorForPoint(left + 1, top);
        Vector2d gradientRightBottom = GetVectorForPoint(left + 1, top + 1);

        //System.out.println(gradientLeftTop + ":" + gradientLeftBottom + ":" + gradientRightTop + ":" + gradientRightBottom);

        Vector2d distanceToLeftTop = new Vector2d(localX, localY);
        Vector2d distanceToLeftBottom = new Vector2d(localX, localY - 1);
        Vector2d distanceToRightTop = new Vector2d(localX - 1, localY);
        Vector2d distanceToRightBottom = new Vector2d(localX - 1, localY - 1);

        double ly1 = gradientLeftTop.dot(distanceToLeftTop);
        double ly2 = gradientLeftBottom.dot(distanceToLeftBottom);
        double ry1 = gradientRightTop.dot(distanceToRightTop);
        double ry2 = gradientRightBottom.dot(distanceToRightBottom);

        double smoothedlocalX = MathFunctions.Quintic(localX);
        double smoothedlocalY = MathFunctions.Quintic(localY);

        double ly = MathFunctions.Lerp(ly1, ly2, smoothedlocalY);
        double ry = MathFunctions.Lerp(ry1, ry2, smoothedlocalY);

        return MathFunctions.Lerp(ly, ry, smoothedlocalX);
    }

    public double Noise2D(double dx, double dy, int octaves, double persistence)
    {
        double amplitude = 1;

        double max = 0;
        double result = 0;

        while (octaves-- > 0)
        {
            max += amplitude;
            result += Noise2D(dx, dy) * amplitude;
            amplitude *= persistence;
            dx *= 2;
            dy *= 2;
        }

        return result / max;
    }
}
