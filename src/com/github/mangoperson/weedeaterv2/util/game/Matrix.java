package com.github.mangoperson.weedeaterv2.util.game;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	public Matrix(int width, int height, double[] numbers) {
		this.width = width;
		this.height = height;
		this.numbers = Util.fillArray(1, width * height);
		for(int i = 0; i < numbers.length; i++) {
			this.numbers.set(i, numbers[i]);
		}
	}
	public Matrix(int width, int height, double fill) {
		this.width = width;
		this.height = height;
		this.numbers = new ArrayList<Double>();
		List<Double> numbers = Util.fillArray(fill, width * height);
		for(int i = 0; i < numbers.size(); i++) {
			this.numbers.add(numbers.get(i));
		}
	}
	
	public final int width;
	
	public final int height;
	
	public final List<Double> numbers;
	
	public MatTerm retreiveTerm(int index) {
		return new MatTerm(this, index);
	}
	
	public MatTerm retreiveTerm(int x, int y) {
		return new MatTerm(this, x, y);
	}
	
	public void setTerm(int index, double number) {
		this.numbers.set(index, number);
	}
	public void setTerm(int x, int y, double number) {
		this.numbers.set(Util.getIndexFromPos(this, x, y), number);
	}
	
	public String getDisplay() {
		String result = "";
		int j = 1;
		for(int i = 0; i < numbers.size(); i++) {
			Double number = numbers.get(i);
			result += number.toString() + " ";
			if(i == width * j -  1) {
				result += "\n";
				j += 1;
			}
		}
		return result;
	}
	
	public void fill(double arg0) {
		for(int i = 0; i < numbers.size(); i++) {
			numbers.set(i, arg0);
		}
	}
	
}
