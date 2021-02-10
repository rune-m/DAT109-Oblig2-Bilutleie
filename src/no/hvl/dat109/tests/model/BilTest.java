package no.hvl.dat109.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;

import no.hvl.dat109.controller.Controller;
import no.hvl.dat109.model.*;

public class BilTest {

  private Bil bil;
  private Controller controller = new Controller();

	@BeforeEach
	public void oppsett() {
    Kunde kunde = new Kunde("S", "R", new Adresse("H 12", 3214, "Tubbe"), 12345678);
		bil = new Bil("AB 12345", "Abba", "Waterloo", "Grønn", 10000, Utleiegruppe.LITEN);
    Reservasjon reservasjon1 = new Reservasjon("123", controller.parseDato("10.02.2021"), controller.parseDato("15.02.2021"), kunde, bil);
    bil.leggTilReservasjon(reservasjon1);
	}

  @Test
  public void bilErLedig() {
    assertTrue(bil.erLedig(controller.parseDato("05.02.2021"), controller.parseDato("09.02.2021")));
  }
  
  @Test
  public void bilErIkkeLedig() {
    assertFalse(bil.erLedig(controller.parseDato("11.02.2021"), controller.parseDato("16.02.2021")));
    assertFalse(bil.erLedig(controller.parseDato("09.02.2021"), controller.parseDato("12.02.2021")));
    assertFalse(bil.erLedig(controller.parseDato("13.02.2021"), controller.parseDato("14.02.2021")));
  }

}
