package com.mycompany.unicafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

	Kassapaate pääte;
	
	@Before
	public void init() {
		pääte = new Kassapaate();
	}
	
	@Test
	public void luotuOikein() {
		assertEquals(0, pääte.edullisiaLounaitaMyyty() + pääte.maukkaitaLounaitaMyyty());
		assertEquals(100_000, pääte.kassassaRahaa());
	}
	
	@Test
	public void edullisestiKäteisellä() {
		assertEquals(60, pääte.syoEdullisesti(300));
		assertEquals(1, pääte.edullisiaLounaitaMyyty());
		assertEquals(100_240, pääte.kassassaRahaa());
		
		assertEquals(230, pääte.syoEdullisesti(230));
		assertEquals(1, pääte.edullisiaLounaitaMyyty());
		assertEquals(100_240, pääte.kassassaRahaa());
	}
	
	@Test
	public void maukkaastiKäteisellä() {
		assertEquals(50, pääte.syoMaukkaasti(450));
		assertEquals(1, pääte.maukkaitaLounaitaMyyty());
		assertEquals(100_400, pääte.kassassaRahaa());
		
		assertEquals(350, pääte.syoMaukkaasti(350));
		assertEquals(1, pääte.maukkaitaLounaitaMyyty());
		assertEquals(100_400, pääte.kassassaRahaa());
	}
	
	@Test
	public void edullisestiKortilla() {
		Maksukortti kortti = new Maksukortti(400);
		assertTrue(pääte.syoEdullisesti(kortti));
		assertEquals(160, kortti.saldo());
		
		assertFalse(pääte.syoEdullisesti(kortti));
		assertEquals(160, kortti.saldo());
		assertEquals(1, pääte.edullisiaLounaitaMyyty());
		
		assertEquals(100_000, pääte.kassassaRahaa());
	}
	
	@Test
	public void maukkaastiKortilla() {
		Maksukortti kortti = new Maksukortti(500);
		assertTrue(pääte.syoMaukkaasti(kortti));
		assertEquals(100, kortti.saldo());
		assertEquals(1, pääte.maukkaitaLounaitaMyyty());
		
		assertFalse(pääte.syoMaukkaasti(kortti));
		assertEquals(100, kortti.saldo());
		
		assertEquals(100_000, pääte.kassassaRahaa());
	}
	
	@Test
	public void lataaRahaa() {
		Maksukortti kortti = new Maksukortti(0);
		pääte.lataaRahaaKortille(kortti, 500);
		assertEquals(500, kortti.saldo());
		assertEquals(100_500, pääte.kassassaRahaa());
		
		pääte.lataaRahaaKortille(kortti, -10);
		assertEquals(500, kortti.saldo());
		assertEquals(100_500, pääte.kassassaRahaa());
	}
}
