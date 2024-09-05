package com.example.hibernate.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    }
}
