package FuctionalProgramming;

import java.util.function.ToDoubleFunction;

public class TriangleAreaFunction {
    public static void main(String[] args) {
        Triangle t = new Triangle(5, 10);
        System.out.println(f.applyAsDouble(t));
    }

    public static ToDoubleFunction<Triangle> f = t -> {
        double result = (1D / 2D) * (t.height * t.base);
        t.setArea(result);
        return result;
    };
}

/**
 * Write a function that sets the area of the given Triangle and returns the area as a double. Do not use the function type Function; there is a function type for converting an arbitrary class into a double, use that.
 * <p>
 * The formula for triangle area is: 1/2 * base * height
 * <p>
 * Assume valid input (base and height are both greater than 0).
 * <p>
 * A full listing of the default function types can be found at http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 */

class Triangle {
    public final int height;
    public final int base;
    private double area;

    public Triangle(int height, int base) {
        this.height = height;
        this.base = base;
    }

    public void setArea(double a) {
        area = a;
    }

    public double getArea() {
        return area;
    }
}