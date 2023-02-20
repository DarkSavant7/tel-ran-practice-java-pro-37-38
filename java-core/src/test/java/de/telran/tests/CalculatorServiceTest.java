package de.telran.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

class CalculatorServiceTest {

    @Mock
    Calculator calculator;

    @Spy
    ArrayList<String> list;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void arrayListTest() {
        list.add("1");
        assertEquals(1, list.size());
        assertEquals("1", list.get(0));
    }


    @Test
    void add() {
//        Calculator calculator = Mockito.mock(Calculator.class);
//        Calculator calculator = Mockito.spy(Calculator.class);
//        Calculator calc = new Calculator();
//        Calculator calculator = Mockito.spy(calc);
        CalculatorService service = new CalculatorService(calculator);

        //1. when()...then()
        //2. do()...when()..
//        Mockito.when(calculator.add(5, 5))
//        Mockito.when(calculator.add(any(), 5)) does not work
//        Mockito.when(calculator.add(anyInt(), anyInt()))
//        Mockito.when(calculator.add(any(), eq(5)))
//        Mockito.when(calculator.add(any(), same(5)))
//                .thenReturn(8)
//                .thenThrow(ArithmeticException.class);

        Mockito.doReturn(8).when(calculator).add(any(), any());

        assertEquals(8, service.add(5, 5));
//        assertThrows(ArithmeticException.class, () -> service.add(5, 5));
//        Mockito.verify(calculator).add(5, 5);
//        Mockito.verify(calculator, Mockito.timeout(1000)).add(5, 5);
//        Mockito.verify(calculator, Mockito.times(1)).add(5, 5);
//        Mockito.verify(calculator, Mockito.after(200).times(1)).add(5, 5);
//        Mockito.verify(calculator).add(1, 5);
        Mockito.verify(calculator, Mockito.times(0)).add(1, 5);
    }

    @Test
    void sub() {
    }

    @Test
    void mul() {
    }

    @Test
    void div() {
    }
}