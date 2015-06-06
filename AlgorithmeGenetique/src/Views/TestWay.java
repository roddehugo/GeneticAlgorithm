/**
 * @file TestWay.java
 * @package Views
 * @project AlgorithmeGenetique
 * @date 23 déc. 2009
 * @user hugo
 */
package Views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;

import Modeles.Chemin;
import Modeles.Fitness;


public class TestWay {

	Vector<Integer> vec;
	JTextField text;
	JLabel lab;
	JButton button;
	public TestWay() {
		JFrame frame = new JFrame("Test");
		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(contentPane());
		frame.getRootPane().setDefaultButton(button);
		frame.setVisible(true);
	}
	
	private Container contentPane() {
		JPanel panel = new JPanel(new BorderLayout());
		text = new JTextField();
		lab = new JLabel("Entrer un chemin (ex: 1,2,3,4,5,6,7,8,9)");
		button = new JButton("Test");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vec = new Vector<Integer>();
				StringTokenizer stk = new StringTokenizer(text.getText(), ",");
				while (stk.hasMoreTokens()) {
					vec.add(Integer.parseInt(stk.nextToken()));
				}
				Chemin away = new Chemin(vec);
				away.setCities(MonRunnable.getCities());
				Fitness fit = new Fitness(away);
				lab.setText(String.valueOf(fit.getLongueur()));
			}
		});
		
		panel.add(text);
		panel.add(lab, BorderLayout.NORTH);
		panel.add(button, BorderLayout.SOUTH);
		return panel;
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
		
		new TestWay();
	}
}
