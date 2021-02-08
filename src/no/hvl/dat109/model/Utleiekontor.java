package no.hvl.dat109.model;

import java.util.List;

public class Utleiekontor {

    private int kontorid;
    private Adresse adresse;
    private int telefonnummer;
    private List<Bil> biler;
    private List<Reservasjon> reservasjoner;

    private static int idCount = 0;


    /**
     * Nytt utleiekontor med liste av biler
     * @param - kontorid
     * @param - adresse
     * @param - telefonnummer
     * @param - biler
     * @param - reservasjoner
     * 
     * @author - Rune, Simen
     */
    public Utleiekontor (Adresse adresse, int telefonnummer, List<Bil> biler, List<Reservasjon> reservasjoner) {
        this.kontorid = ++idCount;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
        this.biler = biler;
        this.reservasjoner = reservasjoner;
    }

    /**
     * Lager en reservasjon og legger til i system.
     */
    public boolean leggTilReservasjon(Reservasjon reservasjon){
        boolean ledig = reservasjon.getBil().leggTilReservasjon(reservasjon);
        if (ledig) {
            reservasjoner.add(reservasjon);
        }
        return ledig;
    }

    /**
     * Fjerner reservasjon og setter bil til ledig. (Skal det være Bil bil, istedet for regNr?)
     * @param - reservasjon
     */
    public void returnereBilTilKontor(Reservasjon reservasjon) {
        //TODO 
        reservasjon.getBil().fjernReservasjon(reservasjon);
        reservasjon.getKunde().fjernReservasjon();
    }


    /**
     * Gir bruker/kunde en kvittering for leie av bil.
     */
    public String leieKvittering() {
        String leier = "Du leier bil";
        //TODO
        return leier;
    }

    /**
     * Gir bruker/kunde kvittering for returnering av bil.
     */
    public String returnerKvittering() {
        String returnert = "Du har levert bil";
        //TODO
        return returnert;
    }

    /**
     * Leter etter en bil. 
     * Bør kanskje ha et søkeparameter = modell?
     */
    public Bil sokEtterBil() {
        //TODO
        return null;
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


}