package de.telran.practice17;

import java.io.FileNotFoundException;

public class Calculator {
    // https://junit.org/junit5/
    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

    public int mul(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        return a / b;
    }

    public void throwException() throws FileNotFoundException {
        throw new FileNotFoundException("AAA");
    }
}
