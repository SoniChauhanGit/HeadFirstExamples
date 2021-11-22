import java.util.Collections;
import java.util.List;

public class Jukebox2 {

  public static void main(String[] args) {
    new Jukebox2().go();
  }

  public void go() {
    Songs s = new Songs();
    List<String> songList = s.getSongs();
    System.out.println(songList);
    Collections.sort(songList);
    System.out.println("\n" + songList);
  }
}
