package ru.jen0k.undercity.helpers;

import java.util.*;

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

        // Генерируем сочетания соседних относительных координат векторов для заданного количества измерений для обхода в цикле.
        if (dimmensions > 1) {
            int[][] coordinatePairs = new int[this.dimmensions][2];
            for (int d = 0; d < this.dimmensions; d++) {
                coordinatePairs[d] = new int[]{0, 1};
            }

            int[][] firstSet = new int[coordinatePairs[0].length][coordinatePairs.length];
            for (int i = 0; i < coordinatePairs[0].length; i++) {
                firstSet[i][0] = coordinatePairs[0][i];
            }

            this.coordinatesMap = GenerateCoordinatesMap(1, coordinatePairs, firstSet);
        }
        else
        {
            this.coordinatesMap = new int[0][0];
        }
    }

    private int dimmensions;
    private double[][] pregenVectors;
    private int[][] coordinatesMap;

    private double Dot(double[] a, double[] b)
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

    private int[][] GenerateCoordinatesMap(int index, int[][] map, int[][] alredyGenerated)
    {
        int[][] newGeneration = new int[alredyGenerated.length * map[index].length][map.length];;

        for (int i = 0; i < alredyGenerated.length; i++)
        {
            for (int j = 0; j < map[index].length; j++)
            {
                newGeneration[j + (i * map[index].length)] = alredyGenerated[i].clone();
                newGeneration[j + (i * map[index].length)][index] = map[index][j];
            }
        }

        if (index < map.length - 1)
        {
            newGeneration = GenerateCoordinatesMap(index + 1, map, newGeneration);
        }

        return newGeneration;
    }

    private double LerpRecursively(int dimmensionIndex, double[] localCoordinates, double[] dotProducts)
    {
        double[] interpolatedDotProducts = new double[dotProducts.length / 2];
        for (int i = 0; i < dotProducts.length ; i += 2)
        {
            interpolatedDotProducts[i / 2] = MathFunctions.Lerp(dotProducts[i], dotProducts[i+1], localCoordinates[dimmensions - dimmensionIndex - 1]);
        }

        if (interpolatedDotProducts.length > 1)
        {
            return LerpRecursively(dimmensionIndex + 1, localCoordinates, interpolatedDotProducts);
        }

        return interpolatedDotProducts[0];
    }

    public double Noise(double... coordinates) throws IllegalArgumentException
    {
        if (coordinates.length != this.dimmensions)
        {
            throw new IllegalArgumentException("The number of coordinates should be equal to the number of dimmensions!");
        }

        long[] nodePosition = new long[dimmensions];
        for (int n = 0; n < dimmensions; n++)
        {
            nodePosition[n] = (long)Math.floor(coordinates[n]);
        }

        double[] localCoordinates = new double[dimmensions];
        for (int n = 0; n < dimmensions; n++)
        {
            localCoordinates[n] =  coordinates[n] - nodePosition[n];
        }

        double[][] neighborsVectors = new double[coordinatesMap.length][dimmensions];
        for (int p = 0; p < coordinatesMap.length; p++)
        {
            long[] neighborCoordinates = new long[dimmensions];
            for (int d = 0; d < dimmensions; d++)
            {
                neighborCoordinates[d] = nodePosition[d] + coordinatesMap[p][d];
            }
            neighborsVectors[p] = GetVectorForPoint(neighborCoordinates);
        }

        double[][] localVectors = new double[coordinatesMap.length][dimmensions];
        for (int p = 0; p < coordinatesMap.length; p++)
        {
            for (int d = 0; d < dimmensions; d++)
            {
                localVectors[p][d] = localCoordinates[d] - coordinatesMap[p][d];
            }
        }

        double[] dotProducts = new double[coordinatesMap.length];
        for (int p = 0; p < coordinatesMap.length; p++)
        {
            dotProducts[p] = Dot(neighborsVectors[p], localVectors[p]);
        }

        double[] smoothedLocalCoordinates = new double[dimmensions];
        for (int d = 0; d < dimmensions; d++)
        {
            smoothedLocalCoordinates[d] = MathFunctions.Quintic(localCoordinates[d]);
        }

        return LerpRecursively(0, smoothedLocalCoordinates, dotProducts);
    }

    public double Noise(int octaves, double persistence, double... coordinates)
    {
        double amplitude = 1;

        double max = 0;
        double result = 0;

        while (octaves-- > 0)
        {
            max += amplitude;
            result += Noise(coordinates) * amplitude;
            amplitude *= persistence;

            for (int i = 0; i < coordinates.length; i++)
            {
                coordinates[i] *= 2;

            }
        }

        return result / max;
    }
}
