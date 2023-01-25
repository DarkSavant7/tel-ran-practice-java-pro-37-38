package de.telran.practice17;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calc;

    @BeforeAll
    static void globalInit() {
        System.out.println("GLOBAL INIT");
    }

    @BeforeEach
    void init() {
        System.out.println("Init calculator");
        calc = new Calculator();
    }

    @Test
    void add() {
        int first = 10;
        int second = 14;
        int expected = 24;
        int actual = calc.add(first, second);
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(11, calc.add(5, 6)),
                () -> assertEquals(12, calc.add(6, 6)),
                () -> assertEquals(11, calc.add(5, 6)),
                () -> assertEquals(4, calc.add(2, 2)),
                () -> assertEquals(10, calc.add(5, 5))
        );
    }

    @CsvSource({
            "1,2,3",
            "2,2,4",
            "3,5,5",
            "4,2,6",
            "5,2,7"
    })
    @ParameterizedTest
    void parametrizedTestOne(int a, int b, int res) {
        assertEquals(res, calc.add(a, b));
    }

    @CsvFileSource(files = {"test-data/1.csv", "test-data/2.csv"})
    @ParameterizedTest
    void parametrizedTestTwo(int a, int b, int res) {
        assertEquals(res, calc.add(a, b));
    }

    @MethodSource(value = "addTestDataGenerator")
    @ParameterizedTest
    void parametrizedTestThree(int a, int b, int res) {
        assertEquals(res, calc.add(a, b));
    }

   static Stream<Arguments> addTestDataGenerator() {
        List<Arguments> args = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int a = (int) (Math.random() * 1000);
            int b = (int) (Math.random() * 1000);
            int res = a + b;
            args.add(Arguments.of(a, b, res));
        }
        return args.stream();
    }

    @Test
    void sub() {
        assertEquals(4, calc.sub(14, 10));
    }

    @Test
    void mul() {
        assertEquals(140, calc.mul(14, 10));
    }

    @Test
    void div() {
        assertEquals(2, calc.div(28, 14));
    }

    @Test
    void divByZero_expectedArithmeticException() {
        assertThrows(
                ArithmeticException.class,
                () -> calc.div(10, 0)
        );
    }

    @Test
    void throwException() {
        assertThrows(FileNotFoundException.class, () -> calc.throwException());
        assertThrows(FileNotFoundException.class, () -> new FileWriter("cats"));
    }

    @AfterEach
    void dispose() {
        System.out.println("Dispose");
    }

    @AfterAll
    static void globalDispose() {
        System.out.println("GLOBAL DISPOSE");
    }
}