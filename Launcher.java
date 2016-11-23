package projet_marco;

import java.util.*;

public class Launcher {
	public static void main(String[] args) {
		
		
		long startTime = System.currentTimeMillis(); //pour voir le temps que dure l'algo
		int nbG = 18; //le nombre de groupes
		int nbP = 35; //le nombre de projets
		
		Resultats res = new Resultats(nbP*2, nbG, nbP); //On génère le tableau
		//System.out.println("\n" + res);
		HashMap<String, Integer>[] sortie = res.bestSolution(); //On recupere la meilleure solution
		/*
		for(int i = 0 ; i < 98 ; i++){
			res = new Resultats(nbP*2, nbG, nbP);
			sortie = res.bestSolution();
			System.out.println(i);
		}
		*/
		/*
		
		long startTime = System.currentTimeMillis();
		float[][] tab = new float[5][5];
		tab[0][0] = 4;
		tab[0][1] = 5;
		tab[0][2] = 0;
		tab[0][3] = 1;
		tab[0][4] = 2;
		tab[1][0] = 3;
		tab[1][1] = 2;
		tab[1][2] = 5;
		tab[1][3] = 2;
		tab[1][4] = 1;
		tab[2][0] = 2;
		tab[2][1] = 1;
		tab[2][2] = 0;
		tab[2][3] = 5;
		tab[2][4] = 1;
		tab[3][0] = 1;
		tab[3][1] = 1;
		tab[3][2] = 1;
		tab[3][3] = 1;
		tab[3][4] = 5;
		tab[4][0] = 0;
		tab[4][1] = 1;
		tab[4][2] = 4;
		tab[4][3] = 1;
		tab[4][4] = 1;
		Resultats res = new Resultats(tab); //On génère le tableau
		HashMap<String, Integer>[] sortie = res.bestSolution(); //On recupere la meilleure solution
		*/
		
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
