package no.hvl.dat109.model;

import java.time.LocalDateTime;

public class Reservasjon {

	private int reservasjonId;
	private String kredittkortNr;
	private LocalDateTime startUtleie;
	private LocalDateTime sluttUtleie;
	private Kunde kunde;
  private Bil bil;
  
  private static int idCount = 0;

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

	@Override
	public String toString() {
		return "Reservasjon [bil=" + bil + ", kredittkortNr=" + kredittkortNr + ", kunde=" + kunde + ", reservasjonId="
				+ reservasjonId + ", sluttUtleie=" + sluttUtleie + ", startUtleie=" + startUtleie + "]";
	}

	

}
