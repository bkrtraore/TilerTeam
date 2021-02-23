package main;

import java.util.ArrayList;
/**
 * classe conteneur de tous les carreaux
 */
import java.util.Iterator;
import java.util.Random;
/**
 * @classe conteneur de tous les carreaux
 * @param carreaux une arrayList qui va contenir les carreaux
 */
public class Carreaux {
	private ArrayList<Carreau> carreaux;
	/**
	 * constructeur de la classe
	 */
	public Carreaux() {
		this.carreaux = new ArrayList<Carreau>();
	}
	/**
	 * @method créer tous les carreaux
	 * @param carreaux
	 */
	public void createAll(Carreaux carreaux) {
		int hauteur = 1, largeur = 1;//On defini la largeur et la hauteur à 1
		char lettreMin = 97;//code ascii de a miniscule
		char lettreMax = 65; //code ascii de A majuscule
		for (int i = 0; i < 9; i++) {//on boucle sur le nombre de carreaux
			carreaux.addCarreau(new Carreau(hauteur, largeur, lettreMin));//on ajoute le carreau avec la lettre en miniscule
			carreaux.addCarreau(new Carreau(hauteur, largeur, lettreMax));//on l'ajoute avec la lettre en majuscule
			if (largeur < 3)//si la largeur est inférieur a 3, on peut encore créer des carreau avec la même hauteur et une largeur plus grande
				largeur++;//on augmente la largeur exp on passe de (1,1) à (1,2)
			else {//sinon on reset la largeur et on augment la hauteur exp on passe de (1,3) à (2,1)
				hauteur++;//augmenter la hauteur
				largeur = 1;//reset la largeur
			}
			lettreMax++;//on incrémente notre code ascii pour passer à la lettre prochaine
			lettreMin++;//on incrémente notre code ascii pour passer à la lettre prochaine
		}
	}
	/**
	 * @method on ajoute un carreau à la liste 
	 * @param carreau
	 */
	public void addCarreau(Carreau carreau) {
		this.carreaux.add(carreau);
	}
	public  void effaceCarreau(Carreau carreauChoisi) {
		if(this.carreaux.contains(carreauChoisi)) {
			this.carreaux.remove(carreauChoisi);
		}
	}
	/**
	 * @method on récupère un carreau depuis un indice donné
	 * @param index
	 * @return
	 */
	public Carreau getCarreau(int indice) {
		 return this.carreaux.get(indice);
	}
	/**
	 * @method pour afficher les carreaux qui sont dans la liste
	 */
	public void write() {
		for (int i = 0; i < carreaux.size(); i++) {
			System.out.println(carreaux.get(i).toString());
		}
	}
	/**
	 * @method nous permet de créer les cartes neutres et les rajouter à la liste des cartes neutres
	 * @param cartesNeutres
	 */
	public void createCartesNeutres(Carreaux cartesNeutres) {
		cartesNeutres.addCarreau(new Carreau(3, 1, 'x'));//créer et rajouter la carte neutre de hauteur 3 et de largeur 1
		cartesNeutres.addCarreau(new Carreau(1, 3, 'x'));//créer et rajouter la carte neutre de hauteur 1 et de largeur 3
	}
	/**
	 * @method récupérer les carreaux selon la carte choisie 
	 * @param carte
	 * @return
	 */
	public  ArrayList<Carreau> recupereCarreauxSelonCarte(Carte carte) {
		char couleur=' ';//initialisation de la variable couleur
		int taille=0;//intialisation de la variable taille
		Iterator<Carreau> iterator =  this.carreaux.iterator();//on va itérer sur la liste des carreaux
		ArrayList<Carreau> carreuxAvecCritere = new ArrayList<Carreau>();//dans cette liste on va mettre les carreaux qui répondent aux critères de la carte choisie
		taille= carte.getTaille();//récupérer la taille de la carte
		if (carte.getTaille()!=0) {//vérifier si le critère c'est la taille, elle doit être différente de zéro
			while(iterator.hasNext()) {//si il y a encore des carreaux dans la liste
				Carreau carreau = iterator.next();//on récupère le carreau
				if(carreau.taille.getHauteur()==taille || carreau.taille.getLargeur()==taille)// si la hauteur ou la largeur du carreau correspond à la taille on le prend
					carreuxAvecCritere.add(carreau);//on rajoute la carreau à la liste des carreaux qu'on va récupérer
			}
		} else {//si la taille est égla à zéro ça veut dire que le critère c'est la couleur
			couleur = carte.getCouleur();//on récupère la couleur
			if(couleur=='R') {//si c'est R (rouge)
				while(iterator.hasNext()) {//verifier si il y a encore des carreaux dans la liste
					Carreau carreau = iterator.next();///on récupère le carreau
					if(carreau.getCouleur()>=65 && carreau.getCouleur()<=90)//si la lettre représentant le couleur est majuscule alors c'est rouge (par convention) 
						carreuxAvecCritere.add(carreau);//on rajoute la carreau à la liste des carreaux qu'on va récupérer
				}
			} else {//sinon si la couleur est bleue 
				while(iterator.hasNext()) {//verifier si il y a encore des carreaux dans la liste
					Carreau carreau = iterator.next();//on récupère le carreau
					if(carreau.getCouleur()>=97 && carreau.getCouleur()<=122)//si la lettre représentant le couleur est miniscule alors c'est bleu (par convention) 
						carreuxAvecCritere.add(carreau);//on rajoute la carreau à la liste des carreaux qu'on va récupérer
				}
			}
		}
		return carreuxAvecCritere;//on retourne la liste des carreaux qui correspondent à nos critères
	}
	/**
	 * @method savoir si le choix du joueur correspond à un carreau existant
	 * @param choix
	 * @param carreauSelonCarte
	 * @return
	 */
	public static  boolean carreauMatchesUserChoice(String choix,ArrayList<Carreau> carreauSelonCarte) {
		boolean contientChoix=false;//boolean pour savoir si la liste des carreaux contient le choix du joueur
		int i=0;
		while(i<carreauSelonCarte.size() && !contientChoix) {//tant que on a pas atteint le dernier carreau et que le choix du joueur est pas validé on continue à chercher (boucler)
			if(carreauSelonCarte.get(i).couleur==choix.charAt(0)) {//si le choix du joueur correspond à une lettre (couleur) d'un carreau des carreaux retournés par le choix de la carte
				contientChoix=true;//alors on valide le choix du joueur
			}
			i++;//on passe au carreau suivant
		}
		return contientChoix;//on retourne la validation ou non du choix du joueur
	}
	/**
	 * @method récupérer les carreaux selon  
	 * @param choix
	 * @param carreauSelonCarte
	 * @return
	 */
	public static  Carreau recupererCarreauSelonChoix(String choix,ArrayList<Carreau> carreauSelonCarte) {
		boolean contientChoix=false;//boolean pour savoir si la liste des carreaux contient le choix du joueur
		int i=0;
		while(i<carreauSelonCarte.size() && !contientChoix) {//tant que on a pas atteint le dernier carreau et que le choix du joueur est pas validé on continue à chercher (boucler)
			if(carreauSelonCarte.get(i).couleur==choix.charAt(0)) {//si le choix du joueur correspond à une lettre (couleur) d'un carreau des carreaux retournés par le choix de la carte
				return carreauSelonCarte.get(i);//alors on retourne ce carreau
			}
			i++;// on avance au carreau suivant
		}
		return null;//sinon on retourne un objet vide
	}

	/**
	 * @method tirer au hasard une carte neutre
	 * @param cartesNeutres
	 * @return
	 */
	public Carreau tirageCarreauNeutre(Carreaux cartesNeutres) {
		Random r = new Random();
		int aleatoire = r.nextInt(2);//affecter un chiffre neutre entre 0 et 1
		return cartesNeutres.getCarreau(aleatoire);//retourner la carte selon le chiffre obtenu
	} 
	/**
	 * @method verifie que la liste des carreaux n'est pas vide
	 * @return
	 */
	public boolean plusDeCarreaux() {
		return this.carreaux.isEmpty();//retourne si oui ou non la liste est vide
	}
	public int carreauxDeScore() {
		return -1*this.carreaux.size();
	}
}
