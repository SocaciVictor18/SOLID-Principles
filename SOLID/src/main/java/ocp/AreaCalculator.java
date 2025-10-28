package ocp;

import java.util.List;

// Calculator depends on the abstraction (Shape). To add a Triangle, add a new class — no edits here.
public class AreaCalculator {
    public double totalArea(List<Shape> shapes) {
        return shapes.stream()
                .mapToDouble(Shape::area)
                .sum();
    }
}
