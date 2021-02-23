package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class App {
	public static void main(String[] args) {
		Scanner channelDeNom1 = new Scanner(System.in);//pour récupérer le premier nom
		Scanner channelDeNom2 = new Scanner(System.in);//pour récupérer le deuxiÃ¨me nom
		String nomJoueur1 = null;
		String nomJoueur2 = null;
		do {
			System.out.println("Veuillez saisir votre le prénom du 1er joueur : ");//on demande le nom
			nomJoueur1 = channelDeNom1.next();
		}while(nomJoueur1.isEmpty() && nomJoueur1.matches("^[ ]+$"));//on vérifie que c'est pas vide ou que des espaces
		do {
			System.out.println("Veuillez saisir votre le prénom du 2Ã¨me joueur :");
			nomJoueur2 = channelDeNom2.next();
		}while(nomJoueur2.isEmpty() && nomJoueur2.matches("^[ ]+$"));//on vérifie que c'est pas vide ou que des espaces
		Joueur joueur1 = new Joueur(nomJoueur1); // premier joueur
		Joueur joueur2 = new Joueur(nomJoueur2);// deuxiÃ¨me joueur
		System.out.println("###########################################################");
		System.out.println("# Bienvenue : "+nomJoueur1+" et "+nomJoueur2+" que la force soit avec vousgfn");
		System.out.println("###########################################################");
		Carreaux carreaux = new Carreaux(); // instanciation de l'ensemble des carreaux
		carreaux.createAll(carreaux); // creer les carreaux et les inserer pour les garder
		PaquetDeCartes paquet = new PaquetDeCartes(); // creation du paquet
		Carreaux cartesNeutres = new Carreaux();// creation du conteneur des cartes neutres
		cartesNeutres.createCartesNeutres(cartesNeutres);// creation des cartes neutres dans le conteneur
		StringBuffer mur = Interface.createBufferToStrat(); // créer le mur du début
		mur = Interface.addNeutre(mur, carreaux.tirageCarreauNeutre(cartesNeutres)); // rajouter une carte neutre aléatoire de faÃ§on aléatoire
		String decision = ""; // variable pour la décision des joueurs
		PaquetDeCartes paquetParticulier = new PaquetDeCartes("pour Carte Vide");
		int i = 0; // pour différencier entre les deux joueurs
		int score=0;//le score de la partie
		while (!carreaux.plusDeCarreaux() && !paquet.paquetVide()&& !decision.equals("stop")) {/* tant que la décision est différente de stop qu'il y a encore des
																								  cartes et il y a encore des carreaux*/
			if (i % 2 == 0) {// si i est pair c'est au tour du premier joueur
				System.out.println("C'est au tour de " + joueur1.getNom() + " de jouer");// on affiche le nom du premier joueur
			} else { // si i est impair c'est le tour du deuxiÃ¨me joueur
				System.out.println("C'est au tour de " + joueur2.getNom() + " de jouer");// on affiche le nom du duxiÃ¨mejoueur
			}
			Interface.afficheInterface(mur); // afficher le mur aux joueurs
			Carte cartetiree = paquet.tirageCarte();// on tire une carte du paquet
			System.out.println();
			System.out.println("La carte tiré est une " + cartetiree.toString());// on affiche la carte tirée
			ArrayList<Carreau> carreauxSelonCarte = carreaux.recupereCarreauxSelonCarte(cartetiree);// on récupÃ¨re les carreaux correspondants Ã  cette carte
			boolean carreauExist = true;
			if(carreauxSelonCarte.isEmpty()) {
				System.out.println("Aucun carreau ne satisfait cette carte");
				carreauExist=false;
			} else {
				Interface.afficheCarreau(carreauxSelonCarte);// on affiche les carreaux aux joueurs
			}
			if(carreauExist) {
				String choixJoueur = null;
				Scanner choixJoueurchannel = new Scanner(System.in);
				boolean choixExist = false;
				do {
					System.out.println();
					System.out.println("\nvoici les carreaux disponibles, veuillez séléctionner une option parmis les trois suivantes");
					System.out.println("1) stop  2) next 3) post");
					choixJoueur = choixJoueurchannel.next();
					choixJoueur = choixJoueur.toUpperCase();
					if (choixJoueur.equals("STOP") || choixJoueur.equals("NEXT") || choixJoueur.equals("POST")) {
						choixExist = true;
					}else {
						System.out.println("Veuillez choisir une option parmis les 3 ");
					}
				} while (!choixExist);
				if (choixJoueur.equals("STOP")) {
					decision = "stop";
					break;
				} else if (choixJoueur.equals("NEXT")) {
					paquetParticulier.addCarte(cartetiree);
					paquet.retirerCarte(cartetiree);
					System.out.println("taille"+ paquetParticulier.taille());
				} else {
					boolean repeter = false;// notre condition d'arrÃªt du choix du carreau
					String reponseCarreau = null;
					Scanner channelDeCommunication = new Scanner(System.in);// channel pour redériger et récupérer l'entré
																			// clavier
					Carreau carreauChoisi = null;
					String[] responsecarreauTable = null;
					boolean insererEstBon = false;
					do {
						boolean entreJuste = true;
						System.out.println("\nVeuillez choisir un carreau, une colonne et une ligne séparés par un espace");// on invite le joueur Ã  choisir un carreau
						reponseCarreau = channelDeCommunication.nextLine(); // on récupÃ¨re la réponse du joueur
						try {
							responsecarreauTable = reponseCarreau.split(" ");
							if (responsecarreauTable.length == 3) {
								try {
									Integer.parseInt(responsecarreauTable[1]);
									Integer.parseInt(responsecarreauTable[2]);
								} catch (NumberFormatException error) {
									System.out.println("Assurez vous que l'abscisse et l'ordonnée entrés soient des chiffres");
									entreJuste = false;
								}
							} else {
								System.out.println(
										"Assurez vous d'avoir mis tous les arguments et d'avoir mis des espaces chacun des arguments");
								entreJuste = false;
							}
							if (entreJuste) {
								if (Carreaux.carreauMatchesUserChoice(responsecarreauTable[0], carreauxSelonCarte)) { // on verifie si le carreau choisi existe dans la liste des carreaux proposés 
									repeter = true;// on arrÃªte de boucler quand le carreau existe
									carreauChoisi = Carreaux.recupererCarreauSelonChoix(reponseCarreau, carreauxSelonCarte); // on
																																// récupÃ¨re
																																// le
																																// carreau
								} else {
									System.out.println("Veuillez vérifier votre choix de carreau");// on redemande au joueur
																									// de choisir le carreau
								}
							}
						} catch (PatternSyntaxException error) {
							System.out.println("Assurez vous d'avoir séparé les choix par un espace");
						}
						if (carreauChoisi != null) {// si on a récupÃ¨rer un carreau
							
							if (responsecarreauTable.length>1&&AcceptCarreauTest.valideTouslesTeste(carreauChoisi,Integer.parseInt(responsecarreauTable[1]), Integer.parseInt(responsecarreauTable[2]),mur)) {// Si le carreau valide tous les testes
								Interface.addBlockToBuffer(mur, carreauChoisi,Integer.parseInt(responsecarreauTable[1]),  Integer.parseInt(responsecarreauTable[2]));//on l'ajoute
								carreaux.effaceCarreau(carreauChoisi);
								paquet.retirerCarte(cartetiree);
								insererEstBon = true;
								carreauChoisi=null;
							} else {
								System.out.println("Votre choix n'est pas valide");
							}
						} 			
					}while(!repeter || !insererEstBon ); // on continue tant que on a pas un carreau qui correspond
					i++;
				}
			}
		}//le jeu est fini 
		score=Interface.ScoreMur(mur)+paquetParticulier.CarteDeScore()+carreaux.carreauxDeScore();
		int niveau = Interface.ScoreMur(mur)/5;
		int carreauNonpose = Math.abs(carreaux.carreauxDeScore());
		System.out.print("Votre score est le suivant : "+ score+" points");
		System.out.print(" ("+Interface.ScoreMur(mur)/5+" niveaux complets,");
		System.out.print(" "+Math.abs(carreaux.carreauxDeScore())+" carreaux non posés,");
		System.out.print(" "+Math.abs(paquetParticulier.CarteDeScore())+" cartes écartées)");
	}
}
