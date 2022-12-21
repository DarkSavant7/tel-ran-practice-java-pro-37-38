package de.telran.practice10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Vector;

public class MyStackImpl<T> implements MyStack<T> {
    //public class MyStackImpl<T extends Comparable> implements MyStack<T> {
    private static int DEFAULT_SIZE = 10;
    private static float DEFAULT_GROWTH_RATE = 2;
    private Object[] vector;
    private int pointer;
    private float growthRate;
//    private Comparator<T> comparator;

    public MyStackImpl() {
        vector = new Object[DEFAULT_SIZE];
        pointer = -1;
        growthRate = DEFAULT_GROWTH_RATE;
    }

//    public MyStackImpl(Comparator<T> comparator) {
//        super();
//        this.comparator = comparator;
//    }

    public MyStackImpl(float growthRate) {
        vector = new Object[DEFAULT_SIZE];
        pointer = -1;
        this.growthRate = growthRate; //@TODO check grouth rate min and max value (1 <-> 2)
    }

    @Override
    public T push(T el) {
//        T el1 = el;
//        T el2 = el;
//        if (comparator != null) {
//            int compareResult = comparator.compare(el1, el2);
//        } else {
//            int compareResult = ((Comparable) el1).compareTo(el2);
//        }
        if (needToGrow()) {
            grow();
        }
        vector[++pointer] = el;
        return el;
    }

    @Override
    public T pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        T el = (T) vector[pointer];
        vector[pointer--] = null;
        return el;
    }

    private void grow() {
        int newLength = (int) (vector.length * growthRate);
        Object[] newVector = Arrays.copyOf(vector, newLength);
        vector = newVector;
    }

    private boolean needToGrow() {
        return pointer == vector.length - 1;
    }


    @Override
    public boolean empty() {
        return pointer < 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= pointer; i++) {
            if (i != pointer) {
                sb.append(vector[i]).append(", ");
            } else {
                sb.append(vector[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
