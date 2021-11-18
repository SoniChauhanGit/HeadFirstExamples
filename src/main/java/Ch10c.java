import java.util.Arrays;
import java.util.List;

public class Ch10c {
    void forExample() {
        List<String> allColors = List.of("Red", "Blue", "Yellow");
        for (String color : allColors) {

            System.out.println(color);
        }
    }

    void forEachExample() {
        List<String> allColors = List.of("Red", "Blue", "Yellow");
        allColors.forEach(color -> System.out.println(color));
    }

    public static void main(String[] args) {
        badFor4();
    }

    // 2, 3, 4, 5
    static void badFor1() {
        int[] ints = {1, 2, 3, 4, 5};
        for (int i = 1; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    //Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
    //	at Ch10c.badFor2(Ch10c.java:31)
    //	at Ch10c.main(Ch10c.java:18)
    static void badFor2() {
        int[] ints = {1, 2, 3, 4, 5};
        for (int i = 0; i <= ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    // Doesn't compile
    static void badFor3() {
//        int[] ints = {1, 2, 3, 4, 5};
//        for (int i = 0; i < ints.size(); i++) {
//            System.out.println(ints[i]);
//        }
    }

    // [I@c818063, [I@c818063, [I@c818063, [I@c818063, [I@c818063
    static void badFor4() {
        int[] ints = {1, 2, 3, 4, 5};
        for (int anInt : ints) {
            System.out.println(ints);
        }
    }

    // doesn't compile
    static void badFor5() {
//        int[] ints = {1, 2, 3, 4, 5};
//        for (int int : ints) {
//            System.out.println(ints);
//        }
    }

    static void badFor6() {
        int[] ints = {1, 2, 3, 4, 5};
        Arrays.stream(ints).forEach(value -> System.out.println(value));
    }



}
