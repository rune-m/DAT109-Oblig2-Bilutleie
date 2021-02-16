package no.hvl.dat109.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import no.hvl.dat109.model.Utleiegruppe;

/**
 * Klasse med ekstra utilities
 * @author Simen og Rune
 */
public class Utility {

  /**
   * Konverterer Utleiegruppe enum til String
   * @param ug utleiegruppe
   * @return String
   */
  public static String enumToString(Utleiegruppe ug) {
		String s = ug.toString().toLowerCase();
		return s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
	}

/**
   * Parse en dato (String) til et LocalDateTime-objekt
   * @param dato
   * @return LocalDateTime ved korrekt input, evt. null
   */
  public static LocalDateTime parseDato(String dato) {

    String nyDato = "";

    // Tar hensyn til om kunden skriver dato eller måned med ett siffer. Plasserer "0" foran hvis mindre enn 10
    try {
      String[] datoSplit = dato.split("\\.", 5);
      
      for (int i = 0; i < 2; i++) {
        if (datoSplit[i].length() == 1) {
          datoSplit[i] = "0" + datoSplit[i];
        }
        nyDato += datoSplit[i] + ".";
      }

      nyDato += datoSplit[2];
    } catch (ArrayIndexOutOfBoundsException e) {
    }
  
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    LocalDateTime dateTime = null;
    nyDato += " 00:00";

    try {
      dateTime = LocalDateTime.parse(nyDato, formatter);
    } catch (DateTimeParseException e) {
    }
    return dateTime;
  }

  /**
   * Legger til 23 timer og 59 minutter for LocalDateTime slutt.
   * @param slutt
   * @return slutt
   */
  public static LocalDateTime formatterSluttdato(LocalDateTime slutt) {
      slutt = slutt.plusHours(23);
      slutt = slutt.plusMinutes(59);
      return slutt;
  }


}
