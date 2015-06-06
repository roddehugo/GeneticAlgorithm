/**
 * @file MonRunnable.java
 * @package Views
 * @project AlgorithmeGenetique
 * @date 20 oct. 09
 * @user hugo
 */
package Views;

import java.util.*;

import javax.swing.*;

import Applet.AG;
import Modeles.*;

public class MonRunnable implements Runnable {

	public int nbVilles = Fenetre.nbVilles , 
				nbChemins = Fenetre.nbChemins, 
				nbGene = Fenetre.nbGene, 
				nbMutations = Fenetre.nbMutations;

	public double nbPourcent = Fenetre.nbPourcent;
	
	public static ArrayList<Ville> cities;
	public static ArrayList<Chemin> ways;
	public static Chemin bestWay;
	public Fitness[] fits;
	public int tailleWays = 0;
	public int a = 0;

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		long time = System.currentTimeMillis();
		
		
		CreatePopulation population = new CreatePopulation(nbVilles,nbChemins);  //Créer les villes et les chemins de départ
		//System.out.println("Population créée !");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Fenetre.setInfoArea("Population créée !");	
				try {AG.frame.repaint();}catch(NullPointerException e){Fenetre.frame.repaint();}
			}
		});
		bestWay = null;
		cities = population.getCities();
		ways = population.getWays();
		tailleWays = ways.size();
		fits = new Fitness[tailleWays];
		//System.out.println("Calcul des générations...");
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Fenetre.setInfoArea("Calcul des générations...");
				Fenetre.progressBar.setMaximum(nbGene);
				try {AG.frame.repaint();}catch(NullPointerException e){Fenetre.frame.repaint();}
			}
		});
		
		do {
			Vector<Double> allLongueurs = new Vector<Double>(tailleWays);
			Vector<Double> allLongueursSort = new Vector<Double>(tailleWays);
			double longTot = 0;
			int enfantIndex = 0;
			int firstEnfantIndex = 0;
			
			for (int j = 0; j < tailleWays; j++) {					//Calcul des fitness pour chaque chemin
				//System.out.print("Longueur pour le chemin n° "+ways[j].getNumWay()+" : ");
				fits[j] = new Fitness(ways.get(j));
				allLongueurs.addElement(fits[j].getLongueur());
			}
			for (int i = 0; i < tailleWays; i++) {			//Additionne toutes les longeurs dans longTot
				longTot += allLongueurs.elementAt(i);
			}
			
			//System.out.println("Toutes les distances : "+allLongueurs);
			//System.out.println("Longueur total : "+longTot);
			
			allLongueursSort = (Vector<Double>) allLongueurs.clone();
			Collections.sort(allLongueursSort);
			
			Selection selA = new Selection(allLongueursSort,ways);	//Selection de deux individus en fonction de leur part
			Selection selB = new Selection(allLongueursSort,ways);
			Vector<Integer> papa = selA.getSelectedWay();
			Vector<Integer> maman = selB.getSelectedWay();
			//System.out.println("Papa : "+papa+", Maman : "+maman);

			Recombinaison recombinaison = new Recombinaison(papa,maman);	//Crossing-Over des deux parents
			Chemin enfant = recombinaison.getEnfantWay();
			enfant.setCities(cities);
			Fitness enfantFit = new Fitness(enfant);	//Calcul Fitness de l'enfant
			//System.out.println("Enfant : "+enfant.getLongueur()+"  "+enfant.getRandGenerate());
			
			Optimisation optimisation = new Optimisation(enfant);
			enfant.setRandGenerate(optimisation.getaWay());
			
			if (enfant.getLongueur() <= allLongueursSort.lastElement()){
				enfantIndex = allLongueurs.indexOf(allLongueursSort.lastElement());
				enfant.setNumWay(enfantIndex);
				ways.set(enfantIndex, enfant);	//Ajoute l'enfant aux Chemins
				fits[enfantIndex] = enfantFit;	//Ajoute enfantFit aux Fitness			
				if ((double) Math.round(Math.random()*100) <= nbPourcent) {	//Applique une mutation si hasard plus grande que pourcentage
					for (int i = 0; i < nbMutations; i++) {		//Applique i Mutations
						Mutation mutate = new Mutation(ways.get(enfantIndex)); 
						ways.get(enfantIndex).setRandGenerate(mutate.getAWay2());
						//System.out.println("way "+i+" : "+ways.get(enfantIndex).getRandGenerate());
					}
				}
			}
			
			Collections.sort(allLongueursSort);
			firstEnfantIndex = allLongueurs.indexOf(allLongueursSort.firstElement());
			//System.out.println(allLongueursSort+"\n"+enfant.getLongueur());
			
			if (bestWay == null) {
				bestWay = ways.get(firstEnfantIndex);
				//System.out.println("Meilleur à la génération n° "+a+" au numéro "+bestWay.getNumWay()+" : "+bestWay.getRandGenerate()+", longueur : "+bestWay.getLongueur());
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Fenetre.setInfoArea("Meilleur à la génération n° "+a+" au numéro "+bestWay.getNumWay()+" : "+bestWay.getRandGenerate()+", longueur : "+bestWay.getLongueur());
						try {AG.frame.repaint();}catch(NullPointerException e){Fenetre.frame.repaint();}
					}
				});
			}else {
				//Prend le plus court chemin comme Meilleur chemin
				if (ways.get(firstEnfantIndex).shorter(bestWay)) {
					bestWay = ways.get(firstEnfantIndex);
					//System.out.println("Meilleur à la génération n° "+a+" au numéro "+bestWay.getNumWay()+" : "+bestWay.getRandGenerate()+", longueur : "+bestWay.getLongueur());
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Fenetre.setInfoArea("Meilleur à la génération n° "+a+" au numéro "+bestWay.getNumWay()+" : "+bestWay.getRandGenerate()+", longueur : "+bestWay.getLongueur());
							try {AG.frame.repaint();}catch(NullPointerException e){Fenetre.frame.repaint();}
						}
					});
				}
			}
			a++;
			//System.out.println("Génération n° : "+a);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Fenetre.progressBar.setString("Génération : "+a+"/"+nbGene);	//Pour la barre de progression
					Fenetre.progressBar.setValue(a);
					try {AG.frame.repaint();}catch(NullPointerException e){Fenetre.frame.repaint();}
				}
			});
			
			if (a == nbGene) {	//Si on est à la fin, on demande si l'utilisateur veut continuer, et sur combien de générations
				String strContinue = JOptionPane.showInputDialog(null, "Terminé. Voulez-vous continuer ? Sur combien de générations ?", "Terminé. Continuer ?", JOptionPane.QUESTION_MESSAGE);
				try {
					int nbContinue = Integer.parseInt(strContinue);
					nbGene += nbContinue;
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Fenetre.progressBar.setMaximum(nbGene);
							try {AG.frame.repaint();}catch(NullPointerException e){Fenetre.frame.repaint();}
						}
					});
				}catch (NumberFormatException f){}
			}
			
		}while(a<nbGene);
		
		long timeTotal = (System.currentTimeMillis()-time);
		final int timeMin = (int) (timeTotal/(60*1000F));
		final int timeSec = (int) ((timeTotal%(60*1000F))/1000F);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Fenetre.setInfoArea("Terminé "+a+"/"+nbGene+", en "+timeMin+" min "+timeSec+" sec");
				try {AG.frame.repaint();}catch(NullPointerException e){Fenetre.frame.repaint();}
			}
		});
		//System.out.println("Terminé "+a+"/"+nbGene+", en "+timeMin+" min "+timeSec+" sec");
	}
	

	/**
	 * @return the bestWay
	 */
	public static Chemin getBestWay() {
	
		return bestWay;
	}
	
	/**
	 * @return the cities
	 */
	public static ArrayList<Ville> getCities() {
	
		return cities;
	}

	/**
	 * @return the ways
	 */
	public static ArrayList<Chemin> getWays() {
	
		return ways;
	}
	
}
