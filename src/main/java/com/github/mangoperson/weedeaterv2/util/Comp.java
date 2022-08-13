package com.github.mangoperson.weedeaterv2.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.BiFunction;

public class Comp {
    public static <T> T[][] matrix(BiFunction<Integer, Integer, T> function, int[] rangeX, int[] rangeY) {
        T[][] result = (T[][]) new Object[rangeX.length][rangeY.length];
        for (int i : range(rangeX.length)) {
            for (int j : range(rangeY.length)) {
                result[i][j] = function.apply(rangeX[i], rangeY[j]);
            }
        }
        return result;
    }

    public static int[] range(int min, int max) {
        int[] result = new int[max - min];
        for (int i = min; i < max; i++) {
            result[i - min] = i;
        }
        return result;
    }

    public static int[] range(int max) {
        return range(0, max);
    }

    public static BufferedImage toImage(int[][] grayscale) {
        BufferedImage result = new BufferedImage(grayscale.length, grayscale[0].length, BufferedImage.TYPE_BYTE_GRAY);
        for (int i : range(grayscale.length)) {
            for (int j : range(grayscale[0].length)) {
                int color = grayscale[i][j];
                result.setRGB(i, j, new Color(color, color, color).getRGB());
            }
        }
        return result;
    }

    public static BufferedImage toImage(int[][][] rgb) {
        BufferedImage result = new BufferedImage(rgb.length, rgb[0].length, BufferedImage.TYPE_INT_RGB);
        for (int i : range(rgb.length)) {
            for (int j : range(rgb[0].length)) {
                int[] color = rgb[i][j];
                result.setRGB(i, j, new Color(color[0], color[1], color[2]).getRGB());
            }
        }
        return result;
    }
}
