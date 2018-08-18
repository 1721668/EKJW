package SchlagDeinTeam;

import java.util.ArrayList;

public interface bedienerInterface {
	/**
	 * Methode um den aktuellen Punktestand zu erhalten
	 * @return Punktestand des Spiels
	 */
	public int[] getPunktestand();
	/**
	 * Methode um das ergebnis des aktuellen Spiels einzutragen
	 * @param team1 Ergebnis Team1
	 * @param team2 Ergebnis Team2
	 * @throws SdTException 
	 */
	public void setErgebnisMiniSpiel (int team1, int team2) throws SdTException;
	/**
	 * Methode um ein Spiel zu laden
	 * @param ort Voller Speicherort des Spiels
	 * @return Spiel, welches geladen wurde
	 * @throws SdTException Fehlermeldung, wenn die Eingabe ungültig war und kein Spiel gefunden wurde
	 */
	public Spiel laden(String ort) throws SdTException;
	/**
	 * speichert ein Spiel
	 * @param ort Speicherort inklusive des Dateinamens
	 * @throws SdTException Fehlermeldung, wenn Parameter ungültig oder Speichern nicht funktioniert
	 */
	public void speichern(String ort) throws SdTException;
	/**
	 * Abfrage der aktuellen Spielrunde
	 * @return Zahl der aktuellen Spielrunde
	 */
	public int getRunde();
	/**
	 * Trägt den neuen Punktestand ein
	 * @param ergebnis Gesamtpunkte des bisherigen Spiels
	 */
	public void setErgebnis(int[] ergebnis);
	/**
	 * Abfrage der gesamten Spieleanzahl
	 * @return Spielenzahl des aktuellen Spiels
	 */
	int anzSpiele();
	/**
	 * Lädt ein Spiel aus einer CSV Datei
	 * @param fullName Voller Speicherort des Spiels
	 * @return Zeichenkette mit den geladenen Daten
	 * @throws SdTException Fehlermeldung bei ungültigen Parametern
	 */
	String ladenCSV(String fullName) throws SdTException;
	/**
	 * speichert ein Spiel in Form einer CSV-datei
	 * @param fullName voller Speicherort des Spiels
	 * @param tn Teilnehmer, die gespeichert werden sollen
	 * @throws SdTException Fehlermeldung bei ungültigen Parametern
	 */
	void speichernCSV(String fullName, ArrayList<String> tn) throws SdTException;
	/**
	 * Fügt ein neues Spiel hinzu
	 * @param einzelSpiel MiniSpiel, welches hinzugefügt werden soll
	 */
	public void addSpiel(MiniSpiel einzelSpiel);
	/**
	 * Abfragen der bisher gespeicherten MiniSpiele
	 * @return Liste mit allen Spielen
	 */
	public ArrayList<MiniSpiel> getSpiele();
	
	/**
	 * aktualisieren der Spieleliste
	 * @param spieleListe neue Spieleliste
	 */
	public void setSpiele(ArrayList<MiniSpiel> spieleListe);
}
