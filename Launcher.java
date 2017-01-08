package projet_marco;

import java.util.*;

public class Launcher {
	public static void main(String[] args) {
		
		
		long startTime = System.currentTimeMillis(); //pour voir le temps que dure l'algo
		int nbG = 20; //le nombre de groupes
		int nbP = 40; //le nombre de projets
		int nEssais = 1000; // le nombre d'essais a effectuer
		boolean stopIfLong = true;
		
		Resultats res = new Resultats(100, nbG, nbP, stopIfLong); //On génère le tableau
		//Pour utiliser des données non générées aléatoirement, il faut créer un tableau d'entiers et appeler le constructeur Resultats(tableau)
		List<HashMap<String, Integer>> sortie = res.bestSolution(); //On recupere les meilleures solutions
		HashMap<String, Integer> repartition = res.improve();
		
		
		
		for(int i = 0 ; i < nEssais-1 ; i++){
			System.out.println("Numero " + (i+2) + " : ");
			res = new Resultats(100, nbG, nbP, stopIfLong);
			sortie = res.bestSolution();
			repartition = res.improve();
		}
		
		System.out.println("\n" + res);
		
		//On affiche le temps qu'a pris l'algo
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("temps moyen : " + (elapsedTime/nEssais) + " millisecondes");
	    
	    System.out.println();
		for (String i : repartition.keySet()) {
			System.out.print(i + " : " + repartition.get(i) + "\t");
		}
	}
}
