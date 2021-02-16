package no.hvl.dat109.tests.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import no.hvl.dat109.controller.Controller;
import no.hvl.dat109.model.Adresse;
import no.hvl.dat109.model.Bil;
import no.hvl.dat109.model.Bilutleieselskap;
import no.hvl.dat109.model.Kunde;
import no.hvl.dat109.model.Reservasjon;
import no.hvl.dat109.model.Utleiegruppe;
import no.hvl.dat109.model.Utleiekontor;
import no.hvl.dat109.utils.Utility;

public class ControllerTest {

  private Controller controller = new Controller();

  private Utleiekontor kontor;
  private Bil ford;
  private Kunde kunde;

  @BeforeEach
  public void oppsett() {
    Bilutleieselskap bilutleieselskap = new Bilutleieselskap("SR Biler", 12345678, new Adresse("Bilgaten 10", 5018, "Bergen"));
    
    ford = new Bil("AB 11111", "Ford", "Focus", "Blå", 100_000, Utleiegruppe.MEDIUM);
    List<Bil> biler = new ArrayList<>();
    biler.add(ford);

    kontor = new Utleiekontor(new Adresse("Utleiegaten 50", 5075, "Fantoft"), 98010905, biler);
    kunde = new Kunde("Simen", "Mathisen", new Adresse("Flåteskjærveien", 3045, "Tjøme"), 45423999);

    controller.leieBil(kontor, ford, kunde, Utility.parseDato("01.01.2021"), Utility.parseDato("02.01.2021"), "123");

  }

  @Test
  public void testParseDato() {
    LocalDateTime dateTime = LocalDateTime.of(2021, 01, 01, 0, 0);
    System.out.println(dateTime);
    assertEquals(Utility.parseDato("01.01.2021"), dateTime);
    assertEquals(Utility.parseDato("1.1.2021"), dateTime);
    assertNotEquals(Utility.parseDato("1..2021"), dateTime);
  }

  @Test
  public void leieBil() {
    Reservasjon reservasjon = controller.leieBil(kontor, ford, kunde, Utility.parseDato("01.02.2021"), Utility.parseDato("02.02.2021"), "123");

    assertTrue(kontor.getReservasjoner().contains(reservasjon));
    assertTrue(ford.getReservasjoner().contains(reservasjon));
    assertTrue(kunde.getReservasjon() != null);

  }

  @Test
  public void returnereBil() {
    int reservasjonId = 1;

    Reservasjon reservasjon = kontor.getReservasjoner().stream().filter(r -> r.getReservasjonId() == reservasjonId).findAny().orElse(null);

    controller.returnerBil(reservasjonId, kontor);

    assertFalse(kontor.getReservasjoner().contains(reservasjon));
    assertFalse(ford.getReservasjoner().contains(reservasjon));
    assertTrue(kunde.getReservasjon() == null);

  }
  
}
