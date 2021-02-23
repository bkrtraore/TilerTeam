package test;

import org.junit.Test;

import main.PaquetDeCartes;
/**
 * 
 * @class tester le paquet des cartes
 *
 */
public class PaquetDeCartesTest {

	@Test
	public void test() {
		
		 PaquetDeCartes p = new PaquetDeCartes();//créer un paquet de cartes
		 System.out.println(p.toString());//afficher le paquet de cartes
		   int n = 0;
		   while (!p.paquetVide()) {//si le paquet n'est pas vide
		     ++n;
		     p.tirageCarte();//on tire la carte
		   }
		   System.out.println(n);//on affiche n 
		  
		}
	}

