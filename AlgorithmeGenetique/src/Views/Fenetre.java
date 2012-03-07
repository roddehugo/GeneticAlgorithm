package Views;

import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;

import java.awt.*;
import java.io.*;

public class Fenetre extends JFrame implements ActionListener, FocusListener, ItemListener
{
	public static JFrame frame;
	public static JButton lancer;
	public static JButton stoper, test;
	public JTextField villeField, cheminField, generationField, mutationField;
	public static JProgressBar progressBar;
	public Thread monThread;
	public Runnable threadTache;
	public static boolean running = false, pause = false;
	public static int nbVilles,nbChemins, nbGene, nbMutations;
	public static double nbPourcent;
	public static JTextArea infoArea;
	private final String VILLE = "Nombre de villes ?", 
						CHEMIN = "Nombre de chemin ?",
						GENE = "Nombre de générations ?",
						MUTATION = "Nombre de mutations ?";
	public static JRadioButton saveVilles, recupVilles;
	
	public Fenetre()
	{
		this.setTitle("Algorithmes Génétiques - Problème du Voyageur de Commerce");
		this.setSize(920,700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setContentPane(contentPane());
		this.getRootPane().setDefaultButton(lancer);
		this.repaint();
	}
	
	private Container contentPane() {
		
		JPanel panel = new JPanel(new BorderLayout());
			panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel rightPanel = new JPanel(new GridBagLayout());
		JPanel bottomPanel = new JPanel(new BorderLayout());
		
		GridBagConstraints c = new GridBagConstraints ();
		JLabel villeLabel = new JLabel(VILLE);
		JLabel cheminLabel = new JLabel(CHEMIN);
		JLabel generationLabel = new JLabel(GENE);
		JLabel mutationLabel = new JLabel(MUTATION);
		
		progressBar = new JProgressBar();
			progressBar.setMinimum(0);
			progressBar.setStringPainted(true);

		infoArea = new JTextArea(10,10);
			infoArea.setLineWrap(true);
			
		JScrollPane scroll = new JScrollPane(infoArea);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
			scroll.setPreferredSize(new Dimension(this.getWidth(),90));
		
		saveVilles = new JRadioButton("Enregistrer les villes ?");
		recupVilles = new JRadioButton("Réutiliser des villes ?");
			recupVilles.addItemListener(this);
		ButtonGroup buttonGrp = new ButtonGroup();
			buttonGrp.add(saveVilles);
			buttonGrp.add(recupVilles);
		
		villeField = new JTextField(15);
			villeField.addFocusListener(this);
		cheminField = new JTextField(15);
			cheminField.addFocusListener(this);
		generationField = new JTextField(15);
			generationField.addFocusListener(this);
		mutationField = new JTextField(15);
			mutationField.addFocusListener(this);

        try{
            lancer = new JButton(new ImageIcon(getClass().getResource("/play.png")));
                lancer.addActionListener(this);
            stoper = new JButton(new ImageIcon(getClass().getResource("/stop.png")));
                stoper.addActionListener(this);
	    } catch (NullPointerException e) {
            lancer = new JButton("Lancer");
                lancer.addActionListener(this);
            stoper = new JButton("Stoper");
                stoper.addActionListener(this);
        }

		
		test = new JButton("Tester un chemin");
			test.addActionListener(this);
		
		panel.add(centerPanel, BorderLayout.CENTER);
			centerPanel.add(new MonJPanel());
		panel.add(rightPanel, BorderLayout.EAST);
					c.anchor = GridBagConstraints.NORTH;
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 0;
					c.gridwidth = 2; 
					c.insets = new Insets(0,0,5,0);
					c.weightx = 1;
				rightPanel.add(saveVilles,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 1;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(recupVilles,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 2;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(villeLabel,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 3;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(villeField,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 4;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(cheminLabel,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 5;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(cheminField,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 6;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(generationLabel,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 7;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(generationField,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 8;
					c.gridwidth = 2;  
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(mutationLabel,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 9;
					c.gridwidth = 2; 
					c.insets = new Insets(0,0,5,0);
				rightPanel.add(mutationField,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 10;
					c.gridwidth = 1;  
					c.insets = new Insets(0,0,0,0);
					c.weightx = 2;
				rightPanel.add(lancer,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 1;
					c.gridy = 10;
					c.gridwidth = 1;  
					c.insets = new Insets(0,0,0,0);
				rightPanel.add(stoper,c);
					c.fill = GridBagConstraints.HORIZONTAL;
					c.gridx = 0;
					c.gridy = 11;
					c.gridwidth = 2;  
					c.insets = new Insets(25,0,0,0);
				rightPanel.add(test, c);
		panel.add(bottomPanel, BorderLayout.SOUTH);
			bottomPanel.add(scroll, BorderLayout.CENTER);
			bottomPanel.add(progressBar, BorderLayout.SOUTH);
		
		return panel;
	}

	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		
		if(src == lancer) {
			if ((!running) && conditions()) {
				this.repaint();
				nbPourcent = (double) Math.random();
				infoArea.setText("Nouveau départ");
				setInfoArea("Pourcentage pour les mutations: "+String.valueOf(nbPourcent));
				progressBar.setString("");
				progressBar.setValue(0);
				threadTache = new MonRunnable();
				monThread = new Thread(threadTache);
				monThread.start();
				lancer.setEnabled(false);
				this.getRootPane().setDefaultButton(stoper);
				running = true;
			}else {
				infoArea.append("Veuillez entrer des nombres décimaux...\n");
			}
		}else if(src == stoper && running) {
			monThread.stop();
			nbVilles = 0;
			nbChemins = 0;
			nbGene = 0;
			nbMutations = 0;
			nbPourcent = 0;
			lancer.setEnabled(true);
			this.getRootPane().setDefaultButton(lancer);
			running = false;
		}else if(src == test) {
			new TestWay();
		}
	}
	
	/**
	 * @return
	 */
	private boolean conditions() {
		boolean condition = false;
		
		try {
			nbVilles = Integer.parseInt(villeField.getText());
			condition = true;
		} catch (NumberFormatException f) {
			condition = false;
		}
		try {
			nbChemins = Integer.parseInt(cheminField.getText());
			condition = true;
		} catch (NumberFormatException f) {
			condition = false;
		}
		try {
			nbGene = Integer.parseInt(generationField.getText());
			condition = true;
		} catch (NumberFormatException f) {
			condition = false;
		}
		try {
			nbMutations = Integer.parseInt(mutationField.getText());
			condition = true;
		} catch (NumberFormatException f) {
			condition = false;
		}
		return condition;
	}

	public void focusGained(FocusEvent e) {
		this.getRootPane().setDefaultButton(lancer);
		((JTextComponent) e.getSource()).selectAll();
	}

	public void focusLost(FocusEvent e) {
		((JTextComponent) e.getSource()).setCaretPosition(0);
	}
	
	/**
	 * @param infoArea the infoArea to set
	 */
	public static void setInfoArea(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				infoArea.append("\n"+text);
				infoArea.setCaretPosition(infoArea.getText().length());
			}
		});
	}

	public void itemStateChanged(ItemEvent e) {
		Object src = e.getSource();
		if (src == recupVilles) {
			if (recupVilles.isSelected()) {
				villeField.setEnabled(false);
			}else {
				villeField.setEnabled(true);
			}
		}
		
	}

	public static void main(String[] args)
	{
		String lookAndFeelName = UIManager.getSystemLookAndFeelClassName();
		
		try {

			UIManager.setLookAndFeel(lookAndFeelName);
		} 
		catch (ClassNotFoundException e) {} 
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		frame = new Fenetre();
		frame.setVisible(true);
	}

}
