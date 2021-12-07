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
    List<Song> allSongs = songs.getSongs();

    List<Song> result = allSongs.stream()
                                 .sorted(Comparator.comparingInt(Song::getTimesPlayed))
                                 .collect(Collectors.toList());

    System.out.println(result);
  }


}