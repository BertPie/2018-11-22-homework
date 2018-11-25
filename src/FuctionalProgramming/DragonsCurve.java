package FuctionalProgramming;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

public class DragonsCurve {

    public static void main(String[] args) {
        System.out.println(createCurve(7));
    }

    public static IntFunction<String> mapFunction = ch -> {
        if (ch == 'a') {
            return "aRbFR";
        }
        if (ch == 'b') {
            return "LFaLb";
        }
        return Character.toString((char) ch);
    };

    public static String createCurve(int n) {
        String string = "Fa";

        return createCurve(n--, string).chars()
                .filter(createFilter('a', false))
                .filter(createFilter('b', false))
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    public static String createCurve(int n, String string) {
        if(n <= 0){
            return string;
        }
        String nextString = string.chars()
                .flatMap(ch -> mapFunction.apply(ch).chars())
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        return createCurve(--n, nextString);
    }

    public static long howMany(char c, String curve) {
        return curve.chars().filter(createFilter(c, true)).count(); //Determined by die roll; guaranteed to be random
    }

    public static IntPredicate createFilter(char filterWhat, boolean keep) {
        return ch -> (ch == filterWhat) == keep;
    }
}

/**
 *We're going to be calculating the Dragon's Curve (and a few metrics about it) using Java streams. (Pre-existing kata: http://www.codewars.com/kata/dragons-curve).
 *
 * The Dragon's Curve is a fractal, the path of which can be easily determined programmatically.
 *
 * Start with the string "Fa"
 * Replace all "a" with "aRbFR" and all "b" with "LFaLb"
 * That is (spaces added for clarity):
 *
 * Iteration 0: Fa
 * Iteration 1: Fa -> F aRbFR
 * Iteration 2: FaRbFR -> F aRbFR R LFaLb FR
 * You will do this "n" times. Then, remove all "a" and "b". The remaining string will contain only FRL (the commands go Forward, turn Right, and turn Left). On a grid, tracing the series of commands results in the Dragon's Curve.
 *
 * You will create a series of streams that create the Dragon's Curve, then analyze the Dragon's Curve.
 *
 * First, you'll want to create a mapping IntFunction to turn 'a' and 'b' into "aRbFR" and "LFaLb". The tests count how many times the mapFunction is used during createCurve, so make sure to actually use your mapFunction and don't just implement a different solution (that's why mapFunction is public; don't make it private).
 *
 * You'll also want to create a filterFactory method to generate IntPredicate functions. The factory will accept a char (to filter) and a boolean (whether we are keeping that char or excluding that char) and return an IntPredicate that filters appropriately.
 *
 * Finally, you need a howMany function that takes a char and a Dragon's Curve String and returns how many instances of char are in the curve (hint: use the filterFactory you just made).
 *
 * IntFunction/IntPredicate? I'm working with Strings and Characters!
 * Funny thing about Java: all Strings are CharSequences (close cousin to char[]). And all chars are ints (don't believe me? 'a' == 97. Try it out.) So, when Oracle was deciding what special functions to include by default in Java 8, instead of adding char-specific functions--like they did for the other primitives--they assume you'll be using the int-specific functions.
 *
 * What you do is use the String function chars() (e.g., "Fa".chars()) to generate an IntStream. You can then use this IntStream like you would any other, and map, filter, and collect to your heart's content. But, since you're playing with ints, you do need to make sure you're converting your ints back to chars when needed ('a' == 97 might be true, but "" + 97 is still "97" and not "a").
 *
 * There are some streams in the Example Test Cases, but not quite in the way you'll be using them.
 *
 * Java streams: http://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html
 *
 * Java functions: http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 *
 * You'll pobably want to reference the IntStream in particular: http://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html
 *
 * Collectors are also quite useful (stream.collect()):
 *
 * http://docs.oracle.com/javase/8/docs/api/java/util/stream/Collector.html
 * http://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html
 */