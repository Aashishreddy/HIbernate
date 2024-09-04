package com.example.hibernate.generics;

public class GenericsJavaApplication {
    public static void main(String[] args) {

        //this will call the constructor and gives a value to thisPrints
        GenericPrinter<Integer> printInteger = new GenericPrinter<>(23);
        printInteger.print();

        GenericPrinter<String> printString = new GenericPrinter<>("Hello");
        printString.print();

        printing("X");
        printing(50);
    }

    public static <T> void printing(T printEverything) {
        System.out.println(printEverything);
    }
}
