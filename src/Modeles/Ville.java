/**
 * @file Ville.java
 * @package Modeles
 * @project AlgorithmeGenetique
 * @date 15 oct. 09
 * @user hugo
 */
package Modeles;

import java.io.Serializable;


public class Ville implements Serializable {

	private int x;
	private int y;
	private int numCity;

	public Ville(int numCity) {
		this.x = (int) (Math.random()*100);						//Nombre aléatoire entre 0 et 100
		this.y = (int) (Math.random()*100);
		this.numCity = numCity;									//Recupère le numéro de la ville
		
	}
	
	/**
	 * @return le numéro de la ville
	 */
	public int getNum() {
	
		return numCity;
	}

	/**
	 * @return la coordonnée x de la ville
	 */
	public int getX() {
	
		return x;
	}

	/**
	 * @return la coordonnée y de la ville
	 */
	public int getY() {
	
		return y;
	}

	/**
	 * @param numCity the numCity to set
	 */
	public void setNumCity(int numCity) {
	
		this.numCity = numCity;
	}
	
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
	
		this.x = x;
	}
	
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
	
		this.y = y;
	}
}
