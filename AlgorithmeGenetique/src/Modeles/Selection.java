/**
 * @file Selection.java
 * @package Modeles
 * @project AlgorithmeGenetique
 * @date 16 oct. 09
 * @user hugo
 */
package Modeles;

import java.util.ArrayList;
import java.util.Vector;


public class Selection {
	private Vector<Double> allFits;
	private int[] selectionArray;
	private int a = 0, rand;
	private ArrayList<Chemin> ways;
	
	public Selection(Vector<Double> allFits, ArrayList<Chemin> ways) {
		this.allFits = allFits;
		this.ways = ways;
		selectionArray = new int[allFits.size()];
		
		for (int i = 0; i < selectionArray.length; i++)
		{
			selectionArray[i] = (int) (Math.sqrt ((i + 1.0) / (allFits.size())) * (10000));
			//Plus de chances pour les premiers chemins
			//System.out.println(selectionArray[i]);
		}

		rand = (int) (Math.random()*10000);
		
		while (rand > selectionArray[a])	//Genere un nombre aléatoire pour la selection
		{
			a++;
		}
		//System.out.println(rand);
		
	}
	
	/**
	 * @return le tableau des selections
	 */
	public int[] getSelectionArray() {
	
		return selectionArray;
	}
	
	/**
	 * @return le l'index de selection de l'individu
	 */
	public double getSelectedIndex() {
		
		return allFits.elementAt(a);
	}
	
	/**
	 * @return le chemin correspondant à la selection
	 */
	public Vector<Integer> getSelectedWay() {
		
		return ways.get(a).getRandGenerate();
	}
}
