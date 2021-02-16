package no.hvl.dat109.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.*;

import no.hvl.dat109.controller.Controller;
import no.hvl.dat109.model.*;
import no.hvl.dat109.utils.Utility;

public class BilTest {

  private Bil bil;
  private Controller controller = new Controller();
  private Reservasjon r1;
  private Reservasjon r2;

	@BeforeEach
	public void oppsett() {
    Kunde kunde = new Kunde("S", "R", new Adresse("H 12", 3214, "Tubbe"), 12345678);
		bil = new Bil("AB 12345", "Abba", "Waterloo", "Grønn", 10000, Utleiegruppe.LITEN);
    Utleiekontor kontor = new Utleiekontor(new Adresse("Utleiegaten 50", 5075, "Fantoft"), 98010905, Arrays.asList(bil));

    r1 = controller.leieBil(kontor, bil, kunde, Utility.parseDato("10.02.2021"), Utility.parseDato("15.02.2021"), "123");
    r2 = controller.leieBil(kontor, bil, kunde, Utility.parseDato("20.02.2021"), Utility.parseDato("20.02.2021"), "123");
	}

  @Test
  public void bilErLedig() {
    assertTrue(bil.erLedig(Utility.parseDato("05.02.2021"), Utility.parseDato("09.02.2021")));
  }
  
  @Test
  public void bilErIkkeLedig() {
    assertFalse(bil.erLedig(Utility.parseDato("11.02.2021"), Utility.parseDato("16.02.2021")));
    assertFalse(bil.erLedig(Utility.parseDato("09.02.2021"), Utility.parseDato("12.02.2021")));
    assertFalse(bil.erLedig(Utility.parseDato("13.02.2021"), Utility.parseDato("14.02.2021")));
    assertFalse(bil.erLedig(Utility.parseDato("13.02.2021"), Utility.parseDato("13.02.2021")));
    assertFalse(bil.erLedig(Utility.parseDato("19.02.2021"), Utility.parseDato("21.02.2021")));
  }

  @Test
  public void nySluttDato() {
    assertEquals(r1.getSluttUtleie().toString(), "2021-02-15T23:59");
    assertNotEquals(r1.getSluttUtleie().toString(), "2021-02-15T23:40");
    assertEquals(r2.getSluttUtleie().toString(), "2021-02-20T23:59");
    assertNotEquals(r2.getSluttUtleie().toString(), "2021-02-15T00:00");
  }

  @Test
  public void startDato() {
    LocalDateTime sluttdato = r1.getStartUtleie();
    assertEquals(sluttdato.toString(), "2021-02-10T00:00");
  }
}
