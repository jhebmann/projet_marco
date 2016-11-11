package projet_marco;

import java.util.*;

public class Launcher {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis(); //pour voir le temps que dure l'algo
		int nbG = 18; //le nombre de groupes
		int nbP = 25; //le nombre de projets
		Resultats res = new Resultats(nbP*2, nbG, nbP); //On génère le tableau
		//System.out.println("\n" + res);
		HashMap<String, Integer>[] sortie = res.bestSolution(); //On recupere la meilleure solution
		
//		for(int i = 0 ; i < 99 ; i++){
//			res = new Resultats(nbP*2, nbG, nbP);
//			sortie = res.bestSolution();
//			System.out.println(i);
//		}
		
		/*
		int compteur = 0;
		int moyenne = 0;
		// System.out.println(res.permutations);
		for (int i = 0; i < 10; i++) {
			compteur = 0;
			System.out.println(i);
			res = new Resultats(10, nbG, nbP);
			sortie = res.bestSolution();
			while (sortie[0] == sortie[1] || sortie[0] == sortie[2] || sortie[2] == sortie[1]) {
				compteur++;
				res = new Resultats(10, nbG, nbP);
				sortie = res.bestSolution();
				//System.out.println("\n\n");
			}
			moyenne+=compteur;
		}
		moyenne/=10;
		*/
		
		//On affiche le resultat avec la plus basse somme des erreurs
		for (String i : sortie[0].keySet()) {
			System.out.print(i + " : " + sortie[0].get(i) + "\t");
		}
		System.out.println();
		//On affiche le resultat avec la plus basse somme des erreurs au carré
		for (String i : sortie[1].keySet()) {
			System.out.print(i + " : " + sortie[1].get(i) + "\t");
		}
		System.out.println();
		//On affiche le resultat avec la plus basse somme des erreurs au cube
		for (String i : sortie[2].keySet()) {
			System.out.print(i + " : " + sortie[2].get(i) + "\t");
		}
		System.out.println("\n" + res);
		//System.out.println("Au bout d'une moyenne de " + moyenne + " essais");
		
		//On affiche le temps qu'a pris l'algo
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("temps : " + elapsedTime + " millisecondes");
	}
}
