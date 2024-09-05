package com.example.hibernate.generics;

import com.example.hibernate.domain.entities.AuthorEntity;

import java.util.ArrayList;
import java.util.List;

public class GenericsJavaApplication {
    public static void main(String[] args) {

        //this will call the constructor and gives a value to thisPrints
        GenericPrinter<Integer> printInteger = new GenericPrinter<>(23);
        printInteger.print();

        GenericPrinter<String> printString = new GenericPrinter<>("Hello");
        printString.print();

        printing("X");
        printing(50);
        AuthorEntity createTestAuthor = AuthorEntity.builder().id(1234L).name("Aashish").age(25).build();
        printing(createTestAuthor);

        printing("X", createTestAuthor);
        printing("Y", 26);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");

        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);

        createGenericList(stringList);
        createGenericList(integerList);
    }

    public static <T> void printing(T printEverything) {
        System.out.println(printEverything);
    }

    public static <T, V> void printing(T printEverything, V someThing) {
        System.out.println(printEverything + " " + someThing);
    }

    public static void createGenericList(ArrayList<?> input) {
        for(Object i: input) {
            System.out.println(i);
        }
    }
}
