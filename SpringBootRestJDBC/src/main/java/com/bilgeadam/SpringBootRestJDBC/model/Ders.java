package com.bilgeadam.SpringBootRestJDBC.model;

public class Ders {
	private long ID;

	private long OGR_ID;

	private long KONU_ID;

	public Ders() {

	}

	public Ders(long iD, long oGR_ID, long kONU_ID) {
		ID = iD;
		OGR_ID = oGR_ID;
		KONU_ID = kONU_ID;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getOGR_ID() {
		return OGR_ID;
	}

	public void setOGR_ID(long oGR_ID) {
		OGR_ID = oGR_ID;
	}

	public long getKONU_ID() {
		return KONU_ID;
	}

	public void setKONU_ID(long kONU_ID) {
		KONU_ID = kONU_ID;
	}

	@Override
	public String toString() {
		return "Ders [ID=" + ID + ", OGR_ID=" + OGR_ID + ", KONU_ID=" + KONU_ID + "]";
	}

}
