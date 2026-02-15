package com.day1;

public class Reverseofstring3 {

	public static void main(String[] args) {
		
		String input = "Hello";
		
		StringBuilder sb = new StringBuilder();
		
		char charArray[] = input.toCharArray();
		
		
		
		for (int index=charArray.length-1;  index>=0;index--) {
			sb.append(charArray[index]);
		}
              System.out.println(sb);
	}

}
