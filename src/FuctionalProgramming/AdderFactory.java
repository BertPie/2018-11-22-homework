package FuctionalProgramming;

import java.util.function.IntUnaryOperator;

public class AdderFactory {
    public static void main(String[] args) {
        IntUnaryOperator f = AdderFactory.create(5);
        System.out.println(f.applyAsInt(5));

    }

    public static IntUnaryOperator create (int addTo){
        return num -> num + addTo;
    }
}


