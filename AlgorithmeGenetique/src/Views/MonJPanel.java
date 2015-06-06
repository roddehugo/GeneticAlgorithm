/**
 * @file MonJPanel.java
 * @package Views
 * @project AlgorithmeGenetique
 * @date 20 oct. 09
 * @user hugo
 */
package Views;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import Modeles.Chemin;
import Modeles.Ville;


@SuppressWarnings("serial")
public class MonJPanel extends JPanel {
		
	public ArrayList<Ville> cities;
	public Chemin bestWay;
	public Vector<Integer> aWay;

	public void paintComponent(Graphics g) {
		int height = 675;
		int width = 540;
		String numVille;
		String wayStr;
		int X = 0;
		int Y = 0;
		
		g.setColor(Color.white);
		g.fillRect(this.getX(), this.getY(), height, width);
		g.setColor(Color.black);
		g.drawRect(this.getX(), this.getY(), height, width);
		
		if (Fenetre.running) {
			int taille = 0;
			try {
				this.cities = (ArrayList<Ville>) MonRunnable.getCities();
				this.bestWay = MonRunnable.getBestWay();
				this.aWay = this.bestWay.getRandGenerate();
				taille = this.aWay.size();
			}catch (NullPointerException e) {
				e.printStackTrace();
			}

			g.setColor(Color.red);
					
			for (int i = 0; i < cities.size(); i++) {
				X = cities.get(i).getX()*6+20;
				Y = cities.get(i).getY()*5+20;
				numVille = String.valueOf(cities.get(i).getNum());
				g.fillOval(X, Y, 5, 5);
				g.drawString(numVille, X-5, Y-5);
			}
			
			g.setColor(Color.black);
			
			for (int i = 1; i < taille; i++) {
				int xDep = this.cities.get(aWay.elementAt(i-1)).getX()*6+22;
				int yDep = this.cities.get(aWay.elementAt(i-1)).getY()*5+22;
				int xFin = this.cities.get(aWay.elementAt(i)).getX()*6+22;
				int yFin = this.cities.get(aWay.elementAt(i)).getY()*5+22;
				
				g.drawLine(xDep, yDep, xFin, yFin);
			}
			
			int xDep = this.cities.get(aWay.elementAt(0)).getX()*6+22;
			int yDep = this.cities.get(aWay.elementAt(0)).getY()*5+22;
			int xFin = this.cities.get(aWay.elementAt(taille-1)).getX()*6+22;
			int yFin = this.cities.get(aWay.elementAt(taille-1)).getY()*5+22;
			g.drawLine(xDep, yDep, xFin, yFin);
			wayStr = String.valueOf(bestWay.getLongueur());
			g.drawString(wayStr, this.getX()+10, this.getY()+20);
		}
	}
}
