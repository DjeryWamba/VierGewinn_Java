import java.util.Random;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen 
 ****************************************************************************************************** */

public class Spieler {
	/* Legen Sie Objektattribute an */
	// TODO
	private String name;
	private int spielerNr;
	private boolean computer;
	
	/* Verwenden Sie weiter unten dieses Klassenattribut */
	private static Random rd = new Random();
	
	/* Vervollstaendigen Sie den Konstruktor */
	public Spieler(String name, int spielerNr, boolean computer) {
		// TODO
		this.name = name;
		this.spielerNr = spielerNr;
		this.computer = computer;
	}

	/* Vervollstaendigen Sie die getter-Methode */
	public String getName() {
		// TODO
		return name;
	}
	
	/* Vervollstaendigen Sie die getter-Methode */
	public int getSpielerNr() {
		// TODO
		return spielerNr;
	}
	
	/* Vervollstaendigen Sie die getter-Methode */
	public boolean getComputer() {
		// TODO
		return computer;
	}
	
	/* Gibt eine Zufallszahl zwischen 0 und 6 zurueck,
	 * vorausgesetzt es handelt sich um einen Computer-Spieler.
	 * Sonst wird -1 zurueckgegeben */
	public int spielzugComputer() {
		// TODO
		if(getComputer()) {
		int zufall = rd.nextInt(7);
		return zufall;
		}
		return -1;
	}
}
