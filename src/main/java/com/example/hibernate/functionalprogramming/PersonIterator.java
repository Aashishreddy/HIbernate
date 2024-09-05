package com.example.hibernate.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Map;

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

        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> integerList = new ArrayList<>(Arrays.asList(integers));

        //multiply each element by 2 using streams and Map
        getListTimesTwo(integerList);

        //define a function outside for better readability
        getListTimeFourUsingFunction(integerList);

        //Get Even numbers
        getEvenNumber(integerList);

        //Even numbers using filter and predicate for better readability
        predicate(integerList);

        //sum of numbers in the list using reduce
        getSum(integerList);

        String[] strings = {"Aashish Reddy", "Vishal Veera", "Chinnu", "Abhi", "Varun", "Harsha",
                            "Abhishek", "Shanthan", "Bucks", "Para", "Srilu"};
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));

        //Get Strings Greater Than size 6
        getStringsGreaterThan6(stringList);

        //function to get String greater than x
        getStringsGreaterThan(stringList);

        //perform collect operations
        collectOperations(stringList);
    }

    public static void getListTimesTwo(List<Integer> integerList) {
        List<Integer> multipliedList = integerList.stream()
                .map(integer -> integer * 2)
                .collect(Collectors.toList());
        multipliedList.forEach(System.out::println);
        System.out.println("IntegerList times two: " + multipliedList);
    }

    public static void getListTimeFourUsingFunction(List<Integer> integerList) {
        Function<Integer, Integer> timesFour = (x) -> x * 4;
        List<Integer> timesFourList = integerList.stream()
                .map(timesFour)
                .collect(Collectors.toList());
        System.out.println("IntegerList times four using Function Object: " + timesFourList);
    }

    public static void getEvenNumber(List<Integer> integerList) {
        List<Integer> evenNumbers = integerList.stream()
                .filter((x) -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even Numbers using filter: " + evenNumbers);
    }

    public static void predicate(List<Integer> integerList) {
        Predicate<Integer> predicateFunction = (x) -> x % 2 == 0;
        List<Integer> predicateList = integerList.stream().filter(predicateFunction).collect(Collectors.toList());
        System.out.println("Even Numbers using Predicate: " + predicateList);
    }

    public static void getStringsGreaterThan6(List<String> stringList) {
        Predicate<String> predicateFunc = (x) -> x.length() > 6;
        List<String> resultList = stringList.stream()
                .filter(predicateFunc)
                .collect(Collectors.toList());
        System.out.println("Strings greater than 6: " + resultList);
    }

    public static void getStringsGreaterThan(List<String> stringList) {
        Function<Integer, Predicate<String>> func = (minLen) -> {
            return (str) -> str.length() > minLen;
        };

        Predicate<String> stringsGreaterThan5 =  func.apply(5);
        Predicate<String> stringsGreaterThan6 = func.apply(6);
        Predicate<String> stringsGreaterThan7 = func.apply(7);

        List<String> resultList = stringList.stream().filter(stringsGreaterThan5).collect(Collectors.toList());
        System.out.println("Function to getStrings greater than x: " + resultList);
    }

    public static void getSum(List<Integer> integerList) {

        //Reduce uses BinaryOperator
        BinaryOperator<Integer> getSum = (acc, x) -> {
            Integer result = acc + x;
            System.out.println("Accumulator: " + acc + ", Number: " + x + ", result: " + result);
            return result;
        };

        Integer sum = integerList.stream().reduce(0, getSum);
        System.out.println("Sum: " + sum);
    }

    public static void collectOperations(List<String> stringList) {

        //.counting()
        Long stringListSize = stringList.stream().collect(Collectors.counting());
        System.out.println("Collect.counting(): " + stringListSize);

        //.groupingBy based on length
        Function<String, Integer> lenFunc = (word) -> word.length();
        Map<Integer, List<String>> resultMap = stringList
                                                    .stream()
                                                    .collect(Collectors.groupingBy(lenFunc));
        System.out.println("Collectors.groupingBy(): " + resultMap);

        //.partitioningBy
        Predicate<String> predFunc = (word) -> word.length() > 5;
        Map<Boolean, List<String>> partitionCollectList = stringList
                                                            .stream()
                                                            .collect(Collectors.partitioningBy(predFunc));
        System.out.println("Collectors.partitioningBy(): " + partitionCollectList);
    }
}
