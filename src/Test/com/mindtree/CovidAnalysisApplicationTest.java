package Test.com.mindtree;

import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mindtree.DAO.CovidDataDAOImpl;
import com.mindtree.Exception.InvalidStateCodeException;
import com.mindtree.Exception.NoStateFoundException;
import com.mindtree.model.Covid_Data;


class CovidAnalysisApplicationTest {

	CovidDataDAOImpl dao;

	public CovidAnalysisApplicationTest() {
		dao = new CovidDataDAOImpl();
	}

	@Test
	void testFuncionalityNameValidData() throws SQLException, InvalidStateCodeException, NoStateFoundException {
		String state = "TN"; // valid data
		List<Covid_Data> data = new ArrayList<>();
		data = dao.getListOfStates();
		boolean res = false;
		for (Covid_Data x : data) {
			if (x.getState().contains(state)) {
				res = true;
				break;
			} else {
				res = false;

			}
		}
		assertTrue(res);
		
		if (res == false) {
			// throw new StateNotFound("Checked state is not found");
			throw new InvalidStateCodeException("Invalid StateCode we cant find ");
		}
		

	}

	@Test
	void testFunctionalityNameNoRecords() throws SQLException, InvalidStateCodeException {
		String State = "TNS"; // invalid data(state)

		List<Covid_Data> data2 = new ArrayList<>();
		data2 = dao.getListOfDistrictByStates(State);

		boolean result = data2.isEmpty();

		if (result) {
			throw new InvalidStateCodeException("Invalid StateCode we cant find districts ");
		}
	

	}

}
