/**
 * @file Mutation.java
 * @package Modeles
 * @project AlgorithmeGenetique
 * @date 18 oct. 09
 * @user hugo
 */
package Modeles;

import java.util.*;


public class Mutation {
	
	private Vector<Integer> aWay;
	private Vector<Integer> aWay2;
	
	/**
	 * @param chemin 
	 */
	public Mutation(Chemin chemin) {
		this.aWay = (Vector<Integer>) chemin.getRandGenerate().clone();
		this.aWay2 = (Vector<Integer>) chemin.getRandGenerate().clone();
		int taille = this.aWay.size();
	
		int num1 = 0;
		int num2 = 0;
		do {
			num1 = (int) (Math.random()*(taille-1)+1);
			num2 = (int) (Math.random()*(taille-2)+2);
		}
		while (num1 == num2);
		int tempNum = 0;
		
		if (num1 < num2) {			//Le plus petit en premier
			tempNum = num1;	
			num1 = num2;
			num2 = tempNum;
		}
		int num = num1;
		for (int i = num2; i >= num1; i--) {	//Inverse les éléments un par un
			this.aWay2.setElementAt(this.aWay.elementAt(num), i);
			num++;
		}
		
	}

	
	/**
	 * @return the aWay2
	 */
	public Vector<Integer> getAWay2() {
	
		return this.aWay2;
	}
}
