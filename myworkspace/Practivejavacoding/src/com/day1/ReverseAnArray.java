package com.day1;

import java.util.Arrays;

public class ReverseAnArray {

	public static void main(String[] args) {
		
		int a[] = {1,3,5};
		System.out.println(Arrays.toString(a));
		
	
		
		int left = 0 ;
		int right = a.length-1;
		int temp;
		
		while(left<right) {
			
            temp = a[left];
			a[left] = a[right];
			a[right] = temp;
			left++;
			right--;
			
			
		}
	  
		System.out.println(Arrays.toString(a));
		
		
			}

}
