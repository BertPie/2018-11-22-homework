import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        /** SOLUTION #1 - using String and chars() */

        Long l = Stream.of("hello", "academy", "java", "junior")
                .reduce("", (acc, s) ->acc+s)
                .chars()
                .distinct()
                .count();

        System.out.println(l);

        /** SOLUTION #2 - USING charbuffer */

        // NOT WORKING

        Stream.of("hello", "academy", "java", "junior")
                .distinct()
                .peek(System.out::println)
                .count();

        // NOT WORKING

    }
}
