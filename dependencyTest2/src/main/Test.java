package main;

import java.util.Date;

public class Test {
	public static void main(String[] args) {
		NumberClass num = new NumberClass();
		num.setI(1);
		System.out.println(num.getI());
		NumberClass num1 = new NumberClass(10, 20);
		System.out.println("1 : ");
		NumberClass num2 = new NumberClass(new Test1());
		System.out.println("2 : ");
		System.out.println(num1.getI());
		num2.print();
		System.out.println("3 : ");
		num1.setDate(new Date());
		System.out.println("4 : ");
		num.setTt(new Test1);
		num.print();
	}
}
