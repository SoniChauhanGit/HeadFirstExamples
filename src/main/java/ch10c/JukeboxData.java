package ch10c;

import java.util.List;

public class JukeboxData {
    class Songs {

        public List<Song> getSongs() {
            return List.of(
                    new Song("somersault", "zero 7", 50),
                    new Song("cassidy", "grateful dead", 65),
                    new Song("$10", "hitchhiker", 80),
                    new Song("havana", "cabello", 60),
                    new Song("Cassidy", "grateful dead", 65),
                    new Song("50 ways", "simon", 70));
        }
    }
}
