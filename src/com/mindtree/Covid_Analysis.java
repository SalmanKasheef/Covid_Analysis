package com.mindtree;


import java.util.Scanner;

import com.mindtree.Controller.Covid_Analysis_Controller;
import com.mindtree.Exception.NoStateFoundException;

public class Covid_Analysis {

	public static void main(String[] args) throws NoStateFoundException {
	
		Scanner sc=new Scanner(System.in);
		
		Covid_Analysis_Controller CA=new Covid_Analysis_Controller();
		
		System.out.println("----------------------------------------");
		
		System.out.println("1: Get States Names");
		
		System.out.println("2: Get District Names for Given States");
		
		System.out.println("3: Display Data By State With In Date Range");
		
		System.out.println("4: Display Confirmed Cases By Comparing Two States For a given Date Range");
		
		System.out.println("5: EXIT");
		
		System.out.println("Please Select Option");
		
		int Option=sc.nextInt();
		
		switch(Option){
			
		case 1: 
			CA.getStatesName();
			break;
		
		case 2:
			CA.getDistrictName();
			break;
		
		case 3:
			CA.displayDataByWithInDateRange();
			break;
			
		case 4:
			CA.comparingTwoStatesFromGivenDateRange();
			break;
		
		case 5:
			break;
	}
		sc.close();
	}

}
