package main;

import java.util.Date;

public class NumberClass {
	private int i;
	private int j;
	Test1 tt;
	Date date;
	
	public NumberClass() {}
	
	public NumberClass(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public void print() {
		System.out.println(tt.getX());
	}
	
	public NumberClass(Test1 tt) {
		this.tt= tt;
	}

	public void setTt(Test1 tt) {
		this.tt = tt;
	}

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
}
