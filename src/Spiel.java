import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisung von checkEingabeMenu()
 ****************************************************************************************************** */

public class Spiel {

	private static Scanner sc = new Scanner(System.in);
	public static Spielfeld sf = new Spielfeld(6, 7);

	public static void main(String[] args) {
		start();
		sc.close();
	}

	/*
	 * Gibt bei Eingabe von 1, 2 oder 3 true zurueck Sonst false
	 */

	public static void start() {
		int eingabe = 0;
		do {
			System.out.println("VIER GEWINNT");
			do {
				System.out.println("1 - Einspieler\n2 - Zwei Spieler\n3 - Programm beenden");
				eingabe = sc.nextInt();
			} while (!checkEingabeMenu(eingabe));
			if (eingabe != 3)
				spielrunde(eingabe);
			else
				System.out.println("Das Programm wird beendet");
		} while (eingabe != 3);
	}

	public static boolean checkEingabeMenu(int eingabe) {
		// TODO
		return eingabe >= 1 && eingabe <= 3;
	}

	public static void spielrunde(int x) {
		String name1 = "";
		String name2 = "";
		boolean isComp1 = false;
		boolean isComp2 = false;
		if (x == 1) {
			System.out.println("Geben Sie den Namen des Spielers ein:");
			name1 = sc.next();
			name2 = "Computer";
			isComp2 = true;
		} else if (x == 2) {
			System.out.println("Geben Sie den Namen des Spielers ein:");
			name1 = sc.next();
			System.out.println("Geben Sie den Namen des zweiten Spielers ein:");
			name2 = sc.next();
		}
		Spieler sp1 = new Spieler(name1, 1, isComp1);
		Spieler sp2 = new Spieler(name2, 2, isComp2);
		System.out.println(sf);
		do {
			if (!sf.testeSieg(sp2.getSpielerNr()) && !sf.unentchieden()) // hat spieler2 gewonnen ende
				spielZug(sp1);
			if (!sf.testeSieg(sp1.getSpielerNr()) && !sf.unentchieden()) // hat spieler1 gewonnen ende
				spielZug(sp2);
		} while (!sf.testeSieg(sp1.getSpielerNr()) && !sf.testeSieg(sp2.getSpielerNr()) && !sf.unentchieden());
		hatGewonnen(sp1, sp2);
		spielfeldLoeschen();
	}

	public static void spielfeldLoeschen() {
		int neuFeld[][] = new int[6][7];
		sf.setFeld(neuFeld);
	}

	public static void spielZug(Spieler sp) {
		int eingabeSpalte = 0;
		if (sp.getComputer()) {
			int x = 0;
			do {
				x = sp.spielzugComputer();
			} while (!sf.setzeSpielstein(x, sp.getSpielerNr()));
			System.out.println(sp.getName() + ": ist an der Reihe");
			System.out.println(sp.getName() + " waehlt Spalte " + x + ".");
		} else {
			do {
				System.out.println(sp.getName() + ": ist an der Reihe");
				eingabeSpalte = sc.nextInt();
			} while (!sf.setzeSpielstein(eingabeSpalte, sp.getSpielerNr()));
			System.out.println(sp.getName() + " waehlt Spalte " + eingabeSpalte + ".");
		}
		System.out.println(sf);
	}

	public static void hatGewonnen(Spieler s, Spieler r) {
		if (sf.unentchieden())
			System.out.println("unentschieden!");
		else {
			if (sf.testeSieg(s.getSpielerNr()))
				System.out.println(s.getName() + " hat gewonnen!");
			else if (sf.testeSieg(r.getSpielerNr()))
				System.out.println(r.getName() + " hat gewonnen!");
		}
		
		/*public static void einSpielerMode() {
		System.out.println("Geben Sie den Namen des Spielers ein:");
		String name = sc.next();
		Spieler sp1 = new Spieler(name, 1, false);
		Spieler sp2 = new Spieler("Computer", 2, true);
		System.out.println(sf);
		do {
			if (!sf.testeSieg(sp2.getSpielerNr()) && !sf.unentchieden()) // hat spieler2 nicht gewonnen wieter
				spielZug(sp1);
			if (!sf.testeSieg(sp1.getSpielerNr())&& !sf.unentchieden()) // hat spieler1 nicht gewonnen weiter
				spielZug(sp2);
		} while (!sf.testeSieg(sp1.getSpielerNr()) && !sf.testeSieg(sp2.getSpielerNr())&& !sf.unentchieden());
        hatGewonnen(sp1,sp2);
        
		spielfeldLoeschen();
	}

	public static void zweiSpielerMode() {
		System.out.println("Geben Sie den Namen des Spielers ein:");
		String name1 = sc.next();
		Spieler sp1 = new Spieler(name1, 1, false);
		System.out.println("Geben Sie den Namen des zweiten Spielers ein:");
		String name2 = sc.next();
		Spieler sp2 = new Spieler(name2, 2, false);
		System.out.println(sf);
		do {
			if (!sf.testeSieg(sp2.getSpielerNr())&& !sf.unentchieden()) // hat spieler2 gewonnen ende
				spielZug(sp1);
			if (!sf.testeSieg(sp1.getSpielerNr())&& !sf.unentchieden()) // hat spieler1 gewonnen ende
				spielZug(sp2);
		} while (!sf.testeSieg(sp1.getSpielerNr()) && !sf.testeSieg(sp2.getSpielerNr())&& !sf.unentchieden());
		hatGewonnen(sp1,sp2);
		spielfeldLoeschen();
	}*/
	}
}
