package com.zilberoman.cardrecognizer.utils;

import java.util.stream.IntStream;

public class MathUtils {

    public static int getLevenshteinDistance(String targetStr, String sourceStr) {
        int targetStringLength = targetStr.length(), sourceStringLength = sourceStr.length();
        int[][] deltaArray = new int[targetStringLength + 1][sourceStringLength + 1];
        IntStream.rangeClosed(1, targetStringLength)
                .forEach(i -> deltaArray[i][0] = i);
        IntStream.rangeClosed(1, sourceStringLength)
                .forEach(j -> deltaArray[0][j] = j);

        for (int j = 1; j <= sourceStringLength; j++) {
            for (int i = 1; i <= targetStringLength; i++) {
                deltaArray[i][j] = targetStr.charAt(i - 1) == sourceStr.charAt(j - 1)
                        ? deltaArray[i - 1][j - 1]
                        : Math.min(deltaArray[i - 1][j] + 1,
                        Math.min(deltaArray[i][j - 1] + 1, deltaArray[i - 1][j - 1] + 1));
            }
        }

        return deltaArray[targetStringLength][sourceStringLength];
    }
}
