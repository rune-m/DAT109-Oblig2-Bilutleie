package no.hvl.dat109.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.ArrayList;

import no.hvl.dat109.controller.Controller;
import no.hvl.dat109.model.*;
import no.hvl.dat109.utils.Utility;

public class UtleiekontorTest {

	private Controller controller = new Controller();

	private Utleiekontor kontor = new Utleiekontor();
	private Bil bil1 = new Bil("ST12345", "Audi", "modell", "Svart", 12345, Utleiegruppe.MEDIUM);
	private Bil bil2 = new Bil("AB98765", "Audi", "modell", "Svart", 12345, Utleiegruppe.LITEN);
	private Bil bil3 = new Bil("EL12345", "Audi", "modell", "Svart", 12345, Utleiegruppe.STOR);
	private List<Bil> biler = new ArrayList<>();

	@BeforeEach
	public void oppsett() {
		biler.add(bil1);
		biler.add(bil2);
		biler.add(bil3);

		kontor.setBiler(biler);

		Kunde kunde = new Kunde("S", "R", new Adresse("H 12", 3214, "Tubbe"), 12345678);

		Reservasjon reservasjon1 = new Reservasjon("123", Utility.parseDato("10.02.2021"),
				Utility.parseDato("15.02.2021"), kunde, bil1);
		Reservasjon reservasjon2 = new Reservasjon("123", Utility.parseDato("13.02.2021"),
				Utility.parseDato("16.02.2021"), kunde, bil2);
		Reservasjon reservasjon3 = new Reservasjon("123", Utility.parseDato("25.02.2021"),
				Utility.parseDato("27.02.2021"), kunde, bil3);
		Reservasjon reservasjon4 = new Reservasjon("123", Utility.parseDato("17.02.2021"),
				Utility.parseDato("23.02.2021"), kunde, bil1);

		bil1.leggTilReservasjon(reservasjon1);
		bil2.leggTilReservasjon(reservasjon2);
		bil3.leggTilReservasjon(reservasjon3);
		bil1.leggTilReservasjon(reservasjon4);

	}

	@Test
	public void bilerIListe() {
		assertEquals(kontor.getBiler(), biler);
	}

	@Test
	public void sokEtterBilerOgFaarRetur() {
		List<Bil> utleiegruppeBiler = kontor.sokEtterBil(Utleiegruppe.STOR, Utility.parseDato("01.01.2021"),
				Utility.parseDato("05.01.2021"));
		assertTrue(!utleiegruppeBiler.isEmpty());
	}

  @Test
  public void sokEtterStorBilOgDenErLedig() {
    List<Bil> biler = kontor.sokEtterBil(Utleiegruppe.STOR, Utility.parseDato("01.01.2021"), Utility.parseDato("03.01.2021"));
    assertTrue(biler.size() == 1 && biler.get(0).equals(biler.get(0)));
  }

  @Test
  public void sokEtterStorBilOgDenErIkkeLedig() {
      List<Bil> biler = kontor.sokEtterBil(Utleiegruppe.STOR, Utility.parseDato("25.02.2021"), Utility.parseDato("26.02.2021"));
      assertTrue(biler.size() == 0);
  }


}
