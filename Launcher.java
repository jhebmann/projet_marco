package projet_marco;

import java.util.*;

public class Launcher {
	public static void main(String[] args) {
		
		
		long startTime = System.currentTimeMillis(); //pour voir le temps que dure l'algo
		int nbG = 18; //le nombre de groupes
		int nbP = 25; //le nombre de projets
		
		Resultats res = new Resultats(100, nbG, nbP); //On g�n�re le tableau
		//Pour utiliser des donn�es non g�n�r�es al�atoirement, il faut cr�er un tableau d'entiers et appeler le constructeur Resultats(tableau)
		List<HashMap<String, Integer>> sortie = res.bestSolution(); //On recupere les meilleures solutions
		
		
		//On affiche les resultats avec la plus basse somme des erreurs
		System.out.print("Il y a " + sortie.size() + " r�partitions possibles :");
		
		for(int j = 0 ; j < sortie.size() ; j++){
			System.out.println();
			for (String i : sortie.get(j).keySet()) {
				System.out.print(i + " : " + sortie.get(j).get(i) + "\t");
			}
		}
		
		System.out.println("\n" + res);
		
		//On affiche le temps qu'a pris l'algo
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("temps : " + elapsedTime + " millisecondes");
	    
	    HashMap<String, Integer> sortie2 = res.improve();
	    System.out.println();
		for (String i : sortie2.keySet()) {
			System.out.print(i + " : " + sortie2.get(i) + "\t");
		}
	}
}
