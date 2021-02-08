package no.hvl.dat109.model;

public class Kunde {

	private String fornavn;
	private String etternavn;
	private Adresse adresse;
	private int tlf;
	private Reservasjon reservasjon;

	/**
	 * Ny kunde
	 * 
	 * @param fornavn
	 * @param etternavn
	 * @param adresse
	 * @param tlf
	 */
	public Kunde(String fornavn, String etternavn, Adresse adresse, int tlf) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.adresse = adresse;
		this.tlf = tlf;
	}

	/**
	 * Ny kunde + reservasjon
	 * 
	 * @param fornavn
	 * @param etternavn
	 * @param adresse
	 * @param tlf
	 * @param reservasjon
	 */
	public Kunde(String fornavn, String etternavn, Adresse adresse, int tlf, Reservasjon reservasjon) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.adresse = adresse;
		this.tlf = tlf;
		this.reservasjon = reservasjon;
	}

	public void fjernReservasjon() {
		reservasjon = null;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public int getTlf() {
		return tlf;
	}

	public void setTlf(int tlf) {
		this.tlf = tlf;
	}

	public Reservasjon getReservasjon() {
		return reservasjon;
	}

	public void setReservasjon(Reservasjon reservasjon) {
		this.reservasjon = reservasjon;
	}

}
