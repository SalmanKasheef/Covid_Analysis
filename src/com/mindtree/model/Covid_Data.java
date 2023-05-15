package com.mindtree.model;
import java.util.Date;

public class Covid_Data implements Comparable<Object> {
	
	private int id;
	
	private Date Date;
	
	private String State;
	
	private String District;
	
	private String Tested;
	
	private String Confirmed;
	
	private String Recovered;

	public Covid_Data() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Covid_Data(String State) {
		setState(State);
	}
	
	public Covid_Data(int id, java.util.Date date, String state, String district, String tested, String confirmed,
			String recovered) {
		super();
		this.id = id;
		Date = date;
		State = state;
		District = district;
		Tested = tested;
		Confirmed = confirmed;
		Recovered = recovered;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getTested() {
		return Tested;
	}

	public void setTested(String tested) {
		Tested = tested;
	}

	public String getConfirmed() {
		return Confirmed;
	}

	public void setConfirmed(String confirmed) {
		Confirmed = confirmed;
	}

	public String getRecovered() {
		return Recovered;
	}

	public void setRecovered(String recovered) {
		Recovered = recovered;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", Date=" + Date + ", State=" + State + ", District=" + District + ", Tested="
				+ Tested + ", Confirmed=" + Confirmed + ", Recovered=" + Recovered + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Confirmed == null) ? 0 : Confirmed.hashCode());
		result = prime * result + ((Date == null) ? 0 : Date.hashCode());
		result = prime * result + ((District == null) ? 0 : District.hashCode());
		result = prime * result + ((Recovered == null) ? 0 : Recovered.hashCode());
		result = prime * result + ((State == null) ? 0 : State.hashCode());
		result = prime * result + ((Tested == null) ? 0 : Tested.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Covid_Data other = (Covid_Data) obj;
		if (Confirmed == null) {
			if (other.Confirmed != null)
				return false;
		} else if (!Confirmed.equals(other.Confirmed))
			return false;
		if (Date == null) {
			if (other.Date != null)
				return false;
		} else if (!Date.equals(other.Date))
			return false;
		if (District == null) {
			if (other.District != null)
				return false;
		} else if (!District.equals(other.District))
			return false;
		if (Recovered == null) {
			if (other.Recovered != null)
				return false;
		} else if (!Recovered.equals(other.Recovered))
			return false;
		if (State == null) {
			if (other.State != null)
				return false;
		} else if (!State.equals(other.State))
			return false;
		if (Tested == null) {
			if (other.Tested != null)
				return false;
		} else if (!Tested.equals(other.Tested))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
