package main;
/**
 * 
 * @class test pour valider l'insertion du carreau
 *
 */
public class AcceptCarreauTest {
	/**
	 * @constructeur
	 */
	public AcceptCarreauTest() {}
	/**
	 * @method valider que tous les tests sont réussis
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean valideTouslesTeste(Carreau carreau, int abscisse, int ordonnee, StringBuffer mur) {
		 return blockDepasseMur(carreau, abscisse) 
					&& blockQuiTouche(carreau, abscisse, ordonnee, mur) //retourner le resultat de tous les tests en forme boolean si oui ou non tous les tests sont réussis 
					&& baseQuiTouche(carreau, abscisse, ordonnee, mur)
					&& cloneCarreau(carreau, abscisse, ordonnee, mur)
					&& remplacementInterdit(carreau, abscisse, ordonnee, mur);
		
	}
	/**
	 * @method verifier que le carreau ne dépassse pas le mur
	 * @param carreau
	 * @param position
	 * @return
	 */
	public static boolean blockDepasseMur(Carreau carreau, int position) {
			return carreau.taille.getLargeur()+position-1<=5;//verifier que la taille pour la position d'insertion ne dépasse pas la taille du mort
	}
	/**
	 * @method vérifier que un carreau touche forcément un autre
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean blockQuiTouche(Carreau carreau, int abscisse, int ordonnee, StringBuffer mur) {
		if(ordonnee<=mur.length()/5-1) {//si l'ordonnee est  inférieur à la taille du mur
			int position = ordonnee*5+abscisse-1;//on se positionne par rapport à la ordonnee et l'abscisse
			if(abscisse > 1 && mur.charAt(position-1)!=32) {//si on n'insère pas à la première colonne on vérifie que à notre gauche il y a des carreaux
				return true;
			} else if (abscisse<5 && (position+carreau.taille.getLargeur()-1)<mur.length() && mur.charAt(position+carreau.taille.getLargeur())!=32){ //si on n'insère pas à la dernière colonne on vérifie que à notre droite il y a des carreaux
				return true;
			} else if (ordonnee>1 && mur.charAt((ordonnee-1)*5+abscisse-1)!=32) {//si on est pas dans la première ligne et que la ligne en dessous est rempli 
				return true;
			} else {//on retourne faux si aucune condition n'est satisfaite
				return false;
			}
		} else {//si l'ordonnée est supérieure à la taille du mur (nouvelle ligne)
			return true;//on retourne vrai parce qu'il y a aucun carreau
		}
	}
	/**
	 * @method verifier que la base du carreau est stable
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean baseQuiTouche(Carreau carreau, int abscisse, int ordonnee, StringBuffer mur) {
		if(ordonnee==1) {//si on est dans la première alors c'est stable
			return true;
		}
		if(ordonnee>mur.length()/5) { //si l'ordonnee est plus grande que le mur 
			if(ordonnee>mur.length()/5) {// si c'est une nouvelle ligne qui ne se repose pas sur une ligne existante
				return false;
			} else {
				return comparerBaseCarreau(carreau,abscisse,ordonnee,mur);// on compare la base
			}
		} else {//si l'ordonnee est inferieur au mur 
			return comparerBaseCarreau(carreau,abscisse,ordonnee,mur);// on compare la base
		}
	}
	/**
	 * @method compare la base du carreau
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean comparerBaseCarreau(Carreau carreau, int abscisse, int ordonnee, StringBuffer mur) {
		int positionDepart = ordonnee*5+abscisse-1;//définir la position de depart
		int positionArivee = positionDepart+ carreau.taille.getLargeur();//définir la position de depart
		String base = mur.substring(positionDepart-5,positionArivee-5);//récupérer la base sur laquelle repose le carreau
		return !base.contains(" ");//retourner si oui ou non cette base contient des espaces
	}
	/**
	 * @method verifier que le careau ne clone aucun autre
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean cloneCarreau(Carreau carreau,int abscisse,int ordonnee,StringBuffer mur) {
		int distance = carreau.taille.getHauteur()+ordonnee-1;//définir la distance
		if(distance>mur.length()/5-1) {//si la taille de la distance est supérieur à la taille du mur 
			return comparerBaseClonageCarreau(carreau, abscisse, ordonnee,mur) ;//on ne vérifie que la base
		} else {
			return comparerCoteClonageCarreau(carreau,abscisse,ordonnee,mur) && comparerBaseClonageCarreau(carreau,abscisse,ordonnee,mur);//on vérifie la base et les côtés
		}
	}
	/**
	 * @method verifier que les côtés ne sont pas clonés
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean comparerCoteClonageCarreau(Carreau carreau,int abscisse,int ordonnee,StringBuffer mur) {
		int distance = carreau.taille.getHauteur()+ordonnee-1;//on définit la distance
		StringBuffer lettrePlusDroite = new StringBuffer();
		StringBuffer lettrePlusGauche = new StringBuffer();
		StringBuffer stringCoteGauche = new StringBuffer();//créer la chaine qui va contennir le coté gauche
		StringBuffer stringCoteDroite = new StringBuffer();//créer la chaine qui va contennir le coté droite
		if(ordonnee>1) {
			if(abscisse>1) {
				lettrePlusGauche.append(mur.charAt((ordonnee-1)*5+abscisse-2));
			}
			if(carreau.taille.getLargeur()+abscisse-1<5) {//si on insère pas dans la dernière ligne
				lettrePlusDroite.append(mur.charAt((ordonnee-1)*5+carreau.taille.getLargeur()+abscisse-1));
			}
		}
		for(int i=ordonnee;i<=distance;i++) {
			if(abscisse>1) {
				stringCoteGauche.append(mur.charAt(i*5+abscisse-2));//on récupère le caractère a gauche du carreau
			}
			if(carreau.taille.getLargeur()+abscisse-1<5) {//si on insère pas dans la dernière ligne
				stringCoteDroite.append(mur.charAt(i*5+carreau.taille.getLargeur()+abscisse-1));//on récupère le caractère a droite du carreau
			}
		}
		if(distance<mur.length()/5-1) {
			if(abscisse>1) {
				lettrePlusGauche.append(mur.charAt((ordonnee+1)*5+abscisse-2));
			}
			if(carreau.taille.getLargeur()+abscisse-1<5) {//si on insère pas dans la dernière ligne
				lettrePlusDroite.append(mur.charAt((ordonnee+1)*5+carreau.taille.getLargeur()+abscisse-1));
			}
		}
		if(abscisse==1) {//si on insère dans la première colonne 
			return stringCompareToString(stringCoteDroite.toString(),carreau,lettrePlusDroite);//on ne compare qu'à droite
		} else if (abscisse==5||carreau.taille.getLargeur()+abscisse-1==5) {//si on insère dans la dernière colonne ou on arrive à la dernière colonne après insertion
			return stringCompareToString(stringCoteGauche.toString(),carreau,lettrePlusGauche);//on ne compare qu'à gauche
		} else {;
			return stringCompareToString(stringCoteDroite.toString(),carreau,lettrePlusDroite) && stringCompareToString(stringCoteGauche.toString(),carreau,lettrePlusGauche);//sinon on compare des deux côtés
		}
	}
	/**
	 * @method verifier qu'il n y a pas deja un carreau au moment de l'insertion
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean remplacementInterdit(Carreau carreau,int abscisse,int ordonnee,StringBuffer mur) {
		boolean canInsert = true;//boolean pour préciser qu'on peut inserer ou pas
		int position=ordonnee;
		if(position>mur.length()/5-1) {
			canInsert=true;
		}else {
			for(int i=0;i<carreau.taille.getHauteur();i++) {
				if (canInsert && (position+i)<mur.length()/5) {//tant que on peut inserer et que la position est supérieur à la hauteur + ordonnee
					if(position*5+carreau.taille.getLargeur()+abscisse-1<mur.length())
					canInsert=mur.substring(position*5+abscisse-1, position*5+carreau.taille.getLargeur()+abscisse-1).matches("^[ ]+$");//on retourne est-ce que oui ou non que l'emplacement est vide
				}
			}
		}
		return canInsert;
	}
	/**
	 * @method verifier que la base ne clone pas un carreau
	 * @param carreau
	 * @param abscisse
	 * @param ordonnee
	 * @param mur
	 * @return
	 */
	public static boolean comparerBaseClonageCarreau(Carreau carreau, int abscisse, int ordonnee, StringBuffer mur) {
		int positionDepart = ordonnee*5+abscisse-1;//définit la position de depart
		int positionArivee = positionDepart+ carreau.taille.getLargeur();//definit la position d'arrivé
		StringBuffer lettrePlus = new StringBuffer();
		if(ordonnee>1) {//si on n'insère pas dans la première ligne
			StringBuffer base = new StringBuffer();
			if(abscisse>1) {//si on est pas dans la première colonne
				lettrePlus.append(mur.charAt(positionDepart-6));//on récupère l'emplacement avant le debut 
			}
			base = base.append(mur.substring(positionDepart-5,positionArivee-5));//on recupère la base sur laquelle le carreau
			if(abscisse+carreau.taille.getLargeur()-1<5) {//si apres insertion on est pas dans la derniere colonne
				lettrePlus.append(mur.charAt(positionArivee-5));//on récupère l'emplacement apres la fin
			}
			return stringCompareToString(base.toString(),carreau,lettrePlus);//on compare la base
		} else {//si on est dans la première ligne on retourne true
			return true;
		}
	}
	/**
	 * @method verifier que un string n'est pas composé du même caratère
	 * @param base
	 * @return
	 */
	public static boolean stringCompareToString(String base,Carreau carreau,StringBuffer lettrePlus) {
		boolean clonePas = true;
		if(!base.matches("^["+base.charAt(0)+"]+$")|| base.matches("^[ ]+$")) {//si le block en bas n'est pas homogène en retourne vrai 
			return clonePas;
		} else {
			int i=0;
			while(i<lettrePlus.length()) {
				if(lettrePlus.charAt(i)==base.charAt(0)) {
					return clonePas;
				}
				i++;
			}
			return !clonePas;
		}
	}
}