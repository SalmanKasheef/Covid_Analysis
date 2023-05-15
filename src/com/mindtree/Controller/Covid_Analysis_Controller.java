package com.mindtree.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mindtree.Exception.NoStateFoundException;
import com.mindtree.Service.Covid_Analysis_Service;

public class Covid_Analysis_Controller {

	static Scanner sc = new Scanner(System.in);
	private Covid_Analysis_Service service ;
	
	public Covid_Analysis_Controller() {
		this.service=new Covid_Analysis_Service();
		
	}

	public void getStatesName() {
		this.service.getAllState();
		
	}
	
	public void getDistrictName() {
		System.out.print("Please Enter State Code");
		String state = sc.next();
		service.getAllDistrictByState(state);
	}

	public void displayDataByWithInDateRange() {
		// TODO Auto-generated method stub

		System.out.print("Plese enter the start date (yyyy-MM-dd):");
		String startDate = sc.nextLine();
		System.out.print("Plese enter the end date (yyyy-MM-dd):");
		String endDate = sc.nextLine();
		
		Date startdate =  Date.valueOf(startDate);
		Date enddate =  Date.valueOf(endDate);
		new SimpleDateFormat("yyyy-MM-dd").format(startdate);
		new SimpleDateFormat("yyyy-MM-dd").format(enddate);
		service.getDataBetweenDatesByState(startdate, enddate);
	}
	


	public void comparingTwoStatesFromGivenDateRange() throws NoStateFoundException {
		// TODO Auto-generated method stub
		System.out.print("Plese enter the start date (yyyy-MM-dd):");
		String startDate = sc.nextLine();
		System.out.print("Plese enter the end date (yyyy-MM-dd):");
		String endDate = sc.nextLine();
		System.out.print("Please enter first state code:");
		String state1 = sc.next();
		System.out.print("Please enter second state code:");
		String state2 = sc.next();
		Date startdate =  Date.valueOf(startDate);
		Date enddate =  Date.valueOf(endDate);
		new SimpleDateFormat("yyyy-MM-dd").format(startdate);
		new SimpleDateFormat("yyyy-MM-dd").format(enddate);
		service.compareDataBetweenStateByDate(startdate, enddate, state1, state2);

	}
	
	

}
