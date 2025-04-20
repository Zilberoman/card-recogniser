package com.zilberoman.cardrecognizer.converters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BinaryStringConverter {
    private static final int WHITE_COLOR = 0xFFFFFFFF;
    private static final int LIGHT_GRAY_COLOR = -8882056;
    public static final String SPACE = " ";
    private static final String STAR = "*";

    public static String convert(BufferedImage image) {
        StringBuilder binaryString = new StringBuilder();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 1; x < image.getWidth(); x++) {
                binaryString.append(convertColor(image.getRGB(x, y)));
            }

            binaryString.append(System.lineSeparator());
        }

        return binaryString.toString();
    }

    private static String convertColor(int color) {
        return color == WHITE_COLOR || color == LIGHT_GRAY_COLOR ? SPACE : STAR;
    }
}
