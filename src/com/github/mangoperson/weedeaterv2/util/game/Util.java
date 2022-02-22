package com.github.mangoperson.weedeaterv2.util.game;

import java.util.ArrayList;
import java.util.List;

public class Util {
	public static List<Double> fillArray(double number, int size) {
		List<Double> result = new ArrayList<Double>();
		for(int i = 0; i < size; i++) {
			result.add(number);
		}
		return result;
	}
	public static int getIndexFromPos(Matrix mat, int x, int y) {
		int width = mat.width;
		int height = mat.height;
		if(x > width) {
			x = width;
		}
		if(x < 0) {
			x = 0;
		}
		if(y > height) {
			y = height;
		}
		if(y < 1) {
			y = 1;
		}
		return width * (y - 1) + x;
	}
	public static int[] getPosFromIndex(Matrix mat, int index) {
		int width = mat.width;
		if(index > mat.numbers.size()) {
			index = mat.numbers.size() - 1;
		}
		if(index < 0) {
			index = 0;
		}
		int[] result = {};
		result[1] = (int)Math.ceil(index / width);
		result[0] = (int)index - width * (result[1] - 1);
		return result;
	}
}
