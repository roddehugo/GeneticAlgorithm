/**
 * @file CreatePopulation.java
 * @package Modeles
 * @project AlgorithmeGenetique
 * @date 15 oct. 09
 * @user hugo
 */
package Modeles;

import Views.Fenetre;
import java.io.*;
import java.util.ArrayList;

public class CreatePopulation {
	private ArrayList<Ville> cities;
	private ArrayList<Chemin> ways;
	private final String file = "villes.serialized";
	
	public CreatePopulation(int nbVilles,int nbChemins) {
		cities = new ArrayList<Ville>();
		ways = new ArrayList<Chemin>();
		if (Fenetre.recupVilles.isSelected()) {
			 ObjectInputStream ois;        
			 try {
	        	ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(file))));
	        	int i = 0;
	        	try {
	        		Ville city = (Ville) ois.readObject();
	        		while(city != null) {
	        			cities.add(city);
	        			city = (Ville) ois.readObject();
	        		}
				} catch (ClassNotFoundException e) {
					for (int j = 0; j < nbVilles; j++) {
						cities.add(new Ville(j));
						//System.out.println("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
						//Fenetre.setInfoArea("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
					}
				}
				ois.close();
	        	
	        } catch (FileNotFoundException e) {
	        	for (int i = 0; i < nbVilles; i++) {
					cities.add(new Ville(i));
					//System.out.println("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
					//Fenetre.setInfoArea("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
				}
	        } catch (IOException e) {
	        	for (int i = 0; i < nbVilles; i++) {
					cities.add(new Ville(i));
					//System.out.println("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
					//Fenetre.setInfoArea("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
				}
	        }     	

		}else {
			for (int i = 0; i < nbVilles; i++) {
				cities.add(new Ville(i));
				//System.out.println("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
				//Fenetre.setInfoArea("Ville : "+cities[i].getNum()+"("+cities[i].getX()+","+cities[i].getY()+")");
			}
		}
		
		if (Fenetre.saveVilles.isSelected()) {
	        ObjectOutputStream oos;
	        
	        try {
	        	oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(file))));
	        	
	        	for (int i = 0; i < nbVilles; i++) {	//Ecrit chaque objet Ville dans le fichier
	        		oos.writeObject(cities.get(i));
				}
	        	
	        	oos.close();
	        		        	
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }     	

		}
		
		for (int i = 0; i < nbChemins; i++) {
			ways.add(new Chemin(i,cities));
			//System.out.println("Chemin "+ways[i].getNumWay()+" : "+ways[i].getRandGenerate());
			//Fenetre.setInfoArea("Chemin "+ways[i].getNumWay()+" : "+ways[i].getRandGenerate());
		}
	}
	
	
	/**
	 * @return the ways
	 */
	public ArrayList<Chemin> getWays() {
		return ways;
	}

	/**
	 * @return la liste des villes
	 */
	public ArrayList<Ville> getCities() {
	
		return cities;
	}
	
}
