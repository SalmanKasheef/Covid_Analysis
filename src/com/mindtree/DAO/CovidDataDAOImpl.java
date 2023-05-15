package com.mindtree.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.Exception.InvalidDateException;
import com.mindtree.Exception.InvalidDateRangeException;
import com.mindtree.Exception.InvalidStateCodeException;
import com.mindtree.Exception.NoDataFoundException;
import com.mindtree.Exception.NoStateFoundException;
import com.mindtree.model.Covid_Data;

public class CovidDataDAOImpl implements CovidDataDAO{

	String url="jdbc:mysql://localhost:3306/covid_analysis";
	String UserName="root";
	String Password="Welcome123$";
	
	public static List<Covid_Data> ListOfAllData=new ArrayList<>();
	
	private static final String SELECT_ALL_DATA="select * from covid_analysis.covid_data";
	
	private static final String SELECT_ALL_STATES="select distinct State from covid_analysis.covid_data"
			+ "order by State";
	
	private static final String SELECT_ALL_DISTRICT_BY_STATE="select distinct District from covid_analysis.covid_data"
			+ "where State=?";
	
	private static final String SELECT_COVID_DATA_BETWEEN_TWO_DATE
						="select * from covid_analysis.covid_data where Date between ? and ? group by State ,Date";
	
	private static final String SELECT_CONFIRMED_CASES_BETWEEN_TWO_STATES
					="Select a.Date,a.State as state1, a.Confirmed as state1_confirmed, b.State as state2, b.Confirmed as state2_confirmed\r\n"
							+ "from covid_analysis.covid_data A left join covid_analysis.covid_data B on a.Date = b.Date \r\n"
							+ "where  a.Date between ? and ? and a.State=? and b.State=? group by a.Date,a.State;";
	
	private static final String CHECK_THE_DATE_PRESENT_OR_NOT=
					"select distinct Date from covid_analysis.covid_data where Date=?";
	
	//Connecting to Sequel Server
	protected Connection getConnection() {
		Connection Con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con=DriverManager.getConnection(url,UserName,Password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Con;
	}
	
	
	@Override
	public List<Covid_Data> getAllData() throws SQLException {
		Connection Con = getConnection();
		PreparedStatement ps =Con.prepareStatement(SELECT_ALL_DATA);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next() ) 
		{
			Covid_Data data = new Covid_Data();
			
			data.setDate(rs.getDate("Date"));
			data.setState(rs.getString("State"));
			data.setDistrict(rs.getString("District"));
			data.setTested(rs.getString("Tested"));
			data.setConfirmed(rs.getString("Confirmed"));
			data.setRecovered(rs.getString("Recovered"));
			data.setId(rs.getInt("id"));
		
			ListOfAllData.add(data);
		}
		return ListOfAllData;
	}

	@Override
	public List<Covid_Data> getListOfStates() throws SQLException {
		List<Covid_Data> listOfState=new ArrayList<>();
		Connection Con=getConnection();
		PreparedStatement ps=Con.prepareStatement(SELECT_ALL_STATES);
		
		ResultSet Rs=ps.executeQuery();
		while(Rs.next()) {
			Covid_Data Cd=new Covid_Data();
			Cd.setState(Rs.getString("State"));
		}
		return listOfState;
		
	}

	@Override
	public List<Covid_Data> getListOfDistrictByStates(String State) throws SQLException, InvalidStateCodeException {
		List<Covid_Data> districtList=new ArrayList<>();
		Connection Con=getConnection();
		PreparedStatement Ps=Con.prepareStatement(SELECT_ALL_DISTRICT_BY_STATE);
		Ps.setString(1,"State");
		System.out.println(Ps);
		
		ResultSet Rs=Ps.executeQuery();
		while(Rs.next()) {
			Covid_Data Cd=new Covid_Data();
			Cd.setDistrict(Rs.getString("District"));
			districtList.add(Cd);
		}
		return districtList;
	}

	@Override
	public List<Covid_Data> getListOfDataByDate(Date StartDate, Date EndDate)
			throws SQLException, InvalidDateException, NoDataFoundException, InvalidDateRangeException {
		
		List<Covid_Data> listOfData = new ArrayList<>();
		Connection Con = getConnection();
		PreparedStatement ps = Con.prepareStatement(SELECT_COVID_DATA_BETWEEN_TWO_DATE);
		ps.setDate(1,  StartDate);
		ps.setDate(2, EndDate);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next() ) 
		{
			Covid_Data data = new Covid_Data();
			
			data.setDate(rs.getDate("Date"));
			data.setState(rs.getString("State"));
			data.setConfirmed(rs.getString("Confirmed"));
			data.setTested(rs.getString("Tested"));
			data.setRecovered(rs.getString("Recovered"));
		
			listOfData.add(data);
		}
		return listOfData;
	}

	@Override
	public List<Covid_Data> getListOfConfirmedCovidCasesBetweenTwoStates(Date Date_1, Date Date_2, String State_1,
			String State_2) throws SQLException, InvalidDateException, InvalidDateRangeException, NoDataFoundException,
			NoStateFoundException {
		List<Covid_Data> listofdata = new ArrayList<>();
		Connection Con = getConnection();
		PreparedStatement ps =Con.prepareStatement(SELECT_CONFIRMED_CASES_BETWEEN_TWO_STATES);
		ps.setDate(1, Date_1);
		ps.setDate(2, Date_2);
		ps.setString(3, State_1);
		ps.setString(4, State_2);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next() ) 
		{
			Covid_Data data = new Covid_Data();
			
			data.setDate(rs.getDate("a.Date"));
			data.setState(rs.getString("State1"));
			data.setConfirmed(rs.getString("state1_confirmed"));
			data.setTested(rs.getString("State2"));
			data.setRecovered(rs.getString("state2_confirmed"));
		
			listofdata.add(data);
		}
		return listofdata;
	}

	@Override
	public boolean CheckTheDatePresentOrNot(Date Date) throws SQLException {
		boolean res = false;
		Connection Con = getConnection();
		PreparedStatement ps = Con.prepareStatement(CHECK_THE_DATE_PRESENT_OR_NOT);
		ps.setDate(1, Date);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			res = true;
		}
		return res;
	}

}
