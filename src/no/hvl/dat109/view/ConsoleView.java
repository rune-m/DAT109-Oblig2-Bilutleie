package no.hvl.dat109.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import no.hvl.dat109.controller.Controller;
import no.hvl.dat109.model.Adresse;
import no.hvl.dat109.model.Bilutleieselskap;
import no.hvl.dat109.model.Kunde;
import no.hvl.dat109.model.Utleiegruppe;
import no.hvl.dat109.model.Utleiekontor;
import no.hvl.dat109.utils.Utility;
import no.hvl.dat109.model.Bil;
import no.hvl.dat109.model.Reservasjon;

/**
 * Klasse for å opprette konsoll-viewet
 * @author Simen og Rune
 */
public class ConsoleView {

  private static Scanner in = new Scanner(System.in);
  private Controller controller;
  private Bilutleieselskap bilutleieselskap;

  /**
   * Lager nytt ConsoleView for å kjøre applikasjonen i konsollen
   */
  public ConsoleView() {
    bilutleieselskap = new Bilutleieselskap("SR Biler", 12345678, new Adresse("Bilgaten 10", 5018, "Bergen"));
    controller = new Controller(bilutleieselskap);
  }

  /**
   * Starter konsollapplikasjonen med to valg: Leie eller returnere bil
   */
  public void start() {

    System.out.println("Hei, velkommen til " + bilutleieselskap.getNavn() + "\n");
    System.out.println("Hva ønsker du?" + "\n 1 - Leie bil\n 2 - Returnere bil");
    int valg = readInt();
    switch(valg) {
        case 1:
            leieBil(); break;
        case 2: 
            returnerBil(); break;
        default:
            System.out.println("Ugyldig valg!");
    }

  }
  
  /**
   * Kunde leier en bil.
   */
  public void leieBil() {
    
    Kunde kunde = lesInnKunde();
    // Kunde kunde = new Kunde("Ola", "Nordmann", new Adresse("Norge 1", 0001, "Norge"), 12345678);
    System.out.println("\nVelkommen, " + kunde.getFornavn() + "!");

    // Kunde velger kontor
    Utleiekontor kontor = lesInnKontor();

    //Kunde velger utleiegruppe
    // Sjekker om det finnes biler på kontor i gruppen
    Utleiegruppe utleiegruppe = velgeUtleiegruppe(kontor);

    // Kunde velger dato for utleie 
    boolean funnet = false;
    while (!funnet) {

      List<LocalDateTime> datoer = lesInnDatoer();
      LocalDateTime startUtleie = datoer.get(0);
      LocalDateTime sluttUtleie = datoer.get(1);

      // Kunde velger bil
      Bil valgtBil = velgeBil(kontor, utleiegruppe, startUtleie, sluttUtleie);

      if (valgtBil != null) {
        // Kunde oppgir kortinformasjon
        String kredittkortNr = lesInnKredittkortinfo();

        Reservasjon r = controller.leieBil(kontor, valgtBil, kunde, startUtleie, sluttUtleie, kredittkortNr);
        System.out.println(r);
        funnet = true;
      } else {
        System.out.println("Ikke ledig, prøv igjen med nye datoer");
      }

    }

  }

  /**
   * Kunde returnerer en bil.
   */
  public void returnerBil() {
    
    // Velg kontor
    System.out.println("\nKontorer: ");
    controller.getKontorer().forEach(System.out::println);
    int kontorId = messageAndReadInt("\nVelg et kontor(Tall): ");
    Utleiekontor kontor = controller.velgeUtleiekontor(kontorId);

    boolean funnet = false;

    // Skriv reservasjonsnummer (finnes?, er leie over)
    while (!funnet) {
      int resId = messageAndReadInt("Skriv inn ditt reservasjonsnummer: ");

      // Returner
      String kvittering = controller.returnerBil(resId, kontor);

      if (kvittering.equals("")) {
        System.out.println("Vi finner ikke reservasjon " + resId + " i våre systemer. Prøv igjen.\n");
      } else {
        System.out.println(kvittering);
        kontor.returKvittering();
        funnet = true;
      }

    }
  
  }

  /**
   * Kunde velger en bil ved et kontor som er ledig mellom startUtleie og sluttUtleie.
   * @param kontor
   * @param utleiegruppe
   * @param startUtleie
   * @param sluttUtleie
   * @return valgtBil
   */
  public Bil velgeBil(Utleiekontor kontor, Utleiegruppe utleiegruppe, LocalDateTime startUtleie, LocalDateTime sluttUtleie) {
    System.out.println("\nLedige biler: ");
      List<Bil> ledigeBiler = controller.sokEtterBilerKontor(kontor, utleiegruppe, startUtleie, sluttUtleie);
      
      if (!ledigeBiler.isEmpty()) {
        ledigeBiler.forEach(System.out::println);
        Bil valgtBil;
        boolean finnes = false;

        // Kunde velger bil
        do {
          int valgtBilId = messageAndReadInt("\n-> Velg bil");
          valgtBil = controller.velgBil(kontor, valgtBilId);

          if (ledigeBiler.contains(valgtBil)) {
            finnes = true;
          } else {
            System.out.println("\nBilen du oppga er ikke ledig, velg en bil som er ledig.");
          }
          
        } while(!finnes);
        
        return valgtBil;
      }

      return null;

  }

  /**
   * Skriver ut alle utleiegrupper og lar kunde velge utleiegruppe fra konsollen
   * @param kontor
   * @return utleiegruppen
   */
  public Utleiegruppe velgeUtleiegruppe(Utleiekontor kontor) {
    System.out.println("\nBiltype: ");
    controller.getUtleiegrupper().forEach((k, v) -> System.out.println(k + " - " + Utility.enumToString(v)));

    Utleiegruppe utleiegruppe;
    boolean funnet = false;
    do {
      int biltypeId = messageAndReadInt("\nVelg en biltype: ");
      utleiegruppe = controller.velgUtleiegruppe(biltypeId);

      if (kontor.getBiler().stream().map(b -> b.getUtleiegruppe()).collect(Collectors.toList()).contains(utleiegruppe)) {
        funnet = true;
        System.out.println("Fant bil på kontor");
      } else {
        System.out.println("\nDet finnes ingen biler av typen '" + Utility.enumToString(utleiegruppe) + "' på kontoret '" + kontor.getAdresse().toString() + "'.");
      }

    } while (!funnet);

    return utleiegruppe;
  }

  /**
   * Leser inn datoer fra kunde.
   * @return datoer
   */
  public List<LocalDateTime> lesInnDatoer() {
    LocalDateTime startUtleie;
    LocalDateTime sluttUtleie;

    List<LocalDateTime> datoer = new ArrayList<>();
      do {
        
        startUtleie = lesInnDato("\nSkriv inn dato når du starte leien (dd.mm.yyyy): ");
        sluttUtleie = lesInnDato("\nSkriv inn dato for når du ønsker å returnere bilen (dd.mm.yyyy): ");
        
        if (sluttUtleie.isBefore(startUtleie)) {
          System.out.println("\n!Returdato kan ikke være før leiedato!");
        }
      } while (sluttUtleie.isBefore(startUtleie));

      if (startUtleie.equals(sluttUtleie)) {
        sluttUtleie = Utility.formatterSluttdato(sluttUtleie);
      }
      datoer.add(startUtleie);
      datoer.add(sluttUtleie);

      return datoer;
      
  }

  /**
   * Kunde velger kontor. Leser inn kontor.
   * @return kontor
   */
  public Utleiekontor lesInnKontor() {
    System.out.println("\nKontorer: ");
    controller.getKontorer().forEach(System.out::println);

    boolean funnet = false;
    Utleiekontor kontor;
    do {
      int kontorId = messageAndReadInt("\nVelg et kontor(Tall): ");
      kontor = controller.velgeUtleiekontor(kontorId);

      if (kontor == null) {
        System.out.println("Kontoret finnes ikke. Oppgi et av kontorene i listen ovenfor.");
      } else {
        funnet = true;
      }

    } while (!funnet);

    return kontor;
  }

  /**
   * Leser inn kundens kredittkortinfo.
   * @return kredittkortNr
   */
  public String lesInnKredittkortinfo() {
    String kredittkortNr;
      do {
        kredittkortNr = messageAndReadString("\nSkriv inn kredittkortnummer for kortet du ønsker å betale med (16 siffer): ");
      } while (!kredittkortNr.matches("^[0-9]{16}"));
    return kredittkortNr;
  }

  /**
   * Skriver ut melding, leser input i konsollen og parser til et LocalDateTime-objekt
   * @param melding som skal printes ut før input
   * @return LocalDateTime-objekt fra input
   */
  public LocalDateTime lesInnDato(String melding) {
    boolean riktigFormat = false;
    LocalDateTime formatertDato = null;
  
    while (!riktigFormat) {
      String dato = messageAndReadString(melding);

      formatertDato = Utility.parseDato(dato);

      if (formatertDato != null) {
        riktigFormat = true;
        return formatertDato;
      }

      System.out.println("Feil format oppgitt for dato. Prøv igjen.");
    }

    return formatertDato;
  }


  /**
   * Leser inn kundeinformasjon fra konsollen
   * @return Kunde-objektet som opprettes
   */
  public Kunde lesInnKunde() {
    // For å lage kunde.
    String fornavn = messageAndReadString("\nSkriv fornavn: ");
    String etternavn = messageAndReadString("Skriv etternavn: ");
    int tlf = messageAndReadInt("Skriv inn telefonnummer: ");

    // For å lage adresse.
    String gateadresse = messageAndReadString("Skriv inn gateadresse og nummer: ");
    int postnummer = messageAndReadInt("Skriv postnummer: ");
    String poststed = messageAndReadString("Skriv poststed: ");

    return controller.nyKunde(fornavn, etternavn, new Adresse(gateadresse, postnummer, poststed), tlf);
  }


  /**
   * Admin legger til data. Bilutleieselskap, utleiekontor, kunder, biler og reservasjoner.
   */
  public void leggTilData() {

    Bil ford = new Bil("AB 11111", "Ford", "Focus", "Blå", 100_000, Utleiegruppe.MEDIUM);
    Bil honda = new Bil("CD 22222", "Honda", "CRV", "Svart", 222_100, Utleiegruppe.STOR);
    List<Bil> biler1 = new ArrayList<>();
    biler1.add(ford);
    biler1.add(honda);
    
    Bil audi = new Bil("CD 11111", "Audi", "A5", "Blå", 100_000, Utleiegruppe.MEDIUM);
    Bil tesla = new Bil("EL 22222", "Tesla", "Modell S", "Svart", 222_100, Utleiegruppe.STOR);
    List<Bil> biler2 = new ArrayList<>();
    biler2.add(audi);
    biler2.add(tesla);

    Utleiekontor uk1 = new Utleiekontor(new Adresse("Utleiegaten 50", 5075, "Fantoft"), 98010905, biler1);
    Utleiekontor uk2 = new Utleiekontor(new Adresse("Utleiegaten 100", 3045, "Tjøme"), 98010905, biler2);
    bilutleieselskap.leggTilKontor(uk1);
    bilutleieselskap.leggTilKontor(uk2);

    Kunde kunde1 = new Kunde("Simen", "Mathisen", new Adresse("Flåteskjærveien", 3045, "Tjøme"), 45423999);
    Kunde kunde2 = new Kunde("Rune", "Mæstad", new Adresse("Skivebakken 21", 5018, "Bergen"), 47275532);
    List<Kunde> kunder = new ArrayList<>();
    kunder.add(kunde1);
    kunder.add(kunde2);

    bilutleieselskap.setKunder(kunder);

    LocalDateTime startUtleie1 = Utility.parseDato("15.02.2021");
    LocalDateTime startUtleie2 = Utility.parseDato("08.02.2021");
    LocalDateTime sluttUtleie1 = Utility.parseDato("20.02.2021");
    LocalDateTime sluttUtleie2 = Utility.parseDato("22.02.2021");

    controller.leieBil(uk1, ford, kunde1, startUtleie2, sluttUtleie2, "123");
    controller.leieBil(uk1, honda, kunde2, startUtleie1, sluttUtleie1, "123");
    controller.leieBil(uk2, audi, kunde1, startUtleie2, sluttUtleie2, "123");
    controller.leieBil(uk2, tesla, kunde2, startUtleie1, sluttUtleie1, "123");
    
  }
  
  /**
   * Leser input fra konsoll og parser til int. Om input ikke er int må bruker oppgi på nytt.
   * @return int fra konsoll
   */
  public int readInt() {
    boolean isInt = false;
    int x = 0;
    while (!isInt) {
      try {
        x = Integer.parseInt(in.nextLine());
        isInt = true;
      } catch (NumberFormatException e) {
        System.out.println("Du må skrive et tall...");
      }
    }
    return x;
  }

  /**
   * Leser neste linje fra konsollen
   * @return neste linje
   */
  public String readString() {
      return in.nextLine();
  }

  /**
   * Skriv ut melding og les inn String fra konsoll
   * @param message
   * @return String
   */
  public String messageAndReadString(String message) {
    System.out.println(message);
    return readString();
  }

  /**
   * Skriv ut melding og les inn Integer fra konsoll
   * @param message
   * @return int
   */
  public int messageAndReadInt(String message) {
    System.out.println(message);
    return readInt();
  }

}
