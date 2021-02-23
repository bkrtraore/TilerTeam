package main;
/**
 * 
 * @class taille du carreau
 *
 */
public class Taille {
	private int hauteur; 
	private int largeur;
	/**
	 * @constructeur
	 * @param hauteur
	 * @param largeur
	 */
	public Taille (int hauteur,int largeur) {
		this.hauteur=hauteur;
		this.largeur=largeur;
	}
	/**
	 * @method récupérer la hauteur du carreau
	 * @return
	 */
	public int getHauteur() {
		return hauteur;
	}
	/**
	 * @method set la hauteur du carreau
	 * @param hauteur
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	/**
	 * @method récupérer la largeur du carreau
	 * @return
	 */
	public int getLargeur() {
		return largeur;
	}
	/**
	 * @method set la largeur du carreau
	 * @param largeur
	 */
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}
}
