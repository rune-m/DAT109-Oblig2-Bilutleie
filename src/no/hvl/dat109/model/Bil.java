package no.hvl.dat109.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bil {

	private String regNr;
	private String merke;
	private String modell;
	private String farge;
	private int kmStand;
	private Utleiegruppe utleiegruppe;
	private List<Reservasjon> reservasjoner;

	public Bil(String regNr, String merke, String modell, String farge, int kmStand, Utleiegruppe utleiegruppe) {
		this.regNr = regNr;
		this.merke = merke;
		this.modell = modell;
		this.farge = farge;
		this.kmStand = kmStand;
		this.utleiegruppe = utleiegruppe;
		reservasjoner = new ArrayList<>();
	};

	/**
	 * Sjekk om Bil er ledig på gitte tidspunkt
	 * 
	 * @param startDato startdato for søk
	 * @param sluttDato sluttdato for søk
	 * @return boolean for om bil er ledig
	 */
	public boolean erLedig(LocalDateTime startDato, LocalDateTime sluttDato) {

		// for (Reservasjon r : reservasjoner) {
		// 	if (startDato.compareTo(r.getStartUtleie()) > 0 && startDato.compareTo(r.getSluttUtleie()) < 0) {
		// 		return false;
		// 	} else if (sluttDato.compareTo(r.getStartUtleie()) > 0 && sluttDato.compareTo(r.getSluttUtleie()) < 0) {
		// 		return false;
		// 	}
		// }
		for (Reservasjon r : reservasjoner) {
			if (startDato.isAfter(r.getStartUtleie()) && startDato.isBefore(r.getSluttUtleie())) {
				return false;
			} else if (sluttDato.isAfter(r.getStartUtleie()) && sluttDato.isBefore(r.getSluttUtleie())) {
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
	public boolean leggTilReservasjon(Reservasjon reservasjon) {
		// if (erLedig(reservasjon.getStartUtleie(), reservasjon.getSluttUtleie())) {
			reservasjoner.add(reservasjon);
			return true;
		// }
		// return false;
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

	// TODO Legge til i utility-klasse?
	public static String enumToString(Utleiegruppe ug) {
		String s = ug.toString().toLowerCase();
		return s.substring(0, 1).toUpperCase();
	}

	@Override
	public String toString() {
		return regNr + " - " + Bil.enumToString(utleiegruppe) + ", " + merke + ", " + modell + ", " + farge + ", " + kmStand;
	}

}
