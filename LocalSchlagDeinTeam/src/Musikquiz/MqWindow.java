package Musikquiz;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import EKJW.teilNehmer;
import EKJW.teilnehmerHinzufügen;
import SchlagDeinTeam.SdTException;
import SchlagDeinTeam.persistenzSerialisiert;

public class MqWindow extends JFrame{
	private ArrayList<teilNehmer> teilnehmer = new ArrayList<teilNehmer>();
	
	public MqWindow() {
		int startOption = JOptionPane.showConfirmDialog(null, "Teilnehmerliste laden?","Startoption",JOptionPane.YES_NO_OPTION);
		switch (startOption){
		case 0:{
			teilnehmerLaden();
			break;
		}default:{
			new teilnehmerHinzufügen();
		}
		}
	}

	@SuppressWarnings("unchecked")
	private void teilnehmerLaden() {
		JFileChooser laden = new JFileChooser();
		laden.setFileSelectionMode(JFileChooser.FILES_ONLY);
		laden.setFileFilter(new FileNameExtensionFilter ("Teilnehmerliste","tn"));
		if (laden.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				persistenzSerialisiert temp = new persistenzSerialisiert();
				this.teilnehmer = (ArrayList<teilNehmer>) temp.laden(laden.getSelectedFile().getAbsolutePath());
			}catch (SdTException e) {
				JOptionPane.showMessageDialog(null, "Laden konnte nicht durchgeführt werden", "Ladefehler", JOptionPane.ERROR_MESSAGE);
			}
		}else
			JOptionPane.showMessageDialog(null, "keine Datei ausgewählt", "Ladefehler", JOptionPane.ERROR_MESSAGE);
	}
}
