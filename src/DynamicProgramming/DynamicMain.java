package DynamicProgramming;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Author: SACHIN
 * Date: 3/4/2016.
 */
public class DynamicMain {

    public interface Func<T>{
        T get(T elem);
    }
    public static void main(String[] args) {

//        Func<Param<Integer>> f = Param<Integer>::new;
        Param<Integer> obj1 = new Param<>();
        Supplier<Param<Integer>> obj = Param<Integer>::new;
        System.out.println(obj.get());
        System.out.println(obj.get());
        Param<Integer> param = obj.get();
        Consumer<Integer> c = param::setElem;
        Supplier<Integer> s = param::getElem;
        c.accept(10);
        System.out.println(s.get());

        Function<String,String> func = Param::<String>returnSame;

        System.out.println("Something");
    }
}
