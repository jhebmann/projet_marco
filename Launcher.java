package projet_marco;

import java.util.*;

public class Launcher {
	public static void main(String[] args) {
		Resultats res = new Resultats();
		//System.out.println(res.permutations);
		HashMap<String, Integer> sortie = res.bestSolution();
		for(String i : sortie.keySet()){
			System.out.print(i + " : " + sortie.get(i) + "\t");
		}
		System.out.println("\n" + res);
	}
}
