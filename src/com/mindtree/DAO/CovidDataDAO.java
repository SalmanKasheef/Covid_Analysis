package com.mindtree.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.mindtree.Exception.InvalidDateException;
import com.mindtree.Exception.InvalidDateRangeException;
import com.mindtree.Exception.InvalidStateCodeException;
import com.mindtree.Exception.NoDataFoundException;
import com.mindtree.Exception.NoStateFoundException;
import com.mindtree.model.Covid_Data;

public interface CovidDataDAO {
	
	public List<Covid_Data> getAllData() throws SQLException;
	
	public List<Covid_Data> getListOfStates() throws SQLException;
	
	public List<Covid_Data> getListOfDistrictByStates(String State) throws SQLException,InvalidStateCodeException;
	
	public List<Covid_Data> getListOfDataByDate(Date StartDate,Date EndDate) throws SQLException,
			InvalidDateException,NoDataFoundException ,InvalidDateRangeException;
	
	public List<Covid_Data> getListOfConfirmedCovidCasesBetweenTwoStates(Date Date_1,Date Date_2,
			String State_1, String State_2)throws SQLException, InvalidDateException,InvalidDateRangeException,
						NoDataFoundException ,NoStateFoundException;
	
	public boolean CheckTheDatePresentOrNot(Date Date) throws SQLException;
}
