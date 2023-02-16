package de.telran.stack_example;

public class Classwork {
    public static void main(String[] args) {
        System.out.println("-----------STACK---------");
        MyStack<Integer> myStack = new MyStackImpl<>();
        myStack.push(5);
        myStack.push(8);
        myStack.push(19);
        myStack.push(3);

        System.out.println(myStack);
        System.out.println(myStack.empty());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack);
    }
}
