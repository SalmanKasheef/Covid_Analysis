package com.mindtree.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mindtree.DAO.CovidDataDAOImpl;
import com.mindtree.Exception.InvalidDateException;
import com.mindtree.Exception.InvalidDateRangeException;
import com.mindtree.Exception.InvalidStateCodeException;
import com.mindtree.Exception.NoDataFoundException;
import com.mindtree.Exception.NoStateFoundException;
import com.mindtree.model.Covid_Data;

public class Covid_Analysis_Service {

	private CovidDataDAOImpl dao;
//	private String state_1;
//	private String tested;
//	private String confirmed1;

	public Covid_Analysis_Service() {
		dao = new CovidDataDAOImpl();
	}


	public void getAllState() {
		// TODO Auto-generated method stub
		Collection<Covid_Data> states = new ArrayList<>();
		try {
			
			states = dao.getListOfStates();
			states.stream().forEach(state ->{
				System.out.println(state.getState());
			});

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public void getAllDistrictByState(String state) {
		// TODO Auto-generated method stub
		Collection<Covid_Data> districts = new ArrayList<>();
		try {
			districts = dao.getListOfDistrictByStates(state);
		       System.out.println("State 1 is present "+ districts.isEmpty());
			if (districts.isEmpty()) {
				throw new InvalidStateCodeException("Invalid state code. Please check your State Code");
			} else {
				System.out.println("Districts");
				districts.stream().forEach((district) ->{
					System.out.println(district.getDistrict());
				});
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidStateCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getDataBetweenDatesByState(Date startdate, Date enddate) {
		// TODO Auto-generated method stub
		List<Covid_Data> data = new ArrayList<>();
//		Covid_Data cd = new Covid_Data();
		try {
			boolean checkStart,checkEnd,finalCheck;
			data = dao.getListOfDataByDate(startdate, enddate);
			  checkStart=dao.CheckTheDatePresentOrNot(startdate);
			  checkEnd=dao.CheckTheDatePresentOrNot(enddate);
		        System.out.println("Start Date is present "+ checkStart);
		        System.out.println("Last Date is present :"+checkEnd);
		
		      
				if (checkStart) {
					if (checkEnd) {
						if (startdate.compareTo(enddate) < 0) {
							if (data != null) {
								finalCheck = true;
							} else {
								finalCheck = false;
								throw new NoDataFoundException();
							}
						} else {
							finalCheck = false;
							throw new InvalidDateRangeException();
						}
					} else {
						finalCheck = false;
						throw new InvalidDateException("Invalid End date, please check your input");
					}

				} else {
					finalCheck = false;
					throw new InvalidDateException("Invalid Start date, please check your input");
				}

			if (finalCheck) {
				System.out.println("Data between two dates");
				System.out.println("    date|State|Confirmed total|tested|recovered");

				String confirmed, tested;

				for (Covid_Data x : data) {
					confirmed = String.format("%15.3s", x.getConfirmed());
					tested = String.format("%6s", x.getTested());
					System.out.println(x.getDate() + "    |" + x.getState() + "   |" + confirmed + "|" + tested + "|"
							+ x.getRecovered());
				}
			}
		}

		catch (SQLException | InvalidDateException | InvalidDateRangeException | NoDataFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void compareDataBetweenStateByDate(Date startdate, Date enddate, String state1, String state2) throws NoStateFoundException {
		// TODO Auto-generated method stub
		List<Covid_Data> data = new ArrayList<>();
		Collection<Covid_Data> district1 = new ArrayList<>();
		Collection<Covid_Data> district2 = new ArrayList<>();
		try {
			boolean checkStart,checkEnd,finalCheck=false;
			data = dao.getListOfConfirmedCovidCasesBetweenTwoStates(startdate, enddate, state1, state2);
			district1=dao.getListOfDistrictByStates(state1);
			district2=dao.getListOfDistrictByStates(state2);
			  checkStart=dao.CheckTheDatePresentOrNot(startdate);
			  checkEnd=dao.CheckTheDatePresentOrNot(enddate);
		        System.out.println("Start is present "+checkStart);
		        System.out.println("Last is present :"+checkEnd);
		        System.out.println("State1 is present "+district1.isEmpty());
		        System.out.println("State2 is present :"+district2.isEmpty());
		
		        if(!(district1.isEmpty() && district2.isEmpty())) {
		        	if (checkStart) {
						if (checkEnd) {
							if (startdate.compareTo(enddate) < 0) {
								if (data != null) {
									finalCheck = true;
								} else {
									finalCheck = false;
									throw new NoDataFoundException();
								}
							} else {
								finalCheck = false;
								throw new InvalidDateRangeException();
							}
						} else {
							finalCheck = false;
							throw new InvalidDateException("Invalid End date, please check your input");
						}

					} else {
						finalCheck = false;
						throw new InvalidDateException("Invalid Start date, please check your input");
					}
		        } else {
		        	finalCheck=false;
		        	throw new InvalidStateCodeException("Invalid state code. please check your Input");
		        }
				

			if (finalCheck)
			{
				System.out.println("     date     |First State|Confirmed total|Second State|Confirmed total|");

				String state_1, confirmed1, tested;

		
			for (Covid_Data y : data) {
					confirmed1 = String.format("%8s", y.getConfirmed());
					tested = String.format("%6s", y.getTested());
					state_1 = String.format("%5s", y.getState());
					System.out.println(y.getDate() + "    |      " + y.getState() + "   |" + confirmed1 + "       |"
							+ tested + "      |" + y.getRecovered());
				}
			}

		} catch (SQLException | InvalidDateException | InvalidDateRangeException | NoDataFoundException | InvalidStateCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
