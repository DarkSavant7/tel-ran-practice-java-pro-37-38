package de.telran.stack_example;

public interface MyStack<T> {
    T push(T el);
    T pop();
    boolean empty();
}
