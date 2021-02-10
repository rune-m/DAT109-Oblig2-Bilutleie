package no.hvl.dat109.model;


import java.util.ArrayList;
import java.util.List;

public class Bilutleieselskap {

	private String navn;
	private int telefonnummer;
	private Adresse adresse;
	private List<Utleiekontor> kontorer;
	private List<Kunde> kunder;

	/**
	 * Nytt bilutleieselskap
	 * 
	 * @param - navn
	 * @param - telefonnummer
	 * @param - adresse
	 * @param - kontorer
	 * @author - Rune, Simen
	 */
	public Bilutleieselskap(String navn, int telefonnummer, Adresse adresse, List<Utleiekontor> kontorer) {
		this.navn = navn;
		this.telefonnummer = telefonnummer;
		this.adresse = adresse;
		this.kontorer = kontorer;
		kunder = new ArrayList<>();
	}

	/**
	 * Nytt bilutleieselskap
	 * 
	 * @param - navn
	 * @param - telefonnummer
	 * @param - adresse
	 * @author - Rune, Simen
	 */
	public Bilutleieselskap(String navn, int telefonnummer, Adresse adresse) {
		this.navn = navn;
		this.telefonnummer = telefonnummer;
		this.adresse = adresse;
		kontorer = new ArrayList<>();
		kunder = new ArrayList<>();
	}

	public void leggTilKontor(Utleiekontor utleiekontor) {
		kontorer.add(utleiekontor);
	}

	public void leggTilKunde(Kunde kunde) {
		kunder.add(kunde);
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public int getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(int telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Utleiekontor> getKontorer() {
		return kontorer;
	}

	public void setKontorer(List<Utleiekontor> kontorer) {
		this.kontorer = kontorer;
	}

	public List<Kunde> getKunder() {
		return kunder;
	}

	public void setKunder(List<Kunde> kunder) {
		this.kunder = kunder;
	}

}
