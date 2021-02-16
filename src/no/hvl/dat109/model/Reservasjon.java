package no.hvl.dat109.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

/**
 * Klassen Reservasjon
 * @author Rune, Simen
 */
public class Reservasjon {

	private static final int prisLiten = 350;
	private static final int prisMedium = 500;
	private static final int prisStor = 750;
	private static final int prisStasjonsvogn = 950;

	private int reservasjonId;
	private String kredittkortNr;
	private LocalDateTime startUtleie;
	private LocalDateTime sluttUtleie;
	private Kunde kunde;
 	private Bil bil;
  
  	private static int idCount = 0;

	/**
	 * Definerer en reservasjon.
	 * @param kredittkortNr
	 * @param startUtleie
	 * @param sluttUtleie
	 * @param kunde
	 * @param bil
	 */
	public Reservasjon(String kredittkortNr, LocalDateTime startUtleie,
			LocalDateTime sluttUtleie, Kunde kunde, Bil bil) {
		this.reservasjonId = ++idCount;
		this.kredittkortNr = kredittkortNr;
		this.startUtleie = startUtleie;
		this.sluttUtleie = sluttUtleie;
		this.kunde = kunde;
		this.bil = bil;
	}

	public int getReservasjonId() {
		return reservasjonId;
	}

	public void setReservasjonId(int reservasjonId) {
		this.reservasjonId = reservasjonId;
	}

	public String getKredittkortNr() {
		return kredittkortNr;
	}

	public void setKredittkortNr(String kredittkortNr) {
		this.kredittkortNr = kredittkortNr;
	}

	public LocalDateTime getStartUtleie() {
		return startUtleie;
	}

	public void setStartUtleie(LocalDateTime startUtleie) {
		this.startUtleie = startUtleie;
	}

	public LocalDateTime getSluttUtleie() {
		return sluttUtleie;
	}

	public void setSluttUtleie(LocalDateTime sluttUtleie) {
		this.sluttUtleie = sluttUtleie;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Bil getBil() {
		return bil;
	}

	public void setBil(Bil bil) {
		this.bil = bil;
	}

	/**
	 * Finner leiepris for en bil.
	 * @return - pris
	 */
	public int prisForLeie() {
		int pris = 0;
		long antallDager = startUtleie.until(sluttUtleie, ChronoUnit.DAYS) + 1;
		Utleiegruppe ug = bil.getUtleiegruppe();
	
		if (ug.equals(Utleiegruppe.LITEN)) {
		  pris = (int) antallDager * prisLiten;
		  return pris;
		} else if (ug.equals(Utleiegruppe.MEDIUM)) {
		  pris = (int) antallDager * prisMedium;
		  return pris;
		} else if (ug.equals(Utleiegruppe.STOR)) {
		  pris = (int) antallDager * prisStor;
		  return pris;
		} else {
		  pris = (int) antallDager * prisStasjonsvogn;
		  return pris;
		}
	 
	  }

	@Override
	public String toString() {
		return "\nReservasjon " + reservasjonId + ":"  + "  \nBil - " + bil + 
				"\nKredittkortnummer - " + kredittkortNr + 
				"\nKunde - " + kunde + 
				"\nStart utleie: " + startUtleie.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " - Slutt utleie: " + sluttUtleie.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) 
				+ " Pris: " + prisForLeie();
	}

	

}
