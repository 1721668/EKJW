package SchlagDeinTeam.GUI;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import EKJW.teilNehmer;


public class spielerEintragen {
	JFrame frm;
	JPanel pnl;
	JFileChooser chooser;
	ArrayList <teilNehmer> tn;
	
	/**
	 * Parameterloser Konstruktor
	 */
	public spielerEintragen() {
		initialize();
	}

	/**
	 * Initialisiert das Fenster
	 */
	private void initialize() {
		frm = new JFrame("Spieler Eintragen");
		pnl = new JPanel();
		int start = new JOptionPane().showConfirmDialog(null, "Möchten Sie eine Teilnehmerliste laden?", "Teilnehmer laden?", JOptionPane.YES_NO_CANCEL_OPTION);
		switch (start) {
		case 0:{
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setFileFilter(new FileNameExtensionFilter("Teilnehmer-Liste", "tn"));
			if (chooser.showOpenDialog(frm) == JFileChooser.APPROVE_OPTION) {
				String teilnehmer = chooser.getSelectedFile().getAbsolutePath();
			}
			break;
		}case 1:{
			break;
		}default:
			System.exit(0);
		}
	}
}
