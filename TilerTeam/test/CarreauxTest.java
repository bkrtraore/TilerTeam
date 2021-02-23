package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Carreaux;

public class CarreauxTest {

	@Test
	public void test() {
		Carreaux MesCrx = new Carreaux();
		assertTrue(MesCrx.plusDeCarreaux());
		
	}

}
