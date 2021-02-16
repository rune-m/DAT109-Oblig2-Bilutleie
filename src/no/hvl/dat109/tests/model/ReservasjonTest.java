package no.hvl.dat109.tests.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;

import java.time.temporal.*;

import no.hvl.dat109.controller.Controller;
import no.hvl.dat109.model.*;
import no.hvl.dat109.utils.Utility;

public class ReservasjonTest {

    private Bil bil;
    private Reservasjon reservasjon1;
    private Controller controller = new Controller();
    private static final int prisLiten = 350;
    private static final int prisMedium = 500;
    private static final int prisStor = 750;
    private static final int prisStasjonsvogn = 950;
  
      @BeforeEach
      public void oppsett() {
        Kunde kunde = new Kunde("S", "R", new Adresse("H 12", 3214, "Tubbe"), 12345678);
            bil = new Bil("AB 12345", "Abba", "Waterloo", "Grønn", 10000, Utleiegruppe.LITEN);
        reservasjon1 = new Reservasjon("123", Utility.parseDato("10.02.2021"), Utility.parseDato("15.02.2021"), kunde, bil);
        bil.leggTilReservasjon(reservasjon1);
      }


      @Test
      public void riktigPris() {
		long antallDager = reservasjon1.getStartUtleie().until(reservasjon1.getSluttUtleie(), ChronoUnit.DAYS) + 1;
       
        Utleiegruppe ug = bil.getUtleiegruppe();

        if (ug.equals(Utleiegruppe.LITEN)) {
            assertEquals(reservasjon1.prisForLeie(), prisLiten*antallDager);
        } else if (ug.equals(Utleiegruppe.MEDIUM)) {
            assertEquals(reservasjon1.prisForLeie(), prisMedium*antallDager);
        } else if (ug.equals(Utleiegruppe.STOR)) {
            assertEquals(reservasjon1.prisForLeie(), prisStor*antallDager);
        } else {
            assertEquals(reservasjon1.prisForLeie(), prisStasjonsvogn*antallDager);
        }
	
    }
}	

