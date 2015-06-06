/**
 * @file AG.java
 * @package Applet
 * @project AlgorithmeGenetique
 * @date 21 janv. 2010
 * @user hugo
 */
package Applet;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Views.Fenetre;

public class AG extends JApplet implements Runnable {
	public static JFrame frame;
	public void run() {
		
	}
	
	public void start() {

	}

	public void stop() {
		
	}

	public void init() {
		this.setSize(250, 45);
		this.setVisible(true);
		
		String lookAndFeelName = UIManager.getSystemLookAndFeelClassName();

		try {

			UIManager.setLookAndFeel(lookAndFeelName);
		} 
		catch (ClassNotFoundException e) {} 
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		JPanel panel = new JPanel(new BorderLayout());
		JButton bouton = new JButton("Appuyer pour lancer l'application");
		bouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						frame = new Fenetre();
						frame.setVisible(true);
					}
				}).start();
			}
		});
		this.setLayout(new BorderLayout());
		this.add(panel);
		panel.add(bouton);
	}
}
