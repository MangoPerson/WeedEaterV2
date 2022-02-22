package com.github.mangoperson.weedeaterv2.util.game;

public class MatTerm {
	public MatTerm(Matrix mat, int index) {
		this.mat = mat;
		this.index = index;
		this.number = mat.numbers.get(index);
		this.x = Util.getPosFromIndex(mat, index)[0];
		this.y = Util.getPosFromIndex(mat, index)[1];
	}
	public MatTerm(Matrix mat, int x, int y) {
		this.mat = mat;
		this.x = x;
		this.y = y;
		this.index = Util.getIndexFromPos(mat, x, y);
		this.number = mat.numbers.get(index);
	}
	
	public Matrix mat;
	
	public double number;
	
	public int x;
	
	public int y;
	
	public int index;
	
	public void set(double number) {
		mat.setTerm(x, y, number);
	}
}
