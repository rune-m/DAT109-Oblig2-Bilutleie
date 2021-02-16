package no.hvl.dat109.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klassen Utleiekontor
 * @author - Simen, Rune
 */
public class Utleiekontor {

    private int kontorid;
    private Adresse adresse;
    private int telefonnummer;
    private List<Bil> biler;
    private List<Reservasjon> reservasjoner;

    private static int idCount = 0;

    public Utleiekontor() {}

    /**
     * Nytt utleiekontor med liste av biler og reservasjoner
     * @param - adresse
     * @param - telefonnummer
     * @param - biler
     * @param - reservasjoner
     */
    public Utleiekontor (Adresse adresse, int telefonnummer, List<Bil> biler, List<Reservasjon> reservasjoner) {
        this.kontorid = ++idCount;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.biler = biler;
        this.reservasjoner = reservasjoner;
    }

    /**
     * Nytt utleiekontor med liste av biler
     * @param - adresse
     * @param - telefonnummer
     * @param - biler
     */
    public Utleiekontor (Adresse adresse, int telefonnummer, List<Bil> biler) {
        this.kontorid = ++idCount;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.biler = biler;
        this.reservasjoner = new ArrayList<>();
    }

    /**
     * Lager en reservasjon og legger til i system.
     */
    public void leggTilReservasjon(Reservasjon reservasjon){
            reservasjoner.add(reservasjon);
    }

    /**
     * Fjerner reservasjon og setter bil til ledig.
     * @param - reservasjon
     */
    public void returnereBilTilKontor(Reservasjon reservasjon) {
        reservasjoner = reservasjoner.stream()
            .filter(r -> r.getReservasjonId() != reservasjon.getReservasjonId())
            .collect(Collectors.toList());
    }

    /**
     * Leter etter en bil og om den er ledig i perioden start til slutt. 
     * @return - ledigeBilerFraValgtGruppe 
     */
    public List<Bil> sokEtterBil(Utleiegruppe utleiegruppe, LocalDateTime startUtleie, LocalDateTime sluttUtleie) {
        List<Bil> ledigeBilerFraValgtGruppe = biler.stream()
                                                   .filter(b -> b.getUtleiegruppe() == utleiegruppe) 
                                                   .filter(b -> b.erLedig(startUtleie, sluttUtleie))
                                                   .collect(Collectors.toList());
        return ledigeBilerFraValgtGruppe;
    }

    public void returKvittering() {
        System.out.println("\nBil er returnert");
    }

    public int getKontorid() {
        return this.kontorid;
    }

    public void setKontorid(int kontorid) {
        this.kontorid = kontorid;
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public int getTelefonnummer() {
        return this.telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public List<Bil> getBiler() {
        return this.biler;
    }

    public void setBiler(List<Bil> biler) {
        this.biler = biler;
    }

    public List<Reservasjon> getReservasjoner() {
        return this.reservasjoner;
    }

    public void setReservasjoner(List<Reservasjon> reservasjoner) {
        this.reservasjoner = reservasjoner;
    }

    @Override
    public String toString() {
        return "" + kontorid + " - " + adresse.toString() + " - " + telefonnummer;
    }

}