package com.learning.springbootWebApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lambda_Stream {
    public static void main(String[] args) {

        // normal way to call the method
//        Walkable obj = new WalkFast();
//        obj.walk(77);

        // we can directly implement the interface while creating the object
//        Walkable obj1 = new Walkable() {
//            @Override
//            public int walk(int steps) {
//                return 0;
//            }
//        };

        //Functional Interface
//        Walkable obj = (int step) ->{
//            System.out.println("Walking fast " + step +" steps.");
//            return step * 2;
//        };
//
//        obj.walk(32);

        List<String> fruits = List.of("Apple", "Mango", "Guava");

        List<Integer> fruitList = fruits
                .stream()
                .map((fruit) -> fruit.length())
                .collect(Collectors.toList());

        System.out.println(fruitList);

        Stream<String> stream = fruits.stream();
        stream
                .sorted()
                .forEach(System.out::println);   //method reference
    }
}

interface Walkable{
    int walk(int steps);
}

class WalkFast implements Walkable{

    @Override
    public int walk(int steps) {
        System.out.println("Walking fast " + steps +" steps.");
        return steps * 2;
    }
}
