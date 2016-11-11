package projet_marco;

import java.util.*;

public class Resultats {
	private int points = 10; // Les points accordés à chaque groupe
	private int groupes = 5; // le nombre de groupes
	private int projets = 7; // le nombre de projets
	// private ArrayList<ArrayList<Integer>> tableau = new
	// ArrayList<ArrayList<Integer>>();
	private float[][] tableau; // le tableau des resultats
	String permutations = ""; // les permutataions possibles
	private float[] maxGrp; // le tableau des points max par groupe
	private float[] maxProj; // le tableau des points max par projet

	/**
	 * Le constructeur sans paramètre, il n'est pas à jour, il faut regarder
	 * celui d'après
	 */
	public Resultats() {
		String temp = "";
		this.tableau = new float[this.projets][this.groupes]; // On crée le
																// tableau
		float maxG;
		this.maxGrp = new float[this.groupes]; // On crée le tableau des points
												// max des groupes
		for (int i = 0; i < this.groupes; i++) {
			for (int j = 0; j < points; j++) {
				tableau[new Random().nextInt(this.projets)][i]++; // On remplit
																	// le
																	// tableau
																	// de façon
																	// aléatoire
			}
		}
		for (int i = 0; i < this.groupes; i++) {
			maxG = 0;
			for (int j = 0; j < this.projets; j++) {
				if (this.tableau[j][i] > maxG)
					maxG = this.tableau[j][i];
			}
			this.maxGrp[i] = maxG; // On remplit le tableau des resultats max
									// pour chaque groupe
		}
		for (char i = 'A'; i < (char) (this.projets + 65); i++) {
			temp += i; // On crée la chaîne de caractères pour trouver toutes
						// les permutations
		}
		System.out.println(this.tableau); // On affiche le tableau
		int i = 0;
		// On va essayer de créer les permutations en ne regardant que le max
		// pour chaque groupe et projet, puis que le max-1, ...
		while (this.permutations.length() == 0) {
			System.out.println("On regarde a max - " + i);
			// this.permutation(temp, i);
			i++;
		}
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
	public Resultats(int p, int g, int pro) {
		String temp = "";
		// Si on a moins de projets que de groupes, ça ne va pas : on définit le
		// nombre de projet égal au nombre de groupes
		if (pro >= g)
			this.projets = pro;
		else
			this.projets = g;
		this.groupes = g;
		this.points = p;
		this.tableau = new float[this.projets][this.groupes];
		float rand;
		float maxG;
		float maxP;
		this.maxGrp = new float[this.groupes];
		this.maxProj = new float[this.projets];
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
		for (int i = 0; i < this.groupes; i++) {
			maxG = 0;
			for (int j = 0; j < this.projets; j++) {
				if (this.tableau[j][i] > maxG)
					maxG = this.tableau[j][i];
			}
			this.maxGrp[i] = maxG; // On remplit le tableau des max pour chaque
									// groupe
		}
		// On réduit les données afin d'avoir plus de précision pour la création
		// des permutations
		for (int i = 0; i < this.groupes; i++) {
			for (int j = 0; j < projets; j++) {
				tableau[j][i] /= maxGrp[i];
				if (tableau[j][i] != 1) {
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
		for (int i = 0; i < this.projets; i++) {
			maxP = 0;
			for (int j = 0; j < this.groupes; j++) {
				if (this.tableau[i][j] > maxP)
					maxP = this.tableau[i][j];
			}
			this.maxProj[i] = maxP; // On remplit le tableau des max pour chaque
									// projet
		}
		// for (int i = 0; i < this.groupes; i++) {
		// maxG = 0;
		// for (int j = 0; j < this.projets; j++) {
		// if (this.tableau[j][i] > maxG)
		// maxG = this.tableau[j][i];
		// }
		// this.maxGrp[i] = maxG;
		// }
		for (char i = 'A'; i < (char) (projets + 65); i++) {
			temp += i; // On crée la chaine de caractères pour trouver les
						// permutations
		}
		// System.out.println(this);
		float i = 0F;
		float j = 0F;
		// On va chercher à créer toutes les permutations possibles sur la
		// chaine de caractères (= toutes les répartitions possibles)
		while (this.permutations.length() == 0) {
			System.out.println("On regarde a max - " + i);

			System.out.println(this);
			this.permutation(temp, i, j);
			// Si on ne trouve pas de permutation avec que les max, on diminue
			// un peu les valeurs et on recommence
			i += 0.001;
			j += 0.001;
		}
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
		int n = str.length();
		if (n == this.projets - this.groupes) {
			this.permutations += prefix + ", ";
		} else {
			for (int i = 0; i < n; i++) {
				// On ne va a l'etape suivante que si on regarde un max - la
				// précision
				if (this.tableau[(int) (str.charAt(i) - 65)][prefix.length()] >= 1 - a
						&& this.tableau[(int) (str.charAt(i) - 65)][prefix
								.length()] >= this.maxProj[(int) (str.charAt(i) - 65)]
										- b * this.maxProj[(int) (str.charAt(i) - 65)])
					permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), a, b);
			}
		}
	}

	public HashMap<String, Integer>[] bestSolution() {
		HashMap<String, Integer> resultat = new HashMap<String, Integer>(), resultat2 = new HashMap<String, Integer>(),
				resultat3 = new HashMap<String, Integer>(), tempMap = new HashMap<String, Integer>();
		float erreurMax = 10000, erreurMax2 = 10000, erreurMax3 = 10000;
		float erreur = 0, erreur2 = 0, erreur3 = 0;
		String temp = "";
		HashMap<String, Integer>[] test = new HashMap[3];

		// On transforme la chaine de caracteres contenant les permutations en
		// tableau
		List<String> items = Arrays.asList(this.permutations.split("\\s*,\\s*"));
		float startTime = System.currentTimeMillis();
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
				erreur2 = 0;
				erreur3 = 0;
				for (int j = 0; j < this.groupes; j++) {
					temp = "" + (char) (j + 65);
					// System.out.println(temp + " " +
					// Character.getNumericValue(items.get(i).charAt(j)));
					// System.out.println((int)items.get(i).charAt(j)-65);
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
															// des erreurs, des
															// erreurs carrées,
															// et des erreurs
															// cube
					// erreur += (1 - tableau[tempMap.get("" + (char) (j + 65))
					// - 1][j]);
					// erreur2 += (1 - tableau[tempMap.get("" + (char) (j + 65))
					// - 1][j])
					// * (1 - tableau[tempMap.get("" + (char) (j + 65)) -
					// 1][j]);
					// erreur3 += (1 - tableau[tempMap.get("" + (char) (j + 65))
					// - 1][j])
					// * (1 - tableau[tempMap.get("" + (char) (j + 65)) - 1][j])
					// * (1 - tableau[tempMap.get("" + (char) (j + 65)) -
					// 1][j]);
					erreur += (this.maxGrp[j]
							- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]));
					erreur2 += (this.maxGrp[j]
							- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]))
							* (this.maxGrp[j]
									- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]));
					erreur3 += (this.maxGrp[j]
							- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]))
							* (this.maxGrp[j]
									- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]))
							* (this.maxGrp[j]
									- Math.round(tableau[tempMap.get("" + (char) (j + 65)) - 1][j] * this.maxGrp[j]));
					// System.out.println((char)(j+65) + " " +
					// tempMap.get(""+(char)(j+65)));
				}
				if (erreur < erreurMax) {
					erreurMax = erreur;
					resultat = tempMap;
				}
				if (erreur2 < erreurMax2) {
					erreurMax2 = erreur2;
					resultat2 = tempMap;
				}
				if (erreur3 < erreurMax3) {
					erreurMax3 = erreur3;
					resultat3 = tempMap;
				}
			}
		}

		float stopTime = System.currentTimeMillis();
		float elapsedTime = stopTime - startTime;
		System.out.println("temps : " + elapsedTime + " millisecondes");

		// On affiche les erreurs
		System.out
				.println("erreur : " + erreurMax + "\nerreur carree : " + erreurMax2 + "\nerreur cube : " + erreurMax3);
		// if (resultat2 == resultat){
		// System.out.println("ils sont egaux youpi");
		// }
		test[0] = resultat;
		test[1] = resultat2;
		test[2] = resultat3;
		return test; // On renvoie les maps correspondant aux plus petites
						// sommes d'erreurs, ...
	}
}