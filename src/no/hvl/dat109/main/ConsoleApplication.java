package no.hvl.dat109.main;

import no.hvl.dat109.view.ConsoleView;

/**
 * Klasse for å starte applikasjonen i konsollen
 * @author Simen og Rune
 */
public class ConsoleApplication {

  public static void main(String[] args) {
    
    ConsoleView view = new ConsoleView();
    
    view.leggTilData();
    view.start();

  }
  
}
