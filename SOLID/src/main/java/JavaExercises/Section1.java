package JavaExercises;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Section1 {
    static void main(String[] args)
    {
        Ex1();
        Ex2();
        Ex3();
        Ex4();
    }

    private static void Ex4() {
        List<String> users = List.of("Victor", "Raul", "Ionut", "Madalina", "Stefan", "Teodor");

        users.stream()
                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::println);
    }

    private static void Ex3() {
        List<String> users = List.of("Victor", "Raul", "Ionut", "Madalina", "Stefan", "Teodor");

        Supplier<LocalDate> dateSupplier = LocalDate::now;
        Consumer<String> printString = System.out::println;

        users.stream()
                .map(name -> String.format("Welcome, %s! Today is %s", name, dateSupplier.get()))
                .forEach(printString);

        /*Consumer<String> greeter = user ->
                System.out.printf("Welcome, %s! Today is %s.%n", user, dateSupplier.get());
        users.forEach(greeter);*/
    }

    private static void Ex2() {
        List<String> names = List.of("Alice", "Bob", "Charlie", "David", "Eve"); // Instead of List.of if I was using ArrayList it would throw a null pointer exception
        Function<String, Integer> nameToLength = String::length; // Or you could use a lambda expression (name -> name.length())

        names.stream()
                .map(name -> name + " -> " + nameToLength.apply(name))
                .forEach(System.out::println);

    }

    private static void Ex1(){
        IntStream numbers = IntStream.rangeClosed(1, 20); // Best Option
        IntStream numbersWay2 = IntStream.range(1, 21);

        Stream<Integer> numbersStream = Stream
                .iterate(1, n -> n + 1)
                .limit(20);
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Predicate<Integer> greaterThan10 = n -> n > 10;

        IntPredicate isEven2 = n -> n % 2 == 0;
        IntPredicate greaterThan10Int = n -> n > 10;

        // When you use IntStream, you MUST use IntPredicate
        numbers.filter(greaterThan10Int.and(isEven2)).forEach(System.out::println);
/*     Here you use Stream<Integer> so you MUST use Predicate<Integer>
        numbersStream
                .filter(greaterThan10)
                .filter(isEven)
                .forEach(System.out::println);
*/

        List<Integer> numbersList = IntStream.rangeClosed(1,20).boxed().toList();

        List<Integer> allNumbersThatAreEven = numbersList.stream().filter(isEven).toList();
        List<Integer> allNumbersThatAreOdd = numbersList.stream().filter(isEven.negate()).toList();
        List<Integer> allNumbersThatAreGreaterThan10 = numbersList.stream().filter(greaterThan10).toList();
        List<Integer> allNumbersThatAreLessThan10 = numbersList.stream().filter(greaterThan10.negate()).toList();
        List<Integer> allNumbersThatAreEvenAndGreaterThan10 = numbersList.stream().filter(isEven.and(greaterThan10)).toList();
        List<Integer> allNumbersThatAreOddAndGreaterThan10 = numbersList.stream().filter(isEven.negate().and(greaterThan10)).toList();


        System.out.print("List of numbers that are even: ");
        System.out.println(allNumbersThatAreEven);
        System.out.print("List of numbers that are odd: ");
        System.out.println(allNumbersThatAreOdd);
        System.out.print("List of numbers that are greater than 10: ");
        System.out.println(allNumbersThatAreGreaterThan10);
        System.out.print("List of numbers that are less than 10: ");
        System.out.println(allNumbersThatAreLessThan10);
        System.out.print("List of numbers that are even and greater than 10: ");
        System.out.println(allNumbersThatAreEvenAndGreaterThan10);
        System.out.print("List of numbers that are odd and greater than 10: ");
        System.out.println(allNumbersThatAreOddAndGreaterThan10);

    }
}
