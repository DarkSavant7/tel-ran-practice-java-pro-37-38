package de.telran.practice17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SpyTest {

    @Spy
    ArrayList<String> list;

    @Test
    void testMockReturnZero() {
        String s = "!!!";
        list.add(s);

        Mockito.verify(list).add(s);
        Assertions.assertEquals(1, list.size());
    }
}


