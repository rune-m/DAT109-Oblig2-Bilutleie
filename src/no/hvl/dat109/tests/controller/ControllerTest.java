package no.hvl.dat109.tests.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.*;

import no.hvl.dat109.controller.Controller;

public class ControllerTest {

  private Controller controller = new Controller();

  @Test
  public void testParseDato() {
    LocalDateTime dateTime = LocalDateTime.of(2021, 01, 01, 0, 0);
    System.out.println(dateTime);
    assertEquals(controller.parseDato("01.01.2021"), dateTime);
  }
  
}
