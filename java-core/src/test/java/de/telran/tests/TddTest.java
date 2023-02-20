package de.telran.tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TddTest {

    @Test
    void greetingTest() {
        Greeter greeter = new Greeter();
        assertEquals("Hello Alex!", greeter.greet("Alex"));
    }

    @Test
    void goodbyeTest() {
        Greeter greeter = new Greeter();
        assertEquals("GoodBye Alex!", greeter.goodbye("Alex"));
    }
}
