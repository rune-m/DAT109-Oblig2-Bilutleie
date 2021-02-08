package no.hvl.dat109.controller;

import no.hvl.dat109.model.Bilutleieselskap;

public class Controller {

  private Bilutleieselskap bilutleieselskap;

  public String leieBil() {

    return "";
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
