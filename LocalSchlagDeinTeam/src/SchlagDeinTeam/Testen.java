package SchlagDeinTeam;

import SchlagDeinTeam.GUI.SpieleSortieren;
import SchlagDeinTeam.GUI.WindowSpielleiter;
import SchlagDeinTeam.GUI.spielEingabe;

public class Testen {

	public static void main(String[] args) throws SdTException {
//		persistenzSerialisiert temp = new persistenzSerialisiert();
//		try {
//			MiniSpiel temp1 = new EinzelSpiel ("Spiel05", "beschreibung");
//			MiniSpiel temp2 = new GruppenSpiel ("Spiel02", "beschreibung");
//			MiniSpiel temp3 = new EinzelSpiel ("Spiel03", "beschreibung");
//			temp.speichern("Test1", temp2);
//			MiniSpiel temp4 = (MiniSpiel) temp.laden("Test1");
//			System.out.println(temp4.toString());
//			
//
//		} catch (SdRException e) {
//			e.printStackTrace();
//		}
//		
//		
		bedienerInterface bi = new Spiel();
		bi.addSpiel(new EinzelSpiel("SpielA","Beschreibung A"));
		bi.addSpiel(new GruppenSpiel("SpielB","Beschreibung B"));
		bi.addSpiel(new EinzelSpiel("SpielC","Beschreibung C"));
		bi.addSpiel(new EinzelSpiel("SpielD","Beschreibung D"));
		new SpieleSortieren(bi,null);
	}

}
