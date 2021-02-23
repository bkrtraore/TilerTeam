package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Joueur;
import main.PaquetDeCartes;

public class JoueurTest {

	@Test
	public void test() {
		PaquetDeCartes PaquetDC = new PaquetDeCartes();
		Joueur A = new Joueur("Bakari");
		Joueur B = new Joueur("Eymen");
		A.piocherUneCarte(PaquetDC);
		B.setNom("Unknow");
		System.out.println(B.getNom());
	}

}
