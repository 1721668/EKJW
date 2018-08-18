package SchlagDeinTeam;

import java.io.Serializable;

public class GruppenSpiel extends MiniSpiel implements Serializable{
	private String beschreibung;
	private int spielNummer;
	
	/**
	 * Konstruktor mit Titel-Parameter
	 * @param name Titel des Spiels
	 * @throws SdTException Fehlermeldung bei falscher Parametereingabe (name = null oder länge <3)
	 */
	private GruppenSpiel(String name) throws SdTException {
		super(name);
		this.spielNummer = super.getAnzahlSpiele();
	}
	/**
	 * Konstruktor zum Erstellen eines neuen Einzelspiels
	 * @param name Titel des Spiels
	 * @param beschreibung Beschreibung des Spiels
	 * @throws SdTException Fehlermeldung wenn Beschreibung leer oder zu kurz (<3)
	 */
	public GruppenSpiel (String name, String beschreibung) throws SdTException {
		this(name);
		if (beschreibung != null && beschreibung.length()>3)
			this.setBeschreibung(beschreibung);
		else
			throw new SdTException("Beschreibung ungültig");
	}
	
	/**
	 * Methode zum Abfragen der Beschreibung
	 * @return Zeichenkette mit der Beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	
	/**
	 * Methode um die Beschreibung zu ändern
	 * @param beschreibung neue Beschreibung
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * Methode um das Spiel als Zeichenkette auszugeben
	 */
	@Override
	public String toString () {
		return this.getTitel() + "\nSpielnummer: " + this.spielNummer + "\n------------\n" + this.getBeschreibung();
	}
}		
		
