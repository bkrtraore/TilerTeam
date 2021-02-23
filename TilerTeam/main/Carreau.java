package main;


/**
 * 
 * @class  carreau qui contient la définition d'un carreau
 * @parametre la taille et la couleur du carreau
 */
@SuppressWarnings("rawtypes")
public class Carreau implements Comparable {
	char couleur;
	Taille taille;
	/**
	 * Constructeur de la classe
	 * @param hauteur
	 * @param largeur
	 * @param couleur
	 */
	public Carreau(int hauteur,int largeur, char couleur) {
		this.taille=new Taille(hauteur,largeur);
		this.couleur=couleur;
	}

	/**
	 * 
	 * @return la couleur du carreau
	 */
	public char getCouleur() {
		return couleur;
	}
	/**
	 * set la couleur du carreau à la coueleur envoyée
	 * @param couleur
	 */
	public void setCouleur(char couleur) {
		this.couleur = couleur;
	}
	/**
	 * toString pour afficher le carreau
	 */
	public String toString() {
		return "Carreau [couleur=" + couleur + ", taille= " + "hauteur : " + taille.getHauteur() + "largeur : "+ taille.getLargeur() +"]";
	}

	@Override
	public int compareTo(Object carreau) {
		int compareChar = ((Carreau)carreau).couleur;
		if(compareChar>=97) {
			return this.couleur-compareChar;
		} else {
			return compareChar-this.couleur;
		}
		
	}

}
