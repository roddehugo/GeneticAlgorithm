/**
 * @file Optimisation.java
 * @package Views
 * @project AlgorithmeGenetique
 * @date 23 déc. 2009
 * @user hugo
 */
package Modeles;

import java.util.Vector;

public class Optimisation {

	private Vector<Integer> aWay;
	private Vector<Integer> aWay2;
	private double longueur = 0;
	
	@SuppressWarnings("unchecked")
	public Optimisation(Chemin chemin) {

		this.aWay = (Vector<Integer>) chemin.getRandGenerate().clone();
		this.aWay2 = (Vector<Integer>) chemin.getRandGenerate().clone();
		this.longueur = chemin.getLongueur();
		int taille = aWay.size();
		int end = taille-1;
		int start = 0;
		
		do {
			this.aWay2.setElementAt(chemin.getRandGenerate().elementAt(end), start);
			this.aWay2.setElementAt(chemin.getRandGenerate().elementAt(start), end);
			double longWay2 = new Fitness(this.aWay2).getLongueur();
			if ( longWay2 < this.longueur) {
				this.aWay.setElementAt(chemin.getRandGenerate().elementAt(end), start);
				this.aWay.setElementAt(chemin.getRandGenerate().elementAt(start), end);
				//System.out.println(longWay2+" plus petit que "+this.longueur);
			}
			start++;
			end--;
		} while (start < (taille/2));
	}
	
	/**
	 * @return the aWay
	 */
	public Vector<Integer> getaWay() {
	
		return aWay;
	}
}
