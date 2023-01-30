package de.telran.practice17;

import java.io.FileNotFoundException;

public class Calculator {
    // https://junit.org/junit5/
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public Integer sub(Integer a, Integer b) {
        return a - b;
    }

    public Integer mul(Integer a, Integer b) {
        return a * b;
    }

    public Integer div(Integer a, Integer b) {
        return a / b;
    }

    public void throwException() throws FileNotFoundException {
        throw new FileNotFoundException("AAA");
    }
}
