package com.example.hibernate.generics;

public class GenericPrinter<T> {

    T thisPrints;
    public GenericPrinter(T thisPrints){
        this.thisPrints = thisPrints;
    }

    public void print(){
        System.out.println(thisPrints);
    }
}
