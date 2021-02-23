package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class App {
	public static void main(String[] args) {
		Scanner channelDeNom1 = new Scanner(System.in);//pour r�cup�rer le premier nom
		Scanner channelDeNom2 = new Scanner(System.in);//pour r�cup�rer le deuxième nom
		String nomJoueur1 = null;
		String nomJoueur2 = null;
		do {
			System.out.println("Veuillez saisir votre le pr�nom du 1er joueur : ");//on demande le nom
			nomJoueur1 = channelDeNom1.next();
		}while(nomJoueur1.isEmpty() && nomJoueur1.matches("^[ ]+$"));//on v�rifie que c'est pas vide ou que des espaces
		do {
			System.out.println("Veuillez saisir votre le pr�nom du 2ème joueur :");
			nomJoueur2 = channelDeNom2.next();
		}while(nomJoueur2.isEmpty() && nomJoueur2.matches("^[ ]+$"));//on v�rifie que c'est pas vide ou que des espaces
		Joueur joueur1 = new Joueur(nomJoueur1); // premier joueur
		Joueur joueur2 = new Joueur(nomJoueur2);// deuxième joueur
		System.out.println("###########################################################");
		System.out.println("# Bienvenue : "+nomJoueur1+" et "+nomJoueur2+" que la force soit avec vousgfn");
		System.out.println("###########################################################");
		Carreaux carreaux = new Carreaux(); // instanciation de l'ensemble des carreaux
		carreaux.createAll(carreaux); // creer les carreaux et les inserer pour les garder
		PaquetDeCartes paquet = new PaquetDeCartes(); // creation du paquet
		Carreaux cartesNeutres = new Carreaux();// creation du conteneur des cartes neutres
		cartesNeutres.createCartesNeutres(cartesNeutres);// creation des cartes neutres dans le conteneur
		StringBuffer mur = Interface.createBufferToStrat(); // cr�er le mur du d�but
		mur = Interface.addNeutre(mur, carreaux.tirageCarreauNeutre(cartesNeutres)); // rajouter une carte neutre al�atoire de façon al�atoire
		String decision = ""; // variable pour la d�cision des joueurs
		PaquetDeCartes paquetParticulier = new PaquetDeCartes("pour Carte Vide");
		int i = 0; // pour diff�rencier entre les deux joueurs
		int score=0;//le score de la partie
		while (!carreaux.plusDeCarreaux() && !paquet.paquetVide()&& !decision.equals("stop")) {/* tant que la d�cision est diff�rente de stop qu'il y a encore des
																								  cartes et il y a encore des carreaux*/
			if (i % 2 == 0) {// si i est pair c'est au tour du premier joueur
				System.out.println("C'est au tour de " + joueur1.getNom() + " de jouer");// on affiche le nom du premier joueur
			} else { // si i est impair c'est le tour du deuxième joueur
				System.out.println("C'est au tour de " + joueur2.getNom() + " de jouer");// on affiche le nom du duxièmejoueur
			}
			Interface.afficheInterface(mur); // afficher le mur aux joueurs
			Carte cartetiree = paquet.tirageCarte();// on tire une carte du paquet
			System.out.println();
			System.out.println("La carte tir� est une " + cartetiree.toString());// on affiche la carte tir�e
			ArrayList<Carreau> carreauxSelonCarte = carreaux.recupereCarreauxSelonCarte(cartetiree);// on r�cupère les carreaux correspondants à cette carte
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
					System.out.println("\nvoici les carreaux disponibles, veuillez s�l�ctionner une option parmis les trois suivantes");
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
					boolean repeter = false;// notre condition d'arrêt du choix du carreau
					String reponseCarreau = null;
					Scanner channelDeCommunication = new Scanner(System.in);// channel pour red�riger et r�cup�rer l'entr�
																			// clavier
					Carreau carreauChoisi = null;
					String[] responsecarreauTable = null;
					boolean insererEstBon = false;
					do {
						boolean entreJuste = true;
						System.out.println("\nVeuillez choisir un carreau, une colonne et une ligne s�par�s par un espace");// on invite le joueur à choisir un carreau
						reponseCarreau = channelDeCommunication.nextLine(); // on r�cupère la r�ponse du joueur
						try {
							responsecarreauTable = reponseCarreau.split(" ");
							if (responsecarreauTable.length == 3) {
								try {
									Integer.parseInt(responsecarreauTable[1]);
									Integer.parseInt(responsecarreauTable[2]);
								} catch (NumberFormatException error) {
									System.out.println("Assurez vous que l'abscisse et l'ordonn�e entr�s soient des chiffres");
									entreJuste = false;
								}
							} else {
								System.out.println(
										"Assurez vous d'avoir mis tous les arguments et d'avoir mis des espaces chacun des arguments");
								entreJuste = false;
							}
							if (entreJuste) {
								if (Carreaux.carreauMatchesUserChoice(responsecarreauTable[0], carreauxSelonCarte)) { // on verifie si le carreau choisi existe dans la liste des carreaux propos�s 
									repeter = true;// on arrête de boucler quand le carreau existe
									carreauChoisi = Carreaux.recupererCarreauSelonChoix(reponseCarreau, carreauxSelonCarte); // on
																																// r�cupère
																																// le
																																// carreau
								} else {
									System.out.println("Veuillez v�rifier votre choix de carreau");// on redemande au joueur
																									// de choisir le carreau
								}
							}
						} catch (PatternSyntaxException error) {
							System.out.println("Assurez vous d'avoir s�par� les choix par un espace");
						}
						if (carreauChoisi != null) {// si on a r�cupèrer un carreau
							
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
		System.out.print(" "+Math.abs(carreaux.carreauxDeScore())+" carreaux non pos�s,");
		System.out.print(" "+Math.abs(paquetParticulier.CarteDeScore())+" cartes �cart�es)");
	}
}
