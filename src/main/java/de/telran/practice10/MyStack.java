package de.telran.practice10;

public interface MyStack<T> {
    T push(T el);
    T pop();
    boolean empty();
}
