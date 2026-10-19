/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {
	/*
	 * DAS ATTRIBUT NICHT AENDERN! Verwenden Sie dieses Attribut! 0: leere Stelle 1:
	 * Stein von Spieler 1 2: Stein von Spieler 2
	 */
	private int[][] feld;

	/*
	 * NICHT AENDERN! Zu Testzwecken vorhanden Darf verwendet werden, muss aber
	 * nicht
	 */
	public Spielfeld(int zeilen, int spalten) {
		feld = new int[zeilen][spalten];
	}

	/*
	 * NICHT AENDERN! Zu Testzwecken vorhanden Darf verwendet werden, muss aber
	 * nicht
	 */
	public int[][] getFeld() {
		return feld;
	}

	/*
	 * NICHT AENDERN! Zu Testzwecken vorhanden Darf verwendet werden, muss aber
	 * nicht
	 */
	public void setFeld(int[][] feld) {
		this.feld = feld;
	}

	/*
	 * Setzt den Spielerstein des Spielers mit der Nr. spielernr in Spalte spalte,
	 * vorausgesetzt es wurde eine korrekte Zahl angegeben und vorausgesetzt in der
	 * Spalte ist noch Platz. Wenn der Spielstein gesetzt werden kann, wird true
	 * zurueckgegeben, sonst false.
	 */
	public boolean setzeSpielstein(int spalte, int spielernr) {
		// TODO
		if (spalte < 0 || spalte >= feld[0].length)
			return false;
		int i = feld.length-1;
		while (i > 0 && feld[i][spalte] != 0)
			i--;
		if (feld[i][spalte] != 0)
			return false;
        feld[i][spalte] = spielernr;
		return true;
	}

	/*
	 * Gibt true zurueck, wenn der Spieler mit der Nr. spielerNr. gewonnen hat,
	 * sonst false. Denken Sie daran, horizontal, vertikal und diagonal rechts und
	 * links zu ueberpruefen.
	 */
	public boolean testeSieg(int spielerNr) {
		// TODO
		for (int i = 0; i < feld.length; i++) {
			for (int j = 0; j < feld[0].length; j++) {
				if (i + 3 < feld.length && feld[i][j] == spielerNr // vertical testeSieg
						&& feld[i + 1][j] == spielerNr && feld[i + 2][j] == spielerNr && feld[i + 3][j] == spielerNr)
					return true;
				if (j + 3 < feld[0].length // horizontal testeSieg
						&& feld[i][j] == spielerNr && feld[i][j + 1] == spielerNr && feld[i][j + 2] == spielerNr
						&& feld[i][j + 3] == spielerNr)
					return true;
				if (i + 3 < feld.length && j + 3 < feld[0].length && feld[i][j] == spielerNr     // diagonal rechts testeSieg
						&& feld[i + 1][j + 1] == spielerNr && feld[i + 2][j + 2] == spielerNr
						&& feld[i + 3][j + 3] == spielerNr)
					return true;
				if (i + 3 < feld.length && j - 3 >= 0 && feld[i][j] == spielerNr && feld[i + 1][j - 1] == spielerNr  // digonal links testeSieg
						&& feld[i + 2][j - 2] == spielerNr && feld[i + 3][j - 3] == spielerNr)
					return true;
			}
		}
		return false;
	}

	
	public boolean unentchieden() {
		for(int i = 0;i<feld.length;i++) {
			for(int j =0;j<feld[0].length;j++) {
				if(feld[i][j] == 0) return false;
			}
		}
			return true;
	}
	/*
	 * Gibt das aktuelle Feld als String zurueck. Genau den Aufbau aus der
	 * Aufgabenstellung beachten. Zeilenwechsel mit "\n"
	 */
	public String toString() {
		// TODO
		String erg = "";
		for (int i = 0; i < feld.length; i++) {
			erg += "| ";
			for (int j = 0; j < feld[0].length; j++) {
				erg += feld[i][j] + " | ";
			}
			erg += "\n";
		}
		erg += "-----------------------------\n";
		erg += "  ";
		for (int i = 0; i < feld[0].length; i++)
			erg += i+"   ";
		return erg;
	}
}
