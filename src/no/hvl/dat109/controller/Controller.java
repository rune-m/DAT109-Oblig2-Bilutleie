package no.hvl.dat109.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.hvl.dat109.model.Adresse;
import no.hvl.dat109.model.Bil;
import no.hvl.dat109.model.Bilutleieselskap;
import no.hvl.dat109.model.Kunde;
import no.hvl.dat109.model.Reservasjon;
import no.hvl.dat109.model.Utleiegruppe;
import no.hvl.dat109.model.Utleiekontor;

public class Controller {

  private Bilutleieselskap bilutleieselskap;

  public Controller(Bilutleieselskap bilutleieselskap) {
    this.bilutleieselskap = bilutleieselskap;
  }

  public Controller() {
  }

  public Kunde nyKunde(String fornavn, String etternavn, Adresse adresse, int tlf) {
    Kunde kunde = new Kunde(fornavn, etternavn, adresse, tlf);
    bilutleieselskap.leggTilKunde(kunde);
    return kunde;
  }

  /**
   * Henter en liste av alle kontorer i bilutleieselskapet.
   * @return liste over alle kontorene i bilutleieselskapet.
   */
  public List<Utleiekontor> getKontorer() {
    return bilutleieselskap.getKontorer();
  }
  
  /**
   * Bruker velger et kontor med valg (id) og returner et objekt av Utleiekontor. 
   * @param - id
   * @return - et utleiekontor
   */
  public Utleiekontor velgeUtleiekontor(int id) {
    List<Utleiekontor> kontorer = getKontorer();
    Utleiekontor valgtKontor = kontorer.stream()
                                             .filter(k -> k.getKontorid() == id)
                                             .findAny().orElse(null);

    return valgtKontor;
  }

  /**
   * Hent alle utleiegrupper
   * @return et Map<Integer, Utleiegrupper> med alle utleiegrupper
   */
  public Map<Integer, Utleiegruppe> getUtleiegrupper() {
    Map<Integer, Utleiegruppe> utleiegrupper = new HashMap<>();
    int i = 0;
    for (Utleiegruppe ug : Utleiegruppe.values()) {
      utleiegrupper.put(++i, ug);
    }
    return utleiegrupper;
  }

  /**
   * Henter en utleiegruppe som er valgt med en gruppe(key).
   * @param gruppe
   * @return en utleiegruppe
   */
  public Utleiegruppe velgUtleiegruppe(Integer gruppe) {
    return getUtleiegrupper().getOrDefault(gruppe, null);
  }

  /**
   * Søk etter ledige biler hos et kontor i et gitt tidsrom
   * @param kontor
   * @param utleiegruppe
   * @param startUtleie
   * @param sluttUtleie
   * @return liste av alle tilgjengelige biler
   */
  public List<Bil> sokEtterBilerKontor(Utleiekontor kontor, Utleiegruppe utleiegruppe, LocalDateTime startUtleie, LocalDateTime sluttUtleie) {
    return kontor.sokEtterBil(utleiegruppe, startUtleie, sluttUtleie);
  }

  /**
   * Hent bil
   * @param kontor
   * @param regNr
   * @return
   */
  public Bil velgBil(Utleiekontor kontor, String regNr) {
    return kontor.getBiler().stream().filter(b -> b.getRegNr() == regNr).findAny().orElse(null);
  }

  /**
   * Lager en ny reservasjon og registrerer denne hos utleiekontor, bil og kunde
   * @param kontor
   * @param bil
   * @param kunde
   * @param startUtleie
   * @param sluttUtleie
   * @param kredittkortNr
   * @return det nye reservasjonsobjektet
   */
  public Reservasjon leieBil(Utleiekontor kontor, Bil bil, Kunde kunde, LocalDateTime startUtleie, LocalDateTime sluttUtleie, String kredittkortNr) {
    Reservasjon reservasjon = new Reservasjon(kredittkortNr, startUtleie, sluttUtleie, kunde, bil);

    kontor.leggTilReservasjon(reservasjon);
    bil.leggTilReservasjon(reservasjon);
    kunde.setReservasjon(reservasjon);

    return reservasjon;

  }

  /**
   * Hent kvittering for en reservasjon
   * @param reservasjon
   * @return kvittering som String
   */
  public String kvitteringForLeie(Reservasjon reservasjon) {
    return reservasjon.toString();
  }

  /**
   * Returnere en bil til kontor
   * @param reservasjonId
   * @param kontor
   * @return
   */
  public String returnerBil(int reservasjonId, Utleiekontor kontor) {
    Reservasjon reservasjon = kontor.getReservasjoner().stream().filter(r -> r.getReservasjonId() == reservasjonId).findAny().orElse(null);

    reservasjon.getKunde().fjernReservasjon();
    reservasjon.getBil().fjernReservasjon(reservasjon);
    kontor.returnereBilTilKontor(reservasjon);

    return kvitteringForLeie(reservasjon);
  }

  /**
   * Parse en dato (String) til et LocalDateTime-objekt
   * @param dato
   * @return LocalDateTime ved korrekt input, evt. null
   */
  public LocalDateTime parseDato(String dato) {
    // TODO sjekke at alle tallene har to siffer?
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    LocalDateTime dateTime = null;
    dato += " 00:00";
    try {
      dateTime = LocalDateTime.parse(dato, formatter);
    } catch (DateTimeParseException e) {
      System.out.println("Feil format");
      return null;
    }
    return dateTime;
  }

  
  
  
  // Lager new kunde -> Returnerer kunde-obj
  // kunde velge utleikontor - utleiekontor.listeAvKontorer
  // Kunde som velger utleiegruppe
  // utleiekontoret har list<bil> biler - sokEtterBil(Utleiegruppe, tid)
  // returner en liste over tilgjengelige biler i utleiegruppe og tid.
  // kunde velger bil 
  // kredittkortinformasjon fra kunde
  // leieBil(bil, kunde, tidsrom) - ny reservasjon Reservasjon(int reservasjonId, int kredittkortNr, int registreringsnummer, 
  //  LocalDateTime startUtleie, LocalDateTime sluttUtleie, Kunde kunde, Bil bil)
  // leieKvittering()

  // retur
  // velger kontor
  // skriver reservasjonsId
  // søker i alle reservasjoner
  // fjerner reservasjonen om den finnes i alle klasser (kunde, kontor, bil)

}
