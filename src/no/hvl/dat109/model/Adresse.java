package no.hvl.dat109.model;

public class Adresse {

	private String gateadresse;
	private int postnummer;
	private String poststed;

	/**
	 * Ny addresse
	 * 
	 * @param - gateadresse
	 * @param - postnummer
	 * @param - poststed
	 * @author - Simen, Rune
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

}
