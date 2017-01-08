package projet_marco;

import java.util.*;

public class Resultats {
	private int points = 10; // Les points accordés à chaque groupe
	private int groupes = 5; // le nombre de groupes
	private int projets = 7; // le nombre de projets
	private float[][] tableau; // le tableau des resultats
	String permutations = ""; // les permutations possibles
	private float[] maxGrp; // le tableau des points max par groupe
	private float[] maxProj; // le tableau des points max par projet
	private long start = 0;
	private boolean stop_if_too_long = false;

	private void initialize(boolean stop_if_too_long) {
		this.stop_if_too_long = stop_if_too_long;
		String temp = "";
		float rand;
		float maxG;
		float maxP;
		this.maxGrp = new float[this.groupes];
		this.maxProj = new float[this.projets];
		for (int i = 0; i < this.groupes; i++) {
			maxG = 0;
			for (int j = 0; j < this.projets; j++) {
				if (this.tableau[j][i] > maxG)
					maxG = this.tableau[j][i];
			}
			this.maxGrp[i] = maxG; // On remplit le tableau des max pour chaque
									// groupe
		}
		for (int i = 0; i < this.projets; i++) {
			maxP = 0;
			for (int j = 0; j < this.groupes; j++) {
				if (this.tableau[i][j] > maxP)
					maxP = this.tableau[i][j];
			}
			this.maxProj[i] = maxP; // On remplit le tableau des max pour chaque
									// projet
		}
		// On réduit les données afin d'avoir plus de précision pour la création
		// des permutations
		for (int i = 0; i < this.groupes; i++) {
			for (int j = 0; j < projets; j++) {
				tableau[j][i] /= maxGrp[i];

				if (tableau[j][i] != 1 && tableau[j][i] != 0) {
					rand = (float) (Math.random() / 100 - 0.005);
					if (rand < 0)
						rand -= 0.01;
					else
						rand += 0.01;
					tableau[j][i] += rand; // On rajoute une valeur aléatoire,
											// entre -0.01 et -0.015, ou 0.01 et
											// 0.015, afin d'essayer d'éviter le
											// pic du nombre de valeurs lors de
											// la génération des permutations
				}
			}
		}
		for (char i = 'A'; i < (char) (projets + 65); i++) {
			temp += i; // On crée la chaine de caractères pour trouver les
						// permutations
		}
		float i = 0F;
		float j = 0F;

		float max = 0;

		for (int k = 0; k < this.maxGrp.length; k++)
			if (this.maxGrp[k] > max)
				max = this.maxGrp[k];

		// On va chercher à créer toutes les permutations possibles sur la
		// chaine de caractères (= toutes les répartitions possibles)
		while (this.permutations.length() == 0) {
			// System.out.println("On regarde a max - " + i + " avec un décalage
			// max sur le max du projet de " + j);

			// On initialise le temps, si on ne trouve pas de valeur dans le
			// temps imparti on recommence
			this.start = System.currentTimeMillis();

			this.permutation(temp, i, j);
			// Si on ne trouve pas de permutation avec que les max, on diminue
			// un peu les valeurs et on recommence
			if (j > max) {
				i += 0.001;
				j = 0;
			}
			j += 1;
		}
	}

	public Resultats(boolean stop_if_too_long) {
		this.tableau = new float[this.projets][this.groupes]; // On crée le
																// tableau
		for (int i = 0; i < this.groupes; i++) {
			for (int j = 0; j < points; j++) {
				tableau[new Random().nextInt(this.projets)][i]++; // On remplit
				// le
				// tableau
				// de façon
				// aléatoire
			}
		}
		this.initialize(stop_if_too_long);
	}

	/**
	 * Le constructeur avec paramètres
	 * 
	 * @param p
	 *            les points pour chaque groupe
	 * @param g
	 *            le nombre de groupes
	 * @param pro
	 *            le nombre de projets
	 */
	public Resultats(int p, int g, int pro, boolean stop_if_too_long) {
		// Si on a moins de projets que de groupes, ça ne va pas : on définit le
		// nombre de projets égal au nombre de groupes
		if (pro >= g)
			this.projets = pro;
		else
			this.projets = g;
		this.groupes = g;
		this.points = p;
		this.tableau = new float[this.projets][this.groupes];
		for (int i = 0; i < this.groupes; i++) {
			for (int j = 0; j < points; j++) {
				tableau[new Random().nextInt(this.projets)][i]++; // On remplit
				// le
				// tableau
				// avec des
				// valeurs
				// aléatoires
			}
		}
		this.initialize(stop_if_too_long);
	}

	public Resultats(float[][] tab, boolean stop_if_too_long) {
		this.projets = tab.length;
		this.groupes = tab[0].length;
		this.points = 0;
		for (int i = 0; i < tab[0].length; i++)
			this.points += tab[0][i];
		this.tableau = tab;
		this.initialize(stop_if_too_long);
	}

	/**
	 * affiche le tableau
	 */
	public String toString() {
		String resultat = "  |";
		for (int i = 0; i < this.groupes; i++) {
			resultat += (char) (i + 65) + " |";
		}

		resultat += "\n";
		for (int i = 0; i <= this.groupes; i++) {
			resultat += "--+";
		}

		for (int i = 0; i < this.projets; i++) {
			resultat += "\n" + (i + 1);
			if ((i + 1) / 10 == 0)
				resultat += " ";
			resultat += "|";
			for (int j = 0; j < this.groupes; j++) {
				resultat += Math.round(tableau[i][j] * this.maxGrp[j]);
				if (Math.round(tableau[i][j] * this.maxGrp[j]) / 10 == 0) {
					resultat += " ";
				}
				resultat += "|";
			}
		}

		return resultat;
	}

	public void permutation(String str, float a, float b) {
		permutation("", str, a, b);
	}

	/**
	 * cherche toutes les permutations, et donc les répartitions, possibles
	 * 
	 * @param prefix
	 * @param str
	 * @param a
	 *            la précision sur les max des groupes
	 * @param b
	 *            la precision sur les max des projets
	 */
	public void permutation(String prefix, String str, float a, float b) {
		if (this.stop_if_too_long && System.currentTimeMillis() - this.start > 100) { // Si
																						// le
																						// calcul
																						// des
			// permutations
			// prend trop de
			// temps, on arrete.
			return;
		}
		int n = str.length();
		if (n == this.projets - this.groupes) {
			this.permutations += prefix + ", ";
		} else {
			for (int i = 0; i < n; i++) {
				// On ne va a l'etape suivante que si on regarde un max - la
				// précision
				if (this.tableau[(int) (str.charAt(i) - 65)][prefix.length()] >= 1 - a
						&& this.tableau[(int) (str.charAt(i) - 65)][prefix.length()]
								* this.maxGrp[prefix.length()] >= this.maxProj[(int) (str.charAt(i) - 65)] - b)
					permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), a, b);
			}
		}
	}

	public List<HashMap<String, Integer>> bestSolution() {
		HashMap<String, Integer> tempMap = new HashMap<String, Integer>();
		float erreurMax = 10000;
		float erreur = 0;
		String temp = "";
		List<HashMap<String, Integer>> tempList = new ArrayList<HashMap<String, Integer>>();

		// On transforme la chaine de caracteres contenant les permutations en
		// tableau
		List<String> items = Arrays.asList(this.permutations.split("\\s*,\\s*"));

		for (int i = 0; i < items.size(); i++) { // On regarde chaque
													// permutation
			if (items.get(i).length() == this.groupes) { // Si on trouve une
															// permutation qui a
															// autant de données
															// que de groupes,
															// on va regarder si
															// son erreur est
															// meilleure que la
															// précédente
				tempMap = new HashMap<String, Integer>();
				erreur = 0;
				for (int j = 0; j < this.groupes; j++) {
					temp = "" + (char) (j + 65);
					tempMap.put(temp, (int) (items.get(i).charAt(j) - 65) + 1); // On
																				// rentre
																				// dans
																				// une
																				// map
																				// le
																				// numero
																				// du
																				// groupe
																				// et
																				// le
																				// projet
																				// correspondant
				}
				for (int j = 0; j < this.groupes; j++) { // On calcule la somme
															// des erreurs
					erreur += (this.maxGrp[j]
							- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]));
				}
				if (erreur < erreurMax) {
					erreurMax = erreur;
				}
			}
		}

		for (int i = 0; i < items.size(); i++) { // On regarde chaque
			// permutation
			if (items.get(i).length() == this.groupes) { // Si on trouve une
				// permutation qui a
				// autant de données
				// que de groupes,
				// on va regarder si
				// son erreur est
				// meilleure que la
				// précédente
				tempMap = new HashMap<String, Integer>();
				erreur = 0;
				for (int j = 0; j < this.groupes; j++) {
					temp = "" + (char) (j + 65);
					tempMap.put(temp, (int) (items.get(i).charAt(j) - 65) + 1); // On
																				// rentre
																				// dans
																				// une
																				// map
																				// le
																				// numero
																				// du
																				// groupe
																				// et
																				// le
																				// projet
																				// correspondant
				}
				for (int j = 0; j < this.groupes; j++) { // On calcule la somme
															// des erreurs
					erreur += (this.maxGrp[j]
							- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]));
				}
				if (erreur == erreurMax || this.stop_if_too_long) {
					tempList.add(tempMap);
				}
			}
		}

		/*
		 * // On affiche les erreurs System.out.println("erreur : " +
		 * erreurMax);
		 */

		return tempList; // On renvoie les maps correspondant aux plus petites
							// sommes d'erreurs
	}

	private HashMap<String, Integer> improveOne(HashMap<String, Integer> sol) {

		float erreur = 0;

		HashMap<String, Integer> tempMap = sol, initialMap = (HashMap<String, Integer>) sol.clone();
		int temp;

		for (String i : initialMap.keySet()) {
			for (String j : initialMap.keySet()) {
				if (i != j && i.charAt(0) <= (char) (this.groupes - 1 + 65)) {
					if (j.charAt(0) <= (char) (this.groupes - 1 + 65)) {
						if ((this.tableau[tempMap.get(j) - 1][((int) i.charAt(0)) - 65]
								- this.tableau[tempMap.get(i) - 1][((int) i.charAt(0))
										- 65]) > (this.tableau[tempMap.get(j) - 1][((int) j.charAt(0)) - 65]
												- this.tableau[tempMap.get(i) - 1][((int) j.charAt(0)) - 65])) {
							temp = tempMap.get(i);
							tempMap.remove(i);
							tempMap.put(i, tempMap.get(j));
							tempMap.remove(j);
							tempMap.put(j, temp);
						}
					} else {
						if (this.tableau[tempMap.get(i) - 1][((int) i.charAt(0))
								- 65] < this.tableau[tempMap.get(j) - 1][((int) i.charAt(0)) - 65]) {
							temp = tempMap.get(i);
							tempMap.remove(i);
							tempMap.put(i, tempMap.get(j));
							tempMap.remove(j);
							tempMap.put(j, temp);
						}
					}
				}
			}
		}

		return tempMap;
	}

	public HashMap<String, Integer> improve() {
		HashMap<String, Integer> firstSol = new HashMap<String, Integer>();
		List<HashMap<String, Integer>> allSolutions = this.bestSolution();
		firstSol = allSolutions.get(0);
		float erreur = 0;

		HashMap<String, Integer> tempMap = firstSol, result = null;
		int temp;
		float errMax = 999999;
		
		for (int k = 0; k < allSolutions.size(); k++) {
			erreur = 0;
			tempMap = allSolutions.get(k);

			tempMap = improveOne(tempMap);

			for (int j = 0; j < this.groupes; j++) { // On calcule la somme
				// des erreurs
				erreur += (this.maxGrp[j]
						- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]));
			}
			if (erreur < errMax) {
				errMax = erreur;
				result = tempMap;
			}
		}
		
		System.out.println("erreur : " + errMax);
		return result;
	}
}