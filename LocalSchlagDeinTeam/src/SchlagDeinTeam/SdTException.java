package SchlagDeinTeam;

import javax.swing.JDialog;

public class SdTException extends Exception {
	/**
	 * Fehlermeldung bei schlagDeinTeam
	 * @param name Bezeichnung der Fehlermeldung
	 */
	public SdTException (String name){
		System.err.println(name);
	}
}
