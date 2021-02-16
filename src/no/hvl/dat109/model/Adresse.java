package no.hvl.dat109.model;

/**
 * Klassen Adresse
 * @author Rune, Simen
 */
public class Adresse {

	private String gateadresse;
	private int postnummer;
	private String poststed;

	/**
	 * Definerer en adresse.
	 * 
	 * @param - gateadresse
	 * @param - postnummer
	 * @param - poststed
	 */
	public Adresse(String gateadresse, int postnummer, String poststed) {
		this.gateadresse = gateadresse;
		this.postnummer = postnummer;
		this.poststed = poststed;
	}

	public String getGateadresse() {
		return this.gateadresse;
	}

	public void setGateadresse(String gateadresse) {
		this.gateadresse = gateadresse;
	}

	public int getPostnummer() {
		return this.postnummer;
	}

	public void setPostnummer(int postnummer) {
		this.postnummer = postnummer;
	}

	public String getPoststed() {
		return this.poststed;
	}

	public void setPoststed(String poststed) {
		this.poststed = poststed;
	}

	@Override
	public String toString() {
		return "" + gateadresse + ", " + postnummer + ", " + poststed;
	}

}
