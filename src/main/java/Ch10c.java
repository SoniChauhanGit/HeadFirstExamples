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
}
