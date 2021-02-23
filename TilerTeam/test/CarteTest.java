package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Carte;

public class CarteTest {

	@Test
	public void test() {
		Carte C = new Carte('B');
		System.out.println(C.toString());
	}

}
