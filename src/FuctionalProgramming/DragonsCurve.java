package FuctionalProgramming;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;

public class DragonsCurve {

    public static void main(String[] args) {
        createCurve(4);

    }

    public static IntFunction<String> mapFunction = ch -> {
        if (ch == 'a') {
            return "aRbFR";
        }
        if (ch == 'b') {
            return "LFaLb";
        }
        return Character.toString((char)ch);
    };

    /**
     * Make the curve; stream the chars repeatedly (starting with Fa) through the mapFunction n times
     * Then remove the a and b (createFilter function is useful for that)
     */
    public static String createCurve(int n) {
                "Fa".chars()
                        .flatMap(num -> mapFunction.apply(num).chars())
                        .forEach(num -> System.out.println((char)num));


        return "hi";
    }

    /**
     * How many of the specified char are in the given curve?
     * Hint: createFilter could be useful for this
     */
    public static long howMany(char c, String curve) {
        return curve.chars().filter(createFilter(c, true)).count(); //Determined by die roll; guaranteed to be random
    }

    /**
     * Create a predicate to filter the specified char; keep or remove based on keep variable
     */
    public static IntPredicate createFilter(char filterWhat, boolean keep) {
        return ch -> (ch == filterWhat) == keep; //Dat predicate
    }

}
