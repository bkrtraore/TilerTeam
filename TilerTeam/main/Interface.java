package main;
import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * @class pour manipuler le mur 
 *
 */
public class Interface {
	/**
	 * @constructeur
	 */
	public Interface() {}
	/**
	 * @method afficher le mur 
	 * @param buffer
	 */
	public static void afficheInterface(StringBuffer buffer) {
		StringBuffer bufferToShow = new StringBuffer(buffer);//creer un nouveau pour ne pas impacter l'original
		int max=bufferToShow.length()/5;//le maximum de ligne que nous avons
		System.out.println(max);
		for(int i=0;i<max;i++) {//on boucle sur la taille des lignes du mur 
				if(max>9) {
					if(i<max-10) {
						System.out.print(max-i-1);//afficher le numéro sur la première colonne de la  ligne
					} else {
						if(i==max-1) {//si i == max-1 on va afficher la première ligne
							System.out.print("  ");//on ajoute un espace dans la première colonne
						}else {
							System.out.print(" "+(max-i-1));//afficher le numéro sur la première colonne de la  ligne
						}
					}
				} else {
					if(i==max-1) {//si i == max-1 on va afficher la première ligne
						System.out.print(" ");//on ajoute un espace dans la première colonne
					}else {
						System.out.print(max-i-1);//afficher le numéro sur la première colonne de la  ligne
					}
				}
			System.out.println(bufferToShow.substring(bufferToShow.length()-5,bufferToShow.length()));//on affiche le contenu de la ligne 
			bufferToShow.delete(bufferToShow.length()-5,bufferToShow.length());//on supprime le contenu de cette ligne
		}
	}
	/**
	 * @mthod creer et retourner  le mur du début
	 * @return mur
	 */
	public static StringBuffer createBufferToStrat() {
		StringBuffer mur = new StringBuffer("12345");//insérer la première ligne qui contient les chiffres
		return mur;//retourner le mur
	}
	/**
	 * @method ajouter la carte neutre au mur 
	 * @param mur
	 * @param carteNeutre
	 * @return
	 */
	public static StringBuffer addNeutre(StringBuffer mur, Carreau carteNeutre) {
		Random r = new Random();
		int aleatoire = r.nextInt(2);//choisir un chiffre aleatoire entre 0 et 1
		if(carteNeutre.taille.getHauteur()==3) {//si la hauteur est égale à 3
			if (aleatoire==0)//si le chiffre tiré est 0 
				return addBlockToBuffer(mur, carteNeutre, 1,1); //j'insère dans la position 1,1
			else 
				return addBlockToBuffer(mur, carteNeutre, 5,1);//sinon je l'insère dans la position 5,1
		} else {//sinon si  la hauteur est égale à 1
			if (aleatoire==0)//si le chiffre tiré est 0 
				return addBlockToBuffer(mur, carteNeutre, 1,1); //j'insère dans la position 1,1
			else 
				return addBlockToBuffer(mur, carteNeutre, 3,1);//sinon je l'insère dans la position 3,1
		}
		
	}
	/**
	 * @method ajouter un carreau au mur
	 * @param murToModify
	 * @param carreau
	 * @param x
	 * @param y
	 * @return
	 */
	public static StringBuffer addBlockToBuffer(StringBuffer murToModify, Carreau carreau, int x, int y) {
		return fromBlockToString(carreau,x,y,murToModify);//on appelle cette methode 
	}
	/**
	 * 
	 * @param carreau
	 * @param x
	 * @param y
	 * @param murToModify
	 * @return
	 */
	public static StringBuffer fromBlockToString(Carreau carreau,int x,int y,StringBuffer murToModify) {
		//int dist = carreau.taille.getHauteur()+y-1;
		for(int i=1;i<=carreau.taille.getHauteur();i++) {
			if(y+i>murToModify.length()/5) {//si l'endroit ou insérer est supérieur à la taille des lignes
				murToModify.append(createNewLine(carreau.taille.getLargeur(),carreau.getCouleur(),x-1));//on crée une nouvelle ligne
			} else {//sinon on insère dans les lignes deja existante
				StringBuffer stringAAjouter = new StringBuffer();//on crée un stringBuffer qui va contenir le carreau à modifier.
				for(int j=0;j<carreau.taille.getLargeur();j++) {
					stringAAjouter.append(carreau.getCouleur());//on ajoute les caractères 
				}
				murToModify.replace((y+i-1)*5+x-1, (y+i-1)*5+x-1+carreau.taille.getLargeur(),stringAAjouter.toString());//on remplace les espaces par les caractères
			}
		}
		return murToModify;
	}
	/**
	 * @method creer une nouvelle ligne dans le mur
	 * @param times
	 * @param cara
	 * @param colnum
	 * @return
	 */
	public static String createNewLine(int times,char cara, int colnum) {
		StringBuffer line = new StringBuffer();//créer une ligne 
		for(int i=0;i<5;i++) {//parcourir les colonnes du mur
			if(colnum>i) {//si la colonne ou on veut inserer est > à celle ou en est actuellement 
				line.append(" ");//on insère un espace
			}else if(i<times+colnum) {//si la colonne ou on est actuellement est inférieur à celle ou on veut insérer plus de décalage en largeur 
				line.append(cara);//on insère le caractère
			} else {
				line.append(" ");//sinon on insère un espace
			}
		}
		return line.toString();//retourner la ligne créée
	}
	public static void sortArray(ArrayList<Carreau> list) {
		ArrayList<Carreau> miniscule = new ArrayList<Carreau>();
		ArrayList<Carreau> majuscule = new ArrayList<Carreau>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).couleur>=97 && list.get(i).couleur<=105) {
				miniscule.add(list.get(i));
			} else {
				majuscule.add(list.get(i));
			}
		}
		list.clear();
		list.addAll(miniscule);
		list.addAll(majuscule);
	}
	/**
	 * @method afficher les carreaux selon le choix de la carte
	 * @param carreauSelonCarte
	 */
	public static void afficheCarreau(ArrayList<Carreau> carreauSelonCarte) {
		Interface.sortArray(carreauSelonCarte);
		StringBuffer premiereLigne = new StringBuffer();//première ligne qui contient nos carreaux
		StringBuffer deuxiemeLigne = new StringBuffer();//deuxième ligne qui contient nos carreaux
		StringBuffer troisiemeLigne = new StringBuffer();//troisième ligne qui contient nos carreaux
		for (int i=0;i<carreauSelonCarte.size();i++) {
			if(carreauSelonCarte.get(i).taille.getHauteur()==1) {//si la hauteur du carreau est 1 on va afficher que sur la première ligne
				for(int j=0;j<carreauSelonCarte.get(i).taille.getLargeur();j++) {//on récupère les caractère
					premiereLigne.append(carreauSelonCarte.get(i).getCouleur());//on rajoute les caractères à la première ligne
					deuxiemeLigne.append(" ");//on rajoute des espaces à la deuxième ligne
					troisiemeLigne.append(" ");//on rajoute des espaces à la troisième ligne
				}
				premiereLigne.append(" ");//ajouter un espace pour séparer entre les carreau
				deuxiemeLigne.append(" ");//ajouter un espace pour séparer entre les carreau
				troisiemeLigne.append(" ");//ajouter un espace pour séparer entre les carreau
			} else if(carreauSelonCarte.get(i).taille.getHauteur()==2) {//si la hauteur du carreau est 2 on va afficher les deux premières lignes
				for(int j=0;j<carreauSelonCarte.get(i).taille.getLargeur();j++) {//on récupère les caractère
					premiereLigne.append(carreauSelonCarte.get(i).getCouleur());//on rajoute les caractères à la première ligne
					deuxiemeLigne.append(carreauSelonCarte.get(i).getCouleur());//on rajoute les caractères à la deuxième ligne
					troisiemeLigne.append(" ");//on rajoute des espaces à la troisième ligne
				}
				premiereLigne.append(" ");//ajouter un espace pour séparer entre les carreau
				deuxiemeLigne.append(" ");//ajouter un espace pour séparer entre les carreau
				troisiemeLigne.append(" ");//ajouter un espace pour séparer entre les carreau
			} else {
				for(int j=0;j<carreauSelonCarte.get(i).taille.getLargeur();j++) {//si la hauteur du carreau est 3 on va afficher que sur toutes les lignes
					premiereLigne.append(carreauSelonCarte.get(i).getCouleur());//on rajoute les caractères à la première ligne
					deuxiemeLigne.append(carreauSelonCarte.get(i).getCouleur());//on rajoute les caractères à la deuxième ligne
					troisiemeLigne.append(carreauSelonCarte.get(i).getCouleur());//on rajoute les caractères à la troisième ligne
				}
			}
			premiereLigne.append(" ");//ajouter un espace pour séparer entre les carreau
			deuxiemeLigne.append(" ");//ajouter un espace pour séparer entre les carreau
			troisiemeLigne.append(" ");	//ajouter un espace pour séparer entre les carreau
		}
		
		System.out.print(troisiemeLigne.toString()+"\n"+deuxiemeLigne.toString()+"\n"+premiereLigne.toString());//afficher les troix lignes
	}
	public static int ScoreMur(StringBuffer mur) {
		String ligne;
		int score=0;
		for(int i=1;i<mur.length()/5;i++) {
			ligne=mur.substring(i*5,i*5+5);
			if(!ligne.contains(" ")) {
				score+= 5;
			}
		}
		return score;
	}
}
