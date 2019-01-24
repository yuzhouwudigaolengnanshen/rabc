package test;

import java.util.Arrays;

public class TestStream {

    public static void main(String[] args) {
        String[] ids = {"1","2","3"};
        int[] ints = Arrays.stream(ids).mapToInt(s -> Integer.valueOf(s)).toArray();
        for (Integer integer : ints) {
            System.out.println(integer);
        }
    }
}
