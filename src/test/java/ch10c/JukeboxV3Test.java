package ch10c;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class JukeboxV3Test {
  private JukeboxData.Songs songs;

  @BeforeEach
  void setUp() {
    songs = new JukeboxData.Songs();
  }

  @Test
  void shouldOutputAllSongsPlayed() {
    List<Song> allSongs = songs.getSongs();

    System.out.println(allSongs);
  }

  @Test
  void shouldOrderByLeastPlayed() {
    // this is impossible!!!!
    // back to square one
//    List<Song> result = log.stream()
//                           .map(logEntry -> songs.getSongById(logEntry.id))
//            .sorted(Comparator.comparingInt())
//                           .collect(Collectors.toList());
//
//    System.out.println(result);
//
  }
}