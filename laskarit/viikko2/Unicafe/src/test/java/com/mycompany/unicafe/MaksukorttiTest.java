package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void oikeaSaldo() {
    	assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void lataaRahaa() {
    	kortti.lataaRahaa(5);
    	assertEquals(15, kortti.saldo());
    }
    
    @Test
    public void otaRahaa() {
    	assertFalse(kortti.otaRahaa(50));
    	assertEquals(10, kortti.saldo());
    	assertTrue(kortti.otaRahaa(10));
    	assertEquals(0, kortti.saldo());
    }
    
    @Test
    public void teksti() {
    	assertEquals("saldo: 0.10", kortti.toString());
    }
}
