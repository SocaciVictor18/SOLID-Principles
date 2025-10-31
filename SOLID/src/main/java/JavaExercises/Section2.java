package JavaExercises;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Section2 {
        static void main (String[] args){
        //EX1
        Box<String> box = new Box<>();
        box.set("Hello");
        System.out.println(box.get());

        //EX2
        System.out.println(sum(List.of(1, 2, 3, 4, 5)));

        //EX3
        List<Person> people = List.of(
            new Person("John", 30),
            new Person("Jane", 25),
            new Person("Bob", 35)
        );

        List<Person>  byAge = people.stream()
                .sorted()
                .toList();

        List<Person> byName = people.stream()
                .sorted(Comparator.comparing(Person::getName))
                .toList();

        byAge.forEach(System.out::println);
        byName.forEach(System.out::println);

        //Ex 4
        Map<String, Double> products = new HashMap<>();
            products.put("Laptop", 1200.0);
            products.put("Phone", 800.0);
            products.put("Tablet", 600.0);
            products.put("Monitor", 300.0);

        products.keySet()
                .stream()
                .sorted()
                .forEach(System.out::println);

        products.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(e -> System.out.println("Most expensive "+e.getKey()+" = "+e.getValue()));
        }

    private static double sum(List<? extends Number> list){
        return list.stream().mapToDouble(Number::doubleValue).sum();
    }

    public static class Box<T> {
        private T content;

        public void set(T value){
            this.content = value;
        }
        public T get(){
            return this.content;
        }
    }

    public static class Person implements Comparable<Person>{


        private String name;
        private int age;

        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }

        public int compareTo(Person other) {
            return Comparator.comparingInt(Person::getAge)
                    .thenComparing(Person::getName)
                    .compare(this, other);
        }

        @Override
        public String toString() {
            return String.format("Name: %s, Age: %d", name, age);
        }

        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
    }

}