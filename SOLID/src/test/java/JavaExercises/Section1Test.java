package JavaExercises;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class Section1Test {

    private PrintStream originalOut;
    private ByteArrayOutputStream baos;

    @BeforeEach
    void setup() {
        originalOut = System.out;
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void teardown() {
        System.setOut(originalOut);
    }

    // ------- helpers -------
    private String invokeAndCapture(String privateMethodName) {
        try {
            var m = JavaExercises.Section1.class.getDeclaredMethod(privateMethodName);
            m.setAccessible(true);
            m.invoke(null);
            return baos.toString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Failed invoking " + privateMethodName, e);
        }
    }

    private static List<String> splitLines(String s) {
        return s.lines().toList();
    }


    @Test
    @DisplayName("Ex1 prints expected even/>10 numbers and the summarized lists")
    void testEx1() {
        String out = invokeAndCapture("Ex1");


        for (String n : List.of("12", "14", "16", "18", "20")) {
            assertTrue(Pattern.compile("(?m)^" + n + "$").matcher(out).find(),
                    "Should contain a line with " + n);
        }

        assertTrue(out.contains("List of numbers that are even: [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]"));
        assertTrue(out.contains("List of numbers that are odd: [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]"));
        assertTrue(out.contains("List of numbers that are greater than 10: [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]"));
        assertTrue(out.contains("List of numbers that are less than 10: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"));
        assertTrue(out.contains("List of numbers that are even and greater than 10: [12, 14, 16, 18, 20]"));
        assertTrue(out.contains("List of numbers that are odd and greater than 10: [11, 13, 15, 17, 19]"));
    }

    @Test
    @DisplayName("Ex2 prints {name -> length} for the fixed list")
    void testEx2() {
        String out = invokeAndCapture("Ex2");
        var lines = splitLines(out);

        assertTrue(lines.contains("Alice -> 5"));
        assertTrue(lines.contains("Bob -> 3"));
        assertTrue(lines.contains("Charlie -> 7"));
        assertTrue(lines.contains("David -> 5"));
        assertTrue(lines.contains("Eve -> 3"));
    }

    @Test
    @DisplayName("Ex3 prints Welcome lines with an ISO date")
    void testEx3() {
        String out = invokeAndCapture("Ex3");
        var lines = splitLines(out);


        Pattern p = Pattern.compile("^Welcome, (Victor|Raul|Ionut|Madalina|Stefan|Teodor)! Today is \\d{4}-\\d{2}-\\d{2}$");

        for (String user : List.of("Victor", "Raul", "Ionut", "Madalina", "Stefan", "Teodor")) {
            boolean found = lines.stream().anyMatch(l -> l.startsWith("Welcome, " + user + "! Today is "));
            assertTrue(found, "Missing greeting for " + user);
        }
        for (String line : lines) {
            if (line.startsWith("Welcome, ")) {
                assertTrue(p.matcher(line).matches(), "Line not ISO-date formatted: " + line);
            }
        }
    }

    @Test
    @DisplayName("Ex4 prints users sorted using compareToIgnoreCase")
    void testEx4() {
        String out = invokeAndCapture("Ex4");
        var lines = splitLines(out);

        var expected = List.of("Ionut", "Madalina", "Raul", "Stefan", "Teodor", "Victor")
                .stream()
                .sorted(String::compareToIgnoreCase)
                .toList();

        var printed = lines.stream().filter(s -> !s.isBlank()).toList();

        assertEquals(expected, printed);
    }
}
