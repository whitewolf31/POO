package problema2;

import java.util.*;

/**
 * Created by mihai
 */

public class ListUtil {
    public static <A, B> A foldl(Function<A, Function<B, A>> function, A acc, List<B> xs) {
        Iterator<B> it = xs.iterator();
        while (it.hasNext()) {
            B current = it.next();
            acc = function.execute(acc).execute(current);
        }

        return acc;
    }

    public static <A> void reverse(List<A> xs) {
        for (int i = 0, j = xs.size() - 1; i < j; i++) {
            xs.add(i, xs.remove(j));
        }
    }

    public static <A, B> A foldr(Function<A, Function<B, A>> function, A acc, List<B> xs) {
        reverse(xs);
        return foldl(function, acc, xs);
    }

    public static <A, B> List<B> map(Function<A, B> function, List<A> xs) {
        List<B> newList = new ArrayList<B>();
        Iterator<A> it = xs.iterator();
        while (it.hasNext()) {
            A current = it.next();
            newList.add(function.execute(current));
        }

        return newList;
    }

    public static <A> List<A> filter(Function<A, Boolean> function, List<A> xs) {
        Iterator<A> it = xs.iterator();
        List<A> newList = new ArrayList<A>();
        while (it.hasNext()) {
            A current = it.next();
            if (function.execute(current)) newList.add(current);
        }

        return newList;
    }

    public static <A> A reduce(Function<A, Function<A, A>> function, List<A> xs) {
        A reduced = xs.get(0);
        for (int i = 1; i < xs.size(); i++) {
            reduced = function.execute(reduced).execute(xs.get(i));
        }

        return reduced;
    }

    public static <A> boolean all(Function<A, Boolean> function, List<A> xs) {
        boolean res = true;
        Iterator<A> it = xs.iterator();
        while (it.hasNext()) {
            res &= function.execute(it.next());
        }

        return res;
    }

    public static <A> boolean any(Function<A, Boolean> function, List<A> xs) {
        boolean res = false;
        Iterator<A> it = xs.iterator();
        while (it.hasNext()) {
            res |= function.execute(it.next());
        }

        return res;
    }

    public static <A extends Number> A sum(List<A> xs, Addition<A> operation) {
        A totalSum = operation.zero();
        Iterator<A> it = xs.iterator();
        while (it.hasNext()) {
            A element = it.next();
            totalSum = operation.add(totalSum, element);
        }

        return totalSum;
    }
}
