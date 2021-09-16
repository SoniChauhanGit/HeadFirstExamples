import java.util.ArrayList;
import java.util.Collections;

public class Jukebox2 {

    public static void main(String[] args) {
        new Jukebox2().go();
    }

    public void go() {
        Songs s = new Songs();
        ArrayList<String> songList = s.getSongs();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println("\n" + songList);
    }
}
