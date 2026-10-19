import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Random;

class UnitTests {
	Random r = new Random();

	/*
	 * ************************************************************* 
	 * Tests fuer Klasse Spiel
	 ***************************************************************/

	@Test
	void testCheckEingabeMenuTrue() {
		assertTrue(Spiel.checkEingabeMenu(1));
		assertTrue(Spiel.checkEingabeMenu(2));
	}

	@Test
	void testCheckEingabeMenuFalse() {
		// Randwerte 0 und 4 auf jeden Fall testen
		assertEquals(Spiel.checkEingabeMenu(0), false);
		assertEquals(Spiel.checkEingabeMenu(4), false);
		// Weitere Werte mit Zufallsprinzip testen
		for (int i = 0; i < 10; i++) {
			assertFalse(Spiel.checkEingabeMenu(r.nextInt(100) + 4));
			assertFalse(Spiel.checkEingabeMenu(r.nextInt(100) - 100));
		}
	}

	/*
	 * 
	 * ************************************************************* 
	 * Tests fuer Klasse Spieler
	 *************************************************************/
	@Test
	void testSpielzugComputerTrue() {
		Spieler s = new Spieler("Test",2,true);
		// Ueberpruefen, dass 'immer' Spalte zwischen 0 und 6 genannt wird
		for (int i = 0; i < 100; i++) {
			int zug = s.spielzugComputer();
			assertTrue(zug >= 0 && zug < 7);
		}		
	}
	
	@Test
	void testSpielzugComputerFalse() {
		Spieler s = new Spieler("Test",2,false);
		assertEquals(s.spielzugComputer(), -1);	
	}
	
	/*
	 * 
	 * ************************************************************* 
	 * Tests fuer Klasse Spielfeld
	 *************************************************************/
	@Test
	void testeSetzeSpielsteinTrue() {
		Spielfeld sf = new Spielfeld(6,7);
		// Die ersten 6 Steine koennen auf jeden Fall gesetzt werden
		assertTrue(sf.setzeSpielstein(r.nextInt(7), r.nextInt(2)+1));
		
		// Sicherstellen, dass Spalte komplett gefuellt werden kann
		int[][] feld = {	{2, 2, 1, 2, 0, 1, 0},
		                	{2, 1, 2, 1, 1, 1, 0},
		                	{1, 1, 2, 1, 1, 1, 0},
		                	{1, 1, 1, 1, 2, 2, 2},
		                	{2, 2, 1, 2, 2, 2, 2},
		                	{1, 1, 1, 1, 2, 2, 2}};
		sf.setFeld(feld);
		assertTrue(sf.setzeSpielstein(4, 1));
	}
	
	@Test
	void testeSetzeSpielsteinFalse() {
		Spielfeld sf = new Spielfeld(6,7);
				
		// Sicherstellen, dass false bei komplett voller Spalte
		int[][] feld = {	{2, 2, 1, 2, 0, 1, 0},
		                	{2, 1, 2, 1, 1, 1, 0},
		                	{1, 1, 2, 1, 1, 1, 0},
		                	{1, 1, 1, 1, 2, 2, 2},
		                	{2, 2, 1, 2, 2, 2, 2},
		                	{1, 1, 1, 1, 2, 2, 2}};
		sf.setFeld(feld);
		assertFalse(sf.setzeSpielstein(0, 1));
		assertFalse(sf.setzeSpielstein(1, 1));
		assertFalse(sf.setzeSpielstein(2, 2));
		assertFalse(sf.setzeSpielstein(3, 2));
		assertFalse(sf.setzeSpielstein(5, 2));
	}
	
	@Test
	void testeTesteSiegHorizontalTrue() {
		// Alle horizontalen Moeglichkeiten fuer beide Spieler testen
		testeSpielerHorizontal(1);	
		testeSpielerHorizontal(2);	
	}
	
	void testeSpielerHorizontal(int spielerNr) {
		int[][] feld = new int[6][7];
		Spielfeld sf = new Spielfeld(6,7);
		for (int zeile = feld.length - 1; zeile > -1; zeile--) {
			for (int spalte = 0; spalte < 4; spalte++) {
				feld[zeile][spalte] = spielerNr;
				for (int i = 0; i < 4; i++) {
					feld[zeile][spalte + i] = spielerNr;
				}
				sf.setFeld(feld);
				assertTrue(sf.testeSieg(spielerNr));
				feld = new int[6][7];
			}
		}
	}
	
	@Test
	void testeTesteSiegVertikalTrue() {
		// Alle vertikalen Moeglichkeiten fuer beide Spieler testen
		testeSpielerVertikal(1);	
		testeSpielerVertikal(2);	
	}
	
	void testeSpielerVertikal(int spielerNr) {
		int[][] feld = new int[6][7];
		Spielfeld sf = new Spielfeld(6,7);
		for (int zeile = feld.length - 1; zeile > 2; zeile--) { 
			for (int spalte = 0; spalte < feld[0].length; spalte++) {
				feld[zeile][spalte] = spielerNr;
				for (int i = 0; i < 4; i++) {
					feld[zeile - i][spalte] = spielerNr;
				}
				sf.setFeld(feld);
				assertTrue(sf.testeSieg(spielerNr));
				feld = new int[6][7];
			}
		}
	}
	
	@Test
	void testeTesteSiegDiagRechtsTrue() {
		// Alle diagonal rechts Moeglichkeiten fuer beide Spieler testen
		testeSpielerDiagRechts(1);	
		testeSpielerDiagRechts(2);	
	}
	
	void testeSpielerDiagRechts(int spielerNr) {
		int[][] feld = new int[6][7];
		Spielfeld sf = new Spielfeld(6,7);
		for (int zeile = feld.length - 1; zeile > 2; zeile--) { 
			for (int spalte = 0; spalte < 4; spalte++) { 
				feld[zeile][spalte] = spielerNr;
				for (int i = 0; i < 4; i++) {
					feld[zeile - i][spalte + i] = spielerNr;
				}
				sf.setFeld(feld);
				assertTrue(sf.testeSieg(spielerNr));
				feld = new int[6][7];
			}
		}
	}
	
	@Test
	void testeTesteSiegDiagLinksTrue() {
		// Alle diagonal links Moeglichkeiten fuer beide Spieler testen
		testeSpielerDiagLinks(1);	
		testeSpielerDiagLinks(2);	
	}
	
	void testeSpielerDiagLinks(int spielerNr) {
		int[][] feld = new int[6][7];
		Spielfeld sf = new Spielfeld(6,7);
		for (int zeile = feld.length - 1; zeile > 2; zeile--) {
			for (int spalte = feld[0].length - 1; spalte > 2; spalte--) {
				feld[zeile][spalte] = spielerNr;
				for (int i = 0; i < 4; i++) {
					feld[zeile - i][spalte - i] = spielerNr;
				}
				sf.setFeld(feld);
				assertTrue(sf.testeSieg(spielerNr));
				feld = new int[6][7];
			}
		}
	}
	
	@Test
	void testeTesteSiegFalse() {
		// Leeres Feld
		int[][] feld = new int[6][7];
		testeTesteSiegFalse(feld, 1);
		testeTesteSiegFalse(feld, 2);
		
		//Spieler 1 knapp NICHT horizontal gewonnen
		int[][] s1_fast_h = {
				  {0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0},
				  {0,0,0,0,0,0,0},
				  {2,2,0,0,0,0,0},
				  {1,1,1,2,0,0,0},
				};
		testeTesteSiegFalse(s1_fast_h, 1);
		
		// Spieler 2 knapp NICHT vertikal gewonnen 
		int[][] s2_fast_v = {
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {1,0,0,0,1,0,0},  
		  {1,0,0,0,2,0,0},
		  {2,0,0,0,2,0,0},
		  {1,0,0,0,2,0,0},
		};
		testeTesteSiegFalse(s2_fast_v, 2);
		
		// Spieler 1 knapp NICHT diagonal rechts gewonnen 
		int[][] s1_fast_drechts = {
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,2,0,0,0},
		  {0,0,1,1,0,0,0},
		  {0,1,2,2,0,0,0},
		  {1,2,2,1,0,0,0},
		};
		testeTesteSiegFalse(s1_fast_drechts, 1);
		
		// Spieler 2 knapp NICHT diagonal links 
		int[][] s2_fast_dlinks = {
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {1,0,0,0,0,0,0},
		  {1,2,0,0,0,0,0},
		  {1,1,2,0,0,0,0},
		  {2,1,1,2,0,0,0},
		};
		testeTesteSiegFalse(s2_fast_dlinks, 2);
		
	}
	
	void testeTesteSiegFalse(int[][] feld, int spielerNr) {
		Spielfeld sf = new Spielfeld(6,7);
		sf.setFeld(feld);
		assertFalse(sf.testeSieg(spielerNr));
	}
	
	
}
