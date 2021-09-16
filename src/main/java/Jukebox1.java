import java.util.*;

public class Jukebox1 {

    // QU: Should this be a List or ArrayList? Have we covered this?
    ArrayList<String> songList = new ArrayList<>();

    public static void main(String[] args) {
        new Jukebox1().go();
    }

    public void go() {
        Songs s = new Songs();
        songList = s.getSongs();
        System.out.println(songList);
    }
}

// Below is the "mock" code. A stand in for the actual
// I/O code that the other programmer will provide later

class Songs {
    public ArrayList<String> getSongs() {
        ArrayList<String> songDB = new ArrayList<>();
        songDB.add("somersault");
        songDB.add("cassidy");
        songDB.add("$10");
        songDB.add("havana");
        songDB.add("Cassidy");
        songDB.add("50 Ways");
        return songDB;
    }
}