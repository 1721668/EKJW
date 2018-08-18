package SchlagDeinTeam;

import java.io.Serializable;
import java.util.ArrayList;

public class Spiel implements Serializable, bedienerInterface{
	private static Spiel spiel;
	private static int runde = 1;
	private ArrayList <MiniSpiel> spiele = new ArrayList <MiniSpiel>();
	private int[] punkteStand = {0,0};
	private MiniSpiel aktuellesSpiel;
	
	public Spiel () {}
	
	public void speichern(String ort) throws SdTException {
		persistenzSerialisiert speicher = new persistenzSerialisiert ();
		speicher.speichern(ort,  this);
	}
	
	public Spiel laden (String ort) throws SdTException {
		persistenzSerialisiert laden = new persistenzSerialisiert ();
		return (Spiel) laden.laden(ort);
	}

	@Override 
	public String ladenCSV (String fullName) throws SdTException {
		persistenzSerialisiert laden = new persistenzSerialisiert();
		return (String) laden.ladenCSV(fullName);
	}
	
	@Override
	public void speichernCSV (String fullName, ArrayList<String> tn) throws SdTException{
		persistenzSerialisiert speicher = new persistenzSerialisiert ();
		speicher.speichernCSV(fullName, tn);
	}
	
	@Override
	public int[] getPunktestand() {
		return punkteStand;
	}
	@Override
	public void setErgebnis (int[] ergebnis) {
		this.punkteStand=ergebnis;
	}
	
	@Override
	public void setErgebnisMiniSpiel(int team1, int team2) throws SdTException {
		aktuellesSpiel.setErgebnis(new int [] {team1,team2});
		setNächstesSpiel();
	}

	@Override
	public int getRunde() {
		return runde;
	}
	/**
	 * Legt atomatisch das nächste Spiel fest
	 * @throws SdTException
	 */
	private void setNächstesSpiel() throws SdTException {
		if (aktuellesSpiel.getErgebnis() == null) {
			throw new SdTException("Ergebnis noch nicht eingetragen");
		}
		aktuellesSpiel = spiele.get(runde++);
	}
	@Override
	public int anzSpiele () {
		return spiele.size();
	}
	@Override
	public void addSpiel(MiniSpiel spiel) {
		spiele.add(spiel);
	}
	
	@Override
	public ArrayList <MiniSpiel> getSpiele (){
		return spiele;
	}

	@Override
	public void setSpiele(ArrayList<MiniSpiel> spieleListe) {
		this.spiele = spieleListe;
		aktuellesSpiel = spiele.get(0);
	}
}
