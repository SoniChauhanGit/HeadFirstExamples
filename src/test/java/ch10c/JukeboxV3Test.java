package ch10c;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class JukeboxV3Test {
  @Test
  void shouldOutputAllSongsPlayed() {
    JukeboxData.Songs songs = new JukeboxData.Songs();
    List<Log> log = new Log().getLog();

    System.out.println(log);

    List<Song> allSongs = log.stream().map(log1 -> songs.getSongById(log1.id))
                            .collect(Collectors.toList());

    System.out.println(allSongs);
  }
}