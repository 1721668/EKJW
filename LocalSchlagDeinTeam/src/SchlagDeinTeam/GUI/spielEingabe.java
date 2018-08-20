package SchlagDeinTeam.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import SchlagDeinTeam.EinzelSpiel;
import SchlagDeinTeam.GruppenSpiel;
import SchlagDeinTeam.MiniSpiel;
import SchlagDeinTeam.SdTException;
import SchlagDeinTeam.Spiel;
import SchlagDeinTeam.bedienerInterface;


public class spielEingabe extends Thread{
	JFileChooser chooser;
	JFrame frm;
	JPanel pn;
	JLabel lb;
	JTextArea b;
	JLabel lt;
	JTextArea t;
	private bedienerInterface bi;
	private JTextField textField;
	private ArrayList<GruppenSpiel>gruppenSpiele = new ArrayList <GruppenSpiel> ();
	private ArrayList<EinzelSpiel>einzelSpiele= new ArrayList <EinzelSpiel> ();
	private GraphicsEnvironment ge;
	private GraphicsDevice[] anzeige;
	
	
	/**
	 * Konstruktor der spielEingabe (Parameterlos)
	 */
	public spielEingabe() {
		anzeigeEinstellen();
		int start = JOptionPane.showConfirmDialog(new JFrame(anzeige[0].getDefaultConfiguration()), "Möchten Sie einen vorhandenen Spielstand laden?", "Startoption", JOptionPane.YES_NO_CANCEL_OPTION);
		int[] temp = {0,0};
		bi = new Spiel();
		bi.setErgebnis(temp);
		
		switch (start) {
		case 0:{
			chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setFileFilter(new FileNameExtensionFilter("SchlagdeinTeam Datei", "sdt"));
			if (chooser.showOpenDialog(frm) == JFileChooser.APPROVE_OPTION) {
				try {
					this.bi = bi.laden(chooser.getSelectedFile().getAbsolutePath());
				}catch (SdTException e) {
					JOptionPane.showMessageDialog(null, "Laden konnte nicht durchgeführt werden", "Ladefehler", JOptionPane.ERROR_MESSAGE);
				}
			}else
				JOptionPane.showMessageDialog(null, "keine Datei ausgewählt", "Ladefehler", JOptionPane.ERROR_MESSAGE);
		}case 1:{
			initialize();
			break;
		}default:{
			System.exit(0);
		}
		}
		start();
		
	}
	
	/**
	 * frägt die aktuellen Spieleingaben ab
	 */
	private void anzeigeEinstellen() {
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		anzeige = ge.getScreenDevices();
	}

	/**
	 * Methode um das Fenster zu befüllen
	 */
	private void initialize() {
		
		/**
		 * @wbp.parser.entryPoint
		 */
		Dimension size;
		size = anzeige[0].getDefaultConfiguration().getBounds().getSize();
		frm=new JFrame(anzeige[0].getDefaultConfiguration());
		frm.setTitle("Spiel hinzufügen");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setResizable(false);
		frm.setBounds((int)(size.getWidth()*0.2),(int)(size.getHeight()*0.2),(int)(size.getWidth()*0.6),(int)(size.getHeight()*0.6));
		frm.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/SchlagDeinTeam/GUI/Icons/Logo_klein.png")));
		
		
		pn = new JPanel();
		pn.setLayout(null);
		pn.setBackground(Color.BLACK);
		pn.setSize(size);
		pn.setVisible(true);
		JLabel logo = new JLabel();
		logo.setVisible(true);
		logo.setIcon(new ImageIcon (new ImageIcon(WindowSpielleiter.class.getResource("/SchlagDeinTeam/GUI/Icons/Logo_klein.png")).getImage().getScaledInstance((int)(size.getWidth()*0.1), (int)(size.getWidth()*0.1), Image.SCALE_DEFAULT)));
		logo.setBounds((int)(frm.getBounds().getWidth()-logo.getIcon().getIconWidth()*1.2),0,logo.getIcon().getIconWidth(),logo.getIcon().getIconWidth());
		
		
		pn.add(logo);
		frm.getContentPane().add(pn);
		
		
		JLabel lblTitel = new JLabel("Titel:");
		lblTitel.setForeground(Color.WHITE);
		lblTitel.setVerticalAlignment(SwingConstants.TOP);
		lblTitel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitel.setBounds((int)(frm.getSize().getWidth()*0.05), (int)(frm.getSize().getWidth()*0.1), (int)(frm.getSize().getWidth()*0.2), (int)(frm.getSize().getHeight()*0.06));
		
		Font schriftLabels = new Font ("Tahoma", Font.BOLD, (int)(lblTitel.getSize().getHeight()*0.7));
		Font schriftFelder = new Font ("Tahoma", Font.PLAIN, (int)(lblTitel.getSize().getHeight()*0.6));
		
		lblTitel.setFont(schriftLabels);
		pn.add(lblTitel);
		
		textField = new JTextField();
		textField.setBackground(new Color (30,30,30));
		textField.setForeground(Color.WHITE);
		textField.setFont(schriftFelder);
		textField.setBorder(null);
		textField.setCaretColor(Color.WHITE);
		textField.setBounds((int)(lblTitel.getBounds().getMaxX()*1.1),(int)(lblTitel.getBounds().getMinY()),(int)(frm.getSize().getWidth()*0.5),(int)(lblTitel.getSize().getHeight()));
		pn.add(textField);
		textField.setColumns(10);
		
		JLabel lblBeschreibung = new JLabel("Beschreibung:");
		lblBeschreibung.setVerticalAlignment(SwingConstants.TOP);
		lblBeschreibung.setFont(schriftLabels);
		lblBeschreibung.setForeground(Color.WHITE);
		lblBeschreibung.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBeschreibung.setBounds((int)lblTitel.getBounds().getMinX(), (int)(lblTitel.getBounds().getMaxY()*1.1), (int)lblTitel.getSize().getWidth(), (int)lblTitel.getSize().getHeight());
		pn.add(lblBeschreibung);
		
		JTextArea textArea = new JTextArea();
		Rectangle text = textField.getBounds();
		textArea.setBackground(new Color (30,30,30));
		textArea.setForeground(Color.WHITE);
		textArea.setFont(schriftFelder);
		textField.setCaretColor(Color.WHITE);
		textArea.setBounds((int)text.getMinX(),(int) lblBeschreibung.getBounds().getMinY(), (int) text.getWidth(),(int) ((frm.getBounds().getHeight()-lblBeschreibung.getBounds().getMaxY())*0.5));
		pn.add(textArea);
		
		JCheckBox c1 = new JCheckBox ("Einzelspiel");
		c1.setBackground(null);
		c1.setForeground(Color.WHITE);
		c1.setFont(schriftFelder);
		c1.setVerticalAlignment(SwingConstants.TOP);
		c1.setVisible(true);
		c1.setSelected(true);
		
		JCheckBox c2 = new JCheckBox ("Gruppenspiel");
		c2.setBackground(null);
		c2.setForeground(Color.WHITE);
		c2.setFont(schriftFelder);
		c2.setVerticalAlignment(SwingConstants.TOP);
		c2.setVisible(true);
		
		JList<JCheckBox> list = new JList<JCheckBox>();
		list.setBackground(new Color (30,30,30));
		list.setFont(schriftFelder);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		

		list.setLayout(new FlowLayout());
		
		c1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (c2.isSelected())
					c2.setSelected(false);
				else
					c1.setSelected(true);
			}
			
		});
		c2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (c1.isSelected())
					c1.setSelected(false);
				else
					c2.setSelected(true);
			}
			
		});
		
		JLabel lblVerhältnis = new JLabel("noch keine Spiele hinzugefügt");
		lblVerhältnis.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerhältnis.setBackground(new Color (30,30,30));
		lblVerhältnis.setForeground(Color.WHITE);
		lblVerhältnis.setFont(schriftFelder);
		lblVerhältnis.setBounds((int)(textArea.getBounds().getMinX()), (int)(textArea.getBounds().getMaxY() + lblTitel.getSize().getHeight()*0.5), (int)(textArea.getBounds().getWidth()), (int)(lblTitel.getSize().getHeight()*2));
		pn.add(lblVerhältnis);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds((int)(textField.getBounds().getMinX()), (int)(logo.getIcon().getIconWidth()*0.2), (int)(frm.getSize().getWidth()*0.5), (int)(textField.getBounds().getHeight()*1.5));
		
		c1.setSize((int)(list.getSize().getWidth()/2),(int)list.getSize().getHeight());
		c2.setSize((int)(list.getSize().getWidth()/2),(int)list.getSize().getHeight());
		
		list.add(c1);
		list.add(c2);
		pn.add(list);
		
		JButton btnFortfahren = new JButton("Fortfahren");
		btnFortfahren.setFont(schriftFelder);
		btnFortfahren.setForeground(Color.WHITE);
		btnFortfahren.setBorder(null);
		btnFortfahren.setBackground(new Color (30,30,30));
		
		btnFortfahren.setSize((int)(textField.getBounds().getWidth()*0.35), (int)(textField.getBounds().getHeight()));
		pn.add(btnFortfahren);
		
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.setFont(schriftFelder);
		btnSpeichern.setBackground(new Color (30,30,30));
		btnSpeichern.setForeground(Color.WHITE);
		btnSpeichern.setBorder(null);
		btnSpeichern.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if(c1.isSelected()) {
						EinzelSpiel temp = new EinzelSpiel (textField.getText(), textArea.getText());
						bi.addSpiel(temp);
						einzelSpiele.add(temp);
					}else {
						GruppenSpiel temp = new GruppenSpiel (textField.getText(), textArea.getText());
						bi.addSpiel(temp);
						gruppenSpiele.add(temp);
					}
					textField.setText("");
					textArea.setText("");
				}catch (SdTException arg0) {
					JOptionPane.showMessageDialog(frm, "" + "Eingaben überprüfen!", "Fehler", JOptionPane.ERROR_MESSAGE);
				}
				String string1, string2;
				if (einzelSpiele.size() == 1) {
					string1 = " Einzelspiel und ";
				}
				else {
					string1 = " Einzelspiele und ";
				}
				if (gruppenSpiele.size() == 1)
					string2 = " Gruppenspiel";
				else
					string2 = " Gruppenspiele";
				lblVerhältnis.setText(einzelSpiele.size() + string1 + gruppenSpiele.size() + string2);
			}
			
		});
		btnFortfahren.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText() != null && textArea.getText() != null) {
					if (textField.getText().length() != 0 || textArea.getText().length() != 0)
						btnSpeichern.doClick();
					if (bi.anzSpiele() == 0) {
						JOptionPane.showMessageDialog(frm, "Fortfahren nicht möglich", "Keine Spiele hinzugefügt", JOptionPane.ERROR_MESSAGE);
						return;
					}
					frm.dispose();
					spielStarten();
				}
			}
		});
		btnSpeichern.setBounds((int)(textField.getBounds().getMinX()), (int)(textArea.getBounds().getMaxY()+textField.getSize().getHeight()*3), (int) btnFortfahren.getSize().getWidth(), (int) btnFortfahren.getSize().getHeight());
		btnFortfahren.setBounds((int)(textField.getBounds().getMaxX()-btnFortfahren.getSize().getWidth()), (int)(btnSpeichern.getBounds().getMinY()), (int) btnSpeichern.getSize().getWidth(), (int) btnSpeichern.getSize().getHeight());
		
		pn.add(btnSpeichern);
		
		frm.setVisible(true);
	}
	
	/**
	 * startet das Spiel
	 */
	private void spielStarten() {
		frm.setVisible(false);
		int sortieren = JOptionPane.showConfirmDialog(frm, "Möchten Sie die Spiele  vorher sortieren?", "Spiele sortieren?", JOptionPane.YES_NO_OPTION);
		for (MiniSpiel temp : einzelSpiele)
			bi.addSpiel(temp);
		for (MiniSpiel temp : gruppenSpiele)
			bi.addSpiel(temp);
		switch (sortieren) {
		case 0: {
			new SpieleSortieren (bi, anzeige);
			break;
		}
		default: new WindowSpielleiter (bi,null); //TODO null ändern
		}
			
				
	}
}
