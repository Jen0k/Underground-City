package ru.jen0k.undercity.helpers;

import java.util.*;
import java.util.stream.Collectors;

public class NoisePerlin
{
    public NoisePerlin(long seedRandom, int dimmensions, int vectorsPerDimmension) throws IllegalArgumentException
    {
        if (dimmensions < 1) {
            throw new IllegalArgumentException("The number of dimmensions cannot be less than one!");
        }

        if (vectorsPerDimmension < 1) {
            throw new IllegalArgumentException("The number of vectors cannot be less than one!");
        }

        this.dimmensions = dimmensions;
        this.pregenVectors = new double[vectorsPerDimmension * this.dimmensions][this.dimmensions];

        Random random = new Random(seedRandom);

        // Генерируем массив векторов через полярные координаты. Если найдём способ быстро и равномерно преобразовывать
        // координаты в индекс этого списка, то это будет оптимальнее, чем делать это же во время генерации шума.
        for (int i = 0; i < this.pregenVectors.length; i++)
        {
            double[] newVector = new double[this.dimmensions];

            if (dimmensions > 1) {
                Arrays.fill(newVector, 1.0);

                // Генерируем случайные Фи для dimmensions измерений
                double[] angles = new double[this.dimmensions - 1];
                for (int a = 0; a < this.dimmensions - 2; a++)
                {
                    angles[a] = MathFunctions.Lerp(0, Math.PI, random.nextDouble());
                }
                angles[this.dimmensions - 2] = MathFunctions.Lerp(0, Math.PI * 2, random.nextDouble());

                // Преобразуем полярные координаты в декартовы, предполагая, что r равно 1, ибо нам нужны нормализованные вектора.
                for (int n = 0; n < this.dimmensions; n++)
                {
                    for (int s = 0; s < n; s++)
                    {
                        newVector[n] *= Math.sin(angles[s]);
                    }

                    if (n < this.dimmensions - 1)
                    {
                        newVector[n] *= Math.cos(angles[n]);
                    }
                }
            }
            else {
                newVector[0] = MathFunctions.Lerp(-1, 1, random.nextDouble());
            }

            this.pregenVectors[i] = newVector;
        }

        Object x = product(Arrays.asList("x", "x+1"), Arrays.asList("y", "y+1"), Arrays.asList("z", "z+1") );
    }

    private int dimmensions;
    private double[][] pregenVectors;

    private double Dot(double a[], double b[])
    {
        double result = 0;
        for (int n = 0; n < a.length; n++)
        {
            result += a[n] * b[n];
        }
        return result;
    }

    private double[] GetVectorForPoint(long... coordinates)
    {
        long position = 0;
        for (int n = 0; n < dimmensions; n++)
        {
            position ^= coordinates[n];
        }
        return pregenVectors[Math.abs((int)(position % pregenVectors.length))];
    }

    private List<?> product(List<?>... a) {
        if (a.length >= 2) {
            List<?> product = a[0];
            for (int i = 1; i < a.length; i++) {
                product = product(product, a[i]);
            }
            return product;
        }

        return Collections.emptyList();
    }

    private <A, B> List<?> product(List<A> a, List<B> b) {
        return Optional.of(a.stream()
                .map(e1 -> Optional.of(b.stream().map(e2 -> Arrays.asList(e1, e2)).collect(Collectors.toList())).orElse(Collections.emptyList()))
                .flatMap(List::stream)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    public double Noise(double... coordinates) throws IllegalArgumentException
    {
        if (coordinates.length != this.dimmensions)
        {
            throw new IllegalArgumentException("The number of coordinates should be equal to the number of dimmensions!");
        }

        double[] nodePosition = new double[dimmensions];
        for (int n = 0; n < dimmensions; n++)
        {
            nodePosition[n] = Math.floor(coordinates[n]);
        }

        double[] localCoordinates = new double[dimmensions];
        for (int n = 0; n < dimmensions; n++)
        {
            localCoordinates[n] =  coordinates[n] - nodePosition[n];
        }

        int nodeVectorsCount = (int)Math.pow(2, dimmensions);
        double[][] nodeVectors = new double[nodeVectorsCount][dimmensions];
        for (int d = 0; d < dimmensions; d++)
        {

        }


        return 0;
    }
}
