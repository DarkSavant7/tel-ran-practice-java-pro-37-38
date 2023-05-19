package de.telran.tests;

public class CheckAssert {

    public static void main(String[] args) {
        for (int i = 1; i < 3; i++)
            for (int j = 3; j >= 1; j--)
                assert i != j : i;

        assert 2 == 1;
        System.out.println("EVERYTHING IS DONE");
    }
}
