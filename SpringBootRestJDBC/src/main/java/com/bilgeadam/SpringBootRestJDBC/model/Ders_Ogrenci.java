package com.bilgeadam.SpringBootRestJDBC.model;

public class Ders_Ogrenci {

	private long ID;
	private long DERS_ID;
	private long OGR_ID;
	private long DEVAMSIZLIK;
	private long NOT;
	
	

	public Ders_Ogrenci() {
		
	}

	public Ders_Ogrenci(long iD, long dERS_ID, long oGR_ID, long dEVAMSIZLIK, long nOT) {

		ID = iD;
		DERS_ID = dERS_ID;
		OGR_ID = oGR_ID;
		DEVAMSIZLIK = dEVAMSIZLIK;
		NOT = nOT;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getDERS_ID() {
		return DERS_ID;
	}

	public void setDERS_ID(long dERS_ID) {
		DERS_ID = dERS_ID;
	}

	public long getOGR_ID() {
		return OGR_ID;
	}

	public void setOGR_ID(long oGR_ID) {
		OGR_ID = oGR_ID;
	}

	public long getDEVAMSIZLIK() {
		return DEVAMSIZLIK;
	}

	public void setDEVAMSIZLIK(long dEVAMSIZLIK) {
		DEVAMSIZLIK = dEVAMSIZLIK;
	}

	public long getNOT() {
		return NOT;
	}

	public void setNOT(long nOT) {
		NOT = nOT;
	}

	@Override
	public String toString() {
		return "Ders_Ogrenci [ID=" + ID + ", DERS_ID=" + DERS_ID + ", OGR_ID=" + OGR_ID + ", DEVAMSIZLIK=" + DEVAMSIZLIK
				+ ", NOT=" + NOT + "]";
	}

}
