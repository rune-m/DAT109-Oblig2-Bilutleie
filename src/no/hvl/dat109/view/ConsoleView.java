package no.hvl.dat109.view;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

import no.hvl.dat109.controller.Controller;
import no.hvl.dat109.model.Adresse;
import no.hvl.dat109.model.Bilutleieselskap;
import no.hvl.dat109.model.Kunde;
import no.hvl.dat109.model.Utleiegruppe;
import no.hvl.dat109.model.Utleiekontor;
import no.hvl.dat109.model.Bil;
import no.hvl.dat109.model.Reservasjon;

public class ConsoleView {

  private static Scanner in = new Scanner(System.in);
  private Controller controller;
  private Bilutleieselskap bilutleieselskap;

  // Bil(int regNr, String merke, String modell, String farge, int kmStand, Utleiegruppe utleiegruppe)
  // Utleiekontor (Adresse adresse, int telefonnummer, List<Bil> biler, List<Reservasjon> reservasjoner)
  public ConsoleView() {
    bilutleieselskap = new Bilutleieselskap("SR Biler", 12345678, new Adresse("Bilgaten 10", 5018, "Bergen"));
    controller = new Controller(bilutleieselskap);
  }

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

    LocalDateTime startUtleie1 = controller.parseDato("15.02.2021");
    LocalDateTime startUtleie2 = controller.parseDato("08.02.2021");
    LocalDateTime sluttUtleie1 = controller.parseDato("20.02.2021");
    LocalDateTime sluttUtleie2 = controller.parseDato("22.02.2021");

    Reservasjon reservasjon1 = new Reservasjon("0123 4567 8901", startUtleie1, sluttUtleie1, kunde1, honda);
    Reservasjon reservasjon2 = new Reservasjon("1234 4321 1234 4321", startUtleie2, sluttUtleie2, kunde2, ford);
    List<Reservasjon> reservasjoner1 = new ArrayList<>();
    reservasjoner1.add(reservasjon1);
    reservasjoner1.add(reservasjon2);

    Reservasjon reservasjon3 = new Reservasjon("0123 4567 8901 1234", startUtleie1, sluttUtleie1, kunde1, audi);
    Reservasjon reservasjon4 = new Reservasjon("1234 4321 1234 4322", startUtleie2, sluttUtleie2, kunde2, tesla);
    List<Reservasjon> reservasjoner2 = new ArrayList<>();
    reservasjoner2.add(reservasjon3);
    reservasjoner2.add(reservasjon4);

    uk1.setReservasjoner(reservasjoner1);
    uk2.setReservasjoner(reservasjoner2);
    
  }

  public void start() {

    System.out.println("Hei, velkommen til " + bilutleieselskap.getNavn());
    System.out.println("Hva ønsker du?" + "\n 1 - Leie bil\n 2 - Returnere bil");
    int valg = readInt();
    switch(valg) {
        case 1:
            leieBil(); break;
        case 2: 
            returerBil(); break;
        default:
            System.out.println("Ugyldig valg!");
    }

  }
  
  /**
   * 1
   */
  public void leieBil() {
    System.out.println("Leier bil...");

    // Kunde kunde = lesInnKunde();
    Kunde kunde = new Kunde("Ola", "Nordmann", new Adresse("Norge 1", 0001, "Norge"), 12345678);

    controller.getKontorer().forEach(System.out::println); // TODO Lag toString() i Utleiekontor!
    
    int kontorId = messageAndReadInt("Velg et kontor(Tall): ");
    Utleiekontor kontor = controller.velgeUtleiekontor(kontorId);
    // controller.sokEtterBilerKontor(kontor, utleiegruppe, startUtleie, sluttUtleie)

    
    kontor.getBiler().forEach(System.out::println);

  }

  /**
   * 2
   */
  public void returerBil() {
    System.out.println("Returnerer bil...");
  }


  public Kunde lesInnKunde() {
    // For å lage kunde.
    String fornavn = messageAndReadString("Skriv fornavn...");
    String etternavn = messageAndReadString("Skriv etternavn...");
    int tlf = messageAndReadInt("Skriv inn telefonnummer...");

    // For å lage adresse.
    String gateadresse = messageAndReadString("Skriv inn gateadresse og nummer...");
    int postnummer = messageAndReadInt("Skriv postnummer...");
    String poststed = messageAndReadString("Skriv poststed...");

    return controller.nyKunde(fornavn, etternavn, new Adresse(gateadresse, postnummer, poststed), tlf);
  }

  // TODO Legge til i utility-klasse?
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

  public String readString() {
      return in.nextLine();
  }

  public String messageAndReadString(String message) {
    System.out.println(message);
    return readString();
  }

  public int messageAndReadInt(String message) {
    System.out.println(message);
    return readInt();
  }

}
