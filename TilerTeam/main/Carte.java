package main;
/**
 * 
 * @class carte 
 *
 */
public class Carte {
	private char couleur=' ';
	private int taille=0;
	/**
	 * @constructeur selon la couleur
	 * @param couleur
	 */
	public Carte(char couleur) {
		this.couleur = couleur;
	}
	/**
	 * @constructeur selon la taille
	 * @param taille
	 */
	public Carte(int taille) {
		this.taille = taille;
	}
	/**
	 * @method retourne la couleur de la carte
	 * @return la couleur 
	 */
	public char getCouleur() {
		return couleur;
	}
	/**
	 * @method set la couleur de la carte
	 * @param couleur
	 */
	public void setCouleur(char couleur) {
		this.couleur = couleur;
	}
	/**
	 * @method retourne la taille de la carte
	 * @return taille
	 */
	public int getTaille() {
		return taille;
	}
	/**
	 * @method set la taille de la carte
	 * @param taille
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}
	/**
	 * @method savoir la couleur de la carte
	 * @param couleur
	 * @return boolean
	 */
	public static String getCouleur(char couleur) {
		return couleur=='R'?"Rouge":"Bleu";//retourner Rouge si la couleur est R sinon retourner bleu
	}
	/**
	 * afficher la carte
	 */
	public String toString() {
		String s = "";
		if(Character.isLetter(this.couleur))//si la couleur existe
			s = "Carte " + getCouleur(this.couleur);//afficher la couleur
		else //sinon afficher la taille
			s = "Carte Taille " + this.taille;
			return s;
	}
}