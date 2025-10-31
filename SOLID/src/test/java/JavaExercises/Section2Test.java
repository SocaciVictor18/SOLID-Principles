package JavaExercises;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class Section2Test {

    /*
    * The compiler will issue a warning about this method. It’ll warn that we’re using a raw-typed collection.
    * If we don’t want to fix the warning, then we can suppress it with the @SuppressWarnings annotation.
    This annotation allows us to say which kinds of warnings to ignore. While warning types can vary by compiler vendor,
    * the two most common are deprecation and unchecked.
    */
    @SuppressWarnings("unchecked")
    private static double invokePrivateSum(List<? extends Number> list){
        try{
            var m = Section2.class.getDeclaredMethod("sum", List.class);
            m.setAccessible(true);
            return (double) m.invoke(null, list);
        }catch(Exception e){
            throw new RuntimeException("Failed invoking sum", e);
        }
    }
    //EX 1
    @Test
    @DisplayName("Box<String> set/get stores and returns the same String")
    void boxStringSetGet(){
        Section2.Box<String> box = new Section2.Box<>();
        box.set("Hello");
        assertEquals("Hello", box.get());
        assertInstanceOf(String.class, box.get());
    }

    @Test
    @DisplayName("Box<Integer> set/get stores and returns the same Integer")
    void boxIntegerSetGet(){
        Section2.Box<Integer> box = new Section2.Box<>();
        box.set(1);
        assertEquals(1, box.get());
        assertInstanceOf(Integer.class, box.get());
    }

    // EX 2
    @Test
    @DisplayName("sum handles List<Integer>")
    void sumIntegers(){
        double s = invokePrivateSum(List.of(1, 2, 3, 4, 5));
        assertEquals(15, s);
    }

    @Test
    @DisplayName("sum handles List<Number> with mixed numeric types")
    void sumMixedNumberList() {
        List<Number> nums = List.of(1, 2.5, 3L, 4.25f);
        double s = invokePrivateSum(nums); // List<Number> matches <? extends Number>
        assertEquals(1 + 2.5 + 3 + 4.25, s, 1e-9);
    }

    // Ex3

    @Test
    @DisplayName("Natural order (Comparable) sorts by age, then by name for ties")
    void personComparableByAgeThenName() {
        List<Section2.Person> people = new ArrayList<>(List.of(
                new Section2.Person("John", 30),
                new Section2.Person("Jane", 25),
                new Section2.Person("Bob", 35),
                new Section2.Person("Alice", 30)
        ));

        List<Section2.Person> sorted = people.stream().sorted().toList();

        List<String> expectedNames = List.of("Jane", "Alice", "John", "Bob");
        assertEquals(expectedNames,
                sorted.stream().map(Section2.Person::getName).collect(Collectors.toList()));
    }

    @Test
    @DisplayName("Comparator by name sorts alphabetically regardless of age")
    void personComparatorByName(){
        List<Section2.Person> people = new ArrayList<>(List.of(
                new Section2.Person("John", 30),
                new Section2.Person("Jane", 25),
                new Section2.Person("Bob", 35),
                new Section2.Person("Alice", 30)
        ));

        List<Section2.Person> sorted = people.stream().sorted(Comparator.comparing(Section2.Person::getName)).toList();

        List<String> expectedNames = List.of("Alice", "Bob", "Jane", "John");
        assertEquals(expectedNames,
                sorted.stream().map(Section2.Person::getName).collect(Collectors.toList()));
    }

    @Test
    @DisplayName("Person.toString follows 'Name: %s, Age: %d'")
    void personToString(){
        Section2.Person person = new Section2.Person("John", 30);
        assertEquals("Name: John, Age: 30", person.toString());
    }

    //EX 4
    @Test
    @DisplayName("Keys are printed sorted; we can compute sorted order from the map")
    void productKeySorted(){
        Map<String, Double> products = new HashMap<>();
        products.put("Laptop", 1200.0);
        products.put("Phone", 800.0);
        products.put("Tablet", 600.0);
        products.put("Monitor", 300.0);

        List<String> sortedKeys = products.keySet()
                .stream()
                .sorted()
                .toList();

        assertEquals(List.of("Laptop", "Monitor", "Phone", "Tablet"), sortedKeys);

    }

    @Test
    @DisplayName("Find most expensive product using max by value")
    void mostExpensiveProduct(){
        Map<String, Double> products = new HashMap<>();
        products.put("Laptop", 1200.0);
        products.put("Phone", 800.0);
        products.put("Tablet", 600.0);
        products.put("Monitor", 300.0);

        var maxEntry = products.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        assertEquals("Laptop", maxEntry.getKey());
        assertEquals(1200.0, maxEntry.getValue());
    }
}
