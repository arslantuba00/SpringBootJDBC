package com.bilgeadam.SpringBootRestJDBC.model;

public class Ogrenci {

	private long ID;
	private String NAME; 
	private long NUMBER;
	private long YEAR;
	

	public Ogrenci() {
		
	}

	public Ogrenci(long iD, String nAME, long nUMBER, long yEAR) {

		ID = iD;
		NAME = nAME;
		NUMBER = nUMBER;
		YEAR = yEAR;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public long getNUMBER() {
		return NUMBER;
	}

	public void setNUMBER(long nUMBER) {
		NUMBER = nUMBER;
	}

	public long getYEAR() {
		return YEAR;
	}

	public void setYEAR(long yEAR) {
		YEAR = yEAR;
	}

	@Override
	public String toString() {
		return "Ogrenci [ID=" + ID + ", NAME=" + NAME + ", NUMBER=" + NUMBER + ", YEAR=" + YEAR + "]";
	}

}
