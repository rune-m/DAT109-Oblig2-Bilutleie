package no.hvl.dat109.main;

import no.hvl.dat109.view.ConsoleView;

public class ConsoleApplication {

  public static void main(String[] args) {
    
    ConsoleView view = new ConsoleView();
    
    view.leggTilData();
    view.start();

  }
  
}
