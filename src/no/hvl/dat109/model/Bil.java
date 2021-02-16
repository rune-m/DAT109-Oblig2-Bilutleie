package no.hvl.dat109.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import no.hvl.dat109.utils.Utility;

/**
 * Klassen Bil
 * @author Rune, Simen
 */
public class Bil {

	private int id;
	private String regNr;
	private String merke;
	private String modell;
	private String farge;
	private int kmStand;
	private Utleiegruppe utleiegruppe;
	private List<Reservasjon> reservasjoner;

	private static int idCount = 0;


	/**
	 * Definerer en bil.
	 * 
	 * @param regNr
	 * @param merke
	 * @param modell
	 * @param farge
	 * @param kmStand
	 * @param utleiegruppe
	 */
	public Bil(String regNr, String merke, String modell, String farge, int kmStand, Utleiegruppe utleiegruppe) {
		this.id = ++idCount;
		this.regNr = regNr;
		this.merke = merke;
		this.modell = modell;
		this.farge = farge;
		this.kmStand = kmStand;
		this.utleiegruppe = utleiegruppe;
		reservasjoner = new ArrayList<>();
	};

	/**
	 * Sjekk om Bil er ledig ved et gitt tidspunkt.
	 * 
	 * @param startDato startdato for søk
	 * @param sluttDato sluttdato for søk
	 * @return boolean for om bil er ledig
	 */
	public boolean erLedig(LocalDateTime startDato, LocalDateTime sluttDato) {

		for (Reservasjon r : reservasjoner) {
			if (startDato.isAfter(r.getStartUtleie()) && startDato.isBefore(r.getSluttUtleie())) {
				return false;
			} else if (sluttDato.isAfter(r.getStartUtleie()) && sluttDato.isBefore(r.getSluttUtleie())) {
				return false;
			} else if (startDato.isBefore(r.getStartUtleie()) && sluttDato.isAfter(r.getSluttUtleie())) {
				return false;
			}
		}

		return true;
	};

	/**
	 * Legg til ny reservasjon for bil
	 * 
	 * @param reservasjon
	 */
	public void leggTilReservasjon(Reservasjon reservasjon) {
		reservasjoner.add(reservasjon);
	}

	/**
	 * Fjern reservasjon
	 * 
	 * @param reservasjon
	 * @return reservasjonen ble fjernet
	 */
	public boolean fjernReservasjon(Reservasjon reservasjon) {
		return reservasjoner.remove(reservasjon);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegNr() {
		return regNr;
	}

	public void setRegNr(String regNr) {
		this.regNr = regNr;
	}

	public String getMerke() {
		return merke;
	}

	public void setMerke(String merke) {
		this.merke = merke;
	}

	public String getModell() {
		return modell;
	}

	public void setModell(String modell) {
		this.modell = modell;
	}

	public String getFarge() {
		return farge;
	}

	public void setFarge(String farge) {
		this.farge = farge;
	}

	public int getKmStand() {
		return kmStand;
	}

	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}

	public Utleiegruppe getUtleiegruppe() {
		return utleiegruppe;
	}

	public void setUtleiegruppe(Utleiegruppe utleiegruppe) {
		this.utleiegruppe = utleiegruppe;
	}

	public List<Reservasjon> getReservasjoner() {
		return reservasjoner;
	}

	public void setReservasjoner(List<Reservasjon> reservasjoner) {
		this.reservasjoner = reservasjoner;
	}

	@Override
	public String toString() {
		return id + " - " + regNr + " - " + Utility.enumToString(utleiegruppe) + ", " + merke + ", " + modell + ", " + farge + ", " + kmStand;
	}

}
