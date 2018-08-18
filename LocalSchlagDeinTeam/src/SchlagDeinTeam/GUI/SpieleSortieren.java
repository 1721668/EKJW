package SchlagDeinTeam.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import SchlagDeinTeam.EinzelSpiel;
import SchlagDeinTeam.GruppenSpiel;
import SchlagDeinTeam.MiniSpiel;
import SchlagDeinTeam.SdTException;
import SchlagDeinTeam.bedienerInterface;

public class SpieleSortieren {
	JFrame frm;
	JPanel pn;
	JPanel spiele;
	bedienerInterface bi;
	GraphicsDevice[] anzeige;
	boolean zweiBildschirme = false;
	JTextField[] nummern;
	JTextArea[] titel;
	JTextArea[] beschreibung;
	JLabel[] logos;
	
	/**
	 * Konstruktor zum Sortieren der Spiele
	 * @param bi Interface, welches das Spiel steuert
	 * @param anzeige Graphikanzeigen zur Bildschirmauswahl
	 */
	public SpieleSortieren (bedienerInterface bi, GraphicsDevice[] anzeige) {
		this.bi = bi;
		if (anzeige != null)
			this.anzeige = anzeige;
		else
			this.anzeige = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		initialize();
	}

	/**
	 * Initialisiert das Fenster
	 */
	private void initialize() {
		frm = new JFrame (anzeige[0].getDefaultConfiguration());
		frm.setTitle("Spiele sortieren");
		frm.setVisible(true);
		frm.setResizable(false);
		frm.setSize((int)(anzeige[0].getDefaultConfiguration().getBounds().getWidth()),(int)(anzeige[0].getDefaultConfiguration().getBounds().getHeight()));
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SchlagDeinTeam/GUI/Icons/Logo_klein.png")));
		
		pn = new JPanel();
		pn.setLayout(null);
		pn.setBackground(Color.BLACK);
		pn.setVisible(true);
		pn.setSize(frm.getSize());
		
		spiele = new JPanel();
		spiele.setBounds((int)(frm.getWidth()*0.025),(int)(frm.getHeight()*0.3),(int)(frm.getWidth()*0.95),(int)(pn.getHeight()*0.675));
		spiele.setLayout(null);
		spiele.setBackground(new Color(50,50,50));
		spiele.setVisible(true);
		
		felderEinfügen();
		
		JButton speichern =new JButton("Speichern");
		speichern.setSize((int)(pn.getWidth()*0.1),(int)(pn.getHeight()*0.05));
		speichern.setBounds((int)(spiele.getBounds().getMaxX()-speichern.getWidth()),(int)(pn.getHeight()*0.2),(int)(speichern.getWidth()),(int)(speichern.getHeight()));
		speichern.setFont(new Font("Tahoma", Font.BOLD, (int)(speichern.getHeight()*0.5)));
		speichern.setBackground(Color.DARK_GRAY);
		speichern.setForeground(Color.WHITE);
		speichern.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				nummerierungÜberprüfen();
				frm.setVisible(false);
				new WindowSpielleiter (bi, anzeige);
				frm.dispose();
			}
			
		});
		
		pn.add(spiele);
		pn.add(speichern);
		
		frm.setContentPane(pn);
		frm.pack();
		frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * FÃ¼gt die Felder zum sortieren ein
	 */
	private void felderEinfügen() {
		Dimension nummerSize = new Dimension ((int)(spiele.getWidth()*0.05),(int)(spiele.getWidth()*0.05));
		Dimension textSize = new Dimension ((int)(spiele.getWidth()*0.4),(int)(spiele.getHeight()/bi.anzSpiele() *0.85));
		nummern = new JTextField [bi.anzSpiele()];
		titel = new JTextArea [bi.anzSpiele()];
		logos = new JLabel [bi.anzSpiele()];
		
		beschreibung = new JTextArea [bi.anzSpiele()];
		ArrayList <MiniSpiel> spielListe = bi.getSpiele();
		Color spielFarbe = null;
		
		Font f = new Font("Tahoma", Font.BOLD, (int)(nummerSize.getHeight()*0.7));
		
		for (int i = 0; i< bi.anzSpiele(); i++) {
			nummern[i] = new JTextField();
			titel[i] = new JTextArea();
			beschreibung[i] = new JTextArea();
			
			nummern[i].setBounds((int)(spiele.getWidth()*0.05),(int)(spiele.getHeight()*0.5+i*(textSize.getHeight()*10/9)),(int)(nummerSize.getWidth()),(int)(nummerSize.getHeight()));
			titel[i].setBounds((int)(nummern[i].getBounds().getMaxX()+nummerSize.getWidth()),(int)(nummern[i].getBounds().getMinY()),(int)(textSize.getWidth()),(int)(textSize.getHeight()));
			beschreibung[i].setBounds((int)(titel[i].getBounds().getMaxX()+nummerSize.getWidth()),(int)(nummern[i].getBounds().getMinY()),(int)(textSize.getWidth()),(int)(textSize.getHeight()));
			
			nummern[i].setFont(f);
			titel[i].setFont(f);
			beschreibung[i].setFont(f);
			
			nummern[i].setCaretColor(Color.WHITE);
			titel[i].setCaretColor(Color.WHITE);
			beschreibung[i].setCaretColor(Color.WHITE);
			
			logos[i].setIcon(new ImageIcon (new ImageIcon(SpieleSortieren.class.getResource("/SchlagDeinTeam/GUI/Bilder/Schlag_Dein_Team_Logo.png")).getImage().getScaledInstance((int)(titel[i].getBounds().getWidth()*1.5), (int)(titel[i].getBounds().getHeight()*1.5),Image.SCALE_DEFAULT)));
			logos[i].setBounds((int)(titel[i].getBounds().getMinX()-titel[i].getBounds().getWidth()*0.25),(int)(titel[i].getBounds().getMinY()-titel[i].getBounds().getHeight()*0.25),(int)(logos[i].getIcon().getIconWidth()),(int)(logos[i].getIcon().getIconHeight()));
			
			if(spielListe.get(i).getClass().equals(EinzelSpiel.class)) {
				EinzelSpiel temp = (EinzelSpiel)spielListe.get(i);
				titel[i].setText(temp.getTitel());
				beschreibung[i].setText(temp.getBeschreibung());
				spielFarbe = new Color (25,25,25);
			}
			else {
				GruppenSpiel temp = (GruppenSpiel)spielListe.get(i);
				titel[i].setText(temp.getTitel());
				beschreibung[i].setText(temp.getBeschreibung());
				spielFarbe = new Color (45,45,45);
			}
			nummern[i].setText("" + (i+1));
			
			nummern[i].setHorizontalAlignment(SwingConstants.CENTER);
			
			nummern[i].setBackground(spielFarbe);
			titel[i].setBackground(spielFarbe);
			beschreibung[i].setBackground(spielFarbe);
			nummern[i].setForeground(Color.WHITE);
			titel[i].setForeground(Color.WHITE);
			beschreibung[i].setForeground(Color.WHITE);
			pn.add(nummern[i]);
			pn.add(logos[i]);
			pn.add(titel[i]);
			pn.add(beschreibung[i]);
		}
	}

	/**
	 * ÃœberprÃ¼ft, ob auschlieÃŸlich gÃ¼ltige Zahlen eingegeben wurden
	 */
	private void nummerierungÜberprüfen() {
		int [] zahlenÜberprüfer = new int [nummern.length];
		int [] zahlen = new int [nummern.length];
		
		for (int i = 0; i< nummern.length; i++) {
			int zahl = 0;
			try {
				zahl = Integer.parseInt(nummern[i].getText());
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frm, nummern[i].getText() + " keine Zahl", "UngÃ¼ltige Eingabe", JOptionPane.ERROR_MESSAGE);
			}
			if (zahl <=0 || zahl > nummern.length) {
				JOptionPane.showMessageDialog(frm, nummern[i].getText() + " keine gÃ¼ltige Zahl (1 bis " + nummern.length + " erlaub)", "UngÃ¼ltige Eingabe", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if (zahlenÜberprüfer[zahl-1]!=0) {
				JOptionPane.showMessageDialog(frm, nummern[i].getText() + " bereits eingegeben", "UngÃ¼ltige Eingabe", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
				zahlenÜberprüfer[zahl-1] = zahl; //Zahlen eintragen, wo welches Spiel steht
				zahlen[zahl-1] = i;
			}
		}
		neuEintragen(zahlen);
	}

	/**
	 * aktualisiert die Spiele nach der gewÃ¼nschten Sortieren
	 * @param zahlen Sortierung der Spiele in einem Array
	 */
	private void neuEintragen(int[] zahlen) {
		ArrayList <MiniSpiel> spieleListe =new ArrayList<MiniSpiel>();
		for (int i = 0; i < nummern.length; i++) { //Spiele hochzahlen
			for (int t = 0; t < nummern.length; t++) { //gesuchtes Spiel mit SpieleListe vergleichen
				if (zahlen[t] == i) {
					MiniSpiel temp = spielAktualisieren (t);
					if(temp == null)
						return;
					spieleListe.add(temp);
				}
			}
		}
		bi.setSpiele(spieleListe);
	}

	/**
	 * Erstellt das neue Minispiel
	 * @param t speicherstelle des Spiels
	 * @return neues Minispiel mit ausgelesenen Feldern
	 */
	private MiniSpiel spielAktualisieren(int t) {
		if (nummern[t].getBackground().equals(new Color (225,25,25))) { //Farbe Einzelspiel
			EinzelSpiel temp = null;
			try {
				temp = new EinzelSpiel(titel[t].getText(), beschreibung[t].getText());
			} catch (SdTException e) {
				JOptionPane.showMessageDialog(frm, "falsche Parameter eingegeben", "UngÃ¼ltig", JOptionPane.ERROR_MESSAGE);
			}
			return temp;
		}
		GruppenSpiel temp = null;
		try {
			temp = new GruppenSpiel(titel[t].getText(), beschreibung[t].getText());
		} catch (SdTException e) {
			JOptionPane.showMessageDialog(frm, "falsche Parameter eingegeben", "UngÃ¼ltig", JOptionPane.ERROR_MESSAGE);
		}
		return temp;
	}
}
