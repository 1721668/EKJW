package SchlagDeinTeam;

import java.io.Serializable;

import SchlagDeinTeam.SdTException;

@SuppressWarnings("serial")
public abstract class MiniSpiel implements Serializable {
	private static int anzSpiele = 0;
	private String titel;
	private int[] ergebnis;
	 /**
	  * Konstruktor eines neuen MiniSpiels
	  * @param name Titel des neu zu erstellenden Spiels
	  * @throws SdTException Fehlermeldung, wenn Parameter ungültig (null oder länge < 3)
	  */
	public MiniSpiel (String name) throws SdTException {
		if (name != null && name.length() >= 3)
		 this.setName(name);
		else
			throw new SdTException ("Falsche Namenseingabe");
		incAnzahlSpiele();
	}
	
	/**
	 * Erhäht die Klassenvariable anzSpiele um +1
	 */
	private void incAnzahlSpiele() {
		anzSpiele++;
	}
	
	/**
	 * Abfrage der Anzahl aller erstellten Spiele
	 * @return Anzahl bisher erstellter Spiele
	 */
	@SuppressWarnings("static-access")
	protected int getAnzahlSpiele() {
		return this.anzSpiele;
	}
	
	/**
	 * Abfrage des Spiel-Titels
	 * @return Titel des Spiels
	 */
	public String getTitel() {
		return titel;
	}
	
	/**
	 * Methode um den Titel eines spiels zu setzen
	 * @param name neuer Titel des Spiels
	 */
	private void setName(String name) {
		this.titel = name;
	}
	
	/**
	 * Setzt das Ergebnis eines MiniSpiels
	 * @param ergebnis einzutragendes Ergebnis
	 */
	public void setErgebnis(int[] ergebnis) {
		this.ergebnis = ergebnis;
	}
	
	/**
	 * Abfrage des Ergebnisses eines Spiels
	 * @return Ergebnis des Spiels
	 */
	public int[] getErgebnis() {
		return ergebnis;
	}
	
}
