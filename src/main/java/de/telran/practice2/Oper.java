package de.telran.practice2;

public enum Oper {
    SUB {
       public int operate(int one, int two) {
            return one - two;
        }
    },
    MUL {
        public int operate(int one, int two) {
            return one * two;
        }
    };

    public abstract int operate(int one, int two);
}
