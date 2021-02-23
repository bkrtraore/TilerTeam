package test;

import org.junit.Test;

import main.Carreau;

public class CarreauTest {

	@Test
	/**
	 * tester le carreau 
	 */
	public void test() {
		Carreau C = new Carreau(2,3,'g');
		System.out.println(C.toString());
	}

}