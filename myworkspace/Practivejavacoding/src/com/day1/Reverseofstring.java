package com.day1;

public class Reverseofstring {

	public static void main(String[] args) {
		
		String input = "Hello";
		
		char charArray[] = input.toCharArray();
		
		for (int index=0;  index<=charArray.length-1;index++) {
			System.out.print(charArray[index]);
		}
		
		System.out.println("");
		
		for (int index=charArray.length-1;  index>=0;index--) {
			System.out.print(charArray[index]);
		}

	}

}
