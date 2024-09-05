package com.example.hibernate.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PersonIterator {

    public static void main(String[] args) {
        Person p1 = Person.builder().name("Aashish").gender("MALE").build();
        Person p2 = Person.builder().name("Bob").gender("FEMALE").build();
        Person p3 = Person.builder().name("David").gender("MALE").build();
        Person p4 = Person.builder().name("Jack").gender("FEMALE").build();

        ArrayList<Person> peopleList = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

        //To retrieve females
        //APPROACH 1
        System.out.println("Approach 1");
        for(Person person : peopleList) {
            if(person.getGender().equals("FEMALE")) {
                System.out.println(person);
            }
        }

        //APPROACH 2 using Streams and filters
        System.out.println("Approach 2");
        List<Person> females = peopleList.stream()
                                            .filter(person -> person.getGender().equals("FEMALE"))
                                            .collect(Collectors.toList());
        females.forEach(System.out::println);

        //multiply each element by 2 using streams
        Integer[] integers = {1, 2, 3, 4, 5};
        List<Integer> integerList = new ArrayList<>(Arrays.asList(integers));
        List<Integer> multipliedList = integerList.stream().map(integer -> integer * 2).collect(Collectors.toList());
        multipliedList.forEach(System.out::println);
        System.out.println(multipliedList);

        //define a function outside
        Function<Integer, Integer> timesFour = (x) -> x * 4;
        List<Integer> timesFourList = integerList.stream()
                                            .map(timesFour)
                                            .collect(Collectors.toList());
        System.out.println(timesFourList);
    }
}
