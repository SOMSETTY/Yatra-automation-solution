package com.day1;

import java.util.Arrays;

public class Reverseofstring2 {

	public static void main(String[] args) {
		
		String input = "Hello";
				
		char charArray[] = input.toCharArray();
		
		int left = 0 ;
		int right = charArray.length-1;
		char temp;
		
		while(left<right) {
			
            temp = charArray[left];
			charArray[left] = charArray[right];
			charArray[right] = temp;
			left++;
			right--;
			
			
		}
	  
		System.out.println(Arrays.toString(charArray));
		
		
			}

}
