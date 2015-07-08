/**
 * @file Recombinaison.java
 * @package Modeles
 * @project AlgorithmeGenetique
 * @date 17 oct. 09
 * @user hugo
 */
package Modeles;

import java.util.Vector;


public class Recombinaison {
	private Vector<Integer> enfant;
	private Chemin enfantWay;
	
	/**
	 * @param maman 
	 * @param papa 
	 * @param j 
	 */
	public Recombinaison(Vector<Integer> papa, Vector<Integer> maman) {
		//System.out.println("Recombinaison : ");
		int taille = papa.size();
		int cesure = (int) (Math.round(Math.random()*(taille-2)+1));	//Nombre aleatoire entre [1,taille-1]
																		//Ex: pour taille = 5 : [1,4]														
		enfant = new Vector<Integer>(taille);	
		
		for (int i = 0; i < cesure; i++) {								//Recopie avant la césure
			enfant.addElement(papa.elementAt(i));
		}
		for (int i = cesure; i < taille; i++) {							//Recopie de la mère apres la césure
			if (!enfant.contains(maman.elementAt(i))) {
				enfant.addElement(maman.elementAt(i));
			}
		}
		for (int i = 0; i < cesure; i++) {								//Complete avec les éléments de la mère avant la césure
			if (!enfant.contains(maman.elementAt(i))) {
				enfant.addElement(maman.elementAt(i));
			}
		}

		enfantWay = new Chemin(enfant);
		
		//System.out.println("Cesure : "+cesure);
		//System.out.println("Enfant : "+enfant);

	}

	
	/**
	 * @return the enfant
	 */
	public Vector<Integer> getEnfant() {
	
		return enfant;
	}


	
	/**
	 * @return the enfantWay
	 */
	public Chemin getEnfantWay() {
	
		return enfantWay;
	}

}
