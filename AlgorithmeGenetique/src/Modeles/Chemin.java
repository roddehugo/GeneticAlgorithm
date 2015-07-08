/**
 * @file Chemin.java
 * @package Modeles
 * @project AlgorithmeGenetique
 * @date 15 oct. 09
 * @user hugo
 */
package Modeles;

import java.util.ArrayList;
import java.util.Vector;

public class Chemin {
	private Vector<Integer> randGenerate;
	private ArrayList<Ville> cities;
	private int numWay;
	private double longueur = 0;
	
	public Chemin(int numWay, ArrayList<Ville> cities2) {
		this.cities = cities2;
		this.numWay = numWay;
		int taille = cities2.size();
		int randIndex;
		this.randGenerate = new Vector<Integer>(taille);
		
		for (int i = 0; i < taille; i++) {
			do {
				randIndex = (int) (Math.random()*taille);
			}
			while (randGenerate.contains(randIndex));
			randGenerate.addElement(randIndex);
		}
	}
	
	public Chemin(Vector<Integer> enfant) {
		this.randGenerate = enfant;
		
	}
	
	/**
	 * @return le chemin
	 */
	public Vector<Integer> getRandGenerate() {
		return randGenerate;
	}
	
	
	/**
	 * @param randGenerate the randGenerate to set
	 */
	public void setRandGenerate(Vector<Integer> randGenerate) {
	
		this.randGenerate = randGenerate;
	}

	/**
	 * @return the cities
	 */
	public ArrayList<Ville> getCities() {
	
		return cities;
	}

	
	/**
	 * @return the numWay
	 */
	public int getNumWay() {
	
		return numWay;
	}

	public boolean shorter(Chemin way) {
		if (way.getLongueur()>this.longueur) {
			return true;
		}
		
		return false;
	}

	/**
	 * @param longueur the longueur to set
	 */
	public void setLongueur(double longueur) {
	
		this.longueur = longueur;
	}

	/**
	 * @return
	 */
	public double getLongueur() {

		return longueur;
	}
	
	/**
	 * @param numWay the numWay to set
	 */
	public void setNumWay(int numWay) {
	
		this.numWay = numWay;
	}
	
	/**
	 * @param cities the cities to set
	 */
	public void setCities(ArrayList<Ville> cities) {
	
		this.cities = cities;
	}

}
