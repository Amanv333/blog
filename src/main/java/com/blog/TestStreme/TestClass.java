package com.blog.TestStreme;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass {
    public static void main(String[] args) {
        Person p1 = new Person("Mike",28);
        Person p2 = new Person("Ravi",22);
        Person p3 = new Person("Stallin",27);
        Person p4 = new Person("Adam",32);
        Person p5 = new Person("Sam",25);
        List<Person> list = Arrays.asList(p1,p2,p3,p4,p5);
        List<Person> result = list.stream().filter(y->y.getName().contains("a")).collect(Collectors.toList());
        result.forEach(person -> System.out.println(person.getName()));


    }
    }

