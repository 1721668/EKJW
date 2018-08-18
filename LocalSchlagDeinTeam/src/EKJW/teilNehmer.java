package EKJW;

import java.io.Serializable;

public class teilNehmer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vorrname, nachname, gender;
	private int alter;
	
	public teilNehmer () {}
	
	public teilNehmer (String Vorname, String Nachname){
		setVorname(Vorname);
		setNachname(Nachname);
	}
	
	public teilNehmer (String Vorname, String Nachname, int alter) {
		setVorname(Vorname);
		setNachname(Nachname);
		setAlter(alter);
	}
	
	public teilNehmer (String Vorname, String Nachname, int alter, String gender) {
		setVorname(Vorname);
		setNachname(Nachname);
		setAlter(alter);
		setGender(gender);
	}
	
	
	
	private void setVorname(String vorname) {
		this.vorrname = vorname;
	}
	public String getVorname() {
		return this.vorrname;
	}
	private void setNachname (String nachname) {
		this.nachname = nachname;
	}
	public String getNachname(){
		return this.nachname;
	}
	private String setGender (String gender) {
		return this.gender = gender;
	}
	public int getAlter() {
		return this.alter;
	}
	public void setAlter(int alter) {
		this.alter = alter;
	}
	public String getGender () {
		return this.gender;
	}
}
