package projet_marco;

import java.util.*;

public class Resultats {
	private int points = 10;
	private int groupes = 5;
	private int projets = 7;
	//private ArrayList<ArrayList<Integer>> tableau = new ArrayList<ArrayList<Integer>>();
	private int[][] tableau;
	String permutations = "";
	
	public Resultats(){
		String temp = "";
		this.tableau = new int[projets][groupes];
		for(int i = 0 ; i < groupes ; i++){
			for(int j = 0 ; j < points ; j++){
				tableau[new Random().nextInt(projets)][i] ++;
			}
		}
		for(int i = 1 ; i<=projets ; i++){
			temp+=i;
		}
		this.permutation(temp);
	}
	
	public Resultats(int p, int g, int pro){
		this.points = p;
		this.groupes = g;
		this.projets = pro;
		this.tableau = new int[projets][groupes];
		for(int i = 0 ; i < groupes ; i++){
			for(int j = 0 ; j < points ; j++){
				tableau[new Random().nextInt(projets)][i] ++;
			}
		}
	}
	
	public String toString(){
		String resultat = "  |";
		for(int i = 0 ; i < this.groupes ; i++){
			resultat+=(char)(i+65) + " |";
		}
		
		resultat+="\n";
		for(int i = 0 ; i <= this.groupes ; i++){
			resultat+="--+";
		}
		
		for(int i = 0 ; i < this.projets ; i++){
			resultat+="\n" + (i+1);
			if ((i+1)/10 == 0)
				resultat+=" ";
			resultat+="|";
			for(int j = 0 ; j < this.groupes ; j++){
				resultat+=tableau[i][j];
				if (tableau[i][j]/10 == 0){
					resultat+=" ";
				}
				resultat+="|";
			}
		}
		
		return resultat;
	}
	
	private void permutation(String str) { 
	    permutation("", str); 
	}

	private void permutation(String prefix, String str) {
	    int n = str.length();
	    if (n == 0){
	    	this.permutations+=prefix + ", ";
	    }
	    else {
	        for (int i = 0; i < n; i++)
	            permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	    }
	}
	
	public HashMap<String, Integer> bestSolution(){
		HashMap<String, Integer> resultat = new HashMap<String, Integer>(), tempMap = new HashMap<String, Integer>();
		int erreurMax = 10000;
		int erreur = 0;
		int maxG = 0;
		String temp = "";
		
		List<String> items = Arrays.asList(this.permutations.split("\\s*,\\s*"));
		for(int i = 0 ; i < items.size() ; i++){
			tempMap = new HashMap<String, Integer>();
			erreur = 0;
			for(int j = 0 ; j < this.groupes ; j++){
				temp = "" + (char)(j+65);
				//System.out.println(temp + " " + Character.getNumericValue(items.get(i).charAt(j)));
				tempMap.put(temp, Character.getNumericValue(items.get(i).charAt(j)));
			}
			for(int j = 0 ; j < this.groupes ; j++){
				maxG = 0;
				for(int k = 0 ; k < this.projets ; k++){
					if (this.tableau[k][j] > maxG){
						maxG = this.tableau[k][j];
					}
				}
				erreur+=(maxG-tableau[tempMap.get(""+(char)(j+65))-1][j]);
				//System.out.println((char)(j+65) + " " + tempMap.get(""+(char)(j+65)));
			}
			if (erreur < erreurMax){
				erreurMax = erreur;
				resultat = tempMap;
			}
		}
		System.out.println("erreur : " + erreurMax);
		return resultat;
	}
}