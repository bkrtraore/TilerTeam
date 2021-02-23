package main;

import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * @class paquets de cartes qui contient toutes les cartes
 *
 */
public class PaquetDeCartes {
	
	private ArrayList<Carte> Paquet = new ArrayList<Carte>();
	/**
	 * @constructeur
	 */
	public PaquetDeCartes() {
		//créer et ajouter les cartes couleur au paquet 
		for(int i = 0; i < 9; i++) {
			this.Paquet.add(new Carte('R'));
			this.Paquet.add(new Carte('B'));
		}
		//créer et ajouter les cartes taille au paquet 
		for(int j = 0; j < 5 ; j++) {
			this.Paquet.add(new Carte(1));
			this.Paquet.add(new Carte(2));
			this.Paquet.add(new Carte(3));
		}
	}
	public PaquetDeCartes(String name) {
		this.Paquet.clear();
	}
	/**
	 * @method afficher la carte
	 */
	public String toString() {
		String s = "";
		for(Carte c : this.Paquet)
			s = s + c.toString() + System.lineSeparator();
			
		return s;
	}
	/**
	 * @method tirer une carte au hasard
	 * @return carte tiré
	 */
	public Carte tirageCarte() {
		assert(!this.paquetVide());
		Random r = new Random();
		int aleatoire = r.nextInt(this.Paquet.size());//choisir un chiffre aléatoire parmi le nombre de cartes restantes
		Carte CarteTir� = this.Paquet.get(aleatoire);//on récupère la carte
		return CarteTir�;
	} 
	/**
	 * @method verifier si le paquet est vide ou pas
	 * @return boolean
	 */
	public boolean paquetVide() {
		return Paquet.isEmpty();
	}
	public int taille() {
		return this.Paquet.size();
	}
	public void retirerCarte(Carte carte) {
		if(this.Paquet.contains(carte)) {
			this.Paquet.remove(carte);		
		}
	}
	public void addCarte(Carte carte) {
		this.Paquet.add(carte);
	}
	public int CarteDeScore() {
		return -1*this.Paquet.size();
	}
}
