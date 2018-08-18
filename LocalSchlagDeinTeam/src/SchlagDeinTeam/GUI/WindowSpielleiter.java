package SchlagDeinTeam.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import SchlagDeinTeam.SdTException;
import SchlagDeinTeam.bedienerInterface;




public class WindowSpielleiter extends Thread{
	static JFrame frm;
	Dimension size;
	Dimension iconSize;
	Dimension buttonSize;
	GraphicsDevice[] gd = null;
	GraphicsDevice aktuellerBildschirm = null;
	
	JMenu mnOptionen; 
	JMenuItem mntmSpeichern;
	JMenuItem mntmLaden;
	JMenuItem mntmBeenden;
	JMenu mnDesign;
	JMenuItem mntmBildschirmeTauschen;
	JMenuItem mntmMaximieren;
	JMenuItem mntmMinimieren;
	
	JPanel spielSteuerung = new JPanel();
	JPanel punkteAnzeige;
	
	JLabel punkteStand1;
	JLabel punkteStand2;
	
	JFileChooser chooser;
	
	JToolBar tb = new JToolBar();

	bedienerInterface bi;
	ArrayList <JButton> punkteListe = new ArrayList<JButton>();
	Font schrift = new Font ("Areal", Font.PLAIN, 25);
	
	/**
	 * Erstellt das Spielleiterfenster (Konstruktor)
	 * @param bi Bedienerinterface des Spiels
	 * @param bildschirme Bildschirme, die angeschlossen sind
	 */
	public WindowSpielleiter (bedienerInterface bi, GraphicsDevice[] bildschirme) {
		if (bildschirme!= null) {
			this.aktuellerBildschirm = bildschirme[0];
			this.gd = bildschirme;
		}
		this.bi = bi;
		initialize();
		frm.setVisible(true);
		frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		start();
	}

	/**
	 * Initialisiert das Fenster
	 */
	private void initialize() {
		
		size = aktuellerBildschirm.getDefaultConfiguration().getBounds().getSize();
		iconSize = new Dimension((int)(size.getHeight()/40), (int)(size.getHeight()/40));
		frm = new JFrame(aktuellerBildschirm.getDefaultConfiguration());
		frm.setUndecorated(true);
		
		frm.setResizable(false);
		frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		aktuellerBildschirm.setFullScreenWindow(frm);
		frm.getContentPane().setLayout(new GridLayout(1,0,0,0));
		spielSteuerung.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		spielSteuerung.setVisible(true);
		punkteAnzeige = new JPanel();
		punkteAnzeige.setBounds((int)(size.getWidth()*0.1),(int)(size.getHeight()*0.2),(int) (size.getWidth()*0.8),(int)(size.getHeight()*0.6));
		punkteAnzeige.setLayout(null);
		punkteAnzeige.setBackground(Color.LIGHT_GRAY);
		punkteAnzeige.setVisible(true);
		
		spielSteuerung.setLayout(null);
		frm.pack();
		frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frm.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frm.setJMenuBar(menuBar);

		mnOptionen = new JMenu("Optionen");
		mnOptionen.setFont(schrift);
		mnOptionen.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/optionen.png")).getImage().getScaledInstance((int)iconSize.getWidth(), (int)iconSize.getHeight(), Image.SCALE_DEFAULT)));
		menuBar.add(mnOptionen);
		
		mntmSpeichern = new JMenuItem("Speichern");
		mntmSpeichern.setFont(schrift);
		mntmSpeichern.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/speichern.png")).getImage().getScaledInstance((int)iconSize.getWidth(), (int)iconSize.getHeight(), Image.SCALE_DEFAULT)));
		mntmSpeichern.setSelectedIcon(null);
		mntmSpeichern.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser ("Speicherdatei auswählen");
				chooser.setDialogTitle("Gültigen Dateinamen eingeben");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setFileFilter(new FileNameExtensionFilter ("SchlagDeinTeam Datei", "sdt"));
				if (chooser.showOpenDialog(mntmSpeichern) == JFileChooser.APPROVE_OPTION) {
					try {
						bi.speichern(chooser.getSelectedFile().getAbsolutePath());
					}catch (SdTException sdt) {
						JOptionPane.showMessageDialog(null, sdt.getMessage(), "Speicherfehler", JOptionPane.ERROR_MESSAGE);
					}
				}else
					JOptionPane.showMessageDialog(null, "Keinen Gültigen Dateinamen ausgewählt", "Speicherfehler", JOptionPane.ERROR_MESSAGE);
			}
			
		});
		mnOptionen.add(mntmSpeichern);
		
		mntmLaden = new JMenuItem("Laden");
		mntmLaden.setFont(schrift);
		mntmLaden.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/Laden_neu.png")).getImage().getScaledInstance((int)iconSize.getWidth(), (int)iconSize.getHeight(), Image.SCALE_DEFAULT)));
		mntmLaden.setSelectedIcon(null);
		mntmLaden.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser ("Speicherdatei auswählen");
				chooser.setDialogTitle("zu öffnende Datei auswählen");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooser.setFileFilter(new FileNameExtensionFilter ("SchlagDeinTeam Datei", "sdt"));
				if (chooser.showOpenDialog(mntmLaden) == JFileChooser.APPROVE_OPTION) {
					try {
						bi.speichern(chooser.getSelectedFile().getAbsolutePath());
					}catch (SdTException sdt) {
						JOptionPane.showMessageDialog(null, sdt.getMessage(), "Ladefehler", JOptionPane.ERROR_MESSAGE);
					}
				}else
					JOptionPane.showMessageDialog(null, "Keine Gültige Datei ausgewählt", "Ladefehler", JOptionPane.ERROR_MESSAGE);
			}
		});
		mnOptionen.add(mntmLaden);
		mnOptionen.add(new JSeparator());
		
		mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setFont(schrift);
		mntmBeenden.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/Beenden.png")).getImage().getScaledInstance((int)iconSize.getWidth(), (int)iconSize.getHeight(), Image.SCALE_DEFAULT)));
		mntmBeenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int Auswahl = JOptionPane.showConfirmDialog(frm, "Spiel vorher speichern?", "Programm beenden Optionen", JOptionPane.YES_NO_CANCEL_OPTION);
				switch (Auswahl) {
				case 0:		mntmSpeichern.doClick();
				case 1:	{
					System.exit(0);
					break;
				}
				default:{}
				}
			}
			
		});
		mnOptionen.add(mntmBeenden);
		mnOptionen.add(new JSeparator());
		
		mnDesign = new JMenu("Design");
		mnDesign.setFont(schrift);
		mnDesign.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/design.png")).getImage().getScaledInstance((int)iconSize.getWidth(), (int)iconSize.getHeight(), Image.SCALE_DEFAULT)));
		mnOptionen.add(mnDesign);
		
		mntmBildschirmeTauschen = new JMenuItem("Bildschirme Tauschen");
		mntmBildschirmeTauschen.setFont(schrift);
		mntmBildschirmeTauschen.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/tauschen.png")).getImage().getScaledInstance((int)iconSize.getWidth(), (int)iconSize.getHeight(), Image.SCALE_DEFAULT)));
		mntmBildschirmeTauschen.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				gd = ge.getScreenDevices();
				if (gd.length == 2) {
					new WindowSpielleiter(bi,new GraphicsDevice[]{gd[1],gd[0]});
					frm.setVisible(false);
					frm.removeAll();
					frm = null;
				}else if (gd.length == 1) {
					return;
				}
				else
					JOptionPane.showMessageDialog(frm, "0 oder mehr als 2 Anzeigen angeschlossen", "Ungltige Bildschirmzahl", JOptionPane.CLOSED_OPTION);
			}
		});
		mnDesign.add(mntmBildschirmeTauschen);
		
		mntmMinimieren = new JMenuItem("Minimieren");
		mntmMinimieren.setFont(schrift);
		mntmMinimieren.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/minimieren.png")).getImage().getScaledInstance((int)iconSize.getWidth(), (int)iconSize.getHeight(), Image.SCALE_DEFAULT)));
		mntmMinimieren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frm.setState(JFrame.ICONIFIED);
			}
			
		});
		
		punkteStand1 = new JLabel();
		punkteStand1.setHorizontalAlignment(SwingConstants.CENTER);
		punkteStand1.setForeground(Color.YELLOW);

		punkteStand1.setBackground(Color.DARK_GRAY);
		punkteStand1.setBounds((int)(punkteAnzeige.getSize().getWidth()*0.2), (int)(punkteAnzeige.getSize().getHeight()*0.2), (int)(punkteAnzeige.getSize().getWidth()*0.2), (int)(punkteAnzeige.getSize().getHeight()*0.25));
		Font punkte = new Font("Tahoma", Font.BOLD, (int)(punkteStand1.getSize().getHeight()*0.9));
		
		punkteStand1.setFont(punkte);
		punkteStand1.setText("" + bi.getPunktestand()[0]);
		punkteStand1.setBorder(new LineBorder(Color.WHITE, 5, true));
		punkteStand1.setVisible(true);
		
		punkteStand2 = new JLabel ();
		punkteStand2.setHorizontalAlignment(SwingConstants.CENTER);
		punkteStand2.setForeground(Color.YELLOW);
		punkteStand2.setFont(punkte);
		punkteStand2.setBackground(Color.DARK_GRAY);
		punkteStand2.setBounds((int)(punkteAnzeige.getSize().getWidth()*0.55), (int)(punkteAnzeige.getSize().getHeight()*0.2), (int)(punkteAnzeige.getSize().getWidth()*0.2), (int)(punkteAnzeige.getSize().getHeight()*0.25));
		punkteStand2.setText("" + bi.getPunktestand()[1]);
		punkteStand2.setBorder(new LineBorder(Color.WHITE, 5, true));
		punkteStand2.setVisible(true);
		
		punkteEintragen();
		
		punkteAnzeige.add(punkteStand1);
		punkteAnzeige.add(punkteStand2);
		
		spielSteuerung.add(punkteAnzeige, BorderLayout.CENTER);
		frm.getContentPane().add(spielSteuerung);
		mnDesign.add(mntmMinimieren);
		
	}

	/**
	 * Trägt die Punkte in der Anzeige ein
	 */
	private void punkteEintragen() {
		double feldLinks = punkteStand1.getBounds().getMinX();
		double feldLinksUnten = punkteStand1.getBounds().getMaxY()*1.02;
		double feldRechts = punkteStand2.getBounds().getMinX();
		double feldRechtsUnten = punkteStand2.getBounds().getMaxY()*1.02;
		int pixelButton = (int)(punkteStand1.getSize().getWidth()*0.18);
		Font punkte = new Font("Tahoma", Font.BOLD, (int)(pixelButton*0.45));
		int c=0;
		for (int i = 1; i <= bi.anzSpiele(); i++) {
			JButton tempLinks = new JButton("" + i);
			tempLinks.setName("" + c++);//Position in ArrayList
			tempLinks.setActionCommand(tempLinks.getText()+ ";" + tempLinks.getName());
			tempLinks.setBackground(Color.black);
			tempLinks.setForeground(Color.DARK_GRAY);
			tempLinks.setVisible(true);
			tempLinks.setFont(punkte);
			
			tempLinks.setBounds((int)feldLinks, (int)feldLinksUnten, pixelButton, pixelButton);

			JButton tempRechts = new JButton(""+i);
			tempRechts.setName("" + c++);
			tempRechts.setActionCommand(tempRechts.getText()+ ";" + tempRechts.getName());
			tempRechts.setBackground(Color.black);
			tempRechts.setForeground(Color.DARK_GRAY);
			tempRechts.setVisible(true);
			tempRechts.setFont(punkte);
			tempRechts.setBounds((int)feldRechts, (int) feldRechtsUnten, pixelButton, pixelButton);
			if (i!=1) {
				tempLinks.setEnabled(false);
				tempRechts.setEnabled(false);
			}
			
			punkteListe.add(tempLinks);
			punkteListe.add(tempRechts);
			feldLinks += (pixelButton *1.16);
			feldRechts += (pixelButton *1.16);
			if ((i % 5) == 0) { //Reihe voll
				feldLinks = punkteStand1.getBounds().getMinX();
				feldRechts = punkteStand2.getBounds().getMinX();
				feldLinksUnten += pixelButton*1.16;
				feldRechtsUnten += pixelButton*1.16;
			}
			
	
			}
		for (JButton temp: punkteListe) {
			punkteAnzeige.add(temp);
			temp.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					temp.setBackground(Color.LIGHT_GRAY);
					String[] actionKommando = e.getActionCommand().split(";");
					int[] punkt = {Integer.parseInt(actionKommando[0]),Integer.parseInt(actionKommando[1])};
					if (bi.anzSpiele()==Integer.parseInt(temp.getText())) {
						for (JButton t: punkteListe)
							t.setEnabled(false);
						
						if (punkt[1]%2 ==0)
							punkteStand1.setText("" + (Integer.parseInt(punkteStand1.getText())+punkt[0]));
						else
							punkteStand2.setText("" + (Integer.parseInt(punkteStand2.getText())+punkt[0]));
						return;
					}
					punkteListe.get(punkt[1]+2).setEnabled(true);
					int team1 = 0;
					int team2 = 0;
					if (punkt[1]%2 == 0) {
						punkteListe.get(punkt[1]+1).setEnabled(false);
						punkteListe.get(punkt[1]+3).setEnabled(true);
						team1 = punkt[0];
					}else {
						punkteListe.get(punkt[1]-1).setEnabled(false);
						punkteListe.get(punkt[1]+1).setEnabled(true);
						team2 = punkt[0];
					}
					try {
						bi.setErgebnisMiniSpiel(team1, team2);
					} catch (SdTException e1) {
						JOptionPane.showConfirmDialog(frm, "Punkte speichern fehlgeschlagen","Kritischer Fehler",JOptionPane.ERROR_MESSAGE);
					}
					punkteStand1.setText("" + (Integer.parseInt(punkteStand1.getText())+team1));
					punkteStand2.setText("" + (Integer.parseInt(punkteStand2.getText())+team2));
				}
			
			});
		}
	}

	/**
	 * Ändert den Sichtbarkeitszustand des Fensters
	 * @param b Wahrheitswert ob das Fenster Sichtbar (true) oder nicht Sichtbar (false) sein soll
	 */
	public static void setVisible(boolean b) {
		frm.setVisible(b);
	}

	/**
	 * Setzt ein neues Benienerinterface
	 * @param bi neues Bedienerinterface
	 */
	public void setSpiele(bedienerInterface bi) {
		this.bi = bi;
	}
}
