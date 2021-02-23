package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Taille;

public class TailleTest {

	@Test
	public void test() {
		Taille Tall = new Taille(5, 5);
		Tall.setHauteur(9); // On change la hauteur
		assertEquals(Tall.getHauteur(), 9);
	}

}
