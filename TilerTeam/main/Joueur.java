package main;
/**
 * 
 * @class joueur qui représente les joueurs
 *
 */
public class Joueur {
	private String nom;
	/**
	 * @constructeur
	 * @param nom
	 */
	public Joueur(String nom) {
		this.nom=nom;
	}
	/**
	 * @method piocher une carte du paquet du cartes
	 * @param paquets
	 * @return la carte tirée
	 */
	public Carte piocherUneCarte(PaquetDeCartes paquets) {
		return paquets.tirageCarte();
	}
	/**
	 * @method return le nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @method set le nom du joueur 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
