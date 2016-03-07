package Practise2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by sachin on 2/26/2016.
 */
public class LamdaExample {
    public static void main(String[] args) {

        Predicate<Integer> isOdd = n -> n%2!=0;
        System.out.println(isOdd.test(7));
        BinaryOperator<Integer> sum = (x, y)->x+y;
        System.out.println(sum.apply(5,7));
        Callable<Integer> callMe = ()->42;
        try {
            System.out.println(callMe.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hello");
        Runnable runner=()-> {
            System.out.println(sum.apply(6, 7));
        };
        runner.run();
        Consumer<String> c = (x) -> System.out.println(x.toLowerCase());
        c.accept("Java2s.com");
        Supplier<String> i = ()->"Parameter Passed to VAR";
        System.out.println(i.get());

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);

        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5,10);
        List<Integer> squaresList = numbers.stream().map( j -> j*j).distinct().collect(Collectors.toList());
        System.out.println(squaresList);

    }
}
