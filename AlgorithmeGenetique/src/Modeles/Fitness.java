/**
 * @file Fitness.java
 * @package Modeles
 * @project AlgorithmeGenetique
 * @date 15 oct. 09
 * @user hugo
 */
package Modeles;

import java.util.*;

import Views.MonRunnable;


public class Fitness {
	
	private Vector<Integer> aWay;
	private ArrayList<Ville> cities;
	private double longueur = 0;
	private int taille;

	public Fitness(Chemin chemin) {
		this.aWay = chemin.getRandGenerate();
		this.cities = chemin.getCities();
		this.longueur = chemin.getLongueur();
		this.taille = aWay.size();
		
		if (this.longueur == 0) {
			for (int i = 1; i < taille; i++) {
				//System.out.println("Pour la ville n° : "+this.cities[(int) aWay.elementAt(i)].getNum()+" à l'emplacement "+(int) aWay.indexOf(this.cities[(int) aWay.elementAt(i)].getNum())+" dans le chemin pour i= "+i);
				//Calcul de la longueur entre chaque ville de coordonées (X,Y)
				
				longueur += calc(i, false);
			}
			//Calcul de la longueur entre la dernière et la première ville
			
			longueur += calc(0, true);
			
			chemin.setLongueur(this.longueur);
		}

		//System.out.println(this.longueur);
	}
	
	public Fitness(Vector<Integer> vec) {
		this.aWay = vec;
		this.cities = MonRunnable.getCities();
		this.longueur = 0;
		this.taille = aWay.size();
		
		if (this.longueur == 0) {
			for (int i = 1; i < taille; i++) {
				//System.out.println("Pour la ville n° : "+this.cities[(int) aWay.elementAt(i)].getNum()+" à l'emplacement "+(int) aWay.indexOf(this.cities[(int) aWay.elementAt(i)].getNum())+" dans le chemin pour i= "+i);
				//Calcul de la longueur entre chaque ville de coordonées (X,Y)
				
				longueur += calc(i, false);
			}
			//Calcul de la longueur entre la dernière et la première ville
			
			longueur += calc(0, true);
			
		}

		//System.out.println(this.longueur);
	}
	
	/**
	 * @return the longeur
	 */
	public double getLongueur() {
	
		return longueur;
	}
	
	private double calc(int i,boolean last){
		
		if(!last){
			return Math.abs(Math.pow((this.cities.get((int) aWay.elementAt(i)).getX())
					-(this.cities.get((int) aWay.elementAt(i-1)).getX()),2)
				+Math.pow((this.cities.get((int) aWay.elementAt(i)).getY())
					-(this.cities.get((int) aWay.elementAt(i-1)).getY()),2));
		}else{
			return Math.abs(Math.pow((this.cities.get((int) aWay.elementAt(this.taille-1)).getX())
					-(this.cities.get((int) aWay.elementAt(0)).getX()),2)
				+Math.pow((this.cities.get((int) aWay.elementAt(this.taille-1)).getY())
					-(this.cities.get((int) aWay.elementAt(0)).getY()),2));
		}
	
	}
}
